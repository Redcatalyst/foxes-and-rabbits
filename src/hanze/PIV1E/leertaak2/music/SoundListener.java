package hanze.PIV1E.leertaak2.music;

<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
=======
import javax.swing.JLabel;
>>>>>>> dingen
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Implements the event listeners. This class wil handel all the events created by the
 * SoundFrame.
 *
 */
public class SoundListener {
	private MusicHandler handler;
	
	public SoundListener(MusicHandler handler) {
		this.handler = handler;
	}
	
	private void adjustSound(JTextField field, MusicFile file, JSlider source) {
		int value = source.getValue();
		field.setText(String.valueOf(value));			
		file.adjustVolume(value);
	}
	
	private void restoreDefaults(JFrame frame) {
		handler.resetDefaults();
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	/**
	 * Listens to actions of the sliders
	 */
	public class AdjustSound implements ChangeListener {
		private MusicFile file;
		private JLabel field;
		
<<<<<<< HEAD
		/**
		 * When making the listener additonal information is needed.
		 * @param file the file associated by the slider
		 * @param field the textfield that shoul be updated wit the slider
		 */
		public AdjustSound(MusicFile file, JTextField field) {
=======
		public AdjustSound(MusicFile file, JLabel field) {
>>>>>>> dingen
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
