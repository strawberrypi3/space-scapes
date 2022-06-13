/**
  * Represents a player or enemy which can participate in a battle
  */
public class Entity {
   /** The desired player-visible name of the Entity */
   private String name;
   /** The current health of the Entity */
   private int health;
   /** The maximum possible health of the Entity */
   private int maxHealth;
   /** Speed of the Entity - can be used in battle to determine which Entity moves first */
   private int speed;
   /** 
     * The current Entity's status. 
     * 0 = no effect. 
     * 1 = poison, Entity's health will reduce by 20% every turn. 
     * 2 = confusion, every one of Entity's moves' accuracy is reduced by 30%. 
     * 3 = tired, Entity's speed is reduced by 50%.
     */
   private int status;
   /** All possible moves available to the Entity to use in battle. */
   private Move[] moveset;
   
   /**
     * Constructs an Entity
     * @param n name
     * @param h max health (and inital current health)
     * @param s initial speed
     * @param m moveset (array of moves)
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
   
   public boolean recieveDamage(int change) {
      return setHealth(health + change);
   }
   
   /**
     * Sets status and updates whatever stats can be immediately updated
     * @param s the status being set
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
         } else if (status == 3) { // sleep undo
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
      } else if (status == 3) { // apply tiredness
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
     * @param h the health to be set
     * @return if the entity is now at max health
     */
   public boolean setHealth(int h) {
      health = h;
      if (health > maxHealth) {
         health = maxHealth;
         return true;
      }
      return false;
   }
   
   public int getStatus() {
      return status;
   }
   
   /**
     * Gets the name of the status based on its integer value
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