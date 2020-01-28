package model;


import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DrawingSheet extends Pane {

	private ArrayList<Rectangle> rectanglesList = new ArrayList<>();	
	private ArrayList<Line> lineList = new ArrayList<>();	
	private ArrayList<Circle> circlesList = new ArrayList<>();	
	private ArrayList<Shape> shapesList = new ArrayList<>();
	private Shape shapeSelected = null;
	private int nbChildrenMax;
	
	//builder
	public DrawingSheet() {
		super();
		this.setStyle("-fx-background-color: white;");
		this.setPrefSize(683,455);
		this.nbChildrenMax = 1;
	}

	/**
     * 
     */
	public void zoom(double mult) {
		this.setPrefSize(this.getWidth()*mult,this.getHeight()*mult);
		
		for(Rectangle rectangle: this.rectanglesList) {
			rectangle.setWidth(rectangle.getWidth()*mult);
			rectangle.setHeight(rectangle.getHeight()*mult);
			rectangle.setX(rectangle.getX()*mult);
			rectangle.setY(rectangle.getY()*mult);
		}
		for(Circle circle: this.circlesList) {
			circle.setCenterX(circle.getCenterX()*mult);
			circle.setCenterY(circle.getCenterY()*mult);
			circle.setScaleX(circle.getScaleX()*mult);
			circle.setScaleY(circle.getScaleY()*mult);
		}
		for(Line line: this.lineList) {
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
		}
	}
	
	/**
     * delete the content of the sheet
     */
	public void deleteAll() {
		this.setNbChildrenMax(1);
		this.getChildren().clear();
		this.getShapesList().clear();
		this.setShapeSelected( null);
	}
	
	
	//getters setters
	
	public ArrayList<Circle> getCirclesList() {
		return circlesList;
	}
	
	public ArrayList<Shape> getShapesList() {
		return shapesList;
	}
	
	public ArrayList<Line> getLinesList() {
		return lineList;
	}
	public ArrayList<Rectangle> getRectanglesList() {
		return rectanglesList;
	}
	
	public void setNbChildrenMax(int nb) {
		this.nbChildrenMax = nb;
	}

	public int getNbChildrenMax() {
		return nbChildrenMax;
	}
	public Shape getShapeSelected() {
		return shapeSelected;
	}
	public void setShapeSelected(Shape shapeSelected) {
		this.shapeSelected = shapeSelected;
	}
	public void resetShapeSelected() {
		if(shapeSelected != null) {
			shapeSelected.setOpacity(1);
			shapeSelected = null;
		}
	}

}
