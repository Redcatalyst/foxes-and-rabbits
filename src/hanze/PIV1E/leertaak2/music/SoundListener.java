package hanze.PIV1E.leertaak2.music;

import hanze.PIV1E.leertaak2.model.SimulationModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Implements the event listeners. This class wil handel all the events created by the
 * SoundFrame.
 *
 */
public class SoundListener {
	private MusicHandler handler;
	
	/**
	 * Registers changes to the SoundFrame and gives it to the MusicHandler.
	 * @param handler The handler to give the changes to.
	 */
	public SoundListener(MusicHandler handler) {
		this.handler = handler;
	}
	
	private void adjustSound(JLabel field, MusicFile file, JSlider source) {
		int value = source.getValue();
		field.setText(String.valueOf(value));			
		file.adjustVolume(value);
	}
	
	private void restoreDefaults(JFrame frame) {
		handler.resetDefaults();
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		new SoundFrame(handler, SoundFrame.model);
	}
	
	/**
	 * Listens to actions of the sliders. When a slider changes the sound will be 
	 * adjusted and the label that is attached to the slider to.
	 */
	public class AdjustSound implements ChangeListener {
		private MusicFile file;
		private JLabel field;
		
		/**
		 * When making the listener additonal information is needed.
		 * @param file the file associated by the slider
		 * @param field the label that should be updated wit the slider
		 */
		public AdjustSound(MusicFile file, JLabel field) {
			this.file = file;
			this.field = field;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			adjustSound(field, file, source);
		}
	}
	
	/**
	 * Listens to actions of a button
	 *
	 */
	public class RestoreDefaults implements ActionListener {
		private JFrame frame;
		
		public RestoreDefaults(JFrame frame) {
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			restoreDefaults(frame);			
		}
		
	}

}
