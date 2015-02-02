package hanze.PIV1E.leertaak2.music;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Illustrates a musicfile, the class only contains a Clip object but just makes it easier to use.
 * The musicfile can only be of type '.wav'.
 * @author Frank
 */
public class MusicFile {
	private Clip sound;
	private AudioInputStream audioInputStream;
	private String name;
	private FloatControl gainControl;
	private float standerdVolume;
	private float currentVolume;
	
	/**
	 * Creates a MusicFile and adds itself to a MusicHandler.
	 * @param path the path to the file
	 * @param handler the MusicHandler this MusicFile should be added to
	 * @param volume the standerd volume the sound should have (50 is normal)
	 * @param name the name of the MusicFile
	 */
	public MusicFile(String path, MusicHandler handler, double volume, String name) {
		handler.addMusicFile(name, this);
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	        sound = AudioSystem.getClip();
	        sound.open(audioInputStream);
	        
	        gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
	        standerdVolume = doubleToFloatVolume(volume);
	        adjustVolume(volume);
	        //sound.close();
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
	 * @param volume the volume is should be set to
	 */
	public void adjustVolume(double volume) {
		gainControl.setValue(doubleToFloatVolume(volume));
		currentVolume = doubleToFloatVolume(volume);
	}
	
	/**
	 * Adjusts the volume to the standerd volume.
	 */
	public void resetVolume() {
		gainControl.setValue(standerdVolume);
		currentVolume = standerdVolume;
	}
	
	/**
	 * Unmutes the sound.
	 */
	public void unMute() {
		gainControl.setValue(currentVolume);
	}
	
	/**
	 * Mutes the sound.
	 */
	public void mute(){
		gainControl.setValue(gainControl.getMinimum());
	}
	
	/**
	 * Gives the current volume the MusicFile is playing on
	 * @return current volume
	 */
	public double getCurrentVolume() {
		return floatToDoubleVolume(currentVolume);
	}
	
	/*
	 * Converts a given float to a value between 0(incl.) and 100(incl.)
	 */
	private double floatToDoubleVolume(float volume) {
		float doubleVolume = gainControl.getMinimum() - gainControl.getMaximum();
		doubleVolume = 100 / doubleVolume;
		doubleVolume = doubleVolume * volume;
		doubleVolume = doubleVolume + gainControl.getMaximum() + 1;
		doubleVolume = 100 - doubleVolume;
		doubleVolume = Math.round(doubleVolume);
		return doubleVolume;
	}
	
	/*
	 * Converts a given int to a float value.
	 */
	private float doubleToFloatVolume(double volume) {
		float floatVolume = -80 - 6;
        floatVolume = floatVolume / 100;
        floatVolume = floatVolume * (float)volume;
        floatVolume = -80 - floatVolume;
        return floatVolume;
	}
}
