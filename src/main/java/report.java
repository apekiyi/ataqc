import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class report {

	public String folder;
	public String file;
	public String project_name;
	public String file_name;
	
	
	public void Start() throws IOException
	{
		file_name="PROJECTS/"+project_name+"/"+folder+"/"+file;
		if(file.equals("nfh.report"))
		{
			nfh();
		}
		if(file.equals("shot.report"))
		{
			shot();
		}
		
		Process proc= Runtime.getRuntime().exec("eog "+file_name);
		
	}
	
	public void shot() throws IOException
	{
		JFrame f=new JFrame();
		f.setBounds(100, 100, 1500, 450);
		f.setVisible(true);
		f.setLayout(null);
		RandomAccessFile raf=new RandomAccessFile(file_name,"r");
		String proj_name=raf.readUTF();
		String seq_no=raf.readUTF();
		String stime=raf.readUTF();
		String etime=raf.readUTF();
		f.setTitle(file_name);
		int fsp=raf.readInt();	
		int lsp=raf.readInt();
		int total_shot=Math.abs(fsp-lsp)+1;
		int gs=raf.readInt();
		int ms=raf.readInt();
		int ce=raf.readInt();
		
		JPanel P1=new JPanel();
		P1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P1.setBounds(10, 10, 540, 400);
		P1.setLayout(null);
		f.add(P1);
		
		JPanel P2=new JPanel();
		P2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P2.setBounds(560, 10, 900, 400);
		P2.setLayout(null);
		f.add(P2);
		
		String[] S={"Project Name","Sequence Number","Start Time","End Time","First Shot Point","Last Shot Point","Number of Shot Points","Number of Good Shot"
				,"Number of Missing Shots","Number of Navigation Header Error"};
		int l=S.length;
		JLabel[] L=new JLabel[l];
		JLabel[] L1=new JLabel[l];
		for(int i=0;i<l;i++)
		{
			L[i]=new JLabel(S[i]);
			L[i].setBounds(10, 40*i, 250, 30);
			L1[i]=new JLabel(S[i]);
			L1[i].setBounds(280, 40*i, 200, 30);
			P1.add(L[i]);
			P1.add(L1[i]);
		}
		
		L1[0].setText(proj_name);
		L1[1].setText(seq_no);
		L1[2].setText(stime);
		L1[3].setText(etime);
		L1[4].setText(String.valueOf(fsp));
		L1[5].setText(String.valueOf(lsp));
		L1[6].setText(String.valueOf(total_shot));
		L1[7].setText(String.valueOf(gs));
		L1[8].setText(String.valueOf(ms));
		L1[9].setText(String.valueOf(ce));
		
		//
		final String[] S1;
		final String[] S2;
		final String[] S3;
		final String[] S4;
		S1=new String[gs];
		S2=new String[ms];
		S3=new String[ce];
		S4=new String[gs+ms+ce];
		
		for(int i=0;i<gs;i++)
		{
			S1[i]=raf.readUTF();
			S4[i]=S1[i];
		}
		for(int i=0;i<ms;i++)
		{
			S2[i]=raf.readUTF();
			S4[gs+i]=S2[i];
		}
		for(int i=0;i<ce;i++)
		{
			S3[i]=raf.readUTF();
			S4[gs+ms+i]=S3[i];
		}
		
	
	    final DefaultTableModel tm=new DefaultTableModel(){
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		};
		tm.addColumn("No");tm.addColumn("Status");
		JTable JT=new JTable();
		JT.setModel(tm);
		JScrollPane SP=new JScrollPane(JT);
		SP.setBounds(10,50,P2.getWidth()-20,P2.getHeight()-70);
		JT.getColumnModel().getColumn(0).setPreferredWidth(100);
		JT.getColumnModel().getColumn(1).setPreferredWidth(SP.getWidth()-100);
		P2.add(SP);
		
		//
		 for(int i=0;i<S4.length;i++)
	        {
	        	Object[] o=new Object[2];
	        	o[0]=i+1;
	        	o[1]=S4[i];
	        	tm.addRow(o);
	        }
		
		//Buttons
		JButton lb1,lb2,lb3,lb4;
				lb1=new JButton("All");
				lb2=new JButton("OK");lb2.setBackground(Color.green);
				lb3=new JButton("N/A");lb3.setBackground(Color.red);
				lb4=new JButton("NO NAV");lb4.setBackground(Color.yellow);
				
				lb1.setBounds(10, 10, 100, 30);
				lb2.setBounds(120, 10, 100, 30);
				lb3.setBounds(230, 10, 100, 30);
				lb4.setBounds(340, 10, 100, 30);
				P2.add(lb1);P2.add(lb2);P2.add(lb3);P2.add(lb4);
				
				lb1.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S4.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S4[i];
			        	tm.addRow(o);
			        }
			        }
				});
				
				lb2.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S1.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S1[i];
			        	tm.addRow(o);
			        }
			        }
				});
				
				lb3.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S2.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S2[i];
			        	tm.addRow(o);
			        }
			        }
				});
				
				lb4.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S3.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S3[i];
			        	tm.addRow(o);
			        }
			        }
				});
		
		f.setResizable(false);		
		raf.close();
		
	}
	
	public void nfh() throws IOException
	{
		JFrame f=new JFrame();
		f.setBounds(100, 100, 1500, 450);
		f.setVisible(true);
		f.setLayout(null);
		RandomAccessFile raf=new RandomAccessFile(file_name,"r");
		String proj_name=raf.readUTF();
		String seq_no=raf.readUTF();
		String stime=raf.readUTF();
		String etime=raf.readUTF();
		f.setTitle(file_name);
		int fsp=raf.readInt();	
		int lsp=raf.readInt();
		int total_shot=Math.abs(fsp-lsp)+1;
		int gs=raf.readInt();
		int ms=raf.readInt();
		int ce=raf.readInt();
		
		JPanel P1=new JPanel();
		P1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P1.setBounds(10, 10, 540, 400);
		P1.setLayout(null);
		f.add(P1);
		
		JPanel P2=new JPanel();
		P2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		P2.setBounds(560, 10, 900, 400);
		P2.setLayout(null);
		f.add(P2);
		
		String[] S={"Project Name","Sequence Number","Start Time","End Time","First Shot Point","Last Shot Point","Number of Shot Points","Number of Good Shot"
				,"Number of Missing Shots","Number of Communication Error"};
		int l=S.length;
		JLabel[] L=new JLabel[l];
		JLabel[] L1=new JLabel[l];
		for(int i=0;i<l;i++)
		{
			L[i]=new JLabel(S[i]);
			L[i].setBounds(10, 40*i, 250, 30);
			L1[i]=new JLabel(S[i]);
			L1[i].setBounds(280, 40*i, 200, 30);
			P1.add(L[i]);
			P1.add(L1[i]);
		}
		
		L1[0].setText(proj_name);
		L1[1].setText(seq_no);
		L1[2].setText(stime);
		L1[3].setText(etime);
		L1[4].setText(String.valueOf(fsp));
		L1[5].setText(String.valueOf(lsp));
		L1[6].setText(String.valueOf(total_shot));
		L1[7].setText(String.valueOf(gs));
		L1[8].setText(String.valueOf(ms));
		L1[9].setText(String.valueOf(ce));
		
		//
		final String[] S1;
		final String[] S2;
		final String[] S3;
		final String[] S4;
		S1=new String[gs];
		S2=new String[ms];
		S3=new String[ce];
		S4=new String[gs+ms+ce];
		
		for(int i=0;i<gs;i++)
		{
			S1[i]=raf.readUTF();
			S4[i]=S1[i];
		}
		for(int i=0;i<ms;i++)
		{
			S2[i]=raf.readUTF();
			S4[gs+i]=S2[i];
		}
		for(int i=0;i<ce;i++)
		{
			S3[i]=raf.readUTF();
			S4[gs+ms+i]=S3[i];
		}
		
	
	    final DefaultTableModel tm=new DefaultTableModel(){
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		};
		tm.addColumn("No");tm.addColumn("Status");
		JTable JT=new JTable();
		JT.setModel(tm);
		JScrollPane SP=new JScrollPane(JT);
		SP.setBounds(10,50,P2.getWidth()-20,P2.getHeight()-70);
		JT.getColumnModel().getColumn(0).setPreferredWidth(100);
		JT.getColumnModel().getColumn(1).setPreferredWidth(SP.getWidth()-100);
		P2.add(SP);
		
		//
		 for(int i=0;i<S4.length;i++)
	        {
	        	Object[] o=new Object[2];
	        	o[0]=i+1;
	        	o[1]=S4[i];
	        	tm.addRow(o);
	        }
		
		//Buttons
		JButton lb1,lb2,lb3,lb4;
				lb1=new JButton("All");
				lb2=new JButton("OK");lb2.setBackground(Color.green);
				lb3=new JButton("N/A");lb3.setBackground(Color.red);
				lb4=new JButton("COMM");lb4.setBackground(Color.yellow);
				
				lb1.setBounds(10, 10, 100, 30);
				lb2.setBounds(120, 10, 100, 30);
				lb3.setBounds(230, 10, 100, 30);
				lb4.setBounds(340, 10, 100, 30);
				P2.add(lb1);P2.add(lb2);P2.add(lb3);P2.add(lb4);
				
				lb1.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S4.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S4[i];
			        	tm.addRow(o);
			        }
			        }
				});
				
				lb2.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S1.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S1[i];
			        	tm.addRow(o);
			        }
			        }
				});
				
				lb3.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S2.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S2[i];
			        	tm.addRow(o);
			        }
			        }
				});
				
				lb4.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	tm.setRowCount(0);
			        for(int i=0;i<S3.length;i++)
			        {
			        	Object[] o=new Object[2];
			        	o[0]=i+1;
			        	o[1]=S3[i];
			        	tm.addRow(o);
			        }
			        }
				});
		
		f.setResizable(false);		
		raf.close();
		
		
	}
	
}
