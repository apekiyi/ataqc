import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;

public class segd_test_read {
	
	public static void main(String[] args)
	{
		String filename="H:\\TAPE0001.REEL\\R000001_1302613295.RAW";
		String filename2="H:\\DATAEX\\TPDM20-0042P4086\\4266.segd";
		String filename3="H:\\ATAQC\\TPDM200040P1088d\\00001007.segd";
		String filename4="F:\\Projeler\\TEST\\MGL2310\\00000018.segd";
		segd_test_read s=new segd_test_read();
		s.readsegd(filename);
	}
	
	int streamer=1;
	byte[] b1;
	
	public void readsegd(String filename)
 	{
		
		File klasor = new File("H:\\TAPE0001.REEL\\");
        
        // Klasördeki tüm dosyaları bir diziye al
        File[] dosyalar = klasor.listFiles();
        
        for (File dosya : dosyalar) {
        	filename = dosya.getAbsolutePath();
        	byte[] b=segd2byte(filename); //segd verisi byte array alınır
     		b1=new byte[b.length];
     		read_gh(b);
     		chan_set(b);				  //chat set parametreleri çekilir
     		amplitudes(b,streamer);
        }
		
 		
 		
 		
 		
 		/*
 		chan_set(b);				  //chat set parametreleri çekilir
 //		saveBytePart(b, 32*(add_blocks+1)+32*number_of_chan_set,extended,"deneme.dat");
 		
 		
 		
 		
 		
 		
 		/*
 		external(b);				  // gun parametreleri okunur
 		amplitudes(b,streamer);
 		info(b);
 		*/
 	}
	
	public void amplitudes(byte[] b,int cable)
	{
		 //Farklı formatlara göre geliştirilecektir
		 int sample_size=0;
		 if(format_code.equals("8058"))
			 sample_size=4;
		 if(format_code.equals("8036"))
			 sample_size=2;
		 if(format_code.equals("8038"))
			 sample_size=4;
		 
		 byte[] g1;
		 int pos=(add_blocks+1)*32+extended+external+number_of_chan_set*32;
	      g1= Arrays.copyOfRange(b,pos , pos+20);
	      String headerText = byte_hex(g1);
	      int header_ext= Integer.parseInt(headerText.substring(10, 12));
	      if(header_ext==0)
	      number_of_samples=(int)(record_length/sample_int);
	      else
	      {
	    	  g1= Arrays.copyOfRange(b,pos+20,pos+52);
		      headerText = byte_hex(g1);
		      number_of_samples=Integer.parseInt(Hex_Dec(headerText.substring(16, 20))); 
	      }
	      
	      int trace_size=number_of_samples*sample_size+20+header_ext*32;
	      
	      System.arraycopy(b, 0, b1, 0, pos);
	      
	    
	    for(int i=0;i<chan_number.length;i++)
	    {
	    	 for(int j=0;j<chan_number[i];j++)
	 	    {
	 	    	int s1=trace_size*j;
	 	    	int s2=trace_size*(chan_number[i]-(j+1));
	 	    	 System.arraycopy(b, pos+s2, b1,pos+s1,trace_size);
	 	    }
	    	 
	    	 pos=pos+trace_size*chan_number[i];
	    }

	    String ffid=String.valueOf(file_number);
	    
	    try (FileOutputStream fos = new FileOutputStream(ffid);
	    	     BufferedOutputStream bos = new BufferedOutputStream(fos)) {
	    	    
	    	    bos.write(b1); // b1 dizisinin tamamını dosyaya yazar
	    	    bos.flush();   // Bellekte kalan son verileri diske iter
	    	    
	    	    System.out.println("Dosya başarıyla yazıldı!");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
	   
	   
		 
	}
	
	
	
	
	
    int[] chan_number,chan_id,cable_no;				// number of channel per chan set
    int number_of_samples;
	
    public void chan_set(byte[] b)
	 {
		 
		 chan_id=new int[number_of_chan_set];
		 chan_number=new int[number_of_chan_set];
		 cable_no=new int[number_of_chan_set];
		 
		
		 
		 for(int i=0;i<number_of_chan_set;i++)
		 {
			 int pos= (add_blocks+1)*32+i*32;
			 byte[] ext = Arrays.copyOfRange(b, pos, pos+32);
			 String headerText = byte_hex(ext);

			 chan_number[i]=Integer.parseInt(headerText.substring(16, 20));
			 chan_id[i]=Integer.parseInt(headerText.substring(20, 21));
			 cable_no[i]=Integer.parseInt(headerText.substring(61, 62));
		 }
	 }
	
	public static byte[] segd2byte(String filename)
	 {
		 File f=new File(filename);
			byte[] b = new byte[(int) f.length()];
			
			

			RandomAccessFile raf;
				try {
					raf = new RandomAccessFile(f,"r");
					raf.read(b);
					raf.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return b;
	 }
	
	int year,day,hour,sec,min;
	float sample_int;
 	String format_code;
	float record_length;
	int add_blocks;
	int number_of_chan_set;
	int extended=0,external=0;
	String extern,extend;
	int file_number;
	
	 public  void read_gh(byte[] b)
	 {
		 byte[] g1;
			//General Header-1
			g1= Arrays.copyOfRange(b, 0, 32);
			String GH = byte_hex(g1);
			String sp=GH.substring(0,4);
			format_code=GH.substring(4,8);
			add_blocks=Integer.parseInt(GH.substring(22, 23));
			year=Integer.parseInt(GH.substring(20, 22));
			day=Integer.parseInt(GH.substring(23, 26));
			hour=Integer.parseInt(GH.substring(26, 28));
			min=Integer.parseInt(GH.substring(28, 30));
			sec=Integer.parseInt(GH.substring(30, 32));
			sample_int=Float.parseFloat(Hex_Dec(GH.substring(44, 46)))/16f;
			number_of_chan_set=Integer.parseInt(GH.substring(56, 58));
			extend= GH.substring(60, 62);
			String rl=GH.substring(51,54);
			extern=GH.substring(62, 64);
			
			//General Header-2
			byte[] g2 = Arrays.copyOfRange(b, 32, 64);
			GH = byte_hex(g2);
			file_number=0;
			if(sp.equals("ffff"))
			{	
			file_number=Integer.parseInt(Hex_Dec(GH.substring(0, 6)));	
			}
			else
			{
			file_number=Integer.parseInt(sp);
			}
			if(extern.equals("ff"))
			{
				external=32*Integer.parseInt(Hex_Dec(GH.substring(14, 18)));
			}
			else
			{
				external=32*Integer.parseInt(extern);
			}
			if(extend.equals("ff"))
			{
				extended=32*Integer.parseInt(Hex_Dec(GH.substring(10, 14)));
			}
			else
			{
				extended=32*Integer.parseInt(extend);
			}
			
			
			if(rl.equals("fff"))
			record_length=Integer.parseInt(Hex_Dec(GH.substring(28, 34)));
			else
			record_length=(int)(Float.parseFloat(rl)*102.4f);
			
			/*
			//General Header-3
			g1 = Arrays.copyOfRange(b, 64, 96);
			GH = byte_hex(g1);
			sp_number=Integer.parseInt(Hex_Dec(GH.substring(16, 22)));
			
			SP.add(sp_number);
			FFID.add(file_number);
			*/
			
	 }
	 
		public static String byte_hex(byte[] a) {
			StringBuilder sb = new StringBuilder(a.length * 2);
			for (byte b : a)
				sb.append(String.format("%02x", b));
			return sb.toString();
		}
		
		public static String Hex_Dec(String S) {


			String output;
			int L = S.length();
			int C = 0;

			for (int i = 0; i < L; i++) {
				int b = 0;
				String a = S.substring(i, i + 1);
				if (a.equals("0"))
					b = 0;
				if (a.equals("1"))
					b = 1;
				if (a.equals("2"))
					b = 2;
				if (a.equals("3"))
					b = 3;
				if (a.equals("4"))
					b = 4;
				if (a.equals("5"))
					b = 5;
				if (a.equals("6"))
					b = 6;
				if (a.equals("7"))
					b = 7;
				if (a.equals("8"))
					b = 8;
				if (a.equals("9"))
					b = 9;
				if (a.equals("a"))
					b = 10;
				if (a.equals("b"))
					b = 11;
				if (a.equals("c"))
					b = 12;
				if (a.equals("d"))
					b = 13;
				if (a.equals("e"))
					b = 14;
				if (a.equals("f"))
					b = 15;

				C += Math.pow(16, L - (i + 1)) * b;
			}
			output = String.valueOf(C);

			return output;
		}
		
		public static void saveBytePart(byte[] data, int offset, int length, String outputFileName) {
	        // Çıktı klasörünü kontrol et (opsiyonel)
	        File file = new File(outputFileName);
	     

	        try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
	            // write(byte[] b, int off, int len) metodu tam olarak istediğiniz işi yapar
	            fos.write(data, offset, length);
	            System.out.println("Dosya başarıyla yazıldı: " + outputFileName);
	        } catch (IOException e) {
	            System.err.println("Yazma hatası: " + e.getMessage());
	        }
	    }

}
