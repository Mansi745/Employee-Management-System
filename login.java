import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;

class login implements MouseListener,FocusListener
{
JFrame fr;
JLabel l1,l2,l3,l4,lc,lg;
JTextField t1;
JPasswordField t2;
Border border1,border2;

	public login()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		fr = new JFrame();
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setLayout(null);		
		fr.setContentPane(new JLabel(new ImageIcon("images/login.png")));

		l1 = new JLabel("UserName  : ");
		l2 = new JLabel("Password   : ");
		l1.setFont(new Font("verdana",Font.BOLD,16));
		l2.setFont(new Font("verdana",Font.BOLD,16));
		l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		l1.setBounds(70,100,150,30);		
		l2.setBounds(70,140,150,30);		
		fr.add(l1);
		fr.add(l2);

		border1 = BorderFactory.createLineBorder(new Color(224,255,255),2);
		border2 = BorderFactory.createLineBorder(Color.black,2);

		t1 = new JTextField();
		t2 = new JPasswordField();
		t1.setFont(new Font("verdana",Font.BOLD,18));
		t2.setFont(new Font("verdana",Font.BOLD,18));
		t1.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,5,0,0)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,0,0)));
		t1.setForeground(Color.BLACK);
		t2.setForeground(Color.BLACK);
		t1.setBounds(210,100,215,30);		
		t2.setBounds(210,140,215,30);
		t1.setOpaque(false);
		t2.setOpaque(false);
		fr.add(t1);
		fr.add(t2);
		t1.addFocusListener(this);
		t2.addFocusListener(this);

		l3 = new JLabel("<html><u>New User<u></html>");
		l4 = new JLabel("<html><u>Forget Password?<u></html>");
		l3.setFont(new Font("verdana",Font.BOLD,11));
		l4.setFont(new Font("verdana",Font.BOLD,11));
		l3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l3.setForeground(Color.red);
		l4.setForeground(Color.red);
		l3.setBounds(70,200,80,20);		
		l4.setBounds(70,225,120,20);
		l3.addMouseListener(this);
		l4.addMouseListener(this);
		fr.add(l3);
		fr.add(l4);

		lc = new JLabel(new ImageIcon("images/cancel1.png"));
		lg = new JLabel(new ImageIcon("images/login1.png"));;
		lc.setBounds(250,225,100,27);
		lg.setBounds(360,225,100,27);
		lc.setBackground(new Color(214, 211, 209));
		lg.setBackground(new Color(66, 73, 73));
		lc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lc.addMouseListener(this);
		lg.addMouseListener(this);
		fr.add(lc);		
		fr.add(lg);		

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void focusGained(FocusEvent fe)
	{
		JTextField txt = (JTextField)fe.getComponent();
		txt.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,5,0,0)));	
	}
	public void focusLost(FocusEvent fe)
	{
		JTextField txt = (JTextField)fe.getComponent();
		txt.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,0,0)));	
	}

	public void mouseEntered(MouseEvent me)
	{
		JLabel lb = (JLabel)me.getComponent();
		if(lb==lc)
		{
			lc.setIcon(new ImageIcon("images/cancel2.png"));
		}
		if(lb==lg)
		{
			lg.setIcon(new ImageIcon("images/login2.png"));
		}
	}
	public void mouseExited(MouseEvent me)
	{
		JLabel lb = (JLabel)me.getComponent();
		if(lb==lc)
		{
			lc.setIcon(new ImageIcon("images/cancel1.png"));
		}
		if(lb==lg)
		{
			lg.setIcon(new ImageIcon("images/login1.png"));
		}
	}
	public void mouseClicked(MouseEvent me)
	{
		JLabel lb = (JLabel)me.getComponent();
		
		if(lb==l3)
		{
			new newuser();
		}

		if(lb==l4)
		{
			new forget();
		}
       
		JLabel bb = (JLabel)me.getComponent();

		if(bb==lc)
		{
			fr.dispose();
		}
		if(bb==lg)
		{
			try
			{
				Connection con = dao.createconnection();	
				PreparedStatement ps = con.prepareStatement("select * from login_table where username=? and password=?");
				ps.setString(1,t1.getText());
				ps.setString(2,t2.getText());
				ResultSet rs = ps.executeQuery();
				if(rs.next()==true)
				{
					JOptionPane.showMessageDialog(fr,"Login Successfully");
					fr.dispose();
					new index(t1.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Sorry! UserName or Password is Invalid");
				}
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}

	public static void main(String args[])
	{
		new login();
	}
}
