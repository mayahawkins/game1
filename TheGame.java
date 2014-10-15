import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;
import javalib.colors.*;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.geom.*;
import java.util.*;
import java.util.Random;
import java.lang.Object.*;
//import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
//import net.slashie.libjcsi.ConsoleSystemInterface;
//import net.slashie.libjcsi.CharKey;
//import java.util.concurrent.TimeUnit;


class Mouse{
	public Posn pinhole;
	public int width;
	public int length;
	public Color color;
	public int area;

	public Mouse(Posn pinhole, int width, int length, Color color){
		this.pinhole = pinhole;
		this.width = width;
		this.length = length;
		this.color = color;
		this.area = this.length * this.width;
	}
	public WorldImage mouseImage(){
		return new RectangleImage(this.pinhole, this.width, this.length, this.color);
	}
	 public Mouse moveMouse(String ke){
	 	if (ke.equals("right") && (this.pinhole.x + (this.width / 2) <= 500)){
	 		return new Mouse(new Posn(this.pinhole.x + 1, this.pinhole.y),
	 			this.width, this.length, this.color);
	 	}
	 	else if (ke.equals("left") && (this.pinhole.x - (this.width / 2) >= 0)){
	 		return new Mouse(new Posn(this.pinhole.x -1, this.pinhole.y),
	 			this.width, this.length, this.color);
	 	}
	 	else if (ke.equals("up") && (this.pinhole.y - (this.length / 2) >= 0)){
	 		return new Mouse(new Posn(this.pinhole.x, this.pinhole.y - 1),
	 			this.width, this.length, this.color);
	 	}
	 	else if (ke.equals("down") && (this.pinhole.y + this.length / 2) <= 500){
	 		return new Mouse(new Posn(this.pinhole.x, this.pinhole.y + 1),
	 			this.width, this.length, this.color);
	 	}
	 	else {
	 		return this;
	 	}
	}
	public Mouse mousePlacer(int x, int y) {
		return new Mouse(new Posn(x, y), this.width, this.length, this.color);
	}
}

class StickyPaper {
	public Posn sTPinhole;
	public int sTWidth;
	public int sTLength;
	public Color sTColor;

	StickyPaper(Posn sTPinhole, int sTWidth, int sTLength, Color sTColor){
	this.sTPinhole = sTPinhole;
	this.sTLength = sTLength;
	this.sTWidth = sTWidth;
	this.sTColor = sTColor;	
	}

	WorldImage stickyPaperImage(){
		return new RectangleImage(this.sTPinhole, this.sTWidth, 
								  this.sTLength, this.sTColor);
	}

	public boolean stuckHuh(Mouse mousePlayer) {
		return (mousePlayer.pinhole.x <= (500 - this.sTPinhole.x + (this.sTWidth / 2)))
			&& (mousePlayer.pinhole.x >= (this.sTPinhole.x - (this.sTWidth / 2)))
			&& (mousePlayer.pinhole.y <= (500 - this.sTPinhole.y + (this.sTLength / 2)))
			&& (mousePlayer.pinhole.y >= (this.sTPinhole.y - (this.sTLength / 2)));
	}

}

class Cat {
	public Posn cPinhole;
	public int cWidth;
	public int cLength;
	public Color cColor;

	Cat(Posn cPinhole, int cWidth, int cLength, Color cColor){
	this.cPinhole = cPinhole;
	this.cWidth = cWidth;
	this.cLength = cLength;
	this.cColor = cColor;	
	}

	WorldImage catImage(){
		return new RectangleImage(this.cPinhole, this.cWidth,
								  this.cLength, this.cColor);
	}

	public boolean feedCat(Mouse mousePlayer) {
		return (((mousePlayer.pinhole.x + (mousePlayer.width / 2)) >= (500 - (500 - (this.cPinhole.x - (this.cWidth / 2)))))
			&& ((mousePlayer.pinhole.y - (mousePlayer.length / 2)) >= (this.cPinhole.y - (this.cLength / 2)))
			&& ((mousePlayer.pinhole.y + mousePlayer.length / 2) <= (this.cPinhole.y + (this.cLength / 2))));
	}

	static Random catRand = new Random();
	public static int randomInt(int min, int max){
		return catRand.nextInt(((max - min) + 1) + min);
	}


public Cat catPlacer(int x, int y){
		return new Cat(new Posn(x, y), this.cWidth, this.cLength, this.cColor);
}
	public Cat catPlacer(){
		return new Cat(new Posn(500 - this.cWidth, randomInt((cLength / 2),
								 (500 - (cLength / 2)))), this.cWidth, this.cLength, this.cColor);
	}
}

class DeadMouse {
	public Posn dPinhole;
	public int dWidth;
	public int dLength;
	public Color dColor;

	public DeadMouse(Posn dPinhole, int dWidth, int dLength, Color dColor){
		this.dPinhole = dPinhole;
		this.dWidth = dWidth;
		this.dLength = dLength;
		this.dColor = dColor;
	}
	public WorldImage deadMouseImage(){
		return new RectangleImage(this.dPinhole, this.dWidth, this.dLength, this.dColor);
	}

	public boolean lawsOfLife(Mouse mousePlayer) {
		return (mousePlayer.pinhole.x <= (500 - this.dPinhole.x + (this.dWidth / 2)))
			&& (mousePlayer.pinhole.x >= (this.dPinhole.x - (this.dWidth / 2)))
			&& (mousePlayer.pinhole.y <= (500 - this.dPinhole.y + (this.dLength / 2)))
			&& (mousePlayer.pinhole.y >= (this.dPinhole.y - (this.dLength / 2)));
	}

}




public class TheGame extends World{
	int worldWidth;
	int worldLength;
	Mouse mousePlayer;
	ArrayList<StickyPaper> stickyPaper;
	Cat cat;
	ArrayList<DeadMouse> deadMouse;
	int lives;
	int points;
	int counter;

	public TheGame(){

	}


	public TheGame(int worldWidth, int worldLength, Mouse mousePlayer,
					ArrayList stickyPaper, Cat cat, ArrayList deadMouse,
					int lives, int points, int counter){
		this.worldWidth = worldWidth;
		this.worldLength = worldLength;
		this.mousePlayer = mousePlayer;
		this.stickyPaper = stickyPaper;
		this.cat = cat;
		this.deadMouse = deadMouse;
		this.lives = lives;
		this.points = points;
		this.counter = counter;
	}


	public World onKeyEvent(String ke){
		System.out.println("This is the ke: " + ke);
		return new TheGame(worldWidth, worldLength, mousePlayer.moveMouse(ke),
						   stickyPaper, cat, deadMouse,
						   lives, points, counter);
	}

	public boolean stuckHuhCheck(){
		if(!stickyPaper.isEmpty()){
			int i = 0;
			while(i != stickyPaper.size()){
				if(stickyPaper.get(i).stuckHuh(mousePlayer)){
					return true;
				}
				else{
					i++;
				}
			}
		}
			return false;
	}

	public boolean lawsOfLifeCheck(){
		if(!deadMouse.isEmpty()){
			int i = 0;
			while(i != deadMouse.size()){
				if(deadMouse.get(i).lawsOfLife(mousePlayer)){
					return true;
				}
				else {
					i++;
				}
			}
		}
		return false;
	}

	static Random wRand = new Random();
	public static int wRandomInt(int min, int max){
		return wRand.nextInt(((max - min) + 1) + min);
	}


 	public World onTick() {
 		System.out.println("On Tick is Running, the counter is " + counter);
 		System.out.println("The amount of stickies is " + stickyPaper.size());
 		if(stuckHuhCheck()){
 			System.out.println("Oh no, dieing!");
 			deadMouse.add((5 - lives), new DeadMouse(this.mousePlayer.pinhole, this.mousePlayer.length,
 						  this.mousePlayer.width, new Color(0, 255, 0)));
 			mousePlayer.mousePlacer(10, this.worldLength / 2);
 			return new TheGame(worldWidth, worldLength, mousePlayer,
 			 stickyPaper, cat, deadMouse, (lives - 1), points, counter);
 		}
 		else if(cat.feedCat(mousePlayer)){
 			System.out.println("yay, feeding!");
 			return new TheGame(worldWidth, worldLength, mousePlayer.mousePlacer(10, this.worldLength / 2),
 			 new ArrayList<StickyPaper>(), cat.catPlacer(), new ArrayList<DeadMouse>(), lives, (points + 1), 0);
 		}
 		else if(lawsOfLifeCheck()){
 		return new TheGame(worldWidth, worldLength, mousePlayer,
 			 stickyPaper, cat, deadMouse, 0, points, counter);
 		}
 		else if(((points <= 10) && (counter == 12 - points)) || ((points >= 10) && (counter == 2))){
 			System.out.println("Making a sticky!");


 			stickyPaper.add(stickyPaper.size(), new StickyPaper(new Posn(wRandomInt((mousePlayer.pinhole.x + (mousePlayer.width / 2) + 4 + wRandomInt(15, 25)),
 			cat.cPinhole.x - 30),
 			 wRandomInt(0, 500)), wRandomInt(15, 25), wRandomInt(15, 25), new Color(255, 255, 0)));
 		System.out.println("The amount of stickies is now " + stickyPaper.size());
	 
 			return new TheGame(worldWidth, worldLength, mousePlayer, stickyPaper, cat, deadMouse, lives, points, 0);
 		}

 		else return new TheGame(worldWidth, worldLength, mousePlayer,
 			 stickyPaper, cat, deadMouse, lives, points, (counter + 1));
 	}

	public WorldEnd worldEnds(){
		if(lives == 0){
			if(points == 0){
				return new WorldEnd(true, new OverlayImages(this.makeImage(),
					new TextImage(new Posn((worldWidth / 2), (worldLength / 2)), 
						("You have lost! You have fed 0 cats! Try Harder!"), Color.black)));
			}
			else {
				return new WorldEnd(true, new OverlayImages(this.makeImage(),
					new TextImage(new Posn((worldWidth / 2), (worldLength / 2)),
					 ("You have lost! You have fed " + points + " cats! Congratulations"), Color.black)));
			}	
		}
		else {
			return new WorldEnd(false, this.makeImage());
		}
	} 

	
	public WorldImage makeBoard = new RectangleImage(new Posn(250, 250), 500, 500, new Color(153, 50, 204));
 		
 	
 	public WorldImage stickyPaperLoop(){
 		for(int i = 0; i < stickyPaper.size(); i++){
 			new OverlayImages(stickyPaper.get(i).stickyPaperImage(), stickyPaperLoop()); 
 		}
	return new RectangleImage(new Posn (0, 0), 0, 0, new Color(0, 0, 0));
 	}

public WorldImage deadMouseLoop(int i){
	while(i != deadMouse.size()){
		return new OverlayImages(deadMouse.get(i).deadMouseImage(), deadMouseLoop(i++));
	}
	return new RectangleImage(new Posn (0, 0), 0, 0, new Color(0, 0, 0));
}


 	public WorldImage makeImage(){
 		return new OverlayImages(makeBoard, new OverlayImages(new TextImage(new Posn(425, 25), "Lives left: " + lives, 20, new Color(0, 0, 128)),
 			new OverlayImages(new TextImage(new Posn(425, 475), "Cats Fed: " + points, 20, new Color(0, 0, 128)),
 			new OverlayImages(cat.catImage(), new OverlayImages(stickyPaperLoop(), new OverlayImages(deadMouseLoop(0), mousePlayer.mouseImage()))))));
 	}

	static Random rand = new Random();
    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

 	public static void moveMouseTester(int i){
 		Mouse testMousey = new Mouse(new Posn(10, 10), 10, 10, new Color(0, 0, 255)); 
 		if(i == 1){
 			System.out.println("TestMousy should have the x 11 and it is " + testMousey.moveMouse("right").pinhole.x);
 		}
 		else if(i == 2){
 			 System.out.println("TestMousy should have the x 9 and it is " + testMousey.moveMouse("left").pinhole.x);
 		}
 		else if(i == 3){
 			 System.out.println("TestMousy should have the y 11 and it is " + testMousey.moveMouse("down").pinhole.y);
 		}
 		else if(i == 4){
 			 System.out.println("TestMousy should have the y 9 and it is " + testMousey.moveMouse("up").pinhole.y);
 		}
 	}

 	public static void moveMouseTester(){
 		int x = 0;
 		while(x != 20){
 			moveMouseTester(randomInt(1, 4));
 			x++;
 		}
 	}

	public static void catFeedTester(){
		int x = 0;
		Mouse testMousey = new Mouse(new Posn(10, 10), 10, 10, new Color(0, 0, 255));
		Cat testFatCat = new Cat(new Posn(35, 10), 15, 15, new Color(255, 0, 0));
		while(x != 20){
			int estMoves = (testFatCat.cPinhole.x - (testFatCat.cWidth / 2)) - (testMousey.pinhole.x + (testMousey.width / 2));
			int counter = 0;
			while((testFatCat.feedCat(testMousey) != true) || (counter <= estMoves)){
				testMousey.moveMouse("right");
				counter++;
				System.out.println("Moving right, counter is " + counter + "and estMoves is " + estMoves);
			}
			if(counter == estMoves){
				System.out.println("The mouse never met the cat!");
			}
			else{
			counter++;	
			System.out.println("Should take about " + estMoves + " moves. It took " + counter + " moves.");
			testMousey.mousePlacer(10, 10);
			testFatCat.catPlacer(10, randomInt(20, 40));
			}
			x++;
		}
	}

	public static void lawsOfLifeTester(){

		ArrayList<DeadMouse> testydeadMouse = new ArrayList();
		Mouse testyMousy = new Mouse(new Posn(10, 250), 10, 10, new Color(0, 0, 255));

		testydeadMouse.add(new DeadMouse(new Posn(30, 50), 10, 100, new Color(0, 0, 0,)));
		testydeadMouse.add(new DeadMouse(new Posn(30, 150), 10, 100, new Color(0, 0, 0,)));
		testydeadMouse.add(new DeadMouse(new Posn(30, 250), 10, 100, new Color(0, 0, 0,)));
		testydeadMouse.add(new DeadMouse(new Posn(30, 350), 10, 100, new Color(0, 0, 0,)));
		testydeadMouse.add(new DeadMouse(new Posn(30, 450), 10, 100, new Color(0, 0, 0,)));
		int counter = 0
		int estMoves = (30 - (testyMousy.pinhole.x + (testMousey.width / 2)));
			while((testdeadMouse.lawsOfLifeCheck() == false) || counter != estMoves){
				testMousey.moveMouse("right");
				counter++;
			}
			if(counter == estMoves){
				System.out.println("The mouse didn't meet it's past self!")
			}
			else{
				System.out.println("The mouse met its past self")
			}

		}

	public static void stuckHuhTester(){

		ArrayList<StickyPaper> testyStickyPaper = new ArrayList();
		Mouse testyMousy = new Mouse(new Posn(10, 250), 10, 10, new Color(0, 0, 255));
		testyStickyPaper.add(new DeadMouse(new Posn(30, 50), 10, 100, new Color(0, 0, 0,)));

		int estMoves = (30 - (testyMousy.pinhole.x + (testMousey.width / 2)));
		testyStickyPaper.add(new DeadMouse(new Posn(30, 150), 10, 100, new Color(255, 255, 0,)));
		testyStickyPaper.add(new DeadMouse(new Posn(30, 250), 10, 100, new Color(255, 255, 0,)));
		testyStickyPaper.add(new DeadMouse(new Posn(30, 350), 10, 100, new Color(255, 255, 0,)));
		testyStickyPaper.add(new DeadMouse(new Posn(30, 450), 10, 100, new Color(255, 255, 0,)));


			while((testyStickyPaper.stuckHuhCheck() == false) || counter != estMoves){
				testMousey.moveMouse("right");
				counter++;
			}
			if(counter == estMoves){
				System.out.println("The mouse didn't get caught on the sticky paper")
			}
			else{
				System.out.println("The mouse met got caught on the sticky paper")
			}

		}



 	public static void main (String [] args){
 		ArrayList<StickyPaper> stickyPaperArray = new ArrayList();
		ArrayList<DeadMouse> deadMouseArray = new ArrayList();
		Mouse newMousy = new Mouse(new Posn(10, 250), 10, 10, new Color(0, 0, 255));
		Cat fatCat = new Cat(new Posn(495, 250), 20, 25, new Color(255, 0, 0));
		int initLives = 5;
		int initPoints = 0;
		int initCounter = 0;


		System.out.println("newMousy should have the x 11 and it is " + newMousy.moveMouse("right").pinhole.x);
		

		moveMouseTester();

// 		catFeedTester();

//		TheGame newGame = new TheGame(500, 500, newMousy, stickyPaperArray, fatCat, deadMouseArray, initLives, initPoints, initCounter);

//		newGame.bigBang(500, 500, 0.1);
 	}


}



 
