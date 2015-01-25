package hanze.PIV1E.leertaak2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import hanze.PIV1E.leertaak2.location.Field;
import hanze.PIV1E.leertaak2.model.SimulationModel;
import hanze.PIV1E.leertaak2.view.GraphView.GraphPanel;

public class ChartView extends AbstractView {

	private static ChartPanel chart;
	
	// The classes being tracked by this view
    private Set<Class> classes;
    // A map for storing colors for participants in the simulation
    private Map<Class, Color> colors;
	
	public ChartView(int width, int height, SimulationModel simulation) {
		super(simulation);
		classes = new HashSet<Class>();
		colors = new HashMap<Class, Color>();
        chart = new ChartPanel(width, height);
        chart.newRun();
        add(chart);
	}

	@Override
	public void showStatus(int step, Field field, FieldStats stats) {
		if(step == 0){
        	chart.newRun();
        } else {
        	chart.update(step, field, stats);
        }		
	}

	@Override
	public void setColor(Class animalClass, Color color) {
		colors.put(animalClass, color);
        classes = colors.keySet();		
	}
	
	class ChartPanel extends JPanel
    {
        // An internal image buffer that is used for painting. For
        // actual display, this image buffer is then copied to screen.
        private BufferedImage chartImage;
		
		/**
         * Create a new, empty GraphPanel.
         */
        public ChartPanel(int width, int height)
        {
        	chartImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            clearImage();
        }
        
        /**
         * Indicate a new simulation run on this panel.
         */
        public void newRun()
        {
            clearImage();
            repaint();
        }
        
        /**
         * Display a new point of data.
         */
        public void update(int step, Field field, FieldStats stats)
        {
            if (classes.size() >= 2) {
                stats.reset();
                
                Graphics g = chartImage.getGraphics();

                int height = chartImage.getHeight();
                int width = chartImage.getWidth();

                g.setColor(this.getBackground());
    			g.fillRect(0, 0, width, height);
    			
    			int total = 0;    			
    			Iterator<Class> it = classes.iterator();
                while (it.hasNext()) {
                	Class class1 = it.next();
                	total += stats.getPopulationCount(field, class1);
                }
    			
                it = classes.iterator();
                int x = 10;
                while (it.hasNext()) {
                	Class class1 = it.next();
                	int count = stats.getPopulationCount(field, class1);
                	g.setColor(colors.get(class1));
                	double value = ((double)height / (double)total) * (double)count;
                	Long l = Math.round(value);
                	int newValue = Integer.valueOf(l.intValue());
                	g.fillRect(x, height - newValue - 1, 20 , newValue);
                	x += 30 ;
                }
                
                repaintNow();
            }
        }

        /**
         * Cause immediate update of the panel.
         */
        public void repaintNow()
        {
            paintImmediately(0, 0, chartImage.getWidth(), chartImage.getHeight());
        }

        /**
         * Clear the image on this panel.
         */
        public void clearImage()
        {
            Graphics g = chartImage.getGraphics();
            g.setColor(this.getBackground());
            g.fillRect(0, 0, chartImage.getWidth(), chartImage.getHeight());
            repaint();
        }

        // The following methods are redefinitions of methods
        // inherited from superclasses.

        /**
         * Tell the layout manager how big we would like to be.
         * (This method gets called by layout managers for placing
         * the components.)
         * 
         * @return The preferred dimension for this component.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(chartImage.getWidth(), chartImage.getHeight());
        }

        /**
         * This component is opaque.
         */
        public boolean isOpaque()
        {
            return true;
        }
		
        /**
         * This component needs to be redisplayed. Copy the internal image 
         * to screen. (This method gets called by the Swing screen painter 
         * every time it want this component displayed.)
         * 
         * @param g The graphics context that can be used to draw on this component.
         */
		public void paintComponent(Graphics g) {
			Dimension size = getSize();
            if(chartImage != null) {
                g.drawImage(chartImage, 0, 0, null);
            }
		}
    }
}