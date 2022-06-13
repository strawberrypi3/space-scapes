/**
  * Represents a single move that can be taken by a character. A move can result in
  * stat changes (such as deducting the target's HP) and has certain probabilities 
  * of successfully occuring. A move can also make a target change its status.
  */
public class Move {
   /** The status effect that can be invoked on opponent when Move is dealt */
   private int statusEffect;
   /**
     * The strength (in Entity health) of the move. Positive value will add to self health,
     * negative value will subtract from opponent's health
     */
   private int strength;
   /** Probability, from 0.0 (0%) to 1.0 (100%), of attack (strength) being dealt successfully. */
   private double probability;
   /**
     * Probability, from 0.0 (0%) to 1.0 (100%), of status effect being dealt on 
     * opponent successfully. 
     */
   private double statusProbability;
   /** Player-visible name of the Move */
   private String name;
   
   /**
     * @param se the move's status effect
     * @param s  the move's strength (negative for attack, positive for cure)
     * @param p  the move's probability (0.0 to 1.0) of dealing damage/cure as per s
     * @param sp the move's probability (0.0 to 1.0) of dealing status effect as per se
     * @param n  the name of the move
     */
   public Move(int se, int s, double p, double sp, String n) {
      statusEffect = se;
      strength = s;
      probability = p;
      statusProbability = sp;
      name = n;
   }
   
   public String getName() {
      return name;
   }
   
   public void setProbability(double p) {
      probability = p;
   }
   
   public void setStatusProbability(double sp) {
      statusProbability = sp;
   }
   
   public double getProbability() {
      return probability;
   }
   
   public double getStatusProbability() {
      return statusProbability;
   }
   
   /**
     * Gives an attack's result in the form of an array, accounting for probability
     * @return a two-element int array where first element represents damage performed (negative
     *         implies damage to opponent, positive implies cure to self) and second element
     *         represents status effect
     */
   public int[] deal() {
      int[] attack = new int[2];
      if (probability > Math.random()) {
         attack[0] = strength;
      }
      if (statusProbability > Math.random()) {
         attack[1] = statusEffect;
      }
      return attack;
   }
}