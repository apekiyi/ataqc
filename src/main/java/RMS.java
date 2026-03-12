import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
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
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;


public class RMS {
		JFrame F;
		JPanel P1,P2,P3,P4,P5,P6,P7,P9,P10,P11,P12,P13,P15,P8,P16,P18,P19,P20,P21,P22,P25;
		GLJPanel GLP1,GLP2;
		JButton[] B1,B2,B3,B5,B6,B7,B8;
		JButton SPB;
		JScrollBar sx=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy=new JScrollBar();
		JScrollBar sx1=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy1=new JScrollBar();
		JScrollBar sx2=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy2=new JScrollBar();
		JTextField T1,ST;
		int arraypanel=0;
		JLabel[] SL; //Status bar parameters
		
	
		JButton IB;
		float a11=-1f,a22=1f,a33=-1f,a44=1f;
		
		//Display settings
		int wiggle;
		int variable;
		int density=1;
		int streamer=1;
		public String[] colmap={"bigtiger","redblue","landmark","rainbow","purple","fold","blackwhite","bluemap","band","BlueYellowRed"};
		int start=0;
		int fill=1;
		static int startx,endx,starty,endy;
		static int startxx,endxx,startyy,endyy;
		float scale=1.5f;
		public static float time;
		static float max;
		static float min;
		int kk=0;
		float sp_wdepth;	
		
		
		
		public int dw_start;
		public int dw_window=500;
		public int dw_end=500;
		public int wshift=800;
		public int wlength=200;
		public int vel_selection=1;
		
		
		/*
		 "/SEGD/"+linename+"."+seqno
		 TPGM191932P1298.298
		 
		 */
		
		//Parameters from User
		/*
		public String project_name="TPGM19";
		public String seq_no="298";
		public int fsp=1500;
		public int lsp=2433;
		public int number_of_traces=1920;
		public int number_of_streamers=4;
		public int number_of_array=2;
		public String linename="TPGM191932P1298";
		public String seqno="298";
		public String filepath;
		public String filename;
		public int number_of_string=6;
		static float offset=37.5f;
		public float shot_interval=37.5f;
		public float distance_source_vessel=195f;
		public float streamer_seperation=100;
		public float array_seperation=50;
		*/
		public String project_name;
		public String seq_no;
		public int fsp;
		public int lsp;
		public int number_of_traces;
		public int number_of_streamers;
		public int number_of_array;
		public String linename;
		public String seqno;
		public String filepath;
		public String filename;
		public int number_of_string;
		static float offset;
		public float shot_interval;
		public float distance_source_vessel;
		public float streamer_seperation;
		public float array_seperation;
		
		
		
		public String textfile;
		
		//Data from SEGD
		float[][] INPUT,OUTPUT;
		public 	ArrayList[][] DATA;
		public  ArrayList[] SP;
		public  ArrayList[] FFID;
		public  ArrayList WDEPTH;
		public ArrayList VEL_SELECTION;
		public  ArrayList DWSTART,DWEND,WSHIFT,WLENGTH;
		
//		public  ArrayList<Integer> PRESSURE1=new ArrayList<Integer>();
		public static int[] pres;
		public static int nonav=0;
		
		//Parameters of SEGD Data
		static float sample_int;
		static int number_of_sample;
		int shot; //current shot to check read
		int number_of_chan_per_streamer;
		public int sp_number;
		int file_number;
		
	
		
		//Parameters for transfer
		int timesayac=0;
		
		
		 JButton lb1,lb2,lb3,lb4;
		 JFrame LF;
		 JTable JT;
		 
		//Color Select Panel
			public int ik=0;
			JDialog cf;
			public static GLJPanel COLP;
			public static ArrayList<float[]> COLB=new ArrayList<float[]>();
			public static ArrayList<float[]> COLS=new ArrayList<float[]>();
			int cur_color;
			int color=3;
			
			//Info panel
			JScrollPane JSP;
			DefaultTableModel m;
			JTable JL;
		
		
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					RMS nt=new RMS();
					nt.F.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		}
		
		public RMS() throws IOException, InterruptedException {
			initialize();
		}
		
		String[] PARAMETERS;
		
		public void readfile()
		{RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("temp.dat","r");
			int a=raf.readInt();
			PARAMETERS=new String[a];
			for(int i=0;i<a;i++)
			{
			PARAMETERS[i]=raf.readUTF();	
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		/*
		
		
		
		
		
		
		
		
		static float offset=37.5f;
		public float shot_interval=37.5f;
		public float distance_source_vessel=195f;
		public float streamer_seperation=100;
		public float array_seperation=50;
		*/
		
		project_name=PARAMETERS[0];	
		linename=project_name+PARAMETERS[17];
		seq_no=PARAMETERS[18];
		seqno=seq_no;
		number_of_array=Integer.parseInt(PARAMETERS[1]);
		number_of_string=Integer.parseInt(PARAMETERS[2]);	
		fsp=Integer.parseInt(PARAMETERS[21]);
		lsp=Integer.parseInt(PARAMETERS[22]);
		time=Float.parseFloat(PARAMETERS[16]);
		timee=time;
		number_of_traces=Integer.parseInt(PARAMETERS[10]);
		number_of_streamers=Integer.parseInt(PARAMETERS[11]);	
		offset=Float.parseFloat(PARAMETERS[12]);
		shot_interval=Float.parseFloat(PARAMETERS[13])*number_of_array;
		distance_source_vessel=Float.parseFloat(PARAMETERS[14]);
		streamer_seperation=Float.parseFloat(PARAMETERS[15]);
		array_seperation=Float.parseFloat(PARAMETERS[4]);
		dw_start=(int)time-1000;

		}
		
		public void initialize() throws IOException, InterruptedException 
		{
		readfile();
		log();
		start_parameters();
		frame();	
		panels();
		buttons();
		scrollbars();
		}
		
		//Variables for Log
		 VERITableModel tm ;
		 VERITableModel tm_ok ;
		 VERITableModel tm_na; 
		 VERITableModel tm_comm;
		
		public void log()
		{
			
			 tm = new VERITableModel();
			 tm_ok = new VERITableModel();
			 tm_na = new VERITableModel();
			 tm_comm = new VERITableModel();
			LF=new JFrame();
			LF.setTitle("RMS Log");
			JT=new JTable();
			JT.setModel(tm);	
			for (int i =0; i<tm.getColumnCount();i++) {
		         JT.setDefaultRenderer(JT.getColumnClass(i), new VERICellRenderer());
		      }
			//((Object) JT).setCellRenderer()
			JScrollPane JSP=new JScrollPane(JT);
			
			
			Image img=new ImageIcon("ICONS//RMS.png").getImage();
			LF.setIconImage(img);
	
			JSP.setBounds(10, 50, 850,600);
			
			// Buttons
			lb1=new JButton("All");
			lb2=new JButton("OK");lb2.setBackground(Color.green);
			lb3=new JButton("N/A");lb3.setBackground(Color.red);
			lb4=new JButton("NO NAV");lb4.setBackground(Color.yellow);
			
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
		
			LF.setLayout(null);
			LF.add(JSP);
			LF.setResizable(false);
			LF.setVisible(false);
		}
		
		
		
		public void scrollbars()
		{
			P8.add(sx);
			sx.setEnabled(false);
			P8.add(sy);
			sy.setEnabled(false);
			 sx.addAdjustmentListener(new AdjustmentListener() {
					@Override
					public void adjustmentValueChanged(AdjustmentEvent arg0) {
						// TODO Auto-generated method stub
						float sa1=RMS.length*(float)sx.getValue()/10000f;
						float sa2=RMS.length*(float)(sx.getValue()+ss2)/10000f;
						startx=(int)sa1;
						endx=(int)sa2;
						GLP1.display();
					}
				
				});
				
				sy.addAdjustmentListener(new AdjustmentListener() {

					@Override
					public void adjustmentValueChanged(AdjustmentEvent arg0) {
						float sa3=RMS[0].length*(float)sy.getValue()/10000f;
						float sa4=RMS[0].length*(float)(sy.getValue()+ss4)/10000f;
						starty=(int)sa3;
						endy=(int)sa4;
						
						GLP1.display();
					}
				
				});
				
				 sx1.addAdjustmentListener(new AdjustmentListener() {
						@Override
						public void adjustmentValueChanged(AdjustmentEvent arg0) {
							// TODO Auto-generated method stub
							float sa1=RMS1.length*(float)sx1.getValue()/10000f;
							float sa2=RMS1.length*(float)(sx1.getValue()+ss22)/10000f;
							startxx=(int)sa1;
							endxx=(int)sa2;
							GLP2.display();
						}
					
					});
					
					sy1.addAdjustmentListener(new AdjustmentListener() {

						@Override
						public void adjustmentValueChanged(AdjustmentEvent arg0) {
							float sa3=RMS1[0].length*(float)sy1.getValue()/10000f;
							float sa4=RMS1[0].length*(float)(sy1.getValue()+ss44)/10000f;
							startyy=(int)sa3;
							endyy=(int)sa4;
							GLP2.display();
						}
					
					});
				
			//Labels
			L=new JLabel[4];
			L[0]=new JLabel("SP NO:");
			L[1]=new JLabel("FFID:");
			L[2]=new JLabel("CHAN NO:");
			L[3]=new JLabel("AMPLITUDE:");
			
			L1=new JLabel[4];
			L1[0]=new JLabel("SP NO:");
			L1[1]=new JLabel("FFID:");
			L1[2]=new JLabel("CHAN NO:");
			L1[3]=new JLabel("AMPLITUDE:");
			
			
			for(int i=0;i<L.length;i++)
			{
				P18.add(L[i]);
				P19.add(L1[i]);
			}
			
			//Scrollbar of Array 2
			P16.add(sx1);
			sx1.setEnabled(false);
			P16.add(sy1);
			sy1.setEnabled(false);
		
			
			
			
		}
		
		int shift=0;
		float[][] RMS,RMS1;
		JButton ShotB;
		
		public void start_parameters() throws IOException
		{
			shot=fsp;
			IB=new JButton();
			ShotB=new JButton();
			IB.setBackground(Color.yellow);
			IB.setText("READY");
			number_of_chan_per_streamer=number_of_traces/number_of_streamers;
			for(int i=0;i<colmap.length;i++)
			{
				if(color==i)
				colormap(colmap[i]);
			}
			
			SP=new ArrayList[number_of_array];
			FFID=new ArrayList[number_of_array];
			WDEPTH=new ArrayList<Float>();
			VEL_SELECTION=new ArrayList<Integer>();
			DWSTART=new ArrayList<Integer>();
			DWEND=new ArrayList<Integer>();
			WSHIFT=new ArrayList<Integer>();
			WLENGTH=new ArrayList<Integer>();
			
			for(int i=0;i<SP.length;i++)
			{
				SP[i]=new ArrayList<Integer>();
				FFID[i]=new ArrayList<Float>();
				
			}
			
			DATA=new ArrayList[number_of_array][number_of_string];
			for(int i=0;i<number_of_array;i++)
			{
				for(int j=0;j<number_of_string;j++)
				{
				DATA[i][j]=new ArrayList<float[]>();
				}
			}
			shift=(int)(distance_source_vessel/shot_interval)+1;
			
			RMS=new float[lsp-fsp][number_of_traces];
			RMS1=new float[lsp-fsp][number_of_traces];
			
		}
		
		public void frame()
		{
			//Frame 
			F=new JFrame();
			F.setBounds(100, 100, 2200, 1300);
			F.setVisible(true);
			F.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
			F.setLayout(null);
			F.setTitle("RMS SEQ: "+seq_no);
			Image img=new ImageIcon("ICONS//RMS.png").getImage();
			F.setIconImage(img);
			 F.addComponentListener(new ComponentAdapter() {
		            public void componentResized(ComponentEvent e) {
		                // This is only called when the user releases the mouse button.
						resize();
		            }
		        });
		}
		
		public void panels()
		{
			//Button Panel
			P1=new JPanel();
			P1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P1.setLayout(null);
			P2=new JPanel();
			//Main Panel
			P2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P2.setLayout(null);
			//Status Panel
			P3=new JPanel();
			P3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P3.setLayout(null);
			F.add(P1);F.add(P2);F.add(P3);
			//Zoom panel
			P4=new JPanel();
			P4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P4.setLayout(null);
			//Zoom panel-Array2
			P5=new JPanel();
			P5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P5.setLayout(null);
			//Settings Panel
			P6=new JPanel();
			P6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P6.setLayout(null);
			//Play-Puase_Stop Panel
			P15=new JPanel();
			P15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P15.setLayout(null);
			P1.add(P4);P1.add(P6);P1.add(P15);P1.add(P5);
			//Time Panel
			P7=new YLabel();
		//	P7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			
			P1.add(IB);P1.add(ShotB);
			
			//Seismic Panel
			P8=new JPanel();
			P8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P8.setLayout(null);
			P2.add(P8);
			
			//Info Panel
			P16=new JPanel();
			P16.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P16.setLayout(null);
			P2.add(P16);
			
			//Info Panel
			
		
			
			//Info Panel
			P18=new JPanel();
			P18.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P18.setLayout(null);
			P3.add(P18);
			
			//Info Panel
			P19=new JPanel();
			P19.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P19.setLayout(null);
			P3.add(P19);
			
			//Status label
			SL=new JLabel[number_of_string+2];
			for(int i=0;i<number_of_string+2;i++)
			{
			 SL[i]=new JLabel();
			 SL[i].setBounds(10*(i+1)+80*i, 5, 80, 30);
			 P19.add(SL[i]);
			}
			
			//Scale Panel
			P20=new JPanel();
			P20.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P20.setLayout(null);
			P1.add(P20);
			
			//Headers
			P9=new XLabel();
		//	P9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P8.add(P7);P8.add(P9);	
			//Seismic Panel
			GLP1=new DrawPanel();
		//	GLP1=new GLJPanel();
			GLP1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P8.add(GLP1);

		//	P11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			//String Selection
			P21=new JPanel();
			P21.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P21.setLayout(null);
			//NT Flip Panel

		
			P10=new YLabel1();
			P11=new XLabel1();
			P11.setLayout(null);
			
			GLP2=new DrawPanel1();
			//	GLP1=new GLJPanel();
			GLP2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P16.add(GLP2);
			
			//Add Panel
			P16.add(P10);P16.add(P11);
			//Flop Time

		//	P13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	//		P13.setLayout(null);
			//Flop Headers
			P22=new JPanel();
			P22.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P22.setLayout(null);
			
		
			
			
			
			P25=new JPanel();
			P25.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P25.setLayout(null);
			P1.add(P25);
			
			//GL PANEL DEFINATION
			
			
			 
			
			 
			
			 
			 GLP1.addGLEventListener( new GLEventListener() {
		            
		            @Override
		            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
		            }
		            
		            @Override
		            public void init( GLAutoDrawable glautodrawable ) {
		            }
		            
		            @Override
		            public void dispose( GLAutoDrawable glautodrawable ) {
		            }
		            
		            @Override
		            public void display( GLAutoDrawable glautodrawable ) {
		            	if(start==1 && RMS!=null && COLS.size()>0)
		            	{
		            		 plot1(glautodrawable );
		            	}
		            	else 
		            	{
		            	whitescreen(glautodrawable );
		            	}
		            }
		        });
			 
			 GLP2.addGLEventListener( new GLEventListener() {
		            
		            @Override
		            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
		            }
		            
		            @Override
		            public void init( GLAutoDrawable glautodrawable ) {
		            }
		            
		            @Override
		            public void dispose( GLAutoDrawable glautodrawable ) {
		            }
		            
		            @Override
		            public void display( GLAutoDrawable glautodrawable ) {
		            	if(start==1 && RMS1!=null && COLS.size()>0)
		            	{
		            		 plot2(glautodrawable );
		            	}
		            	else 
		            	{
		            	whitescreen(glautodrawable);
		            	}
		            }
		        });
			
			
		}
		
		
		
		
	
		
		
	    
	    public float maxmax(float[][] IN)
		{
			float output;
			output=Math.abs(IN[0][0]);
			for(int i=0;i<IN.length;i++)
			{
				for(int j=0;j<IN[0].length;j++)
				{
					if(output<Math.abs(IN[i][j]))
					{
						output=Math.abs(IN[i][j]);
					}
				}
			}
			return output;
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
			
		public void plot1( GLAutoDrawable glautodrawable ) {
		     GL2 gl;
			 gl=null;
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
			 //min=min();
			 max=2;
			 min=0; 
			 int x=endx-startx;
			 int y=endy-starty;
			 float width=2f/(float)x;
			 float height=2f/(float)y;
			 float[] renk;
			
			 gl.glColor3f(1f, 1f, 1f);
			 gl.glRectd(-1f,-1f,1f,1f);

			 if(density==1)
			 {
				 for(int i=0;i<x;i++)
				 {
					 gl.glBegin(GL2.GL_QUAD_STRIP);
					 gl.glVertex2f(-1f+width*i,1f);
					 for(int j=0;j<y;j++)
					 { 
						// if(j+starty<D.length && i+startx<D[0].length)
						 if(RMS!=null)
						 {
						 renk=findcols(RMS[i+startx][j+starty]*scale);
						 if(RMS[i+startx][j+starty]*scale==0)
						 {
							 renk[0]=renk[1]=renk[2]=1;
						 }
						 gl.glColor3f(renk[0], renk[1], renk[2]);
						 gl.glVertex2f(-1f+width*(i+1),1f-height*j); 
						 gl.glVertex2f(-1f+width*i,1f-height*(j+1));
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
					 gl.glBegin(GL2.GL_QUAD_STRIP);
					 for(int j=1;j<y-1;j++)
					 {
						 float deger=((RMS[i+startx][j+starty]*scale-min)/(max-min)-0.5f)*(width/2);
						 if(RMS[i+startx][j+starty]!=9999)
						 {
							 if(deger<0 && RMS[i+startx][j+starty+1]*scale<0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
							 }
							 else if(deger<0 && RMS[i+startx][j+starty+1]*scale>0)
							 {
								 float a=-(RMS[i+startx][j+starty])/(RMS[i+startx][j+starty+1]-RMS[i+startx][j+starty]);
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
							 }
							 else if(deger>0 && RMS[i+startx][j+starty-1]*scale>0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));  
							 }
					 }				 
					 }
					 gl.glEnd();
				 }
				 }
			 
			 if(variable==1 && fill==1)
				 {
					 for(int i=0;i<x;i++)
					 {
						 gl.glBegin(GL2.GL_QUAD_STRIP);
						 for(int j=1;j<y-1;j++)
						 {
							 if(RMS!=null)
							 {
							 float deger=((RMS[i+startx][j+starty]*scale-min)/(max-min)-0.5f)*(width/2);
							 if(RMS[i+startx][j+starty]!=9999)
							 {
								 if(deger>0 && RMS[i+startx][j+starty+1]*scale>0)
								 {
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
								 }
								 else if(deger>0 && RMS[i+startx][j+starty+1]*scale<0)
								 {
									 float a=-(RMS[i+startx][j+starty])/(RMS[i+startx][j+starty+1]-RMS[i+startx][j+starty]);
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
								 }
								 else if(deger<0 && RMS[i+startx][j+starty-1]*scale<0)
								 {
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));  
								 }
								
							 }
							 else
							 {
							 gl.glEnd();
							 gl.glBegin(GL2.GL_QUAD_STRIP);
							 } 
						 }
						 
						 }
						 gl.glEnd();
					 }
					 }
			 
			 
			 
			 
			 if(wiggle==1)
			 {
				 gl.glColor3f(0f, 0f, 0f);
				 gl.glLineWidth(1f);
				 for(int i=0;i<x;i++)
				 {
					 gl.glBegin(GL.GL_LINE_STRIP);
				//	 gl.glVertex2f(-1f+width*i,1f);
					 for(int j=0;j<y;j++)
					 { 
						// if(j+starty<D.length && i+startx<D[0].length)
						 if(RMS!=null)
						 {
						 float deger=((RMS[i+startx][j+starty]*scale-min)/(max-min)-0.5f)*(width/2);
						 if(RMS[i+startx][j+starty]!=9999)
						 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));
						 else
						 gl.glEnd();
						 gl.glBegin(GL.GL_LINE_STRIP);
						 }
					 }
					 gl.glEnd();
				 }
				 
			 }
			 
			 
			
		
			P7.repaint();	
			P9.repaint();
	//		TP.repaint();
			
	     }
			
		public void plot2( GLAutoDrawable glautodrawable ) {
		     GL2 gl;
			 gl=null;
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
			 //min=min();F
			 max=2;
			 min=0; 
			 int x=endxx-startxx;
			 int y=endyy-startyy;
			 float width=2f/(float)x;
			 float height=2f/(float)y;
			 float[] renk;
			
			 gl.glColor3f(1f, 1f, 1f);
			 gl.glRectd(-1f,-1f,1f,1f);

			 if(density==1)
			 {
				 for(int i=0;i<x;i++)
				 {
					 gl.glBegin(GL2.GL_QUAD_STRIP);
					 gl.glVertex2f(-1f+width*i,1f);
					 for(int j=0;j<y;j++)
					 { 
						// if(j+startyy<D.length && i+startxx<D[0].length)
						 if(RMS1!=null)
						 {
						 renk=findcols(RMS1[i+startxx][j+startyy]*scale);
						 gl.glColor3f(renk[0], renk[1], renk[2]);
						 if(RMS1[i+startxx][j+startyy]==9999)
						 {
							 gl.glColor3f(1f,1f,1f);
						 }
						 gl.glVertex2f(-1f+width*(i+1),1f-height*j); 
						 gl.glVertex2f(-1f+width*i,1f-height*(j+1));
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
					 gl.glBegin(GL2.GL_QUAD_STRIP);
					 for(int j=1;j<y-1;j++)
					 {
						 float deger=((RMS1[i+startxx][j+startyy]*scale-min)/(max-min)-0.5f)*(width/2);
						 if(RMS1[i+startxx][j+startyy]!=9999)
						 {
							 if(deger<0 && RMS1[i+startxx][j+startyy+1]*scale<0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
							 }
							 else if(deger<0 && RMS1[i+startxx][j+startyy+1]*scale>0)
							 {
								 float a=-(RMS1[i+startxx][j+startyy])/(RMS1[i+startxx][j+startyy+1]-RMS1[i+startxx][j+startyy]);
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
							 }
							 else if(deger>0 && RMS1[i+startxx][j+startyy-1]*scale>0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));  
							 }
					 }				 
					 }
					 gl.glEnd();
				 }
				 }
			 
			 if(variable==1 && fill==1)
				 {
					 for(int i=0;i<x;i++)
					 {
						 gl.glBegin(GL2.GL_QUAD_STRIP);
						 for(int j=1;j<y-1;j++)
						 {
							 if(RMS1!=null)
							 {
							 float deger=((RMS1[i+startxx][j+startyy]*scale-min)/(max-min)-0.5f)*(width/2);
							 if(RMS1[i+startxx][j+startyy]!=9999)
							 {
								 if(deger>0 && RMS1[i+startxx][j+startyy+1]*scale>0)
								 {
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
								 }
								 else if(deger>0 && RMS1[i+startxx][j+startyy+1]*scale<0)
								 {
									 float a=-(RMS1[i+startxx][j+startyy])/(RMS1[i+startxx][j+startyy+1]-RMS1[i+startxx][j+startyy]);
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
								 }
								 else if(deger<0 && RMS1[i+startxx][j+startyy-1]*scale<0)
								 {
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));  
								 }
								
							 }
							 else
							 {
							 gl.glEnd();
							 gl.glBegin(GL2.GL_QUAD_STRIP);
							 } 
						 }
						 
						 }
						 gl.glEnd();
					 }
					 }
			 
			 
			 
			 
			 if(wiggle==1)
			 {
				 gl.glColor3f(0f, 0f, 0f);
				 gl.glLineWidth(1f);
				 for(int i=0;i<x;i++)
				 {
					 gl.glBegin(GL.GL_LINE_STRIP);
				//	 gl.glVertex2f(-1f+width*i,1f);
					 for(int j=0;j<y;j++)
					 { 
						// if(j+startyy<D.length && i+startxx<D[0].length)
						 if(RMS1!=null)
						 {
						 float deger=((RMS1[i+startxx][j+startyy]*scale-min)/(max-min)-0.5f)*(width/2);
						 if(RMS1[i+startxx][j+startyy]!=9999)
						 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));
						 else
						 gl.glEnd();
						 gl.glBegin(GL.GL_LINE_STRIP);
						 }
					 }
					 gl.glEnd();
				 }
				 
			 }
			 
			 
			
		
			P10.repaint();	
			P11.repaint();
	//		TP.repaint();
			
	     }
		
		float x1,x2,y1,y2,x3,y3;
		
		
		 
		
		
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
			 startx=startxx+(int)((float)((float)x1/((float)GLP1.getWidth())*(endxx-startxx)))+1;
			 endx=startxx+(int)((float)((float)x2/((float)GLP1.getWidth())*(endxx-startxx)))+1; 
			 starty=startyy+(int)((float)((float)y1/((float)GLP1.getHeight())*(endyy-startyy)));
			 endy=startyy+(int)((float)((float)y2/((float)GLP1.getHeight())*(endyy-startyy)));
			 
			
			 
			 if(x1==x2 || y1==y2)
			 {
				zoom_out();
			 }
			 scroll();
			}
		 
		 public void disp_resize1(Point ps,Point pe)
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
			 
			 int startxxxx=startxx;
			 int endxxxx=endxx;
			 int endyyyy=endyy;
			 int startyyy=starty;
			 startxx=startxxxx+(int)((float)((float)x1/((float)GLP1.getWidth())*(endxxxx-startxxxx)))+1;
			 endxx=startxxxx+(int)((float)((float)x2/((float)GLP1.getWidth())*(endxxxx-startxxxx)))+1; 
			 starty=startyyy+(int)((float)((float)y1/((float)GLP1.getHeight())*(endyyyy-startyyy)));
			 endyy=startyyy+(int)((float)((float)y2/((float)GLP1.getHeight())*(endyyyy-startyyy)));
			 
			
			 
			 if(x1==x2 || y1==y2)
			 {
				zoom_out1();
			 }
			 scroll1();
			}
		 
			public void zoom_out()
			{
				F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				startx=0;
				endx=RMS.length;
				starty=0;
				endy=RMS[0].length;
				GLP1.display();
				sx.setEnabled(false);
				sy.setEnabled(false);
				F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void zoom_out1()
			{
				F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				startxx=0;
				endxx=RMS1.length;
				startyy=0;
				endyy=RMS1[0].length;
				GLP2.display();
				sx1.setEnabled(false);
				sy1.setEnabled(false);
				F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
		int ss1,ss2,ss3,ss4;
			
			public void scroll()
			{	   
			   ss1=(int)((float)startx/(float)(RMS.length)*10000);
			   ss2=(int)((float)(endx-startx)/(float)(RMS.length)*10000);
			   sx.setValues(ss1,ss2,0,10000); 
			   sx.setEnabled(true);
				
			   ss3= (int)((float)starty/(float)(RMS[0].length)*10000);
			   ss4= (int)((float)(endy-starty)/(float)(RMS[0].length)*10000);
			   sy.setValues(ss3,ss4,0,10000);
			   sy.setEnabled(true);
			}
			
			int ss11,ss22,ss33,ss44;
			
			public void scroll1()
			{	   
			   ss11=(int)((float)startxx/(float)(RMS1.length)*10000);
			   ss22=(int)((float)(endxx-startxx)/(float)(RMS1.length)*10000);
			   sx1.setValues(ss11,ss22,0,10000); 
			   sx1.setEnabled(true);
				
			   ss33= (int)((float)startyy/(float)(RMS1[0].length)*10000);
			   ss44= (int)((float)(endyy-startyy)/(float)(RMS1[0].length)*10000);
			   sy1.setValues(ss33,ss44,0,10000);
			   sy1.setEnabled(true);
			}
			
		
		 public static void whitescreen( GLAutoDrawable glautodrawable ) {
			 GL2 gl = glautodrawable.getGL().getGL2();
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
			 gl.glColor3f(0.8f, 0.8f, 0.8f);
			 gl.glRectd(-1f,-1f,1f,1f);
		 }
		
			public int ii=0;
		 
		public void buttons()
		{
			//Zoom Panels
			B1=new JButton[4];
			for(int i=0;i<B1.length;i++)
			{
				ImageIcon icon=new ImageIcon("ICONS//"+"w"+String.valueOf(i)+".png");
			    B1[i]=new JButton(icon);
				B1[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
				
				B1[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						if(e.getSource()==B1[0])
						{
							zoom_button();
						}
						if(e.getSource()==B1[1])
						{
							zoom_out();
						}
						if(e.getSource()==B1[2])
						{
						 hor_out();
						}
						if(e.getSource()==B1[3])
						{
					     ver_out();
						}
					}
				});
				
				P4.add(B1[i]);
			}
			
			 B1[0].setToolTipText("Zoom in");
			 B1[1].setToolTipText("Zoom out");
			 B1[2].setToolTipText("Hrz.Zoom out");
			 B1[3].setToolTipText("Vrc.Zoom out");
			 
			 //Settings
			 
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
								B2[0].setBackground(B1[2].getBackground());
							}
						
							else if(wiggle==0)
							{
								wiggle=1;
								B2[0].setBackground(Color.CYAN);
							}
						
						display();
							
						}
						if(e.getSource()==B2[1])
						{
							if(variable==1)
							{
								variable=0;
								B2[1].setBackground(B1[2].getBackground());
							}
							 
							else if(variable==0)
							{
								variable=1;
								B2[1].setBackground(Color.CYAN);
							}
								
						display();
						
						}
						if(e.getSource()==B2[2])
						{
							if(density==1)
							{
							density=0;
							B2[2].setBackground(B1[2].getBackground());
							} 
							else if(density==0)
							{
							density=1;
							B2[2].setBackground(Color.CYAN);
								}
									
							GLP1.display();
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
				P6.add(B2[i]);
				}
				
				 B2[0].setToolTipText("Wiggle");
				 B2[1].setToolTipText("Variable Area");
				 B2[2].setToolTipText("Density");
				 B2[3].setToolTipText("Color map");
				 B2[4].setToolTipText("Settings");
				
				B2[2].setBackground(Color.CYAN);
				
				
				
		//		if(number_of_array>1)
				{
					B3=new JButton[4];
					for(int i=0;i<B3.length;i++)
					{
						ImageIcon icon=new ImageIcon("ICONS//"+"w"+String.valueOf(i)+".png");
					    B3[i]=new JButton(icon);
						B3[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
						
						B3[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{
								if(e.getSource()==B3[0])
								{
									zoom_button1();
								}
								if(e.getSource()==B3[1])
								{
									zoom_out1();
								}
								if(e.getSource()==B3[2])
								{
								 hor_out1();
								}
								if(e.getSource()==B3[3])
								{
							     ver_out1();
								}
							}
						});
						
						P5.add(B3[i]);
					}
					
					 B3[0].setToolTipText("Zoom in");
					 B3[1].setToolTipText("Zoom out");
					 B3[2].setToolTipText("Hrz.Zoom out");
					 B3[3].setToolTipText("Vrc.Zoom out");
				}
				
			
				
				
				
				
				B5=new JButton[3];
				for(int i=0;i<B5.length;i++)
				{
				ImageIcon icon=new ImageIcon("ICONS//"+"r"+String.valueOf(i)+".png");
				B5[i]=new JButton(icon);
				B5[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
				B5[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						if(e.getSource()==B5[0])
						{
							start();
						}
						if(e.getSource()==B5[1])
						{
							pause();
						}
						if(e.getSource()==B5[2])
						{
							try {
								stop();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				P15.add(B5[i]);
				}
				
				  B5[1].setEnabled(false);
				  B5[2].setEnabled(false);
				
				  B5[0].setToolTipText("Play");
				  B5[1].setToolTipText("Pause");
				  B5[2].setToolTipText("Stop");
				
				  ST=new JTextField();
				  ST.setText(String.valueOf(scale));
				  ST.setBounds(54, 4, 74, 32);
				  P20.add(ST);
				  B6=new JButton[2];
					for(int i=0;i<B6.length;i++)
					{
					ImageIcon icon=new ImageIcon("ICONS//"+"z"+String.valueOf(1-i)+".png");
					B6[i]=new JButton(icon);
					B6[i].setBounds(10*(i+1)+116*i, 4, 32, 32);
					B6[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							if(e.getSource()==B6[0])
							{
								scale=scale-0.1f;
								String scale1=String.format("%.1f", scale);
								ST.setText(scale1);
								GLP1.repaint();
								GLP2.repaint();
							}
							if(e.getSource()==B6[1])
							{
								scale=scale+0.1f;
								String scale1=String.format("%.1f", scale);
								ST.setText(scale1);
								GLP1.repaint();
								GLP2.repaint();
							}
						}
					});
					P20.add(B6[i]);
					}
					
					Action action=new AbstractAction()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							String s=ST.getText();
							try{
								float g=Float.parseFloat(s);
								scale=g;
								GLP1.display();
								GLP2.display();
							}
							catch(NumberFormatException f)
							{
								Toolkit.getDefaultToolkit().beep();
							}
						}
						
					};
					ST.addActionListener(action);
					 B6[0].setToolTipText("Gain down");
					 B6[1].setToolTipText("Gain up");
					 
			
					 
					
				
					 B8=new JButton[number_of_streamers];
						for(ii=0;ii<B8.length;ii++)
						{
						String s=String.valueOf(ii+1);
						B8[ii]=new JButton(s);
						B8[ii].setBounds(10*(ii+1)+ii*32, 4, 32, 32);
						B8[ii].setToolTipText("Streamer No"+(ii+1));
						B8[ii].setMargin(new Insets(0,0,0,0));
						B8[ii].setBorder(null);
						B8[ii].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{
								String t=((JButton)e.getSource()).getText();
								int a=Integer.parseInt(t);
								selected_streamer=a;
								button_lights(a-1);
								if(tstart==0 && OUTPUT!=null && OUTPUT!=null)
								{
									readsegd(filename);
									GLP1.display();
									GLP2.display();
								}
							}
						}
						);
						
						
						}
						B8[selected_streamer-1].setBackground(Color.yellow);
						
						
						 B7=new JButton[3];
							for(int i=0;i<B7.length;i++)
							{
								ImageIcon icon;
								if(i==0)
							icon=new ImageIcon("ICONS//"+"l"+String.valueOf(i)+".png");
								else if(i==1)
							icon=new ImageIcon("ICONS//"+"l4.png");	
								else
							icon=new ImageIcon("ICONS//"+"l5.png");			
							B7[i]=new JButton(icon);
							B7[i].setBounds(10*(i+1)+i*32, 4, 32, 32);
							B7[i].addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e)
								{
									if(e.getSource()==B7[0])
									{
								    LF.setBounds(F.getX()+100, F.getY()+100, 900, 700);
									LF.setVisible(true);
									}		
								if(e.getSource()==B7[1])
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
										ImageIO.write(img, "jpg", new File(textfile));
										
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} 
									
								}
								}
								
								if(e.getSource()==B7[2])
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

										textfile="PROJECTS/"+project_name+"/"+seq_no+"/rms.jpg";
										File f=new File(textfile);
										int k=1;
										while(f.exists())
										{
										textfile="PROJECTS/"+project_name+"/"+seq_no+"/nt_"+String.valueOf(k)+".jpg";
										f=new File(textfile);
										k=k+1;
										}
										try {
											ImageIO.write(img, "jpg", new File(textfile));
										
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
								}
								
								
								
							}});
							
							P25.add(B7[i]);
							}
							B7[0].setToolTipText("Info");
							B7[1].setToolTipText("Take a Screenshot");
							B7[2].setToolTipText("Save Screenshot to Database");
						
		}
		
		
		BufferedImage img;
		public void screenshot() throws AWTException, IOException
		{
			
			Robot r=new Robot();
			int a=F.getX()+10;
			int b=F.getY()+80;
			int c=F.getWidth()-40;
			int d=F.getHeight()-170;
			Rectangle screenrect=new Rectangle(a,b,c,d);
			img=r.createScreenCapture(screenrect);
			
			
		}
		
		public void button_lights(int A)
		{
			for(int i=0;i<B8.length;i++)
			{
				B8[i].setBackground(B1[2].getBackground());
			}
			B8[A].setBackground(Color.yellow);
		}
		
		public void zoom_button()
		{
			if(zoom==0)
			{
				zoom=1;	
				B1[0].setBackground(Color.cyan);
			
			}
			else
			{
				zoom=0;
				B1[0].setBackground(B1[1].getBackground());	
			}
			
		}
		
		public void zoom_button1()
		{
			if(zoomm==0)
			{
				zoomm=1;	
				B3[0].setBackground(Color.cyan);
			
			}
			else
			{
				zoomm=0;
				B3[0].setBackground(B1[1].getBackground());	
			}
			
		}
		
		public void hor_out()
		{
			startx=0;
			endx=RMS.length;
			GLP1.display();
			sx.setEnabled(false);
		}
		
		public void hor_out1()
		{
			startxx=0;
			endxx=RMS1.length;
			GLP2.display();
			sx1.setEnabled(false);
		}
		
		public void ver_out1()
		{
			startyy=0;
			endyy=RMS1[0].length;
			GLP2.display();
			sy1.setEnabled(false);
		}
		
		public void ver_out()
		{
			starty=0;
			endy=RMS[0].length;
			GLP1.display();
			sy.setEnabled(false);
		}
		
		int tstart=0;
		public void pause()
		{
			try {
				t.stopp();
				F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			TimeUnit.MILLISECONDS.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			addrow("Paused by user"," ");
			tstart=0;
			B5[0].setEnabled(true);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
			F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			IB.setBackground(Color.yellow);
			IB.setText("Paused");
		}
		
		int control=0;
		
		public void stop() throws InterruptedException
		{
			ilk=0;
			exit=true;
			F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			TimeUnit.MILLISECONDS.sleep(2000);
			tstart=0;
			OUTPUT=null;
			control=0;
			GLP1.display();
			GLP2.display();
			P9.repaint();
			
			for(int i=0;i<DATA.length;i++)
			{
				for(int j=0;j<DATA[i].length;j++)
				{
					DATA[i][j].removeAll(DATA[i][j]);
				}
				
			}
			
			for(int i=0;i<SP.length;i++)
			{
			SP[i].removeAll(SP[i]);
			FFID[i].removeAll(FFID[i]);
			}
			B5[0].setEnabled(true);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
			shot=fsp;
			F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			IB.setText("Stopped");
			IB.setBackground(Color.red);
			DWSTART.removeAll(DWSTART);
			DWEND.removeAll(DWEND);
		}
		
		transfer t;
		public void start()
		{
			t=new transfer();
			exit=false;
			t.start();
			start=1;
			tstart=1;
			//Enable puase-stop buttons
			B5[0].setEnabled(false);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
			ilk=1;
			
			
		}
		
		public void resize()
		{
			P1.setBounds(10,10,F.getWidth()-25,50); 
			P3.setBounds(10,F.getHeight()-90,F.getWidth()-25,50);
			P2.setBounds(10,70,F.getWidth()-25,P3.getY()-75);
			P15.setBounds(10,5,40+32*3,40);
			P4.setBounds(P15.getX()+P15.getWidth()+10,5,50+32*4,40);
			P6.setBounds(P4.getX()+P4.getWidth()+10,5,60+32*5,40);
			P8.setBounds(10,10,(int)((P2.getWidth()-30)*0.5f),P2.getHeight()-20);
			P16.setBounds(P8.getX()+P8.getWidth()+10, P8.getY(),(int)((P2.getWidth()-30)*0.5f),P8.getHeight());
			P20.setBounds(P6.getX()+P6.getWidth()+10,5,50+32*4,40);
	
			//Array1	
			P7.setBounds(10,100,80,(int)((P2.getHeight()-160)));
			GLP1.setBounds(90, 100, P8.getWidth()-120,P7.getHeight());
			sx.setBounds(GLP1.getX(), GLP1.getY()+GLP1.getHeight(),GLP1.getWidth(),20);
			sy.setBounds(GLP1.getX()+GLP1.getWidth(),GLP1.getY(),20,GLP1.getHeight());		
			P9.setBounds(GLP1.getX()-12,10,GLP1.getWidth()+24, 90);
			
			//Array 2
			P10.setBounds(10,100,80,(int)((P2.getHeight()-160)));
			GLP2.setBounds(90, 100, P16.getWidth()-120,P7.getHeight());
			P11.setBounds(GLP2.getX()-12,10,GLP2.getWidth()+24, 90);
			sx1.setBounds(GLP2.getX(), GLP2.getY()+GLP2.getHeight(),GLP2.getWidth(),20);
			sy1.setBounds(GLP2.getX()+GLP2.getWidth(),GLP2.getY(),20,GLP2.getHeight());	
			P5.setBounds(P16.getX()+10, 5, 50+32*4, 40);
			P25.setBounds(P5.getX()+P5.getWidth()+10, 5, 94+42, 40);
			IB.setBounds(P25.getX()+P25.getWidth()+10, 5, 120, 40);
			ShotB.setBounds(IB.getX()+IB.getWidth()+10, 5, 120, 40);
			
			
			
			P18.setBounds(P8.getX(), 5, P8.getWidth(), 40);
			P19.setBounds(P16.getX(), 5, P16.getWidth(), 40);
			for(int i=0;i<L.length;i++)
			{
				L[i].setBounds(10*(i+1)+180*i,5,180,30);
				L1[i].setBounds(10*(i+1)+180*i,5,180,30);
			}
			
		}
	
		
		JTextField t1,t2,t3,t4; //Deep Water Start //Deep Water Window Length 
		int error_control,cur_fill,cur_reverse,cur_vel,gg1,gg2,gg3,gg4;
		public static int reverse=1;
		
		public void settings() throws IOException
		{
			final JDialog DP=new JDialog(F,"Parameter Settings",true);
			JPanel dp1,dp2,dp3,dp4;
			JButton b1,b2;
			//Main Panels
			dp1=new JPanel();
			dp2=new JPanel();
			dp3=new JPanel();
			
			b1=new JButton("Apply");
			b2=new JButton("Apply and Close");
			t1=new JTextField();	
			t2=new JTextField();
			t3=new JTextField();	
			t4=new JTextField();
			
			DP.setLayout(null);
			dp1.setLayout(null);dp2.setLayout(null);dp3.setLayout(null);
			DP.setBounds(F.getX()+100, F.getY()+100, 500,500);
			DP.setResizable(false);
			dp1.setBounds(10,10,450,100);dp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp2.setBounds(10,120,450,150);dp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp3.setBounds(10,280,450,100);dp3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			b1.setBounds(10, 390, 100, 30);
			b2.setBounds(120, 390, 150, 30);
			
			//Add Panels
			DP.add(dp1);DP.add(dp2);DP.add(dp3);
			//Add Buttons
			DP.add(b1);DP.add(b2);
			//Panel1
			//Labels
			JLabel l1=new JLabel("Color Fill");JLabel l2=new JLabel("Polarity");
			l1.setBounds(10,10,100,30);l2.setBounds(10,60,100,30);
			dp1.add(l1);dp1.add(l2);
			//RadioButtons P1 S1
			JRadioButton c1,c2;
			c1=new JRadioButton("Positive");c2=new JRadioButton("Negative");
			ButtonGroup bg1=new ButtonGroup();
			c1.setBounds(120, 10,120, 30);c2.setBounds(280, 10, 120, 30);
			bg1.add(c1);bg1.add(c2);
			dp1.add(c1);dp1.add(c2);
			//RadioButtons P1 S2
			JRadioButton pol1,pol2;
			JRadioButton rmsvel1,rmsvel2;
			pol1=new JRadioButton("Normal");pol2=new JRadioButton("Reverse");
			ButtonGroup bg2=new ButtonGroup();
			pol1.setBounds(120, 60,120, 30);pol2.setBounds(280, 60, 80, 30);
			bg2.add(pol1);bg2.add(pol2);
			dp1.add(pol1);dp1.add(pol2);
			
			//Panel3 Deep Water Labels
			JLabel l8=new JLabel("WC Start Window Above Water Depth");
			l8.setBounds(10,10,250,30);
			t3.setBounds(270,10,100,30);
			dp2.add(l8);dp2.add(t3);
			JLabel l9=new JLabel("WC Window Length in Milliseconds");
			l9.setBounds(10,50,250,30);
			t4.setBounds(270,50,100,30);
			JLabel l10=new JLabel("Use RMS Velocity ( 1500 m/sec )");
			l10.setBounds(10,90,250,30);
			ButtonGroup bg3=new ButtonGroup();
			rmsvel1=new JRadioButton("Yes");rmsvel2=new JRadioButton("No");
			rmsvel1.setBounds(270,90,80,30);
			rmsvel2.setBounds(360,90,80,30);
			bg3.add(rmsvel1);bg3.add(rmsvel2);
			
			dp2.add(l9);dp2.add(t4);dp2.add(l10);dp2.add(rmsvel1);dp2.add(rmsvel2);
			
			//Panel3 Deep Water Labels
			JLabel l6=new JLabel("DW Start Window in Milliseconds");
			l6.setBounds(10,10,250,30);
			t1.setBounds(270,10,100,30);
			dp3.add(l6);dp3.add(t1);
			JLabel l7=new JLabel("DW Window Length in Milliseconds");
			l7.setBounds(10,50,250,30);
			t2.setBounds(270,50,100,30);
			dp3.add(l7);dp3.add(t2);
			
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
			
			rmsvel1.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	vel_selection=1;
		        }});
			rmsvel2.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	vel_selection=2;
		        }});
			
			
		
			
			error_control=0;
			
			b1.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	   String s1=t1.getText(); //dw_start
		        	   String s2=t2.getText(); //dw_end
		        	   String s3=t3.getText(); //wshift
		        	   String s4=t4.getText(); //wlength
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
				        	dw_start=gg1;
				        	dw_end=gg2;
				        	wshift=gg3;
				        	wlength=gg4;
				        	if(tstart==1)
				        	{
				        	GLP1.display();
				        	}
				        }
		        	  
		        	
		        }});
			
			b2.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		      
		        	   String s1=t1.getText(); //dw_start
		        	   String s2=t2.getText(); //dw_end
		        	   String s3=t3.getText(); //wshift
		        	   String s4=t4.getText(); //wlength
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
				        	dw_start=gg1;
				        	dw_end=gg2;
				        	wshift=gg3;
				        	wlength=gg4;
				        	if(tstart==1)
				        	{
				        	GLP1.display();
				        	}
				        }
				
		        
		        	
		         DP.setVisible(false);
		        }});
			

					cur_fill=fill;
					cur_reverse=reverse;
					cur_vel=vel_selection;
			
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
					
					if(vel_selection==1)
					{
						rmsvel1.setSelected(true);
					}
					if(vel_selection==2)
					{
						rmsvel2.setSelected(true);
					}
					
				
					t1.setText(String.valueOf(dw_start));	
					t2.setText(String.valueOf(dw_window));	
					t3.setText(String.valueOf(wshift));	
					t4.setText(String.valueOf(wlength));	
			DP.setVisible(true);
			
		}
		
		
		
		
		public void color_select() throws IOException
		{
			final JDialog cf=new JDialog(F,"Colormap",true);
			JPanel jp=new JPanel();
			COLP=new GLJPanel();
			cf.setResizable(false);
			cf.setLayout(null);
			jp.setLayout(null);
			jp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//	p6.setLayout(null);
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
		            	 GL2 gl = glautodrawable1.getGL().getGL2();
		        		 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT); 
		        		 gl.glBegin(GL2.GL_QUAD_STRIP);
		        		 float height=2f/((float)COLB.size()-1);
		        		 for(int i=0;i<COLB.size();i++)
		        		 {
		        			 float[] renk=COLB.get(i);
		        			 gl.glColor3f(renk[0], renk[1], renk[2]);
		        			 gl.glVertex2f(-1f,1f-height*i); 
							 gl.glVertex2f(1f,1f-height*i); 
		        		 }
		            	gl.glEnd();
						
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
						display();
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
						display();
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
		
		file_manager fm=new file_manager();
		
		public void display()
		{
			GLP1.display();
		}
		
		static private volatile boolean exit = true; 
		long size_of_file=(long)(5728+(number_of_traces)*((timee+2)*2f+244));
		static float timee;
		
	 	public class transfer extends Thread{
			 //Initially setting the flag as true
			 //This method will set flag as false
			 public void stopp() throws IOException
			   {
				 exit = true;
			   }	 
			 @Override
			    public void run() {
				 //Checking filepath
				 filepath=fm.findpath(project_name+"//"+linename);
				 size_of_file=(long)(5728+(number_of_traces)*((timee)*2f+244));
				 while(!exit)
				 {
				 filename=fm.finder(filepath, shot);
				 File F=new File(filename);
				 //Check the file is exists
				
				 if(F.exists())
				 {
					timesayac=0;
					 IB.setBackground(Color.green);
					 IB.setText(String.valueOf("FFID: "+shot));
					 ShotB.setText(String.valueOf("SP:  "+sp_number));
				    readsegd(filename);
				    GLP1.display();
				    GLP2.display();
				  
				  
				   
				    if(nonav==0)
				    {
				    	addrow(shot+".segd was read successfully"+"  SP:  "+sp_number,"OK");
				    	addrow_ok(shot+".segd was read successfully"+"  SP:  "+sp_number,"OK");
				    	
				    }
					
				    else if(nonav==1)
				    {
				    	  addrow(shot+".segd No Navigation Header"+"  SP:  "+sp_number,"NO NAV");	
				    	  addrow_comm(shot+".segd No Navigation Header"+"  SP:  "+sp_number,"NO NAV");	
				    }
				  
				   
				    
					shot++; 
					 if(shot==lsp+1)
					 {
						 finish();
					 }		 
				 }
				 else
				 {
					 
					 try {
					
							if(IB.getBackground()==Color.gray)
							{
								IB.setBackground(Color.yellow);
							}
							else if(IB.getBackground()==Color.yellow)
							{
								IB.setBackground(Color.gray);
							}
							IB.setText(String.valueOf(shot));
						 
						 
						TimeUnit.MILLISECONDS.sleep(1000);
						timesayac=timesayac+1;
						//After 60 seconds find the next shot
						if(timesayac==180)
						{
							addrow(shot+".segd does not exists"+"  SP:  "+sp_number,"N/A");	
							addrow_na(shot+".segd does not exists"+"  SP:  "+sp_number,"N/A");	
							shot++;
							timesayac=0;
						}
						
							
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				
				 }
			 }
			 }
		
	 	public void finish()
	 	{
	 		exit=true;
			B5[0].setEnabled(true);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
			B5[0].setEnabled(false);
			B5[1].setEnabled(false);
			B5[2].setEnabled(false);
			addrow("Sequence is finished"," ");	
			 IB.setText("Finished");
			 IB.setBackground(Color.red);
	 	}
	 	
	 	
	 	public void addrow(String A,String B)
	 	{
	 		//Update Log
			String s1;
			try {
				s1 = date();
				String s2=A;
				String s3=B;
				tm.addVERI(new VERI(s1,s2,s3));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 	}
	 	
	 	public void addrow_ok(String A,String B)
	 	{
	 		//Update Log
			String s1;
			try {
				s1 = date();
				String s2=A;
				String s3=B;
				tm_ok.addVERI(new VERI(s1,s2,s3));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 	}
	 	
	 	public void addrow_comm(String A,String B)
	 	{
	 		//Update Log
			String s1;
			try {
				s1 = date();
				String s2=A;
				String s3=B;
				tm_comm.addVERI(new VERI(s1,s2,s3));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 	}
	 	
	 	public void addrow_na(String A,String B)
	 	{
	 		//Update Log
			String s1;
			try {
				s1 = date();
				String s2=A;
				String s3=B;
				tm_na.addVERI(new VERI(s1,s2,s3));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 	}
	 	
		public String findshotname()
	 	{
	 		String output;
	 		String shotname;
	 		String sh=T1.getText();
	 		if(shot<1000)
	 			shotname="00000"+sh+".segd";
	 		else if(shot<10000)
	 			shotname="0000"+sh+".segd";
	 		else 
	 			shotname="000"+sh+".segd";
	 		output=filepath+"/"+shotname;
	 		return output;
	 	}
	 	
	 	
	 	
	 	public String findfilename()
	 	{
	 		String output;
	 		String shotname;
	 		String sh=String.valueOf(shot);
	 		if(shot<1000)
	 			shotname="00000"+sh+".segd";
	 		else if(shot<10000)
	 			shotname="0000"+sh+".segd";
	 		else 
	 			shotname="000"+sh+".segd";
	 		output=filepath+"/"+shotname;
	 		return output;
	 	}
	 	
	 	
		
	 	int ilk=0;
	 	String format_code;
	 	int add_blocks;
	 	int number_of_chan_set;
	 	String extend,extern;
	 	int external,extended;
	 	int record_length;
	 	
		public void readsegd(String filename)
		{
		
			byte[] b;
			byte[] g1;
			File f=new File(filename);
			RandomAccessFile raf;
			try {
				raf = new RandomAccessFile(f,"r");
				
				int ss=(int)f.length();
				 b=new byte[ss];
				raf.read(b);
				raf.close();

				g1= Arrays.copyOfRange(b, 0, 32);
				String GH = byte_hex(g1);
				String sp=GH.substring(0,4);
				format_code=GH.substring(4,8);
				add_blocks=Integer.parseInt(GH.substring(22, 23));
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
				
				
				//General Header-3
				g1 = Arrays.copyOfRange(b, 64, 96);
				GH = byte_hex(g1);
				sp_number=Integer.parseInt(Hex_Dec(GH.substring(16, 22)));
				
				chan_set(b);
				
		//		int size=b.length;
				int trace_start_point=(add_blocks+1)*32+number_of_chan_set*32+external+extended;
			//	int number_of_chan=(size-trace_start_point)/(trace_block_size)-4;
				float[] input;
				
				int start_chan=number_of_chan_per_streamer*(streamer-1);
				
				
				
				//External (Gun Pressure-Depth Pressure-Water Depth)
				int pos=trace_start_point-(external);
				byte[] ext = Arrays.copyOfRange(b, pos, pos+external);
				GH = byte_hex(ext);
				
				String sdep=GH.substring(160,172);
				float depth=hex2depth(sdep);
				if(depth>100)
				sp_wdepth=depth*1.33f;
				
				WDEPTH.add(sp_wdepth);
				SP[0].add(sp_number);
				VEL_SELECTION.add(vel_selection);
				DWSTART.add(dw_start);
				DWEND.add(dw_start+dw_window);
				WSHIFT.add(wshift);
				WLENGTH.add(wlength);
				
				
				
				//Aplitudes
				//Farklı formatlara göre geliştirilecektir
				 int sample_size=0;
				 if(format_code.equals("8058"))
					 sample_size=4;
				 if(format_code.equals("8036"))
					 sample_size=2;
				 if(format_code.equals("8038"))
					 sample_size=4;
				 
				 
				 
				 
				  pos=(add_blocks+1)*32+extended+external+number_of_chan_set*32;
			      g1= Arrays.copyOfRange(b,pos , pos+20);
			      String headerText = byte_hex(g1);
			      int header_ext= Integer.parseInt(headerText.substring(19, 20));
			      if(header_ext==0)
			      number_of_sample=(int)(record_length/sample_int);
			      else
			      {
			    	  g1= Arrays.copyOfRange(b,pos+20,pos+52);
				      headerText = byte_hex(g1);
				      number_of_sample=Integer.parseInt(Hex_Dec(headerText.substring(16, 20))); 
			      }
			      
			      
			      int trace_size=number_of_sample*sample_size;
			      int header_size=20+header_ext*32;
			      int trace_block_size=trace_size+header_size;
			      
			      int start=0;
			      int end=0;
			      int s=0;
			      for(int i=0;i<number_of_chan_set;i++)
			      {
			    	  if(cable_no[i]>0)
						{
			    	   if(i==0)
			    		   start=0;
			    	   else
			    		   start=chan_pos[i-1];
			    	   
			    	   end=chan_pos[i];
			    	   
			    	   trace_start_point= (add_blocks+1)*32+extended+external+number_of_chan_set*32+trace_block_size*start;
			    	   
			    	
			    	   
			    	   for(int j=0;j<end-start;j++)
			    	   {
			    		 pos=trace_start_point+trace_block_size*(j)+header_size;
			    		 byte[] AMP = Arrays.copyOfRange(b, pos, pos+trace_size);
			    		 FloatBuffer fb = ByteBuffer.wrap(AMP).asFloatBuffer();
							input = new float[number_of_sample];
							fb.get(input); 
							float[] temp=filter2(input);
							dw_end=dw_start+dw_window;
							//Deep water (Flat window)
							float g=mat.rms(temp, (int)(dw_start/sample_int), (int)(dw_end/sample_int));
							
							
							//Shallow Window
							int rcvnb=j;
							int offnb=(int)(offset+12.5*rcvnb);
							
							
							float window=(float)Math.sqrt(sp_wdepth*sp_wdepth+(offnb*offnb)/2.25f);
							float gg=0;
							int w1=0,w2=0;
							//VEL_SELECTION 2- FLAT WINDOW
							if(vel_selection==2)
							w1=(int) (sp_wdepth-wshift);
							//VEL_SELECTION 1- RMS VELOCITY
							if(vel_selection==1)
							w1=(int)window-wshift;
							
							w2=w1+wlength;
							
							if(w1<0)
							{
								w1=0;
								w2=wlength;
							}
							if(w1>time)
							{
								w1=(int)time-wlength;
								w2=(int)time;
							}
							//Calculation of rms
							gg=mat.rms(temp, (int)(w1/sample_int),(int)(w2/sample_int));
							
							
							//Verinin sonu kontrol edilir
							if(kk<RMS.length)
							{
								RMS[kk][number_of_traces-(s+1)]=g;
								RMS1[kk][number_of_traces-(s+1)]=gg;
							}
			    		 
							s++;
			    	   }
			    	   
			    	 
			    	  	
						}
			    	 
			      }
			      
			      kk++;
			      
				
			
				
				//Memory Menegement
				input=null;
				b=null;	
				System.gc();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			f=null;
			b=null;
			OUTPUT=null;
			
			
			//Get the values for the first shot
			if(first==0)
			{
			startx=0;
			endx=RMS.length;
			starty=0;
			endy=RMS[0].length;
			startxx=0;
			endxx=RMS1.length;
			startyy=0;
			endyy=RMS1[0].length;
			sx1.setEnabled(false);
			sy1.setEnabled(false);
			sx.setEnabled(false);
			sy.setEnabled(false);
			first=1;
			}
			System.gc();
			
		}
		
		int[] chan_id;					// chan id 
		int[] chan_number;				// number of channel per chan set
		int[] cable_no;
		int[] chan_pos;
		int number_of_gun;
 	
 	
 	public void chan_set(byte[] b)
	 {
		 
		 chan_id=new int[number_of_chan_set];
		 chan_number=new int[number_of_chan_set];
		 cable_no=new int[number_of_chan_set];
		 chan_pos=new int[number_of_chan_set];
		 
		int st=0;
		 
		 for(int i=0;i<number_of_chan_set;i++)
		 {
			 int pos= (add_blocks+1)*32+i*32;
			 byte[] ext = Arrays.copyOfRange(b, pos, pos+32);
			 String headerText = byte_hex(ext);

			 chan_number[i]=Integer.parseInt(headerText.substring(16, 20));
			 chan_id[i]=Integer.parseInt(headerText.substring(20, 21));
			 cable_no[i]=Integer.parseInt(headerText.substring(61, 62));
			 st=st+chan_number[i];
			 chan_pos[i]=st;
		 }
	 }
		
		int first=0;
		public static float[] filter2(float[] IN)
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
				float d=Y[i*2+1]/((time/sample_int)*3707);
				D[i]=d;
			}
			return D;
		}
		
		int selected_array=0;
		int selected_streamer=1;
		
		public int hex2pre(String A)
		{
			int b=0;
			int len=A.length()/2;
			String s="";
			for(int i=0;i<len;i++)
			{
				s=s+A.substring(2*i+1,2*i+2);
			}
			b=Integer.parseInt(s);
			return b;
		}
		
		public float hex2depth(String A)
		{
			float b=0;
			int a=Integer.parseInt(A.substring(1,2));
			b=b+1000*a;
			a=Integer.parseInt(A.substring(3,4));
			b=b+100*a;
			a=Integer.parseInt(A.substring(5,6));
			b=b+10*a;
			a=Integer.parseInt(A.substring(7,8));
			b=b+a;
			a=Integer.parseInt(A.substring(11,12));
			b=b+(float)a*0.1f;
			
			return b;
		}
		
		public float offset(float a,int array)
		{
			//Array 1 //Array2
			
			float b=0;
			float streamer_position=streamer_seperation*(selected_streamer-1);
			float array_position=streamer_seperation*(number_of_streamers/2-1);
			
			array_position=array_position+(streamer_seperation-array_seperation)/number_of_array+array_seperation*(array-1);
			float c=(streamer_position-array_position)*(streamer_position-array_position);
			a=(2f*a)/1.48f;
			float OFFSET=((offset*offset+c));
			b=(float)Math.sqrt((a*a)+(OFFSET)/(1.48f));
			b=b+25;
			return b;
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
		
		class VERITableModel extends AbstractTableModel {
			   /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// holds the strings to be displayed in the column headers of our table
			   final String[] columnNames = {"Date", "Action", "Status"};
			  
			   // holds the data types for all our columns
			   @SuppressWarnings("rawtypes")
			final Class[] columnClasses = {String.class, String.class, String.class};
			  
			   // holds our data
			   final Vector<VERI> data = new Vector<VERI>();
			   
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
			   /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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
			      else if(column == 2 && w.getStatus() == "NO NAV") {
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
		
		public String date() throws ParseException
		{
			String output;
			Calendar c=Calendar.getInstance();
			String a="dd.MM.yyyy HH:mm:ss";
			SimpleDateFormat sdf=new SimpleDateFormat(a);
			output=sdf.format(c.getTime());
			return output;
		}
		
		public static float[] filter(float[] IN)
		{
			int a=mat.findpow(IN.length);
			float[] A=new float[a];
			float[] B=new float[a];
			
			for(int i=0;i<IN.length;i++)
			{
				A[i]=IN[i];	
			}
			//FFT of Input Data
			float[][] C=mat.fft(A, B, true);
			//float[] F=mat.abs(C[0],C[1]);
			float[][] E=mat.butter(C,10f,3f,250f,0f,2f);
			E=mat.lowpass(E,3f,sample_int);
			float[][] D=mat.fft(E[0],E[1],false);
			float[] G=mat.abs(E[0],E[1]);
			int nos=(int)(time/sample_int);
			float[] OUTPUT=new float[nos];
			
			for(int i=0;i<nos;i++)
			{
				if(reverse==1)
				OUTPUT[i]=D[0][D[0].length-(1+i)];
				else
				OUTPUT[i]=-D[0][D[0].length-(1+i)];	
			}
			
			return OUTPUT;
		}
		
		//Plot Panel Parameteres
		int zoom=0;
		Point pe,ps;
		JLabel[] L=new JLabel[5];
		JLabel[] L1=new JLabel[5];
		int grid=3;
		
		public class DrawPanel extends GLJPanel {
		    private static final long serialVersionUID = 1L;
		    private ArrayList<Point> points;
		   
		    Graphics2D gg ;
		    public int sx,sy;
		    public int ex,ey;
		  
		    public DrawPanel() {
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
		        		 if(RMS!=null )
		        		 {
		        			 int a=(int)(startx+((float)e.getX()/GLP1.getWidth())*(endx-startx));
		        			 int b=(int)(starty+((float)e.getY()/GLP1.getHeight())*(endy-starty));
		        			 int c=fsp+a;
		        			 int d=number_of_traces-b;
		        			 L[0].setText("FFID: "+c);
		        			 if(a<SP[0].size())
		        			 L[1].setText("SP NO: "+SP[0].get(a)); 
		        			 else
		        			 L[1].setText("SP NO: "+0); 	 
		        			 L[2].setText("CHAN NO: "+d);
		        			 L[3].setText("AMPLITUDE: "+RMS[a][b]);
		        			 
		        			
		        		 }
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
					  gg= (Graphics2D)GLP1.getGraphics();
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
			          	 
			        	 if (e.getButton() == MouseEvent.BUTTON3){
			        		
			        		 int a=(int)(startx+((float)e.getX()/GLP1.getWidth())*(endx-startx));
		        			 int b=(int)(starty+((float)e.getY()/GLP1.getHeight())*(endy-starty));
		        			 int c=fsp+a;
		        			 int d=number_of_traces-b;
		        			 if(a<SP[0].size())
		        			 {
		        				 try {

		        				
								SingleSEGD SS=new SingleSEGD();
								SS.filepath=filepath;
								SS.fsp=fsp;
								SS.filename=fm.finder(filepath, c);
								SS.SHOT=String.valueOf(c);
								SS.project_name=project_name;
								SS.linename=linename;
								SS.seq_no=seq_no;
								SS.spdepth=(Float) WDEPTH.get(a);
								SS.wshift=(Integer) WSHIFT.get(a);
								SS.wlength=(Integer) WLENGTH.get(a);
								SS.off_meter=offset;
								SS.dw_start=(Integer)DWSTART.get(a);
								SS.dw_end=(Integer) DWEND.get(a);
								SS.vel_selection=(Integer) VEL_SELECTION.get(a);
								SS.initialize();
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        			 
		        			 }
		        			 
			        	 }
			          	 
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					  if(zoom==1 && !(ps==null))
		                {
						  if(pe.y>GLP1.getHeight())
							  pe.y=GLP1.getHeight()-1;
							  if(pe.y<0)
						      pe.y=0;	  
							  if(pe.x<0)
							  pe.x=0;
							  if(pe.x>GLP1.getWidth())
							  pe.x=GLP1.getWidth()-1;
						  
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
	 	       if(start==1 && OUTPUT!=null)
			    {
			    	int total_sample=endy-starty;
			    	float total_time=total_sample*sample_int;
			    	total_time=total_time/10f;
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
		    		time_now=time_int;
		    		while(time_now<=time)
		    		{
		    			int tyer=(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-starty)/(float)(endy-starty))*GLP1.getHeight());
		    			g2.drawLine(0,y,GLP1.getWidth(),y);
		    			time_now=time_now+time_int;	
		    		}

			    		
			    	
			    	
			    	}
			    }
	 	        
		    }  
		}
		
		int zoomm=0;
		
		public class DrawPanel1 extends GLJPanel {
		    private static final long serialVersionUID = 1L;
		    private ArrayList<Point> points;
		   
		    Graphics2D gg ;
		    public int sx,sy;
		    public int ex,ey;
		  
		    public DrawPanel1() {
		        points = new ArrayList<Point>();
		        setBackground(Color.WHITE);
		        
		        addMouseMotionListener(new MouseMotionAdapter() {
		        	 public void mouseDragged(MouseEvent e) {
		        			if(!(ps==null))
		                	{
		                		if(zoomm==1 )
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
		        		 if(RMS1!=null )
		        		 {
		        			 int a=(int)(startxx+((float)e.getX()/GLP1.getWidth())*(endxx-startxx));
		        			 int b=(int)(startyy+((float)e.getY()/GLP1.getHeight())*(endyy-startyy));
		        			 int c=fsp+a;
		        			 int d=number_of_traces-b;
		        			 L1[0].setText("FFID: "+c);
		        			 if(a<SP[0].size())
		        			 L1[1].setText("SP NO: "+SP[0].get(a)); 
		        			 else
		        			 L1[1].setText("SP NO: "+0); 	 
		        			 L1[2].setText("CHAN NO: "+d);
		        			 L1[3].setText("AMPLITUDE: "+RMS1[a][b]);
		        			 
		        			 
 			
		        		 }
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
					  gg= (Graphics2D)GLP2.getGraphics();
			          	 if (e.getButton() == MouseEvent.BUTTON1){
			              gg.setXORMode(Color.white);
			              ps = e.getPoint();
			              sx=ps.x;
			              sy=ps.y;
			              if(zoomm==1)
			              {
			             	 ex=sx;
			              	 ey=sy;    	
			              	 gg.drawLine(sx, sy, ex, ey);
					   }
			          	 }
			          	 
			          	 if (e.getButton() == MouseEvent.BUTTON3){
				        		
			        		 int a=(int)(startxx+((float)e.getX()/GLP2.getWidth())*(endxx-startxx));
		        			 int b=(int)(startyy+((float)e.getY()/GLP2.getHeight())*(endyy-startyy));
		        			 int c=fsp+a;
		        			 int d=number_of_traces-b;
		        			 if(a<SP[0].size())
		        			 {
		        			 try {
		        				 
		        				 
									SingleSEGD SS=new SingleSEGD();
									SS.filepath=filepath;
									SS.fsp=fsp;
									SS.filename=fm.finder(filepath, c);
									SS.SHOT=String.valueOf(c);
									SS.project_name=project_name;
									SS.linename=linename;
									SS.seq_no=seq_no;
									SS.spdepth=(Float) WDEPTH.get(a);
									SS.wshift=(Integer) WSHIFT.get(a);
									SS.wlength=(Integer) WLENGTH.get(a);
									SS.off_meter=offset;
									SS.dw_start=(Integer)DWSTART.get(a);
									SS.dw_end=(Integer) DWEND.get(a);
									SS.vel_selection=(Integer) VEL_SELECTION.get(a);
									SS.initialize();
		        				
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        			 
		        			 }
			        	 }
			          	 
			          	 
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					  if(zoomm==1 && !(ps==null))
		                {
						  if(pe.y>GLP2.getHeight())
							  pe.y=GLP2.getHeight()-1;
							  if(pe.y<0)
						      pe.y=0;	  
							  if(pe.x<0)
							  pe.x=0;
							  if(pe.x>GLP2.getWidth())
							  pe.x=GLP2.getWidth()-1;
						  
		                	 disp_resize1(ps,pe);
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
	 	       if(start==1 )
			    {
			    	int total_sample=endyy-startyy;
			    	float total_time=total_sample*sample_int;
			    	total_time=total_time/10f;
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
		    		time_now=time_int;
		    		while(time_now<=time)
		    		{
		    			int tyer=(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*GLP2.getHeight());
		    			g2.drawLine(0,y,GLP2.getWidth(),y);
		    			time_now=time_now+time_int;	
		    		}
			    	}
			    }
	 	        
		    }  
		}
		
		public class YLabel extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
				 System.gc();
			    super.paintComponent(g);
			    if(endy>0 && start==1)
			    {
			    	
			    	int total_sample=endy-starty;
			    	float total_time=total_sample*sample_int;
			    	total_time=total_time/10f;
			    	float time_int=0;	    	
			    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
			    	
			    	
			    	for(int i=0;i<times.length-1;i++)
			    	{
			    	 if(total_time<times[i] && total_time>=times[i+1])
			    		 time_int=times[i+1];
			    	}
			    	
			    	float time_now;	
		    		time_now=time_int;
		    		while(time_now<time)
		    		{
		    			int tyer=(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-starty)/(float)(endy-starty))*super.getHeight());
		    			g.drawLine(super.getWidth()-10,y,super.getWidth(),y);
		    			String ss=String.valueOf(number_of_traces-(int)(time_now/sample_int));
		    			g.drawString(ss,20,y+6);
		    			time_now=time_now+time_int;	
		    		}
			    	
			    }
		} 
		}
		
		public class YLabel1 extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
				 System.gc();
			    super.paintComponent(g);
			    if(start==1 && endy>0)
			    {
			    	
			    	int total_sample=endyy-startyy;
			    	float total_time=total_sample*sample_int;
			    	total_time=total_time/10f;
			    	float time_int=0;
			    	
			    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
			    	
			    	
			    	for(int i=0;i<times.length-1;i++)
			    	{
			    	 if(total_time<times[i] && total_time>=times[i+1])
			    		 time_int=times[i+1];
			    	}
			    	
			    	float time_now;	
		    		time_now=time_int;
		    		while(time_now<time)
		    		{
		    			int tyer=(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
		    			g.drawLine(super.getWidth()-10,y,super.getWidth(),y);
		    			String ss=String.valueOf(number_of_traces-(int)(time_now/sample_int));
		    			g.drawString(ss,20,y+6);
		    			time_now=time_now+time_int;	
		    		}
			    	

			    	
			    }
		} 
		}
		
		public class XLabel extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    System.gc();
			    if(start==1 && endx>0)
			    {
			    	/*
			    String ss="SP NB : "+String.valueOf(sp_number);
			    g.drawString(ss,10,12);
			    ss="FFID : "+String.valueOf(file_number);
			    g.drawString(ss,10,26);
			    ss="CABLE NO : "+String.valueOf(selected_streamer);
			    g.drawString(ss,10,40);
			    */
			    	
			    String tit="RMS DEEP WATER ";
			    g.drawString(tit,GLP1.getWidth()/2-96,30);
			    
			    String ss;
			    for(int j=0;j<lsp-fsp;j++)
	    		{
	    			float ssyer=0.5f+(j);
	    			int x=(int)(((float)(ssyer-startx)/(float)(endx-startx))*(super.getWidth()-24))+12;
	    			ss=String.valueOf(fsp+j);
	    			int xx=(int)(((float)ss.length()/2f)*6f);
	    			g.drawString(ss,(int) (x-xx),super.getHeight()-25);
	    			g.drawLine(x,super.getHeight()-10,x,super.getHeight());
	    			int sss=(endx-startx)/20;
	    			j=j+sss;
	    			
	    		}
			    }

			 }
			 }
		
		public class XLabel1 extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    System.gc();
			    
			    if(start==1 && endx>0 )
			    {
			    String ss;
			    String tit="RMS SHALLOW WATER ";
			    g.drawString(tit,GLP1.getWidth()/2-96,30);
			    
			    for(int j=0;j<lsp-fsp;j++)
	    		{
	    			float ssyer=0.5f+(j);
	    			int x=(int)(((float)(ssyer-startxx)/(float)(endxx-startxx))*(super.getWidth()-24))+12;
	    			ss=String.valueOf(fsp+j);
	    			int xx=(int)(((float)ss.length()/2f)*6f);
	    			g.drawString(ss,(int) (x-xx),super.getHeight()-25);
	    			g.drawLine(x,super.getHeight()-10,x,super.getHeight());
	    			int sss=(endxx-startxx)/20;
	    			j=j+sss;
	    			
	    		}}}}
			     
		
}
