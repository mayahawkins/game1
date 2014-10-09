import javalib.funworld.*;
import javalib.worldimages.*;
import javalibworldcanvas.*;
import javalib.colors.*

class FallingPerfectly{
}



class PlayerBlock{
	int length;
	int width;
	IColor color;
	int area;

	PlayerBlock(int length; int width, IColor color){
		this.length = length;
		this.width = width;
		this.color = col;
		this.area = this.length * this.width;
	}
	WorldImage playerBlocklImage(){
		return new RectangleImage(this.length, this.width, this.color)
	} 
	
}


