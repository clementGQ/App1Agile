package model;

import javafx.scene.shape.Shape;

public abstract class DShape {
	
	double posX, posY;
	double translationX, translationY;
	double rotation;
	double scaleX, scaleY;
	Shape shape;
	
	public DShape() {}
	
	/////////////////////////////////////////
	/*
	public DShape(Shape shape) {
		this.shape = shape;
		this.translationX = 0;
		this.translationY = 0;
		this.rotation = 0;
		this.scaleX = 1;
		this.scaleY = 1;		
	}
	*/
	/////////////////////////////////////////
	/*
	public DShape(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
		this.translationX = 0;
		this.translationY = 0;
		this.rotation = 0;
		this.scaleX = 1;
		this.scaleY = 1;		
	}*/
	/////////////////////////////////////////
	
	
	public abstract Shape getShape();
	
	public abstract void zoom(double mult);
	
	public abstract void saveShape();
	
	public abstract String shapeToString();
	
	public abstract void stringToShape(String inputString);
	
	//getters setters
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setTranslationX(double translationX) {
		this.translationX = translationX;
	}

	public void setTranslationY(double translationY) {
		this.translationY = translationY;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	
	
}
