import javalib.funworld.*;
import javalib.worldimages.*;
import javalibworldcanvas.*;
import javalib.colors.*;


class PlayerBlock{
	Posn pinhole;
	int length;
	int width;
	IColor color;
	int area;

	PlayerBlock(int length, int width, IColor color){
		this.pinhole = pinhole;
		this.length = length;
		this.width = width;
		this.color = color;
		this.area = this.length * this.width;
	}
	WorldImage playerBlocklImage(){
		return new RectangleImage(this. pinhole, this.length, this.width, this.color);
	}
	 public PlayerBlock moveBlock(String ke){
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
	public boolean outOfBounds(int width, in height){
		return (this.pinhole.x + (this.width / 2)) > width
		||     (this.pinhole.x - (this.width / 2)) < 0
		||     (this.pinhole.y + (this.height / 2)) > height
		||     (this.pinhole.y - (this.height / 2)) < 0
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

	

 
