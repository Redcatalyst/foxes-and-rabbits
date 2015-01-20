package hanze.PIV1E.leertaak2.GUI;
import hanze.PIV1E.leertaak2.*;
import hanze.PIV1E.leertaak2.location.*;
import hanze.PIV1E.leertaak2.animals.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author Frank Noorlander
 * @version 16/01/2015
 */
public class SimulatorView extends JFrame
{
	// Current simulator version
	private static final String VERSION = "Version 1.0";
	
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    private JLabel versionLabel; 
    private FieldView fieldView;
    
    private JPanel simulationPane;
    private JPanel simulationView;
    private JPanel toolbar;
    private JPanel version;
    private JButton step1;
    private JButton step100;
    
    // A map for storing colors for participants in the simulation
    private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    public SimulatorView(int height, int width)
    {    	
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Fox and Rabbit Simulation");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);
        
        fieldView = new FieldView(height, width);

        Container contents = getContentPane();
        makeMenuBar();
        
        // Container for the simulationView
        simulationPane = new JPanel();
        simulationPane.setLayout(new BorderLayout());
        contents.add(simulationPane);
        
        // Container for the simulation visuals
        simulationView = new JPanel();
        simulationView.setLayout(new BorderLayout());
        simulationView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        simulationView.add(stepLabel, BorderLayout.NORTH);
        simulationView.add(fieldView, BorderLayout.CENTER);
        simulationView.add(population, BorderLayout.SOUTH);
        simulationPane.add(simulationView, BorderLayout.CENTER);
        
        toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0,1));
        step1 = new JButton("Step 1");
        step1.addActionListener(new ActionListener() {
        							public void actionPerformed(ActionEvent e) {step1(); }
        						});
        toolbar.add(step1);
        step100 = new JButton("Step 100");
        step100.addActionListener(new ActionListener() {
        							public void actionPerformed(ActionEvent e) {step100(); }
        						});
        toolbar.add(step100);
        
        JPanel flow = new JPanel();
        flow.add(toolbar);
        simulationPane.add(flow, BorderLayout.WEST);
        
        JPanel version = new JPanel();
        JLabel versionLabel = new JLabel(VERSION);
        simulationPane.add(versionLabel, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }
    
    /**
     * Simulates 1 step in the simulation
     */
    private void step1(){
    	Simulator.simulator.simulateOneStep();
    }
    
    /**
     * Simulates 100 steps in the simulation
     */
    private void step100(){
    	Simulator.simulator.simulate(100);
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    private Color getColor(Class animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        stepLabel.setText(STEP_PREFIX + step);
        stats.reset();
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
        fieldView.repaint();
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
    
    /**
     * shows information about the Foxes and Rabbits simulation
     */
    private void showAbout(){
    	JOptionPane.showMessageDialog(this, "Foxes and Rabits\n" + VERSION, "About ImageViewer", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * stops the program
     */
    private void quit(){
    	System.exit(0);;
    }
    
    public void makeMenuBar(){
    	JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the Menu 1 menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("quit");
        item.addActionListener(new ActionListener() {
					       		public void actionPerformed(ActionEvent e) { quit(); }
					   		});
        menu.add(item);
        
     // create the Menu 2 menu
        menu = new JMenu("Menu 2");
        menubar.add(menu);
        
        item = new JMenuItem("Item 1");
        menu.add(item);
        
     // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About ImageViewer");
        item.addActionListener(new ActionListener() {
                           		public void actionPerformed(ActionEvent e) { showAbout(); }
                       		});
        menu.add(item);
    }
}
