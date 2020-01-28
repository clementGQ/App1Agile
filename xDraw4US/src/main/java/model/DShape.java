package model;

import javafx.scene.shape.Shape;

public abstract class DShape {
	
	double posX, posY;
	double translationX, translationY;
	double rotationX, rotationY;
	double scaleX, scaleY;
	
	public DShape(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
		this.translationX = 0;
		this.translationY = 0;
		this.rotationX = 0;
		this.rotationY = 0;
		this.scaleX = 1;
		this.scaleY = 1;		
	}
	
	public abstract Shape getShape();

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

	public void setRotationX(double rotationX) {
		this.rotationX = rotationX;
	}

	public void setRotationY(double rotationY) {
		this.rotationY = rotationY;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	
	
}
