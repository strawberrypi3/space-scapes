import java.awt.*; // to create windows & graphics
import java.awt.event.*; // to respond to clicks
import javax.swing.*; // more features for windows
import javax.swing.border.*; // border for textbox

public class TextBox {
   public TextBox(String name) {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.add(new jpanel(name));
      frame.pack();
      frame.setVisible(true);
   }
}

class jpanel extends javax.swing.JPanel {
   private String characterName;
   private int index = 0;
   private javax.swing.Timer timer;
   private javax.swing.JButton jButton1;
   private javax.swing.JLabel label;
   
    public jpanel(String charaName) {
        characterName = charaName;
        setLayout(new BorderLayout(5,5));
        jButton1 = new javax.swing.JButton();
        jButton1.setText("Press to proceed");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt)); // lambda expression (->): a short way of method writing
        label = new javax.swing.JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 15));
        add(label, BorderLayout.PAGE_END);
        add(jButton1, BorderLayout.NORTH);
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
   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        typewriter("Lorem ipsum dolor sit amet. Ex reprehenderit repellendus hic galisum perspiciatis hic porro quia qui nihil earum ut illo rerum et possimus pariatur!");
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
}