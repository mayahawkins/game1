import javalib.funworld.*;
import javalib.worldimages.*;
import javalibworldcanvas.*;
import javalib.colors.*;


class Mouse{
	private Posn pinhole;
	private int length;
	private int width;
	private IColor color;
	private int area;

	public Mouse(Posn pinhole, int length, int width, IColor color){
		this.pinhole = pinhole;
		this.length = length;
		this.width = width;
		this.color = color;
		this.area = this.length * this.width;
	}
	public WorldImage mouseImage(){
		return new RectangleImage(this.pinhole, this.length, this.width, this.color);
	}
	 public Mouse moveMouse(String ke){
	 	if (ke.equals("right")){
	 		return new RectangleImage(new Posn(this.pinhole.x + 10, this.pinhole.y),
	 			this.length, this.width, this.color);
	 	}
	 	else if (ke.equals("left")){
	 		return new RectangleImage(new Posn(this.pinhole.x -10, this.pinhole.y),
	 			this.length, this.width, this.color);
	 	}
	 	else if (ke.equals("up")){
	 		return new RectangleImage(new Posn(this.pinhole.x, this.pinhole.y - 10),
	 			this.length, this.width, this.color);
	 	}
	 	else if (ke.equals("down")){
	 		return new RectangleImage(new Posn(this.pinhole.x, this.pinhole + 10),
	 			this.length, this.width, this.color);
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
		||     (this.pinhole.y + (this.height / 2)) > 500
		||     (this.pinhole.y - (this.height / 2)) < 0;
	}
}

class StickyPaper {
	private Posn sTPinhole;
	private int sTHeight;
	private int sTWidth;
	private IColor sTColor;

	StickyPaper(Posn sTPinhole, int sTHeight, int sTWidth, IColor sTColor){
	this.sTPinhole = sTPinhole;
	this.sTHeight = sTHeight;
	this.sTWidth = sTWidth;
	this.sTColor = sTColor;	
	}

	WorldImage StickyPaperImage(){
		return new RectangleImage(this.sTPinhole, this.sTLength,
								 this.sTWidth, this.sTColor);
	}

	public boolean stuckHuh(Mouse mousePlayer) {
		return (mousePlayer.pinhole.x =< (500 - this.sTPinhole.x + (this.sTWidth / 2)))
			&& (mousePlayer.pinhole.x => (this.sTPinhole.x - (this.sTWidth / 2)))_
			&& (mousePlayer.pinhole.y =< (500 - this.sTPinhole.y + (this.sTHeight / 2)))
			&& (mousePlayer.pinhole.y => (this.sTPinhole.y - (this.sTHeight / 2)));
	}

}

class Cat {
	private Posn cPinhole;
	private int cHeight;
	private int cWidth;
	private IColor cColor;

	Cat(Posn cPinhole, int cHeight, int cWidth, IColor cColor){
	this.cPinhole = cPinhole;
	this.cHeight = cHeight;
	this.cWidth = cWidth;
	this.cColor = cColor;	
	}

	WorldImage CatImage(){
		return new RectangleImage(this.cPinhole, this.cLength,
								 this.cWidth, this.cColor);
	}

	public boolean feedCat(Mouse mousePlayer, String ke) {
		return (mousePlayer.pinhole.x =< (500 - this.cPinhole.x + (this.cWidth / 2)))
			&& (mousePlayer.pinhole.x => (this.cPinhole.x - (this.cWidth / 2)))_
			&& (mousePlayer.pinhole.y =< (500 - this.cPinhole.y + (this.cHeight / 2)))
			&& (mousePlayer.pinhole.y => (this.cPinhole.y - (this.cHeight / 2)))
			&& (ke.equals "right");
	}

	public boolean wrongFeeding(Mouse mousePlayer, Sting ke){
		return (mousePlayer.pinhole.x =< (500 - this.cPinhole.x + (this.cWidth / 2)))
			&& (mousePlayer.pinhole.x => (this.cPinhole.x - (this.cWidth / 2)))_
			&& (mousePlayer.pinhole.y =< (500 - this.cPinhole.y + (this.cHeight / 2)))
			&& (mousePlayer.pinhole.y => (this.cPinhole.y - (this.cHeight / 2)))
			&& ((ke.equals "up") || (ke.equals "down"));
	}
}

class DeadMouse {
	private Posn dPinhole;
	private int dLength;
	private int dWidth;
	private IColor dColor;

	public DeadMouse(Posn dPinhole, int dLength, int dWidth, IColor dColor){
		this.dPinhole = dPinhole;
		this.dLength = dLength;
		this.dWidth = dWidth;
		this.dColor = dColor;
	}
	public WorldImage deadMouseImage(){
		return new RectangleImage(this.dPinhole, this.dLength, this.dWidth, this.dColor);
	}

	public boolean lawsOfLife(Mouse mousePlayer) {
		return (mousePlayer.pinhole.x =< (500 - this.dPinhole.x + (this.dWidth / 2)))
			&& (mousePlayer.pinhole.x => (this.dPinhole.x - (this.dWidth / 2)))_
			&& (mousePlayer.pinhole.y =< (500 - this.dPinhole.y + (this.dHeight / 2)))
			&& (mousePlayer.pinhole.y => (this.dPinhole.y - (this.dHeight / 2)));
	}

}




public class TheGame extends World {
	int worldHeight = 500;
	int worldWidth = 500;
 	PlayerBlock playerBlock;

 	public TheGame(Mouse mousePlayer){
 		super();
 		this.playerBlock = playerBlock;
 	}
 	public World onKeyEvent(String ke) {
 		if (ke.equals ("l"))
 			return this.endofWorld("Goodbye");
 		else
 			return new TheGame(this.playerBlock.moveBlock(ke));
 	}
 	public World onTick() {
 		if(stuckhuh) {
 			new DeadMouse(this.pinhole, this.height, this.width, blue);
 			this.mousePlacer();
 		}

 	}
 	//public WorldImage otherImages()
}

	

 
