import javalib.funworld.*;
import javalib.worldimages.*;
import javalibworldcanvas.*;
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
		||     (this.pinhole.y - (this.height / 2)) < 0
	}
}

class StickyPaper {
	Posn sTPinhole;
	int sTHeight;
	int sTWidth;
	IColor sTColor;

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
		mousePlayer.pinhole.x
	}

}

class Cat {
	Posn cPinhole;
	int cHeight;
	int cWidth;
	IColor cColor;

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
}




public class TheGame extends World {
	int worldHeight = 500;
	int worldWidth = 300;
 	PlayerBlock playerBlock;

 	public TheGame(PlayerBlock playerBlock){
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

 	}
 	//public WorldImage otherImages()
}

	

 
