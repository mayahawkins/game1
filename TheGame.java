import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;
import javalib.colors.*;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.geom.*;
import java.util.*;


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
	 	if (ke.equals("right")){
	 		return new Mouse(new Posn(this.pinhole.x + 10, this.pinhole.y),
	 			this.width, this.length, this.color);
	 	}
	 	else if (ke.equals("left")){
	 		return new Mouse(new Posn(this.pinhole.x -10, this.pinhole.y),
	 			this.width, this.length, this.color);
	 	}
	 	else if (ke.equals("up")){
	 		return new Mouse(new Posn(this.pinhole.x, this.pinhole.y - 10),
	 			this.width, this.length, this.color);
	 	}
	 	else if (ke.equals("down")){
	 		return new Mouse(new Posn(this.pinhole.x, this.pinhole.y + 10),
	 			this.width, this.length, this.color);
	 	}
	 	else {
	 		return this;
	 	}
	}
	public void mousePlacer(int x, int y) {
		this.pinhole = new Posn(x, y);
	}
	public boolean outOfBounds(){
		return (this.pinhole.x + (this.width / 2)) > 500
		||     (this.pinhole.x - (this.width / 2)) < 0
		||     (this.pinhole.y + (this.length / 2)) > 500
		||     (this.pinhole.y - (this.length / 2)) < 0;
	}
}

class StickyPaper {
	public Posn sTPinhole;
	public int sTWidth;
	public int sTLength;
	public Color sTColor;

	StickyPaper(Posn sTPinhole, int sTHeight, int sTWidth, Color sTColor){
	this.sTLength = sTLength;
	this.sTPinhole = sTPinhole;
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

	public boolean feedCat(Mouse mousePlayer, String ke) {
		return (mousePlayer.pinhole.x <= (500 - this.cPinhole.x + (this.cWidth / 2)))
			&& (mousePlayer.pinhole.x >= (this.cPinhole.x - (this.cWidth / 2)))
			&& (mousePlayer.pinhole.y <= (500 - this.cPinhole.y + (this.cLength / 2)))
			&& (mousePlayer.pinhole.y >= (this.cPinhole.y - (this.cLength / 2)))
			&& (ke.equals ("right"));
	}

	public boolean wrongFeeding(Mouse mousePlayer, String ke){
		return (mousePlayer.pinhole.x <= (500 - this.cPinhole.x + (this.cWidth / 2)))
			&& (mousePlayer.pinhole.x >= (this.cPinhole.x - (this.cWidth / 2)))
			&& (mousePlayer.pinhole.y <= (500 - this.cPinhole.y + (this.cLength / 2)))
			&& (mousePlayer.pinhole.y >= (this.cPinhole.y - (this.cLength / 2)))
			&& ((ke.equals ("up")) || (ke.equals ("down")));
	}
}

class DeadMouse {
	public Posn dPinhole;
	public int dWidth;
	public int dLength;
	public IColor dColor;

	public DeadMouse(Posn dPinhole, int dLength, int dWidth, IColor dColor){
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
	LinkedList stickyPaper;
	Cat cat;
	LinkedList deadMouse;
	int lives;
	int points;


	public TheGame( int worldWidth, int worldLength, Mouse mousePlayer,
					LinkedList stickyPaper, Cat cat, LinkedList deadMouse,
					int lives, int points){
		this.worldWidth = worldWidth;
		this.worldLength = worldLength;
		this.mousePlayer = mousePlayer;
		this. stickyPaper = stickyPaper;
		this.cat = cat;
		this.deadMouse = deadMouse;
		this.lives = lives;
		this.points = points;
	}


	public World onKey(String ke){
		this.mousePlayer.moveMouse(ke);
		return new TheGame(this.worldWidth, this.worldLength, this.mousePlayer,
						   this.stickyPaper, this.cat, this.deadMouse,
						   this.lives, this.points);
	}


// 	public World onTick() {
// 		if(stuckhuh) {
// 			new DeadMouse(this.pinhole, this.height, this.width, blue);
// 			this.mousePlacer();
// 		}

// 	}


	
	public WorldImage makeBoard = new RectangleImage(new Posn(0, 0), 500, 500, new Color(153, 50, 204));
 		
 	
 	




 	public WorldImage makeImage(){
 		return new OverlayImages(makeBoard, this.mousePlayer.mouseImage());
 	}
 	public TheGame(Mouse mousePlayer){
 		super();
 		this.mousePlayer = mousePlayer;
 	}

 	//public WorldImage otherImages()
 	public static void main (String [] args){

 	}
}

	

 
