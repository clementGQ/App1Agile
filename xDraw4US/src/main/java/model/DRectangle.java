package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DRectangle extends DShape{

	String imagePath;
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
		Color color = new Color(Double.valueOf(tokens[10]), Double.valueOf(tokens[11]), Double.valueOf(tokens[12]), 1);
		this.rectangle.setStroke(color);
		this.rectangle.setStrokeWidth(Double.valueOf(tokens[13]));
		this.setImagePattern(tokens[14]);
	}
	
	public DRectangle(double x, double y, double cursorX, double cursorY, 
			String startingPoint, String imagePath,
			double strokeWidth, Color strokeColor) {
		double xFinal = x;
		double yFinal = y;
		double height;
		double width;
		if(startingPoint.compareTo("corner") == 0) {	//Corner
			width = Math.abs(x-cursorX);
			height = Math.abs(y-cursorY);
			if(x > cursorX) { xFinal = x-width; }
			if(y > cursorY) { yFinal = y-height; }
		} else { 										//Center
			width = Math.abs(x-cursorX) * 2;
			height = Math.abs(y-cursorY) * 2;
			xFinal = x-width/2;	
			yFinal = y-height/2;
		}
		this.rectangle = new Rectangle(xFinal, yFinal, width, height);
		this.setImagePattern(imagePath);
		this.rectangle.setStroke(strokeColor);
		this.rectangle.setStrokeWidth(strokeWidth);
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
	public String shapeToString() {
		String separator = "&";
		String shapeString = "rectangle";
		shapeString += separator + Double.toString(this.rectangle.getX());
		shapeString += separator + Double.toString(this.rectangle.getY());
		shapeString += separator + Double.toString(this.rectangle.getWidth());
		shapeString += separator + Double.toString(this.rectangle.getHeight());
		shapeString += separator + Double.toString(this.rectangle.getTranslateX());
		shapeString += separator + Double.toString(this.rectangle.getTranslateY());
		shapeString += separator + Double.toString(this.rectangle.getScaleX());
		shapeString += separator + Double.toString(this.rectangle.getScaleY());
		shapeString += separator + Double.toString(this.rectangle.getRotate());
		shapeString += separator + Double.toString(((Color)this.rectangle.getStroke()).getRed());
		shapeString += separator + Double.toString(((Color)this.rectangle.getStroke()).getGreen());
		shapeString += separator + Double.toString(((Color)this.rectangle.getStroke()).getBlue());
		shapeString += separator + Double.toString(this.rectangle.getStrokeWidth());
		shapeString += separator + this.imagePath;
		return shapeString;
	}

	@Override
	public void setImagePattern(String imagePath) {
		if(imagePath != "images/.jpg") {
			this.imagePath = imagePath;
			Image image = new Image(imagePath); 
			ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
			this.rectangle.setFill(radialGradient);
		}
	}
	
}
