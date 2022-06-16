import java.util.Scanner;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
  * The overall "script" of the game. Contains a GameWindow object and guides
  * the player through the story of the game.
  */
public class ChoiceSystem {
   /** The window that the entire game is displayed on */
	private static GameWindow window;
   /** The player Entity whose health is displayed to the window */
	private static Entity player;
   /** The player's current answer to the last button prompt */
	private static String answer;
   
   // The following methods have no returns or parameters and simply guide the player
   // through the story:
	public static void ignoreStory() {
		window.write("Program terminated.");
		window.addButton(">");
		answer = Action.waitForAnswer();
		window.setVisible(false);
		window.dispose();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}
   
	public static void closeWindow() {
		window.setVisible(false);
		window.dispose();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}
   
	public static void acknowledgeStory() {
		window.addButton(">");
		window.write("Loading..........");
		answer = Action.waitForAnswer();
      window.setBackground("assets/alert2.jpg");
		window.write("\"Hey you! Yeah you, the one looking at the screen! I need your help.\"");
		answer = Action.waitForAnswer();
		window.write("\"I don't know how your universe is doing, but mine is in grave danger.\"");
		answer = Action.waitForAnswer();
		window.write("\"We're currently being invaded by aliens called conquerors, and we need help.\"");
		answer = Action.waitForAnswer();
		window.write("\"I sent a distress signal through the quantum barrier, and somehow you saw it.\"");
		answer = Action.waitForAnswer();
		window.write("\"I need your help to guide my decisions.\"");
		answer = Action.waitForAnswer();
		window.write("\"Here, I'll connect my vision to your screen...\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/wasteland.jpg");
		window.write("\"Okay. These are the Wastes.\"");
		answer = Action.waitForAnswer();
		window.write("\"This is what's left of the world's greatest cities.\"");
		answer = Action.waitForAnswer();
		window.write("\"I am glad you are here.\"");
		answer = Action.waitForAnswer();
		window.write("\"Shhh, I can hear some conquerors.\"");
		answer = Action.waitForAnswer();
		window.write("\"Help me out... I'm gonna need your advice.\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("Should I follow them or search for survivors?");
		window.addButton("Follow");
		window.addButton("Search");
		answer = Action.waitForAnswer();
		window.deleteButton("Follow");
		window.deleteButton("Search");
		if (answer.equals("Follow")) {
			window.addButton(">");
			window.write("\"Alright, here we go...\"");
			answer = Action.waitForAnswer();
			window.setBackground("assets/mothership1.jpg");
			window.write("\"Wow, look! Is that the mothership?\"");
			answer = Action.waitForAnswer();
			window.write("\"Let's go inside.\"");
			mothership();
		} else if (answer.equals("Search")) {
			window.addButton(">");
			window.write("\"Okay. I must be careful and try and not to get captured.\"");
			answer = Action.waitForAnswer();
			window.write("\"I think that's the City, off in the distance.\"");
			city();
		}
	}
   
	public static void city() {
		answer = Action.waitForAnswer();
		window.setBackground("assets/wasteland2.jpg");
		window.write("\"I should probably tell you a bit about our world.\"");
		answer = Action.waitForAnswer();
		window.write("\"Our version of Earth used to be a lot like yours.\"");
		answer = Action.waitForAnswer();
		window.write("\"A few years ago, some scientists discovered how to traverse quantum plains.\"");
		answer = Action.waitForAnswer();
		window.write("\"This allowed us to communicate with other universes.\"");
		answer = Action.waitForAnswer();
		window.write("\"Unfortunately, some aliens managed to intercept our quantum signaling.\"");
		answer = Action.waitForAnswer();
		window.write("\"They triangulated Earth's location and came to conquer us.\"");
		answer = Action.waitForAnswer();
		window.write("\"Ever since the aliens came, humanity has split.\"");
		answer = Action.waitForAnswer();
		window.write("\"Some of us are governors. They're rich and powerful, but are hated.\"");
		answer = Action.waitForAnswer();
		window.write("\"The rest of us accepted our fate as scavengers.\"");
		answer = Action.waitForAnswer();
		window.write("\"I've stayed out of the city, so I've been avoiding both clans...\"");
		answer = Action.waitForAnswer();
		window.write("\"...but it's probably time for me to choose one.\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/city1.jpg");
		window.write("\"Ah, look! We've made it to the city.\"");
		answer = Action.waitForAnswer();
		city2();
	}
   
	public static void city2() {
      window.clearAllButtons();
      window.setBackground("assets/city1.jpg");
      window.addButton(">");
		window.setForeground("assets/human_guard.png", 150, 170, 130, 478 - 25 - 120);
		window.write("GUARD: \"Hello, and welcome to the city.\"");
		answer = Action.waitForAnswer();
		window.write("GUARD: \"Before you can enter, please tell me your clan.\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("GUARD: \"Are you a governor or a scavenger?\"");
		window.addButton("Governor");
		window.addButton("Scavenger");
		answer = Action.waitForAnswer();
		window.deleteButton("Governor");
		window.deleteButton("Scavenger");
      window.clearForeground();
		if (answer.equals("Governor")) {
			governors();
		} else if (answer.equals("Scavenger")) {
			scavengers();
		}
	}
   
	public static void governors() {
      window.setBackground("assets/vote.jpg");
		window.addButton(">");
      window.write("\"Cool, I'll run for office.\"");
      answer = Action.waitForAnswer();
		window.write("\"Looks like the people are looking for a new president.\"");
		answer = Action.waitForAnswer();
		window.write("\"This could be our chance to save the world!!\"");
		answer = Action.waitForAnswer();
		window.write("\"Should I run for president?\"");
		window.deleteButton(">");
		window.addButton("Yes");
		window.addButton("No");
		answer = Action.waitForAnswer();
		window.deleteButton("Yes");
		window.deleteButton("No");
		if (answer.equals("Yes")) {
			intel();
		} else if (answer.equals("No")) {
			end4();
		}
	}
   
	public static void intel() {
		window.addButton(">");
		window.write("\"Awesome!\"");
		answer = Action.waitForAnswer();
		window.write("\"The election will take some time, but the quantum universe is weird.\"");
		answer = Action.waitForAnswer();
		window.write("\"We can manipulate time in our communication.\"");
		answer = Action.waitForAnswer();
		window.write("\"I'll log off right now, and call you back a month later for me.\"");
		answer = Action.waitForAnswer();
		window.write("\"On your end it should only take a few seconds.\"");
		answer = Action.waitForAnswer();
		window.write("\"Here we go...\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/static.jpg");
		window.write("\"-- Ending call --\"");
		answer = Action.waitForAnswer();
		window.write("-- ... --");
		answer = Action.waitForAnswer();
		window.write("-- ALERT! New call! --");
		answer = Action.waitForAnswer();
      window.setBackground("assets/palace.jpg");
		window.write("\"Hello again! It's been a couple of months over here since we last talked.\"");
		answer = Action.waitForAnswer();
		window.write("\"I got elected president! And get this - I now have access to some intel.\"");
		answer = Action.waitForAnswer();
		window.write("\"This could be exactly what we need in order to drive off the aliens.\"");
		answer = Action.waitForAnswer();
		window.write("\"Here, let's open the intel together...\"");
		answer = Action.waitForAnswer();
		window.write("-- OPENING INTEL --");
		answer = Action.waitForAnswer();
		window.setBackground("assets/controls6.jpg");
		window.write("-- skdljhvnowiauhnjlkfsdgnkjlsjadhfnjlkcjasnielukjvfasnkmlcn --");
		answer = Action.waitForAnswer();
		window.write("\"Oh, I'll need to decode it. One second...\"");
		answer = Action.waitForAnswer();
		window.write("-- DECODING TEXT --");
		answer = Action.waitForAnswer();
		window.write("'Hello. This is Commander Sankt of Squad 0i8.'");
		answer = Action.waitForAnswer();
		window.write("'This is confirmation of our ultimate plan to conquer the universe.'");
		answer = Action.waitForAnswer();
		window.write("'Our architects have drafted blueprints for a majestic paradise city.'");
		answer = Action.waitForAnswer();
		window.write("'We will build this city and make it the center of the developed multiverse.'");
		answer = Action.waitForAnswer();
		window.write("'Unfortunately, due to our species' low population, we need more free labor.'");
		answer = Action.waitForAnswer();
		window.write("'By kidnapping humans, we can use their labor to build our majestic world.'");
		answer = Action.waitForAnswer();
		window.write("'Our empire will be so powerful that it will be virtually unstoppable.'");
		answer = Action.waitForAnswer();
		window.write("'The only way to stop us would be to delete the entire universe at its core.'");
		answer = Action.waitForAnswer();
		window.write("'In the second part of this intel, I will disclose more information.'");
		answer = Action.waitForAnswer();
		window.write("'But please be aware that opening the second part is dangerous.'");
		answer = Action.waitForAnswer();
		window.write("'It could pose a security threat.'");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("\"Oh my... Should i read the rest or take action now?\"");
		window.addButton("Read");
		window.addButton("Take action");
		answer = Action.waitForAnswer();
		window.deleteButton("Read");
		window.deleteButton("Take action");
		if (answer.equals("Read")) {
			recover();
		} else if (answer.equals("Take action")) {
			tell();
		}
	}
   
	public static void recover() {
		window.addButton(">");
		window.write("\"This could change everything. We're so close.\"");
		answer = Action.waitForAnswer();
		window.write("\"In order to decode the rest of the message, I need your help.\"");
		answer = Action.waitForAnswer();
		window.write("\"Could you upload your device's IP address?\"");
		window.clearAllButtons();
		window.addButton("UPLOAD INFO");
		answer = Action.waitForAnswer();
		window.clearAllButtons();
		fourWall();
	}
   
	public static void fourWall() {
      JOptionPane.showMessageDialog(null, "Ah.", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      JOptionPane.showMessageDialog(null, "So that's where you live.", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      JOptionPane.showMessageDialog(null, "Universe number 2309868918123.", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      JOptionPane.showMessageDialog(null, "Perfect, now I know.", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      JOptionPane.showMessageDialog(null, "Such a foolish human... I can't believe you fell for it.", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      JOptionPane.showMessageDialog(null, "Did you actually think this was just a game?", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      JOptionPane.showMessageDialog(null, "We are coming for you next.", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      JOptionPane.showMessageDialog(null, "Thank you... " + System.getProperty("user.name") + ".", "27oyirnc83whjoq938y", JOptionPane.ERROR_MESSAGE);
      closeWindow();
	}
   
	public static void tell() {
		window.addButton(">");
		window.write("\"After a heavy debate, I know what must be done.\"");
		String answer = Action.waitForAnswer();
		window.write("\"To save your galaxies and all the remaining ones, I must delete this one.\"");
		answer = Action.waitForAnswer();
		window.write("\"I know I haven't known you for long, but it was fun while it lasted.\"");
		answer = Action.waitForAnswer();
		window.write("\"Maybe, for you, this is just some small part of your life...\"");
		answer = Action.waitForAnswer();
		window.write("\"But... can you at least promise to remember me?\"");
		answer = Action.waitForAnswer();
		window.write("\"Thank you, and... goodbye.\"");
      window.deleteButton(">");
		window.addButton("DELETE THE UNIVERSE");
		answer = Action.waitForAnswer();
		window.write("UNIVERSE ERASED");
      window.deleteButton("DELETE THE UNIVERSE");
		window.addButton(">");
      answer = Action.waitForAnswer();
		window.clearAllButtons();
		ending("HERO");
	}
   
	public static void end4() {
		window.addButton(">");
		window.write("\"Oh... okay. Well, then I guess I'll just live a normal life.\"");
		answer = Action.waitForAnswer();
		window.write("\"To be honest, I was hoping you would lead me to save my people...\"");
		answer = Action.waitForAnswer();
		window.write("\"But I guess I understand. You have your own life, too.\"");
		answer = Action.waitForAnswer();
		window.write("\"In that case, I guess we don't need to communicate anymore.\"");
		answer = Action.waitForAnswer();
		window.write("\"I'll close this call line... forever...\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/static.jpg");
		window.deleteButton(">");
		ending("LET DOWN");
	}
   
	public static void scavengers() {
		window.addButton(">");
		window.write("\"Sounds like a plan. Scavenger!\"");
		String answer = Action.waitForAnswer();
		window.setBackground("assets/scavengers1.jpg");
		window.write("SCAVENGER: \"Hello, and welcome to our clan.\"");
		answer = Action.waitForAnswer();
		window.write("\"Um, hello to you too.\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("SCAVENGER: \"Do you want to go solo or join us?\"");
		window.addButton("Solo");
		window.addButton("Join");
		answer = Action.waitForAnswer();
		window.deleteButton("Solo");
		window.deleteButton("Join");
		if (answer.equals("Solo")) {
			solo();
		} else if (answer.equals("Join")) {
			join();
		}
	}
   
	public static void solo() {
		window.addButton(">");
		window.write(".....");
		answer = Action.waitForAnswer();
		window.write("SCAVENGER: \"You think you can survive without us, huh?\"");
		answer = Action.waitForAnswer();
		window.write("\"Uh, no... I'm just more of a lone wolf, that's all.\"");
		answer = Action.waitForAnswer();
		window.write("SCAVENGER: \"Well. Lone wolves don't survive long without a pack...\"");
		window.setBackground("assets/scavengers3.jpg");
		answer = Action.waitForAnswer();
		window.clearAllButtons();
		Move[] enemyMoveset = new Move[3];
		enemyMoveset[0] = new Move(0, -50, 1.0, 0.0, "Magic Spell");
		enemyMoveset[1] = new Move(1, -10, 0.2, 0.8, "Poison Stab");
		enemyMoveset[2] = new Move(0, 40, 1.0, 0.0, "Band-aid");
		Entity scavengers = new Entity("Scavenger Clan", 200, 40, enemyMoveset);
		boolean battleWon = window.doBattle(scavengers, "assets/battle.wav");
		if (battleWon) {
			steal();
		} else {
			bait();
		}
	}
   
	public static void join() {
		window.addButton(">");
		window.write("SCAVENGER: \"You chose correctly, we kill traitors and use them as bait.\"");
		answer = Action.waitForAnswer();
		window.write("SCAVENGER: \"Come with us, we'll take you to the haven. You can live here forever.\"");
		answer = Action.waitForAnswer();
		window.write("\"Hmm... I guess this works.\"");
		answer = Action.waitForAnswer();
		window.write("\"I was thinking things would be a bit more exciting, but... okay.\"");
		answer = Action.waitForAnswer();
		window.write("\"At least we made it to the other humans.\"");
		answer = Action.waitForAnswer();
		window.write("\"Honestly, they don't look like they need much saving anyways.\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/haven.jpg");
		window.write("SCAVENGER: \"Ah, here we are. This is the Haven.\"");
		answer = Action.waitForAnswer();
		window.write("SCAVENGER: \"Come, join our movement. The movement of the people.\"");
		answer = Action.waitForAnswer();
		window.write("\"Well, guess I don't need your help anymore.\"");
		answer = Action.waitForAnswer();
		window.write("\"Thanks for your help!\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		ending("SCAVENGER");
	}
   
	public static void steal() {
      window.setBackground("assets/desert.jpg");
		window.addButton(">");
		window.write("\"Phew. That was a close one.\"");
		answer = Action.waitForAnswer();
		window.write("\"Thanks for your amazing strategical thinking!\"");
		answer = Action.waitForAnswer();
		window.write("\"Hmm... looks like there's something on this scavenger's body.\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("\"Should I steal it or leave?\"");
		window.addButton("Steal");
		window.addButton("Leave");
		answer = Action.waitForAnswer();
		window.deleteButton("Steal");
		window.deleteButton("Leave");
		if (answer.equals("Steal")) {
			timeLoop();
		} else if (answer.equals("Leave")) {
			starve();
		}
	}
   
	public static void timeLoop() {
		window.setForeground("assets/time-travel-gadget.png", 130, 230, 400, 300);
		window.addButton(">");
		window.write("\"Hmm... it seems to be some sort of machine.\"");
		answer = Action.waitForAnswer();
		window.write("\"Oh, a button! What happens if I press i-\"");
		answer = Action.waitForAnswer();
		window.write("--QUANTUM TIME TRAVEL INITIATED--");
		answer = Action.waitForAnswer();
		window.write("--JUMPING BACK 30 SECONDS--");
		answer = Action.waitForAnswer();
		window.clearAllButtons();
		timeLoop(); // loop infinitely (RECURSION ending)
	}
   
	public static void starve() {
		window.addButton(">");
		window.write("\"Okay. I don't really know where to go... and there's not much water.\"");
		answer = Action.waitForAnswer();
		window.write("\"I'm getting really thirsty...\"");
		answer = Action.waitForAnswer();
		window.write("\"Ah...\"");
		answer = Action.waitForAnswer();
		window.write("\"...w...a...t...e...r...\"");
		answer = Action.waitForAnswer();
		window.write("...");
		answer = Action.waitForAnswer();
		window.write("-- SYSTEM OFFLINE --");
      window.setBackground("assets/static.jpg");
		answer = Action.waitForAnswer();
		ending("STRANDED");
	}
   
	public static void bait() {
		window.setBackground("assets/Slide74.jpg");
		window.addButton(">");
		window.write("\"*GASP* *PANT*\"");
		answer = Action.waitForAnswer();
		window.write("SCAVENGER: \"You are weak. You are the perfect bait for the conquerors.\"");
		answer = Action.waitForAnswer();
		window.write("\"WAIT, NOOOOOOOO!!\"");
		answer = Action.waitForAnswer();
		window.write("-- CALL DISCONNECTED --");
      window.setBackground("assets/static.jpg");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		ending("BAIT");
	}
   
	public static void mothership() {
		window.setBackground("assets/interior-dock.jpg"); 
		window.write("\"Hmm... this place is massive.\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("\"Should I go find the weapons arsenal or the control room?\"");
		window.addButton("Weapons Arsenal");
		window.addButton("Control Room");
		answer = Action.waitForAnswer();
		window.deleteButton("Weapons Arsenal");
		window.deleteButton("Control Room");
		if (answer.equals("Weapons Arsenal")) {
			fight();
		} else if (answer.equals("Control Room")) {
			hack();
		}
	}
   
	public static void fight() {
		window.setBackground("assets/interior-weapons.jpg"); 
		window.addButton(">");
		window.write("\"I think the weapons are this way.\"");
		String answer = Action.waitForAnswer();
		window.write("\"...wait... did you hear that?\"");
		answer = Action.waitForAnswer();
		window.write("\"I think there’s an alien nearby...\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"Who is there?\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"Come out, human dirt!\"");
		answer = Action.waitForAnswer();
      window.setForeground("assets/guard.png", 15, 175, 620, 350);
		window.deleteButton(">");
		Move[] moveset = new Move[3];
		moveset[0] = new Move(0, -50, 1.0, 0.0, "Ray Gun");
		moveset[1] = new Move(2, -20, 0.8, 0.5, "Speak BlorgBlorg");
		moveset[2] = new Move(0, 80, 1.0, 1.0, "a bottle of prescription Zorg pills");
		Entity enemy = new Entity("Private BlorgBurger", 250, 60, moveset);
		boolean battleWon = window.doBattle(enemy, "assets/battle.wav");
		if (battleWon) {
         window.clearForeground();
			space();
		} else {
         window.addButton(">");
         window.write("ALIEN: \"Hah! That's what you get!\"");
         answer = Action.waitForAnswer();
         window.write("ALIEN: \"I'm taking you to the brig!\"");
         answer = Action.waitForAnswer();
         window.deleteButton(">");
			arenaChoice();
		}
	}
   
	public static void space() {
		window.addButton(">");
		window.write("\"Phew... thanks for getting me through that.\"");
		answer = Action.waitForAnswer();
		window.write("-- PASSENGERS, PLEASE SECURE YOUR BELONGINGS --");
		answer = Action.waitForAnswer();
		window.write("-- WE WILL NOW BE LAUNCHING TO OUTER SPACE --");
		answer = Action.waitForAnswer();
		window.write("*ZOOOOOOOOOOOOOOMMMMMMMMMM*");
		answer = Action.waitForAnswer();
		window.write("\"...holy cow, am I in space? Woah.\"");
		answer = Action.waitForAnswer();
		window.write("\"I’m not sure if this is the best idea...\"");
		answer = Action.waitForAnswer();
		window.write("\"Should I find an escape pod, or find a hiding spot?\"");
		window.deleteButton(">");
		window.addButton("Escape Pod");
		window.addButton("Hiding Spot");
		answer = Action.waitForAnswer();
		window.deleteButton("Escape Pod");
		window.deleteButton("Hiding Spot");
		if (answer.equals("Escape Pod")) {
			malfunction();
		} else if (answer.equals("Hiding Spot")) {
			vent();
		}
	}
   
	public static void vent() {
		window.setBackground("assets/vent2.jpg");
		window.addButton(">");
		window.write("\"I hope this is a good enough hiding spot.\"");
		String answer = Action.waitForAnswer();
		window.write("ALIEN: \"I smell human scum... you can’t hide from us!!\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"Ha, I can smell you!\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"You’re going to the brig like the rest of your like!\"");
		answer = Action.waitForAnswer();
		arenaChoice();
	}
   
	public static void malfunction() {
		window.setBackground("assets/escape-pod.jpg");
		window.addButton(">");
		window.write("\"Aha, I think this is an escape pod! Perfect.\"");
		answer = Action.waitForAnswer();
		window.write("\"Let’s see here... this button looks like a planet!\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/controls5.jpg");
		window.write("-- Beep! Setting course for plsdksjdhflkjwn-- -- --");
		answer = Action.waitForAnswer();
		window.write("ERROR ERROR ERROR EROROREO REOERREEROER-----");
		answer = Action.waitForAnswer();
		window.write("\"OH NO, what’s happening?! I think it’s-\"");
		answer = Action.waitForAnswer();
		window.write("-- LAUNCHING POD --");
		answer = Action.waitForAnswer();
		window.setBackground("assets/galaxy3.jpg");
		window.write("\"AHHHHHHHHHHH!!!!!!!!\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/galaxy2.jpg");
		window.write(".");
		answer = Action.waitForAnswer();
		window.write("..");
		window.setBackground("assets/galaxy1.jpg");
		answer = Action.waitForAnswer();
		window.write("...");
		window.setBackground("assets/peacePlanet.jpg");
		answer = Action.waitForAnswer();
		window.write("\"...where am I?\"");
		answer = Action.waitForAnswer();
		window.write("\"Wow... it looks like a new planet.\"");
		answer = Action.waitForAnswer();
		window.write("\"But this one looks... peaceful?\"");
		answer = Action.waitForAnswer();
		window.write("\"I think this planet could support human life.\"");
		answer = Action.waitForAnswer();
		window.write("\"I guess we have a big choice to make.\"");
		answer = Action.waitForAnswer();
		window.write("\"I could somehow try and go back to Earth and save humans...\"");
		answer = Action.waitForAnswer();
		window.write("\"Or... I could stay here, and live peacefully... forever.\"");
		answer = Action.waitForAnswer();
      window.deleteButton(">");
		window.addButton("Return to People");
		window.addButton("Live Happily");
      window.write("What should I do?");
		answer = Action.waitForAnswer();
		window.clearAllButtons();
		if (answer.equals("Return to People")) {
			travel();
		} else if (answer.equals("Live Happily")) {
			end3();
		}
	}
   
	public static void end3() {
		window.addButton(">");
		window.write("\"You’re right. Maybe humanity deserves this, anyways.\"");
		answer = Action.waitForAnswer();
		window.write("\"I think... I think this is good.\"");
		answer = Action.waitForAnswer();
		window.write("\"Thank you, friend. I guess this is goodbye.\"");
		answer = Action.waitForAnswer();
		window.write("\"Signing off now...\"");
		answer = Action.waitForAnswer();
		window.write("...");
		answer = Action.waitForAnswer();
		window.write("..");
		answer = Action.waitForAnswer();
		window.write(".");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.setBackground("assets/static.jpg");
		ending("PEACE");
	}
   
	public static void travel() {
		window.addButton(">");
		window.write("\"You’re right, I have to return to save them. It’s only fair.\"");
		answer = Action.waitForAnswer();
		window.write("\"Let’s try to get this escape pod to actually work this time...\"");
		answer = Action.waitForAnswer();
		window.write("\"Cool, it seems to be intact.\"");
		answer = Action.waitForAnswer();
		window.write("\"Here we go. To Earth... for real this time.\"");
		answer = Action.waitForAnswer();
		window.write("-- DESTINATION SET FOR EARTH --");
		answer = Action.waitForAnswer();
		window.write("-- BLASTING OFF --");
		answer = Action.waitForAnswer();
      window.setBackground("assets/galaxy2.jpg");
		window.write("-- ZOOOOOOOOOMMMMMMMMM --");
		answer = Action.waitForAnswer();
      window.setBackground("assets/galaxy1.jpg");
		window.write("-- ... --");
		answer = Action.waitForAnswer();
		window.write("-- DESTINATION REACHED --");
		answer = Action.waitForAnswer();
		window.setBackground("assets/wasteland2.jpg");
		window.write("\"Hey, it worked this time!\"");
		answer = Action.waitForAnswer();
		window.write("\"Looks like we landed right in front of the city.\"");
		answer = Action.waitForAnswer();
		window.write("\"Let’s go inside!!\"");
		answer = Action.waitForAnswer();
		city2();
	}
   
	public static void arenaChoice() {
      window.clearAllButtons();
      window.addButton(">");
		window.setBackground("assets/arena1.jpg");	
      window.setForeground("assets/guard.png", 15, 175, 620, 350);
		window.write("ALIEN: \"Here we are, wimpy human.\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"Just because I’m nice, I’ll give you a choice.\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"Normally we’d send you straight to the arena...\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"But if you really want, I’ll be nice...\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"...and let you work in the trash compactor instead!\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"What’ll it be?\"");
		window.deleteButton(">");
		window.addButton("Fight");
		window.addButton("Work");
		answer = Action.waitForAnswer();
		window.deleteButton("Fight");
		window.deleteButton("Work");
		if (answer.equals("Fight")) {
			arena();
		} else if (answer.equals("Work")) {
			labor();
		}
	}
   
	public static void labor() {
		window.addButton(">");
		window.write("ALIEN: \"Good choice, albeit a bit anticlimactic.\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/trash-site2.jpg");
      window.clearForeground();
		window.write("ALIEN: \"This is the trash compactor.\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"You will work here for the rest of your miserable life.\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"Your pay will be zero dollars.\"");
		answer = Action.waitForAnswer();
		window.write("ALIEN: \"Bye bye! HAHAHAHAHAHA!!!!\"");
		answer = Action.waitForAnswer();
		window.write("\"NOOOOOOOOOOO!!!!!!\"");
		answer = Action.waitForAnswer();
		window.write("\"I can’t believe you would lead me here, player.\"");
		answer = Action.waitForAnswer();
		window.write("\"I should never have trusted y-\"");
		answer = Action.waitForAnswer();
      window.setForeground("assets/guard.png", 15, 175, 620, 350);
		window.write("ALIEN: \"Who are you talking to? No messaging allowed!\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/static.jpg");
		window.write("-- CONNECTION LOST --");
      window.clearForeground();
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		ending("PRISONER");
	}
   
	public static void hack() {
		window.addButton(">");
		window.write("\"Hmm, the room should be just around this corner.\"");
		String answer = Action.waitForAnswer();
		window.write("...");
		answer = Action.waitForAnswer();
		window.setBackground("assets/interior-control-room.jpg");
		window.write("\"WOAH! Look at all this tech.\"");
		answer = Action.waitForAnswer();
		window.write("\"It's every tech nerd's dream.\"");
		answer = Action.waitForAnswer();
		window.write("*SHAKE*");
		answer = Action.waitForAnswer();
		window.write("\"What was that?! What happened?\"");
		answer = Action.waitForAnswer();
		window.setBackground("assets/space-view.jpg");
		window.write("\"...Am I in SPACE?!\"");
		answer = Action.waitForAnswer();
		window.write("\"I think I can figure out a way to hack into the pilot controls.\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.setBackground("assets/controls7.jpg");
		window.write("\"Okay I'm in, should I re-route the ship back to earth or go to the conquerors' planet?\"");
		window.addButton("Earth");
		window.addButton("Conquerors' Planet");
		answer = Action.waitForAnswer();
		window.deleteButton("Earth");
		window.deleteButton("Conquerors' Planet");
		if (answer.equals("Earth")) {
			earth();
		} else if (answer.equals("Conquerors' Planet")) {
			planet();
		}
	}
   
	public static void planet() {
		window.addButton(">");
		window.write("\"Wow, this is so cool.\"");
      window.setBackground("assets/alienPlanet.jpg");
		answer = Action.waitForAnswer();
		window.write("\"I'm on an alien planet, crazy, if only my mom could see me now.\"");
		answer = Action.waitForAnswer();
		window.write("\"Too bad she got killed when I was a small child :(\" ");
		answer = Action.waitForAnswer();
		window.write("\"Oh hey, I think I see some other humans over there... are they prisoners?\"");
		answer = Action.waitForAnswer();
		window.write("\"I think I have to have a disguise if I am going to survive.\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("\"Should I disguise myself or follow the prisoners?\"");
		window.addButton("Disguise");
		window.addButton("Prisoners");
		answer = Action.waitForAnswer();
		window.deleteButton("Disguise");
		window.deleteButton("Prisoners");
		if (answer.equals("Disguise")) {
			party();
		} else if (answer.equals("Prisoners")) {
			window.addButton(">");
			window.write("\"OK, let's follow the prisoners. I just have to try not to be-\"");
			answer = Action.waitForAnswer();
         window.setForeground("assets/guard.png", 15, 175, 620, 350);
			window.write("ALIEN GUARD: \"Hey you! I see you! Get in line!!\"");
         answer = Action.waitForAnswer();
			window.write("ALIEN GUARD: \"Ha, so stupid. You didn't even have a disguise!\"");
			answer = Action.waitForAnswer();
			window.write("ALIEN GUARD: \"Alright everyone, to the arena!!\"");
			answer = Action.waitForAnswer();
         window.deleteButton(">");
			arena();
		}
	}
   
	public static void earth() {
		window.setBackground("assets/city2.jpg");
		window.addButton(">");
		window.write("\"Wew, glad I got off that ship, I am a little afraid of heights.\"");
		answer = Action.waitForAnswer();
		window.write("\"I just have to get out of this place, there are too many conquerors.\"");
		answer = Action.waitForAnswer();
		//scenery changes, heavy breathing,	twig snap, gun	load 
		window.write("???: \"Hello? Anybody there?\"");
		answer = Action.waitForAnswer();
		//tribe steps out	
		window.setBackground("assets/scavengers3.jpg");
		// add scavenger leader image
		window.write("SCAVENGERS: \"Ah. Hello fellow human, come join us. We have food and shelter.\"");
		answer = Action.waitForAnswer();
		window.write("\"Yes please, but how are we going to stop the conquerors?\"");
		answer = Action.waitForAnswer();
		window.write("SCAVENGERS: \"BWAHAHAHHAHAHHAAAHAHA\"");
		//laughter 
		window.write("\"I didn't think I was funny.\"");
		answer = Action.waitForAnswer();
		window.write("SCAVENGERS: \"Oh no, you have found the wrong tribe. We focus on survival.\"");
		answer = Action.waitForAnswer();
		
      window.write("SCAVENGERS: \"So, will you join us?\"");
      answer = Action.waitForAnswer();
		
		window.write("REBEL LEADER: \"Or don’t join them... join us. We want to stop the conqueors.\"");
		window.setForeground("assets/rebel-leader.png", 160, 200, 330, 420);
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("\"What should I do, do I hide or do I rebel?\"");
		window.addButton("Hide");
		window.addButton("Rebel");
		answer = Action.waitForAnswer();
		window.deleteButton("Hide");
		window.deleteButton("Rebel");
		if (answer.equals("Hide")) {
			end1();
		} else if (answer.equals("Rebel")) {
			shipBuild();
		}
	}
   
	public static void party() {
		window.addButton(">");
		window.write("\"Wow this clothing is so cool!! I want to stay here forever.\"");
		answer = Action.waitForAnswer();
      window.setForeground("assets/guard.png", 15, 175, 620, 350);
      window.write("ALIEN: \"Hey you...\"");
      answer = Action.waitForAnswer();
      window.write("ALIEN: \"What are you doing here?\"");
      answer = Action.waitForAnswer();
      window.write("ALIEN: \"Come on, everyone's at the party!!!!\"");
      answer = Action.waitForAnswer();
      window.setBackground("assets/party2.jpg");
      window.clearForeground();
		window.write("\"Woah! Look at this place!!\"");
		answer = Action.waitForAnswer();
		window.write("\"Thanks for your help, now I am going to go get one of those drinks over there.\"");
		answer = Action.waitForAnswer();
		window.write("—-System Offline-—");
      window.setBackground("assets/static.jpg");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		ending("PARTY");
	}
   
	public static void arena() {
      window.clearForeground();
		window.setBackground("assets/arena2.jpg");
		window.addButton(">");
		window.write("**llllllladies and gentlemen... welcome to...**");
		answer = Action.waitForAnswer();
		window.write("**...the 9276349th annual PRISON BASH!!!!!!**");
		answer = Action.waitForAnswer();
		window.write("CROWD: \"WOOOOOOOOHOOOOOOOOOO!!!!!!!!!!!\"");
		answer = Action.waitForAnswer();
		window.write("\"Holy cow, what is this place?!\"");
		answer = Action.waitForAnswer();
		window.setForeground("assets/guard.png", 15, 175, 620, 350);
		window.write("ALIEN GUARD: \"Hey you there, you’re up next.\"");
		answer = Action.waitForAnswer();
		window.write("\"What?! No, hey, let go of me!\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("ALIEN GUARD: \"Shut up! Which monster do you want to fight? Big or small?\"");
		window.addButton("Big");
		window.addButton("Small");
		answer = Action.waitForAnswer();
		window.deleteButton("Big");
		window.deleteButton("Small");
		if (answer.equals("Big")) {
			glory();
		} else if (answer.equals("Small")) {
			death1();
		}
	}
   
	public static void end1() {
		window.addButton(">");
      window.clearForeground();
		window.write("SCAVENGERS: \"We are glad you have chosen to stay.\"");
		answer = Action.waitForAnswer();
		window.write("SCAVENGERS: \"Let me show you our camp.\"");
		answer = Action.waitForAnswer();
      window.setBackground("assets/haven.jpg");
		window.write("\"Well, guess I'll just stay here for now.\"");
		answer = Action.waitForAnswer();
		window.write("\"Hey, at least I'm safe... I did go to space, after all.\"");
		answer = Action.waitForAnswer();
		window.write("\"I guess this is the end for now... see ya!\"");
		answer = Action.waitForAnswer();
      window.setBackground("assets/static.jpg");
      window.write("-- CALL DISCONNECTED --");
      answer = Action.waitForAnswer();
		window.deleteButton(">");
		ending("HAVEN");
	}
   
	public static void shipBuild() {
		window.setBackground("assets/mothership2.jpg");
      window.clearForeground();
		window.addButton(">");
		window.write("\"What is this place. . .and what is that?\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"That, my friend, is our ticket out of this crumbling planet.\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"Come aboard, we are about ready to launch.\"");
		answer = Action.waitForAnswer();
		window.write("\"Where are we going?\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"We are going to conquer the conquerors’ world.\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"One more thing, I have a very important question for you.\"");
		answer = Action.waitForAnswer();
		window.write("\"Well, what is it?\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("REBEL LEADER: \"Will you be a leader or follower on this expedition?\"");
		window.addButton("Leader");
		window.addButton("Follower");
		answer = Action.waitForAnswer();
		window.deleteButton("Leader");
		window.deleteButton("Follower");
		if (answer.equals("Leader")) {
			leader();
		} else if (answer.equals("Follower")) {
			death2();
		}
	}
   
	public static void ending(String text) {
		window.clearAllButtons();
		window.write("You got the " + text + " ending. Play again?");
		window.addButton("Yes");
		window.addButton("No");
		answer = Action.waitForAnswer();
		window.deleteButton("Yes");
		window.deleteButton("No");
		if (answer.equals("Yes")) {
         player.setHealth(player.getMaxHealth());
         player.setStatus(0);
			acknowledgeStory();
		} else if (answer.equals("No")) {
			closeWindow();
		}
	}
   
	public static void death1() {
		window.addButton(">");
      window.setForeground("assets/small_alien.png", 250, 300, 182, 277);
		window.write("\"Yeah, this guy looks weak. Let's take him down!\"");
		answer = Action.waitForAnswer();
		Move[] moveset = new Move[1];
		moveset[0] = new Move(0, Integer.MIN_VALUE, 1, 0, "Tap");
		Entity smallAlien = new Entity("Small Alien", 10000000, 10000000, moveset);
		window.deleteButton(">");
		boolean battleWon = window.doBattle(smallAlien, "assets/battle.wav");
		window.write("\"...huh.\"");
		window.addButton(">");
		answer = Action.waitForAnswer();
		window.write("\"What a way to go.\"");
		answer = Action.waitForAnswer();
		window.write("\"...\"");
		answer = Action.waitForAnswer();
		window.write("\"-- CALL DISCONNECTED --\"");
      window.setBackground("assets/static.jpg");
      window.clearForeground();
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		ending("ILLUSION");
	}
   
	public static void glory() {
      window.clearForeground();
		window.setBackground("assets/giant.jpg");
		Move[] bigMoveset = new Move[3];
		bigMoveset[0] = new Move(0, -90, 0.7, 0, "Body Slam");
		bigMoveset[1] = new Move(0, -50, 1.0, 0, "Tackle");
		bigMoveset[2] = new Move(0, -1, 1.0, 0, "a book of insults");
		Entity bigAlien = new Entity("BIG ALIEN", 250, 1, bigMoveset);
		boolean battleWon = window.doBattle(bigAlien, "assets/battle.wav");
		if (!battleWon) {
         window.addButton(">");
			window.write("-- CALL DISCONNECTED --");
         window.setBackground("assets/static.jpg");
         answer = Action.waitForAnswer();
			ending("ELIMINATED");
		} else {
         window.setBackground("assets/arena2.jpg");
			window.addButton(">");
			window.write("CROWD: \"...\"");
			answer = Action.waitForAnswer();
			window.write("CROWD: \"...WOOOOOOOOOHOOOOOOOOOOOO!!!!!\"");
			answer = Action.waitForAnswer();
         window.setForeground("assets/commander.png", 15, 230, 500, 330);
			window.write("CONQUEROR: \"You have fought valiantly, human one.\"");
			answer = Action.waitForAnswer();
			window.write("CONQUEROR: \"You may reside with our king if you pass one final test.\"");
			answer = Action.waitForAnswer();
			window.write("\"What is it? All I really want is to sleep.\"");
			answer = Action.waitForAnswer();
			window.write("CONQUEROR: \"Do you see those prisoners over there?\"");
			answer = Action.waitForAnswer();
			window.write("\"Yes, why?\"");
			answer = Action.waitForAnswer();
			window.deleteButton(">");
			window.write("CONQUEROR: \"Do you want to kill or save them?\"");
			window.addButton("Kill");
			window.addButton("Save");
			answer = Action.waitForAnswer();
			window.deleteButton("Kill");
			window.deleteButton("Save");
			if (answer.equals("Kill")) {
				royal();
			} else if (answer.equals("Save")) {
				execute();
			}
		}
	}
   
	public static void execute() {
		window.addButton(">");
		window.write("CROWD: *Gasp*");
		answer = Action.waitForAnswer();
		window.write("CONQUEROR: \"You chose wrong. Now you must face execution.\"");
		answer = Action.waitForAnswer();
		window.write("CONQUEROR: \"There is no mercy here.\"");
		answer = Action.waitForAnswer();
		window.write("\"Oh, NO...\"");
		answer = Action.waitForAnswer();
		Move[] moveset = new Move[1];
		moveset[0] = new Move(0, Integer.MIN_VALUE, 1, 0, "EXECUTE");
		Entity executioner = new Entity("SUPREME LEADER", 10000000, 10000000, moveset);
		window.deleteButton(">");
		boolean battleWon = window.doBattle(executioner, "assets/battle.wav");
      window.addButton(">");
      window.write("-- CONNECTION LOST --");
      window.clearForeground();
      window.setBackground("assets/static.jpg");
      answer = Action.waitForAnswer();
		ending("UNAVENGED");
	}
   
	public static void royal() {
		window.addButton(">");
		window.write("CROWD: \"WOOOOHOOOOOO!!!!!!!!\"");
		answer = Action.waitForAnswer();
		window.write("CONQUEROR: \"You have chosen wisely. Let us watch them burn.\"");
		answer = Action.waitForAnswer();
      window.write("CONQUEROR: \"Then, do you want to watch a movie?\"");
      answer = Action.waitForAnswer();
		window.write("\"Sure!\"");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
      window.clearForeground();
      window.setBackground("assets/static.jpg");
		ending("TRAITOR");
	}
   
	public static void death2() {
		window.addButton(">");
      window.setForeground("assets/rebel-leader.png", 160, 200, 330, 420);
		window.write("REBEL LEADER: \"You want to just be a follower?\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"No true friend of the revolution is a follower.\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"We are all leaders here! Leaders of a new world!\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"But it seems that you don't agree with our mindset.\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"Fellow rebels, take this foolishness as a lesson.\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"This is what happens to those who comply rather than rebel!!\"");
		answer = Action.waitForAnswer();
      window.deleteButton(">");
      Move[] moveset = new Move[3];
      moveset[0] = new Move(0, -120, 0.75, 0, "Energy Sword");
      moveset[1] = new Move(0, 60, 0.8, 0.8, "Twitter to gather support for his revolutionary cause");
      moveset[2] = new Move(2, -60, 0.75, 0.5, "Gravity Gun");
      Entity leader = new Entity("REBEL LEADER", 350, 80, moveset);
      boolean battleWon = window.doBattle(leader, "assets/rebel.wav");
      window.addButton(">");
      if (battleWon) {
         window.clearForeground();
         window.write("REBEL LEADER: \"Ungh... guess you beat me...\"");
         answer = Action.waitForAnswer();
         window.write("REBEL LEADER: \"I know you don't consider yourself a leader...\"");
         answer = Action.waitForAnswer();
         window.write("REBEL LEADER: \"...but the revolution could use someone like you.\"");
         answer = Action.waitForAnswer();
         window.write("REBEL LEADER: \"Someone who... *cough* ...who won't stop at anything.\"");
         answer = Action.waitForAnswer();
         window.write("REBEL LEADER: \"I hope you reconsider...\"");
         answer = Action.waitForAnswer();
         window.write("REBEL LEADER: \"...\"");
         answer = Action.waitForAnswer();
         window.write("...");
         answer = Action.waitForAnswer();
         window.write("*in the silence, you realize you killed a human being*");
         answer = Action.waitForAnswer();
         window.write("OTHER REBEL: \"How could you...?\"");
         answer = Action.waitForAnswer();
         window.write("ANOTHER REBEL: \"Let's make 'em pay for their actions!!\"");
         answer = Action.waitForAnswer();
         window.write("REBELS: \"FOR THE REVOLUTION!!!\"");
         answer = Action.waitForAnswer();
         window.write("\"No, wait, I-\"");
         answer = Action.waitForAnswer();
         window.setBackground("assets/static.jpg");
         window.write("-- CALL DISCONNECTED --");
         answer = Action.waitForAnswer();
         window.write("...");
         answer = Action.waitForAnswer();
         window.write("...");
         answer = Action.waitForAnswer();
         window.write("...what have I done?");
         answer = Action.waitForAnswer();
         window.deleteButton(">");
         ending("BAD");
      } else {
         window.write("-- CALL DISCONNECTED --");
         window.clearForeground();
         window.setBackground("assets/static.jpg");
         answer = Action.waitForAnswer();
         window.deleteButton(">");
         ending("COMPLIANT");
      }
	}
   
	public static void leader() {
		window.setBackground("assets/final_battlefield.jpg");
		window.addButton(">");
		window.write("...*After a long journey across the galaxy*...");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"Welcome to the conquerors world. Let’s get to work.\"");
		answer = Action.waitForAnswer();
		window.write("\"Yes, lets do it. Mwahahahaha!!\"");
		answer = Action.waitForAnswer();
		window.write("REBEL LEADER: \"Look over there, an alien! Kill it!!\"");
		answer = Action.waitForAnswer();
      window.setForeground("assets/small_alien.png", 250, 300, 182, 277);
		window.clearAllButtons();
		Move[] weakMoveset = new Move[1];
		weakMoveset[0] = new Move(0, 0, 0, 0, "Plead For Forgiveness");
		Entity weakAlien = new Entity("Small Alien", 1, 500, weakMoveset);
      window.deleteButton(">");
		boolean battleWon = window.doBattle(weakAlien, "assets/battle.wav"); //	impossible to lose 
		window.write("\"Hehehehe. I feel powerful! I could do this FOREVER!!!\"");
      window.addButton(">");
      window.clearForeground();
		answer = Action.waitForAnswer();
		window.write("???: \"Stop, I beg of you! Don't kill the innocent!\"");
		answer = Action.waitForAnswer();
      window.setForeground("assets/commander.png", 15, 230, 500, 330);
		Audio song = new Audio("assets/final boss.wav");
		song.play();
      window.setSong(song); // allows song to close on X-button pressed
		window.write("*The avatar looks up at the conqueror facing them*");
		answer = Action.waitForAnswer();
		window.write("*The battle rages around you*");
		answer = Action.waitForAnswer();
		window.write("*You look the conqueror in the eyes*");
		answer = Action.waitForAnswer();
		window.write("\"It ends here.\"");
      answer = Action.waitForAnswer();
		window.deleteButton(">");
		Move[] strongMoveset = new Move[6];
		strongMoveset[0] = new Move(0, -80, 0.8, 0, "Ray Gun");
		strongMoveset[1] = new Move(1, -20, 0.5, 0.9, "Poison Gas");
		strongMoveset[2] = new Move(2, 0, 0, 0.75, "Advanced Quantum Mechanics");
		strongMoveset[3] = new Move(3, -10, 1.0, 1.0, "Sleep Powder");
		strongMoveset[4] = new Move(0, 100, 1.0, 0.8, "Alien Regeneration");
		strongMoveset[5] = new Move(1, -50, 1.0, 0.2, "Tentacle Slap");
		Entity alienBoss = new Entity("CONQUEROR LEADER", 500, 50, strongMoveset);
		battleWon = window.doBattle(alienBoss);
      song.stop();
		if (battleWon) {
			evil();
		} else {
			window.addButton(">");
			window.write("-- CALL DISCONNECTED --");
         window.clearForeground();
         window.setBackground("assets/static.jpg");
			answer = Action.waitForAnswer();
			window.write("...maybe this is what you deserved...");
			answer = Action.waitForAnswer();
			ending("KARMA");
		}
	}
   
	public static void evil() {
      window.clearForeground();
      window.setBackground("assets/army.jpg");
		window.addButton(">");
		window.write("\"Ah. It feels good to destroy.\"");
		answer = Action.waitForAnswer();
		window.write("REBEL: \"That... that was Commander BlorgZorg himself!!\"");
		answer = Action.waitForAnswer();
		window.write("REBEL: \"You killed the leader! You are our true master!!\"");
		answer = Action.waitForAnswer();
		window.write("\"I... I am your master.\"");
		answer = Action.waitForAnswer();
		window.write("THE PEOPLE: \"You are our master!!\"");
		answer = Action.waitForAnswer();
		window.write("\"I AM YOUR MASTER!!!!\"");
		answer = Action.waitForAnswer();
		window.write("THE PEOPLE: \"YOU ARE OUR MASTER!! MASTER OF THE UNIVERSE!!\"");
		answer = Action.waitForAnswer();
		window.write("THE PEOPLE: \"ALL HAIL THE NEW RULER!!\"");
		answer = Action.waitForAnswer();
		window.write("\"All hail... all hail ME.\"");
		answer = Action.waitForAnswer();
		window.write("\"My, how the tables have turned. Look at me now, mother.\"");
		answer = Action.waitForAnswer();
		window.write("\"And you... I almost forgot about you. We're done here.\"");
		answer = Action.waitForAnswer();
		window.write("-- CALL DISCONNECTED--");
      window.setBackground("assets/static.jpg");
		answer = Action.waitForAnswer();
		window.write("...");
		answer = Action.waitForAnswer();
		window.write("...");
		answer = Action.waitForAnswer();
		window.write("...what have I done??");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		ending("FALLEN ANGEL");
	}
   
	public static void main(String[] args) {
		Move[] moveset = new Move[5]; //	Player will	have 4 moves in their moveset	
		//	Cures	self by 100	HP: 
		moveset[0] = new Move(0, 100, 1.0, 0.5, "Cure");
		//	High chance	of	poison, can	also deal 20 HP of damage:	
		moveset[1] = new Move(1, -20, 0.5, 0.8, "Snakebite");
		//	High chance	of	dealing 100	damage to enemy, very small chance of poison: 
		moveset[2] = new Move(1, -100, 0.8, 0.1, "Punch");
		moveset[3] = new Move(2, 0, 0.0, 1.0, "Speak Pig Latin"); // Confuses enemy 
		moveset[4] = new Move(3, 0, 0.0, 1.0, "Read \"Building Java Programs\" by Marty Stepp and Stuart Reges"); //	Makes	enemy	tired	
		player = new Entity("Avatar", 200, 50, moveset);
		window = new GameWindow(player);
		window.addButton(">");
		window.setBackground("assets/alert1.jpg");
		window.write("ALERT! ALERT! This universe is in grave danger.");
		answer = Action.waitForAnswer();
		window.deleteButton(">");
		window.write("Ignore or acknowledge alert?");
		window.addButton("Ignore");
		window.addButton("Acknowledge");
		answer = Action.waitForAnswer();
		window.deleteButton("Ignore");
		window.deleteButton("Acknowledge");
		if (answer.equals("Ignore")) {
			ignoreStory();
		} else if (answer.equals("Acknowledge")) {
		   acknowledgeStory();
      }
	}
}