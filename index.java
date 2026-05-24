import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class index implements Runnable,MouseListener  {
    JFrame fr,fc;
    JLabel lb1,lb2,lb3,lb4,lb5,lexit,l1,lb;
    Thread th = null;
    Boolean bn = true;
    
	public index(String un)
     {      
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fc = new JFrame();
		fr.setSize(dim.width,dim.height);
		fc.setSize(dim.width,dim.height);
		fr.setLayout(null);
		fr.setBackground(Color.gray);
		fr.setContentPane(new JLabel(new ImageIcon("images/index.png")));
		fc.setContentPane(new JLabel(new ImageIcon("images/opacity.png")));

		lb1 = new JLabel(new ImageIcon("images/insert1.png"));
		lb2 = new JLabel(new ImageIcon("images/search1.png"));
		lb3 = new JLabel(new ImageIcon("images/modify1.png"));
		lb4 = new JLabel(new ImageIcon("images/delete1.png"));
		lb5 = new JLabel(new ImageIcon("images/display1.png"));
		lb1.setBounds(80,130,125,130);
		lb2.setBounds(80,245,122,130);
		lb3.setBounds(80,375,122,130);
		lb4.setBounds(80,505,122,130);
		lb5.setBounds(80,635,122,130);
		lb1.addMouseListener(this);
		lb2.addMouseListener(this);
		lb3.addMouseListener(this);
		lb4.addMouseListener(this);
		lb5.addMouseListener(this);	
		lb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lb1);
		fr.add(lb2);
		fr.add(lb3);
		fr.add(lb4);
		fr.add(lb5);

		l1 = new JLabel();
		l1.setBounds(1250,720,230,25);
		l1.setForeground(Color.white);
		l1.setFont(new Font("verdana",Font.BOLD,17));
		fr.add(l1);

		lexit = new JLabel(new ImageIcon("images/exit1.png"));
		lexit.setBounds(dim.width-80,80,35,35);
		lexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lexit.addMouseListener(this);
		fr.add(lexit);
		
		th = new Thread(this);
		th.start();	

        fr.setUndecorated(true);
		fr.setVisible(true);
		fc.setUndecorated(true);

	
    }
	   public void run()
	   {
			while(bn){
				java.util.Date dt = new java.util.Date();
				l1.setText(dt.toString());
				try
			{
			th.sleep(1000);
			}
			catch(Exception e)
			{
			}
			}
	   }

	   public void mouseEntered(MouseEvent me)
	   {
		 JLabel lb = (JLabel)me.getComponent();
		 if(lb==lb1){
			lb1.setIcon(new ImageIcon("images/insert2.png"));
			lb1.setToolTipText("insert");
		 }
		 if(lb==lb2){
			lb2.setIcon(new ImageIcon("images/search2.png"));
			lb2.setToolTipText("search");
		 }
		 if(lb==lb3){
			lb3.setIcon(new ImageIcon("images/modify2.png"));
			lb3.setToolTipText("modify");
		 }
		 if(lb==lb4){
			lb4.setIcon(new ImageIcon("images/delete2.png"));
			lb4.setToolTipText("delete");
		 }
		 if(lb==lb5){
			lb5.setIcon(new ImageIcon("images/display2.png"));
			lb5.setToolTipText("display");
		 }
		 if(lb==lexit){
			lexit.setIcon(new ImageIcon("images/exit2.png"));
			lexit.setToolTipText("exit");
		 }
	   }

	   public void mouseExited(MouseEvent me)
	   {
		JLabel lb = (JLabel)me.getComponent();

		if(lb==lb1)
		{
			lb1.setIcon(new ImageIcon("images/insert1.png"));
		}
		if(lb==lb2){
			lb2.setIcon(new ImageIcon("images/search1.png"));
		 }
		 if(lb==lb3){
			lb3.setIcon(new ImageIcon("images/modify1.png"));
		 }
		 if(lb==lb4){
			lb4.setIcon(new ImageIcon("images/delete1.png"));
		 }
		 if(lb==lb5){
			lb5.setIcon(new ImageIcon("images/display1.png"));
		 }
		 if(lb==lexit){
			lexit.setIcon(new ImageIcon("images/exit1.png"));
		 }
	   }

	   public void mouseClicked(MouseEvent me)
	   {
           JLabel lb = (JLabel)me.getComponent();

		if(lb==lb1)
		{
			fr.setEnabled(false);
			fc.setOpacity(0.60f);
			fc.setVisible(true);
			fc.setEnabled(false);
			new insert(fr,fc);
		}
	
		if(lb==lb2)
		{
			fr.setEnabled(false);
			fc.setOpacity(0.60f);
			fc.setVisible(true);
			fc.setEnabled(false);
			new search(fr,fc);
		}
	
		if(lb==lb3)
		{
			fr.setEnabled(false);
			fc.setOpacity(0.60f);
			fc.setVisible(true);
			fc.setEnabled(false);
			new modify(fr,fc);
		}
	
		if(lb==lb4)
		{
			fr.setEnabled(false);
			fc.setOpacity(0.60f);
			fc.setVisible(true);
			fc.setEnabled(false);
			new delete(fr,fc);
		}

		if(lb==lb5)
		{
			fr.setEnabled(false);
			fc.setOpacity(0.60f);
			fc.setVisible(true);
			fc.setEnabled(false);
			new display(fr,fc);
		}

		if(lb==lexit)
		{
			bn=false;
			th=null;
			fc.dispose();
			fr.dispose();
		}
	   }

	   public void mousePressed(MouseEvent me){}
	   public void mouseReleased(MouseEvent me){}	
	   public static void main(String args[])
	    {
		new index("Mansi");
	    }
} 