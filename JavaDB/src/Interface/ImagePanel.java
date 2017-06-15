package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	private BufferedImage image;
	public ImagePanel(String path,Color background){
		this.setBackground(background);
		try{
			image = ImageIO.read(new File(path));
		}catch(IOException e){
			System.out.println("Image Not Found: "+ e.getMessage());
		}
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(image,0,0,null);
	}

}
