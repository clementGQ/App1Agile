package model;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class DLine extends DShape{
	
	double posXEnd, posYEnd;
	Line line;
	
	public DLine(Line line) {
		this.posXEnd = line.getEndX();
		this.posYEnd = line.getEndY();
		
		this.line = line;
	}
	
	public DLine(String inputString) {
		this.line = new Line();
		String[] tokens = inputString.split("&");
		this.line.setStartX(Double.valueOf(tokens[1]));
		this.line.setEndX(Double.valueOf(tokens[2]));
		this.line.setEndY(Double.valueOf(tokens[3]));
		this.line.setTranslateX(Double.valueOf(tokens[4]));
		this.line.setTranslateY(Double.valueOf(tokens[5]));
		this.line.setScaleX(Double.valueOf(tokens[6]));
		this.line.setScaleY(Double.valueOf(tokens[7]));
		this.line.setRotate(Double.valueOf(tokens[8]));
	}

	
	public DLine(double posX, double posY, double posX2, double posY2) {
		this.posXEnd = posX2;
		this.posYEnd = posY2;
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
	public void saveShape() {
		this.posX = this.line.getStartX();
		this.posY = this.line.getStartY();
		this.posXEnd = this.line.getEndX();
		this.posYEnd = this.line.getEndY();
		
		this.translationX = this.line.getTranslateX();
		this.translationY = this.line.getTranslateY();
		this.scaleX = this.line.getScaleX();
		this.scaleY = this.line.getScaleY();
		this.rotation = this.line.getRotate();
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
		return shapeString;
	}

	//Useless ??
	@Override
	public void stringToShape(String inputString) {
		String[] tokens = inputString.split("&");
		for (String t : tokens) {
		  System.out.println(t);
		}
	}

}
