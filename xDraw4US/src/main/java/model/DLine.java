package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class DLine extends DShape{
	
	Line line;
	
	public DLine(String inputString) {
		this.line = new Line();
		String[] tokens = inputString.split("&");
		this.line.setStartX(Double.valueOf(tokens[1]));
		this.line.setStartY(Double.valueOf(tokens[2]));
		this.line.setEndX(Double.valueOf(tokens[3]));
		this.line.setEndY(Double.valueOf(tokens[4]));
		this.line.setTranslateX(Double.valueOf(tokens[5]));
		this.line.setTranslateY(Double.valueOf(tokens[6]));
		this.line.setScaleX(Double.valueOf(tokens[7]));
		this.line.setScaleY(Double.valueOf(tokens[8]));
		this.line.setRotate(Double.valueOf(tokens[9]));
		this.line.setStrokeWidth(Double.valueOf(tokens[10]));
		Color color = new Color(Double.valueOf(tokens[11]), Double.valueOf(tokens[12]), Double.valueOf(tokens[13]), 1);
		this.line.setStroke(color);
	}
	
	public DLine(double x, double y, double cursorX, double cursorY, 
			String startingPoint, String imagePath,
			double strokeWidth, Color strokeColor) {
		double xFinal = x;
		double yFinal = y;

		if(startingPoint.compareTo("center") == 0) { 
			xFinal = x-(cursorX-x);
			yFinal = y-(cursorY-y);
		}

		this.line = new Line(xFinal, yFinal, cursorX, cursorY);
		this.line.setStroke(strokeColor);
		this.line.setStrokeWidth(strokeWidth);
	}

	@Override
	public Shape getShape() {
		return this.line;
	}

	@Override
	public void zoom(double mult) {
		
		double normX = Math.abs(line.getStartX()-line.getEndX());
		double normY = Math.abs(line.getStartY()-line.getEndY());
		
		if(line.getStartX()>line.getEndX()) {
			line.setEndX(line.getEndX()*mult);
			line.setStartX(line.getEndX()+normX*mult);
		}
		else {
			line.setStartX(line.getStartX()*mult);
			line.setEndX(line.getStartX()+normX*mult);
		}
		
		if(line.getStartY()>line.getEndY()) {
			line.setEndY(line.getEndY()*mult);
			line.setStartY(line.getEndY()+normY*mult);
		}
		else {
			line.setStartY(line.getStartY()*mult);
			line.setEndY(line.getStartY()+normY*mult);
		}
		line.setStrokeWidth(line.getStrokeWidth()*mult);
		line.setTranslateX(line.getTranslateX()*mult);
		line.setTranslateY(line.getTranslateY()*mult);
	}

	@Override
	public String shapeToString() {
		String separator = "&";
		String shapeString = "line";
		shapeString += separator + Double.toString(this.line.getStartX());
		shapeString += separator + Double.toString(this.line.getStartY());
		shapeString += separator + Double.toString(this.line.getEndX());
		shapeString += separator + Double.toString(this.line.getEndY());
		shapeString += separator + Double.toString(this.line.getTranslateX());
		shapeString += separator + Double.toString(this.line.getTranslateY());
		shapeString += separator + Double.toString(this.line.getScaleX());
		shapeString += separator + Double.toString(this.line.getScaleY());
		shapeString += separator + Double.toString(this.line.getRotate());
		shapeString += separator + Double.toString(this.line.getStrokeWidth());
		shapeString += separator + Double.toString(((Color)this.line.getStroke()).getRed());
		shapeString += separator + Double.toString(((Color)this.line.getStroke()).getGreen());
		shapeString += separator + Double.toString(((Color)this.line.getStroke()).getBlue());
		return shapeString;
	}

	@Override
	public void setImagePattern(String imagePath) {
		// No ImagePattern for a Line
	}

}
