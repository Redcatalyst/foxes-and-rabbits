package hanze.PIV1E.leertaak2.music;

import java.util.HashMap;

import javax.sound.sampled.Clip;

/**
 * Handles the playing of the music.
 */
public class MusicHandler {
	private HashMap<String, MusicFile> files;
	
	/**
	 * Creates a MusicHandler.
	 */
	public MusicHandler() {
		files = new HashMap<String, MusicFile>();
	}
	
	/**
	 * Adds a MusicFile to the MusicHandler.
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
		new SoundFrame(this);
	}
	
	/**
	 * Starts infinite playing of the given MusicFile.
	 * @param file the MusicFile to be played
	 */
	public void startInfinite(MusicFile file) {
		if(!file.getClip().isRunning()) {
			file.getClip().loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	/**
	 * Plays the MusicFile once.
	 * @param file the MusicFile to be started
	 */
	public void start(MusicFile file) {
		if(!file.getClip().isRunning()) {
			file.getClip().start();
			file.getClip().close();
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
	 * returns all the MusicFiles the MusicHandler has.
	 * @return all the MusicFiles
	 */
	public HashMap<String, MusicFile> getMusicFiles() {
		return files;
	}
}
