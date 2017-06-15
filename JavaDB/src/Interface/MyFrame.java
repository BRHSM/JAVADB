package Interface;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame(){
		super(); 
		setBounds(100,100,350,600);
	}
	public MyFrame(String titolo){
		super(titolo);
		setBounds(100,100, 350,600);
	}
}

