import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

public class NFH {

	public JFrame F;
	//Panels
	public GLJPanel MP; 			//Main panel for graphics
	public static JPanel[] P;		//Panels for General-Buttons-Status
	public JPanel[] CP;				//Child Panels
	public JPanel p6,p7,p8,p9,p10;
	public static JButton[] WB,B2,B3,B4,B5,B6;	//View Button
	public static JTextField ST,ST1;
	public static GLJPanel COLP;
	JScrollBar sx=new JScrollBar(JScrollBar.HORIZONTAL);
	JScrollBar sy=new JScrollBar();
	
	
	public int number_of_string;
	public int number_of_chan;
	public int number_of_array;
	public static int fsp;
	public static int lsp;
	public String project_name;
	public String seq_no;
	String filepath;
	public JButton IB;
	
	
	public int time=300;
	public int autotime=400;
	public int not_len=500;
	
	
	
	int total_chan;
	
	public static ArrayList<float[]> COLS=new ArrayList<float[]>();
	public static ArrayList<float[]> COLB=new ArrayList<float[]>();
	
	//Settings Parameters
	static float scale=1f;
	public static int variable=0;
	public static int density=1;
	public static int wiggle=0;
	public int reverse=1;
	public static int fill=1;
	public static int grid=1;
	public static int cur_fill;
	public static int cur_reverse;
	public static int cur_grid;
	
	
	int color=10;
	public int cur_color;
	
	
	static GL2 gl;
	@SuppressWarnings("rawtypes")
	public static ArrayList[] DATA;
	public static  ArrayList[] SP;
	public ArrayList<String> FILES=new ArrayList<String>();
	int start=0;
	float a1,a2,a3,a4;
	int zoom=0;
	public static int shot;
	
	public transfer tt;
	public draw dd=new draw();
	public JScrollPane jsp;
	public JDialog jd;
	public JLabel[] L;

	public float sample_int;
	public static JPanel LP;
	public static JPanel TP;
	public String[] colmap={"bigtiger","redblue","landmark","rainbow","purple","fold","blackwhite","bluemap","band","BlueYellowRed","Antalya"};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NFH window = new NFH();
					window.F.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public NFH() throws IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	
	
	public class FileNames extends Thread{
		 //Initially setting the flag as true
		 //This method will set flag as false
		 public void stopp() throws IOException
		   {exit = true;}	 
		 @Override
		    public void run() {
			    File FF=new File("TEST");
				
				int control=0;
			 while(!exit)
			 {
				 
			 try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 File[] S=FF.listFiles();
			 for(int i=0;i<S.length;i++)
			 {
				 control=0;
				 for(int j=0;j<FILES.size();j++)
				 {
					if(FILES.get(j).equals(S[i].getAbsolutePath()))
					control=1;
				 }
				 if(control==0)
				 {
					 FILES.add(S[i].getAbsolutePath());
					 try {
						read(S[i].getAbsolutePath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
					
			 }
			 
			 start=1;
			 MP.display();
			 MP.repaint();
			 
				
				 
				 
		 }
			 
			 File[] S=FF.listFiles();
			 for(int i=0;i<S.length;i++)
			 {
			  try {
				read(S[i].getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }
			 }
			 
		 
	}
	
	 VERITableModel tm ;
	 VERITableModel tm_ok ;
	 VERITableModel tm_na; 
	 VERITableModel tm_comm;
	 JButton lb1,lb2,lb3,lb4;
	 JFrame LF;
	 JTable JT;
	public void log()
	{
		
		 tm = new VERITableModel();
		 tm_ok = new VERITableModel();
		 tm_na = new VERITableModel();
		 tm_comm = new VERITableModel();
		LF=new JFrame();
		JT=new JTable();
		JT.setModel(tm);	
		for (int i =0; i<tm.getColumnCount();i++) {
	         JT.setDefaultRenderer(JT.getColumnClass(i), new VERICellRenderer());
	      }
		//((Object) JT).setCellRenderer()
		JScrollPane JSP=new JScrollPane(JT);
	
		JSP.setBounds(10, 50, 850,600);
		
		// Buttons
		lb1=new JButton("All");
		lb2=new JButton("OK");lb2.setBackground(Color.green);
		lb3=new JButton("N/A");lb3.setBackground(Color.red);
		lb4=new JButton("COMM");lb4.setBackground(Color.yellow);
		
		lb1.setBounds(10, 10, 100, 30);
		lb2.setBounds(120, 10, 100, 30);
		lb3.setBounds(230, 10, 100, 30);
		lb4.setBounds(340, 10, 100, 30);
		
		LF.add(lb1);LF.add(lb2);LF.add(lb3);LF.add(lb4);
		
		
		//Button action
		lb1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	JT.setModel(tm);
	        	JT.getColumnModel().getColumn(0).setPreferredWidth(250);
	    		JT.getColumnModel().getColumn(1).setPreferredWidth(500);
	    		JT.getColumnModel().getColumn(2).setPreferredWidth(100);
	        }
		});
		lb2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	JT.setModel(tm_ok);	
	        	JT.getColumnModel().getColumn(0).setPreferredWidth(250);
	    		JT.getColumnModel().getColumn(1).setPreferredWidth(500);
	    		JT.getColumnModel().getColumn(2).setPreferredWidth(100);
	        }
		});
		lb3.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	JT.setModel(tm_na);	
	        	JT.getColumnModel().getColumn(0).setPreferredWidth(250);
	    		JT.getColumnModel().getColumn(1).setPreferredWidth(500);
	    		JT.getColumnModel().getColumn(2).setPreferredWidth(100);
	        }
		});
		lb4.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	JT.setModel(tm_comm);	
	        	JT.getColumnModel().getColumn(0).setPreferredWidth(250);
	    		JT.getColumnModel().getColumn(1).setPreferredWidth(500);
	    		JT.getColumnModel().getColumn(2).setPreferredWidth(100);
	        }
		});
		
		
	//	tm.addColumn("Time");tm.addColumn("Action");tm.addColumn("Status");
		JT.getColumnModel().getColumn(0).setPreferredWidth(250);
		JT.getColumnModel().getColumn(1).setPreferredWidth(500);
		JT.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		LF.setTitle("NFH Log");
		Image img=new ImageIcon("ICONS//NFH.png").getImage();
		LF.setIconImage(img);
	
		LF.setLayout(null);
		LF.add(JSP);
		LF.setResizable(false);
	}
	
	public String date() throws ParseException
	{
		String output;
		Calendar c=Calendar.getInstance();
		String a="dd.MM.yyyy HH:mm:ss";
		SimpleDateFormat sdf=new SimpleDateFormat(a);
		output=sdf.format(c.getTime());
		return output;
	}
		
	public int ik=0;
	JDialog cf;
	public void color_select() throws IOException
	{
		final JDialog cf=new JDialog(F,"Colormap",true);
		JPanel jp=new JPanel();
		COLP=new GLJPanel();
		cf.setResizable(false);
		cf.setLayout(null);
		jp.setLayout(null);
		jp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p6.setLayout(null);
		cf.setTitle("Colormap");
		JButton jb=new JButton("Apply");
		JButton jb1=new JButton("Apply and Close");
		
		cf.add(jp);
		cf.add(jb);
		cf.add(jb1);
		cf.add(COLP);
		
		final JRadioButton[] jrb=new JRadioButton[colmap.length];
		
		ButtonGroup bg=new ButtonGroup();   
		for(ik=0;ik<colmap.length;ik++)
		{
			jrb[ik]=new JRadioButton(colmap[ik]);
			bg.add(jrb[ik]);
			jrb[ik].setBounds(10,5+50*ik,120,50);
			jp.add(jrb[ik]);
		}
		
		cf.setBounds(F.getX()+200,F.getY()+200,300,jrb[colmap.length-1].getY()+150);
		jp.setBounds(10, 10, cf.getWidth()-77, cf.getHeight()-90);
		jb.setBounds(10,jp.getY()+jp.getHeight()+10,100,30);
		jb1.setBounds(120,jp.getY()+jp.getHeight()+10,150,30);
		COLP.setBounds(jp.getX()+jp.getWidth()+10,jp.getY(),30, jp.getHeight());
		
		  COLP.addGLEventListener( new GLEventListener() {
	            
	            @Override
	            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
	         //       Testgjp.setup( glautodrawable.getGL().getGL2(), width, height );
	            }
	            
	            @Override
	            public void init( GLAutoDrawable glautodrawable ) {
	            }
	            
	            @Override
	            public void dispose( GLAutoDrawable glautodrawable ) {
	            }
	            
	            @Override
	            public void display( GLAutoDrawable glautodrawable1 ) {
	            	 GL2 gl1 = glautodrawable1.getGL().getGL2();
	        		 gl1.glClear(gl.GL_COLOR_BUFFER_BIT|gl.GL_DEPTH_BUFFER_BIT); 
	        		 gl1.glBegin(gl.GL_QUAD_STRIP);
	        		 float height=2f/((float)COLB.size()-1);
	        		 for(int i=0;i<COLB.size();i++)
	        		 {
	        			 float[] renk=COLB.get(i);
	        			 gl1.glColor3f(renk[0], renk[1], renk[2]);
	        			 gl1.glVertex2f(-1f,1f-height*i); 
						 gl1.glVertex2f(1f,1f-height*i); 
	        		 }
	            	gl1.glEnd();
					
	            }
	            	
	            	
	            	
	        });

		for(ik=0;ik<colmap.length;ik++)
		{
			jrb[ik].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        if(e.getSource()==jrb[0])
		        {
		        	cur_color=0;
		        }
		        
		        if(e.getSource()==jrb[1])
		        {
		        	cur_color=1;
		        }
		        
		        if(e.getSource()==jrb[2])
		        {
		        	cur_color=2;

		        }
		        if(e.getSource()==jrb[3])
		        {
		        	cur_color=3;

		        }
		        if(e.getSource()==jrb[4])
		        {
		        	cur_color=4;

		        }
		        if(e.getSource()==jrb[5])
		        {
		        	cur_color=5;

		        }
		        if(e.getSource()==jrb[6])
		        {
		        	cur_color=6;

		        }
		        if(e.getSource()==jrb[7])
		        {
		        	cur_color=7;

		        }
		        if(e.getSource()==jrb[8])
		        {
		        	cur_color=8;

		        }
		        if(e.getSource()==jrb[9])
		        {
		        	cur_color=9;

		        }
		        if(e.getSource()==jrb[10])
		        {
		        	cur_color=10;

		        }
		        try {
					colorbar(colmap[cur_color]);
					  COLP.display();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      
		        }});
		}
		
		jb.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
	        		color=cur_color;
					colormap(colmap[color]);
					MP.display();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        }});
		
		jb1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
	        		color=cur_color;
					colormap(colmap[color]);
					MP.display();
					cf.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        }});
		
		//
		for(ik=0;ik<colmap.length;ik++)
		{
		if(color==ik)
		{
			jrb[ik].setSelected(true);
			colorbar(colmap[color]);
			  COLP.display();
		}
		}
	
		cf.setVisible(true);
	}
	
	JTextField t1=new JTextField();JTextField t2=new JTextField();JTextField t3=new JTextField();JTextField t4=new JTextField();
	int error_control=0;
	  int gg1,gg2,gg3,gg4;
	  int settings_control=0;
	public void settings() throws IOException
	{
		final JDialog DP=new JDialog(F,"Display Settings",true);
		JPanel dp1,dp2,dp3;
		JButton b1,b2;
		//Main Panels
		dp1=new JPanel();
		dp2=new JPanel();
		dp3=new JPanel();
		b1=new JButton("Apply");
		b2=new JButton("Apply and Close");
		DP.setLayout(null);
		dp1.setLayout(null);dp2.setLayout(null);dp3.setLayout(null);
		DP.setBounds(F.getX()+100, F.getY()+100,500,500);
		DP.setResizable(false);
		dp1.setBounds(10,10,450,150);dp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dp2.setBounds(10,170,450,100);dp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dp3.setBounds(10,280,450,100);dp3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		b1.setBounds(10, dp3.getY()+dp3.getHeight()+10, 100, 30);
		b2.setBounds(120,b1.getY(), 150, 30);
		
		//Add Panels
		DP.add(dp1);DP.add(dp2);DP.add(dp3);
		//Add Buttons
		DP.add(b1);DP.add(b2);
		//Panel1
		//Labels
		JLabel l1=new JLabel("Color Fill");JLabel l2=new JLabel("Polarity");JLabel l3=new JLabel("Grid On");
		l1.setBounds(10,10,100,30);l2.setBounds(10,60,100,30);l3.setBounds(10,110,100,30);
		dp1.add(l1);dp1.add(l2);dp1.add(l3);
		//RadioButtons P1 S1
		JRadioButton c1,c2;
		c1=new JRadioButton("Positive");c2=new JRadioButton("Negative");
		ButtonGroup bg1=new ButtonGroup();
		c1.setBounds(120,10,120, 30);c2.setBounds(280, 10, 140, 30);
		bg1.add(c1);bg1.add(c2);
		dp1.add(c1);dp1.add(c2);
		//RadioButtons P1 S2
		JRadioButton pol1,pol2;
		pol1=new JRadioButton("Normal");pol2=new JRadioButton("Reverse");
		ButtonGroup bg2=new ButtonGroup();
		pol1.setBounds(120, 60,120, 30);pol2.setBounds(280, 60, 140, 30);
		bg2.add(pol1);bg2.add(pol2);
		dp1.add(pol1);dp1.add(pol2);
		//RadioButtons P1 S3
		JRadioButton g1,g2;
		g1=new JRadioButton("Yes");g2=new JRadioButton("No");
		ButtonGroup bg3=new ButtonGroup();
		g1.setBounds(120, 110,120, 30);g2.setBounds(280, 110, 140, 30);
		bg3.add(g1);bg3.add(g2);
		dp1.add(g1);dp1.add(g2);
		//Panel2 Labels
		JLabel l4=new JLabel("Trace Gap Between String");JLabel l5=new JLabel("Sample Gap Between Cluster");
		l4.setBounds(10,10,250,30);l5.setBounds(10,60,250,30);
		dp2.add(l4);dp2.add(l5);
		//Panel2 TextField
		t1.setBounds(280,10,100,30);t2.setBounds(280,60,100,30);
		dp2.add(t1);dp2.add(t2);
		
		//Panel3 Labels
		JLabel l6=new JLabel("End Time in Milliseconds");
		l6.setBounds(10,10,250,30);
		dp3.add(l6);
		//Panel3 TextField
		t3.setBounds(280,10,100,30);
		dp3.add(l6);dp3.add(t3);
		
		//Panel3 Label2
		JLabel l7=new JLabel("Number of Traces per Gather");
		l7.setBounds(10,60,250,30);
		dp3.add(l7);
		//Panel3 TextField2
		t4.setBounds(280,60,100,30);
		dp3.add(l6);dp3.add(t4);
		
		
		//Parameters
		c1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	cur_fill=1;
	        }});
		c2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	cur_fill=2;
	        }});
		
		pol1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	cur_reverse=1;
	        }});
		pol2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	cur_reverse=2;
	        }});
		
		g1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	cur_grid=1;
	        }});
		g2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	cur_grid=2;
	        }});
		
		error_control=0;
		b1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        String s1=t1.getText();
	      
	        try{
				gg1=Integer.parseInt(s1);
				t1.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t1.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        String s2=t2.getText();
	        try{
				gg2=Integer.parseInt(s2);
				t2.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t2.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        String s3=t3.getText();
	        try{
				gg3=Integer.parseInt(s3);
				t3.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t3.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        
	        String s4=t4.getText();
	        try{
				gg4=Integer.parseInt(s4);
				t4.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t4.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        
	        if(error_control==0)
	        {
	        	fill=cur_fill;
	        	reverse=cur_reverse;
	        	grid=cur_grid;
	        	if(gg1!=gapx || gg2!=gapy || gg3!=time || gg4!=not_len)
	        	{
	        		gapx=gg1;
		        	gapy=gg2;
		        	time=gg3;
		        	not_len=gg4;
		        	//Remove the data from DATASET which is bigger then expected Gather Size
		        	if(not_len<DATA[0].size())
		        	{
		        		int fark=-not_len+DATA[0].size();
		        		for(int i=0;i<DATA.length;i++)
		        		{
		        			for(int j=0;j<fark;j++)
		        			{
		        				DATA[i].remove(0);
		        			}
		        		}
		        	}
		        	settings_control=1;	
	        	}     	
	        	if(D!=null)
	        	gap();
	        	MP.display();
	        	
	        }
	        	
	        	
	        }});
		
		b2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        String s1=t1.getText();
	      
	        try{
				gg1=Integer.parseInt(s1);
				t1.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t1.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        String s2=t2.getText();
	        try{
				gg2=Integer.parseInt(s2);
				t2.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t2.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        String s3=t3.getText();
	        try{
				gg3=Integer.parseInt(s3);
				t3.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t3.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        
	        String s4=t4.getText();
	        try{
				gg4=Integer.parseInt(s4);
				t4.setBackground(Color.white);
			}
			catch(NumberFormatException f)
			{
				t4.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				error_control=1;
			}
	        
	        if(error_control==0)
	        {
	        	fill=cur_fill;
	        	reverse=cur_reverse;
	        	grid=cur_grid;
	        	if(gg1!=gapx || gg2!=gapy || gg3!=time || gg4!=not_len)
	        	{
	        		gapx=gg1;
		        	gapy=gg2;
		        	time=gg3;
		        	not_len=gg4;
		        	//Remove the data from DATASET which is bigger then expected Gather Size
		        	if(not_len<DATA[0].size())
		        	{
		        		int fark=-not_len+DATA[0].size();
		        		for(int i=0;i<DATA.length;i++)
		        		{
		        			for(int j=0;j<fark;j++)
		        			{
		        				DATA[i].remove(0);
		        			}
		        		}
		        	}
		        	settings_control=1;	
	        	}     	
	        	if(D!=null)
	        	gap();
	        	MP.display();
	        }
	        	
	         DP.setVisible(false);
	        }});
		

				cur_fill=fill;
				cur_reverse=reverse;
				cur_grid=grid;
		
				if(fill==1)
				{
					c1.setSelected(true);
				}
				if(fill==2)
				{
					c2.setSelected(true);
				}
				
				if(reverse==1)
				{
					pol1.setSelected(true);
				}
				if(reverse==2)
				{
					pol2.setSelected(true);
				}
				
				if(grid==1)
				{
					g1.setSelected(true);
				}
				if(grid==2)
				{
					g2.setSelected(true);
				}
				
				String tt1=String.valueOf(gapx);
				t1.setText(tt1);
				String tt2=String.valueOf(gapy);
				t2.setText(tt2);
				String tt3=String.valueOf(time);
				t3.setText(tt3);
				String tt4=String.valueOf(not_len);
				t4.setText(tt4);
				
		
		DP.setVisible(true);
		
	}
	
	
		
	public void zoom_button()
	{
		if(zoom==0)
		{
			zoom=1;	
			WB[0].setBackground(Color.cyan);
		
		}
		else
		{
			zoom=0;
			WB[0].setBackground(WB[1].getBackground());	
		}
		
	}
	
	
	int gapx=20;
	int gapy=20;
	static float[][] D;
	int cur_start=0;
	int single=0;
	int chan_no=0;
	
	public void gap()
	{
		nos=(int)((float)time/sample_int);
		if(single==0)
		{
		int col=0;
		int row=0;
		if(number_of_array==1)
		{
		col=DATA[0].size()*number_of_string+(number_of_string-1)*gapx;
		}
		else
		{
			col=DATA[0].size()*(number_of_string/2)+DATA[18].size()*(number_of_string/2)+(number_of_string-1)*gapx;
		}
		row=nos*number_of_chan+gapy*(number_of_chan-1);
		D=null;
		System.gc();
		D=new float[row][col];
		int startrow=0;
		int startcol=0;
		int scol=0;
	
		for(int i=0;i<D.length;i++)
		{
			for(int j=0;j<D[0].length;j++)
			{
			D[i][j]=9999f;	
			}
		}
		
		
		for(int i=0;i<DATA.length;i++)
		{
			startcol=(i/number_of_chan);
			startrow=i-(startcol*number_of_chan);
			startrow=(nos+gapy)*startrow;
			
			if(startcol>0)
			{
				scol=0;
				for(int ll=0;ll<startcol;ll++)
				{
					
					scol=DATA[ll*number_of_chan].size()+gapx+scol;
				}
			}
			for(int j=0;j<DATA[i].size();j++)
			{
				float[] a=(float[])(DATA[i].get(j));
				for(int k=0;k<nos;k++)
				{
					if(scol+j<D[0].length && startrow+k<D.length && reverse==1)
					{
						D[startrow+k][scol+j]=a[k];		
					}
					else if(scol+j<D[0].length && startrow+k<D.length && reverse==2)
					{
						D[startrow+k][scol+j]=-a[k];		
					}
					
				}
				
			}
			
			startcol=0;
		}
		}
		if(single==1)
		{
			int xx=DATA[chan_no].size();
			int yy=nos;
			D=null;
			D=new float[yy][xx];
			if(reverse==1)
			{
				for(int i=0;i<xx;i++)
				{
					float[] a=(float[])(DATA[chan_no].get(i));
					for(int j=0;j<yy;j++)
					{
					D[j][i]=a[j];
					}	
				}	
			}
			
			if(reverse==2)
			{
				for(int i=0;i<xx;i++)
				{
					float[] a=(float[])(DATA[chan_no].get(i));
					for(int j=0;j<yy;j++)
					{
					D[j][i]=-a[j];
					}	
				}	
			}
			
			 endx=D[0].length;
			 startx=0;
			 starty=0;
			 endy=D.length;	
			 
			 settings_control=0;
			
			
		}
		
			//Refresh parameters
		if(tstart==1 || settings_control==1)
		{
			 endx=D[0].length;
			 startx=0;
			 starty=0;
			 endy=D.length;	
			 settings_control=0;
		}
		
				
	}
	
	public String linename;
	public String[] PARAMETERS;
	
	public void readfile()
	{
		
		
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("temp.dat","r");
			int a=raf.readInt();
			PARAMETERS=new String[a];
			for(int i=0;i<a;i++)
			{
			PARAMETERS[i]=raf.readUTF();	
			}			
			fsp=Integer.parseInt(PARAMETERS[19]);
			lsp=Integer.parseInt(PARAMETERS[20]);
			seq_no=PARAMETERS[18];
			linename=PARAMETERS[17];
			project_name=PARAMETERS[0];
			number_of_array=Integer.parseInt(PARAMETERS[1]);
			number_of_string=Integer.parseInt(PARAMETERS[2]);
			number_of_chan=Integer.parseInt(PARAMETERS[3]);
			raf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	String fp1;
	String fp2;
	JRadioButton[] RB;
	JDialog DD;
	public void checkfolder()
	{
		filepath="data//NFH//"+project_name+"//"+linename;
		/*
		filepath="/DS/"+project_name+linename;
		filepath=project_name+linename;
		
		System.out.println(filepath);
		
		fp1=project_name+linename+"[";
		fp2=fp1;
		File F1=new File("/DS");
		String[] L=F1.list();
		
		filepath=project_name+linename;
		File F1=new File(filepath);
		String[] L=F1.list();
		for(int i=0;i<L.length;i++)
		{
			if(L[i].length()>fp1.length())
			{
			if(fp1.equals(L[i].substring(0, fp1.length())))
			{
			fp2=L[i];
			}
			}
		}
		
		if(fp1!=fp2)
		{
			DD=new JDialog(F,"Choose Folder",true);
			DD.setBounds(F.getX()+100, F.getY()+100, 400, 200);
			DD.setLayout(null);
			RB=new JRadioButton[2];
			JButton FB=new JButton("Choose Folder");
			ButtonGroup bg=new ButtonGroup();
			RB[0]=new JRadioButton();
			RB[1]=new JRadioButton();
			RB[0].setText(filepath);
			RB[1].setText("/DS/"+fp2);
			RB[0].setBounds(20, 20, 350, 50);
			RB[1].setBounds(20, 70, 350, 50);
			FB.setBounds(20,130,300,30);
			bg.add(RB[0]);bg.add(RB[1]);
			RB[0].setSelected(true);
			DD.add(RB[0]);
			DD.add(RB[1]);
			DD.add(FB);
			FB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(RB[0].isSelected())
				filepath=RB[0].getText();
				else
				filepath=RB[1].getText();
				DD.setVisible(false);
			}});
			
			DD.setResizable(false);
			DD.setVisible(true);
		}
		
	 */
		
	}
	
	public void initialize() throws IOException, InterruptedException {
		
		readfile();
	
		
		total_chan=number_of_chan*number_of_string;
		DATA=new ArrayList[total_chan] ;
		for(int i=0;i<total_chan;i++)
		{
		DATA[i]=new ArrayList<Float[]>();
		}
		
		SP=new ArrayList[number_of_array];
		for(int i=0;i<number_of_array;i++)
		{
		SP[i]=new ArrayList<Integer>();
		}
		
		
		
		//Transfer thread start
		tt=new transfer();
		
		
		for(int i=0;i<colmap.length;i++)
		{
			if(color==i)
			colormap(colmap[i]);
		}
		
		
		F= new JFrame();
		F.setBounds(100, 100, 1800, 1200);
		F.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		F.setTitle("NFH VIEWER - Sequence No: "+seq_no);
		F.setName("NFH VIEWER");
		Image img=new ImageIcon("ICONS//NFH.png").getImage();
		F.setIconImage(img);
		
		
		//Check main folder
		checkfolder();
		
		
		//Panel Defination
		MP=new MouseTest();
		MP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		   MP.addGLEventListener( new GLEventListener() {
	            
	            @Override
	            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
	         //       Testgjp.setup( glautodrawable.getGL().getGL2(), width, height );
	            }
	            
	            @Override
	            public void init( GLAutoDrawable glautodrawable ) {
	            }
	            
	            @Override
	            public void dispose( GLAutoDrawable glautodrawable ) {
	            }
	            
	            @Override
	            public void display( GLAutoDrawable glautodrawable ) {
	            	
	            	if(start==1 && DATA[0]!=null && COLS.size()>0)
	            	{
	           		 plot(glautodrawable );
	            	}
	            	else
		            whitescreen(glautodrawable );

	            }
	        });
		P=new JPanel[3];
		for(int i=0;i<P.length;i++)
		{
			P[i]=new JPanel();
			P[i].setLayout(null);
		}
		LP=new Labels();
		TP=new Time();
		F.add(P[0]);
		P[0].setLayout(null);
		LP.setLayout(null);
		TP.setLayout(null);
		P[0].add(P[1]);P[0].add(P[2]);P[0].add(MP);P[0].add(LP);P[0].add(TP);
		//Scroll Bars
		sx.setEnabled(false);
		sy.setEnabled(false);
		P[0].add(sx);P[0].add(sy);
		p6=new JPanel();
		p6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P[1].add(p6);
		p6.setLayout(null);
		p7=new JPanel();
		p7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p7.setLayout(null);
		P[1].add(p7);
		p8=new JPanel();
		p8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p8.setLayout(null);
		P[1].add(p8);
		p9=new JPanel();
		p9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p9.setLayout(null);
		P[1].add(p9);
		p10=new JPanel();
		p10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p10.setLayout(null);
		P[1].add(p10);
		IB=new JButton();
		P[1].add(IB);
		
		//Child Panels
		CP=new JPanel[1];
		WB=new JButton[4];
		child();	
		 F.addComponentListener(new ComponentAdapter() {
	            public void componentResized(ComponentEvent e) {
	                // This is only called when the user releases the mouse button.
	             try {
					resize();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            }
	        });
		 
		 WB[0].setToolTipText("Zoom in");
		 WB[1].setToolTipText("Zoom out");
		 WB[2].setToolTipText("Hrz.Zoom out");
		 WB[3].setToolTipText("Vrc.Zoom out");
		 
		 L=new JLabel[5];
		 P[2].setLayout(null);
		 for(int i=0;i<L.length;i++)
		 {
			 L[i]=new JLabel();
			 L[i].setBounds(10+300*i, 10, 280, 30);
			 P[2].add(L[i]); 
		 }
		 SB_update();
		
		 
		
		 
		 B2=new JButton[5];
			for(int i=0;i<B2.length;i++)
			{
			ImageIcon icon=new ImageIcon("ICONS//"+"ww"+String.valueOf(i)+".png");
			B2[i]=new JButton(icon);
			B2[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
			B2[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					if(e.getSource()==B2[0])
					{
						if(wiggle==1)
						{
							wiggle=0;
							B2[0].setBackground(WB[2].getBackground());
						}
					
						else if(wiggle==0)
						{
							wiggle=1;
							B2[0].setBackground(Color.CYAN);
						}
					
						MP.display();
						
					}
					if(e.getSource()==B2[1])
					{
						if(variable==1)
						{
							variable=0;
							B2[1].setBackground(WB[2].getBackground());
						}
						 
						else if(variable==0)
						{
							variable=1;
							B2[1].setBackground(Color.CYAN);
						}
							
					MP.display();
					}
					if(e.getSource()==B2[2])
					{
						if(density==1)
						{
						density=0;
						B2[2].setBackground(WB[2].getBackground());
						} 
						else if(density==0)
						{
						density=1;
						B2[2].setBackground(Color.CYAN);
							}
								
						MP.display();
					}
					if(e.getSource()==B2[3])
					{
						try {
							color_select();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(e.getSource()==B2[4])
					{
					  try {
						settings();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
			});
			p6.add(B2[i]);
			}
			
			 B2[0].setToolTipText("Wiggle");
			 B2[1].setToolTipText("Variable Area");
			 B2[2].setToolTipText("Density");
			 B2[3].setToolTipText("Color map");
			 B2[4].setToolTipText("Settings");
			
			B2[2].setBackground(Color.CYAN);
			
			B3=new JButton[3];
			for(int i=0;i<B3.length;i++)
			{
			ImageIcon icon=new ImageIcon("ICONS//"+"r"+String.valueOf(i)+".png");
			B3[i]=new JButton(icon);
			B3[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
			B3[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					if(e.getSource()==B3[0])
					{
						try {
							start();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						SB_update();
					}
					if(e.getSource()==B3[1])
					{
						
						try {
							pause();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(e.getSource()==B3[2])
					{
					    try {
							try {
								stop();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			p7.add(B3[i]);
			}
		//	 button_passive(B2);
			 button_passive(WB);
			B3[1].setEnabled(false);
			B3[2].setEnabled(false);
			
			 B3[0].setToolTipText("Play");
			 B3[1].setToolTipText("Pause");
			 B3[2].setToolTipText("Stop");
			 
			 
			 B4=new JButton[2];
				for(int i=0;i<B4.length;i++)
				{
				ImageIcon icon=new ImageIcon("ICONS//"+"z"+String.valueOf(1-i)+".png");
				B4[i]=new JButton(icon);
				B4[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
				B4[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						if(e.getSource()==B4[0])
						{
							scale=scale-0.1f;
							String scale1=String.format("%.1f", scale);
							ST.setText(scale1);
							MP.display();
						}
						if(e.getSource()==B4[1])
						{
							scale=scale+0.1f;
							String scale1=String.format("%.1f", scale);
							ST.setText(scale1);
							MP.display();
						}
					}
				});
				p8.add(B4[i]);
				}
				
				ST=new JTextField();
				ST.setBounds(B4[1].getX()+42,B4[0].getY(),64,32);
				String scale1=String.format("%.1f", scale);
				ST.setText(scale1);
				p8.add(ST);
				Action action=new AbstractAction()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String s=ST.getText();
						try{
							float g=Float.parseFloat(s);
							scale=g;
							MP.display();
						}
						catch(NumberFormatException f)
						{
							Toolkit.getDefaultToolkit().beep();
						}
					}
					
				};
				ST.addActionListener(action);
				 B4[0].setToolTipText("Gain down");
				 B4[1].setToolTipText("Gain up");
				
				
				//Select Trace
				 B5=new JButton[2];
					for(int i=0;i<B4.length;i++)
					{
					ImageIcon icon=new ImageIcon("ICONS//"+"f"+String.valueOf(i)+".png");
					B5[i]=new JButton(icon);
					B5[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
					B5[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							String ss=ST1.getText();
							if(e.getSource()==B5[1])
							{
								if(ss.equals("All")|| ss.equals("all"))
								{
									single=1;
									chan_no=0;
								}
								else
								{
									chan_no++;
									if(chan_no<0)
									{
										chan_no=0;
										Toolkit.getDefaultToolkit().beep();
									}
									else if(chan_no>total_chan-1)
									{
										chan_no=total_chan-1;
										Toolkit.getDefaultToolkit().beep();
									}
									
								}
								gap();
								MP.display();
								zoom_out();
								
							}
							if(e.getSource()==B5[0])
							{
								if(ss.equals("All"))
								{
									chan_no=-1;
									ST1.setText("All");
									Toolkit.getDefaultToolkit().beep();
								}
								else if(ss.equals("1"))
								{
									single=0;
									chan_no=-1;
									ST1.setText("All");
								}
								else
								{
									chan_no--;
									if(chan_no<0)
									{
										chan_no=0;
										Toolkit.getDefaultToolkit().beep();
									}
									else if(chan_no>total_chan-1)
									{
										chan_no=total_chan-1;
										Toolkit.getDefaultToolkit().beep();
									}
									
								}
								gap();
								MP.display();
								zoom_out();
							}
							if(chan_no>=0)
							ST1.setText(String.valueOf(chan_no+1));
						}
					});
					p9.add(B5[i]);
					}
					
					 B5[0].setToolTipText("Trace down");
					 B5[1].setToolTipText("Trace up");
					
					ST1=new JTextField();
					ST1.setBounds(B5[1].getX()+42,B5[0].getY(),64,32);
					ST1.setText("All");
					p9.add(ST1);
					Action action1=new AbstractAction()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{							
							String s=ST1.getText();
							if(s.equals("All") || s.equals("all"))
							{
								single=0;
								gap();
								MP.display();
								zoom_out();
								
							}
							else
							{
								try{
									int g=Integer.parseInt(s);
								
									single=1;
									chan_no=g-1;
									if(chan_no<0)
									{
										chan_no=0;
										Toolkit.getDefaultToolkit().beep();
									}
									else if(chan_no>total_chan-1)
									{
										chan_no=total_chan-1;
										Toolkit.getDefaultToolkit().beep();
									}
									gap();
									MP.display();
									zoom_out();
								}
								catch(NumberFormatException f)
								{
									Toolkit.getDefaultToolkit().beep();
								}
								
								
								
							}
							
						}
						
					};
					ST1.addActionListener(action1);
			 
			 ///Lists
					B6=new JButton[6];
					for(int i=0;i<B6.length;i++)
					{
					ImageIcon icon=new ImageIcon("ICONS//"+"l"+String.valueOf(i)+".png");
					B6[i]=new JButton(icon);
					B6[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
					B6[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							if(e.getSource()==B6[0])
							{
								LF.setBounds(F.getX()+100, F.getY()+100, 900, 700);
								LF.setVisible(true);
								
							}
							if(e.getSource()==B6[1])
							{
								try {
									savereport();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if(e.getSource()==B6[2])
							{
								JFileChooser fc=new JFileChooser();
								int userselection=fc.showSaveDialog(F);
								if( userselection == JFileChooser.APPROVE_OPTION)
								{
									textfile=fc.getSelectedFile().getAbsolutePath();
									try {
										savetext();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
								
								
								
								//Svae Final Report as Text File
							}
							if(e.getSource()==B6[3])
							{
							  reference_stack();
							}
							
							if(e.getSource()==B6[4])
							{
								try {
									screenshot();
								} catch (AWTException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								JFileChooser fc=new JFileChooser();
								int userselection=fc.showSaveDialog(F);
								if( userselection == JFileChooser.APPROVE_OPTION)
								{
									textfile=fc.getSelectedFile().getAbsolutePath();
									try {
										ImageIO.write(img1, "jpg", new File(textfile));
									
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
								
							}
							if(e.getSource()==B6[5])
							{
								try {
									screenshot();
								} catch (AWTException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}

									textfile="PROJECTS/"+project_name+"/"+seq_no+"/nfh.jpg";
									File f=new File(textfile);
									int k=1;
									while(f.exists())
									{
									textfile="PROJECTS/"+project_name+"/"+seq_no+"/nfh_"+String.valueOf(k)+".jpg";
									f=new File(textfile);
									k=k+1;
									}
									try {
										ImageIO.write(img1, "jpg", new File(textfile));
									
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							}
							
						}
					});
					p10.add(B6[i]);
					}
					
					 B6[0].setToolTipText("Info");
					 B6[1].setToolTipText("Save");
					 B6[2].setToolTipText("Save as .txt file");
					 B6[3].setToolTipText("Save as reference");
					 B6[4].setToolTipText("Take a Screenshot");
					 B6[5].setToolTipText("Save Screenshot to Database");
					
					button_passive(B4);
					button_passive(B5);
					button_passive(B6);
					ST.setEnabled(false);
					ST1.setEnabled(false);
					B6[0].setEnabled(true);
					B6[4].setEnabled(true);
					B6[5].setEnabled(true);
		 
		 
		 //Zoom in-out
		 sx.addAdjustmentListener(new AdjustmentListener() {
				@Override
				public void adjustmentValueChanged(AdjustmentEvent arg0) {
					// TODO Auto-generated method stub
					float sa1=D[0].length*(float)sx.getValue()/10000f;
					float sa2=D[0].length*(float)(sx.getValue()+ss2)/10000f;
					startx=(int)sa1;
					endx=(int)sa2;
					MP.display();
				}
			
			});
			
			sy.addAdjustmentListener(new AdjustmentListener() {

				@Override
				public void adjustmentValueChanged(AdjustmentEvent arg0) {
					// TODO Auto-generated method stub
				//	float sa4=-1+2*(float)((sy.getValue()))/10000f;
				//	float sa3=-1+2*(float)((sy.getValue()+ss4))/10000f;
					
					float sa3=D.length*(float)sy.getValue()/10000f;
					float sa4=D.length*(float)(sy.getValue()+ss4)/10000f;
					starty=(int)sa3;
					endy=(int)sa4;
					
					MP.display();
				}
			
			});
		 
			shot=fsp;
			//log of sequence
			log();
			IB.setBackground(Color.yellow);
			IB.setText("READY");
	
	
	}
	
	
	
	
	public void reference_stack()
	{
		int x=DATA.length;
		int y=number_of_samples;
		float[][] OUTPUT=new float[x][y];
		for(int i=0;i<DATA.length;i++)
		{
			for(int j=0;j<DATA[i].size();j++)
			{
				float[] B=(float[]) DATA[i].get(j);
				for(int k=0;k<number_of_samples;k++)
				{
				OUTPUT[i][k]=OUTPUT[i][k]+B[k]/(float)(DATA[i].size());	
				}
			}	
		}
		
		try {
			RandomAccessFile raf=new RandomAccessFile("PROJECTS/"+project_name+"/"+"refstack.dat","rw");
			raf.writeUTF(seq_no);
			raf.writeInt(x);
			raf.writeInt(y);
			for(int i=0;i<OUTPUT.length;i++)
			{
				for(int j=0;j<OUTPUT[0].length;j++)
				{
					raf.writeFloat(OUTPUT[i][j]);
				}
				
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
	
	
	public void reference()
	{
		int x=DATA.length;
		int y=(int)((float)autotime/sample_int);
		float[][] OUTPUT=new float[x][y];
		for(int i=0;i<DATA.length;i++)
		{
			float[][] B=new float[DATA[i].size()][y];
			for(int j=0;j<DATA[i].size();j++)
			{
				float[] A=(float[]) DATA[i].get(j);
				B[j]=AutoCorrelation(A,y);
			}
			
			for(int k=0;k<y;k++)
			{
				float sum=0;
				for(int l=0;l<DATA[i].size();l++)
				{
					sum=sum+B[l][k];
				}
				OUTPUT[i][k]=sum/(float)(DATA[i].size());
			}		
		}
		
		try {
			RandomAccessFile raf=new RandomAccessFile("PROJECTS/"+project_name+"/"+seq_no+"/"+"reference.dat","rw");
			raf.writeInt(x);
			raf.writeInt(y);
			for(int i=0;i<OUTPUT.length;i++)
			{
				for(int j=0;j<OUTPUT[0].length;j++)
				{
					raf.writeFloat(OUTPUT[i][j]);
				}
				
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
	
	public float[] AutoCorrelation(float[] INPUT,int size)
	{
		float[] OUTPUT=new float[size];
		float sum;
		for(int i=0;i<size;i++)
		{
			sum=0;
			for(int j=0;j<size-i;j++)
			{
				sum+=INPUT[j]*INPUT[j+i];
			}
			OUTPUT[i]=sum;
		}
		return OUTPUT;
	}
	
	public BufferedImage img1;
	public void screenshot() throws AWTException, IOException
	{
		Robot r=new Robot();
		int a=F.getX()+10;
		int b=F.getY()+80;
		int c=F.getWidth()-40;
		int d=F.getHeight()-170;
		Rectangle screenrect=new Rectangle(a,b,c,d);
		img1=r.createScreenCapture(screenrect);
		
		
	}
	
	public void savereport() throws IOException
	{
		String filename="PROJECTS/"+project_name+"/"+seq_no+"/nfh.report";
		if(!new File("PROJECTS/"+project_name+"/"+seq_no).exists())
		{
			new File("PROJECTS/"+project_name+"/"+seq_no).mkdir();
		}
		int x=tm.getRowCount();
		Object start=tm.getValueAt(0, 0);
		Object end=tm.getValueAt(x-1, 0);
		int good_shot=tm_ok.getRowCount();
		int missing_shot=tm_na.getRowCount();
		int comm_error=tm_comm.getRowCount();
		try {
			RandomAccessFile raf=new RandomAccessFile(filename,"rw");
			raf.writeUTF(project_name);
			raf.writeUTF(seq_no);
			raf.writeUTF((String)start);
			raf.writeUTF((String)end);
			raf.writeInt(fsp);raf.writeInt(lsp);
			raf.writeInt(good_shot);raf.writeInt(missing_shot);raf.writeInt(comm_error);
			for(int i=0;i<good_shot;i++)
			{
				String s=(String)tm_ok.getValueAt(i, 1);
				raf.writeUTF(s);
			}
			for(int i=0;i<missing_shot;i++)
			{
				String s=(String)tm_na.getValueAt(i, 1);
				raf.writeUTF(s);
			}
			for(int i=0;i<comm_error;i++)
			{
				String s=(String)tm_comm.getValueAt(i, 1);
				raf.writeUTF(s);
			}
			raf.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	String textfile;
	
	public void savetext() throws IOException
	{
		int x=tm.getRowCount();
		Object start=tm.getValueAt(0, 0);
		Object end=tm.getValueAt(x-1, 0);
		int good_shot=tm_ok.getRowCount();
		int missing_shot=tm_na.getRowCount();
		int comm_error=tm_comm.getRowCount();
	    File f=new File(textfile);
	    f.createNewFile();
		PrintWriter pw=new PrintWriter(new FileWriter(f));
		pw.println("Project Name: "+project_name);
		pw.println("Sequence Number: "+seq_no);
		pw.println("Start Time: "+start);
		pw.println("End Time: "+end);
		pw.println("First Shot Point: "+fsp);
		pw.println("Last Shot Point: "+lsp);
		pw.println("Number of Shot Points: "+(Math.abs(lsp-fsp)+1));
		pw.println("Number of Good Shots: "+good_shot);
		pw.println("Number of Missing Shots: "+missing_shot);
		pw.println("Number of Communication Errors: "+comm_error);
		
		if(good_shot>0)
		{
			pw.println("------------------------------------Good Shots--------------------------------------");
			pw.println("---------------------------------------------------------------------------------------");
			pw.println("--Time--------------------Status--------------------------------------------------------");
			for(int i=0;i<good_shot;i++)
			{
			String ss=tm_ok.getValueAt(i, 0)+"   "+tm_ok.getValueAt(i, 1);
			pw.println(ss);
			}
		}
		
		if(missing_shot>0)
		{
			pw.println("------------------------------------Missing Shots--------------------------------------");
			pw.println("---------------------------------------------------------------------------------------");
			pw.println("--Time--------------------Status--------------------------------------------------------");
			for(int i=0;i<missing_shot;i++)
			{
			String ss=tm_na.getValueAt(i, 0)+"   "+tm_na.getValueAt(i, 1);
			pw.println(ss);
			}
		}
		
		if(comm_error>0)
		{
			pw.println("------------------------------------Communication Error--------------------------------------");
			pw.println("---------------------------------------------------------------------------------------");
			pw.println("--Time--------------------Status--------------------------------------------------------");
			for(int i=0;i<comm_error;i++)
			{
			String ss=tm_comm.getValueAt(i, 0)+"   "+tm_comm.getValueAt(i, 1);
			pw.println(ss);
			}
		}
		
		
		pw.close();
		
	}
	
	int ss1,ss2,ss3,ss4;
	
	public void scroll()
	{	   
	   ss1=(int)((float)startx/(float)(D[0].length)*10000);
	   ss2=(int)((float)(endx-startx)/(float)(D[0].length)*10000);
	   sx.setValues(ss1,ss2,0,10000); 
	   sx.setEnabled(true);
		
	   ss3= (int)((float)starty/(float)(D.length)*10000);
	   ss4= (int)((float)(endy-starty)/(float)(D.length)*10000);
	   sy.setValues(ss3,ss4,0,10000);
	   sy.setEnabled(true);
	}
	
	public void colormap(String A) throws IOException
	{
		COLS.removeAll(COLS);
		File file=new File("COLORS//"+A);    //creates a new file instance  
		FileReader fr=new FileReader(file);   //reads the file  
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		String line=br.readLine();  
		while((line=br.readLine())!=null)  
		{ 
		float[] renk=new float[3];
		String[] S=line.split(" ");
		renk[0]=Float.parseFloat(S[0])/65025f;
		renk[1]=Float.parseFloat(S[1])/65025f;
		renk[2]=Float.parseFloat(S[2])/65025f;
		COLS.add(renk);
		}  
		fr.close();    //closes the stream and release the resources  	 
	}
	
	
	public void colorbar(String A) throws IOException
	{
		COLB.removeAll(COLB);
		File file=new File("COLORS//"+A);    //creates a new file instance  
		FileReader fr=new FileReader(file);   //reads the file  
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		String line=br.readLine();  
		while((line=br.readLine())!=null)  
		{ 
		float[] renk=new float[3];
		String[] S=line.split(" ");
		renk[0]=Float.parseFloat(S[0])/65025f;
		renk[1]=Float.parseFloat(S[1])/65025f;
		renk[2]=Float.parseFloat(S[2])/65025f;
		COLB.add(renk);
		}  
		fr.close();    //closes the stream and release the resources  	 
	}
	
	
	
	public static float max()
	{
		float output=((float[]) DATA[0].get(0))[0];
		for(int i=0;i<DATA.length;i++)
		{
			for(int j=0;j<DATA[i].size();j++)
			{
				for(int k=0;k<number_of_samples;k++)
				{
					float a=((float[]) DATA[i].get(j))[k];
					if(a>output)
						output=a;
				}
			}
		}
		return output;
	}
	
	public static float min()
	{
		float output=((float[]) DATA[0].get(0))[0];
		for(int i=0;i<DATA.length;i++)
		{
			for(int j=0;j<DATA[i].size();j++)
			{
				for(int k=0;k<number_of_samples;k++)
				{
					float a=((float[]) DATA[i].get(j))[k];
					if(a<output)
						output=a;
				}
			}
		}
		return output;
	}
	
	int tstart=0;
	public void start() throws ParseException
	{
		//Update Log
		String s1=date();
		String s2="Started by user";
		String s3=" ";
		Object[] o={s1,s2,s3};
		tm.addVERI(new VERI(s1,s2,s3));
		
				if(tstart==0)
				{
			System.gc();
			exit=false;exit1=false;
			transfer tt=new transfer();
			tt.start();
			button_passive(B2);
			button_passive(WB);
			button_passive(B4);
			button_passive(B5);
			ST.setEnabled(false);
			ST1.setEnabled(false);
			B3[1].setEnabled(true);
			B3[2].setEnabled(true);
			WB[0].setBackground(WB[1].getBackground());
			zoom=0;
			start=1;
			tstart=1;
			B3[0].setEnabled(false);
			sx.setEnabled(false);
			sy.setEnabled(false);
				}
	}


	public void pause() throws ParseException
	{
		try {
			tt.stopp();
			F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Update Log
			String s1=date();
			String s2="Paused by user";
			String s3=" ";
			Object[] o={s1,s2,s3};
			tm.addVERI(new VERI(s1,s2,s3));
			
			tstart=0;	
			button_active(B2);
			button_active(WB);
			button_active(B4);
			button_active(B5);
			ST.setEnabled(true);
			ST1.setEnabled(true);
			SB_update();
			B3[0].setEnabled(true);
			F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			IB.setBackground(Color.yellow);
			IB.setText("Paused");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void stop() throws InterruptedException, IOException, ParseException
	{
		try {
			String s1=date();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tt.stopp();
		F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s1=date();
		String s2="Stoped by user";
		String s3=" ";
		Object[] o={s1,s2,s3};
		tm.addVERI(new VERI(s1,s2,s3));
		tstart=0;	
		button_active(B2);
		button_passive(WB);
		button_passive(B4);
		button_passive(B5);
		B3[0].setEnabled(true);
		B3[1].setEnabled(false);
		B3[2].setEnabled(false);
		SB_update();
		B3[0].setBackground(B3[1].getBackground());
			start=0;
			MP.display();
			shot=fsp;
			for(int i=0;i<DATA.length;i++)
			{
			DATA[i].removeAll(DATA[i]);
			}
			D=null;
	//		F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			LP.repaint();
			TP.repaint();
			button_passive(WB);
			L[4].setText("Stopped");
			tm.removeall();
			tm_ok.removeall();
			tm_na.removeall();
			tm_comm.removeall();
		F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		IB.setText("Stopped");
		IB.setBackground(Color.red);
	}
	
	
	
	public static float[] findcols(float A)
	{
		float[] R=new float[3];
		int siz=COLS.size();
		float oran=(A-min)/(max-min)*siz;
		if(oran>siz-1)
		oran=siz-1;
		if(oran<0)
		oran=0;
		R=COLS.get((int)oran);
		return R;
	}
	
	public void button_passive(JButton[] B)
	{
		for(int i=0;i<B.length;i++)
		{
		B[i].setEnabled(false);
		}
		
	}
	
	public void button_active(JButton[] B)
	{
		for(int i=0;i<B.length;i++)
		{
		B[i].setEnabled(true);
		}
		
	}
	
	public void SB_update()
	{
		L[0].setVisible(false);
		L[1].setVisible(false);
		L[2].setVisible(false);
		L[3].setVisible(false);
		
		 L[0].setText("Amplitude= ");	 
		 L[1].setText("Time= ");
		 L[3].setText("STRING_NO= ");	 
		 L[2].setText("SP_NO= ");	 		
	}
	
	 static float min,max;
	 int nos;
	 static int startx,endx,starty,endy;
	 
	 
	 public static void whitescreen( GLAutoDrawable glautodrawable ) {
		 gl = glautodrawable.getGL().getGL2();
		 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
		 gl.glColor3f(1f, 1f, 1f);
		 gl.glRectd(-1f,-1f,1f,1f);
	 }
	 @SuppressWarnings("unused")
	public static void plot( GLAutoDrawable glautodrawable ) {
		 
		 if(DATA[0].size()>0)
		 {
		 gl=null;
		 gl = glautodrawable.getGL().getGL2();
		 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
		 //min=min();
		 max=max();
		 min=min();
		 if(-min>max)
		  min=-max;
		 else
		   max=-min;		 
		 int x=endx-startx;
		 int y=endy-starty;
		 float width=2f/(float)x;
		 float height=2f/(float)y;
		 float x1,x2,y1,y2;
		 float[] renk;
		 
		 gl.glColor3f(1f, 1f, 1f);
		 gl.glRectd(-1f,-1f,1f,1f);
		 
		
		 
		 if(density==1)
		 {
			 for(int i=0;i<x;i++)
			 {
				 gl.glBegin(gl.GL_QUAD_STRIP);
				 gl.glVertex2f(-1f+width*i,1f);
				 for(int j=0;j<y;j++)
				 { 
					// if(j+starty<D.length && i+startx<D[0].length)
					 if(D!=null)
					 {
					 renk=findcols(D[j+starty][i+startx]*scale);
					 gl.glColor3f(renk[0], renk[1], renk[2]);
					 if(D!=null)
					 {
					 if(j+starty<D.length && i+startx<D[0].length)
					 {
						if( D[j+starty][i+startx]==9999)
					 {
						 gl.glColor3f(1f,1f,1f);
					 }
					 gl.glVertex2f(-1f+width*(i+1),1f-height*j); 
					 gl.glVertex2f(-1f+width*i,1f-height*(j+1));
					 }
					 }
					 }
				 }
				 gl.glEnd();
			 }
			 gl.glFlush();
		 }
		
		 
		
		 gl.glColor3f(0f, 0f, 0f);
		
		 if(variable==1 && fill==2)
		 {
			 for(int i=0;i<x;i++)
			 {
				 gl.glBegin(gl.GL_QUAD_STRIP);
				 for(int j=1;j<y-1;j++)
				 {
					 if(D!=null)
					 {
					 float deger=((D[j+starty][i+startx]*scale-min)/(max-min)-0.5f)*(width/2);
					 if(D[j+starty][i+startx]!=9999)
					 {
						 if(deger<0 && D[j+starty+1][i+startx]*scale<0)
						 {
							 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
							 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
						 }
						 else if(deger<0 && D[j+starty+1][i+startx]*scale>0)
						 {
							 float a=-(D[j+starty][i+startx])/(D[j+starty+1][i+startx]-D[j+starty][i+startx]);
							 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
							 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
						 }
						 else if(deger>0 && D[j+starty-1][i+startx]*scale>0)
						 {
							 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
							 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));  
						 }
						
					 }
					 else
					 {
					 gl.glEnd();
					 gl.glBegin(gl.GL_QUAD_STRIP);
					 } 
				 }
				 
				 }
				 gl.glEnd();
			 }
			 }if(variable==1 && fill==1)
			 {
				 for(int i=0;i<x;i++)
				 {
					 gl.glBegin(gl.GL_QUAD_STRIP);
					 for(int j=1;j<y-1;j++)
					 {
						 if(D!=null)
						 {
						 float deger=((D[j+starty][i+startx]*scale-min)/(max-min)-0.5f)*(width/2);
						 if(D[j+starty][i+startx]!=9999)
						 {
							 if(deger>0 && D[j+starty+1][i+startx]*scale>0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
							 }
							 else if(deger>0 && D[j+starty+1][i+startx]*scale<0)
							 {
								 float a=-(D[j+starty][i+startx])/(D[j+starty+1][i+startx]-D[j+starty][i+startx]);
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
							 }
							 else if(deger<0 && D[j+starty-1][i+startx]*scale<0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));  
							 }
							
						 }
						 else
						 {
						 gl.glEnd();
						 gl.glBegin(gl.GL_QUAD_STRIP);
						 } 
					 }
					 
					 }
					 gl.glEnd();
				 }
				 }
		 
		 
		 
		 
		 if(wiggle==1)
		 {
			 gl.glColor3f(0f, 0f, 0f);
			 for(int i=0;i<x;i++)
			 {
				 gl.glBegin(gl.GL_LINE_STRIP);
			//	 gl.glVertex2f(-1f+width*i,1f);
				 for(int j=0;j<y;j++)
				 { 
					// if(j+starty<D.length && i+startx<D[0].length)
					 if(D!=null)
					 {
					 float deger=((D[j+starty][i+startx]*scale-min)/(max-min)-0.5f)*(width/2);
					 if(D[j+starty][i+startx]!=9999)
					 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));
					 else
					 gl.glEnd();
					 gl.glBegin(gl.GL_LINE_STRIP);
					 }
				 }
				 gl.glEnd();
			 }
			 
		 }
	
		LP.repaint();	
		TP.repaint();
		 }
     }

	 
	static private volatile boolean exit = true; 
	int sayac=0;
	 int control=0;
	
	 public void go() throws InterruptedException, IOException, ParseException
	 {
		 stop();
	 }
	
 	public class transfer extends Thread{
		 //Initially setting the flag as true
		 //This method will set flag as false
		 public void stopp() throws IOException
		   {
			 exit = true;
		   }	 
		 @Override
		    public void run() {
			 
				
				File FF=new File(filepath);
				if(!FF.exists())
				{
				String s1;
				try {
					s1 = date();
					String s2=filepath+" does not exist";
					String s3="ERROR";
					Object[] o={s1,s2,s3};
					 JOptionPane.showMessageDialog(null,s2,"Error",JOptionPane.ERROR_MESSAGE);
					tm.addVERI(new VERI(s1,s2,s3));
					try {
						go();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						stopp();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
				else
				{
					String s1;
					try {
						s1 = date();
						String s2="Reading file from "+filepath;
						String s3="";
						Object[] o={s1,s2,s3};
						tm.addVERI(new VERI(s1,s2,s3));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				 sayac=0;
			 while(!exit)
			 {	
				
				String fn=filepath+"/"+String.valueOf(shot)+".segd";; //Aranacak veri
				 control=0;
				 File ff=new File(fn);
				if(ff.exists())
				    {
						control=1;
						try {
							String text=String.valueOf(shot)+".segd was read successfully";
							L[4].setForeground(Color.red);
		//					L[4].setText("Status= "+text);
							IB.setBackground(Color.green);
							IB.setText(String.valueOf(shot));
							read(fn);
							gap();
							sayac=0;
							MP.display();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(fsp<lsp)
						shot=shot+1;
						else
						shot=shot-1;
					   }
				else
				{
					control=0;
					try {
						if(IB.getBackground()==Color.gray)
						{
							IB.setBackground(Color.yellow);
						}
						else if(IB.getBackground()==Color.yellow)
						{
							IB.setBackground(Color.gray);
						}
						else if(IB.getBackground()==Color.green)
						{
							IB.setBackground(Color.yellow);
						}
						IB.setText(String.valueOf(shot));
						 
						
						TimeUnit.MILLISECONDS.sleep(1000);
						sayac=sayac+1;
						if(sayac==60)
						{
							String text=(String.valueOf(shot)+".segd does not exist.");
							L[4].setForeground(Color.red);
				//			L[4].setText("Status= "+text);
							
							try {
								String s1=date();
								String s2=String.valueOf(shot)+".segd does not exist";
								String s3="N/A";
								Object[] o={s1,s2,s3};
								tm.addVERI(new VERI(s1,s2,s3));
								tm_na.addVERI(new VERI(s1,s2,s3));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							control=1;
							if(fsp<lsp)
							shot=shot+1;
							else
							shot=shot-1;	
							
							sayac=0;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				  
				
				
				if(lsp>fsp && shot==lsp+1)
				  {
					finish();
				  }
				  else if(lsp<fsp && shot==lsp-1)
				  {
					 finish();
				  } 
			  System.gc();
			 }
			 }		 
		 }

 	
 	
 	public void finish()
 	{
 		  exit=true; 
		  exit=true; 
		  gap();
		  MP.display();
		  L[4].setText("Finished");
		  IB.setText("Finished");
		  IB.setBackground(Color.red);
		  button_active(B2);
		  button_active(WB);
		  button_passive(B3);
		  button_active(B4);
			button_active(B5);
		  button_active(B6);
		  ST.setEnabled(true);
		  ST1.setEnabled(true);
		  tstart=0;
		try {
			 String s1;
			s1 = date();
			String s2="Sequence is finished";
			String s3="";
			Object[] o={s1,s2,s3};
			tm.addVERI(new VERI(s1,s2,s3));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	static private volatile boolean exit1 = false; 
		 	public class draw extends Thread{
				 //Initially setting the flag as true
				 //This method will set flag as false
				 public void stopp() throws IOException
				   {exit1 = true;
				   
				   }	 
				 @Override
				    public void run() {
					 while(!exit1)
					 {  
				
					  
				//		TimeUnit.SECONDS.sleep(5);	  
						if(DATA[0].size()>0)
						{
							gap();
							MP.display();
						}
					
					  System.gc(); 
					 }		 
				 }
				 }
 	
 	
 	
	
	 
	 
	
	 int x1,x2,x3,y1,y2,y3;
     float a5,a6,a7,a8;
	 
	 public void disp_resize(Point ps,Point pe)
		{
		 x1=ps.x;
		 x2=pe.x;
		 y1=ps.y;
		 y2=pe.y;
		 
	
		
		 if(x1>x2)
		 {
		  x3=x1;
		  x1=x2;
		  x2=x3;
		 }
		 if(y2<y1)
		 {
		  y3=y1;
		  y1=y2;
		  y2=y3;
		 }
		 
		 int startxx=startx;
		 int endxx=endx;
		 int endyy=endy;
		 int startyy=starty;
		 startx=startxx+(int)((float)((float)x1/((float)MP.getWidth())*(endxx-startxx)))+1;
		 endx=startxx+(int)((float)((float)x2/((float)MP.getWidth())*(endxx-startxx)))+1; 
		 starty=startyy+(int)((float)((float)y1/((float)MP.getHeight())*(endyy-startyy)));
		 endy=startyy+(int)((float)((float)y2/((float)MP.getHeight())*(endyy-startyy)));
		 
		
		 
		 if(x1==x2 || y1==y2)
		 {
			zoom_out();
		 }
		 scroll();
		}
	 
	 
		public void zoom_out()
		{
			F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			startx=0;
			endx=D[0].length;
			starty=0;
			endy=D.length;
			MP.display();
			sx.setEnabled(false);
			sy.setEnabled(false);
			F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	
	public void child()
	{
		CP[0]=new JPanel();
		CP[0].setLayout(null);
		int x=156;
		int sx=WB.length*32+(WB.length+1)*10;
		CP[0].setBounds(x, 5, sx,40);
		CP[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P[1].add(CP[0]);
		
		for(int i=0;i<WB.length;i++)
		{
		ImageIcon icon=new ImageIcon("ICONS//"+"w"+String.valueOf(i)+".png");
	    WB[i]=new JButton(icon);
		WB[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
		
		WB[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==WB[0])
				{
					zoom_button();
				}
				if(e.getSource()==WB[1])
				{
					zoom_out();
				}
				if(e.getSource()==WB[2])
				{
				hor_out();
				}
				if(e.getSource()==WB[3])
				{
			    ver_out();
				}
			}
		});
		
		CP[0].add(WB[i]);
		}	
	}
	
	public void hor_out()
	{
		startx=0;
		endx=D[0].length;
		MP.display();
		sx.setEnabled(false);
	}
	
	public void ver_out()
	{
		starty=0;
		endy=D.length;
		MP.display();
		sy.setEnabled(false);
	}
	
	int say=0;
	public void resize() throws IOException, InterruptedException
	{
		
		P[1].setBounds(5, 5, P[0].getWidth()-10, 50);
		P[1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P[2].setBounds(5, P[0].getHeight()-55, P[0].getWidth()-10, 50);
		P[2].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		MP.setBounds(100, 120, P[0].getWidth()-220, P[0].getHeight()-(225));
		LP.setBounds(MP.getX()-12,P[1].getX()+P[1].getHeight()+10,MP.getWidth()+24,55);
		TP.setBounds(20,MP.getY(),MP.getX()-20,MP.getHeight()+10);
		sx.setBounds(5, P[0].getHeight()-80, P[1].getWidth(), 20);
		sy.setBounds(P[0].getWidth()-20,P[1].getY()+P[1].getHeight()+5,20,MP.getHeight()+70);
		P[0].repaint();P[0].revalidate();
		P[1].repaint();P[1].revalidate();
		P[2].repaint();P[2].revalidate();
		LP.repaint();LP.revalidate();

		
		 p6.setBounds(344, 5, 220, 40); 
		 p8.setBounds(574, 5, 178, 40);
		 p7.setBounds(10, 5, 136, 40);
		 p9.setBounds(762, 5,178, 40);
		 p10.setBounds(950, 5,262, 40);
		 IB.setBounds(p10.getX()+p10.getWidth()+10, 5, 100, 40);


	
	}

	
	public static int number_of_samples;
	@SuppressWarnings("unchecked")
	public  void read(String file) throws IOException
	{
		int POS=DATA.length/2;
		RandomAccessFile raf=new RandomAccessFile(file,"r");
		File f=new File(file);
		int ss=(int)f.length();
		byte[] b =new byte[ss];
		raf.read(b);
		raf.close();
		
		//General Header-1
		byte[] g1 = Arrays.copyOfRange(b, 0, 32);
		String GH = byte_hex(g1);
		String sp=GH.substring(0,4);
		int add_blocks=Integer.parseInt(GH.substring(22, 23));
		float sample_interval=Float.parseFloat(GH.substring(44, 46))/16f;
		int extended=Integer.parseInt(GH.substring(60, 62))*32;
		sample_int=sample_interval;
		int extern=0;
		String external=GH.substring(62, 64);
		
		//General Header-2
		byte[] g2 = Arrays.copyOfRange(b, 32, 64);
		GH = byte_hex(g2);
		int file_number=0;
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
		
		int record_length=Integer.parseInt(Hex_Dec(GH.substring(28, 34)));
		
		//Channel set
		int chan_pos=(add_blocks+1)*32;
		byte[] cs = Arrays.copyOfRange(b, chan_pos, chan_pos+32);
		GH = byte_hex(cs);
		int number_of_chan=Integer.parseInt(GH.substring(16, 20));
		int number_of_extensions=Integer.parseInt(GH.substring(57, 58));
		number_of_samples=(int)((float)record_length/sample_interval);
		
		chan_pos=chan_pos+32+extern+extended;
		int trace_hdr=20+32*number_of_extensions;
		int amp_size=number_of_samples*4;
		int total_trace=amp_size+trace_hdr;
		
		
		//SP List Update
		if(number_of_array==1)
		{
			SP[0].add(file_number);
			if(SP[0].size()>not_len)
			SP[0].remove(0);
		}
		else
		{
		if(file_number % 2 == 0)
		{
			SP[1].add(file_number);
			if(SP[1].size()>not_len)
		    SP[1].remove(0);
		}
		else
		{
			SP[0].add(file_number);	
			if(SP[0].size()>not_len)
			    SP[0].remove(0);
			
		}
		}
		
		float[] data=new float[0];
		
		float noc_cal=(ss-chan_pos)/(trace_hdr+number_of_samples*4);
		if(noc_cal==18)
		{
			try {
				String s1=date();
				String s2=f.getName()+" was read successfully";
				String s3="OK";
				Object[] o={s1,s2,s3};
				tm.addVERI(new VERI(s1,s2,s3));
				tm_ok.addVERI(new VERI(s1,s2,s3));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(int i=0;i<number_of_chan;i++)
		{
		int pos=chan_pos+total_trace*i+trace_hdr;
		byte[] AMP = Arrays.copyOfRange(b, pos, pos+amp_size);
		FloatBuffer fb = ByteBuffer.wrap(AMP).asFloatBuffer();
		data = new float[number_of_samples];
		fb.get(data); 
		if(number_of_array==1)
		{
			DATA[i].add(data);
			if(DATA[i].size()>not_len)
			DATA[i].remove(0);
		}
		else
		{
			 if(file_number % 2 == 0)
			{
				DATA[i+POS].add(data);
				if(DATA[i+POS].size()>not_len)
				DATA[i+POS].remove(0);
			}
			else
			{
				DATA[i].add(data);
				if(DATA[i].size()>not_len)
				DATA[i].remove(0);
				
			}
		}
		}
		}
		else
		{try {
			String s1=date();
			String s2="Communication Error: "+f.getName()+"  Number of chan="+String.valueOf((int)noc_cal);
			String s3="COMM";
			Object[] o={s1,s2,s3};
			tm.addVERI(new VERI(s1,s2,s3));
			tm_comm.addVERI(new VERI(s1,s2,s3));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		f=null;
		b=null;
		data=null;
		System.gc();
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
	
	 Point ps = null;
     Point pe   = null;
     Point pss=null;
	
	public class MouseTest extends GLJPanel {
	    private static final long serialVersionUID = 1L;
	    private ArrayList<Point> points;
	   
	    Graphics2D gg ;
	    public int sx,sy;
	    public int ex,ey;
	  
	    public MouseTest() {
	        points = new ArrayList<Point>();
	        setBackground(Color.WHITE);
	        
	        addMouseMotionListener(new MouseMotionAdapter() {
	        	 public void mouseDragged(MouseEvent e) {
	        			if(!(ps==null))
	                	{
	                		if(zoom==1 )
	                		{
	                		gg.drawLine(sx, sy, ex, sy);
	                		gg.drawLine(sx, sy, sx, ey);
	                		gg.drawLine(sx, ey, ex, ey);
	                		gg.drawLine(ex, sy, ex, ey);
	                		  pe=e.getPoint();
	                          int xx=e.getPoint().x;
	                          int yy=e.getPoint().y;
	                          gg.drawLine(sx, sy, xx, sy);
	                          gg.drawLine(sx, sy, sx, yy);
	                          gg.drawLine(sx, yy, xx, yy);
	                          gg.drawLine(xx, sy, xx, yy);
	                          //
	                    	 ex=xx;
	                    	 ey=yy;
	                		}
	                	}
	        	 }
	        	 public void mouseMoved(MouseEvent e) {
	        		 if(D!=null && tstart==0)
	        		 {
	        			 L[0].setVisible(true);
	        				L[1].setVisible(true);
	        				L[2].setVisible(true);
	        				L[3].setVisible(true);
	        			 
	        			 
	        			 
	        			 float a=startx+((float)e.getX()/MP.getWidth())*(endx-startx);
	        			 float b=starty+((float)e.getY()/MP.getHeight())*(endy-starty);
	        			 int c=(int)(b/(float)(nos+gapy));
	        			 int d=(int)(b-c*(nos+gapy));
	        			 float f=sample_int*d;
	        			 String z=String.valueOf(f);
	        			 //Amplitude
	        			 float g=D[(int)b][(int)a];
	        			 if(g<9999)
	        			 L[0].setText("Amplitude= "+String.format("%.3f",g));
	        			 else
	        			 {
	        			 L[0].setText("Amplitude= ");	 
	        			 }
	        			 
	        			 //Time
	        			 if(f<time && g<9999)
	        			 L[1].setText("Time= "+z+" ms");
	        			 else
	        			 L[1].setText("Time= ");
	        			 
	        			 //SP NUMBERS 
	        			 float h=a;
	        			 int k=0;
	        			 int m=0;
	        			 int i=0;
	        			 String l="";
	        			 while(h>0)
	        			 {
	        				 k=(int)h;
	        				 h=h-(DATA[i*number_of_chan].size()+gapx);
	        				 i++;
	        			 }
	        			 if(single==1)
	        				 i=chan_no;
	        			 if(g<9999 && single==0)
	        			 L[3].setText("STRING_NO= "+i);
	        			 else if(g<9999 && single==1)
	        			 {
	        				int cc=chan_no/number_of_chan+1; 
	        				 L[3].setText("STRING_NO= "+cc); 
	        			 }
	        			 else
	        			 L[3].setText("STRING_NO= ");	 
	        			 
	        			 if(i>number_of_string/number_of_array && g<9999)
	        				 m=(Integer) SP[1].get(k);
	        			 else if(i<=number_of_string/number_of_array && g<9999)
	        		     m=(Integer) SP[0].get(k);
	        			 if(m>0)
	        			 L[2].setText("SP_NO= "+m);
	        			 else
	        			 L[2].setText("SP_NO= ");	 
	        		 }	 
	        		 else if(tstart==1)
	        			SB_update();
	        	 }
	        	 
	        	 
	    	  });
	        
	      addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				 
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				  gg= (Graphics2D)MP.getGraphics();
		          	 if (e.getButton() == MouseEvent.BUTTON1){
		              gg.setXORMode(Color.white);
		              ps = e.getPoint();
		              sx=ps.x;
		              sy=ps.y;
		              if(zoom==1)
		              {
		             	 ex=sx;
		              	 ey=sy;    	
		              	 gg.drawLine(sx, sy, ex, ey);
				   }
		          	 }
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				  if(zoom==1 && !(ps==null))
	                {
					     if(pe.y>MP.getHeight())
						  pe.y=MP.getHeight()-1;
						  if(pe.y<0)
					      pe.y=0;	  
						  if(pe.x<0)
						  pe.x=0;
						  if(pe.x>MP.getWidth())
						  pe.x=MP.getWidth()-1;
					  
	                	 disp_resize(ps,pe);
	                     repaint();
	                     ps = null;
	                } 
			}
	      });
	      
	      
	  
            
	    }
	
	  
	    @Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
 	        Graphics2D g2 = (Graphics2D) g;
 	       if(start==1 && D!=null)
		    {
		    	int total_sample=endy-starty;
		    	float total_time=total_sample*sample_int;
		    	total_time=total_time/20f;
		    	float time_int=0;
		    	
		    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
		    	
		    	if(grid==1)
		    	{
		    	for(int i=0;i<times.length-1;i++)
		    	{
		    	 if(total_time<times[i] && total_time>=times[i+1])
		    		 time_int=times[i+1];
		    	}
		    	
		    	float time_now;
		    	for(int i=0;i<number_of_chan;i++)
		    	{
		    		time_now=time_int;
		    		while(time_now<=time)
		    		{
		    			int tyer=(nos+gapy)*i+(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-starty)/(float)(endy-starty))*MP.getHeight());
		    			g2.drawLine(0,y,MP.getWidth(),y);
		    			time_now=time_now+time_int;
		    			
		    			
		    		}
		    		
		    	}
		    	}
		    }
 	        
	    }  
	}
	
	
	public class Labels extends JPanel{
		 int cc;
		 @Override
		    public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    if(start==1 && D!=null)
		    {
		    	for(int i=0;i<number_of_string;i++)
		    	{
		    		int ii=i;
		    		if(single==1)
		    		ii=chan_no/number_of_chan;
		    		int k=i;
		    		int syer=0;
		    		for(int j=0;j<k;j++)
		    		{
		    			syer=syer+DATA[j*number_of_chan].size()+gapx;
		    		}
		    		int x=(int)(((float)(syer-startx)/(float)(endx-startx))*MP.getWidth())+12;
		    		g.drawString(String.valueOf(ii+1),x,15);
		    		int y=DATA[ii*number_of_chan].size();
		    		float ssyer=(float)syer;
		    		
		    		if(ii<number_of_string/number_of_array)
		    		{
			    		for(int j=0;j<y;j++)
			    		{
			    			ssyer=syer+0.5f+(j);
			    			x=(int)(((float)(ssyer-startx)/(float)(endx-startx))*MP.getWidth())+12;
			    			String ss=String.valueOf(SP[0].get(j));
			    			g.drawString(ss,x-12,40);
			    			g.drawLine(x,super.getHeight()-10,x,super.getHeight());
			    			int sss=(endx-startx)/50;
			    			j=j+sss;
			    		}
			    		
			    		if(y>0)
			    		{
			    			ssyer=syer+0.5f+(y-1);
			    			x=(int)(((float)(ssyer-startx)/(float)(endx-startx))*MP.getWidth())+12;
			    			String ss=String.valueOf(SP[0].get(y-1));
			    			g.drawString(ss,x-12,40);
			    			g.drawLine(x,45,x,55);
			    			
			    		}
			    		
		    		}
		    		
		    		if(ii>=number_of_string/number_of_array)
		    		{
			    		for(int j=0;j<y;j++)
			    		{
			    			ssyer=syer+0.5f+(j);
			    			x=(int)(((float)(ssyer-startx)/(float)(endx-startx))*MP.getWidth())+12;
			    			String ss=String.valueOf(SP[1].get(j));
			    			g.drawString(ss,x-12,40);
			    			g.drawLine(x,super.getHeight()-10,x,super.getHeight());
			    			int sss=(endx-startx)/50;
			    			j=j+sss;
			    			
			    		}
			    		if(y>0)
			    		{
			    			ssyer=syer+0.5f+(y-1);
			    			x=(int)(((float)(ssyer-startx)/(float)(endx-startx))*MP.getWidth())+12;
			    			String ss=String.valueOf(SP[1].get(y-1));
			    			g.drawString(ss,x-12,40);
			    			g.drawLine(x,super.getHeight()-10,x,super.getHeight());
			    		}
			    		
			    		
			    	   
		    		}

		    		
		    		
		    	}
		    }
		 
		
	} 
	}
	
	

	
	
	public class Time extends JPanel{
		 int cc;
		 @Override
		    public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    if(start==1 && D!=null)
		    {
		    	int total_sample=endy-starty;
		    	float total_time=total_sample*sample_int;
		    	total_time=total_time/20f;
		    	float time_int=0;
		    	
		    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
		    	
		    	
		    	for(int i=0;i<times.length-1;i++)
		    	{
		    	 if(total_time<times[i] && total_time>=times[i+1])
		    		 time_int=times[i+1];
		    	}
		    	
		    	float time_now;
		    	for(int i=0;i<number_of_chan;i++)
		    	{
		    		time_now=time_int;
		    		while(time_now<=time)
		    		{
		    			int tyer=(nos+gapy)*i+(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-starty)/(float)(endy-starty))*MP.getHeight());
		    			g.drawLine(TP.getWidth(),y,TP.getWidth()-10,y);
		    			String ss=String.valueOf(time_now);
		    			g.drawString(ss,20,y+6);
		    			time_now=time_now+time_int;
		    			
		    			
		    		}
		    		
		    	}
		    	
		    }
	} 
	}
	

	class VERI {
		   private String  date;
		   private String  action;
		   private String   status;
		  
		   public VERI(String date, String action, String status) {
		      this.date = date;
		      this.action = action;
		      this.status = status;
		   }
		  
		   public String getDate()     { return date; }
		   public String getAction()  { return action; }
		   public String  getStatus()    { return status; }
		  
		   public String toString() {
		      return "[" + date + ", " + action + ", " + status  + "]"; }
		}
	
	
	
	class VERITableModel extends AbstractTableModel {
		   // holds the strings to be displayed in the column headers of our table
		   final String[] columnNames = {"Date", "Action", "Status"};
		  
		   // holds the data types for all our columns
		   final Class[] columnClasses = {String.class, String.class, String.class};
		  
		   // holds our data
		   final Vector data = new Vector();
		   
		   // adds a row
		   public void addVERI(VERI w) {
		      data.addElement(w);
		      fireTableRowsInserted(data.size()-1, data.size()-1);
		   }
		  
		   public int getColumnCount() {
		      return columnNames.length;
		   }
		          
		   public int getRowCount() {
		      return data.size();
		   }
		  
		   public String getColumnName(int col) {
		      return columnNames[col];
		   }
		   public void removeall()
		   {
			   data.removeAllElements();
		   }
		  
		  
		  
		   public Object getValueAt(int row, int col) {
		      VERI w = (VERI) data.elementAt(row);
		      if (col == 0)      return w.getDate();
		      else if (col == 1) return w.getAction();
		      else if (col == 2) return w.getStatus();
		      else return null;
		   }
		  
		   public Object getValueAtRow(int row) {
		      VERI w = (VERI) data.elementAt(row);
		      return w;
		   }
		  
		   public boolean isCellEditable(int row, int col) {
		      return false;
		   }
		}
	
	class VERICellRenderer extends DefaultTableCellRenderer {
		   public Component getTableCellRendererComponent(
		            JTable table, Object value, boolean isSelected,
		            boolean hasFocus, int row, int column)
		   {
		      VERITableModel wtm = (VERITableModel)table.getModel();
		      VERI w = (VERI) wtm.getValueAtRow(row);
		  
		      setBackground(Color.white);
		      if (column == 2 && w.getStatus()=="OK") {
		    	  setBackground(Color.green);
		      }
		      else if(column == 2 && w.getStatus() == "COMM") {
			         setBackground(Color.yellow); 
		      }
			  else if(column == 2 && w.getStatus() == "N/A") {
				         setBackground(Color.RED);
			      }
			  else if(column == 2 && w.getStatus() == "ERROR") {
			         setBackground(Color.RED);
		      }
		      
		      return super.getTableCellRendererComponent(table, value, isSelected,
		                                                 hasFocus, row, column);
		   }
		}
	
	
	
}


