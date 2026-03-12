import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class Main {

JFrame F;
	int len;
	JTextField[] T;
	JComboBox C=new JComboBox();
	int[] rows;
	//public String project_name="No Project Selection";
	public String project_name;
	public String program_name="ATAQC";
	public String[] ListNames={"Line Name","Sequence No","First Shot Point","Last Shot Point","First File Number","Last File Number"};
	JDialog D,DA;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.F.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() throws IOException {
		initialize();
	}
	
	JPanel BUTTONPANEL;
	JPanel LISTPANEL;
	JPanel REPORTPANEL;
	JScrollPane REPORTFOLDER,REPORTFILE;
	JButton[] BUTTON;
	JScrollPane LISTSCROLL;
	JButton Add_Seq,Remove_Seq,Refresh_Seq;
	JTable JT,JT1,JT2;
	DefaultTableModel TM;
	DefaultTableModel TM1,TM2;
	
	JFrame sf;
	int kk;
	
	public void startpage()
	{
		sf=new JFrame();
		sf.setVisible(true);
		sf.dispose();
		sf.setBounds(600,500, 900,500);
		sf.setUndecorated(true);
		sf.setResizable(false);	
		D.setVisible(true);
		
	}
	
	
	private void initialize() throws IOException {
		// TODO Auto-generated method stub
	
		F=new JFrame();
		PROJECT();
		startpage();
		
		if(project_name==null)
		System.exit(0);
		F.setBounds(100, 100, 1500, 1000);
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		F.setTitle(program_name+"-"+project_name);
		F.setLayout(null);
		Image img=new ImageIcon("ICONS//MTA.png").getImage();
		F.setIconImage(img);
		components();
		LISTPANEL.setVisible(true);
		BUTTON[0].setBackground(Color.cyan);
		
		 F.addComponentListener(new ComponentAdapter() {
	            public void componentResized(ComponentEvent e) {
	            	resize();
	            }
	            });
		 
		 F.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent e) {
			        try {
			            update_file(); // Kapanırken son bir kez kaydet
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			    }
			});
		 
	   
	}
	
	
	public void update_file1()
	{
		while(TM2.getRowCount()>0)
		{
			TM2.removeRow(0);
		}
		for(int j=0;j<rows.length;j++)
		{
			String fname=(String)TM1.getValueAt(rows[j], 0);
			String pname="PROJECTS/"+project_name+"/"+fname;
			File f=new File(pname);
			File[] ff=f.listFiles();
			for(int i=0;i<ff.length;i++)
			{
				Object[] o=new Object[4];
				o[0]=fname;
				o[1]=ff[i].getName();
				o[2]=ff[i].length();
				String a="dd.MM.yyyy HH:mm:ss";
				SimpleDateFormat sdf=new SimpleDateFormat(a);
				String output=sdf.format(ff[i].lastModified());
				o[3]=output;
				TM2.addRow(o);
			}
			
		}
		
		
		
	}
	
	 int ref_control=0;
	
	public void components() throws IOException
	{
		
		BUTTONPANEL=new JPanel();
		BUTTONPANEL.setLayout(null);
		BUTTONPANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		F.add(BUTTONPANEL);
		
		 BUTTON=new JButton[11];
		 for(int i=0;i<BUTTON.length;i++)
		 {
		ImageIcon icon=new ImageIcon("ICONS//"+"B"+String.valueOf(i)+".png");
		BUTTON[i]=new JButton(icon);
		BUTTON[i].setBounds(20*(i+1)+64*i, 8, 64, 64);
		BUTTONPANEL.add(BUTTON[i]);
		 }
		 BUTTON[0].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	 REPORTPANEL.setVisible(false);
		        	 LISTPANEL.setVisible(true);
		        	 BUTTON[0].setBackground(Color.cyan);
		        	 BUTTON[1].setBackground(BUTTON[5].getBackground());
		        	 BUTTON[2].setBackground(BUTTON[5].getBackground());
		        }
		        });
		 BUTTON[1].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	 REPORTPANEL.setVisible(true);
		        	 LISTPANEL.setVisible(false);
		        	 BUTTON[0].setBackground(BUTTON[5].getBackground());
		        	 BUTTON[1].setBackground(Color.cyan);
		        	 BUTTON[2].setBackground(BUTTON[5].getBackground());
		        }
		        });
		 
		 BUTTON[2].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	save_changes();
		        }
		        });
		 
		 BUTTON[3].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	check_file();
		        	if(seq_control==0)
		        	{
			        	write_file();
			        	
			        	String[] args=new String[1];
		        		NFH.main(args);
			        	
			        	/*
			        	try {
							Process proc= Runtime.getRuntime().exec("java -jar NFH.jar");
			        	
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						*/
		        	}
		        }
		        });
		
		 BUTTON[4].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	check_ref();
		        	check_file();
		        	if(seq_control==0 && ref_control==0)
		        	{
	//	        		transfer_parameters();
			        	write_file();
			        	String[] args=new String[1];
		        		ALD.main(args);
		        		/*
			        	try {
							Process proc= Runtime.getRuntime().exec("java -jar ALD.jar");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						*/
		        	}
		        }
		        });
		 
		 
		 	BUTTON[5].addActionListener(new ActionListener() {
		        @Override
		        	public void actionPerformed(ActionEvent e) {
		        	check_file();
		        	if(seq_control==0)
		        	{
	        	write_file();
	        	String[] args=new String[1];
        		SEGD.main(args);
	        	
	        	/*
	        	try {
					Process proc= Runtime.getRuntime().exec("java -jar SEGD.jar");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
	        	}
		        }
		 		}
		 );
		 	
		 	BUTTON[6].addActionListener(new ActionListener() {
		        @Override
		        	public void actionPerformed(ActionEvent e) {
		        	check_file();
		        	if(seq_control==0)
		        	{
	        	write_file();
	         	String[] args=new String[1];
        		NT.main(args);
	        	/*
	        	try {
					Process proc= Runtime.getRuntime().exec("java -jar NT.jar");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
	        	}
		        }
		 		}
		 	);
		 	
			BUTTON[7].addActionListener(new ActionListener() {
		        @Override
		     	public void actionPerformed(ActionEvent e) {
		        	check_file();
		        	if(seq_control==0)
		        	{
	        	write_file();
	        	String[] args=new String[1];
        		RMS.main(args);
	        	/*
	        	try {
					Process proc= Runtime.getRuntime().exec("java -jar RMS.jar");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
	        	}
		        }
		        	
		        
		 		}
		 	);
			
			BUTTON[8].addActionListener(new ActionListener() {
				 @Override
			     	public void actionPerformed(ActionEvent e) {
			        	check_file();
			        	if(seq_control==0)
			        	{
		        	write_file();
		        	String[] args=new String[1];
	        		XFEED.main(args);
		        	
		        	/*
		        	try {
						Process proc= Runtime.getRuntime().exec("java -jar XFEED.jar");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					*/
		        	}
			        }
			 		}
		 	);
			
			BUTTON[9].addActionListener(new ActionListener() {
				 @Override
			     	public void actionPerformed(ActionEvent e) {
			        	check_file();
			        	if(seq_control==0)
			        	{
		        	write_file();
		        	String[] args=new String[1];
	        		SHRED.main(args);
		        	
		        	
		        	/*
		        	try {
						Process proc= Runtime.getRuntime().exec("java -jar SHRED.jar");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	*/
		        	}
			        }
			 		}
		 	);
		 	
		 	
		 	BUTTON[10].addActionListener(new ActionListener() {
		        @Override
		        	public void actionPerformed(ActionEvent e) {
		        	JDialog D=new JDialog(F,"Information",true);
		        	D.setLayout(null);
		        	D.setBounds(F.getX()+F.getWidth()/2-150, (F.getY()+F.getHeight())/2-100, 300, 200);
		        	JLabel L=new JLabel("ATAQC Software");
		        	JLabel L1=new JLabel("Version 1.0.0");
		        	JLabel L2=new JLabel("Author: Alican PEKIYI");
		        	L.setBounds(10,10,400,30);
		        	L1.setBounds(10,50,400,30);
		        	L2.setBounds(10,90,400,30);
		        	D.add(L);D.add(L1);D.add(L2);
		        	D.setLayout(null);
		        	D.setResizable(false);
		        	D.setVisible(true);
		        }
		 		}
		 );
		 
		 LISTPANEL=new JPanel();
		 LISTPANEL.setLayout(null);
		 LISTPANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 F.add(LISTPANEL);
		 	
		 //List Panel Components
		 TM=new DefaultTableModel();
		 
		 JT=new JTable();
		 JT.setModel(TM);
		 LISTSCROLL=new JScrollPane(JT);
		 LISTPANEL.add(LISTSCROLL);
		 JT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		
		 
	
		 
		 for(int i=0;i<ListNames.length;i++)
		 {
			TM.addColumn(ListNames[i]); 
		 }
		 
		 
		 update_table();
		 ImageIcon icon=new ImageIcon("ICONS/z0.png");
		 Add_Seq=new JButton(icon);
		 Add_Seq.setBounds(10,9,32,32);
		 Add_Seq.setToolTipText("Add Sequence");
		 LISTPANEL.add(Add_Seq);
		 
		 icon=new ImageIcon("ICONS/z1.png");
		 Remove_Seq=new JButton(icon);
		 Remove_Seq.setBounds(52,9,32,32);
		 Remove_Seq.setToolTipText("Remove Sequence");
		 LISTPANEL.add(Remove_Seq);
		 
		 icon=new ImageIcon("ICONS/A0.png");
		 Refresh_Seq=new JButton(icon);
		 Refresh_Seq.setBounds(94,9,32,32);
		 Refresh_Seq.setToolTipText("Refresh Sequence");
		 LISTPANEL.add(Refresh_Seq);
		 
		 Add_Seq.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        add_sequence();
		        }
		        });
		 Remove_Seq.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        try {
					remove_sequence();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		        }
		        });
		 Refresh_Seq.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        try {
					update_table();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        		
		        }
		        });
		 
		 LISTPANEL.setVisible(false);
		 
		 REPORTPANEL=new JPanel();
		 REPORTPANEL.setLayout(null);
		 REPORTPANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 F.add(REPORTPANEL);
		 
		 TM1=new DefaultTableModel() 
		 {
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		 };
		 
		 TM2=new DefaultTableModel()
		 {
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		 };
		 
		 
		 JT1=new JTable();
		 JT2=new JTable();
		 
		 JT2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				JTable table=(JTable)e.getSource();
				if(e.getClickCount()==2 && table.getSelectedRow()!=-1)
				{
					int a=table.getSelectedRow();
					String folder=(String)table.getValueAt(a, 0);
					String file=(String)table.getValueAt(a, 1);
					report r=new report();
					r.folder=folder;
					r.file=file;
					r.project_name=project_name;
					try {
						r.Start();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			 
		 });
		
		 
		 TM1.addColumn("Folder Name");
		 TM2.addColumn("Version"); TM2.addColumn("Report File");	 TM2.addColumn("Size");	 TM2.addColumn("Modification Date");
		 update_folder();
		 
		 
		 REPORTFOLDER=new JScrollPane(JT1);
		 REPORTFILE=new JScrollPane(JT2);
		 REPORTPANEL.add(REPORTFOLDER); 
		 REPORTPANEL.add(REPORTFILE);
		 
		 JT1.setModel(TM1);
		 JT2.setModel(TM2);
		 REPORTPANEL.setVisible(false);
		 
		 JT1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				rows=JT1.getSelectedRows();
				update_file1();
			}
			 
		 });
		 
		 
		 icon=new ImageIcon("ICONS//A0.png");
		 refreshfolder=new JButton(icon);
		 icon=new ImageIcon("ICONS//A1.png");
		 removefolder=new JButton(icon);
		 refreshfolder.setBounds(10,9,32,32);
		 REPORTPANEL.add(refreshfolder);
		 removefolder.setBounds(52,9,32,32);
		 REPORTPANEL.add(removefolder);
		 
		 refreshfolder.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        update_folder();
		        }
		        });
		 removefolder.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        rows=JT2.getSelectedRows();
		        remove_file();	
		        }
		        });
		 
		
		 
		 
		
	}
	
	//Checking reference point
	public void check_ref()
	{
		File f=new File("PROJECTS/"+project_name+"/refstack.dat");
		if(f.exists())
		{
			seq_control=0;
		}
		else
		{
			  JOptionPane.showMessageDialog(null, "No reference file for comparsion.","No reference file",JOptionPane.ERROR_MESSAGE);
			  ref_control=1;
		}
		
	}
	
	int seq_control=0;
	public void check_file()
	{
		//Check if row is selected or not
		if(JT.getSelectedRow()==-1 || LISTPANEL.isVisible()==false)
		{
			  JOptionPane.showMessageDialog(null, "No sequence is selected.","Select Sequence",JOptionPane.ERROR_MESSAGE);
			  seq_control=1;
		}
		else
		{
			seq_control=0;
			//Check the value of parameters
			String a1,a2,a3,a4;
			a1=(String)JT.getValueAt(JT.getSelectedRow(),2);
			a2=(String)JT.getValueAt(JT.getSelectedRow(),3);
			a3=(String)JT.getValueAt(JT.getSelectedRow(),4);
			a4=(String)JT.getValueAt(JT.getSelectedRow(),5);
			try
			{
				Integer.parseInt(a1);
			}
			catch (NumberFormatException ex)
			{
				 JOptionPane.showMessageDialog(null, "First Shot Point Number is not correct.","Error",JOptionPane.ERROR_MESSAGE);
				  seq_control++;
			}
			try
			{
				Integer.parseInt(a2);
			}
			catch (NumberFormatException ex)
			{
				 JOptionPane.showMessageDialog(null, "Last Shot Point Number is not correct.","Error",JOptionPane.ERROR_MESSAGE);
				  seq_control++;
			}
			try
			{
				Integer.parseInt(a3);
			}
			catch (NumberFormatException ex)
			{
				 JOptionPane.showMessageDialog(null, "First File Number is not correct.","Error",JOptionPane.ERROR_MESSAGE);
				  seq_control++;
			}
			try
			{
				Integer.parseInt(a4);
			}
			catch (NumberFormatException ex)
			{
				 JOptionPane.showMessageDialog(null, "Last File Number is not correct.","Error",JOptionPane.ERROR_MESSAGE);
				  seq_control++;
			}
			
		}
		
		
	}
	
	
	int number_of_array;
	int number_of_string;
	int number_of_chan_per_string;
	int fsp;
	int lsp;
	int ffn;
	int lfn;
	String seqno;
	
	
	
	//Read and send parameters to classes
public void transfer_parameters()
{
	String a="PROJECTS/"+project_name+"/parameters";
	RandomAccessFile raf;
	try {
		raf = new RandomAccessFile(a,"r");
		raf.readUTF();raf.readUTF();raf.readUTF();raf.readUTF();
		String a1=raf.readUTF();String a2=raf.readUTF();String a3=raf.readUTF();
		number_of_array=Integer.parseInt(a1);
		number_of_string=Integer.parseInt(a2);
		number_of_chan_per_string=Integer.parseInt(a3);
		seqno= (String)JT.getValueAt(JT.getSelectedRow(),1);
		a1=(String)JT.getValueAt(JT.getSelectedRow(),2);
		a2=(String)JT.getValueAt(JT.getSelectedRow(),3);
		fsp=Integer.parseInt(a1);
		lsp=Integer.parseInt(a2);
		a1=(String)JT.getValueAt(JT.getSelectedRow(),4);
		a2=(String)JT.getValueAt(JT.getSelectedRow(),5);
		ffn=Integer.parseInt(a1);
		lfn=Integer.parseInt(a2);
		raf.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	}


	
public void readback()
{
	RandomAccessFile raf;
	try {
		raf = new RandomAccessFile("temp.dat","rw");
		int a=raf.readInt();
		for(int i=0;i<a;i++)
		{
			System.out.println(raf.readUTF());
		}
		raf.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	public void write_file()
	{
		RandomAccessFile raf;
		RandomAccessFile raf1;
		try {
			raf = new RandomAccessFile("PROJECTS/"+project_name+"/parameters","r");
			raf1 = new RandomAccessFile("temp.dat","rw");
			int a=raf.readInt();
			raf1.writeInt(a+6); //6 Sequence parameters from table
			//Project Parameters
			for(int i=0;i<a;i++)
			{
				String aa=raf.readUTF();
				raf1.writeUTF(aa);
			}
			
			//Sequence Parameters
			for(int i=0;i<6;i++)
			{
				String aa=(String)JT.getValueAt(JT.getSelectedRow(),i);
				raf1.writeUTF(aa);
			}

			raf.close();
			raf1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	public void start_nfh()
	{
			try {
				NFH nfh=new NFH();
				nfh.fsp=fsp;
				nfh.lsp=lsp;
				nfh.seq_no=seqno;
				nfh.project_name=project_name;
				nfh.number_of_array=number_of_array;
				nfh.number_of_string=number_of_string;
				nfh.number_of_chan=number_of_chan_per_string;
				nfh.initialize();
				nfh.F.setVisible(true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	}
	*/
	JButton refreshfolder,removefolder;
	
	public void remove_file()
	{
		for(int j=0;j<rows.length;j++)
		{
			String pname=(String)TM2.getValueAt(rows[j], 0);
			String fname=(String)TM2.getValueAt(rows[j], 1);
			String dname="PROJECTS/"+project_name+"/"+pname+"/"+fname;
			File f=new File(dname);
			f.delete();
		}
		
		update_folder();
		
	}
	
	
	public void update_folder()
	{
		
		System.gc();
		String S="PROJECTS/"+project_name;
		File f=new File(S);
		File[] files=f.listFiles();
		
		
		while(TM1.getRowCount()>0)
		{
			TM1.removeRow(0);
		}
		
		//Sorting files by number
		int a=files.length;
		int b=0; 			//number of dirs
		for(int i=0;i<a;i++)
		{
			int m=0;
			if(files[i].isDirectory())
			{
				b++;
				m=Integer.parseInt(files[i].getName());	
			}			
		}
		int[] s=new int[b];
		File[] sortfile=new File[b];
		b=0;
		for(int i=0;i<a;i++)
		{
			int m;
			if(files[i].isDirectory())
			{
				m=Integer.parseInt(files[i].getName());	
				s[b]=m;
				b++;
			}			
		}
		
		Arrays.sort(s);
		for(int i=0;i<s.length;i++)
		{
			for(int j=0;j<a;j++)
			{
				if(files[j].isDirectory())
				{
					int m=Integer.parseInt(files[j].getName());	
					if(m==s[i])
					{
						sortfile[i]=files[j];
					}
				}			
			}
		}
		

		
		for(int i=0;i<sortfile.length;i++)
		{
			if(sortfile[i].isDirectory())
			{
				Object[] o={sortfile[i].getName()};
				TM1.addRow(o);
			}
		}
		files=null;
		sortfile=null;
	}
	
	
	
	JDialog SD;
	JTextField[] ST;
	JButton SB;
	
	public void remove_sequence() throws IOException {
	    int a = JT.getSelectedRow();
	    
	    // 1. Seçim kontrolü: Satır seçilmediyse işlemi durdur
	    if (a == -1) {
	        JOptionPane.showMessageDialog(F, "Lütfen silinecek bir sıra seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // 2. Silinecek klasör adını al
	    String seqNo = (String) JT.getValueAt(a, 1);
	    File folderToDelete = new File("PROJECTS/" + project_name + "/" + seqNo);

	    // 3. Kullanıcıdan onay al (Güvenlik için)
	    int confirm = JOptionPane.showConfirmDialog(F, 
	        seqNo + " nolu sırayı ve ilgili tüm dosyaları silmek istediğinize emin misiniz?", 
	        "Silme Onayı", JOptionPane.YES_NO_OPTION);

	    if (confirm == JOptionPane.YES_OPTION) {
	        // 4. Klasörü ve içindekileri sil (Java IO kullanarak)
	        deleteFolderRecursive(folderToDelete);

	        // 5. Modeli ve dosyayı güncelle
	        TM.removeRow(a);
	        update_file(); // lists dosyasını günceller
	        update_folder(); // Rapor panelindeki klasör listesini yeniler
	        
	        JOptionPane.showMessageDialog(F, "Sıra başarıyla silindi.");
	    }
	}

	// Klasörü içindekilerle birlikte silmek için yardımcı metod
	private void deleteFolderRecursive(File folder) {
	    File[] files = folder.listFiles();
	    if (files != null) {
	        for (File f : files) {
	            if (f.isDirectory()) {
	                deleteFolderRecursive(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
	
	public void add_sequence()
	{
		len=ListNames.length;
		JLabel[] L=new JLabel[len];
		ST=new JTextField[len];
		SD=new JDialog(F,"Line Parameters",true);
		JButton SB=new JButton("Apply");
		SD.setBounds(100, 100, 350, 50*(len+2));
		SD.setLayout(null);
		SD.setResizable(false);
		for(int i=0;i<len;i++)
		{
			L[i]=new JLabel(ListNames[i]);
			ST[i]=new JTextField();
			L[i].setBounds(10, 10+i*50, 200, 30);
			ST[i].setBounds(220, 10+i*50, 100, 30);
			SD.add(L[i]);SD.add(ST[i]);
		}
		
		SB.setBounds(220,10+len*50,100,30);
		SD.add(SB);
		
		SB.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	Object[] O=new Object[len];
	        	for(int i=0;i<len;i++)
	    		{
	        		O[i]=ST[i].getText();
	    		}
	        	/*
	        	O[len]="1001"; //First File Number
	       // 	int last_ffid=1001+(int)Math.abs((double)O[2]-(double)O[3]);
	        	int last_ffid=1001+Math.abs((int)Double.parseDouble((String)O[2])-(int)Double.parseDouble((String)O[3]));
	        	O[len+1]=String.valueOf(last_ffid);
	        	*/
	        	TM.addRow(O);
	        	SD.setVisible(false);
	        	try {
					update_file();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	//Add folder to the project
	        	new File("PROJECTS/"+project_name+"/"+ST[1].getText()).mkdir();
	        	File ff=new File("PROJECTS/"+project_name+"/"+ST[1].getText());
	        	ff.setWritable(true,false);
	        	ff.setReadable(true,false);
	        	ff.setExecutable(true,false);
	        	try {
	        		String fn="PROJECTS/"+project_name+"/"+ST[1].getText();
					Process proc= Runtime.getRuntime().exec("chmod 777"+fn);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	update_folder();
	        }
	        });
		SD.setVisible(true);
	}
	
	public void permission(File file) throws IOException{
	//	Set<> perms=new HashSet<>();
		
		
		
	}
	
	public void update_file() throws IOException
	{
		String S1="PROJECTS/"+project_name+"/lists";
		int t=TM.getRowCount();
		if(t>0)
		{
		RandomAccessFile raf=new RandomAccessFile(S1,"rw");
		raf.writeInt(t);
		for(int i=0;i<TM.getRowCount();i++)
		{
			for(int j=0;j<TM.getColumnCount();j++)
			{
			raf.writeUTF((String) TM.getValueAt(i, j));	
			}
		}
		raf.close();
		}
		else
		{
			File f=new File(S1);
			if(f.exists())
			f.delete();
		}
		
	}
	
	public void update_table() throws IOException
	{
		//Clear Model
		if(TM.getRowCount()>0)
		{
			for(int i=TM.getRowCount()-1;i>-1;i--)
			{
				TM.removeRow(i);
			}
			
		}
	
		String S1="PROJECTS/"+project_name+"/lists";
		File f=new File(S1);
		if(f.exists())
		{
		RandomAccessFile raf=new RandomAccessFile(S1,"r");
		int len=raf.readInt();
		for(int i=0;i<len;i++)
		{
		Object[] OBJ=new Object[ListNames.length];
		for(int j=0;j<OBJ.length;j++)
		{
			OBJ[j]=(Object)raf.readUTF();
		}
		TM.addRow(OBJ);
		}
		raf.close();
		}
	}
	
	public void resize()
	{
		BUTTONPANEL.setBounds(10, 10, F.getWidth()-25, 80);
		LISTPANEL.setBounds(10, 100, F.getWidth()-25, F.getHeight()-130);
		REPORTPANEL.setBounds(10, 100, F.getWidth()-25, F.getHeight()-130);
		LISTSCROLL.setBounds(10, 50, LISTPANEL.getWidth()-20, LISTPANEL.getHeight()-60);
		JT.setBounds(0, 0, LISTSCROLL.getWidth(), LISTSCROLL.getHeight());
		REPORTFOLDER.setBounds(10, 50,200, REPORTPANEL.getHeight()-60);
		JT1.setBounds(0, 0, REPORTFOLDER.getWidth(), REPORTFOLDER.getHeight());
		REPORTFILE.setBounds(220, 50,REPORTPANEL.getWidth()-230, REPORTPANEL.getHeight()-60);
		JT2.setBounds(0, 0, REPORTFILE.getWidth(), REPORTFILE.getHeight());
	}
	
	public void PROJECT()
	{
		JButton[] JB=new JButton[3];
		String[] S={"Select Project","Add new Project","Remove Project"};
		JPanel p1=new JPanel();
		p1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p1.setLayout(null);
		D=new JDialog(F,"Project Chooser",true);
		D.setBounds(1250, 700, 220,250);
		D.setLayout(null);
		D.setResizable(false);
		
		for(int i=0;i<3;i++)
		{
			JB[i]=new JButton(S[i]);
			JB[i].setBounds(10, 10+50*i, 170, 30);
			p1.add(JB[i]);
		}
		
		
		
		JB[0].addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	select_project();
	        }
	        });
		
		JB[1].addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	add_project();
	        }
	        });
		JB[2].addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	remove_project();
	        }
	        });
		
//		JB[1].setEnabled(false);
		JB[2].setEnabled(false);
		
		update_combobox();
		
		p1.setBounds(10, 10, 190, 150);
		C.setBounds(10, 180, 190, 30);
		D.add(C);D.add(p1);
		Image img=new ImageIcon("ICONS//MTA.png").getImage();
		D.setIconImage(img);
	
	}
	
	public void select_project()
	{
		if(C.getSelectedIndex()==0)
		{
			  JOptionPane.showMessageDialog(null, "No project is selected.","Select Project",JOptionPane.ERROR_MESSAGE);	
		}
		else
		{
			project_name=(String)C.getSelectedItem();
			D.dispose();
		}
	}
	
	public void remove_project()
	{
		//If combobox select nothing
		if(C.getSelectedIndex()==0)
		{
			  JOptionPane.showMessageDialog(null, "No project is selected.","Remove Project",JOptionPane.ERROR_MESSAGE);	
		}
		else
		{
			//Ask Question
			int dialogbutton=JOptionPane.YES_NO_OPTION;
			int res=JOptionPane.showConfirmDialog(null, "Are you sure to remove this project: "+C.getSelectedItem(),"WARNING",dialogbutton);
			if(res==0)
			{
				//Delete files in folder
				File f=new File("PROJECTS/"+C.getSelectedItem());
				if(f.exists())
				{
				File[] ff=f.listFiles();
				for(int i=0;i<ff.length;i++)
				{
					//if directory..delete inside of dir
					if(ff[i].isDirectory())
					{
						File[] ff1=ff[i].listFiles();
						for(int j=0;j<ff1.length;j++)
						{
							ff1[j].delete();
						}
					}
					//Delete the directory or the file
					ff[i].delete();
				}
			}
				f.delete();
				C.setSelectedIndex(0);
				update_combobox();
			}
			
				
		}
	}
	
	public void update_combobox()
	{
		//ComboBox Add Items
				File f=new File("PROJECTS");
				C.removeAllItems();
				String[] s=f.list();
				C.addItem(" ");
				for(int i=0;i<s.length;i++)
				{
					C.addItem(s[i]);
				}
	}

	JTextField TF0;
	JTextField[] TF1,TF2,TF3;
	public int para_control=0;
	
	public void save_changes()
	{
		JPanel DP0,DP1,DP2,DP3;
		JLabel[] L1,L2,L3;
		JLabel S=new JLabel("Project Name");
		TF0=new JTextField();
		JButton B;
		
		DP0=new JPanel();
		DP0.setLayout(null);
		DP0.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		DP0.setBorder(BorderFactory.createTitledBorder("General Parameters"));
	
		S.setBounds(10, 30, 100, 30);
		TF0.setBounds(220,30, 100, 30);
		DP0.add(S);DP0.add(TF0);
		DP0.setBounds(10, 10, 340, 80);
	
		
		//Source Parameters
		String[] S1={"Number of Arrays","Number of Strings","Number of Chan per String","Source Seperation (meters)","Source Depth Min (meters)","Source Depth Max (meters)",
				"Source Pressure Min (psi)","Source Pressure Max (psi)","NFH Record Length (ms)"};
		DP1=new JPanel();
		DP1.setLayout(null);
		DP1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		DP1.setBorder(BorderFactory.createTitledBorder("Source Parameters"));
	
		DP1.setBounds(10, DP0.getY()+DP0.getHeight(), 340, S1.length*50+30);
		L1=new JLabel[S1.length];
		TF1=new JTextField[S1.length];
		for(int i=0;i<S1.length;i++)
		{
			L1[i]=new JLabel(S1[i]);
			TF1[i]=new JTextField();
			L1[i].setBounds(10, 30+i*50, 200, 30);
			TF1[i].setBounds(220, 30+i*50, 100, 30);
			DP1.add(L1[i]);DP1.add(TF1[i]);
		}
		
		//Source Parameters
				String[] S2={"Number of Traces","Number of Streamers","Near Offset (meters)","Shot Point Interval (meters)","E.sounder to First CMP (meters)",
						"Streamer Seperation","Record Length (ms)"};
				DP2=new JPanel();
				DP2.setLayout(null);
				DP2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				DP2.setBorder(BorderFactory.createTitledBorder("Reciever Parameters"));
				
				DP2.setBounds(DP1.getX()+DP1.getWidth()+10, DP1.getY(), 340, S2.length*50+30);
				L2=new JLabel[S2.length];
				TF2=new JTextField[S2.length];
				for(int i=0;i<S2.length;i++)
				{
					L2[i]=new JLabel(S2[i]);
					TF2[i]=new JTextField();
					L2[i].setBounds(10, 30+i*50, 200, 30);
					TF2[i].setBounds(220, 30+i*50, 100, 30);
					DP2.add(L2[i]);DP2.add(TF2[i]);
				}
				
				//Directory Parameters
				String[] S3={"Source Directory","SEGD Directory"};
				DP3=new JPanel();
				DP3.setLayout(null);
				DP3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				DP3.setBorder(BorderFactory.createTitledBorder("File Directories"));
				DP3.setBounds(DP1.getX(), DP1.getY()+DP1.getHeight()+10, 700, S3.length*50+30);
				L3=new JLabel[S3.length];
				TF3=new JTextField[S3.length];
				String[] S4={"/projectname+$linename/nfh","$projectname$+linename/segd1"};
				
				for(int i=0;i<S3.length;i++)
				{
					L3[i]=new JLabel(S3[i]);
					TF3[i]=new JTextField();
					L3[i].setBounds(10, 30+i*50, 200, 30);
					TF3[i].setBounds(220, 30+i*50,450, 30);
					TF3[i].setText(S4[i]);
					TF3[i].setEnabled(false);
					TF3[i].setBackground(Color.gray);
					TF3[i].setForeground(Color.yellow);
					DP3.add(L3[i]);DP3.add(TF3[i]);
				}
		
		
		DA=new JDialog(F,"Settings",true);
		DA.setBounds(100, 100, 750,900);
		DA.setLayout(null);
		DA.setResizable(false);
		
		B=new JButton("Save Changes");
		B.setBounds(230, DP3.getY()+DP3.getHeight()+10, 400, 30);
		
		DA.add(DP1);DA.add(DP2);DA.add(DP0);DA.add(B);DA.add(DP3);
		
		String s="PROJECTS/"+project_name+"/parameters";
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(s,"r");
			raf.readInt();
			TF0.setText(raf.readUTF());
			TF0.setEnabled(false);
			for(int i=0;i<TF1.length;i++)
			{
				TF1[i].setText(raf.readUTF());
			}
			for(int i=0;i<TF2.length;i++)
			{
				TF2[i].setText(raf.readUTF());
			}
			raf.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//Read Data from project
		
		
		//Parameters
		B.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	para_control=0;
	        	//Project Name Check
	        	if(TF0.getText().isEmpty())
	        	{
	        		TF0.setBackground(Color.yellow);
	        		para_control=1;
	        	}
	        	else
	        	{
	        		TF0.setBackground(Color.white);
	        	}
	        	//Source Parameters Check
	        	ic(TF1[0]);ic(TF1[1]);ic(TF1[2]);fc(TF1[3]);fc(TF1[4]);fc(TF1[5]);ic(TF1[6]);ic(TF1[7]);;fc(TF1[8]);
	        	ic(TF2[0]);ic(TF2[1]);fc(TF2[2]);fc(TF2[3]);fc(TF2[4]);fc(TF2[5]);fc(TF2[6]);
	        	
	        	//Check Parameters
	        	if(para_control==1)
	        	{
	        		
	        	}
	        	else
	        	{
	        		String s="PROJECTS/"+TF0.getText();
		        	String ss=s+"/parameters";
		        		try {
							RandomAccessFile raf=new RandomAccessFile(ss,"rw");
							int A=1+TF1.length+TF2.length; //1- TF0 length (project name)
							raf.writeInt(A);
							raf.writeUTF(TF0.getText());
							for(int i=0;i<TF1.length;i++)
							{
								raf.writeUTF(TF1[i].getText());
							}
							for(int i=0;i<TF2.length;i++)
							{
								raf.writeUTF(TF2[i].getText());
							}
							raf.close();
							update_combobox();
							DA.setVisible(false);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	
	        	}  	
	        }
	        }
		);
		
		DA.setVisible(true);	
	}
	
	public void add_project()
	{
		JPanel DP0,DP1,DP2;
		JLabel[] L1,L2;
		JLabel S=new JLabel("Project Name");
		TF0=new JTextField();
		JButton B;
		
		DP0=new JPanel();
		DP0.setLayout(null);
		DP0.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		DP0.setBorder(BorderFactory.createTitledBorder("General Parameters"));
	
		S.setBounds(10, 30, 100, 30);
		TF0.setBounds(220,30, 100, 30);
		DP0.add(S);DP0.add(TF0);
		DP0.setBounds(10, 10, 340, 80);
	
		
		//Source Parameters
		String[] S1={"Number of Arrays","Number of Strings","Number of Chan per String","Source Seperation (meters)","Source Depth Min (meters)","Source Depth Max (meters)",
				"Source Pressure Min (psi)","Source Pressure Max (psi)","NFH Record Length (ms)"};
		DP1=new JPanel();
		DP1.setLayout(null);
		DP1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		DP1.setBorder(BorderFactory.createTitledBorder("Source Parameters"));
	
		DP1.setBounds(10, DP0.getY()+DP0.getHeight(), 340, S1.length*50+30);
		L1=new JLabel[S1.length];
		TF1=new JTextField[S1.length];
		for(int i=0;i<S1.length;i++)
		{
			L1[i]=new JLabel(S1[i]);
			TF1[i]=new JTextField();
			L1[i].setBounds(10, 30+i*50, 200, 30);
			TF1[i].setBounds(220, 30+i*50, 100, 30);
			DP1.add(L1[i]);DP1.add(TF1[i]);
		}
		
		//Source Parameters
				String[] S2={"Number of Traces","Number of Streamers","Near Offset (meters)","Shot Point Interval (meters)","E.sounder to First CMP (meters)",
						"Streamer Seperation","Record Length (ms)"};
				DP2=new JPanel();
				DP2.setLayout(null);
				DP2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				DP2.setBorder(BorderFactory.createTitledBorder("Reciever Parameters"));
				
				DP2.setBounds(10, DP1.getY()+DP1.getHeight(), 340, S2.length*50+30);
				L2=new JLabel[S2.length];
				TF2=new JTextField[S2.length];
				for(int i=0;i<S2.length;i++)
				{
					L2[i]=new JLabel(S2[i]);
					TF2[i]=new JTextField();
					L2[i].setBounds(10, 30+i*50, 200, 30);
					TF2[i].setBounds(220, 30+i*50, 100, 30);
					DP2.add(L2[i]);DP2.add(TF2[i]);
				}
		
		
		DA=new JDialog(F,"Add Project",true);
		DA.setBounds(100, 100, 365,1050);
		DA.setLayout(null);
		DA.setResizable(false);
		
		B=new JButton("Apply");
		B.setBounds(230, DP2.getY()+DP2.getHeight()+10, 100, 30);
		
		DA.add(DP1);DA.add(DP2);DA.add(DP0);DA.add(B);
		Icon icon=new ImageIcon("ICONS//0.png");
		Image img=new ImageIcon("ICONS//z0.png").getImage();
		DA.setIconImage(img);
		
		//Parameters
		B.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	para_control=0;
	        	//Project Name Check
	        	if(TF0.getText().isEmpty())
	        	{
	        		TF0.setBackground(Color.yellow);
	        		para_control=1;
	        	}
	        	else
	        	{
	        		TF0.setBackground(Color.white);
	        	}
	        	//Source Parameters Check
	        	ic(TF1[0]);ic(TF1[1]);ic(TF1[2]);fc(TF1[3]);fc(TF1[4]);fc(TF1[5]);ic(TF1[6]);ic(TF1[7]);;fc(TF1[8]);
	        	ic(TF2[0]);ic(TF2[1]);fc(TF2[2]);fc(TF2[3]);fc(TF2[4]);fc(TF2[5]);fc(TF2[6]);
	        	
	        	//Check Parameters
	        	if(para_control==1)
	        	{
	        		
	        	}
	        	else
	        	{
	        		String s="PROJECTS/"+TF0.getText();
		        	File f=new File(s);
		        	if(f.exists())
		        	{
		        	  JOptionPane.showMessageDialog(null, "Project "+ TF0.getText()+" is already created.","Warning",JOptionPane.ERROR_MESSAGE);	
		        	}
		        	else
		        	{
		        		f.mkdir();
		        		String ss=s+"/parameters";
		        		try {
							RandomAccessFile raf=new RandomAccessFile(ss,"rw");
							int A=1+TF1.length+TF2.length; //1- TF0 length (project name)
							raf.writeInt(A);
							raf.writeUTF(TF0.getText());
							for(int i=0;i<TF1.length;i++)
							{
								raf.writeUTF(TF1[i].getText());
							}
							for(int i=0;i<TF2.length;i++)
							{
								raf.writeUTF(TF2[i].getText());
							}
							raf.close();
							update_combobox();
							DA.setVisible(false);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	}
	        	}
	        		
	        	
	        }
	        }
		);
		
		DA.setVisible(true);	
	}
	
	//Float Control
	public void fc(JTextField TF)
	{
		 try{
				float gg=Float.parseFloat(TF.getText());
				TF.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				TF.setBackground(Color.yellow);
				para_control=1;
			}
		
	}
	
	//Integer Control
	public void ic(JTextField TF)
	{
		 try{
				int gg=Integer.parseInt(TF.getText());
				TF.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				TF.setBackground(Color.yellow);
				para_control=1;
			}
		
	}


}
