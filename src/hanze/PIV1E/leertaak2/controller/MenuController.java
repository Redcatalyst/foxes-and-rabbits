package hanze.PIV1E.leertaak2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import hanze.PIV1E.leertaak2.main.Simulator;
import hanze.PIV1E.leertaak2.model.SimulationModel;

public class MenuController extends AbstractController {

	public MenuController(SimulationModel simulation) {
		super(simulation);
	}
	
	/**
     * shows information about the Foxes and Rabbits simulation
     */
    private void showAbout(){
    	JOptionPane.showMessageDialog(this, "Foxes and Rabits\n" + Simulator.VERSION, "About ImageViewer", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * stops the program
     */
    private void quit(){
    	System.exit(0);;
    }

	public class ShowAbout implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showAbout();
		}
	}
	
	public class Quit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			quit();
		}
	}
}
