/**
  * Represents a player or enemy which can participate in a battle
  */
public class Entity {
   private String name;
   private int health;
   private int maxHealth;
   private int speed; // In battle, the Entity with the lower speed moves first
   private int status; // 0 = none, 1 = poison, 2 = confusion, 3 = tired
   private Move[] moveset;
   
   /**
     * Constructs an Entity
     * @param n - name
     * @param h - max health (and inital current health)
     * @param s - initial speed
     * @param m - moveset (array of moves)
     */
   public Entity(String n, int h, int s, Move[] m) {
      name = n;
      health = h;
      maxHealth = h;
      speed = s;
      moveset = m;
      status = 0;
   }
   
   public String getName() {
      return name;
   }
   
   public Move[] getMoveset() {
      return moveset;
   }
   
   public void recieveDamage(int change) {
      setHealth(health + change);
   }
   
   /**
     * Sets status and updates whatever stats can be immediately updated
     * @param s - the status being set
     */
   public void setStatus(int s) {
      if (status == s) { // no change
         return;
      } else {
         // Undo previous status effect:
         if (status == 2) { // confusion undo
            for (int i = 0; i < moveset.length; i++) {
               moveset[i].setProbability(moveset[i].getProbability() * 1.42);
               moveset[i].setStatusProbability(moveset[i].getStatusProbability() * 1.42);
            }
         } else if (status == 3) { // poison undo
            speed *= 2;
         }
      }
      
      status = s;
      
      // Poison (status == 1) is handled by the Battle class
      if (status == 2) { // apply confusion
         for (int i = 0; i < moveset.length; i++) {
            moveset[i].setProbability(moveset[i].getProbability() * 0.7);
            moveset[i].setStatusProbability(moveset[i].getStatusProbability() * 0.7);
         }
      } else if (status == 3) { // apply poison
         speed /= 2;
      }
   }
   
   public int getSpeed() {
      return speed;
   }
   
   public int getHealth() {
      return health;
   }
   
   public int getMaxHealth() {
      return maxHealth;
   }
   
   /**
     * Sets Entity health, but will not set health above the max health
     * @param h - the health to be set
     */
   public void setHealth(int h) {
      health = h;
      if (health > maxHealth) {
         health = maxHealth;
         System.out.println(name + " is at max health!");
      }
   }
   
   public int getStatus() {
      return status;
   }
   
   /**
     * Gets the name of the status
     * @return the name of the current status
     */
   public String getStatusName() {
      String name;
      if (status == 0) {
         return "regular";
      } else if (status == 1) {
         return "poison";
      } else if (status == 2) {
         return "confused";
      } else if (status == 3) {
         return "tired";
      }
      return "";
   }
}