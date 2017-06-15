package Logics;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class init {
	public static final String VERSION = "1.0";
	public static JPanel panel;
	public static void main(String[] args){
		try {
			Globals.initializeGlobals();
			JFrame f = new Interface.MyFrame("MySystem");
			Container c = f.getContentPane(); 
			panel = new Interface.MyPanel(); 
			f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			c.add(panel); 
			f.show();
		} catch (ClassNotFoundException | SQLException e) {
			//no connection
		}
		 
	}
}

