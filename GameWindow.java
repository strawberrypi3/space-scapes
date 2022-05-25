/**
  * Handles the turns and stat changes of a battle between the player
  * and a single enemy
  */
import java.util.Scanner;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*; // border for textbox
import java.awt.*;
import java.awt.event.*; // to respond to clicks
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameWindow extends JFrame {
   public static final int HB_LENGTH = 53; // Length in percieved (scaled) pixels of health bar
   public static final int HB_WIDTH = 3; // Width in percieved (scaled) pixels of health bar
   
   private Entity player;
   private Entity enemy;
   private JFrame frame;
   private JFrame textFrame;
   private TextPanel textPanel;
   
   public GameWindow(Entity p) {
      this.getContentPane().setLayout(null);
      
      textFrame = new JFrame("Text Screen");
      textFrame.setSize(654, 478-25);
      textFrame.setVisible(true);
      textPanel = new TextPanel("");
      textFrame.add(textPanel);
      textFrame.validate();
      textFrame.repaint();
      
      player = p;
   }
   
   public boolean doBattle(Entity e, Scanner console) {
      enemy = e;
      
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
   
   public void write(String message) {
      textPanel.typewriter(message);
      System.out.println("CHECK");
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
   
   public class TextPanel extends javax.swing.JPanel {
      private String characterName;
      private int index = 0;
      private javax.swing.Timer timer;
      private javax.swing.JButton jButton1;
      private javax.swing.JLabel label;
      private Image board;
      private Image status;
      
       public TextPanel(String charaName) {
           characterName = charaName;
           setLayout(new BorderLayout(5,5));
          // jButton1 = new javax.swing.JButton();
          // jButton1.setText("Press to proceed");
          // jButton1.addActionListener(evt -> jButton1ActionPerformed(evt)); // lambda expression (->): a short way of method writing
           label = new javax.swing.JLabel();
           label.setFont(new Font("Arial", Font.BOLD, 15));
           add(label, BorderLayout.PAGE_END);
          // add(jButton1, BorderLayout.NORTH);
           setPreferredSize(new Dimension(650, 300));
   
           Border bevel = BorderFactory.createRaisedBevelBorder();
           Border title = BorderFactory.createTitledBorder(characterName.toUpperCase());
           Border matte = BorderFactory.createMatteBorder(15, 15, 15, 15, new Color(0, 0, 0, 0));
           Border padding = BorderFactory.createEmptyBorder(3, 10, 7, 10);
           Border compound1 = BorderFactory.createCompoundBorder(bevel, title);
           Border compound2 = BorderFactory.createCompoundBorder(matte, compound1);
           Border compound3 = BorderFactory.createCompoundBorder(compound2, padding);
           label.setBorder(compound3);
       }
      
      // private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
      //     typewriter("Lorem ipsum dolor sit amet. Ex reprehenderit repellendus hic galisum perspiciatis hic porro quia qui nihil earum ut illo rerum et possimus pariatur!");
      // }
   
      public void imagePanel() {
         try {
            board = ImageIO.read(new File("assets/BattleUIPanel.png"));
            status = ImageIO.read(new File("assets/status" + player.getStatus() + ".png"));
         } catch (IOException ex){
            System.out.println("Error: Image file not found");
            return;
         }
      }
      public void typewriter(String message) {
         if (timer != null && timer.isRunning()) return;
        
         // resets typewriter when button clicked again
         index = 0;
         label.setText("<html>");
              
         timer = new Timer(30, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               label.setText(label.getText() + String.valueOf(message.charAt(index)));
               index++;
               if (index >= message.length()) {
                  timer.stop();
               }
            }
         });
         timer.start();
      }
      public Dimension getPreferredSize() {
          return new Dimension(30, 30);
      }
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         g.clearRect(0, 0, getWidth(), getHeight());
         
         imagePanel();
         
         // Board is scaled up by 5
         board = board.getScaledInstance(640, 640, Image.SCALE_DEFAULT);
         g.drawImage(board, 0, 0, this);
         
         status = status.getScaledInstance(48, 48, Image.SCALE_DEFAULT);
         g.drawImage(status, 558, 13, this);
         
         // Draw red section of health bar:
         double percentHealth = (double) player.getHealth() / player.getMaxHealth();
         int pixelsRed = HB_LENGTH - (int)(HB_LENGTH * percentHealth);
         g.setColor(Color.RED);
         g.fillRect(80, 30, pixelsRed*5, HB_WIDTH*5);
         repaint();
      }
   }
} 