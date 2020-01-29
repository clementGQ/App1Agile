package model;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DCircle extends DShape{
	
	double radius;
	ImagePattern image;
	Circle circle;
	
	public DCircle(String inputString) {
		this.circle = new Circle();
		String[] tokens = inputString.split("&");
		this.circle.setCenterX(Double.valueOf(tokens[1]));
		this.circle.setCenterY(Double.valueOf(tokens[2]));
		this.circle.setRadius(Double.valueOf(tokens[3]));
		this.circle.setTranslateX(Double.valueOf(tokens[4]));
		this.circle.setTranslateY(Double.valueOf(tokens[5]));
		this.circle.setScaleX(Double.valueOf(tokens[6]));
		this.circle.setScaleY(Double.valueOf(tokens[7]));
		this.circle.setRotate(Double.valueOf(tokens[8]));
	}
	
	public DCircle(Circle circle) {
		this.posX = circle.getCenterX();
		this.posY = circle.getCenterY();
		this.radius = circle.getRadius();
		this.image = (ImagePattern) circle.getFill();
		
		this.circle = circle;
	}
	
	public DCircle(double posX, double posY, double radius, ImagePattern image) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.image = image;
	}

	@Override
	public Shape getShape() {
		return this.circle;
	}

	@Override
	public void zoom(double mult) {
		circle.setCenterX(circle.getCenterX()*mult);
		circle.setCenterY(circle.getCenterY()*mult);
		circle.setScaleX(circle.getScaleX()*mult);
		circle.setScaleY(circle.getScaleY()*mult);
		circle.setTranslateX(circle.getTranslateX()*mult);
		circle.setTranslateY(circle.getTranslateY()*mult);
	}

	@Override
	public void saveShape() {
		this.posX = this.circle.getCenterX();
		this.posY = this.circle.getCenterY();
		this.radius = this.circle.getRadius();
		this.translationX = this.circle.getTranslateX();
		this.translationY = this.circle.getTranslateY();
		this.scaleX = this.circle.getScaleX();
		this.scaleY = this.circle.getScaleY();
		this.rotation = this.circle.getRotate();
	}

	@Override
	public String shapeToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stringToShape(String inputString) {
		// TODO Auto-generated method stub
		
	}

}
