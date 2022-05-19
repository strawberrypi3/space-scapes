public class Entity {
   private String name;
   private int health;
   private int maxHealth;
   private int speed;
   private int status;
   private Move[] moveset;
   
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
   
   public void setStatus(int s) {
      if (status == s) { // no change
         return;
      }
      status = s;
      
      // Poison (status == 1) is handled by the Battle class
      if (status == 2) {
         for (int i = 0; i < moveset.length; i++) {
            moveset[i].setProbability(moveset[i].getProbability() * 0.7);
            moveset[i].setStatusProbability(moveset[i].getStatusProbability() * 0.7);
         }
      } else if (status == 3) {
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
      } else {
         return "";
      }
   }
}