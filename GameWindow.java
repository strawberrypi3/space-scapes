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
  * The window which displayes the game's images, the player's current status effect, the player's 
  * current health, the text being printed to screen, and the buttons for advancing the text.
  * Has the capability to process battles given two Entity objects (one being a player).
  */
public class GameWindow extends JFrame implements ActionListener {
   /** Length in percieved (scaled) pixels of health bar */
   public static final int HB_LENGTH = 53;
   /** Width in percieved (scaled) pixels of health bar */
   public static final int HB_WIDTH = 3;
   
   /** Entity representing the player, whose status is displayed to the window */
   private Entity player;
   /** Enemy which participates in battle against the player */
   private Entity enemy;
   /** The game window frame */
   private JFrame frame;
   /** The panel which contains the text and buttons */
   private TextPanel textPanel;
   /** The music which plays during a battle */
   private Audio song;
   /** The layout of the TextPanel */
   FlowLayout layout = new FlowLayout();
   
   /**
     * Constructs a game window and sets up textPanel and overall layout
     * @param p Entity which will serve as the player in battles
     */
   public GameWindow(Entity p) {
      frame = new JFrame("Text Screen");
      frame.setSize(646, 555);
      frame.setVisible(true);
      frame.setResizable(false);
      textPanel = new TextPanel("", p);
      frame.add(textPanel);
      frame.validate();
      frame.repaint();
      
      // Make audio also stop upon closing:
      frame.addWindowListener(new java.awt.event.WindowAdapter() {
         @Override
         public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            if (song != null) {
               song.stop();
            }
            System.exit(0);
         }
      });
      
      player = p;

      layout.setAlignment(FlowLayout.CENTER);
      textPanel.setLayout(layout);
   }
   
   /**
     * Processes all the turns of a battle against an entity
     * @param e the enemy which will fight against the player
     * @return true if player won the battle, false otherwise
     */
   public boolean doBattle(Entity e) {
      clearAllButtons();
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
            playerTurn();
            
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
      addButton(">");
      write("Battle over...");
      String a = Action.waitForAnswer();
      if (player.getHealth() > 0) {
         write("You won!!");
         a = Action.waitForAnswer();
         clearAllButtons();
         return true;
      } else {
         write("You lost - GAME OVER");
         a = Action.waitForAnswer();
         clearAllButtons();
         return false;
      }
   }
   
   /**
     * Processes all the turns of a battle against an entity and plays
     * the desired audio file for the duration of the battle
     * @param e the enemy which will fight against the player
     * @param musicPath the filepath of the desired .wav file to be played during the battle
     * @return true if player won the battle, false otherwise
     */
   public boolean doBattle(Entity e, String musicPath) {
      song = new Audio(musicPath);
      song.play();
      boolean result = doBattle(e);
      song.stop();
      return result;
   }
   
   /**
     * Prints a message to the textbox on the TextPanel
     * @param message the message to be printed
     */
   public void write(String message) {
      textPanel.typewriter(message + "</html>");
   }
   
   /**
     * Displays an image to the foreground of the TextPanel using default position and size values
     * @param path the filepath of the image to be displayed
     */
   public void setForeground(String path) {
      textPanel.setForeground(path);
   }
   
   /**
     * Displays an image to the foreground of the TextPanel using custom size and position values
     * @param path the filepath of the image to be displayed
     * @param x the desired x-position of the top left point of the image
     * @param y the desired y-position of the top left point of the image
     * @param width the desired horizontal side length of the image
     * @param height the desired vertical side length of the image
     */
   public void setForeground(String path, int x, int y, int width, int height) {
      textPanel.setForeground(path, x, y, width, height);
   } 
   
   /**
     * Sets foreground image to null
     */
   public void clearForeground() {
      textPanel.clearForeground();
   }
   
   /**
     * Displays an image in the background of the TextPanel, automatically stretched across the
     * length of the screen
     * @param path the filepath of the image to be displayed
     */
   public void setBackground(String path) {
      textPanel.setBackground(path);
   }
   
   /**
     * Prompts the player to select a move and processes said move
     * @return the result of the dealt processed move taken by the player
     */
   public int[] playerTurn() {
      write("Select move:");
      Move[] moveset = player.getMoveset();
      for (int i = 0; i < moveset.length; i++) {
         addButton(moveset[i].getName());
      }
      deleteButton(">");
      String answer = Action.waitForAnswer();
      Move chosenMove = player.getMoveset()[0];
      for (Move m : player.getMoveset()) {
         if (answer.equals(m.getName())) {
            chosenMove = m;
         }
      }
      clearAllButtons();
      return processMove(chosenMove, player, enemy);
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
     * Deals a Move against an opponent, prints its result to the textbox, and updates statuses 
     * and health accordingly
     * @param move the move that was chosen by subject
     * @param subject the Entity that chose the move being processed
     * @param other the opposing Entity battling the subject
     * @return the dealt move's returned array
     */
   public int[] processMove(Move move, Entity subject, Entity other) {
      subject.getName();
      move.getName();
      addButton(">");
      write(subject.getName() + " used " + move.getName());
      String a = Action.waitForAnswer();
      
      int result[] = move.deal();
      
      if (result[0] == 0 && result[1] == 0) { // Move unsuccessful
         write("...but it didn't work!");
         a = Action.waitForAnswer();
         clearAllButtons();
         return result;
      }
      
      if (result[0] > 0) {
         write(subject.getName() + " healed by " + result[0] + " HP!");
         boolean maxHealth = subject.recieveDamage(result[0]);
         a = Action.waitForAnswer();
         if (maxHealth) {
            write(subject.getName() + " is at max health!");
            a = Action.waitForAnswer();
         }
      } else {
         write(subject.getName() + " dealt " + Math.abs(result[0]) + " damage!");
         other.recieveDamage(result[0]);
         a = Action.waitForAnswer();
      }
      
      if (result[1] == 1) {
         write("Poison has been afflicted to " + other.getName());
         a = Action.waitForAnswer();
      } else if (result[1] == 2) {
         write(other.getName() + " is confused!");
         a = Action.waitForAnswer();
      } else if (result[1] == 3) {
         write(other.getName() + " is tired!");
         a = Action.waitForAnswer();
      }
      
      other.setStatus(result[1]);
      
      clearAllButtons();
      
      return result;
   }
   
   /**
     * Adds a button to textPanel with a specific name/text/label
     * @param text the desired button's label/text/name
     */
   public void addButton(String text) {
      JButton b = new JButton(text);
      b.addActionListener(this);
      b.setBackground(new Color(Integer.parseInt("5a078b", 16))); // 35 3 55, purple
      b.setForeground(new Color(Integer.parseInt("7cad6d", 16))); // green
      textPanel.add(b, BorderLayout.SOUTH);
   }
   
   /**
     * Deletes a button on textPanel based on its label name
     * @param text the label/text/name of the button to delete
     */
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
   
   /**
     * Deletes all buttons currently on textPanel
     */
   public void clearAllButtons() {
      Component[] components = textPanel.getComponents();
      for (Component c : components) {
         if (c instanceof JButton) {
            textPanel.remove(c);
         }
      }
   }
   
   /**
     * Sets Action's static answer variable to the text name of the button that was pressed
     * @param e the ActionEvent result of the action performed
     */
   public void actionPerformed(ActionEvent e) {
      JButton source = (JButton)e.getSource();
      Action.setAnswer(source.getLabel());
   }
   
   public void setSong(Audio a) {
      song = a;
   }
   
   /**
     * A panel which displays text and also draws graphics every frame
     */
   public class TextPanel extends javax.swing.JPanel {
      /** Time (ms) until each successive character is printed to screen */
      public static final int TEXT_SPEED = 2;
      /** Current last index of string being printed to screen */
      private int index = 0;
      /** Timer used by typwriter() to stall the typing of each successive character */
      private javax.swing.Timer timer;
      /** The text label that messages are written to */
      private javax.swing.JLabel label;
      /** The UI image that serves as the border and overall GUI */
      private Image board;
      /** The image that displays the player's current status effect */
      private Image status;
      /** The background image */
      private Image background;
      /** The foreground image */
      private Image foreground;
      /** X-coordinate of the upper left corner of the foreground image */
      private int foregroundX;
      /** Y-coordinate of the upper left corner of the foreground image */
      private int foregroundY;
      /** Width (pixels) of the foreground image */
      private int foregroundWidth;
      /** Height (pixels) of the foreground image */
      private int foregroundHeight;
      /** Player whose information is displayed to the panel */
      private Entity player;
      /** Set to true only on the frame that a new image is set, to prevent unneccesary drawing */
      private boolean newImage = false;
      
      /**
        * Constructs TextPanel
        * @param charaName the border title
        * @param p the player Entity
        */
      public TextPanel(String charaName, Entity p) {
         setLayout(new BorderLayout(5,5));
         label = new javax.swing.JLabel();
         label.setFont(new Font("Arial", Font.BOLD, 15));
         add(label, BorderLayout.PAGE_END);
         setPreferredSize(new Dimension(650, 300));
           
         Border matte = BorderFactory.createMatteBorder(15, 15, 0, 15, new Color(0, 0, 0, 0));
         label.setBorder(matte);
           
         player = p;
      }
      
      /**
        * Displays an image to the foreground of the TextPanel using default position and size values
        * @param path the filepath of the image to be displayed
        */
      public void setForeground(String path) {
         setForeground(path, 25, 80, 640 - 50, 478 - 25 - 120); // set with defaults
      }
      
      /**
        * Displays an image to the foreground of the TextPanel using custom size and position values
        * @param path the filepath of the image to be displayed
        * @param x the desired x-position of the top left point of the image
        * @param y the desired y-position of the top left point of the image
        * @param width the desired horizontal side length of the image
        * @param height the desired vertical side length of the image
        */
      public void setForeground(String path, int x, int y, int width, int height) {
         foreground = imagePanel(path, foreground);
         foregroundX = x;
         foregroundY = y;
         foregroundWidth = width;
         foregroundHeight = height;
         foreground = foreground.getScaledInstance(foregroundWidth, foregroundHeight, Image.SCALE_DEFAULT);
      }
      
      /**
        * Sets foreground image to null
        */
      public void clearForeground() {
         foreground = null;
      }
      
      /**
        * Displays an image in the background of the TextPanel, automatically stretched across the
        * length of the screen
        * @param path the filepath of the image to be displayed
        */
      public void setBackground(String path) {
         background = imagePanel(path, background);
         background = background.getScaledInstance(654, 453, Image.SCALE_DEFAULT);
      }
   
      /**
        * Tries to set the desired image file to the desired image object
        * @param path the filepath of the image
        * @param im the image object
        * @return the newly set image object
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
        * @param message the message to be printed
        */
      public void typewriter(String message) {
         if (timer != null && timer.isRunning()) 
            return;
        
         // resets typewriter when button clicked again
         index = 0;
         label.setText("<html>");
              
         timer = new Timer(TEXT_SPEED, 
            new AbstractAction() {
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
        * @param g a Graphics object, automatically passed in
        */
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         g.clearRect(0, 0, getWidth(), getHeight());
         
         // Draw the background
         if (background != null) {
            g.drawImage(background, 0, 0+21*5, this);
         }
         
         board = imagePanel("assets/BattleUIPanel.png", board);
         status = imagePanel("assets/status" + player.getStatus() + ".png", status);
         
         board = board.getScaledInstance(640, 640, Image.SCALE_DEFAULT); // Board is scaled up by 5
         g.drawImage(board, 0, 0, this);
         
         status = status.getScaledInstance(48, 48, Image.SCALE_DEFAULT);
         g.drawImage(status, 558, 18+21*5, this);
         
         // Draw the foreground
         if (foreground != null) {
            g.drawImage(foreground, foregroundX, foregroundY, this);
         }
         
         // Draw red section of health bar:
         double percentHealth = (double) player.getHealth() / player.getMaxHealth();
         int pixelsRed = HB_LENGTH - (int)(HB_LENGTH * percentHealth);
         g.setColor(Color.RED);
         if (pixelsRed > HB_LENGTH) {
            pixelsRed = HB_LENGTH; // clamp
         }
         g.fillRect(80, 35 + 21*5, pixelsRed*5, HB_WIDTH*5);
         
         repaint();
      }
   }
}