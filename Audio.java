/**
  * Class for an Audio object, which can play and stop a wav file
  */
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Audio {
   /** Audio file is set to this clip, which can then be stopped and started. */
   private Clip clip;
   
   /**
     * Constructs an Audio object
     * @param p the filepath of the desired .wav file
     */
   public Audio(String p) {
      try {
         File musicPath = new File(p);
         
         if (musicPath.exists()) {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
         } else {
            System.out.println("Audio file path does not exist");
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   /**
     * Plays the audio file based on the constructed path
     */
   public void play() {
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);
   }
   
   /**
     * Stops the audio file
     */
   public void stop() {
      clip.stop();
   }
}