import javax.swing.*;
import java.util.concurrent.TimeUnit;

public final class Action {
   private String answer;
   
   public static String waitForAnswer() {
      while (answer == null) {
         try {
            Thread.sleep(0);
         } catch (InturruptedException ex) {
            return null;
         }
      }
      return answer;
   }
   
   public static void setAnswer(String a) {
      answer = a;
   }
}