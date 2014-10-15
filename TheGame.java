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
	public void mousePlacer(int x, int y) {
		this.pinhole = new Posn(x, y);
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

	WorldImage StickyPaperImage(){
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

	Cat(Posn cPinhole, int cHeight, int cWidth, Color cColor){
	this.cPinhole = cPinhole;
	this.cWidth = cWidth;
	this.cLength = cLength;
	this.cColor = cColor;	
	}

	WorldImage CatImage(){
		return new RectangleImage(this.cPinhole, this.cWidth,
								  this.cLength, this.cColor);
	}

	public boolean feedCat(Mouse mousePlayer) {
		return ((mousePlayer.pinhole.x + mousePlayer.width) == (500 - this.cPinhole.x + (this.cWidth / 2)))
			&& ((mousePlayer.pinhole.y - mousePlayer.length) > (this.cPinhole.y + (this.cLength / 2)))
			&& ((mousePlayer.pinhole.y + mousePlayer.length) < (this.cPinhole.y - (this.cLength / 2)));
	}

	public boolean wrongFeeding(Mouse mousePlayer){
		return (mousePlayer.pinhole.x > (500 - this.cPinhole.x + (this.cWidth / 2)))
			&& ((mousePlayer.pinhole.y == (this.cPinhole.y - (this.cLength / 2))) || 
				(mousePlayer.pinhole.y == (this.cPinhole.y + (this.cLength / 2))));
	}

	static Random catRand = new Random();
	public static int randomInt(int min, int max){
		return catRand.nextInt(((max - min) + 1) + min);
	}


	public void catPlacer(){
		this.cPinhole = new Posn(500 - this.cWidth, randomInt((cLength / 2),
								 (500 - (cLength / 2))));
	}
}

class DeadMouse {
	public Posn dPinhole;
	public int dWidth;
	public int dLength;
	public Color dColor;

	public DeadMouse(Posn dPinhole, int dLength, int dWidth, Color dColor){
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
	static final int INITIAL_CAPACITY = 10;
	int worldWidth;
	int worldLength;
	Mouse mousePlayer;
	ArrayList<StickyPaper> stickyPaper;
	Cat cat;
	ArrayList<DeadMouse> deadMouse;
	int lives;
	int points;
	int counter;


	public TheGame( int worldWidth, int worldLength, Mouse mousePlayer,
					ArrayList stickyPaper, Cat cat, ArrayList deadMouse,
					int lives, int points, int counter){
		this.worldWidth = worldWidth;
		this.worldLength = worldLength;
		this.mousePlayer = mousePlayer;
		this.stickyPaper = new ArrayList<StickyPaper>();
		this.cat = cat;
		this.deadMouse = new ArrayList<DeadMouse>();
		this.lives = lives;
		this.points = points;
		this.counter = counter;
	}


	public World onKey(String ke){
		return new TheGame(worldWidth, worldLength, mousePlayer.moveMouse(ke),
						   stickyPaper, cat, deadMouse,
						   lives, points, counter);
	}

	public boolean stuckHuhCheck(){
		if(!stickyPaper.isEmpty()){
			int i = 0;
			while(i != stickyPaper.size()){
				if(stickyPaper.get(i).stuckHuh(this.mousePlayer)){
					return true;
				}
				else {
					i++;
				}
			}
			return false;
		}
		else 
			return false;
	}

	public boolean lawsOfLifeCheck(){
		if(!deadMouse.isEmpty()){
			int i = 0;
			while(i != deadMouse.size()){
				if(deadMouse.get(i).lawsOfLife(this.mousePlayer)){
					return true;
				}
				else {
					i++;
				}
			}
			return false;
		}
		return false;
	}

	static Random wRand = new Random();
	public static int wRandomInt(int min, int max){
		return wRand.nextInt(((max - min) + 1) + min);
	}


 	public World onTick() {
 		if(stuckHuhCheck()){
 			deadMouse.add((5 - lives), new DeadMouse(this.mousePlayer.pinhole, this.mousePlayer.length,
 						  this.mousePlayer.width, new Color(0, 255, 0)));
 			mousePlayer.mousePlacer(10, this.worldLength / 2);
 			return new TheGame(worldWidth, worldLength, mousePlayer,
 			 stickyPaper, cat, deadMouse, (lives - 1), points, counter);
 		}
 		else if(cat.wrongFeeding(mousePlayer)) {
 			mousePlayer.mousePlacer(10, this.worldLength / 2);
 			return new TheGame(worldWidth, worldLength, mousePlayer,
 			 stickyPaper, cat, deadMouse, lives, points, counter);
 		}
 		else if(cat.feedCat(mousePlayer)){
 			mousePlayer.mousePlacer(10, this.worldLength / 2);
 			cat.catPlacer();
 			deadMouse.clear();
 			stickyPaper.clear();
 			return new TheGame(worldWidth, worldLength, mousePlayer,
 			 stickyPaper, cat, deadMouse, lives, (points + 1), counter);
 		}
 		else if(lawsOfLifeCheck()){
 		return new TheGame(worldWidth, worldLength, mousePlayer,
 			 stickyPaper, cat, deadMouse, 0, points, counter);
 		}
 		else if(((points <= 10) && (counter == 12 - points)) || ((points >= 10) && (counter == 2))){
 			int randWidth = wRandomInt(7, 15);
 			int randLength = wRandomInt(7, 15);
 			int randXPosn = wRandomInt((mousePlayer.pinhole.x + (mousePlayer.width / 2) + 4 + (randWidth / 2)), (cat.cPinhole.x - (randWidth + 5)));
 			int randYPosn = wRandomInt((randLength / 2), (500 - randLength / 2));

 			stickyPaper.add(stickyPaper.size(), new StickyPaper(new Posn(randXPosn, randYPosn), randWidth, randLength, new Color(255, 255, 0)));
 
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

	
	public WorldImage makeBoard = new RectangleImage(new Posn(0, 0), 500, 500, new Color(153, 50, 204));
 		
 	
 	




 	public WorldImage makeImage(){
 		return new OverlayImages(makeboard, new OverlayImages(new TextImage(new Posn(475, 7), "Lives left: " + lives, 10, new Color(0, 0, 128)),
 			new OverlayImages(new TextImage(new Posn(475, 475), "Cats Fed: " + points, 10, new Color(0, 0, 128)),
 			new OverlayImages(cat, new OverlayImages(stickyPaper, new OverlayImages(deadMouse, mousePlayer))))));
 	}


 	public TheGame(Mouse mousePlayer){
 		super();
 		this.mousePlayer = mousePlayer;
 	}



 	//public WorldImage otherImages()
 	public static void main (String [] args){

 	}


}

	

 
