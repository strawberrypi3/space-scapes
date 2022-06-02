import java.util.Scanner;
import java.util.ArrayList;
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

/**
  * The window which shows the game and its images, as well as a text panel with buttons
  */
public class GameWindow extends JFrame implements ActionListener {
   public static final int HB_LENGTH = 53; // Length in percieved (scaled) pixels of health bar
   public static final int HB_WIDTH = 3; // Width in percieved (scaled) pixels of health bar
   
   private Entity player;
   private Entity enemy; // enemy which participates in battle against player
   private JFrame frame; // the game window frame
   private TextPanel textPanel; // the panel which contains the text
   
   private ArrayList<JButton> buttons;
   FlowLayout layout = new FlowLayout();
   
   /**
     * Constructs a game window and sets up textPanel and overall layout
     * @param p - Entity which will serve as the player in battles
     */
   public GameWindow(Entity p) {
      //this.getContentPane().setLayout(null);
      
      frame = new JFrame("Text Screen");
      frame.setSize(654, 453+5*13);
      frame.setVisible(true);
      textPanel = new TextPanel("", p);
      frame.add(textPanel);
      frame.validate();
      frame.repaint();
      
      player = p;
      
      buttons = new ArrayList<JButton>();
      layout.setAlignment(FlowLayout.CENTER);
      textPanel.setLayout(layout);
   }
   
   /**
     * Processes all the turns of a battle against an entity
     * @param e - the enemy which will fight against the player
     * @return true if player won the battle, false otherwise
     */
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
   
   /**
     * Prints a message to the textbox on the TextPanel
     * @param message - the message to be printed
     */
   public void write(String message) {
      textPanel.typewriter(message + "</html>");
   }
   
   /**
     * Displays an image to the foreground of the TextPanel using default position and size values
     * @param path - the filepath of the image to be displayed
     */
   public void setForeground(String path) {
      textPanel.setForeground(path);
   }
   
   /**
     * Displays an image to the foreground of the TextPanel using custom size and position values
     * @param path - the filepath of the image to be displayed
     * @param x - the desired x-position of the top left point of the image
     * @param y - the desired y-position of the top left point of the image
     * @param width - the desired horizontal side length of the image
     * @param height - the desired vertical side length of the image
     */
   public void setForeground(String path, int x, int y, int width, int height) {
      textPanel.setForeground(path, x, y, width, height);
   } 
   
   /**
     * Displays an image in the background of the TextPanel, automatically stretched across the
     * length of the screen
     * @param path - the filepath of the image to be displayed
     */
   public void setBackground(String path) {
      textPanel.setBackground(path);
   }
   
   /**
     * Prompts the player to select a move and processes said move
     * @return the result of the dealt processed move taken by the player
     */
   public int[] playerTurn(Scanner console) {
      System.out.println("Select from the following:");
      Move[] moveset = player.getMoveset();
      for (int i = 0; i < moveset.length; i++) {
         System.out.println("\t(" + (i + 1) + ") " + moveset[i].getName());
      }
      int moveNumber = console.nextInt() - 1;
      return processMove(moveset[moveNumber], player, enemy);
   }
   
   /**
     * Has the enemy pick a random move from their moveset and processes said move
     * @return the result of the dealt processed move taken by the enemy
     */
   public int[] enemyTurn() {
      Move[] moveset = enemy.getMoveset();
      int moveNumber = (int)(Math.random() * moveset.length);
      Move move = moveset[moveNumber];
      return processMove(move, enemy, player);
   }
   
   /**
     * Deals a Move, prints its result, and update statuses and health accordingly
     * @param move - the move that was chosen by subject
     * @param subject - the Entity that chose the move being processed
     * @param other - the opposing Entity battling the subject
     * @return the dealt move's returned array
     * @see Move.deal()
     */
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
   
   public void addButton(String text) {
      JButton b = new JButton(text);
      b.addActionListener(this);
      buttons.add(b);
      textPanel.add(b, BorderLayout.SOUTH);
   }
   
   public void deleteButton(String text) {
      Component[] components = textPanel.getComponents();
      for (Component c : components) {
         if (c instanceof JButton) {
            if (((JButton)c).getLabel().equals(text)) {
               textPanel.remove(c);
            }
         }
      }
   }
   
   public void actionPerformed(ActionEvent e) {
      JButton source = (JButton)e.getSource();
      Action.setAnswer(source.getLabel());
   }
   
   /**
     * A panel which displays text and also draws graphics every frame
     */
   public class TextPanel extends javax.swing.JPanel {
      private String characterName;
      private int index = 0;
      private javax.swing.Timer timer;
      private javax.swing.JButton jButton1;
      private javax.swing.JLabel label;
      private Image board;
      private Image status;
      private Image background;
      private Image foreground;
      private int foregroundX;
      private int foregroundY;
      private int foregroundWidth;
      private int foregroundHeight;
      private Entity player;
      
      /**
        * Constructs TextPanel
        * @param charaName - the border title
        * @param p - the player Entity
        */
      public TextPanel(String charaName, Entity p) {
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
           
           /*
           Border bevel = BorderFactory.createRaisedBevelBorder();
           Border title = BorderFactory.createTitledBorder(characterName.toUpperCase());
           Border matte = BorderFactory.createMatteBorder(15, 15, 15, 15, new Color(0, 0, 0, 0));
           Border padding = BorderFactory.createEmptyBorder(3, 10, 7, 10);
           Border compound1 = BorderFactory.createCompoundBorder(bevel, title);
           Border compound2 = BorderFactory.createCompoundBorder(matte, compound1);
           Border compound3 = BorderFactory.createCompoundBorder(compound2, padding);
           label.setBorder(compound3);
           */
           Border matte = BorderFactory.createMatteBorder(15, 15, 0, 15, new Color(0, 0, 0, 0));
           label.setBorder(matte);
           
           player = p;
       }
      
      /**
        * Displays an image to the foreground of the TextPanel using default position and size values
        * @param path - the filepath of the image to be displayed
        */
      public void setForeground(String path) {
         foreground = imagePanel(path, foreground);
         // Default settings:
         foregroundX = 25;
         foregroundY = 80;
         foregroundWidth = 640 - 50;
         foregroundHeight = 478 - 25 - 120;
      }
      
      /**
        * Displays an image to the foreground of the TextPanel using custom size and position values
        * @param path - the filepath of the image to be displayed
        * @param x - the desired x-position of the top left point of the image
        * @param y - the desired y-position of the top left point of the image
        * @param width - the desired horizontal side length of the image
        * @param height - the desired vertical side length of the image
        */
      public void setForeground(String path, int x, int y, int width, int height) {
         foreground = imagePanel(path, foreground);
         foregroundX = x;
         foregroundY = y;
         foregroundWidth = width;
         foregroundHeight = height;
      }
      
      /**
        * Displays an image in the background of the TextPanel, automatically stretched across the
        * length of the screen
        * @param path - the filepath of the image to be displayed
        */
      public void setBackground(String path) {
         background = imagePanel(path, background);
      }
      
      // private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
      //     typewriter("Lorem ipsum dolor sit amet. Ex reprehenderit repellendus hic galisum perspiciatis hic porro quia qui nihil earum ut illo rerum et possimus pariatur!");
      // }
   
      /**
        * Tries to set the desired image file to the desired image object
        * @param path - the filepath of the image
        * @param im - the image object
        */
      public Image imagePanel(String path, Image im) {
         try {
            im = ImageIO.read(new File(path));
            return im;
         } catch (IOException ex){
            System.out.println("Error: Image file not found");
            return im;
         }
      }
      
      /**
        * Prints a message to the textbox with a stalled typewriter effect
        * @param message - the message to be printed
        */
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
      
      /**
        * Paints graphics to the panel each frame
        * @param g - a Graphics object, automatically passed in
        */
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         g.clearRect(0, 0, getWidth(), getHeight());
         
         // Draw the background
         if (background != null) {
            background = background.getScaledInstance(654, 453, Image.SCALE_DEFAULT);
            g.drawImage(background, 0, 0+13*5, this);
         }
         
         board = imagePanel("assets/BattleUIPanel.png", board);
         status = imagePanel("assets/status" + player.getStatus() + ".png", status);
         
         board = board.getScaledInstance(640, 640, Image.SCALE_DEFAULT); // Board is scaled up by 5
         g.drawImage(board, 0, 0, this);
         
         status = status.getScaledInstance(48, 48, Image.SCALE_DEFAULT);
         g.drawImage(status, 558, 13+13*5, this);
         
         // Draw the foreground
         if (foreground != null) {
            foreground = foreground.getScaledInstance(foregroundWidth, foregroundHeight, Image.SCALE_DEFAULT);
            g.drawImage(foreground, foregroundX, foregroundY, this);
         }
         
         // Draw red section of health bar:
         double percentHealth = (double) player.getHealth() / player.getMaxHealth();
         int pixelsRed = HB_LENGTH - (int)(HB_LENGTH * percentHealth);
         g.setColor(Color.RED);
         g.fillRect(80, 30, pixelsRed*5, HB_WIDTH*5);
         repaint();
      }
   }
} 