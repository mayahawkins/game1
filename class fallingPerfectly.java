import javalib.funworld.*;
import javalib.worldimages.*;
import javalibworldcanvas.*;
import javalib.colors.*

class FallingPerfectly{
}



class PlayerBlock{
	Posn pinhole;
	int length;
	int width;
	IColor color;
	int area;

	PlayerBlock(int length; int width, IColor color){
		this.pinhole = pinhole;
		this.length = length;
		this.width = width;
		this.color = col;
		this.area = this.length * this.width;
	}
	WorldImage playerBlocklImage(){
		return new RectangleImage(this. pinhole, this.length, this.width, this.color)
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
}

public class TheGame extends World {
	int width = 500;
	int height = 300;
 	PlayerBlock playerBlock;

 	public TheGameWorld(PlayerBlock playerBlock){
 		super();
 		this.playerBlock = playerBlock;
 	}
 	public World onKeyEvent(String ke) {
 		if (ke.equals ("x"))
 			return this.endofWorld("Goodbye");
 		else
 			return new TheGame(this.playerBlock.moveBlock(ke));
 	}
 	public World onTick() {

 	}
 	//public WorldImage otherImages()
}

	

 
