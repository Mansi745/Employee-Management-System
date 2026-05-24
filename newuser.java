import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;

class newuser implements MouseListener,FocusListener
{
JFrame fr;
JLabel l1,l2,lc,lg,l3;
JTextField t1,t3;
Border border1,border2;
JPasswordField t2;

	public newuser()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		fr = new JFrame();
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setLayout(null);		
		fr.setContentPane(new JLabel(new ImageIcon("images/newuser.png")));

		l1 = new JLabel("Enter UserName");
		l2 = new JLabel("Enter Password");
		l3 = new JLabel("Enter Email");
		l1.setFont(new Font("verdana",Font.BOLD,16));
		l2.setFont(new Font("verdana",Font.BOLD,16));
		l3.setFont(new Font("verdana",Font.BOLD,16));
		l1.setForeground(Color.red);
		l2.setForeground(Color.red);
		l3.setForeground(Color.red);
		l1.setBounds(60,80,150,30);		
		l2.setBounds(60,120,150,30);		
		l3.setBounds(60,160,150,30);		
		fr.add(l1);
		fr.add(l2);
		fr.add(l3);

        border1 = BorderFactory.createLineBorder(new Color(224,255,255),2);
		border2 = BorderFactory.createLineBorder(Color.black,2);


		t1 = new JTextField();
		t2 = new JPasswordField();
		t3 = new JTextField();
		t1.setFont(new Font("verdana",Font.BOLD,18));
		t2.setFont(new Font("verdana",Font.BOLD,18));
		t3.setFont(new Font("verdana",Font.BOLD,18));
		t1.setForeground(Color.black);
		t2.setForeground(Color.black);
		t3.setForeground(Color.black);
		t1.setBounds(220,80,215,30);		
		t2.setBounds(220,120,215,30);
		t3.setBounds(220,160,215,30);
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
        t1.addFocusListener(this);
		t2.addFocusListener(this);
        t3.addFocusListener(this);

		lc = new JLabel(new ImageIcon("images/cancel1.png"));
		lg = new JLabel(new ImageIcon("images/save1.png"));
		lc.setBounds(220,220,100,30);
		lg.setBounds(335,220,100,30);
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
			lg.setIcon(new ImageIcon("images/save2.png"));
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
			lg.setIcon(new ImageIcon("images/save1.png"));
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
				PreparedStatement ps = con.prepareStatement("insert into login_table value(?,?,?)");
				ps.setString(1,t1.getText());
				ps.setString(2,t2.getText());
				ps.setString(3,t3.getText());
				int z = ps.executeUpdate();
				if(z>0)
				{
					JOptionPane.showMessageDialog(fr,"Registered Successfully");
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Sorry! Error in Registering");
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
