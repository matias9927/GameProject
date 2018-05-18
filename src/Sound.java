/* Sound.java
 * Matias Saavedra Silva and Johnny Pabst
 * Thread that plays background music for overworld and battles
 */
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound extends Thread{
	
	public String overworldMusic =  "src\\Sound\\Pokemon SilverGoldCrystal - New Bark Town.wav";
	public String battleMusic = "src\\Sound\\GS_Rival.wav";
	public String bossMusic = "src\\Sound\\Red_Battle.wav";
	public AudioInputStream audioInputStream; 
	public Clip clip;
	
	
	/* Takes string with the file path to the music files
	 * and plays the song
	 */
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
	
	//Stops the song currently playing
	public void soundStop() {
		clip.stop();
		clip.close();
	}

}
