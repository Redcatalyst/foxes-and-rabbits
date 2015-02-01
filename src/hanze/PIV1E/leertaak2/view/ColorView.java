package hanze.PIV1E.leertaak2.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class ColorView extends JPanel{
	private BufferedImage image;
	
	public ColorView() {
		try {
			image = ImageIO.read(new File("resources/colors.png"));
	    } catch (IOException ex) {}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
