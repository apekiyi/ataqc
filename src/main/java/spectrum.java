import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;


public class spectrum {

	/**
	 * @param args
	 */
	
	
	//Importing parameters

	public JFrame F;
	public float sample_int;
	public float[][] OUTPUT;
	
	 JPanel flp,fbp; //Freq label panel//Freq button panels
	 JDialog freq;
	 JLabel fl1,fl2;
	 JPanel fp1,fp2; //Amplitude panels
	 JButton fb1,fb2;
	 GLJPanel FP;
	 float [][] FFTOUT;
	 float[] FFTAVG; //Average of values
	 float[] FFTDB;  //Average of Desibel
	 float[] AVGSP;  //Spline of Average
	 float[] DBSP;   //Spline of Desibel
	 JPanel fp=new JPanel(); //Status bar of Frequency
	 JLabel fl3,fl4;    //Status bar headers
	 int fftchoose=3;
	 
	  public void freqbuttons()
	   {
		   fb1=new JButton("N");
		   fb2=new JButton("dB");
		   fbp=new JPanel();
		   fbp.setLayout(null);
		   fbp.setBounds(300, 10,130,50);
		   fbp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		   fb1.setBounds(10,10,50,30);
		   fb2.setBounds(70, 10,50,30);
		   fb1.setToolTipText("Normalized");
		   fb2.setToolTipText("Decibel");
		   fb1.setMargin(new Insets(0,0,0,0));
		   fb2.setMargin(new Insets(0,0,0,0));
		   fb1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
				fftchoose=3;
				FP.display();
				fp2.repaint();
				}
			});
		   fb2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
				fftchoose=2;
				FP.display();
				fp2.repaint();
				}
			});
		   freq.add(fbp);
		   fbp.add(fb1);
		   fbp.add(fb2);
	   }
	   
	   public void freqstatusbar()
	   {
		   fp=new JPanel();
		   fp.setLayout(null);
		   fp.setBounds(FP.getX(),FP.getHeight()+FP.getY(),FP.getWidth(),20);
		   fl3=new JLabel("");
		   fl4=new JLabel("");
		   freq.add(fp); 
		   fl3.setBounds(10, 2, 250, 15);
		   fl4.setBounds(170, 2, 250, 15);
		   fp.add(fl3);
		   fp.add(fl4);
	   }
	   
	   public void freqlabels(int x1,int y1,int x2,int y2)
	   {
		   flp=new JPanel();
		   flp.setBounds(10, 10,280,50);
		   flp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		   fl1=new JLabel("Chan no : "+x1+"-"+x2);
		   fl2=new JLabel("Time    : "+y1*sample_int+"-"+y2*sample_int+" ms");
		   fl1.setBounds(10, 10, 250, 20);
		   fl2.setBounds(10, 30, 250, 30);
		   freq.add(flp);flp.add(fl1); flp.add(fl2);
	   }
	   
	   public void freqlabelpanels()
	   {
		   fp1=new fftx();
		   fp2=new ffty();
		   fp1.setBounds(60,60,920,50);
		   fp2.setBounds(10,110,50,450);
		   freq.add(fp1); freq.add(fp2);
	   }
	   
	   public void freqdisppanel()
	   {
		   FP=new FFTPANEL();
		   FP.addGLEventListener( new GLEventListener() {
	            
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

	            		 plot2(glautodrawable );

	       //     	whitescreen(glautodrawable );
	            	
	            }
	        });
		   FP.setBounds(60, 110, 920, 450);
		   freq.add(FP);
	   }
	   
	   public float[] amp2db(float[] INPUT)
	   {
		   float[] OUTPUT=new float[INPUT.length];
		   float max=max(INPUT);
		   for(int i=0;i<OUTPUT.length;i++)
		   {
			   OUTPUT[i]=20*(float)Math.log(INPUT[i]/max);
		   }
		   return OUTPUT;
	   }
	   
	   public float[] FFT_AVERAGE(float[][] INPUT)
	   {
		   int len=INPUT[0].length;
		   int a=INPUT.length;
		   float[] OUTPUT=new float[len];
		   float sum;
		   for(int i=0;i<len;i++)
		   {
			   sum=0;
			  for(int j=0;j<a;j++)
			  {
				  sum=sum+INPUT[j][i];
			  }
			  OUTPUT[i]=sum/a;
		   }
		   return OUTPUT;
	   }
	   
	   public float[][] FFT_DATA(int x1,int x2,int y1,int y2)
	   {
		   int len=y2-y1;
		   float[] A=new float[len];
		   float[][] B=new float[x2-x1][];
		   for(int j=x1;j<x2;j++)
		   {
		   for(int i=0;i<len;i++)
		   {
		    A[i]=OUTPUT[x1][y1+i];
		   }
		  B[j-x1]=amplitude(A);
		   }
		   return B;
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
		
		public static float getValue (float[] p, float x, int y) {
			float p0=p[y-2];
			float p1=p[y-1];
			float p2=p[y];
			float p3=p[y+1];
			
			float d1=0.5f*(p2-p0);
			float d2=0.5f*(p3-p1);
			float a0,a1,a2,a3;
			a0=p1;
			a1=d1;
			a2=(3*(p2-p1))-(2*d1)-d2;
			a3=d1+d2+2*(-p2+p1);
			float output=a0+a1*x+a2*x*x+a3*x*x*x;
			return output;
		}
		
		public float[] Spline(float[] INPUT)
		{
			float[] OUTPUT=INPUT;
			
			for(int i=2;i<OUTPUT.length-2;i++)
			{
			OUTPUT[i]=	getValue (INPUT,1f,i);
			}
			
			return OUTPUT;
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
			
			public void plot2( GLAutoDrawable glautodrawable ) {
			     GL2 gl;
				 gl=null;
				 gl = glautodrawable.getGL().getGL2();
				 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
				 //White Screen
				 gl.glColor3f(1f, 1f, 1f);
				 gl.glRectd(-1f,-1f,1f,1f);
				 gl.glColor3f(1f, 0f, 0f);
				 float height;
				 int y;
				 float max,min;
				 
				 if(fftchoose==2)
				 {
				 //Desibel 
				 y=FFTDB.length;
				 height=2f/(float)y;
				 max=max(FFTDB);
				 min=min(FFTDB);
				 max=max+10;
				 min=min-10;
				 gl.glColor3f(1f, 0f, 0f);
					 gl.glBegin(GL.GL_LINE_STRIP);
					 for(int j=0;j<y;j++)
					 { 
						 float deger=((FFTDB[j]-min)/(max-min))*2f-1f;
						 gl.glVertex2f(-1f+(height*j),deger);
					 }
					 gl.glEnd();
				//Grid Y
					 float diff=min-max;
					 diff=diff/5;
					 int dif=(int)diff/5;
					 dif=(dif+1)*10;
					 int cons=dif;

					 
					 while(dif>(min-max))
					 {
					 gl.glColor3f(0f, 0f, 0f);	 
					 float a=-1f+((min-dif)/(min-max))*2f;
					 gl.glBegin(GL.GL_LINE_STRIP);
					 gl.glVertex2f(-1f,a); 
					 gl.glVertex2f(1f,a);
					 gl.glEnd();
					 dif=dif+cons;
					 }
					 
					 
				 }
					 
					 
					 if(fftchoose==3)
					 {
					  //Amplitude
						 y=FFTAVG.length;
					
						 height=2f/(float)y;
						 max=max(FFTAVG);
						 min=min(FFTAVG);
						 max=max*1.1f;
						 min=0;
							 gl.glBegin(GL.GL_LINE_STRIP);
							 for(int j=0;j<y;j++)
							 { 
								 float deger=((FFTAVG[j]-min)/(max-min))*2f-1f;
								 gl.glVertex2f(-1f+(height*j),deger);
							 }
							 gl.glEnd();
							 
							 
							 gl.glColor3f(0f, 0f, 0f);
							 for(int i=1;i<6;i++)
							 {
								 gl.glBegin(GL.GL_LINE_STRIP);
								 float a=((float)(20*i)/110f)*2f-1f;
								 gl.glVertex2f(-1f,a);
								 gl.glVertex2f(1f,a);
								 gl.glEnd();
							 }
							 
					 }
			
				 
					 
					 //Grid X
					 	float Nyquist=(1f/(float)(2*sample_int))*1000;
					 	 gl.glColor3f(0f, 0f, 0f);
					 	for(int j=0;j<Nyquist;j++)
					 	{
					 	float x=((float)j/Nyquist)*2f-1f;
		    		
		    		
		    			if(j>0)
		    			{
		    				 gl.glBegin(GL.GL_LINE_STRIP);
		    				 gl.glVertex2f(x,-1f);
		    				 gl.glVertex2f(x,1f);
		    				 gl.glEnd();
		    			}
		    			int sss=(int)(Nyquist/10)-1;
		    			j=j+sss;
					 	}
		     }
			
			 public void freq_display(int x1,int x2,int y1, int y2)
			   {
				   freq=new JDialog(F,"Frequency Spectrum",true);
				   freq.setLayout(null);
				   freq.setResizable(false);
				   freq.setBounds(100, 100, 1000, 630);
				   //Labels
				   freqlabels(x1,y1,x2,y2);
				   //Panels
				   freqlabelpanels();
				   //Display Panel
				   freqdisppanel();
				   //Status Panel
				   freqstatusbar();
				   //Button
				   freqbuttons();   
				   //Read Data
				   FFTOUT=FFT_DATA(x1,x2,y1,y2);
				   //Average of output
				   FFTAVG=FFT_AVERAGE(FFTOUT);
				 //Spline Interpolation
			//	   AVGSP=Spline(FFTAVG);
				   //Desibel of the Data
				   FFTDB=amp2db(FFTAVG);
				   //Spline Desibel
			//	   DBSP=Spline(FFTDB);			   
				   //Display the data
				   FP.display();
				   //Display the dialogbox
				   freq.setVisible(true);
			   }
			 
				public class FFTPANEL extends GLJPanel {
				    private static final long serialVersionUID = 1L;
				
				   
				    Graphics2D gg ;
				    public int sx,sy;
				    public int ex,ey;
				  
				    public FFTPANEL() {
				     
				        setBackground(Color.WHITE);
				        
				        addMouseMotionListener(new MouseMotionAdapter() {
				        	 public void mouseDragged(MouseEvent e) {
				        			
				        	 }
				        	 public void mouseMoved(MouseEvent e) {
				        		int a=e.getX();
				        		float Nyquist=(1f/(2f*sample_int))*1000;
				        		float xpos=((float)a/(float)FP.getWidth())*Nyquist;
				        		fl3.setText("Freq: "+ new DecimalFormat("##.##").format(xpos)+" Hz");
				        		float fl=FFTAVG.length; 
				        		int hpos=(int)(((float)a/(float)FP.getWidth())*fl); 
				        		if(fftchoose==3)
				        		fl4.setText("Amplitude: "+ new DecimalFormat("##.##").format(FFTAVG[hpos]));
				        		if(fftchoose==2)
					          fl4.setText("Amplitude: "+ new DecimalFormat("##.##").format(FFTDB[hpos])+" (dB)");
				        	 }
				        	 
				        	 
				    	  });
				        
				      
				    }
				
				    @Override
				    public void paintComponent(Graphics g) {
				    	super.paintComponent(g);
				    	
					    
			 	        
				    }  
				}
				
				public final class fftx extends JPanel{
					 /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					int cc;
					 @Override
					    public void paintComponent(Graphics g) {
					    super.paintComponent(g);
					    System.gc();
					    float Nyquist=(1f/(2*sample_int))*1000f;
					    for(int j=0;j<Nyquist;j++)
			    		{
			    			int x=(int)(((float)((float)j/Nyquist)*(super.getWidth())));
			    			String ss=String.valueOf((j));
			    			int xx=(int)(((float)ss.length()/2f)*6f);
			    			if(j>0)
			    			{
			    			g.drawString(ss,(int) (x-xx),super.getHeight()-25);
			    			g.drawLine(x-1,super.getHeight()-10,x-1,super.getHeight());
			    			}
			    			int sss=(int)(Nyquist/10)-1;
			    			j=j+sss;
			    			
			    		}

					 }
					 }
				
				public class ffty extends JPanel{
					 /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					int cc;
					 @Override
					    public void paintComponent(Graphics g) {
					    super.paintComponent(g);
					    System.gc();
					    float min,max;
					    if(fftchoose==3)
					    {
					    	for(int i=1;i<6;i++)
					    	{
					    		float y=super.getHeight()-((float)(i*20)/110f)*super.getHeight();
					    		String A=String.valueOf(i*20);
					    		g.drawString(A,super.getWidth()-50,(int)y+6);
				    			g.drawLine(super.getWidth()-10,(int)y,super.getWidth(),(int)y);
					    	}
					    }
					    
					    if(fftchoose==2)
					    {
					    	 max=max(FFTDB);
							 min=min(FFTDB);
							 max=max+10;
							 min=min-10;
							//Grid Y
								 float diff=min-max;
								 diff=diff/5;
								 int dif=(int)diff/5;
								 dif=(dif+1)*10;
								 int cons=dif;

								 while(dif>(min-max))
								 {	 
								 float a=((min-dif)/(min-max))*super.getHeight();
								 String ss=String.valueOf((dif));
								 g.drawString(ss,(int)super.getWidth()-50,super.getHeight()-(int)a+6);
								 g.drawLine(super.getWidth()-10,super.getHeight()-(int)a-1,super.getWidth(),super.getHeight()-(int)a-1);
								 dif=dif+cons;
								 }
					    }

					 }
					 }
				

}
