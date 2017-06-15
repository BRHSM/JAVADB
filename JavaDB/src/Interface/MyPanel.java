package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MyPanel extends JPanel{

	private JPanel browser = new browserPanel();
	public  JPanel action = new ActionPanel();
	
	public MyPanel(){
		this.setLayout(new BorderLayout());
		this.add(browser,BorderLayout.NORTH);
		this.add(action,BorderLayout.CENTER);		
	}	
}
