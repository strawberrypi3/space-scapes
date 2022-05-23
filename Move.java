/**
  * Represents a single move that can be taken by a character. A move can result in
  * stat changes (such as deducting the target's HP) and has certain probabilities 
  * of successfully occuring. A move can also make a target change its status.
  */

public class Move {
   private int statusEffect; // See documentation
   private int strength; // Positive for healing, negative for attacking
   private double probability; // From 0.0 (0%) to 1.0 (100%), probability of attack occuring
   private double statusProbability; // Same as probability, but with status effect occuring
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
     * @return a two element array where first element represents strength,
               second element represents status effect
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