import java.util.*;
import java.lang.*;

public class Game {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      Move[] moveset = new Move[5];
      moveset[0] = new Move(0, 100, 1.0, 0.5, "Cure");
      moveset[1] = new Move(1, -20, 0.5, 0.8, "Snakebite"); // High chance of poison
      moveset[2] = new Move(1, -100, 0.8, 0.1, "Punch");
      moveset[3] = new Move(2, 0, 0.0, 1.0, "Speak Pig Latin"); // Confuses enemy
      moveset[4] = new Move(3, 0, 0.0, 1.0, "Melatonin"); // Makes enemy tired
      
      Move[] frankensteinMoveset = new Move[1];
      frankensteinMoveset[0] = new Move(0, -50, 1.0, 0.0, "Smack");
      
      Entity player = new Entity("Me", 200, 50, moveset);
      Entity enemy = new Entity("Frankenstein", 100, 40, frankensteinMoveset);
      
      Battle fight1 = new Battle(player, enemy);
      fight1.doBattle(console);
   }
}