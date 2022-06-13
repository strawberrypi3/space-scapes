import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
  * A singleton class which contains the String label of the button
  * last pressed by the player, allowing other classes to wait for the
  * player's input
  */
public final class Action {
   /** The time (in milliseconds) between updating for button presses */
   public static final int WAIT_TIME = 10;
   /** The intended answer inputted by the player in response to a prompt (such as a button) */
   private static String answer;
   
   /** Privately constructs a single Action instance (singleton) */
   private Action() {
      answer = null;
   }
   
   /**
     * Continuously loops until answer is set to a non-null value, then returns
     * said answer. Allows a script to pause/wait until answer is set.
     * @return the new non-null answer
     */
   public static String waitForAnswer() {
      while (answer == null) {
         try {
            Thread.sleep(WAIT_TIME);
         } catch (InterruptedException ex) {
            System.out.println(ex);
            return null;
         }
      }
      String currentAnswer = answer;
      answer = null;
      return currentAnswer;
   }
   
   /** 
     * Sets the desired answer value and breaks the loop in waitForAnswer() if 
     * it has been called
     * @param a the desired answer inputted by the player
     */
   public static void setAnswer(String a) {
      answer = a;
   }
}