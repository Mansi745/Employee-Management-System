import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border; 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.regex.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.io.*;

class modify implements ItemListener,ActionListener
{
String salary[]={"0","80000","60000","40000","35000","25000","15000"};
String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
String str[]={"Emp UID","Emp Name","Father Name","Gender","Date of Birth","House Address","Mobile Number","Current Email"};
JFrame fr,fx,fc;
JLabel lbl[],l1,l2,l3,l4,l5,l6,l7,lpic;
int x=80,y=90,x1=220,y1=90;
JTextField txt[],t6;
JRadioButton r1,r2;
ButtonGroup bg;
JComboBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9;
JButton b1,b2,b3;
Image originalimg,scaleimg;
String path,id;

	public modify(JFrame f1,JFrame f2)
	{	
		fx = f1;
		fc = f2;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		fr = new JFrame();
		fr.setBounds((dim.width-1050)/2,(dim.height-500)/2,900,550);
		fr.setLayout(null);		
		fr.setContentPane(new JLabel(new ImageIcon("images/modify.png")));

		lbl = new JLabel[8];//left side content
		for(int i=0 ; i<lbl.length ; i++)
		{
			lbl[i] = new JLabel(str[i]);
			lbl[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lbl[i].setFont(new Font("verdana",Font.PLAIN,16));
			lbl[i].setForeground(Color.gray);
			lbl[i].setBounds(x,y,150,30);
			fr.add(lbl[i]);
			y = y + 43;
		}

		Border border = BorderFactory.createLineBorder(Color.gray);
		
		txt = new JTextField[6];  //right side text field
		for(int i=0 ; i<txt.length ; i++)
		{
			txt[i] = new JTextField();
			txt[i].setFont(new Font("verdana",Font.PLAIN,16));
			txt[i].setForeground(Color.pink);
			txt[i].setBounds(x1,y1,190,30);
			txt[i].setOpaque(false);
			txt[i].setCaretColor(Color.pink);
			txt[i].setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1,5,1,1)));	
			fr.add(txt[i]);
			if(i==2)
			{
				y1 = y1 + 129;
			}
			else
			{
				y1 = y1 + 43;
			}
		}
		txt[0].setFont(new Font("verdana",Font.BOLD,18));		
		txt[0].setEditable(false);

		txt[4].addKeyListener(new KeyAdapter()
		{
   		public void keyTyped(KeyEvent e)
		{
			char c = e.getKeyChar();

      			if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) 
			{
         			e.consume();  // ignore event
      			}
			else if(txt[4].getText().length()>9)
			{
				txt[4].setText(txt[4].getText().substring(0,txt[4].getText().length()-1));
			}
		}
		});

		bg = new ButtonGroup();
		r1 = new JRadioButton("Male");
		r2 = new JRadioButton("Female");
		r1.setFont(new Font("verdana",Font.PLAIN,16));
		r2.setFont(new Font("verdana",Font.PLAIN,16));
		r1.setForeground(Color.gray);
		r2.setForeground(Color.gray);
		r1.setBounds(220,219,70,30);
		r2.setBounds(310,219,90,30);
		r1.setOpaque(false);
		r2.setOpaque(false);
		bg.add(r1);
		bg.add(r2);
		fr.add(r1);	
		fr.add(r2);	

		cb1 = new JComboBox();
		for(int i=1 ; i<=31 ; i++)
		{
			cb1.addItem(""+i);
		}
		cb1.setBounds(220,262,50,30);
		cb1.setFont(new Font("verdana",Font.PLAIN,12));
		fr.add(cb1);

		cb2 = new JComboBox();
		for(int i=0 ; i<12 ; i++)
		{
			cb2.addItem(month[i]);
		}
		cb2.setBounds(280,262,55,30);
		cb2.setFont(new Font("verdana",Font.PLAIN,12));
		fr.add(cb2);
	
		cb3 = new JComboBox();
		for(int i=1965 ; i<=1996 ; i++)
		{
			cb3.addItem(""+i);
		}
		cb3.setBounds(345,262,65,30);
		cb3.setFont(new Font("verdana",Font.PLAIN,12));
		fr.add(cb3);

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
		cb9.addItemListener(this);
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
		
		b1 = new JButton("Srch",new ImageIcon("images/search4.png"));
		b1.setBounds(80,477,100,30);
		b1.addActionListener(this);
		fr.add(b1);

		b2 = new JButton("Modify",new ImageIcon("images/modify4.png"));
		b2.setBounds(190,477,100,30);
		b2.addActionListener(this);
		fr.add(b2);

		b3 = new JButton("Close",new ImageIcon("images/cross4.png"));
		b3.setBounds(300,477,100,30);
		b3.addActionListener(this);
		fr.add(b3);

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{
				String srch = JOptionPane.showInputDialog(fr,"Enter Your ID");
				if(srch!=null)
				{
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
						String gen = rs.getString(4);
						if(gen.equals("male")) 
						{
							r1.getModel().setSelected(true);
						}
						else
						{
							r2.getModel().setSelected(true);
						} 

						String dt[] = rs.getString(5).split("-");
						
						cb1.setSelectedIndex(Integer.parseInt(dt[2])-1);
						cb2.setSelectedIndex(Integer.parseInt(dt[1])-1);
						cb3.setSelectedItem(dt[0]);

						txt[3].setText(rs.getString(6));
						txt[4].setText(rs.getString(7));
						txt[5].setText(rs.getString(8));

						cb4.setSelectedItem(rs.getString(9));
						cb5.setSelectedItem(rs.getString(10));
						cb6.setSelectedItem(rs.getString(11));
						cb7.setSelectedItem(rs.getString(12));
						cb8.setSelectedItem(rs.getString(13));
						cb9.setSelectedItem(rs.getString(14));
						t6.setText(rs.getString(15));	
						originalimg = Toolkit.getDefaultToolkit().getImage("photo/"+id+".jpg");
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
			catch(Exception e)
			{
				System.out.println(e);
			}	
		}

		if(ae.getSource()==b2)
		{
			if(txt[0].getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(fr,"Error! ID can't be leave blank");
			}
			else if(txt[1].getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(fr,"Error! Employee's Name can't be leave blank");
			}
			else if(txt[2].getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(fr,"Error! Father's Name can't be leave blank");
			}
			else if(!(r1.isSelected() || r2.isSelected()))
			{
				JOptionPane.showMessageDialog(fr,"Error! Gender can't be leave blank");
			}
			else if(txt[3].getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(fr,"Error! Address can't be leave blank");
			}
			else if(txt[4].getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(fr,"Error! Mobile can't be leave blank");
			}
			else if(txt[5].getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(fr,"Error! Email can't be leave blank");
			}
			else if(((String)cb4.getSelectedItem()).equals("Select"))
			{
				JOptionPane.showMessageDialog(fr,"Error! Please Select High School");
			}
			else if(((String)cb5.getSelectedItem()).equals("Select"))
			{
				JOptionPane.showMessageDialog(fr,"Error! Please Select Intermediate");
			}
			else if(((String)cb9.getSelectedItem()).equals("Select"))
			{
				JOptionPane.showMessageDialog(fr,"Error! Please Select Designation");
			}
			else if(txt[5].getText().length()>0)
			{
				Pattern pattern = Pattern.compile("([a-zA-Z0-9_\\-\\.]+)@((\\[a-z]{1,3}\\.[a-z]" + "{1,3}\\.[a-z]{1,3}\\.)|(([a-zA-Z\\-]+\\.)+))" + "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)",Pattern.MULTILINE);
      				Matcher m = pattern.matcher(txt[5].getText());
      				boolean b = m.matches();
				if(b!=true)
        			{
					JOptionPane.showMessageDialog(fr,"Invalid Email Address");
	        		}
				else
				{
					try
					{
						String gen = "";
						if(r1.isSelected())
						{
							gen = "male";
						}
						if(r2.isSelected())
						{
							gen = "female";
						}

						SimpleDateFormat format = new SimpleDateFormat("yyyy/MMM/dd");
						String str = (String)cb3.getSelectedItem()+"/"+cb2.getSelectedItem()+"/"+cb1.getSelectedItem();
						java.util.Date langDate=null;
						java.sql.Date sqlDate=null;
    						try
						{
						langDate = format.parse(str);
    						sqlDate = new java.sql.Date(langDate.getTime());
						}
						catch(ParseException pe)
						{
							System.out.println(pe);
						}
						Connection con = dao.createconnection();
						PreparedStatement ps = con.prepareStatement("update emp_record set emp_name=?,emp_father=?,gender=?,dob=?,address=?,mobile=?,email=?,high_school=?,intermediate=?,graduation=?,post_graduation=?,experience=?,designation=?,salary=? where uid=?");
						ps.setString(1,txt[1].getText());
						ps.setString(2,txt[2].getText());
						ps.setString(3,gen);
						ps.setDate(4,sqlDate);
						ps.setString(5,txt[3].getText());
						ps.setString(6,txt[4].getText());
						ps.setString(7,txt[5].getText());
						ps.setString(8,(String)cb4.getSelectedItem());
						ps.setString(9,(String)cb5.getSelectedItem());
						ps.setString(10,(String)cb6.getSelectedItem());
						ps.setString(11,(String)cb7.getSelectedItem());
						ps.setString(12,(String)cb8.getSelectedItem());
						ps.setString(13,(String)cb9.getSelectedItem());
						ps.setString(14,t6.getText());
						ps.setString(15,txt[0].getText());
						int z = ps.executeUpdate();
						if(z>0)
						{
							JOptionPane.showMessageDialog(fr,"Record Updated Successfully.....");
						}
						else
						{
							JOptionPane.showMessageDialog(fr,"Record Not Updated.....");
	        			}
						con.close();
					}
					catch(Exception e)
					{
						System.out.println(e);	
					}
				}	
			}
		}

		if(ae.getSource()==b3)
		{
			fc.setVisible(false);
			fx.setEnabled(true);
			fr.dispose();
		}
	}

	public void itemStateChanged(ItemEvent ie)
	{
		int z = cb9.getSelectedIndex();
		t6.setText(""+salary[z]);
	}

	public void quitsplash()
	{
		fr.dispose();
	}
}
