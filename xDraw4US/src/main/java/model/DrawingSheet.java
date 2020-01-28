package model;


import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import views.HorizontalPaletteController;
import views.VerticalPaletteController;

public class DrawingSheet extends Pane {

	private HorizontalPaletteController hpController;
	private VerticalPaletteController vpController;
	
	private ChoiceBox<String> fillingPatternSelection;
	private ChoiceBox<String> strokeSizeSelection;
	private ColorPicker colorStrokePicker;

	double orgSceneX, orgSceneY;
	
	private double x,y; 			// Shape attributs
	public double getLastShapeX() {
		return x;
	}
	public double getLastShapeY() {
		return y;
	}
	private double radius; 			//Circle attributs
	private double width, height; 	//Rectangle attributs
	private int nbChildrenMax;
	
	private ArrayList<Rectangle> rectanglesList = new ArrayList<>();
	private ArrayList<Line> lineList = new ArrayList<>();
	private ArrayList<Circle> circlesList = new ArrayList<>();
	private ArrayList<Shape> shapesList = new ArrayList<>();
	public ArrayList<Shape> getShapesList() {
		return shapesList;
	}
	
	private boolean isShapeCreated = false;
	
	private Shape shapeSelected = null;
	public void setShapeSelected(Shape shapeSelected) {
		this.shapeSelected = shapeSelected;
	}
	
	public DrawingSheet(HorizontalPaletteController hpController,VerticalPaletteController vpController) {
		super();
		this.setStyle("-fx-background-color: white;");
		this.setPrefSize(683,455);
		this.hpController =hpController;
		this.vpController =vpController;
		this.setColorPickerListener();
		this.setFillingPatternListener();
		this.setStrokeSizeListener();
		this.nbChildrenMax = 1;
		this.setMouseEvent();
	
	}
	
	private void setMouseEvent() {

		this.setOnMousePressed((t) -> {
			x = t.getX();
			y = t.getY();
			isShapeCreated = false;
		});
		
		this.setOnMouseDragged((t) -> {
			if(this.vpController.getSelectedTools() != "selection") {
				resetShapeSelected();													// reset la shape plus d�s un changement d'outil (Optionnel)
				Shape shapeCreated = null;
				if(this.vpController.getSelectedTools() == "circle") {				//Circle
					radius = Math.sqrt(
							Math.pow(x-t.getX(), 2) +
							Math.pow(y-t.getY(), 2)
							);
					Circle circ = new Circle(x, y, radius);
				    circ.setStroke(colorStrokePicker.getValue());
				    circ.setStrokeWidth(Character.getNumericValue(strokeSizeSelection.getValue().charAt(0)));
				    	
				    String imagePath = "images/" + fillingPatternSelection.getValue() + ".jpg";
					Image image = new Image(imagePath); 
					ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
					circ.setFill(radialGradient);

					shapeCreated = circ;
				}
				if(this.vpController.getSelectedTools() == "rectangle") {	//Rectangle
					width = Math.abs(x-t.getX());
					height = Math.abs(y-t.getY());
					Rectangle rect = new Rectangle(x, y, width, height);
					if(x > t.getX()) {
						rect.setX(x-width);
					}
					if(y > t.getY()) {
						rect.setY(y-height);
					}
					rect.setStroke(colorStrokePicker.getValue());
					rect.setStrokeWidth(Character.getNumericValue(strokeSizeSelection.getValue().charAt(0)));
					
					String imagePath = "images/" + fillingPatternSelection.getValue() + ".jpg";
					Image image = new Image(imagePath); 
					ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
					rect.setFill(radialGradient);
					
					shapeCreated = rect;
				}
				if(this.vpController.getSelectedTools() == "line") {			//Line    TODO
					Line l = new Line(x, y, t.getX(), t.getY());
					l.setStrokeWidth(Character.getNumericValue(strokeSizeSelection.getValue().charAt(0)));
					l.setStroke(colorStrokePicker.getValue());
					shapeCreated = l;
				}
				
				//Ajout de la forme
				if(shapeCreated != null) {
					if(this.getChildren().size() >= nbChildrenMax) {
						this.getChildren().remove(this.getChildren().size()-1);
					}
					this.getChildren().add(shapeCreated);
					isShapeCreated = true;
				}
			}
		});
		
		this.setOnMouseReleased((t) -> {
			if(isShapeCreated) {
				Shape shape1 = (Shape)this.getChildren().get(nbChildrenMax-1);
				
				shape1.setOnMouseClicked((ts) -> {									//Selection d'une forme
					if(this.vpController.getSelectedTools() == "selection") {
						resetShapeSelected();
						this.shapeSelected = shape1;
						shapeSelected.setOpacity(0.5);
						colorStrokePicker.setValue((Color)shapeSelected.getStroke());
					}
				});
				shape1.setOnKeyPressed(e -> {
					if (e.getCode() == KeyCode.RIGHT) {
					    System.out.println("test");
					}
				});	
				shapesList.add(shape1);
				switch(this.vpController.getSelectedTools()) {
				case "circle": 
					circlesList.add((Circle) shape1);
					break;
				case "rectangle": 
					rectanglesList.add((Rectangle) shape1);
					break;
				case "line": 
					lineList.add((Line) shape1);
					break;
				
				}
				nbChildrenMax++;
			}
		});
	}

	
	public void setColorPickerListener() {
		this.colorStrokePicker = this.hpController.getColorStrokePicker();
		colorStrokePicker.setOnAction((t) -> {
			if(shapeSelected != null) {
				shapeSelected.setStroke(colorStrokePicker.getValue());
			}
		});
	}
	
	public void setFillingPatternListener() {
		this.fillingPatternSelection = this.hpController.getFillingPattern();
		fillingPatternSelection.setOnAction((t) -> {
			if(shapeSelected != null && fillingPatternSelection.getValue() != "None") {
				String imagePath = "images/" + fillingPatternSelection.getValue() + ".jpg";
				Image image = new Image(imagePath); 
			    ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
			    shapeSelected.setFill(radialGradient);
			}
		});
	}
	
	public void setStrokeSizeListener() {
		this.strokeSizeSelection = this.hpController.getStrokeSize();
		strokeSizeSelection.setOnAction((t) -> {
			if(shapeSelected != null) {
			    shapeSelected.setStrokeWidth(Character.getNumericValue(strokeSizeSelection.getValue().charAt(0)));
			}
		});
	}
	
	public void keyPressedEvent(String Key) {
		if (Key == "M") {
			zoom(2);
		}
		if (Key == "L") {
			zoom(0.5);
		}
		if(shapeSelected != null) {
			switch(Key) {
			  case "Z":
				  shapeSelected.setTranslateY(shapeSelected.getTranslateY()-2);
				  break;
			  case "Q":
				  shapeSelected.setTranslateX(shapeSelected.getTranslateX()-2);
				  break;
			  case "S":
				  shapeSelected.setTranslateY(shapeSelected.getTranslateY()+2);
				  break;
			  case "D":
				  shapeSelected.setTranslateX(shapeSelected.getTranslateX()+2);
				  break;
			  case "A":
				  shapeSelected.setScaleX(shapeSelected.getScaleX()+0.05);
				  shapeSelected.setScaleY(shapeSelected.getScaleY()+0.05);
				  break;
			  case "E":
				  shapeSelected.setScaleX(shapeSelected.getScaleX()-0.05);
				  shapeSelected.setScaleY(shapeSelected.getScaleY()-0.05);
				  break;
			  case "R":
				  shapeSelected.setRotate(shapeSelected.getRotate()+2);
				  break;
			}
		}
	}
	
	private void resetShapeSelected() {
		if(shapeSelected != null) {
			shapeSelected.setOpacity(1);
			shapeSelected = null;
		}
	}
	
	public void setHpController(HorizontalPaletteController hpController) {
		this.hpController = hpController;
	}
	
	public void setVpController(VerticalPaletteController vpController) {
		this.vpController = vpController;
	}
	
	public void setNbChildrenMax(int nb) {
		this.nbChildrenMax = nb;
	}
	
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

}
