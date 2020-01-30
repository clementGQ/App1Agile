package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DCircle;
import model.DLine;
import model.DRectangle;
import model.DShape;
import model.DrawingSheet;
import views.AController;
import views.AttributePanelController;
import views.HorizontalPaletteController;
import views.VerticalPaletteController;

public class DrawingSheetController {
	
	public DrawingSheet drawingSheet;
	
	private HorizontalPaletteController hpController;
	private VerticalPaletteController vpController;
	private MainApp mainApp;
	
	private ChoiceBox<String> fillingPatternSelection;
	private ChoiceBox<String> strokeSizeSelection;
	private ColorPicker colorStrokePicker;
	
	private double x,y;
	private double radius;
	double width;
	double height;
	private boolean isShapeCreated = false;
	
	//builder
	public DrawingSheetController(HorizontalPaletteController hpController,VerticalPaletteController vpController, MainApp mainApp) {
		drawingSheet = new DrawingSheet();
		this.hpController =hpController;
		this.vpController =vpController;
		this.mainApp = mainApp;
		this.setMouseEvent();
		this.colorStrokePicker = this.hpController.getColorStrokePicker();
		this.fillingPatternSelection = this.hpController.getFillingPattern();
		this.strokeSizeSelection = this.hpController.getStrokeSize();
	}
	
	/**
     * Controls the keys events
     */
	public void keyPressedEvent(String Key) {
		if (Key == "M") {
			//drawingSheet.zoom(2);
			drawingSheet.saveShapes();
		}
		if (Key == "L") {
			//drawingSheet.zoom(0.5);
			drawingSheet.loadShapes();
		}
		if(drawingSheet.getShapeSelected() != null) {
			switch(Key) {
			  case "Z":
				  drawingSheet.getShapeSelected().setTranslateY(drawingSheet.getShapeSelected().getTranslateY()-2);
				  break;
			  case "Q":
				  drawingSheet.getShapeSelected().setTranslateX(drawingSheet.getShapeSelected().getTranslateX()-2);
				  break;
			  case "S":
				  drawingSheet.getShapeSelected().setTranslateY(drawingSheet.getShapeSelected().getTranslateY()+2);
				  break;
			  case "D":
				  drawingSheet.getShapeSelected().setTranslateX(drawingSheet.getShapeSelected().getTranslateX()+2);
				  break;
			  case "A":
				  drawingSheet.getShapeSelected().setScaleX(drawingSheet.getShapeSelected().getScaleX()+0.05);
				  drawingSheet.getShapeSelected().setScaleY(drawingSheet.getShapeSelected().getScaleY()+0.05);
				  break;
			  case "E":
				  drawingSheet.getShapeSelected().setScaleX(drawingSheet.getShapeSelected().getScaleX()-0.05);
				  drawingSheet.getShapeSelected().setScaleY(drawingSheet.getShapeSelected().getScaleY()-0.05);
				  break;
			  case "R":
				  drawingSheet.getShapeSelected().setRotate(drawingSheet.getShapeSelected().getRotate()+2);
				  break;
			}
		}
	}
	
	/**
     * Controls the mouse events
     */
	private void setMouseEvent() {
		drawingSheet.setOnMousePressed((t) -> {
			x = t.getX();
			y = t.getY();
			isShapeCreated = false;
		});
		
		drawingSheet.setOnMouseDragged((t) -> {
			if(this.vpController.getSelectedTools() != "selection") {
				drawingSheet.resetShapeSelected();							// reset la shape plus d�s un changement d'outil (Optionnel)
				Shape shapeCreated = null;
				if(this.vpController.getSelectedTools() == "circle") {				//Circle
					
					if(this.vpController.getStartingPoint() == "center") {
						radius = Math.sqrt(
								Math.pow(x-t.getX(), 2) +
								Math.pow(y-t.getY(), 2)
								);
					} else {
						radius = Math.sqrt(
								Math.pow(x-t.getX(), 2) +
								Math.pow(y-t.getY(), 2)
								)/2;
					}
					Circle circ = new Circle(x, y, radius);
					if(this.vpController.getStartingPoint() == "corner") {
						circ.setCenterX(x+(t.getX()-x)/2);
						circ.setCenterY(y+(t.getY()-y)/2);
					}
				    circ.setStroke(colorStrokePicker.getValue());
				    circ.setStrokeWidth(Character.getNumericValue(strokeSizeSelection.getValue().charAt(0)));
				    	
				    String imagePath = "images/" + fillingPatternSelection.getValue() + ".jpg";
					Image image = new Image(imagePath); 
					ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
					circ.setFill(radialGradient);

					shapeCreated = circ;
				}
				if(this.vpController.getSelectedTools() == "rectangle") {	//Rectangle
					if(this.vpController.getStartingPoint() == "corner") {
						width = Math.abs(x-t.getX());
						height = Math.abs(y-t.getY());
					}
					if(this.vpController.getStartingPoint() == "center") {
						width = Math.abs(x-t.getX()) * 2;
						height = Math.abs(y-t.getY()) * 2;
					}
					Rectangle rect = new Rectangle(x, y, width, height);
					if(this.vpController.getStartingPoint() == "corner") {
						if(x > t.getX()) {
							rect.setX(x-width);
						}
						if(y > t.getY()) {
							rect.setY(y-height);
						}
					}
					if(this.vpController.getStartingPoint() == "center") {
						rect.setX(x-width/2);	
						rect.setY(y-height/2);
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
					if(this.vpController.getStartingPoint() == "center") { 
						l.setStartX(x-(t.getX()-x));
						l.setStartY(y-(t.getY()-y));
					}
					l.setStrokeWidth(Character.getNumericValue(strokeSizeSelection.getValue().charAt(0)));
					l.setStroke(colorStrokePicker.getValue());
					shapeCreated = l;
				}
				
				//Ajout de la forme
				if(shapeCreated != null) {
					if(drawingSheet.getChildren().size() >= drawingSheet.getNbChildrenMax()) {
						drawingSheet.getChildren().remove(drawingSheet.getChildren().size()-1);
					}
					drawingSheet.getChildren().add(shapeCreated);
					isShapeCreated = true;
				}
			}
		});
		
		this.drawingSheet.setOnMouseReleased((t) -> {
			if(isShapeCreated) {
				Shape shape1 = (Shape)drawingSheet.getChildren().get(drawingSheet.getNbChildrenMax()-1);
				shape1.setOnMouseClicked((ts) -> {									//Selection d'une forme
					if(this.vpController.getSelectedTools() == "selection") {
						drawingSheet.resetShapeSelected();
						drawingSheet.setShapeSelected(shape1) ;
						drawingSheet.getShapeSelected().setOpacity(0.5);
						colorStrokePicker.setValue((Color)drawingSheet.getShapeSelected().getStroke());
						mainApp.showShapeEditPanel(shape1);
					}
				});
				switch(this.vpController.getSelectedTools()) {
				case "circle": 
					drawingSheet.getShapesList().add(new DCircle((Circle) shape1));
					break;
				case "rectangle": 
					drawingSheet.getShapesList().add(new DRectangle((Rectangle) shape1));
					break;
				case "line": 
					drawingSheet.getShapesList().add(new DLine((Line) shape1));
					break;
				}
				drawingSheet.setNbChildrenMax(drawingSheet.getNbChildrenMax()+1);
			}
		});
	}
 

	
	
	//getters setters
	public void setHpController(HorizontalPaletteController hpController) {
		this.hpController = hpController;
	}
	
	public HorizontalPaletteController getHpController() {
		return hpController;
	}
	
	public void setVpController(VerticalPaletteController vpController) {
		this.vpController = vpController;
	}
	
	public DrawingSheet getDrawingSheet() {
		return drawingSheet;
	}
	

	
	
	

}
