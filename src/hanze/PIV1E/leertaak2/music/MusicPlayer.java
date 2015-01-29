package hanze.PIV1E.leertaak2.music;

import javax.sound.sampled.Clip;

/**
 * Class to play MusicFile's.
 */
public class MusicPlayer
{
	
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

}
