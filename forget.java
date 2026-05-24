import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;

class forget implements MouseListener,FocusListener
{
JFrame fr;
JLabel l1,lc,lg,l3;
JTextField t1,t3;
Border border1,border2;

	public forget()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		fr = new JFrame();
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setLayout(null);		
		fr.setContentPane(new JLabel(new ImageIcon("images/forget.png")));

		l1 = new JLabel("Enter UserName");
		l3 = new JLabel("Enter Email");
		l1.setFont(new Font("verdana",Font.BOLD,16));
		l3.setFont(new Font("verdana",Font.BOLD,16));
		l1.setForeground(Color.cyan);
		l3.setForeground(Color.cyan);
		l1.setBounds(60,100,150,30);		
		l3.setBounds(60,150,150,30);		
		fr.add(l1);
		fr.add(l3);

		border1 = BorderFactory.createLineBorder(new Color(224,255,255),2);
		border2 = BorderFactory.createLineBorder(Color.black,2);


		t1 = new JTextField();
		t3 = new JTextField();
		t1.setFont(new Font("verdana",Font.BOLD,18));
		t3.setFont(new Font("verdana",Font.BOLD,18));
		t1.setForeground(Color.black);
		t3.setForeground(Color.black);
		t1.setBounds(220,100,215,30);		
		t3.setBounds(220,150,215,30);
		t1.setOpaque(false);
		t3.setOpaque(false);
		fr.add(t1);
		fr.add(t3);
		t1.addFocusListener(this);
		t3.addFocusListener(this);

		lc = new JLabel(new ImageIcon("images/cancel1.png"));
		lg = new JLabel(new ImageIcon("images/submit1.png"));
		lc.setBounds(220,210,100,30);
		lg.setBounds(335,210,100,30);
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
			lg.setIcon(new ImageIcon("images/submit2.png"));
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
			lg.setIcon(new ImageIcon("images/submit1.png"));
		}
	}
	public void mouseClicked(MouseEvent me)
	{
		JLabel lb = (JLabel)me.getComponent();
		if(lb==lc)
		{
			fr.dispose();
		}
		if(lb==lg)
		{
			try
			{
				Connection con = dao.createconnection();	
				PreparedStatement ps = con.prepareStatement("select * from login_table where username=? and email=?");
				ps.setString(1,t1.getText());
				ps.setString(2,t3.getText());
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(fr,"Your Password is :- " + rs.getString("password"));
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Sorry! UserName or Email is Invalid");
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
}
