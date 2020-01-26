package model;


import java.util.ArrayList;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
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
	//private ColorPicker colorPicker;
	private ColorPicker colorStrokePicker;

	double orgSceneX, orgSceneY;
	
	private double x,y; 			// Shape attributs
	private double radius; 			//Circle attributs
	private double width, height; 	//Rectangle attributs
	
	private int nbChildrenMax;
	private ArrayList<Shape> shapesList = new ArrayList<>();
	
	private boolean isShapeCreated = false;
	
	private Shape shapeSelected = null;

	public DrawingSheet() {
		super();
		
		this.setStyle("-fx-background-color: white;");
		this.setPrefSize(700,400);
//		AnchorPane.setTopAnchor(this,30d);
//		AnchorPane.setLeftAnchor(this,30d);
//		AnchorPane.setRightAnchor(this,30d);
//		AnchorPane.setBottomAnchor(this,30d);
		
		nbChildrenMax = 1;

		this.setOnMousePressed((t) -> {
			x = t.getX();
			y = t.getY();
			isShapeCreated = false;
		});
		
		this.setOnMouseDragged((t) -> {
			if(this.vpController.getSelectedTools() != "selection") {
				resetShapeSelected();													//TODO reset la shape plus d�s un changement d'outil (Optionnel)
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
				if(this.vpController.getSelectedTools() == "rectangle") {			//Rectangle
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
					resetShapeSelected();
					this.shapeSelected = shape1;
					shapeSelected.setOpacity(0.5);
					colorStrokePicker.setValue((Color)shapeSelected.getStroke());
				});
				
				
				shapesList.add(shape1);
				System.out.println("nombre de formes : " + shapesList.size());
				System.out.println("nombre de children : " + nbChildrenMax);
				nbChildrenMax++;
			}
		});
		
		/*
		this.setOnKeyPressed(e -> {
			if(shapeSelected != null ) {					//Shape Selected
			    if (e.getCode() == KeyCode.RIGHT) {
			    	//shapeSelected.setRotate(shapeSelected.getRotate()+2);   //Rotation
			    	shapeSelected.setTranslateX(shapeSelected.getTranslateX()+2);
			    }
			    if (e.getCode() == KeyCode.LEFT) {
			    	shapeSelected.setTranslateX(shapeSelected.getTranslateX()-2);
			    }
			    if (e.getCode() == KeyCode.UP) {
			    	shapeSelected.setTranslateY(shapeSelected.getTranslateY()-2);
			    }
			    if (e.getCode() == KeyCode.DOWN) {
			    	shapeSelected.setTranslateY(shapeSelected.getTranslateY()+2);
			    }
			}
		});*/
	}
	
	/**
	 * Get the list of drawn shapes.
	 * @return the list.
	 */
	public ArrayList<Shape> getShapesList() {
		return shapesList;
	}
	public void setColorPickerListener() {
		//this.colorPicker = this.hpController.getColorPicker();
		/*colorPicker.setOnAction((t) -> {
			if(shapeSelected != null) {
				shapeSelected.setFill(colorPicker.getValue());
			}
		});*/
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

}
