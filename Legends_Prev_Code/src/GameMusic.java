import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

//Music class created to play music during program execution
public class GameMusic extends JFrame {

		private ArrayList<Clip> clips = new ArrayList<Clip>();

		public void playMusic(String pen)throws InterruptedException {     

		try {
		     // Open an audio input stream.           
		      File soundFile = new File(pen); 
		      AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
		      Clip clip = AudioSystem.getClip();

		     clip.open(audioIn);
		     clip.start();
		     clips.add(clip);
		     clip.loop(EXIT_ON_CLOSE);
		  } catch (UnsupportedAudioFileException e) {
		     e.printStackTrace();
		  } catch (IOException e) {
		     e.printStackTrace();
		  } catch (LineUnavailableException e) {
		     e.printStackTrace();
		  }
		 }
		 public boolean stop(int id) {
		    if (clips.get(id-1) != null) {
		        clips.get(id-1).stop();
		        return true;
		    }
		    return false;
	   }

}
