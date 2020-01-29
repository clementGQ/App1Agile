package model;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DRectangle extends DShape{

	double width, height;
	ImagePattern image;
	Rectangle rectangle;
	
	public DRectangle(String inputString) {
		this.rectangle = new Rectangle();
		String[] tokens = inputString.split("&");
		this.rectangle.setX(Double.valueOf(tokens[1]));
		this.rectangle.setY(Double.valueOf(tokens[2]));
		this.rectangle.setWidth(Double.valueOf(tokens[3]));
		this.rectangle.setHeight(Double.valueOf(tokens[4]));
		this.rectangle.setTranslateX(Double.valueOf(tokens[5]));
		this.rectangle.setTranslateY(Double.valueOf(tokens[6]));
		this.rectangle.setScaleX(Double.valueOf(tokens[7]));
		this.rectangle.setScaleY(Double.valueOf(tokens[8]));
		this.rectangle.setRotate(Double.valueOf(tokens[9]));
	}
	
	
	public DRectangle(Rectangle rectangle) {
		this.posX = rectangle.getX();
		this.posY = rectangle.getY();
		this.width = rectangle.getWidth();
		this.height = rectangle.getHeight();
		this.image = (ImagePattern) rectangle.getFill();
		
		this.rectangle = rectangle;
	}
	
	public DRectangle(double posX, double posY, double width, double height, ImagePattern image) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.image = image;
	}

	@Override
	public Shape getShape() {
		return this.rectangle;
	}

	@Override
	public void zoom(double mult) {
		rectangle.setWidth(rectangle.getWidth()*mult);
		rectangle.setHeight(rectangle.getHeight()*mult);
		rectangle.setX(rectangle.getX()*mult);
		rectangle.setY(rectangle.getY()*mult);
		rectangle.setTranslateX(rectangle.getTranslateX()*mult);
		rectangle.setTranslateY(rectangle.getTranslateY()*mult);
	}

	@Override
	public void saveShape() {
		this.posX = this.rectangle.getX();
		this.posY = this.rectangle.getY();
		this.width = this.rectangle.getWidth();
		this.height = this.rectangle.getHeight();
		this.translationX = this.rectangle.getTranslateX();
		this.translationY = this.rectangle.getTranslateY();
		this.scaleX = this.rectangle.getScaleX();
		this.scaleY = this.rectangle.getScaleY();
		this.rotation = this.rectangle.getRotate();
		
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
