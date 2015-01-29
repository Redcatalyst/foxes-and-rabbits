package hanze.PIV1E.leertaak2.music;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SoundListener {

	public void adjustSound(JTextField field, MusicFile file, JSlider source) {
		int value = source.getValue();
		field.setText(String.valueOf(value));			
		file.adjustVolume(value);
	}
	
	public class AdjustSound implements ChangeListener {
		private MusicFile file;
		private JTextField field;
		
		public AdjustSound(MusicFile file, JTextField field) {
			this.file = file;
			this.field = field;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			
			int value = source.getValue();
			field.setText(String.valueOf(value));			
			file.adjustVolume(value);
		}
	}

}
