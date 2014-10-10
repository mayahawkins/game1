import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;
import javalib.colors.*;


class Mouse{
	public Posn pinhole;
	public int length;
	public int width;
	public IColor color;
	public int area;

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
	 		return new Mouse(new Posn(this.pinhole.x + 10, this.pinhole.y),
	 			this.length, this.width, this.color);
	 	}
	 	else if (ke.equals("left")){
	 		return new Mouse(new Posn(this.pinhole.x -10, this.pinhole.y),
	 			this.length, this.width, this.color);
	 	}
	 	else if (ke.equals("up")){
	 		return new Mouse(new Posn(this.pinhole.x, this.pinhole.y - 10),
	 			this.length, this.width, this.color);
	 	}
	 	else if (ke.equals("down")){
	 		return new Mouse(new Posn(this.pinhole.x, this.pinhole.y + 10),
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
		||     (this.pinhole.y + (this.length / 2)) > 500
		||     (this.pinhole.y - (this.length / 2)) < 0;
	}
}

class StickyPaper {
	public Posn sTPinhole;
	public int sTLength;
	public int sTWidth;
	public IColor sTColor;

	StickyPaper(Posn sTPinhole, int sTHeight, int sTWidth, IColor sTColor){
	this.sTPinhole = sTPinhole;
	this.sTLength = sTLength;
	this.sTWidth = sTWidth;
	this.sTColor = sTColor;	
	}

	WorldImage StickyPaperImage(){
		return new RectangleImage(this.sTPinhole, this.sTLength,
								 this.sTWidth, this.sTColor);
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
	public int cLength;
	public int cWidth;
	public IColor cColor;

	Cat(Posn cPinhole, int cHeight, int cWidth, IColor cColor){
	this.cPinhole = cPinhole;
	this.cLength = cLength;
	this.cWidth = cWidth;
	this.cColor = cColor;	
	}

	WorldImage CatImage(){
		return new RectangleImage(this.cPinhole, this.cLength,
								 this.cWidth, this.cColor);
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
	public int dLength;
	public int dWidth;
	public IColor dColor;

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
		return (mousePlayer.pinhole.x <= (500 - this.dPinhole.x + (this.dWidth / 2)))
			&& (mousePlayer.pinhole.x >= (this.dPinhole.x - (this.dWidth / 2)))
			&& (mousePlayer.pinhole.y <= (500 - this.dPinhole.y + (this.dLength / 2)))
			&& (mousePlayer.pinhole.y >= (this.dPinhole.y - (this.dLength / 2)));
	}

}




public class TheGame extends World{
	int worldHeight = 500;
	int worldWidth = 500;
	Mouse mousePlayer;

 	public WorldImage makeImage(){
 		return new OverlayImages(this.mousePlayer.mouseImage(), this.mousePlayer.mouseImage());
 	}
 	public TheGame(Mouse mousePlayer){
 		super();
 		this.mousePlayer = mousePlayer;
 	}
 	public World onKeyEvent(String ke) {
 		if (ke.equals ("l"))
 			return this.endofWorld("Goodbye");
 		else
 			return new TheGame(this.mousePlayer.moveMouse(ke));
 	}
// 	public World onTick() {
// 		if(stuckhuh) {
// 			new DeadMouse(this.pinhole, this.height, this.width, blue);
// 			this.mousePlacer();
// 		}

 //	}
 	//public WorldImage otherImages()
 	public static void main (String [] args){

 	}
}

	

 
