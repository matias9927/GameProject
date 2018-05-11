import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static void main(String[] args) {
		soundtrackLoop("src\\Sound\\Pokemon SilverGoldCrystal - New Bark Town.wav", true);
	}

	public static void soundtrackLoop (String soundName, boolean loop) {
	    while(loop) {
	        soundPlay(soundName);
	    }
	}

	public static void soundPlay(String soundName) { 
	    try {
	        AudioInputStream audioInputStream; 
	        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();               
	           try {
	                Thread.sleep(50);
	            } catch (InterruptedException ie) { 
	                ie.printStackTrace();
	            }
	    } catch(Exception error) {           
	        System.out.println("Error with playing sound."+error);
	    }
	}
	
}
