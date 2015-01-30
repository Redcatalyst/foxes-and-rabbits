package hanze.PIV1E.leertaak2.music;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;

import hanze.PIV1E.leertaak2.main.Simulator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * Class that makes a JFrame which is able to adjust the sound.
 */
public class SoundFrame {
	private MusicHandler handler;
	private JFrame frame;
	private Container content;
	private SoundListener listener;
	
	/**
	 * Makes a SoundFrame
	 * @param handler The handler associated with this SoundFrame
	 */
	public SoundFrame(MusicHandler handler) {
		this.handler = handler;
		listener = new SoundListener(handler);
		makeFrame();
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
        //frame.setResizable(false);
		frame.setTitle("Sound volume");
	}
	
	/**
	 * makes the options to adjust the sound
	 */
	private void makeOptions() {
		content.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Sound volume");
		label.setFont(new Font(label.getName(), Font.BOLD, 15));
		content.add(label, BorderLayout.NORTH);
		
		JPanel sliders = new JPanel();
		content.add(sliders);
		GridLayout layout = new GridLayout(0,2);
		layout.setHgap(5);
		layout.setVgap(5);
		sliders.setLayout(layout);
		
		JLabel name;
		JSlider slider;
		JTextField text;
		JPanel panel;
		for (HashMap.Entry<String, MusicFile> entry : handler.getMusicFiles().entrySet()) {
			name = new JLabel(entry.getKey());
			sliders.add(name);
			
			double value = entry.getValue().getCurrentVolume();
			panel = new JPanel();
			text = new JTextField(2);
			text.setText(String.valueOf(Math.round(value)));
			panel.add(text);
			slider = new JSlider(0, 100, 50);
			slider.addChangeListener(listener.new AdjustSound(entry.getValue(), text));
			slider.setValue((int)Math.round(value));
			panel.add(slider);
			sliders.add(panel);
		}
		JButton reset = new JButton("reset to defaults");
		reset.addActionListener(listener.new RestoreDefaults(frame));
		JPanel down = new JPanel();
		down.add(reset);
		sliders.add(down, BorderLayout.SOUTH);
	}
}
