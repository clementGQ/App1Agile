package model;

import javafx.scene.shape.Shape;

public abstract class DShape {
	
	public DShape() {}

	public abstract Shape getShape();
	
	public abstract void zoom(double mult);
	
	public abstract String shapeToString();
	
	public abstract void setImagePattern(String imagePath);
	
}
