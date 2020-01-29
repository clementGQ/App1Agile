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
			drawingSheet.zoom(2);
		}
		if (Key == "L") {
			drawingSheet.zoom(0.5);
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
				drawingSheet.resetShapeSelected();													// reset la shape plus d�s un changement d'outil (Optionnel)
				Shape shapeCreated = null;
				if(this.vpController.getSelectedTools() == "circle") {				//Circle
					double radius = Math.sqrt(
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
					double width = Math.abs(x-t.getX());
					double height = Math.abs(y-t.getY());
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
						System.out.println("thomas le morfale");
						mainApp.showShapeEditPanel(shape1);
					}
				});
				shape1.setOnKeyPressed(e -> {
					if (e.getCode() == KeyCode.RIGHT) {
					    System.out.println("test");
					}
				});	
				drawingSheet.getShapesList().add(shape1);
				switch(this.vpController.getSelectedTools()) {
				case "circle": 
					drawingSheet.getCirclesList().add((Circle) shape1);
					break;
				case "rectangle": 
					drawingSheet.getRectanglesList().add((Rectangle) shape1);
					break;
				case "line": 
					drawingSheet.getLinesList().add((Line) shape1);
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
