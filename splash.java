import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class splash
{
JFrame fr;

	public splash()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		fr = new JFrame();
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setLayout(null);		
		fr.setContentPane(new JLabel(new ImageIcon("images/splash.png")));

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void quitsplash()
	{
		fr.dispose();
	}

	public static void main(String args[])
	{
		splash sp = new splash();
		try
		{
			Thread.sleep(3000);
		}
		catch(Exception e)
		{ System.out.println(e);
		}
		sp.quitsplash();
		new login();
	}
}
