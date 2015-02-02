package hanze.PIV1E.leertaak2.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

/**
 * Puts a image on a JPanel.
 * @author Frank
 */
public class ColorView extends JPanel{
	private BufferedImage image;
	
	/**
	 * Creates a ColorView instance and loads the image.
	 */
	public ColorView() {
		try {
			image = ImageIO.read(new File("resources/colors.png"));
	    } catch (IOException ex) {}
	}
	
	/**
	 * Displays the image on the component.
	 */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
