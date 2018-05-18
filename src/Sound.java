import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound extends Thread{
	
	public String overworldMusic =  "src\\Sound\\Pokemon SilverGoldCrystal - New Bark Town.wav";
	public String battleMusic = "src\\Sound\\GS_Rival.mp3";
	public AudioInputStream audioInputStream; 
	public Clip clip;
	
	/*public static void main(String[] args) {
		soundtrackLoop(overworldMusic, true);
	}*/

	public void soundtrackLoop (String soundName, boolean loop) {
	    /*while(loop) {
	        soundPlay(soundName);
	    }*/
		soundPlay(soundName);
	}

	/*public static void soundPlay(String soundName) { 
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
	}*/
	
	public void soundPlay(String soundName) { 
	    try {
	        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
	        clip = AudioSystem.getClip();
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
	
	public void soundStop() {
		clip.stop();
		clip.close();
	}
	
	public void run(){

	}

}
