package hanze.PIV1E.leertaak2.music;

/**
 * Illustrates a musicfile, the class only contains a Clip object but just makes it easier to use.
 * The musicfile can only be of type '.wav'.
 */
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicFile {
	private Clip sound;
	private String name;
	private FloatControl gainControl;
	private float standerdVolume;
	private float currentVolume;
	
	/**
	 * Creates a MusicFile and adds itself to a musichandler.
	 * @param path the path to the file
	 * @param handler the MusicHandler this MusicFile should be added to
	 * @param volume the standerd volume the sound should have (0 is normal)
	 */
	public MusicFile(String path, MusicHandler handler, float volume, String name) {
		handler.addMusicFile(name, this);
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	        sound = AudioSystem.getClip();
	        sound.open(audioInputStream);
	        
	        gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
	        standerdVolume = volume;
	        adjustVolume(volume);
	    } catch(Exception e) {
	        System.out.println("Error with sound.");
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Returns the clip that the MusicFile is actually representing
	 * @return the Clip
	 */
	public Clip getClip() {
		return sound;
	}
	
	/**
	 * Returns the name of the MusicFile
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adjusts the volume of the MusicFile.
	 * @param volume the volume adjustment that should be made (0 is nothing)
	 */
	public void adjustVolume(float volume) {
		gainControl.setValue(volume);
		currentVolume = volume;
	}
	
	/**
	 * Adjusts the volume to the standerd volume.
	 */
	public void setToStanderdVolume() {
		adjustVolume(standerdVolume);
	}
	
	/**
	 * Unmutes the sound.
	 */
	public void unMute() {
		adjustVolume(currentVolume);
	}
	
	/**
	 * Mutes the sound.
	 */
	public void mute(){
		gainControl.setValue(-80.0f);
	}
}
