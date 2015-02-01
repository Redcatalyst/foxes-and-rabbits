package hanze.PIV1E.leertaak2.model;

import hanze.PIV1E.leertaak2.actor.*;
import hanze.PIV1E.leertaak2.main.Simulator;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Class that makes a JFrame which is able to adjust simulation settings.
 */
public class SimulationSettings {
	private JFrame frame;
	private Container content;
	private SettingsListener listener;
	
	public SimulationSettings() {
		makeFrame();
		listener = new SettingsListener(frame);
		makeOptions();
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Makes the JFrame
	 */
	private void makeFrame() {
		frame = new JFrame();
		content = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(Simulator.frame.getLocationOnScreen().x + Simulator.frame.getSize().width + 10, Simulator.frame.getLocationOnScreen().y);
        frame.setResizable(false);
		frame.setTitle("Simulation settings");
	}
	
	/**
	 * makes the options to adjust simulation
	 */
	private void makeOptions() {		
		JPanel sliders = new JPanel();
		sliders.setLayout(new BoxLayout(sliders, BoxLayout.Y_AXIS));
		content.add(sliders);
		
		JLabel name, text, header;
		JSlider slider;
		JPanel row, combine;
		
		//Bear
		header = new JLabel("Bear");
		header.setFont(new Font(header.getName(), Font.BOLD, 15));
		sliders.add(header);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max age");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf(Bear.max_age));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(500);
		slider.setMinimum(1);
		slider.setValue(Bear.max_age);
		slider.addChangeListener(listener.new BearMaxAge(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Breeding probability");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf((int)(Bear.breeding_probability * 100)));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(100);
		slider.setMinimum(0);
		slider.setValue((int)(Bear.breeding_probability * 100));
		slider.addChangeListener(listener.new BearBreedingAge(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max litter size");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf((int)(Bear.max_litter_size)));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(10);
		slider.setMinimum(0);
		slider.setValue((int)(Bear.max_litter_size));
		slider.addChangeListener(listener.new BearMaxLitterSize(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		//Fox
		header = new JLabel("Fox");
		header.setFont(new Font(header.getName(), Font.BOLD, 15));
		sliders.add(header);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max age");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf(Fox.max_age));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(500);
		slider.setMinimum(1);
		slider.setValue(Fox.max_age);
		slider.addChangeListener(listener.new FoxMaxAge(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Breeding probability");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf((int)(Fox.breeding_probability * 100)));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(100);
		slider.setMinimum(0);
		slider.setValue((int)(Fox.breeding_probability * 100));
		slider.addChangeListener(listener.new FoxBreedingAge(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max litter size");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf((int)(Fox.max_litter_size)));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(10);
		slider.setMinimum(0);
		slider.setValue((int)(Fox.max_litter_size));
		slider.addChangeListener(listener.new FoxMaxLitterSize(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		//Hunter
		header = new JLabel("Hunter");
		header.setFont(new Font(header.getName(), Font.BOLD, 15));
		sliders.add(header);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max rabbit population");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf(Hunter.rabbit_max_population));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(5000);
		slider.setMinimum(0);
		slider.setValue(Hunter.rabbit_max_population);
		slider.addChangeListener(listener.new HunterRabbit(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max fox population");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf(Hunter.fox_max_population));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(5000);
		slider.setMinimum(0);
		slider.setValue(Hunter.fox_max_population);
		slider.addChangeListener(listener.new HunterFox(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max bear population");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf(Hunter.bear_max_population));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(5000);
		slider.setMinimum(0);
		slider.setValue(Hunter.bear_max_population);
		slider.addChangeListener(listener.new HunterBear(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		//Rabbit
		header = new JLabel("Rabbit");
		header.setFont(new Font(header.getName(), Font.BOLD, 15));
		sliders.add(header);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max age");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf(Rabbit.max_age));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(500);
		slider.setMinimum(1);
		slider.setValue(Rabbit.max_age);
		slider.addChangeListener(listener.new RabbitMaxAge(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Breeding probability");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf((int)(Rabbit.breeding_probability * 100)));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(100);
		slider.setMinimum(0);
		slider.setValue((int)(Rabbit.breeding_probability * 100));
		slider.addChangeListener(listener.new RabbitBreedingAge(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		row = new JPanel(new GridLayout(0,2));
		name = new JLabel("Max litter size");
		row.add(name);
		combine = new JPanel();
		text = new JLabel();
		text.setText(String.valueOf((int)(Rabbit.max_litter_size)));
		combine.add(text);
		slider = new JSlider();
		slider.setMaximum(10);
		slider.setMinimum(0);
		slider.setValue((int)(Rabbit.max_litter_size));
		slider.addChangeListener(listener.new RabbitMaxLitterSize(text));
		combine.add(slider);
		row.add(combine);
		sliders.add(row);
		
		//Tourist
		
		//Fire
		
		//Reset
		JButton reset = new JButton("reset to defaults");
		reset.addActionListener(listener.new Reset());
		sliders.add(reset);
	}
}
