package hanze.PIV1E.leertaak2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import hanze.PIV1E.leertaak2.main.Simulator;
import hanze.PIV1E.leertaak2.model.*;

/**
 * Handles the events created by the menu in the JFrame.
 */
public class MenuController extends AbstractController {
	
	/**
	 * Creates a MenuController. It needs a model to give his information to.
	 * @param simulation the SimulationController to give the information to
	 */
	public MenuController(SimulationModel simulation) {
		super(simulation);
	}
	
    private void showAbout(){
    	final ImageIcon icon = new ImageIcon("resources/icon.png");
        JOptionPane.showMessageDialog(null, "Foxes and Rabits\n" + Simulator.VERSION, "About Foxes and Rabbits", JOptionPane.INFORMATION_MESSAGE, icon);
    	//JOptionPane.showMessageDialog(this, "Foxes and Rabits\n" + Simulator.VERSION, "About Foxes and Rabbits", icon);
    }
    
    /**
     * Shows information about the Foxes and Rabbits simulation.
     */
	public class ShowAbout implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showAbout();
		}
	}
	
	/**
     * Stops the program.
     */
	public class Quit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	/**
	 * Mutes all game sounds.
	 */
	public class MuteAllSound implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			simulation.getMusicHandler().muteAllSound();
		}
	}
	
	/**
	 * Unmutes all game sounds.
	 */
	public class UnMuteAllSound implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			simulation.getMusicHandler().unMuteAllSound();
		}
	}
	
	/**
	 * Shows the menu to adjust the game sounds.
	 */
	public class AdjustSound implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			simulation.getMusicHandler().adjustSound();
		}
	}
}
