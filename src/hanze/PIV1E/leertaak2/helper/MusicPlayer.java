package hanze.PIV1E.leertaak2.helper;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class to play music files
 * 
 * @author Frank Noorlander.
 */
public class MusicPlayer
{    
    public static void playMusic(String file){
    	Media hit = new Media(file);
    	MediaPlayer mediaPlayer = new MediaPlayer(hit);
    	mediaPlayer.play();
    }

}
