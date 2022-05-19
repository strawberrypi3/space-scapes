/**
  * Handles the turns and stat changes of a battle between the player
  * and a single enemy
  */
import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Battle extends JFrame {
   public static final int HB_LENGTH = 53; // Length in percieved (scaled) pixels of health bar
   public static final int HB_WIDTH = 3; // Width in percieved (scaled) pixels of health bar
   
   private Entity player;
   private Entity enemy;
   private JFrame frame;
   private JPanel panel;
   
   public Battle(Entity p, Entity e) {
      player = p;
      enemy = e;
   }
   
   public boolean doBattle(Scanner console) {
      // temporary UI, will integrate with Textbox and Game
      frame = new JFrame("Battle Screen");
      frame.setSize(640, 640);
      frame.setVisible(true);
      GamePanel panel = new GamePanel();
      frame.add(panel);
      frame.validate();
      frame.repaint();
      
      int turn;
      if (player.getSpeed() < enemy.getSpeed()) {
         turn = 1;
      } else if (player.getSpeed() > enemy.getSpeed()) {
         turn = 0;
      } else {
         turn = (int)(Math.random() * 2);
      }
      while (player.getHealth() > 0 && enemy.getHealth() > 0) {
         if (turn % 2 == 0) {
            playerTurn(console);
            
            if (enemy.getStatus() == 1) { // poison
               enemy.setHealth((int)(enemy.getHealth() * 0.8));
            }
         } else {
            enemyTurn();
            if (player.getStatus() == 1) { // poison
               player.setHealth((int)(player.getHealth() * 0.8));
            }
         }
         printStats();
         turn++;
      }
      System.out.println("Battle over");
      if (player.getHealth() > 0) {
         System.out.println("You won!!");
         return true;
      } else {
         System.out.println("You lost - GAME OVER");
         return false;
      }
   }
   
   public int[] playerTurn(Scanner console) {
      System.out.println("Select from the following:");
      Move[] moveset = player.getMoveset();
      for (int i = 0; i < moveset.length; i++) {
         System.out.println("\t(" + (i + 1) + ") " + moveset[i].getName());
      }
      int moveNumber = console.nextInt() - 1;
      return processMove(moveset[moveNumber], player, enemy);
   }
   
   public int[] enemyTurn() {
      Move[] moveset = enemy.getMoveset();
      int moveNumber = (int)(Math.random() * moveset.length);
      Move move = moveset[moveNumber];
      return processMove(move, enemy, player);
   }
   
   public int[] processMove(Move move, Entity subject, Entity other) {
      System.out.println(subject.getName() + " used " + move.getName());
      int result[] = move.deal();
      
      if (result[0] == 0 && result[1] == 0) { // Move unsuccessful
         System.out.println("...but it didn't work!");
         return result;
      }
      
      if (result[0] > 0) {
         System.out.println(subject.getName() + " healed by " + result[0] + " HP!");
         subject.recieveDamage(result[0]);
      } else {
         System.out.println(subject.getName() + " dealt " + Math.abs(result[0]) + " damage!");
         other.recieveDamage(result[0]);
      }
      
      if (result[1] == 1) {
         System.out.println("Poison has been afflicted to " + other.getName());
      }
      
      other.setStatus(result[1]);
      
      return result;
   }
   
   public void printStats() {
      System.out.println();
      System.out.println("Your HP: " + player.getHealth());
      System.out.println("Your status: " + player.getStatusName());
      System.out.println();
   }
   
   public class GamePanel extends JPanel {
      private Image image;
      
      public GamePanel() {
         imagePanel();
      }
      
      public void imagePanel() {
         try {
            image = ImageIO.read(new File("BattleUIPanel.png"));
         } catch (IOException ex){
            System.out.println("nope");
            return;
         }
      }
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         g.clearRect(0, 0, getWidth(), getHeight());
         
         // What looks like a "pixel" is actually about 5 x 5 pixels
         image = image.getScaledInstance(640, 640, Image.SCALE_DEFAULT);
         g.drawImage(image, 0, 0, this);
         
         // Draw red section of health bar:
         double percentHealth = (double) player.getHealth() / player.getMaxHealth();
         int pixelsRed = HB_LENGTH - (int)(HB_LENGTH * percentHealth);
         g.setColor(Color.RED);
         g.fillRect(80, 30, pixelsRed*5, HB_WIDTH*5);
         repaint();
      }
   }
}