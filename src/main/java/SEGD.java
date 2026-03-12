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
import java.awt.Stroke;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javax.swing.BoxLayout;
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
import javax.swing.table.TableColumnModel;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;


public class SEGD {
		JFrame F;
		JPanel P1,P2,P3,P4,P5,P6,P7,P9,P10,P11,P12,P13,P15,P8,P16,P17,P18,P19,P20,P21,P22,P23,P24,P25,P26,P27;
		GLJPanel GLP1,GLP3,GLP4,GLP2;
		JButton[] B1,B2,B3,B4,B5,B6,B7,B8;
		JButton SPB;
		JScrollBar sx=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy=new JScrollBar();
		JScrollBar sx1=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy1=new JScrollBar();
		JScrollBar sx2=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy2=new JScrollBar();
		JScrollBar sx3=new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar sy3=new JScrollBar();
		JTextField T1,ST;
		int arraypanel=0;
		JRadioButton[] RP,RD; //Radiobutton Pressure-RadipButton Display
		JLabel[] SL; //Status bar parameters
		
		//Gun Pressure
		int pres1;
		int pres2;
		int pres3;
		int pres4;
		int pres5;
		int pres6;
		
		
		float a1=-1f,a2=1f,a3=-1f,a4=1f;
		
		//Depth Controller
		int depth1;
		int depth2;
		int depth3;
		int depth4;
		int depth5;
		int depth6;
		
		float a11=-1f,a22=1f,a33=-1f,a44=1f;	
		float a111=-1f,a222=1f,a333=-1f,a444=1f;
		
		
		
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
		float scale=100;
		public static float time;
		static float max;
		static float min;
		public int aux_rec_len=1000;
		public int aux_len=500;
	
		
		//Parameters from User
		/*
		public String project_name="TPGM19";
		public String seq_no="351";
		public int fsp=1375;
		public int lsp=2275;
		public int number_of_traces=1920;
		public int number_of_streamers=4;
		public int number_of_array=2;
		public String linename="TPGM191324P1351";
		public String seqno="351";
		public String filepath;
		public String filename;
		public int number_of_string=6;
		static int pres_min=1900;
		static int pres_max=2100;
		static float depth_min=6f;
		static float depth_max=8f;
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
		public static int number_of_string;
		static int pres_min;
		static int pres_max;
		static float depth_min;
		static float depth_max;
		static float pres_maxx;
		static float pres_minn;
		
		static float depth_minn;
		static float depth_maxx;
		public String textfile;
		
		static //Data from SEGD
		float[][] INPUT;
		static float[][] OUTPUT;
		public static  ArrayList[] PRESSURE;
		public static  ArrayList[] DEPTH;
		public static  ArrayList SP;
		public static  ArrayList FFID;
		public  float[][] TRIGGER;
		public  float[][] BREAK;
		public ArrayList  SP_BR;
		public static ArrayList <String> ERROR;
		
//		public  ArrayList<Integer> PRESSURE1=new ArrayList<Integer>();
		public static int[] pres;
		public static int nonav=0;
		
		//Parameters of SEGD Data
		static float sample_int;
		static int number_of_sample;
		static int shot; //current shot to check read
		int number_of_chan_per_streamer;
		public static int sp_number;
		static int file_number;
	
		
		//Parameters for transfer
		int timesayac=0;
		
		
		 
		 JButton lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8;
		 static JFrame LF;
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
			JScrollPane JSP,JSP2;
			DefaultTableModel m;
			static DefaultTableModel m2;
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
			
			project_name=PARAMETERS[0];	
			linename=project_name+PARAMETERS[17];
			seq_no=PARAMETERS[18];
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
			
			pres_maxx=pres_max+50;
			pres_minn=pres_min-50;
			depth_minn=depth_min-0.25f;
			depth_maxx=depth_max+0.25f;
			//TRIGGER and BREAK DEF
			TRIGGER=new float[aux_len][10000];
			BREAK=new float[aux_len][10000];
			
			//ERROR
			ERROR= new ArrayList<>();
			
			trig_start_x=0;
			trig_end_x=aux_len;
			trig_start_y=0;
			trig_end_y=aux_rec_len;
			
			break_start_x=0;
			break_end_x=aux_len;
			break_start_y=0;
			break_end_y=aux_rec_len;
			
			
			
			
			for (int i = 1; i <= number_of_string; i++) {
	            try {
	            	 java.lang.reflect.Field f = this.getClass().getDeclaredField("depth" + i);
	                f.setInt(this, 1);
	                f = this.getClass().getDeclaredField("pres" + i);
	                f.setInt(this, 1);
	            } catch (NoSuchFieldException | IllegalAccessException ex) {
	                ex.printStackTrace();
	            }
	        }
	    
			
			
			}
			
		
public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					SEGD nt=new SEGD();
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
		
		public SEGD() throws IOException, InterruptedException {
			initialize();
		}
		
		public void initialize() throws IOException, InterruptedException 
		{
		readfile();
		model_defination();
		log();
		start_parameters();
		frame();	
		panels();
		buttons();
		scrollbars();
		radiobuttons();
		}
		
		 static DefaultTableModel model;
		
		 public static void model_defination()
		 {
			 model =
	             new javax.swing.table.DefaultTableModel(
	                 new Object[]{"Date", "Action", "N/A", "No Nav", "A/F", "D/T", "P/E", "D/E", "Status"}, 0) {
	                 @Override public boolean isCellEditable(int r, int c) { return false; }
	                 @Override public Class<?> getColumnClass(int c) {
	                     if (c >= 2 && c <= 7) return java.lang.Integer.class; // N/A..D/E: 1/0
	                     return java.lang.String.class;
	                 }
	             };

		 }
		
		
		 public static void log() {
			    javax.swing.SwingUtilities.invokeLater(new Runnable() {
			        public void run() {
			            LF = new javax.swing.JFrame("Log");
			            LF.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
			            LF.setSize(1300, 700);
			            LF.setLocationRelativeTo(null);
			            LF.setResizable(false);

			            final java.awt.Color CELL_BG = new java.awt.Color(240, 240, 240);
			            final java.awt.Color GRID    = new java.awt.Color(120, 120, 120);

			            // === TABLE ===
			            final javax.swing.JTable table = new javax.swing.JTable(model) {
			                @Override
			                public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
			                    java.awt.Component c = super.prepareRenderer(renderer, row, column);
			                    int statusCol = getColumnCount() - 1;
			                    if (!isRowSelected(row) && column != statusCol) {
			                        c.setBackground(CELL_BG); // Status rengi sabit kalsın
			                    }
			                    return c;
			                }
			            };
			            table.setFillsViewportHeight(true);
			            table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
			            table.setRowHeight(32);
			            table.setShowGrid(true);
			            table.setShowVerticalLines(true);
			            table.setShowHorizontalLines(true);
			            table.setIntercellSpacing(new java.awt.Dimension(1, 1));
			            table.setGridColor(GRID);
			            table.setBackground(CELL_BG);

			            // === SORTER (filtre/sıralama) ===
			            final javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter =
			                new javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel>(model);
			            table.setRowSorter(sorter);

			            // === HEADER renkleri ===
			            final javax.swing.table.JTableHeader header = table.getTableHeader();
			            header.setReorderingAllowed(false);
			            header.setPreferredSize(new java.awt.Dimension(header.getWidth(), 40));
			            header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
			                @Override
			                public java.awt.Component getTableCellRendererComponent(
			                        javax.swing.JTable tbl, Object value, boolean isSelected,
			                        boolean hasFocus, int row, int column) {
			                    javax.swing.JLabel lbl = (javax.swing.JLabel) super.getTableCellRendererComponent(
			                            tbl, value, isSelected, hasFocus, row, column);
			                    lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			                    lbl.setFont(lbl.getFont().deriveFont(java.awt.Font.BOLD, 14f));
			                    lbl.setOpaque(true);
			                    lbl.setForeground(java.awt.Color.BLACK);

			                    java.awt.Color bg = new java.awt.Color(200, 200, 200);
			                    if (column == 2)      bg = java.awt.Color.RED;      // N/A
			                    else if (column == 3) bg = java.awt.Color.YELLOW;   // No Nav
			                    else if (column == 4) bg = java.awt.Color.ORANGE;   // A/F
			                    else if (column == 5) bg = java.awt.Color.PINK;     // D/T
			                    else if (column == 6) bg = java.awt.Color.CYAN;     // P/E
			                    else if (column == 7) bg = java.awt.Color.MAGENTA;  // D/E
			                    lbl.setBackground(bg);
			                    return lbl;
			                }
			            });

			            // === ✓ / ✗ renderer (1 -> ✓ yeşil, 0 -> ✗ kırmızı) ===
			            final javax.swing.table.TableCellRenderer checkX = new javax.swing.table.DefaultTableCellRenderer() {
			                @Override
			                public java.awt.Component getTableCellRendererComponent(
			                        javax.swing.JTable t, Object v, boolean s, boolean fcs, int r, int c) {
			                    javax.swing.JLabel lbl = (javax.swing.JLabel) super.getTableCellRendererComponent(t, v, s, fcs, r, c);
			                    lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			                    lbl.setOpaque(true);
			                    if (!s) lbl.setBackground(CELL_BG);

			                    java.lang.Integer val = null;
			                    if (v instanceof java.lang.Number) val = ((java.lang.Number) v).intValue();
			                    else if (v != null) try { val = java.lang.Integer.valueOf(v.toString().trim()); } catch (Exception ignore) {}

			                    if      (val != null && val.intValue() == 1) { lbl.setText("\u2713"); lbl.setForeground(new java.awt.Color(0,150,0)); }
			                    else if (val != null && val.intValue() == 0) { lbl.setText("\u2717"); lbl.setForeground(new java.awt.Color(200,0,0)); }
			                    else lbl.setText("");
			                    return lbl;
			                }
			            };
			            int lastDataColInit = table.getColumnCount() - 2; // Status'tan önceki
			            for (int c = 2; c <= lastDataColInit; c++) {
			                table.getColumnModel().getColumn(c).setCellRenderer(checkX);
			            }

			         // === STATUS renderer (hepsi 1 -> OK/yeşil, biri 0 -> ERROR/kırmızı) ===
			            final javax.swing.table.TableCellRenderer statusRenderer = new javax.swing.table.DefaultTableCellRenderer() {
			                @Override
			                public java.awt.Component getTableCellRendererComponent(
			                        javax.swing.JTable t, Object v, boolean s, boolean fcs, int r, int c) {

			                    javax.swing.JLabel lbl = (javax.swing.JLabel) super.getTableCellRendererComponent(t, v, s, fcs, r, c);
			                    lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			                    lbl.setFont(lbl.getFont().deriveFont(java.awt.Font.BOLD, 14f));
			                    lbl.setOpaque(true);

			                    // *** KRİTİK DÜZELTME: view index -> model index ***
			                    int modelRow = t.convertRowIndexToModel(r);

			                    javax.swing.table.TableModel m = t.getModel();
			                    int lastDataCol = m.getColumnCount() - 2; // 2..lastDataCol (N/A..D/E)
			                    boolean allOnes = true, anyZero = false;

			                    for (int i = 2; i <= lastDataCol; i++) {
			                        Object val = m.getValueAt(modelRow, i); // <-- modelRow ile oku
			                        int iv = -1;
			                        if (val instanceof java.lang.Number) iv = ((java.lang.Number) val).intValue();
			                        else if (val != null) try { iv = java.lang.Integer.parseInt(val.toString().trim()); } catch (Exception ignore) {}

			                        if (iv != 1) allOnes = false;
			                        if (iv == 0) anyZero = true;
			                    }

			                    if (allOnes && !anyZero) {
			                        lbl.setText("OK");
			                        lbl.setBackground(new java.awt.Color(0,170,0));
			                        lbl.setForeground(java.awt.Color.WHITE);
			                    } else if (anyZero) {
			                        lbl.setText("ERROR");
			                        lbl.setBackground(new java.awt.Color(200,0,0));
			                        lbl.setForeground(java.awt.Color.WHITE);
			                    } else {
			                        if (!s) lbl.setBackground(new java.awt.Color(240,240,240));
			                        lbl.setForeground(new java.awt.Color(60,60,60));
			                        lbl.setText("");
			                    }
			                    return lbl;
			                }
			            };
			            table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(statusRenderer);


			            // === ÜST PANEL: 3 BUTON (ALL / OK / Error) ===
			            final javax.swing.JPanel topBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 8));
			            topBar.setBackground(new java.awt.Color(230,230,230));

			            final javax.swing.JButton btnAll   = new javax.swing.JButton("ALL");
			            final javax.swing.JButton btnOK    = new javax.swing.JButton("OK");
			            final javax.swing.JButton btnError = new javax.swing.JButton("Error");

			            java.awt.Font bFont = new java.awt.Font("Arial", java.awt.Font.BOLD, 14);
			            btnAll.setFont(bFont);
			            btnOK.setFont(bFont);
			            btnError.setFont(bFont);

			            btnAll.setFocusPainted(false);
			            btnOK.setFocusPainted(false);
			            btnError.setFocusPainted(false);

			            btnOK.setBackground(new java.awt.Color(0,170,0));
			            btnOK.setForeground(java.awt.Color.WHITE);
			            btnOK.setOpaque(true);

			            btnError.setBackground(new java.awt.Color(200,0,0));
			            btnError.setForeground(java.awt.Color.WHITE);
			            btnError.setOpaque(true);

			         // === FİLTRE BUTONLARI ===

			         // ALL -> tüm satırlar
			         btnAll.addActionListener(new java.awt.event.ActionListener() {
			             public void actionPerformed(java.awt.event.ActionEvent e) {
			                 sorter.setRowFilter(null);
			             }
			         });

			         // OK -> tüm veri sütunları 1 (2..lastDataCol) ve hiç 0 yok
			         btnOK.addActionListener(new java.awt.event.ActionListener() {
			             public void actionPerformed(java.awt.event.ActionEvent e) {
			                 sorter.setRowFilter(new javax.swing.RowFilter<javax.swing.table.DefaultTableModel, Integer>() {
			                     @Override
			                     public boolean include(Entry<? extends javax.swing.table.DefaultTableModel, ? extends Integer> entry) {
			                         javax.swing.table.DefaultTableModel m = entry.getModel();
			                         int r = entry.getIdentifier();
			                         int lastDataCol = m.getColumnCount() - 2; // 2..lastDataCol veri sütunları
			                         if (lastDataCol < 2) return false;

			                         boolean allOnes = true, anyZero = false;
			                         for (int c = 2; c <= lastDataCol; c++) {
			                             Object v = m.getValueAt(r, c);
			                             int iv = -1;
			                             if (v instanceof Number) iv = ((Number) v).intValue();
			                             else if (v != null) try { iv = Integer.parseInt(v.toString().trim()); } catch (Exception ignore) {}

			                             if (iv != 1) allOnes = false;
			                             if (iv == 0) anyZero = true;
			                         }
			                         return allOnes && !anyZero; // tam OK
			                     }
			                 });
			             }
			         });

			         // ERROR -> veri sütunlarından en az biri 0 (2..lastDataCol)
			         btnError.addActionListener(new java.awt.event.ActionListener() {
			             public void actionPerformed(java.awt.event.ActionEvent e) {
			                 sorter.setRowFilter(new javax.swing.RowFilter<javax.swing.table.DefaultTableModel, Integer>() {
			                     @Override
			                     public boolean include(Entry<? extends javax.swing.table.DefaultTableModel, ? extends Integer> entry) {
			                         javax.swing.table.DefaultTableModel m = entry.getModel();
			                         int r = entry.getIdentifier();
			                         int lastDataCol = m.getColumnCount() - 2; // 2..lastDataCol veri sütunları
			                         if (lastDataCol < 2) return false;

			                         for (int c = 2; c <= lastDataCol; c++) {
			                             Object v = m.getValueAt(r, c);
			                             int iv = -1;
			                             if (v instanceof Number) iv = ((Number) v).intValue();
			                             else if (v != null) try { iv = Integer.parseInt(v.toString().trim()); } catch (Exception ignore) {}

			                             if (iv == 0) return true; // herhangi biri 0 ise ERROR
			                         }
			                         return false;
			                     }
			                 });
			             }
			         });



			            topBar.add(btnAll);
			            topBar.add(btnOK);
			            topBar.add(btnError);

			         // === LAYOUT ===
			            final javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(table);
			            scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			            scrollPane.setViewportBorder(null);

			            // Yeni ana panel: kenarlarda 10px boşluk
			            final javax.swing.JPanel contentPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
			            contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
			            scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180,180,180), 1));
			            contentPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

			            // Frame’e ekleme
			            LF.setLayout(new java.awt.BorderLayout());
			            LF.add(topBar, java.awt.BorderLayout.NORTH);
			            LF.add(contentPanel, java.awt.BorderLayout.CENTER);
			            

			            // === SÜTUN GENİŞLİKLERİ: viewport'a tam otur ===
			            final javax.swing.table.TableColumnModel cm = table.getColumnModel();
			            final int wDatePct = 12, wActionPct = 24, wStatusPct = 20;
			            java.awt.event.ComponentListener cl = new java.awt.event.ComponentAdapter() {
			                private void applyWidths() {
			                    int vw = scrollPane.getViewport().getWidth();
			                    if (vw <= 0 || table.getColumnCount() < 9) return;

			                    int datePx   = vw * wDatePct   / 100;
			                    int actionPx = vw * wActionPct / 100;
			                    int statusPx = vw * wStatusPct / 100;

			                    int lastDataCol = table.getColumnCount() - 2; // 2..lastDataCol (N/A..D/E)
			                    int midCount = (lastDataCol - 2 + 1);
			                    int remaining = vw - (datePx + actionPx + statusPx);
			                    if (remaining < 0) remaining = 0;
			                    int midPx = (midCount > 0) ? (remaining / midCount) : 0;
			                    int remainder = remaining - (midPx * midCount);

			                    cm.getColumn(0).setPreferredWidth(datePx);
			                    cm.getColumn(1).setPreferredWidth(actionPx);
			                    for (int i = 2; i <= lastDataCol; i++) {
			                        int add = ((i - 2) < remainder) ? 1 : 0;
			                        cm.getColumn(i).setPreferredWidth(midPx + add);
			                    }
			                    cm.getColumn(table.getColumnCount() - 1).setPreferredWidth(statusPx);
			                }
			                @Override public void componentResized(java.awt.event.ComponentEvent e) { applyWidths(); }
			                @Override public void componentShown  (java.awt.event.ComponentEvent e) { applyWidths(); }
			            };
			            scrollPane.addComponentListener(cl);
			            table.addComponentListener(cl);
			            javax.swing.SwingUtilities.invokeLater(new Runnable() {
			                public void run() { cl.componentResized(new java.awt.event.ComponentEvent(scrollPane, java.awt.event.ComponentEvent.COMPONENT_RESIZED)); }
			            });

			            // === ANINDA GÜNCELLEME: modele kulak ver, görünümü tazele + auto-scroll ===
			            model.addTableModelListener(new javax.swing.event.TableModelListener() {
			                @Override
			                public void tableChanged(javax.swing.event.TableModelEvent e) {
			                    javax.swing.SwingUtilities.invokeLater(() -> {
			                        try {
			                            if (table.getRowSorter() != null) {
			                                // Satır/sıra değişimleri için görünümü tazele
			                                table.getRowSorter().allRowsChanged();
			                            }
			                            table.revalidate();
			                            table.repaint();

			                            // Otomatik en alta kaydır (eklenen/güncellenen son satırı göster)
			                            int last = table.getRowCount() - 1;
			                            if (last >= 0) {
			                                int viewRow = table.convertRowIndexToView(last);
			                                if (viewRow >= 0) {
			                                    java.awt.Rectangle r = table.getCellRect(viewRow, 0, true);
			                                    table.scrollRectToVisible(r);
			                                }
			                            }
			                        } catch (Exception ignore) {}
			                    });
			                }
			            });

			            // LF.setVisible(true);  // görünürlüğü burada ya da dışarıdan kontrol edebilirsin
			        }
			    });
			}

		 
	
		
		public void radiobuttons()
		{
			RP=new JRadioButton[number_of_string];
			RD=new JRadioButton[number_of_string];
			for(int i=0;i<RP.length;i++)
			{
				String s="String: "+(i+1);
				RP[i]=new JRadioButton(s);
				RP[i].setBounds(10+(i*1)+100*i, 5, 100, 30);
				RP[i].setSelected(true);
				P21.add(RP[i]);
				RD[i]=new JRadioButton(s);
				RD[i].setBounds(10+(i*1)+100*i, 5, 100, 30);
				RD[i].setSelected(true);
				P22.add(RD[i]);
				if(i==0)
				{
					RP[0].setForeground(Color.red);
					RD[0].setForeground(Color.red);
					RP[0].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if(pres1==1)
							{
							RP[0].setSelected(false);
							pres1=2;
							GLP3.display();
							}
							else if(pres1==2)
							{
								RP[0].setSelected(true);
								pres1=1;
								GLP3.display();
							}
						
						}
						
					});
					
					RD[0].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if(depth1==1)
							{
							RP[0].setSelected(false);
							depth1=2;
							GLP4.display();
							}
							else if(depth1==2)
							{
								RD[0].setSelected(true);
								depth1=1;
								GLP4.display();
							}
						
						}
						
					});
				}
				if(i==1)
				{
					RP[1].setForeground(Color.blue);
					RD[1].setForeground(Color.blue);
					RP[1].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(pres2==1)
							{
							RP[1].setSelected(false);
							pres2=2;
							GLP3.display();
							}
							else if(pres2==2)
							{
								RP[1].setSelected(true);
								pres2=1;
								GLP3.display();
							}
						
						}
					});
					
					RD[1].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if(depth2==1)
							{
							RP[1].setSelected(false);
							depth2=2;
							GLP4.display();
							}
							else if(depth2==2)
							{
								RD[1].setSelected(true);
								depth2=1;
								GLP4.display();
							}
						
						}
						
					});
					
					
				}
				if(i==2)
				{
					RP[2].setForeground(Color.green);
					RD[2].setForeground(Color.green);
					RP[2].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(pres3==1)
							{
							RP[2].setSelected(false);
							pres3=2;
							GLP3.display();
							}
							else if(pres3==2)
							{
								RP[2].setSelected(true);
								pres3=1;
								GLP3.display();
							}
						
						}
					});
					
					RD[2].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if(depth3==1)
							{
							RP[2].setSelected(false);
							depth3=2;
							GLP4.display();
							}
							else if(depth3==2)
							{
								RD[2].setSelected(true);
								depth3=1;
								GLP4.display();
							}
						
						}
						
					});
					
					
				}
				if(i==3)
				{
					RP[3].setForeground(Color.yellow);
					RD[3].setForeground(Color.yellow);
					RP[3].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(pres4==1)
							{
							RP[3].setSelected(false);
							pres4=2;
							GLP3.display();
							}
							else if(pres4==2)
							{
								RP[3].setSelected(true);
								pres4=1;
								GLP3.display();
							}
						
						}
					});
					
					RD[3].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if(depth4==1)
							{
							RP[3].setSelected(false);
							depth4=2;
							GLP4.display();
							}
							else if(depth4==2)
							{
								RD[3].setSelected(true);
								depth4=1;
								GLP4.display();
							}
						
						}
						
					});
				}
				if(i==4)
				{
					RP[4].setForeground(Color.cyan);
					RD[4].setForeground(Color.cyan);
					RP[4].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(pres5==1)
							{
							RP[4].setSelected(false);
							pres5=2;
							GLP3.display();
							}
							else if(pres4==2)
							{
								RP[4].setSelected(true);
								pres5=1;
								GLP3.display();
							}
						
						}
					});
					
					RD[4].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if(depth5==1)
							{
							RP[4].setSelected(false);
							depth5=2;
							GLP4.display();
							}
							else if(depth2==2)
							{
								RD[1].setSelected(true);
								depth5=1;
								GLP4.display();
							}
						
						}
						
					});
					
				}
				if(i==5)
				{
					RP[5].setForeground(Color.pink);
					RD[5].setForeground(Color.pink);
					RP[5].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(pres6==1)
							{
							RP[5].setSelected(false);
							pres6=2;
							GLP3.display();
							}
							else if(pres5==2)
							{
								RP[5].setSelected(true);
								pres6=1;
								GLP3.display();
							}
						
						}
					});
					
					RD[5].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if(depth6==1)
							{
							RP[5].setSelected(false);
							depth6=2;
							GLP4.display();
							}
							else if(depth6==2)
							{
								RD[5].setSelected(true);
								depth6=1;
								GLP4.display();
							}
						
						}
						
					});
					
				}
				
			}
			
			
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
			number_of_chan_per_streamer=number_of_traces/number_of_streamers;
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
			SP_BR=new ArrayList<Integer>();
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
			P1.add(P4);P1.add(P5);P1.add(P6);P1.add(P15);
			//Time Panel
			P7=new YLabel();
		//	P7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
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
			
			
			
			
			
			
			JL=new JTable();
			JL.setModel(m);
			JL.setRowHeight(16);
			m.addColumn("Objects");m.addColumn("Value");
			JSP=new JScrollPane(JL);
			P17.add(JSP);
			
		
			m2 = new DefaultTableModel();
			m2.addColumn("Error");

			JTable errorTable = new JTable(m2);
			errorTable.setRowHeight(16);
			JSP2 = new JScrollPane(errorTable);
			P17.add(JSP2);
			
			/*
			// Altına "Error" başlıklı ikinci tablo
			m2 = new DefaultTableModel();
			m2.addColumn("Error");

			JTable errorTable = new JTable(m2);
			errorTable.setRowHeight(16);

			JScrollPane errorScroll = new JScrollPane(errorTable);
			P17.add(errorScroll);

			// Panel düzeni (örneğin BoxLayout ile dikey hizalama)
			P17.setLayout(new BoxLayout(P17, BoxLayout.Y_AXIS));
			*/
			
			
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
			
		//	P8.setVisible(false);
			//Shot Point 
			P10=new XLabel1();
		//	P10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			//Gun Pressure
			P11=new YLabel1();
		//	P11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			//String Selection
			P21=new JPanel();
			P21.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P21.setLayout(null);
			//NT Flip Panel
			GLP3=new DrawPanel1();
			GLP3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			//Add Panel
			P16.add(P10);P16.add(P11);P16.add(GLP3);P16.add(P21);
			//Flop Time
			P12=new XLabel2();
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
			P23=new JPanel();
			P23.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P23.setLayout(null);
			P1.add(P23);
			
			P24=new JPanel();
			P24.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P24.setLayout(null);
			P1.add(P24);
			
			//NT Flpp Panel
			GLP4=new DrawPanel2();
			GLP4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			P16.add(GLP4);	P16.add(P12);	P16.add(P13);	P16.add(P22);	
			
			//GL PANEL DEFINATION
			
		
			P25=new YLabel3();
			P26=new YLabel3();
			P27=new XLabel3();
			
		;P17.add(P25);P17.add(P26);P17.add(P27);
			
			
			
			 
			 GLP3.addGLEventListener( new GLEventListener() {
		            
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
		            	if(start==1 && OUTPUT!=null && COLS.size()>0)
		            	{
		            
		            		 plot2(glautodrawable );
		            	}
		            	else
		            	whitescreen(glautodrawable );
		            }
		        });
			 
			 GLP4.addGLEventListener( new GLEventListener() {
		            
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
		            	if(start==1 && OUTPUT!=null && COLS.size()>0)
		            	{
		            
		            		 plot3(glautodrawable );
		            	}
		            	else
		            	whitescreen(glautodrawable );
		            }
		        });
			 
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
		
		
		
		
		public void plot2( GLAutoDrawable glautodrawable ) {
			 GL2 gl;
			 gl=null;
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
		     blackscreen(glautodrawable );
			 int x=PRESSURE[0].size();
			 float width=2f/(float)(x-1);
			 int y=endyy-startyy;
			 a3=-1;
			 a4=1;
			 
			 if(pres1==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(1, 0, 0);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 int A=(Integer) PRESSURE[0].get(xx);
				 float y1=((float)(A-pres_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(pres2==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(0, 0, 1);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 int A=(Integer) PRESSURE[1].get(xx);
				 float y1=((float)(A-pres_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(pres3==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(0, 1, 0);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 int A=(Integer) PRESSURE[2].get(xx);
				 float y1=((float)(A-pres_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(pres4==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(1, 1, 0);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 int A=(Integer) PRESSURE[3].get(xx);
				 float y1=((float)(A-pres_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(pres5==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(0, 1, 1);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 int A=(Integer) PRESSURE[4].get(xx);
				 float y1=((float)(A-pres_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(pres6==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(1, 0, 1);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 int A=(Integer) PRESSURE[5].get(xx);
				 float y1=((float)(A-pres_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 //Zoom out zoom in
			    gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		  		gl.glLoadIdentity();
		  		gl.glOrthof(a1,a2,a3,a4, -1f, 1f);
		  		gl.glFlush();
			 
			 
			 
			 P10.repaint();
			 P11.repaint();
			 
		}
		
		public void plot3( GLAutoDrawable glautodrawable ) {
			 GL2 gl;
			 gl=null;
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
		     blackscreen(glautodrawable );
			 int x=DEPTH[0].size();
			 float width=2f/(float)(x-1);
			 float y=depth_maxx-depth_minn;
			 a33=-1;
			 a44=1;
			 

			 
			 if(depth1==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(1, 0, 0);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 float A= (Float)DEPTH[0].get(xx);
				 float y1=((float)(A-depth_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(depth2==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(0, 0, 1);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 float A=(Float) DEPTH[1].get(xx);
				 float y1=((float)(A-depth_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(depth3==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(0, 1, 0);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 float A=(Float) DEPTH[2].get(xx);
				 float y1=((float)(A-depth_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(depth4==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(1, 1, 0);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 float A=(Float) DEPTH[3].get(xx);
				 float y1=((float)(A-depth_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(depth5==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(0, 1, 1);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 float A=(Float) DEPTH[4].get(xx);
				 float y1=((float)(A-depth_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 if(depth6==1)
			 {
			 gl.glBegin(GL.GL_LINE_STRIP);
			 gl.glColor3f(1, 0, 1);
			 int xx=0;
			 while (xx<x)
			 {
				 float x1=-1f+xx*width;
				 float A=(Float) DEPTH[5].get(xx);
				 float y1=((float)(A-depth_minn)/(float)y)*2-1f;
				 xx++;
				 gl.glVertex2f(x1,y1);
			 }
			 gl.glEnd();
			 }
			 
			 //Zoom out zoom in
			    gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		  		gl.glLoadIdentity();
		  		gl.glOrthof(a11,a22,a33,a44, -1f, 1f);
		  		gl.glFlush();
			 
			 
			 
			 P13.repaint();
			 P12.repaint();
			 
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
		
			P7.repaint();	
			P9.repaint();
	//		TP.repaint();
			
	     }
		
		//DRAW TRIGGER
		public int trig_start_x,trig_start_y;
		public int trig_end_x,trig_end_y;
		
		
		public void plot4( GLAutoDrawable glautodrawable ) {
		     GL2 gl;
			 gl=null;
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
			 //min=min();
			 max=maxmax(TRIGGER);
			 min=-max; 
			 int x=trig_end_x-trig_start_x;
			 int y=trig_end_y-trig_start_y;
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
						// if(j+trig_start_y<D.length && i+trig_start_x<D[0].length)
						 if(TRIGGER!=null)
						 {
						 renk=findcols(TRIGGER[i+trig_start_x][j+trig_start_y]*(scale/50));
						 gl.glColor3f(renk[0], renk[1], renk[2]);
						 if(TRIGGER[i+trig_start_x][j+trig_start_y]==9999)
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
						 float deger=((TRIGGER[i+trig_start_x][j+trig_start_y]*(scale/50)-min)/(max-min)-0.5f)*(width/2);
						 if(TRIGGER[i+trig_start_x][j+trig_start_y]!=9999)
						 {
							 if(deger<0 && TRIGGER[i+trig_start_x][j+trig_start_y+1]*(scale/50)<0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
							 }
							 else if(deger<0 && TRIGGER[i+trig_start_x][j+trig_start_y+1]*(scale/50)>0)
							 {
								 float a=-(TRIGGER[i+trig_start_x][j+trig_start_y])/(TRIGGER[i+trig_start_x][j+trig_start_y+1]-TRIGGER[i+trig_start_x][j+trig_start_y]);
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
							 }
							 else if(deger>0 && TRIGGER[i+trig_start_x][j+trig_start_y-1]*(scale/50)>0)
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
							 if(TRIGGER!=null)
							 {
							 float deger=((TRIGGER[i+trig_start_x][j+trig_start_y]*(scale/50)-min)/(max-min)-0.5f)*(width/2);
							 if(TRIGGER[i+trig_start_x][j+trig_start_y]!=9999)
							 {
								 if(deger>0 && TRIGGER[i+trig_start_x][j+trig_start_y+1]*(scale/50)>0)
								 {
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
								 }
								 else if(deger>0 && TRIGGER[i+trig_start_x][j+trig_start_y+1]*(scale/50)<0)
								 {
									 float a=-(TRIGGER[i+trig_start_x][j+trig_start_y])/(TRIGGER[i+trig_start_x][j+trig_start_y+1]-TRIGGER[i+trig_start_x][j+trig_start_y]);
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
								 }
								 else if(deger<0 && TRIGGER[i+trig_start_x][j+trig_start_y-1]*(scale/50)<0)
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
						// if(j+trig_start_y<D.length && i+trig_start_x<D[0].length)
						 if(TRIGGER!=null)
						 {
						 float deger=((TRIGGER[i+trig_start_x][j+trig_start_y]*(scale/50)-min)/(max-min)-0.5f)*(width/2);
						 if(TRIGGER[i+trig_start_x][j+trig_start_y]!=9999)
						 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j));
						 else
						 gl.glEnd();
						 gl.glBegin(GL.GL_LINE_STRIP);
						 }
					 }
					 gl.glEnd();
				 }
				 
			 }
		
			P25.repaint();	
			P26.repaint();
			P27.repaint();
			
	     }
		public int break_start_x,break_start_y;
		public int break_end_x,break_end_y;
		
		
		public void plot5( GLAutoDrawable glautodrawable ) {
		     GL2 gl;
			 gl=null;
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
			 //min=min();
			 max=maxmax(BREAK);
			 min=-max; 
			 int x=break_end_x-break_start_x;
			 int y=break_end_y-break_start_y;
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
						// if(j+break_start_y<D.length && i+break_start_x<D[0].length)
						 if(BREAK!=null)
						 {
						 renk=findcols(BREAK[i+break_start_x][j+break_start_y]*(scale/50));
						 gl.glColor3f(renk[0], renk[1], renk[2]);
						 if(BREAK[i+break_start_x][j+break_start_y]==9999)
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
						 float deger=((BREAK[i+break_start_x][j+break_start_y]*(scale/50)-min)/(max-min)-0.5f)*(width/2);
						 if(BREAK[i+break_start_x][j+break_start_y]!=9999)
						 {
							 if(deger<0 && BREAK[i+break_start_x][j+break_start_y+1]*(scale/50)<0)
							 {
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
							 }
							 else if(deger<0 && BREAK[i+break_start_x][j+break_start_y+1]*(scale/50)>0)
							 {
								 float a=-(BREAK[i+break_start_x][j+break_start_y])/(BREAK[i+break_start_x][j+break_start_y+1]-BREAK[i+break_start_x][j+break_start_y]);
								 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
								 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
							 }
							 else if(deger>0 && BREAK[i+break_start_x][j+break_start_y-1]*(scale/50)>0)
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
							 if(BREAK!=null)
							 {
							 float deger=((BREAK[i+break_start_x][j+break_start_y]*(scale/50)-min)/(max-min)-0.5f)*(width/2);
							 if(BREAK[i+break_start_x][j+break_start_y]!=9999)
							 {
								 if(deger>0 && BREAK[i+break_start_x][j+break_start_y+1]*(scale/50)>0)
								 {
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+1)); 
								 }
								 else if(deger>0 && BREAK[i+break_start_x][j+break_start_y+1]*(scale/50)<0)
								 {
									 float a=-(BREAK[i+break_start_x][j+break_start_y])/(BREAK[i+break_start_x][j+break_start_y+1]-BREAK[i+break_start_x][j+break_start_y]);
									 gl.glVertex2f(-1f+(width)*(i+0.5f)+deger,1f-height*(j)); 
									 gl.glVertex2f(-1f+(width)*(i+0.5f),1f-height*(j+a));  
								 }
								 else if(deger<0 && BREAK[i+break_start_x][j+break_start_y-1]*(scale/50)<0)
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
						// if(j+break_start_y<D.length && i+break_start_x<D[0].length)
						 if(BREAK!=null)
						 {
						 float deger=((BREAK[i+break_start_x][j+break_start_y]*(scale/50)-min)/(max-min)-0.5f)*(width/2);
						 if(BREAK[i+break_start_x][j+break_start_y]!=9999)
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
			 y2=GLP3.getHeight()-y2;
			 y1=GLP3.getHeight()-y1;
			 a5=((float)x1/(float)GLP3.getWidth())*(a2-a1)+a1;
			 a6=((float)x2/(float)GLP3.getWidth())*(a2-a1)+a1;
			 a7=((float)y1/(float)GLP3.getHeight())*(a4-a3)+a3;
			 a8=((float)y2/(float)GLP3.getHeight())*(a4-a3)+a3;	
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
			 y2=GLP3.getHeight()-y2;
			 y1=GLP3.getHeight()-y1;
			 a5=((float)x1/(float)GLP3.getWidth())*(a22-a11)+a11;
			 a6=((float)x2/(float)GLP3.getWidth())*(a22-a11)+a11;
			 a7=((float)y1/(float)GLP3.getHeight())*(a44-a33)+a33;
			 a8=((float)y2/(float)GLP3.getHeight())*(a44-a33)+a33;	
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
		 
		 public void disp_resize3(Point ps,Point pe)
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
			 
			
			 if(x1<0)
				x1=0;
			  if(y1<0)
				 y1=0;
			 
			 
			 int trig_start_xx=trig_start_x;
			 int trig_end_xx=trig_end_x;
			 int trig_end_yy=trig_end_y;
			 int trig_start_yy=trig_start_y;
			
			 
			 
			 if(x1==x2 || y1==y2)
			 {
		//		zoom_out();
			 }
			 scroll3();
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
			 
			 if(x2>GLP1.getWidth())
			 x2=GLP1.getWidth();
			 if(y2>GLP1.getHeight())
			 y2=GLP1.getHeight();
			 
			 if(x1<0)
			x1=0;
			if(y1<0)
			y1=0;
			 
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
			
			public void scroll3()
			{  
			   ss1=(int)((float)trig_start_x/(float)(TRIGGER.length)*10000);
			   ss2=(int)((float)(trig_end_x-trig_start_x)/(float)(TRIGGER.length)*10000);
			   sx3.setValues(ss1,ss2,0,10000); 
			   sx3.setEnabled(true);
				
			   ss3= (int)((float)trig_start_y/(float)(TRIGGER[0].length)*10000);
			   ss4= (int)((float)(trig_end_y-trig_start_y)/(float)(TRIGGER[0].length)*10000);
			   sy3.setValues(ss3,ss4,0,10000);
			   sy3.setEnabled(true);
			}
			
		
		 public static void whitescreen( GLAutoDrawable glautodrawable ) {
			 GL2 gl = glautodrawable.getGL().getGL2();
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
			 gl.glColor3f(0.5f, 0.5f,0.5f);
			 gl.glRectd(-1f,-1f,1f,1f);
		 }
		 
		 public static void blackscreen( GLAutoDrawable glautodrawable ) {
			 GL2 gl = glautodrawable.getGL().getGL2();
			 gl = glautodrawable.getGL().getGL2();
			 gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
			 gl.glColor3f(0f, 0f, 0f);
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
							disp();
						}
						if(e.getSource()==B3[1])
						{
							int g=Integer.parseInt(T1.getText())-1;
							T1.setText(String.valueOf(g));
							disp();
						}
					}
				});
				P5.add(B3[i]);
				}
				
				T1=new JTextField(String.valueOf(fsp));
				T1.setBounds(B3[1].getX()+42, 4, 74,32);
				P5.add(T1);
				
				//Shot Value changed
				Action action1=new AbstractAction()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String s=T1.getText();
						try{
							int g=Integer.parseInt(s);
							if(g<fsp)
							T1.setText(String.valueOf(fsp));
							if(g>shot)
							T1.setText(String.valueOf(shot));
							if(T1.isEnabled())
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
				
				B3[0].setToolTipText("Go to First SP Number");
				B3[1].setToolTipText("Decrease SP Number");
				B4[0].setToolTipText("Increase SP Number");
				B4[1].setToolTipText("Go to Last SP Number");
				
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
					 
					 SPB=new JButton("READY");
					 SPB.setBackground(Color.yellow);
			//		 SPB.setFont(new Font("Arial",40));
					 P1.add(SPB);
					 
					 String[] INFOS={"Information","Save","Save as a .txt file","Take a Screenshot","Save Screenshot to Database"};
					 B7=new JButton[5];
						for(int i=0;i<B7.length;i++)
						{
							ImageIcon icon=new ImageIcon("ICONS//"+"l"+String.valueOf(i)+".png");	
						if(i>2)
						{
						 icon=new ImageIcon("ICONS//"+"l"+String.valueOf(i+1)+".png");	
						}
						
						B7[i]=new JButton(icon);
						B7[i].setBounds(10*(i+1)+i*32, 4, 32, 32);
						B7[i].setToolTipText(INFOS[i]);
						B7[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{
								if(e.getSource()==B7[0])
								{
							    LF.setBounds(F.getX()+100, F.getY()+100, 1300, 700);
								LF.setVisible(true);
								}
								if(e.getSource()==B7[1])
								{
									try {
										savereport();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								if(e.getSource()==B7[2])
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
								}
								
								if(e.getSource()==B7[3])
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
								if(e.getSource()==B7[4])
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

										textfile="PROJECTS/"+project_name+"/"+seq_no+"/segd.jpg";
										File f=new File(textfile);
										int k=1;
										while(f.exists())
										{
										textfile="PROJECTS/"+project_name+"/"+seq_no+"/segd_"+String.valueOf(k)+".jpg";
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
								
							}
						}
						);
						
						P23.add(B7[i]);
						}
					 
					 B7[1].setEnabled(false);
					 B7[2].setEnabled(false);
				
					 B8=new JButton[number_of_streamers+1];
					 //Close the SEGD Display
					 B8[0]=new JButton("C");
					 B8[0].setMargin(new Insets(0,0,0,0));
					 B8[ii].setToolTipText("Close SEGD Viewer");
					 B8[0].setBorder(null);
					 B8[0].setBounds(10, 4, 32, 32);
				     P24.add(B8[0]);
					 B8[0].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{
								button_lights(0);
								P8.setVisible(false);
								resize();
							}});
					 
					 //Streamer Buttons
						for(ii=1;ii<B8.length;ii++)
						{
						String s=String.valueOf(ii);
						B8[ii]=new JButton(s);
						B8[ii].setToolTipText("Streamer No"+(ii));
						B8[ii].setBounds(10*(ii+1)+ii*32, 4, 32, 32);
						B8[ii].setMargin(new Insets(0,0,0,0));
						B8[ii].setBorder(null);
						B8[ii].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{
								P8.setVisible(true);
								resize();
								String t=((JButton)e.getSource()).getText();
								int a=Integer.parseInt(t);
								streamer=a;
								button_lights(a);
								if(tstart==0 && OUTPUT!=null && OUTPUT!=null)
								{
									readsegd(filename);
									GLP1.display();
								}
							}
						}
						);
						
						P24.add(B8[ii]);
						}
						B8[streamer].setBackground(Color.yellow);
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
		
		
		
		public void savetext() throws IOException
		{
			/*
			int x=tm.getRowCount();
			Object start=tm.getValueAt(0, 0);
			Object end=tm.getValueAt(x-1, 0);
			int good_shot=tm_ok.getRowCount();
			int missing_shot=tm_na.getRowCount();
			int comm_error=tm_comm.getRowCount();
			int autofire_count=tm_af.getRowCount();
			int delta_timing=tm_dt.getRowCount();
			int pressure_error=tm_pe.getRowCount();
			int depth_error=tm_de.getRowCount();
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
			
			if(autofire_count>0)
			{
				pw.println("------------------------------------Autofire--------------------------------------");
				pw.println("---------------------------------------------------------------------------------------");
				pw.println("--Time--------------------Status--------------------------------------------------------");
				for(int i=0;i<missing_shot;i++)
				{
				String ss=tm_af.getValueAt(i, 0)+"   "+tm_af.getValueAt(i, 1);
				pw.println(ss);
				}
			}
			
			if(delta_timing>0)
			{
				pw.println("------------------------------------Delta Timing-------------------------------------");
				pw.println("---------------------------------------------------------------------------------------");
				pw.println("--Time--------------------Status--------------------------------------------------------");
				for(int i=0;i<missing_shot;i++)
				{
				String ss=tm_dt.getValueAt(i, 0)+"   "+tm_dt.getValueAt(i, 1);
				pw.println(ss);
				}
			}
			
			if(pressure_error>0)
			{
				pw.println("------------------------------------Pressure Error--------------------------------------");
				pw.println("---------------------------------------------------------------------------------------");
				pw.println("--Time--------------------Status--------------------------------------------------------");
				for(int i=0;i<missing_shot;i++)
				{
				String ss=tm_pe.getValueAt(i, 0)+"   "+tm_pe.getValueAt(i, 1);
				pw.println(ss);
				}
			}
			
			if(depth_error>0)
			{
				pw.println("------------------------------------Depth Error--------------------------------------");
				pw.println("---------------------------------------------------------------------------------------");
				pw.println("--Time--------------------Status--------------------------------------------------------");
				for(int i=0;i<missing_shot;i++)
				{
				String ss=tm_de.getValueAt(i, 0)+"   "+tm_de.getValueAt(i, 1);
				pw.println(ss);
				}
			}
			
			
			pw.close();
			*/
			
		}
		
		
		public void savereport() throws IOException
		{
			/*
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

			*/
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
	//		addrow("Paused by user"," ");
			
			String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
			model.addRow(new Object[]{now,"Paused by user","","","","","","",""});
			
			T1.setEnabled(true);
			tstart=0;
			B5[0].setEnabled(true);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
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
	//		addrow("Stopped by user"," ");
			
			tstart=0;
			OUTPUT=null;
			
			control=0;
			
			for(int i=0;i<number_of_string;i++)
			{
				PRESSURE[i].removeAll(PRESSURE[i]);
				DEPTH[i].removeAll(DEPTH[i]);
			}
			
			/*
			tm.removeall();
			tm_ok.removeall();
			tm_na.removeall();
			tm_comm.removeall();
			tm_af.removeall();
			tm_dt.removeall();
			tm_pe.removeall();
			tm_de.removeall();
			*/
			
			GLP1.display();
			GLP3.display();
			GLP4.display();    
		
			
			
			B5[0].setEnabled(true);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
			SPB.setText("Stopped");
			SPB.setBackground(Color.red);
			shot=fsp;
			F.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		
		public void start()
		{
	//		addrow("Started by user"," ");
			String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
			model.addRow(new Object[]{now,"Started by user","","","","","","",""});
			transfer t=new transfer();
			exit=false;
			t.start();
			start=1;
			tstart=1;
			//Enable puase-stop buttons
			B5[0].setEnabled(false);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
			T1.setEnabled(false);
			
			
			
		}
		
		public void resize()
		{
			P1.setBounds(10,10,F.getWidth()-25,50); 
			P3.setBounds(10,F.getHeight()-90,F.getWidth()-25,50);
			P2.setBounds(10,70,F.getWidth()-25,P3.getY()-75);
			P15.setBounds(10,5,40+32*3,40);
			P4.setBounds(P15.getX()+P15.getWidth()+10,5,50+32*4,40);
			P5.setBounds(P4.getX()+P4.getWidth()+10,5,70+32*6,40);
			P6.setBounds(P5.getX()+P5.getWidth()+10,5,60+32*5,40);
		
			if(P8.isVisible())
			{
				P17.setBounds(P1.getX(),10,(int)((P2.getWidth()-40)*0.25f),P2.getHeight()-20);
				P8.setBounds(P17.getX()+P17.getWidth()+10, P17.getY(),(int)((P2.getWidth()-40)*0.35f),P17.getHeight());
				P16.setBounds(P8.getX()+P8.getWidth()+10, P17.getY(),(int)((P2.getWidth()-40)*0.4f),P17.getHeight());
			}
			else
			{
				P17.setBounds(P1.getX(),10,(int)((P2.getWidth()-40)*0.3f),P2.getHeight()-20);
				P16.setBounds(P17.getX()+P17.getWidth()+10, P17.getY(),(int)((P2.getWidth()-40)*0.7f),P17.getHeight());
			}
			
			JSP.setBounds(0,0, P17.getWidth(),(int)(P17.getHeight()*0.7f));
			JSP2.setBounds(0,JSP.getHeight(), P17.getWidth(),(int)(P17.getHeight()*0.3f));
			
			
			JL.getColumnModel().getColumn(0).setPreferredWidth((int)(JSP.getWidth()*0.25f));
			JL.getColumnModel().getColumn(1).setPreferredWidth((int)(JSP.getWidth()*0.75f));
		
			P20.setBounds(P6.getX()+P6.getWidth()+10,5,50+32*4,40);
			P23.setBounds(P20.getX()+P20.getWidth()+10,5,60+32*5,40);
			P24.setBounds(P23.getX()+P23.getWidth()+10,5,(number_of_streamers+2)*10+32*(number_of_streamers+1),40); //Streamer no
			SPB.setBounds(P24.getX()+P24.getWidth()+10,P24.getY(),150,P24.getHeight());
			//FFT Panel 	
			P7.setBounds(10,100,80,(int)((P2.getHeight()-160)));
			GLP1.setBounds(90, 100, P8.getWidth()-120,P7.getHeight());
			sx.setBounds(GLP1.getX(), GLP1.getY()+GLP1.getHeight(),GLP1.getWidth(),20);
			sy.setBounds(GLP1.getX()+GLP1.getWidth(),GLP1.getY(),20,GLP1.getHeight());		
			P9.setBounds(GLP1.getX()-12,10,GLP1.getWidth()+24, 90);		
			//Gun Pressure Panel
			P10.setBounds(10,10,P16.getWidth()-20,50);
			int h=(P16.getHeight()-230)/2;
			P11.setBounds(P10.getX(),P10.getY()+P10.getHeight(),80,h-20);
			GLP3.setBounds(P11.getX()+P11.getWidth(),P11.getY(),P10.getWidth()-104,P11.getHeight());
			P21.setBounds(P11.getX(),P11.getY()+P11.getHeight()+30,P10.getWidth(),40);
			sx1.setBounds(GLP3.getX(),GLP3.getY()+GLP3.getHeight(),GLP3.getWidth(),20);
			sy1.setBounds(GLP3.getX()+GLP3.getWidth(),GLP3.getY(),20,GLP3.getHeight());	
			//Gun Depth Panel
			P12.setBounds(P10.getX(), P21.getY()+P21.getHeight()+10, P10.getWidth(), P10.getHeight());
			P13.setBounds(P11.getX(), P21.getY()+P21.getHeight()+10+P12.getHeight(), P11.getWidth(), P11.getHeight());
			GLP4.setBounds(P13.getX()+P13.getWidth(),P12.getY()+P12.getHeight(),GLP3.getWidth(),GLP3.getHeight());
			P22.setBounds(P11.getX(),P13.getY()+P13.getHeight()+30,P10.getWidth(),40);
			sx2.setBounds(GLP4.getX(),GLP4.getY()+GLP4.getHeight(),GLP4.getWidth(),20);
			sy2.setBounds(GLP4.getX()+GLP4.getWidth(),GLP4.getY(),20,GLP4.getHeight());			
		//	P8.repaint();P8.validate();
			P16.repaint();P16.validate();
			
			//Seal Trigger Panel
			int aux_height= (P17.getHeight()-JSP.getHeight()-80)/2;
		
		
			
			
			P17.repaint();P17.validate();
			
			
			P18.setBounds(P8.getX(), 5, P8.getWidth(), 40);
			P19.setBounds(P16.getX(), 5, P16.getWidth(), 40);
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
			DP.setBounds(F.getX()+100, F.getY()+100, 500,540);
			DP.setResizable(false);
			dp1.setBounds(10,10,450,150);dp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp2.setBounds(10,170,450,100);dp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp3.setBounds(10,280,450,100);dp3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dp4.setBounds(10,400,450,50);dp4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
			c1.setBounds(120, 10,100, 30);c2.setBounds(250, 10, 120, 30);
			bg1.add(c1);bg1.add(c2);
			dp1.add(c1);dp1.add(c2);
			//RadioButtons P1 S2
			JRadioButton pol1,pol2;
			pol1=new JRadioButton("Normal");pol2=new JRadioButton("Reverse");
			ButtonGroup bg2=new ButtonGroup();
			pol1.setBounds(120, 60,100, 30);pol2.setBounds(250, 60, 120, 30);
			bg2.add(pol1);bg2.add(pol2);
			dp1.add(pol1);dp1.add(pol2);
			//RadioButtons P1 S3
			JRadioButton g1,g2;
			g1=new JRadioButton("Yes");g2=new JRadioButton("No");
			ButtonGroup bg3=new ButtonGroup();
			g1.setBounds(120, 110,100, 30);g2.setBounds(250, 110, 120, 30);
			bg3.add(g1);bg3.add(g2);
			dp1.add(g1);dp1.add(g2);
			//Panel2 Labels
			JLabel l4=new JLabel("Minimum Pressure (psi)");JLabel l5=new JLabel("Maximum Pressure (psi)");
			l4.setBounds(10,10,200,30);l5.setBounds(10,60,200,30);
			dp2.add(l4);dp2.add(l5);
			//Panel2 TextField
			t1=new JTextField();t2=new JTextField();t3=new JTextField();
			t1.setBounds(250,10,100,30);t2.setBounds(250,60,100,30);
			dp2.add(t1);dp2.add(t2);
			
			//Panel3 Labels
			JLabel l7=new JLabel("Minimum Depth (meter)");JLabel l8=new JLabel("Maximum Depth (meter)");
			l7.setBounds(10,10,200,30);l8.setBounds(10,60,200,30);
			dp3.add(l7);dp3.add(l8);
			//Panel3 TextField
			t4=new JTextField();t5=new JTextField();
			t4.setBounds(250,10,100,30);t5.setBounds(250,60,100,30);
			dp3.add(t4);dp3.add(t5);
			
			//Panel4 Labels
			JLabel l6=new JLabel("End Time in Milliseconds");
			l6.setBounds(10,10,200,30);l5.setBounds(10,60,200,30);
			dp4.add(l6);
			//Panel4 TextField
			t3.setBounds(250,10,100,30);
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
		        	
		        	pres_maxx=pres_max+50;
		        	pres_minn=pres_min-50;
		        	depth_maxx=depth_max+0.25f;
		        	depth_minn=depth_min-0.25f;
		        	
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
		        	pres_maxx=pres_max+50;
		        	pres_minn=pres_min-50;
		        	depth_maxx=depth_max+0.25f;
		        	depth_minn=depth_min-0.25f;
		        	
		        	
		        	endy=(int)(time/sample_int);
		        	if(tstart==1)
		        	{
		        	GLP1.display();
		        	GLP3.display();
		        	GLP4.display();
		        	
		        	
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
		
	
		
		public void display()
		{
			GLP1.display();
	
			
			
		}
		
		
		
		
		file_manager fm=new file_manager();
		
		int afgun,dtgun;
		float dterror;
		//Pressure String check to add
		int pstring,mstring;
		int pvalue;
		float mvalue;
		
		static private volatile boolean exit = true; 

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
		
				 while(!exit)
				 {
		
				
				filename=fm.finder(filepath, shot);
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
				    GLP3.display();
				    GLP4.display(); 
	//			    GLP5.display();
	//			    GLP6.display();
				  
				   
				   
 
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
						if(timesayac==300)
						{
			//				addrow(shot+".segd does not exists","N/A");	
							
							String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
							String a=shot+".segd does not exists";
							model.addRow(new Object[]{now,a,0,0,0,0,0,0,""});	
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
	 	//	addrow("Sequence is finished"," ");	
	 		B7[1].setEnabled(true);
	 		B7[2].setEnabled(true);
	 		T1.setEnabled(true);
			B5[0].setEnabled(true);
			B5[1].setEnabled(true);
			B5[2].setEnabled(true);
			SPB.setText("Finished");
			SPB.setBackground(Color.red);
			B5[0].setEnabled(false);
			B5[1].setEnabled(false);
			B5[2].setEnabled(false);
	 	}
	 	
	 	
	 	

	 	
		
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	public String findpath()
	 	{
	 		String output;
	 		int seq=Integer.parseInt(seqno);
	 		String s;
	 		//tape name 
	 		if(seq<10)
	 			s="tape000"+seqno;
	 		else if(seq<100)
	 			s="tape00"+seqno;
	 		else 
	 			s="tape0"+seqno;
	 		File f=new File("/SEGD/"+linename+"."+seqno+"/"+s);
	 		if(f.exists())
	 		{
	 			output="/SEGD/"+linename+"."+seqno+"/"+s;
	 		}
	 		else
	 		{
	 			output="/SEGD/"+linename+"."+seqno+"/";
	 		}
	 			 		
	 		return output;
	 	}
		
	 	static int ilk=0;
	 	int aux_count=0;
	 	public  ArrayList trigger=new ArrayList<float[]>();
	 	public  ArrayList breakk=new ArrayList<float[]>();
	 	
	 	public void readsegd(String filename)
	 	{
	 		byte[] b=segd2byte(filename); //segd verisi byte array alınır
	 		read_gh(b);
	 		chan_set(b);				  //chat set parametreleri çekilir
	 		external(b);				  // gun parametreleri okunur
	 		amplitudes(b,streamer);
	 		info(b);
	 		
	 		
	 	}
	 	

	 	
	 	public void info(byte[] b)
	 	{
	 		//Add Element To List
			
			m.setRowCount(0);
			m2.setRowCount(0);
			
			Object[] A=new Object[2];
			A[0]="File Name";A[1]=filename;m.addRow(A);
			A[0]="File Size";A[1]=b.length+" bytes";m.addRow(A);
			A[0]="File Number";A[1]=file_number;m.addRow(A);
			A[0]="Shot Point Number";A[1]=sp_number;m.addRow(A);
			A[0]="Record Length";A[1]=record_length+" ms";m.addRow(A);
			A[0]="Sample Interval";A[1]=sample_int+" ms";m.addRow(A);
			A[0]="Number of Chan Set";A[1]=number_of_chan_set;m.addRow(A);
			A[0]="Size of Extended Header";A[1]=extended+" bytes";m.addRow(A);
			A[0]="Size of External Header";A[1]=extern+" bytes";m.addRow(A);
			
			for(int i=0;i<number_of_chan_set;i++)
			{
				if(chan_number[i]>0)
				{
					A[0]="Number of Channels ( Cable No:" +cable_no[i]+")";
					A[1]=chan_number[i];
					m.addRow(A);
				}
				
			}
			
			A[0] = "Master Latitude";   A[1] = mlat;   m.addRow(A.clone());
			A[0] = "Master Longitude";   A[1] = mlon;   m.addRow(A.clone());
			A[0] = "Water Depth (meter)"; A[1] = wdepth; m.addRow(A.clone());
			A[0] = "Source Latitude";   A[1] = slat;   m.addRow(A.clone());
			A[0] = "Source Longitude";   A[1] = slon;   m.addRow(A.clone());
			A[0] = "Master Gyro";  A[1] = mgyro;  m.addRow(A.clone());
			A[0] = "Master Cmg";   A[1] = mcmg;   m.addRow(A.clone());
			A[0] = "Vessel speed";  A[1] = speed;  m.addRow(A.clone());
			
			
			A[0] = "Number of Strings";          A[1] = nogs;                        m.addRow(A.clone());
			A[0] = "Number of Gun"; A[1] = String.valueOf(number_of_gun); m.addRow(A.clone());
			A[0] = "Number of Fired Gun";     A[1] = gun_fired;                   m.addRow(A.clone());
			A[0] = "Number of Fire Error";      A[1] = fire_err;                    m.addRow(A.clone());
			A[0] = "Number of Autofired Guns";         A[1] = afire;                       m.addRow(A.clone());
			A[0] = "Number of Misfired Guns";     A[1] = mis_fired;                   m.addRow(A.clone());
			A[0] = "Total Error Spread";     A[1] = total_err;                   m.addRow(A.clone());
			A[0] = "Volume cu. in";        A[1] = volume;                      m.addRow(A.clone());
			A[0] = "Mainfold Pressure";        A[1] = mainfold;                      m.addRow(A.clone());
			
			
			/*
			A[0]="Number of Seis Traces";A[1]=seis;m.addRow(A);
			A[0]="Number of General Block";A[1]=(int)(add_blocks+1);m.addRow(A);
			
			
			
			if(nonav==0){A[0]="Status";A[1]="OK";m.addRow(A);}
			else{A[0]="Status";A[1]="NO NAV";m.addRow(A);}
			*/
			
			//Error ekleme
			
			Object[] B=new Object[1];
			
			for(int i=0;i<ERROR.size();i++)
			{
				B[0]=ERROR.get(i);
				m2.addRow(B);
			}
			
			ERROR.clear();
	 	}
	 	
	 	
	 	
	 	
	 	 public static void amplitudes(byte[] b,int cable)
		 {
			 //tanımlamalar
			 byte[] AMP ;
			 FloatBuffer fb;
			 float[] input;
			 
			 
			 
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
			 
			 int cable_chan=0;
			 
			 for(int i=0;i<number_of_chan_set;i++)
			 {
				 if(cable_no[i]!=cable)
				 trace_position=trace_position+chan_number[i]*(trace_size+header_size);
				 else
				 {
					 cable_chan=chan_number[i];
				 }	 
			 }
			 
			 trace_position=trace_position+(add_blocks+1+number_of_chan_set)*32+extended+external;
			 
			    INPUT=new float[cable_chan][];
				float[][] OUTPUT1=new float[cable_chan][];
			 
			 for(int i=0;i<cable_chan;i++)
			 {
				 pos=trace_position+header_size+(trace_size+header_size)*i;
				 
				 if(format_code.equals("8058"))
						 {
					    AMP = Arrays.copyOfRange(b, pos, pos+trace_size);
						fb = ByteBuffer.wrap(AMP).asFloatBuffer();
						input = new float[number_of_sample];
						fb.get(input);
						INPUT[i]=input;
						OUTPUT1[i]=filter2(input);
						 }
			 }
			 
			 OUTPUT=OUTPUT1;
				
				//Memory Menegement
				OUTPUT1=null;
				input=null;
				b=null;	
				System.gc();
				b=null;
			//	OUTPUT=INPUT;
				INPUT=null;
			
			System.gc();
			//Get the values for the first shot
			if(ilk==0)
			{
				startx=0;
				endx=cable_chan;
				starty=0;
				int nos=(int)(time/sample_int);
				endy=nos;
				ilk=1;		
			}
			
			startxx=0;
			startyy=(int) pres_minn;
			endxx=PRESSURE[0].size();
			endyy=(int) pres_maxx;
			 
			 
		 }
	 	
	 	static int[] chan_id;					// chan id 
		static int[] chan_number;				// number of channel per chan set
		static int[] cable_no;
		static int number_of_gun;
		
		
		static int af,dt_err,pre_err,dep_err; // Error Parametreleri 0- error var 1-error yok
		static int delta=15;
		
	
		static float[] depth;
		
		 static String wdepth,slat,slon,mlat,mlon,mgyro,mcmg,speed;
		 static String nogs,gun_fired,fire_err,afire,mis_fired,total_err,volume,mainfold;
		 
		 public static void error_add(String a)
		 {
			 ERROR.add(a);
		 }
		
		 public static void external(byte[] b)
		 {
			
			 
			 int pos=(add_blocks+1+number_of_chan_set)*32+extended;
			 byte[] ext = Arrays.copyOfRange(b, pos, pos+external);
			 String headerText = new String(ext, java.nio.charset.StandardCharsets.US_ASCII);
			 
			 ////NAV HEADER
			 int index = headerText.indexOf('$');
			 int header_length=0;
			 
			 
			 if (index != -1 && index + 5 < headerText.length()) {
			     // $ + 1 (skip) + 4 karakter
			     String result = headerText.substring(index + 2, index + 6);
			     header_length=Integer.parseInt(result);
			     
			      mlat=headerText.substring(index+58, index+69);
				  mlon=headerText.substring(index+69, index+80);
				  wdepth=headerText.substring(index+80, index+86);
				  slat=headerText.substring(index+86, index+97);
				  slon=headerText.substring(index+97, index+108);
				  mgyro=headerText.substring(index+108, index+113);
				  mcmg=headerText.substring(index+113, index+118);
				  speed=headerText.substring(index+118, index+122);
				  
				
			 }
			 else
			 {
				      mlat="N/A";
					  mlon= "N/A";
					  wdepth="N/A";
					  slat="N/A";
					  slon="N/A";
					  mgyro="N/A";
					  mcmg="N/A";
					  speed="N/A";			
					error_add( shot+".segd does not exists");

			 }
			 
			
			 
			 //GCS 90 verisi çekilir
			 String target = "*GCS90";
			 index = headerText.indexOf(target);
			 header_length=0;
			 depth=new float[number_of_string];
			 

			 if (index != -1) {
				 int start = index + target.length();      // GCS90'dan hemen sonrası
				    int end = Math.min(start + 4, headerText.length()); // Taşma olmasın
				    String hl = headerText.substring(start, end);
				    header_length=Integer.parseInt(hl);
				    
				  //gcs 90 verisi okunur
					String header=headerText.substring(index, index+header_length);
					
					
					// String basınç değerleri okunur- pressure hatası veren gun kontrol edilir
					for(int i=0;i<number_of_string;i++)
					{
						int st=90+i*4;
						pres[i]=Integer.valueOf(header.substring(st,st+4 ));
						if(pres[i]>pres_max || pres[i]<pres_min)
						{
							pre_err=0;
							int str=i+1;
							String s="String: "+str+" Pressure/Error: "+pres[i];
							error_add(s);
						}
					}
					
					
				
					
					nogs=header.substring(49, 50);
					number_of_gun= Integer.parseInt(header.substring(50, 52));
					gun_fired=header.substring(52, 54);
					fire_err=header.substring(54, 56);
					afire=header.substring(56, 58);
					mis_fired=header.substring(58, 60);
					total_err=header.substring(60, 63);
					mainfold=header.substring(82, 86);
					volume=header.substring(63, 68);
					
					
					
					int nogps=number_of_gun/number_of_string;
					int sabit=nogps;
					float gun_total=0;
					int sayac=0;
					int say=0;
					af=0;
					dt_err=0;
					//gun parametreleri okunur
					for (int i=0;i<number_of_gun;i++)
					{
						int st=90+number_of_string*4+i*22;
						
						//Autofire kontrol 5-6
						String autofire=header.substring(st+5,st+6);
						if(autofire.equals("N"))
						af=af+1;
						else
						{
							int str=i+1;
							String s="Gun: "+str+ " Autofire";
							error_add(s);
						}
						
						//Delta Error Check 16-19
						int delta_time = Math.abs(Integer.valueOf(header.substring(st+16,st+19)));
						if(delta_time<delta)
							dt_err=dt_err+1;
						else
						{
							int str=i+1;
							String s="Gun: "+str+" dt Error: "+Integer.valueOf(header.substring(st+16,st+19));
							error_add(s);
						}
							
						
						
						//Depth 19-22
						int gun_depth = Math.abs(Integer.valueOf(header.substring(st+19,st+22)));
						if(gun_depth>0)
						{
							gun_total=gun_total+gun_depth;
							sayac++;
						}
						if(i==nogps-1)
						{
							depth[say]=(gun_total/sayac)/10f;
							sayac=0;
							nogps=nogps+sabit;
							say++;
							gun_total=0;
						}
					}
					
					nonav=1;

					if(af<number_of_gun)
						af=0;
					else
						af=1;
					
					if(dt_err<number_of_gun)
						dt_err=0;
					else
						dt_err=1;
					
					pre_err=1;
					dep_err=1;
					
					for(int i=0;i<number_of_string;i++)
					{
						if(pres[i]>pres_max || pres[i]<pres_min)
						{
							pre_err=0;
						}
						
						if(depth[i]>depth_max || depth[i]<depth_min)
						{
							dep_err=0;
						}
					}
					
					
					//Basınç ve Derinlik Değerleri Yazılır
					int check=0;
					for(int i=0;i<number_of_string;i++)
					{
						if(pres[i]==0)
						check++;
					}
					
					//if there is a pressure values..add them to PRESSURE LIST
					if(check==number_of_string)
					{
						int s=PRESSURE[0].size();
						for(int i=0;i<number_of_string;i++)
						{
						PRESSURE[i].add(PRESSURE[i].get(s-1));	
						DEPTH[i].add(DEPTH[i].get(s-1));
						}
						
					}
					else
					{		
						for(int i=0;i<number_of_string;i++)
						{
						PRESSURE[i].add(pres[i]);
						DEPTH[i].add(depth[i]);
						}
					}
					
					//Log güncelleme
					 String action="File Number:"+file_number+ "(SP:"+sp_number+ ")was read succesfully";
					 String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
					 model.addRow(new Object[]{now,action,1,nonav,af,dt_err,pre_err,dep_err,""});
				    
			 } 
			 //GCS 90 verisi eksik 
			 else
			 {
				 nonav=0;
				 af=0;
				 dt_err=0;
				 pre_err=0;
				 dep_err=0; 
				    
				 String action="File Number:"+file_number+ "(SP:"+sp_number+ ")was read succesfully";
				 String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
				 model.addRow(new Object[]{now,action,1,nonav,af,dt_err,pre_err,dep_err,""});	
				 
			 }
		 }
	 	
	 	
		 public static void chan_set(byte[] b)
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
	 	
	 	static int year,day,hour,sec;
	 	static String format_code;
		static float record_length;
		static int add_blocks;
		static int number_of_chan_set;
		static int extended,external;
		static String extern,extend;
	 	 
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
			
			
			//General Header-3
			g1 = Arrays.copyOfRange(b, 64, 96);
			GH = byte_hex(g1);
			sp_number=Integer.parseInt(Hex_Dec(GH.substring(16, 22)));
			
			SP.add(sp_number);
			FFID.add(file_number);
			
			
	 }
	 	 
	 	
		public void readsegd1(String filename)
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
				int add_blocks=Integer.parseInt(GH.substring(22, 23));
				sample_int=Float.parseFloat(Hex_Dec(GH.substring(44, 46)))/16f;
				int number_of_chan_set=Integer.parseInt(GH.substring(56, 58));
				int extended=Integer.parseInt(GH.substring(60, 62))*32;
				int extern=0;
				String external=GH.substring(62, 64);
				
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
				
				int record_length=Integer.parseInt(Hex_Dec(GH.substring(28, 34)));
				number_of_sample=(int)((float)record_length/(float)sample_int)+1;
				//Channel set
				int chan_pos=(add_blocks+1)*32;
				byte[] cs = Arrays.copyOfRange(b, chan_pos, chan_pos+32);
				GH = byte_hex(cs);
				int number_of_extensions=Integer.parseInt(GH.substring(57, 58));
				int header_size=20+32*number_of_extensions;
				int amp_size=number_of_sample*4;
				int trace_block_size= header_size+amp_size;
				
				//General Header-3
				g1 = Arrays.copyOfRange(b, 64, 96);
				GH = byte_hex(g1);
				sp_number=Integer.parseInt(Hex_Dec(GH.substring(16, 22)));
				
				
				
				SP.add(sp_number);
				FFID.add(file_number);
		//		int size=b.length;
				int trace_start_point=(add_blocks+1)*32+number_of_chan_set*32+extern+extended;
			//	int number_of_chan=(size-trace_start_point)/(trace_block_size)-4;
				float[] input;
				
				int start_chan=number_of_chan_per_streamer*(streamer-1);
				INPUT=new float[number_of_chan_per_streamer][];
				float[][] OUTPUT1=new float[number_of_chan_per_streamer][];
				
				
				//Extended Info
				int pos=trace_start_point-(extern+extended);
				byte[] ext = Arrays.copyOfRange(b, pos, pos+extended);
				GH = byte_hex(ext);
				int chan_total=Integer.parseInt(Hex_Dec(GH.substring(16, 24)));	
				int aux=Integer.parseInt(Hex_Dec(GH.substring(24, 32)));
				int seis=Integer.parseInt(Hex_Dec(GH.substring(32, 40)));
				
				//External (Gun Pressure Info)
				pos=trace_start_point-(extern);
				ext = Arrays.copyOfRange(b, pos, pos+extern);
				GH = byte_hex(ext);
				
				
				
				
				
				for(int i=0;i<number_of_string;i++)
				{
					int pre=hex2pre(GH.substring(552+i*8,560+i*8));	
					pres[i]=pre;
				}
				nonav=0; //Nav header check
				int check=0;
				for(int i=0;i<number_of_string;i++)
				{
					if(pres[i]==0)
					check++;
				}
				
				//if there is a pressure values..add them to PRESSURE LIST
				if(check==number_of_string)
				{
					nonav=1;
					//Save the previous file as a pressure
					int s=PRESSURE[0].size();
					for(int i=0;i<number_of_string;i++)
					{
					PRESSURE[i].add(PRESSURE[i].get(s-1));	
					DEPTH[i].add(DEPTH[i].get(s-1));
					}
					
				}
				else
				{
					
					//PRESSURE.add(pres);			
					for(int i=0;i<number_of_string;i++)
					{
					PRESSURE[i].add(pres[i]);	
					if(pres[i]>pres_max || pres[i]<pres_min)
					{
						nonav=4;
						pstring=i+1;
						pvalue=pres[i];
					}
					}
				}
				float dep=0;
				int k=1;
				int number_of_gun=12;
				int total_gun=number_of_gun*number_of_string*number_of_array;
				float ort=0;
				if(nonav==0)
				{
				for(int i=0;i<total_gun;i++)
				{
				//	int d=(hex2pre(GH.substring(638+i*44,644+i*44)));
					int d=(hex2pre(GH.substring(number_of_string*8+590+i*44,number_of_string*8+596+i*44)));
					if(d>0)
					{
						dep=dep+d;
						ort++;
					}
					
					if(i==(number_of_gun*k)-1 && ort>0)
					{
						DEPTH[k-1].add(dep/(ort*10f));
						if(dep/(ort*10f) >depth_max ||  dep/(ort*10f)<depth_min)
						{
							nonav=5;
							mstring=k;
							mvalue=dep/(ort*10f);
						}
						ort=0;
						dep=0;
						k++;
					}
				}
				}
				String AFC="0";
				int afc=0;
				afgun=0;
				if(nonav==0)
				{
				for(int i=0;i<total_gun;i++)
				{
				//Autofire Check
				AFC=(GH.substring(number_of_string*8+562+i*44,number_of_string*8+564+i*44));
				if(!AFC.equals("4e"))
				{
				nonav=2;	
				afgun=i+1;
				}
				//Delta Timing Check
				int d=(hex2pre(GH.substring(number_of_string*8+586+i*44,number_of_string*8+590+i*44)));
				String MINUS=GH.substring(number_of_string*8+584+i*44,number_of_string*8+586+i*44);
				if(MINUS.equals("2d"))
				{
					d=-d;
				}
				if(d>15 || d<-15)
				{
					nonav=3;	
					dtgun=i+1;
					dterror=(float)d/10f;
				}
				
				
				}
				}
			
				//Read TRIGGER
				pos=trace_start_point+header_size;
				byte[] AMP = Arrays.copyOfRange(b, pos, pos+amp_size);
				FloatBuffer fb = ByteBuffer.wrap(AMP).asFloatBuffer();
				input = new float[number_of_sample];
				fb.get(input); 
				
				trigger.add(input);
				
				if(aux_len<aux_count+1)
				{
					trigger.remove(0);
				}
				
				for(int i=0;i<trigger.size();i++)
				{
				TRIGGER[i]=(float[]) trigger.get(i);	
				}
				
				
				//Read Break
				pos=trace_start_point+header_size+trace_block_size;
				AMP = Arrays.copyOfRange(b, pos, pos+amp_size);
				fb = ByteBuffer.wrap(AMP).asFloatBuffer();
				input = new float[number_of_sample];
				fb.get(input); 
				
			    breakk.add(input);
			    SP_BR.add(sp_number);
				
				if(aux_len<aux_count+1)
				{
					breakk.remove(0);
					SP_BR.remove(0);
				}
				
				for(int i=0;i<breakk.size();i++)
				{
				BREAK[i]=(float[]) breakk.get(i);	
				}
				
				
				aux_count=aux_count+1;

				if(P8.isVisible())
				{
				for(int i=0;i<number_of_chan_per_streamer;i++)
				{
					pos=trace_start_point+trace_block_size*(i+start_chan+aux)+header_size;
					AMP = Arrays.copyOfRange(b, pos, pos+amp_size);
					fb = ByteBuffer.wrap(AMP).asFloatBuffer();
					input = new float[number_of_sample];
					fb.get(input); 
					INPUT[i]=input;
					OUTPUT1[i]=filter2(input);
				}
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
				A[0]="Number of Traces";A[1]=chan_total;m.addRow(A);
				A[0]="Number of Auxes";A[1]=aux;m.addRow(A);
				A[0]="Number of Seis Traces";A[1]=seis;m.addRow(A);
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
			startyy=(int) pres_minn;
			endxx=PRESSURE[0].size();
			endyy=(int) pres_maxx;
			
		}
		
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
				D[i]=Y[i*2+1];
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
				  else if(column == 2 && w.getStatus() == "A/F") {
				         setBackground(Color.orange);
			      }
				  else if(column == 2 && w.getStatus() == "D/T") {
				         setBackground(Color.pink);
			      }
				  else if(column == 2 && w.getStatus() == "P/E") {
				         setBackground(Color.lightGray);
			      }
				  else if(column == 2 && w.getStatus() == "D/E") {
				         setBackground(Color.magenta);
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
				        //    	 gg.drawLine(sx1, sy1, ex1, ey1);
						   
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
		        		 if(OUTPUT!=null )
		        		 {
		        			 float r1=e.getX()/(float)GLP3.getWidth();
		        			 r1=(a2-a1)*r1+a1;
		        			 r1=((r1+1f)/2f)*(PRESSURE[0].size()-1);
		        			 int r2=Math.round(r1);
		        			 SL[0].setText("SP: "+SP.get(r2));
		        			 SL[1].setText("FFID :"+FFID.get(r2));
		        			 
		        			 for(int i=0;i<number_of_string;i++)
		        			 {
		        				 SL[2+i].setText("ST "+(i+1)+" "+PRESSURE[i].get(r2));
		        			 }
			
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
					  gg= (Graphics2D)GLP3.getGraphics();
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
				              a1=-1f;
				              a2=1f;
				              scroll1();
				              GLP3.display();
				          	 }
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
		      });
		    }
		
		    @Override
		    public void paintComponent(Graphics g) {
		    	super.paintComponent(g);
	 	        Graphics2D g2 = (Graphics2D) g;
	 	       Graphics2D g3=(Graphics2D) g;
	 	       if(start==1)
			    {
			    	
			    	
			    	startyy=(int)(((1f-a4)/2f)*(pres_maxx-pres_minn)+pres_minn);
			    	endyy=(int)(((1f-a3)/2f)*(pres_maxx-pres_minn)+pres_minn);
			    	

			    	int total_sample=endyy-startyy;
			    	float total_time=total_sample;
			    	total_time=total_time/5f;
			    	float time_int=0;
			    	/*
			    	float[] times={10000,1000,500,250,100,75,50,25,10,5,2.5f,1f,0.5f,0.25f,0,1f};
			    	
			    	
			    	for(int i=0;i<times.length-1;i++)
			    	{
			    	 if(total_time<times[i] && total_time>=times[i+1])
			    		 time_int=times[i+1];
			    	}
			    	
			    	float time_now;	
		    		time_now=pres_min;
		    		while(time_now<pres_maxx)
		    		{
		    			int tyer=(int)(time_now);
		    			int y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
		    			if(time_now> pres_minn)
		    			{
		    //				g.drawLine(0,y,super.getWidth(),y);
		    			}
		    			
		    			time_now=pres_max;	
		    		}
			    	*/
			   
			    	
			    	//VERTICAL GRID
		    		float r1=0f;
				    float r2=0f;
	   			 	r1=(a2-a1)*r1+a1;
	   			 	r1=((r1+1f)/2f)*(PRESSURE[0].size()-1);
	   			 	
	   			 	r2=1f;
				 	r2=(a2-a1)*r2+a1;
				 	r2=((r2+1f)/2f)*(PRESSURE[0].size()-1);
				 	g2.setColor(Color.WHITE);
			     	for(int i=(int)r1;i<=r2;i++)
				 	{
				 		int x=(int)(((float)(i-r1)/(float)(r2-r1))*(super.getWidth()));
				 		
				 		if(x>0 && x<super.getWidth())
				 		{
				 		g3.drawLine(x,0,x,super.getHeight());
				 		}
		    			
				 		int sss= (int)((r2-r1)/10);
		    			i=i+sss;
				 	}
			    	
			    	Stroke dashed=new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[]{9},0);
	    			g2.setStroke(dashed);
			    	int tyer=(int)(pres_min);
	    			int y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
	    			g.drawLine(0,y,super.getWidth(),y);
	    			
	    			tyer=(int)(pres_max);
	    			y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
	    			g.drawLine(0,y,super.getWidth(),y);
	    			
		    		
		    		
				

			    		
			    
			    	
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
		        		 if(OUTPUT!=null )
		        		 {
		        			 float r1=e.getX()/(float)GLP3.getWidth();
		        			 r1=(a2-a1)*r1+a1;
		        			 r1=((r1+1f)/2f)*(DEPTH[0].size()-1);
		        			 int r2=Math.round(r1);
		        			 SL[0].setText("SP: "+SP.get(r2));
		        			 SL[1].setText("FFID :"+FFID.get(r2));
		        			 
		        			 for(int i=0;i<number_of_string;i++)
		        			 {
		        				 SL[2+i].setText("ST "+(i+1)+" "+DEPTH[i].get(r2));
		        			 }
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
					  gg= (Graphics2D)GLP4.getGraphics();
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
				              a11=-1f;
				              a22=1f;
				              scroll2();
				              GLP4.display();
				          	 }
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
	 	        g.setColor(Color.white);
	 	       if(start==1)
			    {
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

	 	    	   
	 	    	   float startyy=(((1f-a4)/2f)*(depth_maxx-depth_minn)+depth_minn);
			    	float endyy=(((1f-a3)/2f)*(depth_maxx-depth_minn)+depth_minn);
			    	

			    	
			    	
	
		    		
		    		Stroke dashed=new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[]{9},0);
	    			g2.setStroke(dashed);
			    	float tyer=depth_min;
	    			int y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
	    			g.drawLine(0,y,super.getWidth(),y);
	    			
	    			tyer=depth_max;
	    			y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
	    			g.drawLine(0,y,super.getWidth(),y);
		    		

			    		
			    	
			    	
			    	}
			    
	 	        
		    }  
		}
		
		
		
		public class DrawPanel3 extends GLJPanel {
		    private static final long serialVersionUID = 1L;
		    private ArrayList<Point> points;
		   
		    Graphics2D gg,gg1 ;
		    public int sx,sy;
		    public int ex,ey;
		  
		    public DrawPanel3() {
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
		                		gg1.drawLine(sx, sy, ex, sy);
		                		gg1.drawLine(sx, sy, sx, ey);
		                		gg1.drawLine(sx, ey, ex, ey);
		                		gg1.drawLine(ex, sy, ex, ey);
		                		  pe=e.getPoint();
		                          int xx=e.getPoint().x;
		                          int yy=e.getPoint().y;
		                          gg.drawLine(sx, sy, xx, sy);
		                          gg.drawLine(sx, sy, sx, yy);
		                          gg.drawLine(sx, yy, xx, yy);
		                          gg.drawLine(xx, sy, xx, yy);
		                          gg1.drawLine(sx, sy, xx, sy);
		                          gg1.drawLine(sx, sy, sx, yy);
		                          gg1.drawLine(sx, yy, xx, yy);
		                          gg1.drawLine(xx, sy, xx, yy);
		                          //
		                    	 ex=xx;
		                    	 ey=yy;
		                		}
		                	}
		        	 }
		        	 public void mouseMoved(MouseEvent e) {
		        		 if(OUTPUT!=null )
		        		 {
		        	
		        			
		        			 /*
		        			 L[0].setText("FFID: "+c);
		        			 
		        			 if(a<SP[0].size())
		        			 L[1].setText("SP NO: "+SP[0].get(a)); 
		        			 else
		        			 L[1].setText("SP NO: "+0); 	 
		        			 L[2].setText("CHAN NO: "+d);
		        			 L[3].setText("AMPLITUDE: "+RMS[a][b]);
		        			 */
		        			 
		        			
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
					
					  
					  
			          	 if (e.getButton() == MouseEvent.BUTTON1){
			              gg.setXORMode(Color.white);
			              gg1.setXORMode(Color.white);
			              ps = e.getPoint();
			              sx=ps.x;
			              sy=ps.y;
			              if(zoom==1)
			              {
			             	 ex=sx;
			              	 ey=sy;    	
			              	 gg.drawLine(sx, sy, ex, ey); 
			              	 gg1.drawLine(sx, sy, ex, ey);
			              	 
					   }
			          	 }	 
			          	 
			          	 if (e.getButton() == MouseEvent.BUTTON3){
			          		trig_start_x=0;
			    			trig_end_x=aux_len;
			    			trig_start_y=0;
			    			trig_end_y=aux_rec_len;
			    			
			    			break_start_x=0;
			    			break_end_x=aux_len;
			    			break_start_y=0;
			    			break_end_y=aux_rec_len;
			    			sx3.setEnabled(false);
			    			sy3.setEnabled(false);
			    		
				              
				          	 }
			          	 
			          	 
			          
			          	 
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
		      });
		    }
		
		    @Override
		    public void paintComponent(Graphics g) {
		    	super.paintComponent(g);
	 	        Graphics2D g2 = (Graphics2D) g;
	 	        /*
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
		    			g2.drawLine(0,y,GLP5.getWidth(),y);
		    			time_now=time_now+time_int;	
		    		}

			    		
			    	
			    	
			    	}
			    }
	 	       */
	 	      if(start==1 && OUTPUT!=null)
			    {
			    	  
			    	int total_sample=break_end_y-break_start_y;
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
		    		while(time_now<sample_int*aux_rec_len)
		    		{
		    			int tyer=(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-break_start_y)/(float)(break_end_y-break_start_y))*super.getHeight());
		    		
		    			time_now=time_now+time_int;	
		    		}
			    	

			    	
			    }
	 	       
	 	        
		    }  
		}
		
		
		
		
		
		/*
		
		public class DrawPanel3 extends GLJPanel {
		    private static final long serialVersionUID = 1L;
		    private ArrayList<Point> points;
		   
		    Graphics2D gg ;
		    public int sx,sy;
		    public int ex,ey;
		  
		    public DrawPanel3() {
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
		        		 if(OUTPUT!=null )
		        		 {
		        			 float r1=e.getX()/(float)GLP5.getWidth();
		        			 r1=(a2-a1)*r1+a1;
		        			 r1=((r1+1f)/2f)*(DEPTH[0].size()-1);
		        			 int r2=Math.round(r1);
		        			 SL[0].setText("SP: "+SP.get(r2));
		        			 SL[1].setText("FFID :"+FFID.get(r2));
		        			 
		        			 for(int i=0;i<number_of_string;i++)
		        			 {
		        				 SL[2+i].setText("ST "+(i+1)+" "+DEPTH[i].get(r2));
		        			 }
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
					  gg= (Graphics2D)GLP4.getGraphics();
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
				              a11=-1f;
				              a22=1f;
				              scroll2();
				              GLP4.display();
				          	 }
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
		    		


			    		
			    	
			    	
			    	}
			    
	 	        
		    }  
		}
		
		
		*/
		
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
		
		public class YLabel3 extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
				 System.gc();
			    super.paintComponent(g);
			    if(start==1 && OUTPUT!=null)
			    {
			    	  
			    	int total_sample=break_end_y-break_start_y;
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
		    		while(time_now<sample_int*aux_rec_len)
		    		{
		    			int tyer=(int)(time_now/sample_int);
		    			int y=(int)(((float)(tyer-break_start_y)/(float)(break_end_y-break_start_y))*super.getHeight());
		    			g.drawLine(super.getWidth()-10,y,super.getWidth(),y);
		    			String ss=String.valueOf((int)time_now);
		    			g.drawString(ss,5,y+6);
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
		
		//Break-Trigger
		public class XLabel3 extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    System.gc();
			    if(start==1 && OUTPUT!=null)
			    {
			 
			    	/*
			    String ss="SP NB : "+String.valueOf(sp_number);
			    g.drawString(ss,10,12);
			    ss="FFID : "+String.valueOf(file_number);
			    g.drawString(ss,10,26);
			    ss="CABLE NO : "+String.valueOf(streamer);
			    g.drawString(ss,10,40);
			    */
			    
			    
			    for(int j=0;j<aux_len;j++)
	    		{
	    			float ssyer=0.5f+(j);
	    			int x=(int)(((float)(ssyer-trig_start_x)/(float)(trig_end_x-trig_start_x))*(super.getWidth()-24))+12;
	    			String ss="";
	    			if(j<SP_BR.size())
	    			ss=String.valueOf(SP_BR.get(j));
	    			int xx=(int)(((float)ss.length()/2f)*6f);
	    			g.drawString(ss,(int) (x-xx),super.getHeight()-25);
	    			g.drawLine(x,super.getHeight()-10,x,super.getHeight());
	    			int sss=(trig_end_x-trig_start_x)/10;
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
			    if(start==1)
			    {
			    String ss="GUN PRESSURE";
			    g.drawString(ss,P10.getWidth()/2+8,12);
			    
			    float r1=0f;
			    float r2=0f;
   			 	r1=(a2-a1)*r1+a1;
   			 	r1=((r1+1f)/2f)*(PRESSURE[0].size()-1);
   			 	
   			 	r2=1f;
			 	r2=(a2-a1)*r2+a1;
			 	r2=((r2+1f)/2f)*(PRESSURE[0].size()-1);
			 	
			 	for(int i=(int)r1;i<=r2;i++)
			 	{
			 		int x=(int)(((float)(i-r1)/(float)(r2-r1))*(super.getWidth()-104))+80;
			 		ss=String.valueOf(SP.get(i));
			 		int xx=(int)(((float)ss.length()/2f)*6f);
			 		if(x>79)
			 		{
			 			g.drawString(ss,(int) (x-xx),super.getHeight()-25);
				 		g.drawLine(x,super.getHeight()-10,x,super.getHeight());
			 		}
	    			
			 		int sss= (int)((r2-r1)/10);
	    			i=i+sss;
			 	}
			 	
			   
			    }
			     
			 }}
		
		public class XLabel2 extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    System.gc();
			    if(start==1)
			    {
			    String ss="STRING DEPTH";
			    g.drawString(ss,P10.getWidth()/2+8,12);
			    
			    float r1=0f;
			    float r2=0f;
  			 	r1=(a22-a11)*r1+a11;
  			 	r1=((r1+1f)/2f)*(DEPTH[0].size()-1);
  			 	
  			 	r2=1f;
			 	r2=(a22-a11)*r2+a11;
			 	r2=((r2+1f)/2f)*(DEPTH[0].size()-1);
			 	
			 	for(int i=(int)r1;i<=r2;i++)
			 	{
			 		int x=(int)(((float)(i-r1)/(float)(r2-r1))*(super.getWidth()-104))+80;
			 		ss=String.valueOf(SP.get(i));
			 		int xx=(int)(((float)ss.length()/2f)*6f);
			 		if(x>79)
			 		{
			 			g.drawString(ss,(int) (x-xx),super.getHeight()-25);
				 		g.drawLine(x,super.getHeight()-10,x,super.getHeight());
			 		}
	    			
			 		int sss= (int)((r2-r1)/10);
	    			i=i+sss;
			 	}
			 	
			   
			    }
			     
			 }}
		
		public class YLabel1 extends JPanel{
			 int cc;
			 @Override
			    public void paintComponent(Graphics g) {
				 System.gc();
			    super.paintComponent(g);
			    if(start==1)
			    {
			    	
			    	startyy=(int)(((1f-a4)/2f)*(pres_maxx-pres_minn)+pres_minn);
			    	endyy=(int)(((1f-a3)/2f)*(pres_maxx-pres_minn)+pres_minn);
			    	

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
		    		while(time_now<pres_maxx)
		    		{
		    			int tyer=(int)(time_now);
		    			int y=super.getHeight()-(int)(((float)(tyer-startyy)/(float)(endyy-startyy))*super.getHeight());
		    			if(time_now> pres_minn)
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
			    	
			    	startyyy=(float)(((1f-a44)/2f)*(depth_maxx-depth_minn)+depth_minn);
			    	endyyy=(float)(((1f-a33)/2f)*(depth_maxx-depth_minn)+depth_minn);
			    	

			    	float total_sample=depth_maxx-depth_minn;
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
		    		
		    		while(time_now<depth_maxx)
		    		{
		    			float tyer=(time_now);
		    			int y=super.getHeight()-(int)(((float)(tyer-startyyy)/(float)(endyyy-startyyy))*super.getHeight());
		    			if(time_now> depth_minn)
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
