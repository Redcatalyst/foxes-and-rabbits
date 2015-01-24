package hanze.PIV1E.leertaak2.main;

import hanze.PIV1E.leertaak2.view.*;
import hanze.PIV1E.leertaak2.actor.*;
import hanze.PIV1E.leertaak2.controller.*;
import hanze.PIV1E.leertaak2.model.*;

import java.awt.BorderLayout;
import java.awt.Color;

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
 	public static final String VERSION = "Version 1.0";
 	// The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    
    public static JFrame frame;
    private MenuController menuController;
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator()
    {
        SimulationModel simulation = new SimulationModel(DEFAULT_DEPTH, DEFAULT_WIDTH);
        SimulatorView view = new SimulatorView(DEFAULT_DEPTH, DEFAULT_WIDTH, simulation);
        GraphView graph = new GraphView(100, 140, 100, simulation);
        RunController controller = new RunController(simulation);
        menuController = new MenuController(simulation);
        
        frame = new JFrame();
        makeMenuBar();
        JPanel extraViews = new JPanel();
        extraViews.add(graph);
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

        // Set the color of the animals in the simulation
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
        view.setColor(Bear.class, Color.RED);
        graph.setColor(Rabbit.class, Color.ORANGE);
        graph.setColor(Fox.class, Color.BLUE);
        graph.setColor(Bear.class, Color.RED);
        
        
        // Setup a valid starting point.
        simulation.reset();
    }
    
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
