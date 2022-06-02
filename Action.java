import javax.swing.*;
import java.util.concurrent.TimeUnit;

public final class Action {
   private static String answer;
   
   public static String waitForAnswer() {
      while (answer == null) {
         try {
            Thread.sleep(500);
         } catch (InterruptedException ex) {
            return null;
         }
      }
      return answer;
   }
   
   public static void setAnswer(String a) {
      answer = a;
   }
}