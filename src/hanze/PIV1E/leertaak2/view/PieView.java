package hanze.PIV1E.leertaak2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;

import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.model.SimulationModel;

public class PieView extends AbstractView {

	public PieView(int width, int height, SimulationModel simulation) {
		super(simulation);
		
	}

	@Override
	public void showStatus(int step, Field field, FieldStats stats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(Class animalClass, Color color) {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.BLUE);
		
//		g.fillArc(10, 10, 180, 180, 0, aantal);
	}
	
	class GraphPanel extends JPanel
    {
        
    }
}
