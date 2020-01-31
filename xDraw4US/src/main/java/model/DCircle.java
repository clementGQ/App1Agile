package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DCircle extends DShape{
	
	String imagePath;
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
		Color color = new Color(Double.valueOf(tokens[9]), Double.valueOf(tokens[10]), Double.valueOf(tokens[11]), 1);
		this.circle.setStroke(color);
		this.circle.setStrokeWidth(Double.valueOf(tokens[12]));
		this.setImagePattern(tokens[13]);
	}
	
	public DCircle(double x, double y, double cursorX, double cursorY, 
			String startingPoint, String imagePath,
			double strokeWidth, Color strokeColor) {
		double xFinal = x;
		double yFinal = y;
		double radius = Math.sqrt(Math.pow(x-cursorX, 2)+Math.pow(y-cursorY, 2));
		
		if(startingPoint.compareTo("corner") == 0) {
			radius = radius/2;
			xFinal = x+(cursorX-x)/2;
			yFinal = y+(cursorY-y)/2;
		}
		
		this.circle = new Circle(xFinal, yFinal, radius);
		this.setImagePattern(imagePath);
		this.circle.setStroke(strokeColor);
		this.circle.setStrokeWidth(strokeWidth);
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
	public String shapeToString() {
		String separator = "&";
		String shapeString = "circle";
		shapeString += separator + Double.toString(this.circle.getCenterX());
		shapeString += separator + Double.toString(this.circle.getCenterY());
		shapeString += separator + Double.toString(this.circle.getRadius());
		shapeString += separator + Double.toString(this.circle.getTranslateX());
		shapeString += separator + Double.toString(this.circle.getTranslateY());
		shapeString += separator + Double.toString(this.circle.getScaleX());
		shapeString += separator + Double.toString(this.circle.getScaleY());
		shapeString += separator + Double.toString(this.circle.getRotate());
		shapeString += separator + Double.toString(((Color)this.circle.getStroke()).getRed());
		shapeString += separator + Double.toString(((Color)this.circle.getStroke()).getGreen());
		shapeString += separator + Double.toString(((Color)this.circle.getStroke()).getBlue());
		shapeString += separator + Double.toString(this.circle.getStrokeWidth());
		shapeString += separator + this.imagePath;
		return shapeString;
	}

	@Override
	public void setImagePattern(String imagePath) {
		this.imagePath = imagePath;
		Image image = new Image(imagePath); 
		ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
		this.circle.setFill(radialGradient);
	}

}
