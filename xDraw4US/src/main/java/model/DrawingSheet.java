package model;


import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import views.HorizontalBarController;

public class DrawingSheet extends Pane {

	private HorizontalBarController hbController;

	double orgSceneX, orgSceneY;
	
	private double x,y; 			// Shape attributs
	private double radius; 			//Circle attributs
	private double width, height; 	//Rectangle attributs
	
	private int nbChildrenMax;
	private ArrayList<Shape> shapesList = new ArrayList<>();
	
	private boolean isShapeCreated = false;
	
	//private Shape shapeSelected = null;
	
	private String utilSelected = "";
	private Paint colorSelected = Color.RED;

	public DrawingSheet() {
		super();
		this.setStyle("-fx-background-color: white;");
		this.setPrefSize(1000,1000);
		AnchorPane.setTopAnchor(this,30d);
		AnchorPane.setLeftAnchor(this,30d);
		AnchorPane.setRightAnchor(this,30d);
		AnchorPane.setBottomAnchor(this,30d);
		
		nbChildrenMax = 1;

		utilSelected = "circle"; 						// UTIL SELECTED (circle / rectangle / line / ... )
	

		this.setOnMousePressed((t) -> {
			x = t.getX();
			y = t.getY();
			isShapeCreated = false;
		});
		
		this.setOnMouseDragged((t) -> {
			if(utilSelected != "") {
				Shape shapeCreated = null;
				if(utilSelected == "circle") {				//Circle
					radius = Math.sqrt(
							Math.pow(x-t.getX(), 2) +
							Math.pow(y-t.getY(), 2)
							);
					Circle circ = new Circle(x, y, radius, this.hbController.getColorPicked());
					/*
					Image image = new Image("images/cookie.png"); 
				    ImagePattern radialGradient = new ImagePattern(image, 20, 20, 40, 40, false);
				    circ.setFill(radialGradient);*/
				    circ.setStroke(Color.BLACK);
				    circ.setStrokeWidth(3);
					
					
					shapeCreated = circ;
				}
				if(utilSelected == "rectangle") {			//Rectangle
					width = Math.abs(x-t.getSceneX());
					height = Math.abs(y-t.getSceneY());
					Rectangle rect = new Rectangle(x, y, width, height);
					if(x > t.getSceneX()) {
						rect.setX(x-width);
					}
					if(y > t.getSceneY()) {
						rect.setY(y-height);
					}
					//rect.setFill(colorSelected);
					
					// FILL WITH PATTERN
					Image image = new Image("images/beer-pattern.jpg"); 
				    ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
					rect.setFill(radialGradient);
					rect.setStroke(Color.BLACK);
					rect.setStrokeWidth(3);
					
					shapeCreated = rect;
				}
				if(utilSelected == "line") {			//Line    TODO
					Line l = new Line(x, y, t.getSceneX(), t.getSceneY());
					l.setStrokeWidth(3);
					l.setStroke(colorSelected);
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
				
				shape1.setOnMouseClicked((ts) -> {
					System.out.println("form selected");
					//shapeSelected = shape1;
				});
				
				
				shapesList.add(shape1);
				System.out.println("nombre de formes : " + shapesList.size());
				System.out.println("nombre de children : " + nbChildrenMax);
				nbChildrenMax++;
			}
		});
		/*
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.NUMPAD1) { 				// Util Selected
				System.out.println("touche 1");
		    	utilSelected = "circle";
		    	utilText.setText(utilSelected);
		    }
			if (e.getCode() == KeyCode.NUMPAD2) {
		    	utilSelected = "rectangle";
		    	utilText.setText(utilSelected);
		    }
			if (e.getCode() == KeyCode.NUMPAD3) {
		    	utilSelected = "line";
		    	utilText.setText(utilSelected);
		    }
			
			if (e.getCode() == KeyCode.R) { 				// Color Selected
		    	colorSelected = Color.RED;
		    	utilText.setFill(colorSelected);
		    }
			if (e.getCode() == KeyCode.G) {
				colorSelected = Color.GREEN;
				utilText.setFill(colorSelected);
		    }
			if (e.getCode() == KeyCode.B) {
				colorSelected = Color.BLUE;
				utilText.setFill(colorSelected);
			}
			if (e.getCode() == KeyCode.D) {
				this.getChildren().clear();
				shapesList.clear();
				nbChildrenMax = 1; // Text effacé mais on s'en fou
			}
			
			if(shapeSelected != null ) {					//Shape Selected
			    if (e.getCode() == KeyCode.R) {
			    	shapeSelected.setFill(Color.RED);
			    }
			    if (e.getCode() == KeyCode.G) {
					shapeSelected.setFill(Color.GREEN);
			    }
			    if (e.getCode() == KeyCode.B) {
			    	shapeSelected.setFill(Color.BLUE);
			    }
			    if (e.getCode() == KeyCode.RIGHT) {
			    	
			    	shapeSelected.setRotate(shapeSelected.getRotate()+2);
			    	//shapeSelected.setTranslateX(shapeSelected.getTranslateX()+2);
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
	
	public void setHbController(HorizontalBarController hbController) {
		this.hbController = hbController;
	}

}
