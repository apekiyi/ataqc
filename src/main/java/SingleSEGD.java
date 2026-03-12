import java.awt.AWTException;
import java.awt.BasicStroke;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

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
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;


public class SingleSEGD {
		JFrame F;
		JPanel P1,P2,P3,P4,P5,P6,P7,P9,P12,P13,P15,P8,P17,P18,P19,P20,P22;
		GLJPanel GLP1,GLP2;
		JButton[] B1,B2,B3,B4,B6;
		JButton SPB;
		JScrollBar sx=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy=new JScrollBar();
		JScrollBar sx1=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy1=new JScrollBar();
		JScrollBar sx2=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy2=new JScrollBar();
		public String SHOT;
		JTextField T1,ST;
		int arraypanel=0;
		JRadioButton[] RP,RD; //Radiobutton Pressure-RadipButton Display
		JLabel[] SL; //Status bar parameters
		

	
		
		float a1=-1f,a2=1f,a3=-1f,a4=1f;
		
		file_manager fm=new file_manager();
		
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
		float scale=500;
		public static float time;
		static float max;
		static float min;
	
		

		
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
		static int pres_min;
		static int pres_max;
		static float depth_min;
		static float depth_max;
		public float off_meter;
		public int wshift,wlength,dw_start,dw_end;
		
		public String textfile;
		
		//Data from SEGD
		float[][] INPUT,OUTPUT;
		public  ArrayList[] PRESSURE;
		public  ArrayList[] DEPTH;
		public  ArrayList SP;
		public  ArrayList FFID;
		public int vel_selection;
		
//		public  ArrayList<Integer> PRESSURE1=new ArrayList<Integer>();
		public static int[] pres;
		public static int nonav=0;
		
		//Parameters of SEGD Data
		static float sample_int;
		static int number_of_sample;
		int shot; //current shot to check read
		int number_of_chan_per_streamer;
		public int sp_number;
		public float spdepth;
		int file_number;
	
		
		//Parameters for transfer
		int timesayac=0;
		
		//Variables for Log
		 VERITableModel tm ;
		 VERITableModel tm_ok ;
		 VERITableModel tm_na; 
		 VERITableModel tm_comm;
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
			int color=0;
			
			//Info panel
			JScrollPane JSP;
			DefaultTableModel m;
			JTable JL;
		
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
			
		//	project_name=PARAMETERS[0];	
		//	linename=project_name+PARAMETERS[17];
		//	seq_no=PARAMETERS[18];
		//	System.out.println(seq_no);System.out.println(project_name);System.out.println(linename);
			seqno=seq_no;
			time=Float.parseFloat(PARAMETERS[16]);
			timee=time;
			number_of_array=Integer.parseInt(PARAMETERS[1]);
			number_of_string=Integer.parseInt(PARAMETERS[2]);	
			fsp=Integer.parseInt(PARAMETERS[21]);
			lsp=Integer.parseInt(PARAMETERS[22]);
			number_of_traces=Integer.parseInt(PARAMETERS[10]);
			number_of_streamers=Integer.parseInt(PARAMETERS[11]);
			depth_min=Float.parseFloat(PARAMETERS[5]);
			depth_max=Float.parseFloat(PARAMETERS[6]);
			pres_min=Integer.parseInt(PARAMETERS[7]);
			pres_max=Integer.parseInt(PARAMETERS[8]);	
			}
			
	
public static void main(String[] args) {
	
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			try {
				SingleSEGD s=new SingleSEGD();
				s.F.setVisible(true);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
			
		});
		}
		
		public SingleSEGD() throws IOException, InterruptedException {
	//		initialize();
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
	
		F.setVisible(true);
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
						float sa1=OUTPUT.length*(float)sx.getValue()/10000f;
						float sa2=OUTPUT.length*(float)(sx.getValue()+ss2)/10000f;
						startx=(int)sa1;
						endx=(int)sa2;
						GLP1.display();
					}
				
				});
				
				sy.addAdjustmentListener(new AdjustmentListener() {

					@Override
					public void adjustmentValueChanged(AdjustmentEvent arg0) {
						// TODO Auto-generated method stub
					//	float sa4=-1+2*(float)((sy.getValue()))/10000f;
					//	float sa3=-1+2*(float)((sy.getValue()+ss4))/10000f;
						
						float sa3=OUTPUT[0].length*(float)sy.getValue()/10000f;
						float sa4=OUTPUT[0].length*(float)(sy.getValue()+ss4)/10000f;
						starty=(int)sa3;
						endy=(int)sa4;
						
						GLP1.display();
					}
				
				});
				
			//Labels
			L=new JLabel[3];
			L[0]=new JLabel("CHAN NO:");
			L[1]=new JLabel("TIME:");
			L[2]=new JLabel("AMPLITUDE:");
			
			for(int i=0;i<L.length;i++)
			{
				P18.add(L[i]);
			}
			
		}
		
		public void start_parameters() throws IOException
		{
			shot=fsp;
			number_of_chan_per_streamer=number_of_traces;
			for(int i=0;i<colmap.length;i++)
			{
				if(color==i)
				colormap(colmap[i]);
			}
			pres=new int[number_of_string]; //Pressures 
			PRESSURE=new ArrayList[number_of_string];
			DEPTH=new ArrayList[number_of_string];
			for(int i=0;i<PRESSURE.length;i++)
			{
				PRESSURE[i]=new ArrayList<Integer>();
				DEPTH[i]=new ArrayList<Float>();
			}
			SP=new ArrayList<Integer>();
			FFID=new ArrayList<Integer>();
		
			
		}
		
		public void frame()
		{
			//Frame 
			F=new JFrame();
			F.setBounds(100, 100, 2200, 1300);
			F.setVisible(true);
			F.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			F.setLayout(null);
			F.setTitle("SEGD VIEWER SEQ: "+seq_no);
			Image img=new ImageIcon("ICONS//SEGD.png").getImage();
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
			//SP panel
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
			P1.add(P4);P1.add(P5);P1.add(P6);
			//Time Panel
			P7=new YLabel();
		//	P7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			//Seismic Panel
			P8=new JPanel();
			P8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P8.setLayout(null);
			P2.add(P8);
			

			
			//Info Panel
			P17=new JPanel();
			P17.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P17.setLayout(null);
			P2.add(P17);
			m=new DefaultTableModel()
			{
				 public boolean isCellEditable(int row, int col) {
				      return false;
				   }	
			};
			;
			JL=new JTable();
			JL.setModel(m);
			JL.setRowHeight(30);
			m.addColumn("Objects");m.addColumn("Value");
			JSP=new JScrollPane(JL);
			P17.add(JSP);
			
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
			GLP1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P8.add(GLP1);
			//Shot Point 
		
		
			
			//Flop Time
			
		//	P12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			//Flop Headers
			P13=new YLabel2();
		//	P13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P13.setLayout(null);
			//Flop Headers
			P22=new JPanel();
			P22.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P22.setLayout(null);
			
			//Info Settings
			
			
			
			

			
			
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
		            	if(start==1 && OUTPUT!=null && COLS.size()>0)
		            	{
		            		 plot1(glautodrawable );
		            	}
		            	else 
		            	{
		            	whitescreen(glautodrawable );
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
			 max=maxmax(OUTPUT);
			 min=-max; 
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
						 if(OUTPUT!=null)
						 {
						 renk=findcols(OUTPUT[i+startx][j+starty]*scale);
						 gl.glColor3f(renk[0], renk[1], renk[2]);
						 if(OUTPUT[i+startx][j+starty]==9999)
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
						 float deger=((OUTPUT[i+startx][j+starty]*scale-min)/(max-min)-0.5f)*(width/2);
						 if(OUTPUT[i+startx][j+starty]!=9999)
						 {
							 if(deger<0 && OUTPUT[i+startx][j+starty+1]*scale<0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
							 }
							 else if(deger<0 && OUTPUT[i+startx][j+starty+1]*scale>0)
							 {
								 float a=-(OUTPUT[i+startx][j+starty])/(OUTPUT[i+startx][j+starty+1]-OUTPUT[i+startx][j+starty]);
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
							 }
							 else if(deger>0 && OUTPUT[i+startx][j+starty-1]*scale>0)
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
							 if(OUTPUT!=null)
							 {
							 float deger=((OUTPUT[i+startx][j+starty]*scale-min)/(max-min)-0.5f)*(width/2);
							 if(OUTPUT[i+startx][j+starty]!=9999)
							 {
								 if(deger>0 && OUTPUT[i+startx][j+starty+1]*scale>0)
								 {
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
								 }
								 else if(deger>0 && OUTPUT[i+startx][j+starty+1]*scale<0)
								 {
									 float a=-(OUTPUT[i+startx][j+starty])/(OUTPUT[i+startx][j+starty+1]-OUTPUT[i+startx][j+starty]);
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
								 }
								 else if(deger<0 && OUTPUT[i+startx][j+starty-1]*scale<0)
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
				 for(int i=0;i<x;i++)
				 {
					 gl.glBegin(GL.GL_LINE_STRIP);
				//	 gl.glVertex2f(-1f+width*i,1f);
					 for(int j=0;j<y;j++)
					 { 
						// if(j+starty<D.length && i+startx<D[0].length)
						 if(OUTPUT!=null)
						 {
						 float deger=((OUTPUT[i+startx][j+starty]*scale-min)/(max-min)-0.5f)*(width/2);
						 if(OUTPUT[i+startx][j+starty]!=9999)
						 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));
						 else
						 gl.glEnd();
						 gl.glBegin(GL.GL_LINE_STRIP);
						 }
					 }
					 gl.glEnd();
				 }
				 
			 }
			 
			 
			 //Shallow-1
			 gl.glColor3f(0f, 1f, 0f);
			 gl.glLineWidth(3f);
			 gl.glBegin(GL.GL_LINE_STRIP);
			 float y1=0;
			 for(int i=0;i<x;i++)
			 {
				 float a=spdepth;
				 float offnb=(startx+i)*12.5f+off_meter;
				 if(vel_selection==1)
				 a=(float)Math.sqrt(spdepth*spdepth+(offnb*offnb)/2.25f);
				 a=a-wshift;
				y1=a/sample_int;
				y1=(y1-starty)/(endy-starty);
				y1=1f-2f*y1;
				float x1=-1f+width*i;
				gl.glVertex2f(x1, y1);		 
			 }
			 gl.glVertex2f(1, y1);
			 gl.glEnd();
			 
			 //Shallow-2
			 gl.glColor3f(0f, 1f, 0f);
			 gl.glLineWidth(3f);
			 gl.glBegin(GL.GL_LINE_STRIP);
			 y1=0;
			 for(int i=0;i<x;i++)
			 {
				 float a=spdepth;
				 float offnb=(startx+i)*12.5f+off_meter;
				 if(vel_selection==1)
				 a=(float)Math.sqrt(spdepth*spdepth+(offnb*offnb)/2.25f);
				 a=a-(wshift-wlength);
				y1=a/sample_int;
				y1=(y1-starty)/(endy-starty);
				y1=1f-2f*y1;
				float x1=-1f+width*i;
				gl.glVertex2f(x1, y1);		 
			 }
			 gl.glVertex2f(1, y1);
			 gl.glEnd();
			 
			 //Depth-1
			 gl.glColor3f(1f, 1f, 0f);
			 gl.glLineWidth(3f);
			 gl.glBegin(GL.GL_LINE_STRIP);
			 y1=0;
			 for(int i=0;i<x;i++)
			 {
				 float a=dw_start;
				y1=a/sample_int;
				y1=(y1-starty)/(endy-starty);
				y1=1f-2f*y1;
				float x1=-1f+width*i;
				gl.glVertex2f(x1, y1);		 
			 }
			 gl.glVertex2f(1, y1);
			 gl.glEnd();
			 
			 //Depth-2
			 gl.glColor3f(1f, 1f, 0f);
			 gl.glLineWidth(3f);
			 gl.glBegin(GL.GL_LINE_STRIP);
			 y1=0;
			 for(int i=0;i<x;i++)
			 {
				 float a=dw_end;
				y1=a/sample_int;
				y1=(y1-starty)/(endy-starty);
				y1=1f-2f*y1;
				float x1=-1f+width*i;
				gl.glVertex2f(x1, y1);		 
			 }
			 gl.glVertex2f(1, y1);
			 gl.glEnd();
			 
		
			P7.repaint();	
			P9.repaint();
	//		TP.repaint();
			
	     }
		
		public float max(float[] INPUT)
		{
			float OUTPUT=INPUT[0];
			for(int i=0;i<INPUT.length;i++)
			{
				if(OUTPUT<INPUT[i])
				OUTPUT=INPUT[i];
			}
			return OUTPUT;
		}
		
		public float min(float[] INPUT)
		{
			float OUTPUT=INPUT[0];
			for(int i=0;i<INPUT.length;i++)
			{
				if(OUTPUT>INPUT[i])
				OUTPUT=INPUT[i];
			}
			return OUTPUT;
		}
		
		
		
		float x1,x2,y1,y2,x3,y3;
		int ss1,ss2,ss3,ss4;
		float a5,a6,a7,a8;
		
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
			 if(y1<y2)
			 {
			  y3=y1;
			  y1=y2;
			  y2=y3;
			 }
			
			 a1=a5;
			 a2=a6;
			 a3=a7;
			 a4=a8;
			 
			 if(x1==x2 || y1==y2)
			 {
				 a1=-1;
				 a2=1;
				 a3=-1;
				 a4=1;
			 }	 
			 scroll1();
			}
		 
		 public void disp_resize2(Point ps,Point pe)
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
			 if(y1<y2)
			 {
			  y3=y1;
			  y1=y2;
			  y2=y3;
			 }
			
			 a11=a5;
			 a22=a6;
			 a33=a7;
			 a44=a8;
			 
			 if(x1==x2 || y1==y2)
			 {
				 a11=-1;
				 a22=1;
				 a33=-1;
				 a44=1;
			 }	 
			 scroll2();
			}
		
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
		 
		 int fftx1,fftx2,ffty1,ffty2;
		 public void fft_points(Point ps,Point pe)
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
			 fftx1=startxx+(int)((float)((float)x1/((float)GLP1.getWidth())*(endxx-startxx)))+1;
			 fftx2=startxx+(int)((float)((float)x2/((float)GLP1.getWidth())*(endxx-startxx)))+1; 
			 ffty1=startyy+(int)((float)((float)y1/((float)GLP1.getHeight())*(endyy-startyy)));
			 ffty2=startyy+(int)((float)((float)y2/((float)GLP1.getHeight())*(endyy-startyy)));
			 
			 spectrum spec=new spectrum();
			 spec.F=F;
			 spec.OUTPUT=OUTPUT;
			 spec.sample_int=sample_int;
			 spec.freq_display(fftx1,fftx2,ffty1,ffty2);
			 
			 
			}
		 
	
		 
		   
		 
		 
		 
			public void zoom_out()
			{
				F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				startx=0;
				endx=OUTPUT.length;
				starty=0;
				endy=OUTPUT[0].length;
				GLP1.display();
				sx.setEnabled(false);
				sy.setEnabled(false);
				F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
		
			
			public void scroll()
			{	   
			   ss1=(int)((float)startx/(float)(OUTPUT.length)*10000);
			   ss2=(int)((float)(endx-startx)/(float)(OUTPUT.length)*10000);
			   sx.setValues(ss1,ss2,0,10000); 
			   sx.setEnabled(true);
				
			   ss3= (int)((float)starty/(float)(OUTPUT[0].length)*10000);
			   ss4= (int)((float)(endy-starty)/(float)(OUTPUT[0].length)*10000);
			   sy.setValues(ss3,ss4,0,10000);
			   sy.setEnabled(true);
			}
			
			public void scroll1()
			{	   
				 	ss1= (int)(((a1+1f)/2f)*10000);
				 	ss2= (int)(((a2-a1)/2f)*10000);
					sx1.setValues(ss1,ss2,0,10000);
					sx1.setEnabled(true);
					ss3= (int)(((a3+1f)/2f)*10000);
					ss4= (int)(((a4-a3)/2f)*10000);
					sy1.setValues(ss3,ss4,0,10000);
					sy1.setEnabled(true);
			}
			
			public void scroll2()
			{	   
				 	ss1= (int)(((a11+1f)/2f)*10000);
				 	ss2= (int)(((a22-a11)/2f)*10000);
					sx2.setValues(ss1,ss2,0,10000);
					sx2.setEnabled(true);
					ss3= (int)(((a3+1f)/2f)*10000);
					ss4= (int)(((a4-a3)/2f)*10000);
					sy2.setValues(ss3,ss4,0,10000);
					sy2.setEnabled(true);
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
			SPB=new JButton();
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
				
				//Shot selection
				B3=new JButton[2];
				for(int i=0;i<B3.length;i++)
				{
				ImageIcon icon=new ImageIcon("ICONS//"+"ww"+String.valueOf(i)+".png");
				B3[i]=new JButton();
				B3[i].setBounds(10*(i+1)+32*i, 4, 32, 32);
				B3[i].setMargin(new Insets(0,0,0,0));
				B3[i].setBorder(null);
				B3[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						if(e.getSource()==B3[0])
						{
							T1.setText(String.valueOf(fsp));
							filename=	fm.finder(filepath, fsp);
							disp();
						}
						if(e.getSource()==B3[1])
						{
							int g=Integer.parseInt(T1.getText())-1;
							filename=	fm.finder(filepath, g);
							T1.setText(String.valueOf(g));
							disp();
						}
					}
				});
				P5.add(B3[i]);
				}
				
			//	T1=new JTextField(String.valueOf(fsp));
				T1=new JTextField(SHOT);
				start=1;
				disp();
				T1.setBounds(B3[1].getX()+42, 4, 74,32);
				P5.add(T1);
				
				//Shot Value changed
				Action action1=new AbstractAction()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String s=T1.getText();
						
						if(shot<Integer.valueOf(s))
						s=String.valueOf(shot);
						
						int g=Integer.parseInt(s);
						filename=fm.finder(filepath, g);
						
						try{
							disp();
						}
						catch(NumberFormatException f)
						{
							Toolkit.getDefaultToolkit().beep();
						}
					}
					
				};
				T1.addActionListener(action1);
				
				
				
				B4=new JButton[2];
				for(int i=0;i<B4.length;i++)
				{
				ImageIcon icon=new ImageIcon("ICONS//"+"ww"+String.valueOf(i)+".png");
				B4[i]=new JButton();
				B4[i].setBounds(T1.getX()+74+10*(i+1)+32*i, 4, 32, 32);
				B4[i].setMargin(new Insets(0,0,0,0));
				B4[i].setBorder(null);
				B4[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						if(e.getSource()==B4[0])
						{
							int g=Integer.parseInt(T1.getText())+1;
							filename=	fm.finder(filepath, g);
							T1.setText(String.valueOf(g));
							disp();
						}
						if(e.getSource()==B4[1])
						{
							T1.setText(String.valueOf(shot));
							disp();
						}
					}
				});
				P5.add(B4[i]);
				}
				
				
				B3[0].setText("<<");
				B3[1].setText("<");
				B4[0].setText(">");
				B4[1].setText(">>");
				B3[0].setEnabled(false);
				B4[1].setEnabled(false);
				
				B3[0].setToolTipText("Go to First SP Number");
				B3[1].setToolTipText("Decrease SP Number");
				B4[0].setToolTipText("Increase SP Number");
				B4[1].setToolTipText("Go to Last SP Number");
				
			
				
				{
	//			ImageIcon icon=new ImageIcon("ICONS//"+"r"+String.valueOf(i)+".png");
				
		
				}
				
				
				
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
								scale=scale-5f;
								String scale1=String.format("%.1f", scale);
								ST.setText(scale1);
								GLP1.repaint();
							}
							if(e.getSource()==B6[1])
							{
								scale=scale+5f;
								String scale1=String.format("%.1f", scale);
								ST.setText(scale1);
								GLP1.repaint();
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
					 
				
			//		 SPB.setFont(new Font("Arial",40));
					 P17.add(SPB);
					 
					 String[] INFOS={"Information","Save","Save as a .txt file","Take a Screenshot","Save Screenshot to Database"};
					
						
						
					 
					
				
					
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
			pw.println("Number of Navigation Header Error: "+comm_error);
			
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
				pw.println("------------------------------------No Navigation Header--------------------------------------");
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
		
		
		public void savereport() throws IOException
		{
			String filename="PROJECTS/"+project_name+"/"+seq_no+"/shot.report";
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
		
		public void disp()
		{
			
			 File f=new File(filename);
			 //Check the file is exists
			 if(f.exists())
			 {
				 F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			    readsegd(filename);
			    
			    T1.setText(T1.getText());
			    SPB.setText(T1.getText());
			    SPB.setBackground(Color.green);
			    
			    GLP1.display();
			    F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			 } 
			 else
			 {
				 T1.setText(String.valueOf(fsp));
				 filename=fm.finder(filepath,fsp);
				 disp();
			 }
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
		
		public void hor_out()
		{
			startx=0;
			endx=OUTPUT.length;
			GLP1.display();
			sx.setEnabled(false);
		}
		
		public void ver_out()
		{
			starty=0;
			endy=OUTPUT[0].length;
			GLP1.display();
			sy.setEnabled(false);
		}
		
		int tstart=0;
		public void pause()
		{
			F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			TimeUnit.MILLISECONDS.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			exit=true;
			addrow("Paused by user"," ");
			
			T1.setEnabled(true);
			tstart=0;
			
			SPB.setText("Paused");
			SPB.setBackground(Color.yellow);
			F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		int control=0;
		
		public void stop() throws InterruptedException
		{
			exit=true;
			F.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			TimeUnit.MILLISECONDS.sleep(7000);
			addrow("Stopped by user"," ");
			
			tstart=0;
			OUTPUT=null;
			
			control=0;
			
			for(int i=0;i<number_of_string;i++)
			{
				PRESSURE[i].removeAll(PRESSURE[i]);
				DEPTH[i].removeAll(DEPTH[i]);
			}
			
			tm.removeall();
			tm_ok.removeall();
			tm_na.removeall();
			tm_comm.removeall();
			
			GLP1.display();
			
		
			SPB.setText("Stopped");
			SPB.setBackground(Color.red);
			shot=fsp;
			F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		
		public void start()
		{
			addrow("Started by user"," ");
			transfer t=new transfer();
			exit=false;
			t.start();
			start=1;
			tstart=1;
			//Enable puase-stop buttons
		
			T1.setEnabled(false);
			
			
			
		}
		
		public void resize()
		{
			P1.setBounds(10,10,F.getWidth()-25,50); 
			P3.setBounds(10,F.getHeight()-90,F.getWidth()-25,50);
			P2.setBounds(10,70,F.getWidth()-25,P3.getY()-75);
			P4.setBounds(10,5,50+32*4,40);
		//	P4.setBounds(P15.getX()+P15.getWidth()+10,5,50+32*4,40);
			P5.setBounds(P4.getX()+P4.getWidth()+10,5,70+32*6,40);
			P6.setBounds(P5.getX()+P5.getWidth()+10,5,60+32*5,40);
			P17.setBounds(P1.getX(),10,(int)((P2.getWidth()-40)*0.2f),P2.getHeight()-20);
			SPB.setBounds(100,30,P17.getWidth()-200,90);
			JSP.setBounds(0, 150, P17.getWidth(), P17.getHeight()-150);
			JL.getColumnModel().getColumn(0).setPreferredWidth((int)(JSP.getWidth()*0.25f));
			JL.getColumnModel().getColumn(1).setPreferredWidth((int)(JSP.getWidth()*0.75f));
			P8.setBounds(P17.getX()+P17.getWidth()+10, P17.getY(),(int)((P2.getWidth()-40)*0.8f),P17.getHeight());
			P20.setBounds(P6.getX()+P6.getWidth()+10,5,50+32*4,40);
		
		
			//FFT Panel 	
			P7.setBounds(10,100,80,(int)((P2.getHeight()-160)));
			GLP1.setBounds(90, 100, P8.getWidth()-120,P7.getHeight());
			sx.setBounds(GLP1.getX(), GLP1.getY()+GLP1.getHeight(),GLP1.getWidth(),20);
			sy.setBounds(GLP1.getX()+GLP1.getWidth(),GLP1.getY(),20,GLP1.getHeight());		
			P9.setBounds(GLP1.getX()-12,10,GLP1.getWidth()+24, 90);		
			//Gun Pressure Panel
	//	
		//	P8.repaint();P8.validate();
		
			
			P18.setBounds(P8.getX(), 5, P8.getWidth(), 40);
		//	P19.setBounds(P16.getX(), 5, P16.getWidth(), 40);
			for(int i=0;i<L.length;i++)
			{
				L[i].setBounds(10*(i+1)+150*i,5,150,30);
			}
			

			
		}
	
		
		JTextField t1,t2,t3,t4,t5;
		int error_control,cur_fill,cur_reverse,cur_grid,gg1,gg2,gg3;
		float gg4,gg5;
		public static int reverse=1;
		
		public void settings() throws IOException
		{
			final JDialog DP=new JDialog(F,"Display Settings",true);
			JPanel dp1,dp2,dp3,dp4;
			JButton b1,b2;
			//Main Panels
			dp1=new JPanel();
			dp2=new JPanel();
			dp3=new JPanel();
			dp4=new JPanel();
			b1=new JButton("Apply");
			b2=new JButton("Apply and Close");
			DP.setLayout(null);
			dp1.setLayout(null);dp2.setLayout(null);dp3.setLayout(null);;dp4.setLayout(null);
			DP.setBounds(F.getX()+100, F.getY()+100, 400,540);
			DP.setResizable(false);
			dp1.setBounds(10,10,350,150);dp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp2.setBounds(10,170,350,100);dp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp3.setBounds(10,280,350,100);dp3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp4.setBounds(10,400,350,50);dp4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			b1.setBounds(10, 470, 100, 30);
			b2.setBounds(120, 470, 150, 30);
			
			//Add Panels
			DP.add(dp1);DP.add(dp2);DP.add(dp3);DP.add(dp4);
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
			c1.setBounds(120, 10,80, 30);c2.setBounds(210, 10, 80, 30);
			bg1.add(c1);bg1.add(c2);
			dp1.add(c1);dp1.add(c2);
			//RadioButtons P1 S2
			JRadioButton pol1,pol2;
			pol1=new JRadioButton("Normal");pol2=new JRadioButton("Reverse");
			ButtonGroup bg2=new ButtonGroup();
			pol1.setBounds(120, 60,80, 30);pol2.setBounds(210, 60, 80, 30);
			bg2.add(pol1);bg2.add(pol2);
			dp1.add(pol1);dp1.add(pol2);
			//RadioButtons P1 S3
			JRadioButton g1,g2;
			g1=new JRadioButton("Yes");g2=new JRadioButton("No");
			ButtonGroup bg3=new ButtonGroup();
			g1.setBounds(120, 110,80, 30);g2.setBounds(210, 110, 80, 30);
			bg3.add(g1);bg3.add(g2);
			dp1.add(g1);dp1.add(g2);
			//Panel2 Labels
			JLabel l4=new JLabel("Minimum Pressure (psi)");JLabel l5=new JLabel("Maximum Pressure (psi)");
			l4.setBounds(10,10,200,30);l5.setBounds(10,60,200,30);
			dp2.add(l4);dp2.add(l5);
			//Panel2 TextField
			t1=new JTextField();t2=new JTextField();t3=new JTextField();
			t1.setBounds(220,10,100,30);t2.setBounds(220,60,100,30);
			dp2.add(t1);dp2.add(t2);
			
			//Panel3 Labels
			JLabel l7=new JLabel("Minimum Depth (meter)");JLabel l8=new JLabel("Maximum Depth (meter)");
			l7.setBounds(10,10,200,30);l8.setBounds(10,60,200,30);
			dp3.add(l7);dp3.add(l8);
			//Panel3 TextField
			t4=new JTextField();t5=new JTextField();
			t4.setBounds(220,10,100,30);t5.setBounds(220,60,100,30);
			dp3.add(t4);dp3.add(t5);
			
			//Panel4 Labels
			JLabel l6=new JLabel("End Time in Milliseconds");
			l6.setBounds(10,10,200,30);l5.setBounds(10,60,200,30);
			dp4.add(l6);
			//Panel4 TextField
			t3.setBounds(220,10,100,30);
			dp4.add(l6);dp4.add(t3);
			if(tstart==1)
			t3.setEnabled(false);
			else
			t3.setEnabled(true);	
			
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
		        	gg4=Float.parseFloat(s4);
					t4.setBackground(Color.white);
				}
				catch(NumberFormatException f)
				{
					t4.setBackground(Color.red);
					Toolkit.getDefaultToolkit().beep();
					error_control=1;
				}
		        
		        String s5=t5.getText();
		        try{
		        	gg5=Float.parseFloat(s5);
					t5.setBackground(Color.white);
				}
				catch(NumberFormatException f)
				{
					t5.setBackground(Color.red);
					Toolkit.getDefaultToolkit().beep();
					error_control=1;
				}
		        
		        if(error_control==0)
		        {
		        	fill=cur_fill;
		        	reverse=cur_reverse;
		        	grid=cur_grid;
		        	pres_min=gg1;
		        	pres_max=gg2;
		        	time=gg3;
		        	depth_min=gg4;
		        	depth_max=gg5;
		        	endy=(int)(time/sample_int);
		        	
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
		        	gg4=Float.parseFloat(s4);
					t4.setBackground(Color.white);
				}
				catch(NumberFormatException f)
				{
					t4.setBackground(Color.red);
					Toolkit.getDefaultToolkit().beep();
					error_control=1;
				}
		        
		        String s5=t5.getText();
		        try{
		        	gg5=Float.parseFloat(s5);
					t5.setBackground(Color.white);
				}
				catch(NumberFormatException f)
				{
					t5.setBackground(Color.red);
					Toolkit.getDefaultToolkit().beep();
					error_control=1;
				}
		        
		        if(error_control==0)
		        {
		        	fill=cur_fill;
		        	reverse=cur_reverse;
		        	grid=cur_grid;
		        	pres_min=gg1;
		        	pres_max=gg2;
		        	time=gg3;
		        	depth_min=gg4;
		        	depth_max=gg5;
		        	endy=(int)(time/sample_int);
		        	if(tstart==1)
		        	{
		        	GLP1.display();
		    
		        	}
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
					
					String tt1=String.valueOf((int)pres_min);
					t1.setText(tt1);
					String tt2=String.valueOf((int)pres_max);
					t2.setText(tt2);
					String tt4=String.valueOf(depth_min);
					t4.setText(tt4);
					String tt5=String.valueOf(depth_max);
					t5.setText(tt5);
					
				
					String tt3=String.valueOf((int)time);
					t3.setText(tt3);
					
			
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
			LF.setTitle("SEGD Log");
			Image img=new ImageIcon("ICONS//SEGD.png").getImage();
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
		
		public void display()
		{
			GLP1.display();
		}
		
		static private volatile boolean exit = true; 
		long size_of_file=(long)(5728+(number_of_traces+2)*((timee+2)*2f+240));
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
			//	 filepath=findpath1();
				 size_of_file=(long)(5728+(number_of_traces+2)*((timee+2)*2f+244));
			//	  JOptionPane.showMessageDialog(null, String.valueOf(size_of_file),filename, JOptionPane.ERROR_MESSAGE);
				 while(!exit)
				 {
				 filename=findfilename();
				 File F=new File(filename);
				 //Check the file is exists
				 if(F.exists())
				 {
					timesayac=0;
				    readsegd(filename);
				    T1.setText(String.valueOf(shot));
					 SPB.setText(String.valueOf(shot));
					 SPB.setBackground(Color.green);
				   
				    GLP1.display();
			
				  
				   
				    if(nonav==0)
				    {
				    	addrow(shot+".segd was read succesfully","OK");
				    	addrow_ok(shot+".segd was read succesfully","OK");
				    }
					
				    else if(nonav==1)
				    {
				    	  addrow(shot+".segd No Navigation Header","NO NAV");	
				    	  addrow_comm(shot+".segd No Navigation Header","NO NAV");	
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
						    SPB.setText(String.valueOf(shot));
						    if(SPB.getBackground()==Color.yellow)
						    SPB.setBackground(Color.gray);
						    else
						    SPB.setBackground(Color.yellow);	
						 
						 
						TimeUnit.MILLISECONDS.sleep(1000);
						timesayac=timesayac+1;
						//After 60 seconds find the next shot
						if(timesayac==180)
						{
							addrow(shot+".segd does not exists","N/A");	
							addrow_na(shot+".segd does not exists","N/A");	
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
	 		addrow("Sequence is finished"," ");
	 		T1.setEnabled(true);
			SPB.setText("Finished");
			SPB.setBackground(Color.red);
			
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
	 	int external=0;
	 	int extended;
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

				//General Header-1
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
				SP.add(sp_number);
				FFID.add(file_number);
				
				
				
				
		//		int size=b.length;
				int trace_start_point=(add_blocks+1)*32+number_of_chan_set*32+external+extended;
			//	int number_of_chan=(size-trace_start_point)/(trace_block_size)-4;
				float[] input;
				
				int start_chan=0;
				INPUT=new float[number_of_chan_per_streamer][];
				float[][] OUTPUT1=new float[number_of_chan_per_streamer][];
				
				
				int[] chan_id;					// chan id 
				int[] chan_number;				// number of channel per chan set
				int[] cable_no;
				int number_of_gun;
			 
			
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
			
				
				
				
				
				 //tanımlamalar
				 byte[] AMP ;
				 FloatBuffer fb;
				 
				 
				 
				 //Farklı formatlara göre geliştirilecektir
				 int sample_size=0;
				 if(format_code.equals("8058"))
					 sample_size=4;
				 if(format_code.equals("8036"))
					 sample_size=2;
				 if(format_code.equals("8038"))
					 sample_size=4;
				 
				 
				 
				 
				 int pos=(add_blocks+1)*32+extended+external+number_of_chan_set*32;
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
				  int trace_position=0;		//Seçilen kanalın hangi trace pozisyonuna geldiğini hesaplar
				  int trace_block_size=trace_size+header_size;
				
				
				  //Hangi stremaer ile işlem yapılacaksa pozisyonu bulunur
			      for(int i=0;i<number_of_chan_set;i++)
					 {
			    	  
						 if(cable_no[i]!=streamer)
						 trace_position=trace_position+chan_number[i]*(trace_size+header_size);    	  
					 }
				  
				
				for(int i=0;i<number_of_chan_per_streamer;i++)
				{
					pos=trace_start_point+trace_block_size*(i)+header_size;
					AMP = Arrays.copyOfRange(b, pos, pos+trace_size);
					fb = ByteBuffer.wrap(AMP).asFloatBuffer();
					input = new float[number_of_sample];
					fb.get(input); 
					INPUT[i]=input;
					OUTPUT1[i]=filter2(input);
				}
				
				OUTPUT=OUTPUT1;
				
				//Add Element To List
				m.setRowCount(0);
				Object[] A=new Object[2];
				A[0]="File Name";A[1]=filename;m.addRow(A);
				A[0]="File Size";A[1]=b.length+" bytes";m.addRow(A);
				A[0]="File Number";A[1]=file_number;m.addRow(A);
				A[0]="Shot Point Number";A[1]=sp_number;m.addRow(A);
				A[0]="Record Length";A[1]=record_length+" ms";m.addRow(A);
				A[0]="Sample Interval";A[1]=sample_int+" ms";m.addRow(A);
				A[0]="Number of General Block";A[1]=(int)(add_blocks+1);m.addRow(A);
				A[0]="Number of Chan Set";A[1]=number_of_chan_set;m.addRow(A);
				A[0]="Size of Extended Header";A[1]=extended;m.addRow(A);
				A[0]="Size of External Header";A[1]=extern;m.addRow(A);
				A[0]="File Header Length";A[1]=trace_start_point+" bytes";m.addRow(A);
				A[0]="Trace Block Size";A[1]=trace_block_size+" bytes";m.addRow(A);
				if(nonav==0){A[0]="Status";A[1]="OK";m.addRow(A);}
				else{A[0]="Status";A[1]="NO NAV";m.addRow(A);}
				
				
				//Memory Menegement
				OUTPUT1=null;
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
		//	OUTPUT=INPUT;
			INPUT=null;
			
			System.gc();
			//Get the values for the first shot
			if(ilk==0)
			{
				startx=0;
				endx=number_of_chan_per_streamer;
				starty=0;
				int nos=(int)(time/sample_int);
				endy=nos;
				ilk=1;		
			}
			
			startxx=0;
			startyy=pres_min;
			endxx=PRESSURE[0].size();
			endyy=pres_max;
			
		}
		
		public static float[] filter2(float[] IN)
		{
			int a=mat.findpow(IN.length);
			float[] B=new float[a*2+1];

			//Add normal values
			for(int i=0;i<IN.length;i++)
			{
				B[i*2+1]=IN[i];	
			}
			
			float[] X = FFT.four1(B, a, 1);
			float[] Z=FFT.low(X, 10, sample_int);
			float[] Y=	FFT.four1(Z, a,-1);
			
			
			float[] D=new float[IN.length];
			for(int i=0;i<IN.length;i++)
			{
				D[i]=Y[i*2+1];
			}
			return D;
		}
		
		public static float[] amplitude(float[] IN)
		{
			int a=mat.findpow(IN.length);
			float[] B=new float[a*2+1];

			//Add normal values
			for(int i=0;i<IN.length;i++)
			{
				B[i*2+1]=IN[i];	
			}
			
			float[] X = FFT.four1(B, a, 1);
			
			float[] D=new float[a/2];
			float dmax=0;
			for(int i=0;i<D.length;i++)
			{
				D[i]=X[2*i+1]*X[2*i+1]+X[2*i+2]*X[2*i+2];
				D[i]=(float)Math.sqrt((double)D[i]);
				if(D[i]>dmax)
				dmax=D[i];
			}
			
			//Find max
			
			for(int i=0;i<D.length;i++)
			{
				D[i]=(D[i]/dmax)*100f;
			}
			return D;
		}
		
	
		
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
		Point pe1,ps1;
		JLabel[] L=new JLabel[4];
		int grid=1;
		
		public class DrawPanel extends GLJPanel {
		    private static final long serialVersionUID = 1L;
		    private ArrayList<Point> points;
		   
		    Graphics2D gg ;
		    public int sx,sy;
		    public int ex,ey;
		    public int sx1,sy1;
		    public int ex1,ey1;
		  
		    public DrawPanel() {
		        points = new ArrayList<Point>();
		        setBackground(Color.WHITE);
		        
		        addMouseMotionListener(new MouseMotionAdapter() {
		        	 public void mouseDragged(MouseEvent e) {
		        		 //Zoom
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
		        			
		        			//Specktral Analysis
		        			if(!(ps1==null))
		                	{
		        				gg.drawLine(sx1, sy1, ex1, sy1);
		                		gg.drawLine(sx1, sy1, sx1, ey1);
		                		gg.drawLine(sx1, ey1, ex1, ey1);
		                		gg.drawLine(ex1, sy1, ex1, ey1);
		                		  pe1=e.getPoint();
		                          int xx=e.getPoint().x;
		                          int yy=e.getPoint().y;
		                           gg.drawLine(sx1, sy1, xx, sy1);
		                           gg.drawLine(sx1, sy1, sx1, yy);
		                           gg.drawLine(sx1, yy, xx, yy);
		                           gg.drawLine(xx, sy1, xx, yy);
		                          //
		                    	 ex1=xx;
		                    	 ey1=yy;
		                	}
		        			
		        			
		        			
		        	 }
		        	 public void mouseMoved(MouseEvent e) {
		        		 if(OUTPUT!=null )
		        		 {
		        			 int a=(int)(startx+((float)e.getX()/GLP1.getWidth())*(endx-startx));
		        			 int b=(int)(starty+((float)e.getY()/GLP1.getHeight())*(endy-starty));
		        			 L[0].setText("CHAN NO: "+(a+1));
		        			 L[1].setText("TIME: "+(b*sample_int));
		        			 String c=String.format("%.3f",OUTPUT[a][b]/((time/sample_int)*3707));
		        			 L[2].setText("AMPLITUDE: "+c);	        			
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
				              gg.setXORMode(Color.red);
				              gg.setStroke(new BasicStroke(3));
				              ps1 = e.getPoint();
				              sx1=ps1.x;
				              sy1=ps1.y;

				             	 ex1=sx1;
				              	 ey1=sy1;    	
				          	 }
			          	 
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					  if(zoom==1 && !(ps==null))
		                {
		                	 disp_resize(ps,pe);
		                     repaint();
		                     ps = null;
		                } 
					  
					  if(!(ps1==null))
		                {
		                     fft_points(ps1,pe1);
		                     repaint();
		                     ps1 = null;
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
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					  if(zoom==1 && !(ps==null))
		                {
		                	 disp_resize1(ps,pe);
		                     repaint();
		                     ps = null;
		                } 
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
		      });
		    }
		
		    @Override
		    public void paintComponent(Graphics g) {
		    	super.paintComponent(g);
	 	        Graphics2D g2 = (Graphics2D) g;
	 	       if(start==1)
			    {
			    	
			    	
			    	startyy=(int)(((1f-a4)/2f)*(pres_max-pres_min)+pres_min);
			    	endyy=(int)(((1f-a3)/2f)*(pres_max-pres_min)+pres_min);
			    	

			    	int total_sample=endyy-startyy;
			    	float total_time=total_sample;
			    	total_time=total_time/5f;
			    	float time_int=0;
			    	
			    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
			    	
			    	
			    	for(int i=0;i<times.length-1;i++)
			    	{
			    	 if(total_time<times[i] && total_time>=times[i+1])
			    		 time_int=times[i+1];
			    	}
			    	
			    	float time_now;	
		    		time_now=time_int;
		    		while(time_now<pres_max)
		    		{
		    			int tyer=(int)(time_now);
		    			int y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
		    			if(time_now> pres_min)
		    			{
		    				g.drawLine(0,y,super.getWidth(),y);
		    			}
		    			
		    			time_now=time_now+time_int;	
		    		}
			    	
			    	
		    		
		    		//VERTICAL GRID
		    		float r1=0f;
				    float r2=0f;
	   			 	r1=(a2-a1)*r1+a1;
	   			 	r1=((r1+1f)/2f)*(PRESSURE[0].size()-1);
	   			 	
	   			 	r2=1f;
				 	r2=(a2-a1)*r2+a1;
				 	r2=((r2+1f)/2f)*(PRESSURE[0].size()-1);
				 	
				 	for(int i=(int)r1;i<=r2;i++)
				 	{
				 		int x=(int)(((float)(i-r1)/(float)(r2-r1))*(super.getWidth()));
				 		
				 		if(x>0 && x<super.getWidth())
				 		{
				 		g.drawLine(x,0,x,super.getHeight());
				 		}
		    			
				 		int sss= (int)((r2-r1)/10);
		    			i=i+sss;
				 	}

			    		
			    
			    	
			    	}
			    }
	 	        
		      
		}
		
		
		
		public class DrawPanel2 extends GLJPanel {
		    private static final long serialVersionUID = 1L;
		    private ArrayList<Point> points;
		   
		    Graphics2D gg ;
		    public int sx,sy;
		    public int ex,ey;
		  
		    public DrawPanel2() {
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

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					  if(zoom==1 && !(ps==null))
		                {
		                	 disp_resize2(ps,pe);
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
	 	       if(start==1)
			    {
			    	
	 	    	   
	 	    	  startyy=(int)(((1f-a4)/2f)*(pres_max-pres_min)+pres_min);
			    	endyy=(int)(((1f-a3)/2f)*(pres_max-pres_min)+pres_min);
			    	

			    	int total_sample=endyy-startyy;
			    	float total_time=total_sample;
			    	total_time=total_time/5f;
			    	float time_int=0;
			    	
			    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
			    	
			    	
			    	for(int i=0;i<times.length-1;i++)
			    	{
			    	 if(total_time<times[i] && total_time>=times[i+1])
			    		 time_int=times[i+1];
			    	}
			    	
			    	float time_now;	
		    		time_now=time_int;
		    		while(time_now<pres_max)
		    		{
		    			int tyer=(int)(time_now);
		    			int y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
		    			if(time_now> pres_min)
		    			{
		    				g.drawLine(0,y,super.getWidth(),y);
		    			}
		    			
		    			time_now=time_now+time_int;	
		    		}
		    		
		    		//VERTICAL GRID
		    		float r1=0f;
				    float r2=0f;
	   			 	r1=(a22-a11)*r1+a11;
	   			 	r1=((r1+1f)/2f)*(DEPTH[0].size()-1);
	   			 	
	   			 	r2=1f;
				 	r2=(a22-a11)*r2+a11;
				 	r2=((r2+1f)/2f)*(DEPTH[0].size()-1);
				 	
				 	for(int i=(int)r1;i<=r2;i++)
				 	{
				 		int x=(int)(((float)(i-r1)/(float)(r2-r1))*(super.getWidth()));
				 		
				 		if(x>0 && x<super.getWidth())
				 		{
				 		g.drawLine(x,0,x,super.getHeight());
				 		}
		    			
				 		int sss= (int)((r2-r1)/10);
		    			i=i+sss;
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
			    if(start==1 && OUTPUT!=null)
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
		    			String ss=String.valueOf(time_now);
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
			    if(start==1 && OUTPUT!=null)
			    {
			 
			    String ss="SP NB : "+String.valueOf(sp_number);
			    g.drawString(ss,10,12);
			    ss="FFID : "+String.valueOf(file_number);
			    g.drawString(ss,10,26);
			    ss="CABLE NO : "+String.valueOf(streamer);
			    g.drawString(ss,10,40);
			    
			    
			    for(int j=0;j<number_of_chan_per_streamer;j++)
	    		{
	    			float ssyer=0.5f+(j);
	    			int x=(int)(((float)(ssyer-startx)/(float)(endx-startx))*(super.getWidth()-24))+12;
	    			ss=String.valueOf((j+1));
	    			int xx=(int)(((float)ss.length()/2f)*6f);
	    			g.drawString(ss,(int) (x-xx),super.getHeight()-25);
	    			g.drawLine(x,super.getHeight()-10,x,super.getHeight());
	    			int sss=(endx-startx)/20;
	    			j=j+sss;
	    			
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
			    if(start==1)
			    {
			    	
			    	startyy=(int)(((1f-a4)/2f)*(pres_max-pres_min)+pres_min);
			    	endyy=(int)(((1f-a3)/2f)*(pres_max-pres_min)+pres_min);
			    	

			    	int total_sample=endyy-startyy;
			    	float total_time=total_sample;
			    	total_time=total_time/5f;
			    	float time_int=0;
			    	
			    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
			    	
			    	
			    	for(int i=0;i<times.length-1;i++)
			    	{
			    	 if(total_time<times[i] && total_time>=times[i+1])
			    		 time_int=times[i+1];
			    	}
			    	
			    	float time_now;	
		    		time_now=time_int;
		    		while(time_now<pres_max)
		    		{
		    			int tyer=(int)(time_now);
		    			int y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
		    			if(time_now> pres_min)
		    			{
		    				g.drawLine(super.getWidth()-10,y,super.getWidth(),y);
			    			String ss=String.valueOf(time_now);
			    			g.drawString(ss,20,y+6);
		    			}
		    			
		    			time_now=time_now+time_int;	
		    		}
		    		
		    		
		    		
			    	

			    	
			    }
		} 
		}
		
		public float endyyy,startyyy;
		
		public class YLabel2 extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
				 System.gc();
			    super.paintComponent(g);
			    if(start==1)
			    {
			    	
			    	startyyy=(float)(((1f-a44)/2f)*(depth_max-depth_min)+depth_min);
			    	endyyy=(float)(((1f-a33)/2f)*(depth_max-depth_min)+depth_min);
			    	

			    	float total_sample=depth_max-depth_min;
			    	float total_time=total_sample;
			    	total_time=total_time/5f;
			    	float time_int=0;
			    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0.1f};
			    	
			    	for(int i=0;i<times.length-1;i++)
			    	{
			    	 if(total_time<times[i] && total_time>=times[i+1])
			    		 time_int=times[i+1];
			    	}
			    	
			    	float time_now;	
		    		time_now=time_int;
		    		
		    		while(time_now<depth_max)
		    		{
		    			float tyer=(time_now);
		    			int y=super.getHeight()-(int)(((float)(tyer-startyyy)/(float)(endyyy-startyyy))*super.getHeight());
		    			if(time_now> depth_min)
		    			{
		    				g.drawLine(super.getWidth()-10,y,super.getWidth(),y);
		    		//		String scale1=String.format("%.1f", scale);
			    			String ss=String.format("%.2f",time_now);
			    			g.drawString(ss,20,y+6);
		    			}
		    			
		    			time_now=time_now+time_int;	
		    		}
		    		
		    		
		    		
			    	

			    	
			    }
		} 
		}
		
		
		
}
