import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class file_manager {

	
		
		static float[][][] DATA;
		static int[] cable_position;
		
	    public static void main(String[] args) {
	    	/*
	        String filename="C:\\Users\\Home\\Desktop\\2026\\segd_test\\00000111.segd";
	        byte[] b=segd2byte(filename);
	        read_gh(b);
	        chan_set(b);
	        amplitudes();
	        */
	        
	        
	       
	        
	    }
	    
		public static float[] filter(float[] IN)
		{
			int a=mat.findpow(IN.length);
			float[] B=new float[a*2+1];
			for(int i=0;i<IN.length;i++)
			{
				B[i*2+1]=IN[i];	
			}
			
			float[] X = FFT.four1(B, a, 1);
			float[] Z=FFT.low(X, 3, sample_int);
			float[] Y=	FFT.four1(Z, a,-1);
			
			
			float[] D=new float[IN.length];
			for(int i=0;i<IN.length;i++)
			{
				D[i]=Y[i*2+1];
			}
			return D;
		}
	
	
 	public String findpath(String pn)
 	{ 		
 		String output;
 		output="data//SEGD//"+pn;
 		System.out.println(output); 		
 		return output;
 	}
 	
	public  String finder(String filepath,int shot)
	{
	     String filename=" ";
		 File folder = new File(filepath);
		 String[] fileNames = folder.list();
		 String sh=String.valueOf(shot);
		 
							 
		 for(int i=0;i<fileNames.length;i++)
		 {
			
			Pattern p = Pattern.compile("(?<![1-9])0*" + sh + "\\.segd$");
	        Matcher m = p.matcher(fileNames[i]);
	        
	        if (m.find()) {
	            filename=filepath+"//"+fileNames[i];
	          
	        } else {
	        	
	        					        	
	        }
	    }
		 
		return filename;
	}
	
	
	public static float[][] amplitudes_xfeed(String filename,int number_of_streamers)
	{
		  byte[] b=segd2byte(filename);
	        read_gh(b);
	        chan_set(b);
		
		
		
		 DATA=new float[number_of_streamers][][];
		 float[][] OUTPUT=new float[number_of_streamers][number_of_sample];
	        cable_position=new int[number_of_streamers];
	        for(int i=1;i<=number_of_streamers;i++)
	        {
	        	for(int j=0;j<number_of_chan_set;j++)
	        	{
	        		if(i==cable_no[j])
	        		{
	        			
	        			int number_of_chan=chan_number[j];
	        			DATA[i-1]=new float[number_of_chan][number_of_sample];
	        			   int top=0;
	        			for(int k=0;k<j;k++)
	        			{
	        				top=top+chan_number[k];
	        			}
	        			cable_position[i-1]=top;
	        			
	        		}
	        	}
	        }
	        
	        
	        int header_size=0;
	        int start_pos=(add_blocks+1)*32+number_of_chan_set*32+extended+extern;
	        
	        
	        if(number_of_chan_set==1)
	        	header_size=chan_header_size[0];
	        else if(chan_header_size[1]==chan_header_size[0])
	    	    header_size=chan_header_size[0];
	        
	        int amp_size=number_of_sample*sample_size;
	        int trace_size=header_size+amp_size;
	        
	        byte[] AMP;
	        FloatBuffer fb;
			float[] input=new float[number_of_sample];
	        
			
			for(int i=0;i<number_of_streamers;i++)
			{
				int pos=start_pos+cable_position[i]*trace_size;
				
				for(int j=0;j<DATA[i].length;j++)
				{
					  int pos1=pos+trace_size*j+header_size;
					  AMP = Arrays.copyOfRange(b, pos1, pos1+amp_size);
					  fb = ByteBuffer.wrap(AMP).asFloatBuffer();
					  input = new float[number_of_sample];
					  fb.get(input);
					  DATA[i][j]=filter(input);
				}
			}
			
			float sum=0;
			for(int i=0;i<number_of_streamers;i++)
			{	
				for(int j=0;j<number_of_sample;j++)
				{
					sum=0;
					for(int k=0;k<DATA[i].length;k++)
					{
						sum=sum+DATA[i][k][j];
					}
					sum=sum/DATA[i].length;
					OUTPUT[i][j]=sum;
				}
			}
			
			return OUTPUT;
	}
	
	//SEGD READ CLASS
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
	 
		public static String byte_hex(byte[] a) {
			StringBuilder sb = new StringBuilder(a.length * 2);
			for (byte b : a)
				sb.append(String.format("%02x", b));
			return sb.toString();
		}
		
		static String format_code,external;
		static int add_blocks,number_of_chan_set,extended,file_number,extern,record_length,number_of_sample,sp_number,sample_size;
		static float sample_int;
	 
	 
	 public static void read_gh(byte[] b)
	 {
		 byte[] g1;
			//General Header-1
			g1= Arrays.copyOfRange(b, 0, 32);
			String GH = byte_hex(g1);
			String sp=GH.substring(0,4);
			format_code=GH.substring(4,8);
			add_blocks=Integer.parseInt(GH.substring(22, 23));
			sample_int=Float.parseFloat(Hex_Dec(GH.substring(44, 46)))/16f;
			number_of_chan_set=Integer.parseInt(GH.substring(56, 58));
			extended=Integer.parseInt(GH.substring(60, 62))*32;
			String rl=GH.substring(51,54);
			external=GH.substring(62, 64);
			
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
			if(external.equals("ff"))
			{
				extern=32*Integer.parseInt(Hex_Dec(GH.substring(14, 18)));
			}
			else
			{
				extern=32*Integer.parseInt(external);
			}
			if(rl.equals("fff"))
			record_length=Integer.parseInt(Hex_Dec(GH.substring(28, 34)));
			else
			record_length=Integer.parseInt(rl);	
			number_of_sample=(int)((float)record_length/(float)sample_int)+1;
			
			//General Header-3
			g1 = Arrays.copyOfRange(b, 64, 96);
			GH = byte_hex(g1);
			sp_number=Integer.parseInt(Hex_Dec(GH.substring(16, 22)));
			
			
			sample_size=0;
			if(format_code.equals("8058"))
				sample_size=4;
			
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
	 
	 
	 	static int[] chan_id;					// chan id 
		static int[] chan_number;				// number of channel per chan set
		static int[] chan_header_size;
		static int[] cable_no;
		
	 
	 public static void chan_set(byte[] b)
	 {
		 chan_id=new int[number_of_chan_set];
		 chan_number=new int[number_of_chan_set];
		 chan_header_size=new int[number_of_chan_set];
		 cable_no=new int[number_of_chan_set];
		 
		
		 
		 for(int i=0;i<number_of_chan_set;i++)
		 {
			 int pos= (add_blocks+1)*32+i*32;
			 byte[] ext = Arrays.copyOfRange(b, pos, pos+32);
			 String headerText = byte_hex(ext);

			 chan_number[i]=Integer.parseInt(headerText.substring(16, 20));
			 chan_id[i]=Integer.parseInt(headerText.substring(20, 21));
			 chan_header_size[i]=Integer.parseInt(headerText.substring(57, 58))*32+20;
			 cable_no[i]=Integer.parseInt(headerText.substring(61, 62));
			 
			 
		 }
	 }
	
	
}
