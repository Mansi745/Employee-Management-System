import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border; 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.sql.*;

class search implements ActionListener
{
String str[]={"Emp Unique ID","Emp Name","Father Name","Gender","Date of Birth","House Address","Mobile Number","Current Email"};
JFrame fr,fx,fc;
JLabel lbl[],l1,l2,l3,l4,l5,l6,l7,lpic;
int x=80,y=90,x1=220,y1=90;
JTextField txt[],t6;
ButtonGroup bg;
JComboBox cb4,cb5,cb6,cb7,cb8,cb9;
JButton b1,b2,b3;
Image originalimg,scaleimg;
String path,id;
int p=0;

	public search(JFrame f1,JFrame f2)
	{	
		fx = f1;
		fc = f2;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		fr = new JFrame();
		fr.setBounds((dim.width-1050)/2,(dim.height-500)/2,900,550);
		fr.setLayout(null);		
		fr.setContentPane(new JLabel(new ImageIcon("images/search.png")));

		lbl = new JLabel[8];
		for(int i=0 ; i<lbl.length ; i++)
		{
			lbl[i] = new JLabel(str[i]);
			lbl[i].setFont(new Font("verdana",Font.PLAIN,16));
			lbl[i].setForeground(Color.gray);
			lbl[i].setBounds(x,y,150,30);
			fr.add(lbl[i]);
			y = y + 43;
		}

		Border border = BorderFactory.createLineBorder(Color.gray);
		
		txt = new JTextField[8];
		for(int i=0 ; i<txt.length ; i++)
		{
			txt[i] = new JTextField();
			txt[i].setEditable(false);
			txt[i].setFont(new Font("verdana",Font.PLAIN,16));
			txt[i].setForeground(Color.pink);
			txt[i].setBounds(x1,y1,190,30);
			txt[i].setOpaque(false);
			txt[i].setCaretColor(Color.pink);
			txt[i].setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
			fr.add(txt[i]);
			y1 = y1 + 43;
		}
		
		l1 = new JLabel("High School");
		l1.setFont(new Font("verdana",Font.PLAIN,16));
		l1.setForeground(Color.gray);
		l1.setBounds(505,90,150,30);
		fr.add(l1);	

		l2 = new JLabel("Intermediate");
		l2.setFont(new Font("verdana",Font.PLAIN,16));
		l2.setForeground(Color.gray);
		l2.setBounds(505,133,150,30);
		fr.add(l2);	

		l3 = new JLabel("Graduation");
		l3.setFont(new Font("verdana",Font.PLAIN,16));
		l3.setForeground(Color.gray);
		l3.setBounds(505,176,150,30);
		fr.add(l3);	
		
		l4 = new JLabel("Post Graduation");
		l4.setFont(new Font("verdana",Font.PLAIN,16));
		l4.setForeground(Color.gray);
		l4.setBounds(505,219,150,30);
		fr.add(l4);	

		l5 = new JLabel("Experience");
		l5.setFont(new Font("verdana",Font.PLAIN,16));
		l5.setForeground(Color.gray);
		l5.setBounds(505,262,150,30);
		fr.add(l5);	

		l6 = new JLabel("Designation");
		l6.setFont(new Font("verdana",Font.PLAIN,16));
		l6.setForeground(Color.gray);
		l6.setBounds(505,305,150,30);
		fr.add(l6);	

		l7 = new JLabel("Salary");
		l7.setFont(new Font("verdana",Font.PLAIN,16));
		l7.setForeground(Color.gray);
		l7.setBounds(505,348,150,30);
		fr.add(l7);	

		cb4 = new JComboBox();
		cb4.addItem("Select");
		cb4.addItem("UP Board");
		cb4.addItem("CBSE Board");
		cb4.addItem("ICSE Board");
		cb4.addItem("Other Board");
		cb4.setFont(new Font("verdana",Font.PLAIN,14));
		cb4.setBounds(650,90,150,30);
		fr.add(cb4);

		cb5 = new JComboBox();
		cb5.addItem("Select");
		cb5.addItem("UP Board");
		cb5.addItem("CBSE Board");
		cb5.addItem("ICSE Board");
		cb5.addItem("Other Board");
		cb5.setFont(new Font("verdana",Font.PLAIN,14));
		cb5.setBounds(650,133,150,30);
		fr.add(cb5);

		cb6 = new JComboBox();
		cb6.addItem("Select");
		cb6.addItem("BA");
		cb6.addItem("B.Com");
		cb6.addItem("B.Sc");
		cb6.addItem("BBA");
		cb6.addItem("BCA");
		cb6.addItem("B.Tech");
		cb6.setFont(new Font("verdana",Font.PLAIN,14));
		cb6.setBounds(650,176,150,30);
		fr.add(cb6);

		cb7 = new JComboBox();
		cb7.addItem("Select");
		cb7.addItem("MA");
		cb7.addItem("M.Com");
		cb7.addItem("M.Sc");
		cb7.addItem("MBA");
		cb7.addItem("MCA");
		cb7.addItem("M.Tech");
		cb7.setFont(new Font("verdana",Font.PLAIN,14));
		cb7.setBounds(650,219,150,30);
		fr.add(cb7);

		cb8 = new JComboBox();
		cb8.addItem("None");
		cb8.addItem("1");
		cb8.addItem("2");
		cb8.addItem("3");
		cb8.addItem("4");
		cb8.addItem("5");
		cb8.addItem("More Than 5");
		cb8.setFont(new Font("verdana",Font.PLAIN,14));
		cb8.setBounds(650,262,150,30);
		fr.add(cb8);

		cb9 = new JComboBox();
		cb9.addItem("Select");
		cb9.addItem("Director");
		cb9.addItem("Manager");
		cb9.addItem("Asst. Manager");
		cb9.addItem("Clerk");
		cb9.addItem("Supervisior");
		cb9.addItem("Peon");
		cb9.setFont(new Font("verdana",Font.PLAIN,14));
		cb9.setBounds(650,305,150,30);
		fr.add(cb9);

		t6 = new JTextField();
		t6.setFont(new Font("verdana",Font.PLAIN,16));
		t6.setForeground(Color.pink);
		t6.setBounds(650,348,150,30);
		t6.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t6.setOpaque(false);
		fr.add(t6);	

		lpic = new JLabel(new ImageIcon("images/photo.png"));
		lpic.setBounds(700,390,101,101);	
		fr.add(lpic);
		
		b1 = new JButton("Search",new ImageIcon("images/search4.png"));
		b1.setBounds(80,477,105,30);
		b1.addActionListener(this);
		fr.add(b1);

		b2 = new JButton("Reset",new ImageIcon("images/restart4.png"));
		b2.setBounds(195,477,100,30);
		b2.addActionListener(this);
		fr.add(b2);

		b3 = new JButton("Close",new ImageIcon("images/cross4.png"));
		b3.setBounds(305,477,100,30);
		b3.addActionListener(this);
		fr.add(b3);

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
            try{
            String srch = JOptionPane.showInputDialog(fr,"Enter your id");
            if(srch!=null){
                Connection con = dao.createconnection();
                PreparedStatement ps = con.prepareStatement("select * from emp_record where uid=?");
                ps.setString(1,srch);
					ResultSet rs = ps.executeQuery();
					if(rs.next()==true)
					{
						id = rs.getString(1).substring(4);
						txt[0].setText(rs.getString(1));
						txt[1].setText(rs.getString(2));
						txt[2].setText(rs.getString(3));
						txt[3].setText(rs.getString(4));
						txt[4].setText(rs.getString(5));
						txt[5].setText(rs.getString(6));
						txt[6].setText(rs.getString(7));
						txt[7].setText(rs.getString(8));
						cb4.setSelectedItem(rs.getString(9));
						cb5.setSelectedItem(rs.getString(10));
						cb6.setSelectedItem(rs.getString(11));
						cb7.setSelectedItem(rs.getString(12));
						cb8.setSelectedItem(rs.getString(13));
						cb9.setSelectedItem(rs.getString(14));
						t6.setText(rs.getString(15));	
						originalimg = Toolkit.getDefaultToolkit().getImage("photo/"+id+".png");
						scaleimg = originalimg.getScaledInstance(100,100,Image.SCALE_DEFAULT);	
						lpic.setIcon(new ImageIcon(scaleimg));
					}
					else
					{
						JOptionPane.showMessageDialog(fr,"Invalid Employee ID");
					}
					con.close();
            }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
if(ae.getSource()==b2)
		{
			txt[0].setText("");
			txt[1].setText("");
			txt[2].setText("");
			txt[3].setText("");
			txt[4].setText("");
			txt[5].setText("");
			txt[6].setText("");
			txt[7].setText("");
			t6.setText("");
			cb4.setSelectedIndex(0);
			cb5.setSelectedIndex(0);
			cb6.setSelectedIndex(0);
			cb7.setSelectedIndex(0);
			cb8.setSelectedIndex(0);
			cb9.setSelectedIndex(0);
			lpic.setIcon(new ImageIcon("images/photo.png"));
		}

		if(ae.getSource()==b3)
		{
			fc.setVisible(false);
			fx.setEnabled(true);
			fr.dispose();
		}
	}

	public void quitsplash()
	{
		fr.dispose();
	}
}

