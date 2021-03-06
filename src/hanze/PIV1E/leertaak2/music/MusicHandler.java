package hanze.PIV1E.leertaak2.music;

import hanze.PIV1E.leertaak2.model.SimulationModel;

import java.util.HashMap;

import javax.sound.sampled.Clip;

/**
 * Handles the playing of the music.
 * @author Frank
 */
public class MusicHandler {
	private HashMap<String, MusicFile> files;
	private SimulationModel model;
	
	/**
	 * Creates a MusicHandler.
	 * @param model The model this handler belongs to.
	 */
	public MusicHandler(SimulationModel model) {
		this.model = model;
		files = new HashMap<String, MusicFile>();
	}
	
	/**
	 * Adds a MusicFile to the MusicHandler.
	 * @param name The name of the sound
	 * @param file MusicFile to be added
	 */
	public void addMusicFile(String name, MusicFile file){
		files.put(name, file);
	}
	
	/**
	 * Sets all the sound volumes to its lowest. Does not stop the music!
	 */
	public void muteAllSound() {
		for (HashMap.Entry<String, MusicFile> entry : files.entrySet()) {
			entry.getValue().mute();
		}
	}
	
	/**
	 * Unmutes all the sounds.
	 */
	public void unMuteAllSound() {
		for (HashMap.Entry<String, MusicFile> entry : files.entrySet()) {
			entry.getValue().unMute();
		}
	}
	
	/**
	 * Gives a menu to adjust all sounds.
	 */
	public void adjustSound() {
		new SoundFrame(this, model);
	}
	
	/**
	 * Starts infinite playing of the given MusicFile.
	 * @param file the MusicFile to be played
	 */
	public void startInfinite(MusicFile file) {
		if(!file.getClip().isRunning()) {
			Thread thread = new Thread(new Runnable(){
				public void run(){
					try {
						file.getClip().setMicrosecondPosition(0);
						file.getClip().loop(Clip.LOOP_CONTINUOUSLY);
					} catch(Exception e) {
						System.out.println("Error with sound.");
				        e.printStackTrace();
					}
				}
			});
			thread.start();
		}
	}
	
	/**
	 * Plays the MusicFile once.
	 * @param file the MusicFile to be started
	 */
	public void start(MusicFile file) {
		if(!file.getClip().isRunning()) {
			Thread thread = new Thread(new Runnable(){
				public void run(){
					try {
						file.getClip().setMicrosecondPosition(0);
						file.getClip().start();
					} catch(Exception e) {
						System.out.println("Error with sound.");
				        e.printStackTrace();
					}
				}
			});
			thread.start();
		}
	}
	
	/**
	 * Stops the MusicFile.
	 * @param file the MusicFile to be stopped
	 */
	public void stop(MusicFile file) {
		if(file.getClip().isRunning()) {
			file.getClip().stop();
		}
	}
	
	/**
	 * resets all the sounds to their default volume
	 */
	public void resetDefaults() {
		for (HashMap.Entry<String, MusicFile> entry : files.entrySet()) {
			entry.getValue().resetVolume();
		}
	}
	
	/**
	 * returns all the MusicFiles the MusicHandler has.
	 * @return all the MusicFiles
	 */
	public HashMap<String, MusicFile> getMusicFiles() {
		return files;
	}
}
