package hanze.PIV1E.leertaak2.main;

import hanze.PIV1E.leertaak2.view.*;
import hanze.PIV1E.leertaak2.actor.*;
import hanze.PIV1E.leertaak2.controller.*;
import hanze.PIV1E.leertaak2.model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Frank Noorlander
 * @version 16/01/2015
 */
public class Simulator
{
    // Current simulator version
 	public static final String VERSION = "Version 2.5";
 	// The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    
    // Color of fox
    private static final Color FOXCOLOR = Color.BLUE;
    // Color of fox
    private static final Color RABBITCOLOR = Color.ORANGE;
    // Color of fox
    private static final Color BEARCOLOR = Color.RED;
    // Color of fox
    private static final Color HUNTERCOLOR = Color.GREEN;
    // List of all views
    private ArrayList<AbstractView> views;
    
    public static JFrame frame;
    private SimulationModel simulation;
    private MenuController menuController;
    private AbstractView view, graph, pie;
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator()
    {
    	views = new ArrayList<AbstractView>();
        simulation = new SimulationModel(DEFAULT_DEPTH, DEFAULT_WIDTH);
        view = new SimulatorView(DEFAULT_DEPTH, DEFAULT_WIDTH, simulation);
        views.add(view);
        graph = new GraphView(140, 140, 100, simulation);
        views.add(graph);
        pie = new PieView(140, 140, simulation);
        views.add(pie);
        RunController controller = new RunController(simulation);
        menuController = new MenuController(simulation);
        
        frame = new JFrame();
        makeMenuBar();
        JPanel extraViews = new JPanel();
        extraViews.setLayout(new BoxLayout(extraViews, 1));
        extraViews.add(graph);
        extraViews.add(pie);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.getContentPane().add(extraViews, BorderLayout.EAST);
        frame.getContentPane().add(controller, BorderLayout.WEST);
        frame.getContentPane().add(menuController, BorderLayout.NORTH);
        
        JLabel versionLabel = new JLabel(VERSION);
	    frame.getContentPane().add(versionLabel, BorderLayout.SOUTH);        
        frame.setTitle("Fox and Rabbit Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 50);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        setColor();
        
        // Setup a valid starting point.
        simulation.reset();
    }
    
    /*
     * give everything a color
     */
    public void setColor() {
    	for (AbstractView view : views){
	    	view.setColor(Rabbit.class, RABBITCOLOR);
	        view.setColor(Fox.class, FOXCOLOR);
	        view.setColor(Bear.class, BEARCOLOR);
	        view.setColor(Hunter.class, HUNTERCOLOR);
    	}
    }
    
    /*
     * make the menu in the simulation and give it the controller as actionlistener
     */
    public void makeMenuBar(){
    	JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the Menu 1 menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("quit");
        item.addActionListener(menuController.new Quit());
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
        item.addActionListener(menuController.new ShowAbout());
        menu.add(item);
    }
}
