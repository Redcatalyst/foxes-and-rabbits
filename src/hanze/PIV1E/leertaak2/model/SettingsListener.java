package hanze.PIV1E.leertaak2.model;

import hanze.PIV1E.leertaak2.actor.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Implements the event listeners. This class will handel all the events created by the
 * SoundFrame.
 *
 */
public class SettingsListener {
	private JFrame frame;
	
	public SettingsListener(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Turns rabbit infection on or off
	 */
	public class RabbitInfection implements ActionListener {
		private JButton button;
		private boolean infection = true;
		private JSlider infectionS;
		
		public RabbitInfection(JButton button, JSlider infection) {
			this.button = button;
			infectionS = infection;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(infection == false){
				infection = true;
				button.setText("Turn infection off");
				SimulationModel.rabbit_infected_probability = SimulationModel.RABBIT_INFECTED_PROBABILITY;
				infectionS.setEnabled(true);
				infectionS.setValue((int)(SimulationModel.rabbit_infected_probability * 100));
			} else {
				infection = false;
				button.setText("Turn infection on");
				infectionS.setEnabled(false);
				SimulationModel.rabbit_infected_probability = 0;
			}
		}
	}
	
	/**
	 * Resets all the standerd simulation values
	 */
	public class Reset implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Bear.max_age = Bear.MAX_AGE;
			Bear.breeding_probability = Bear.BREEDING_PROBABILITY;
			Bear.max_litter_size = Bear.MAX_LITTER_SIZE;
			Fox.max_age = Fox.MAX_AGE;
			Fox.breeding_probability = Fox.BREEDING_PROBABILITY;
			Fox.max_litter_size = Fox.MAX_LITTER_SIZE;
			Hunter.rabbit_max_population = Hunter.RABBIT_MAX_POPULATION;
			Hunter.fox_max_population = Hunter.FOX_MAX_POPULATION;
			Hunter.bear_max_population = Hunter.BEAR_MAX_POPULATION;
			Rabbit.max_age = Rabbit.MAX_AGE;
			Rabbit.breeding_probability = Rabbit.BREEDING_PROBABILITY;
			Rabbit.max_litter_size = Rabbit.MAX_LITTER_SIZE;
			SimulationModel.rabbit_infected_probability = SimulationModel.RABBIT_INFECTED_PROBABILITY;
			
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			new SimulationSettings();
		}
	}
	
	/**
	 * Listens to actions of the bear sliders
	 */
	public class BearBreedingAge implements ChangeListener {
		JLabel text;
		
		public BearBreedingAge(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			double value = ((JSlider)e.getSource()).getValue();
			Bear.breeding_probability = value / 100;
			text.setText(String.valueOf((int)value));
		}
	}
	
	public class BearMaxAge implements ChangeListener {
		JLabel text;
		
		public BearMaxAge(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Bear.max_age = value;
			text.setText(String.valueOf(value));
		}
	}
	
	public class BearMaxLitterSize implements ChangeListener {
		JLabel text;
		
		public BearMaxLitterSize(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Bear.max_litter_size = value;
			text.setText(String.valueOf(value));
		}
	}
	
	/**
	 * Listens to actions of the bear sliders
	 */
	public class FoxBreedingAge implements ChangeListener {
		JLabel text;
		
		public FoxBreedingAge(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			double value = ((JSlider)e.getSource()).getValue();
			Fox.breeding_probability = value / 100;
			text.setText(String.valueOf((int)value));
		}
	}
	
	public class FoxMaxAge implements ChangeListener {
		JLabel text;
		
		public FoxMaxAge(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Fox.max_age = value;
			text.setText(String.valueOf(value));
		}
	}
	
	public class FoxMaxLitterSize implements ChangeListener {
		JLabel text;
		
		public FoxMaxLitterSize(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Fox.max_litter_size = value;
			text.setText(String.valueOf(value));
		}
	}
	
	/**
	 * Listens to actions of the hunter sliders
	 */
	public class HunterRabbit implements ChangeListener {
		JLabel text;
		
		public HunterRabbit(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Hunter.rabbit_max_population = value;
			text.setText(String.valueOf((int)value));
		}
	}
	
	public class HunterFox implements ChangeListener {
		JLabel text;
		
		public HunterFox(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Hunter.fox_max_population = value;
			text.setText(String.valueOf(value));
		}
	}
	
	public class HunterBear implements ChangeListener {
		JLabel text;
		
		public HunterBear(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Hunter.bear_max_population = value;
			text.setText(String.valueOf(value));
		}
	}
	
	/**
	 * Listens to actions of the rabbit sliders
	 */
	public class RabbitBreedingAge implements ChangeListener {
		JLabel text;
		
		public RabbitBreedingAge(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			double value = ((JSlider)e.getSource()).getValue();
			Rabbit.breeding_probability = value / 100;
			text.setText(String.valueOf((int)value));
		}
	}
	
	public class RabbitMaxAge implements ChangeListener {
		JLabel text;
		
		public RabbitMaxAge(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Rabbit.max_age = value;
			text.setText(String.valueOf(value));
		}
	}
	
	public class RabbitMaxLitterSize implements ChangeListener {
		JLabel text;
		
		public RabbitMaxLitterSize(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int value = ((JSlider)e.getSource()).getValue();
			Rabbit.max_litter_size = value;
			text.setText(String.valueOf(value));
		}
	}
	
	public class RabbitInfectionChance implements ChangeListener {
		JLabel text;
		
		public RabbitInfectionChance(JLabel text) {
			this.text = text;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			double value = ((JSlider)e.getSource()).getValue();
			SimulationModel.rabbit_infected_probability = value / 100;
			text.setText(String.valueOf((int)value));
		}
	}
}
