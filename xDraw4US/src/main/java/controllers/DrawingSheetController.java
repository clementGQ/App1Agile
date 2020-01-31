package controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import model.DShape;
import model.DrawingSheet;
import views.HorizontalPaletteController;
import views.VerticalPaletteController;

public class DrawingSheetController {
	
	public DrawingSheet drawingSheet;
	
	private HorizontalPaletteController hpController;
	private VerticalPaletteController vpController;
	private MainApp mainApp;
	
	private ChoiceBox<String> strokeSizeSelection;
	private ColorPicker colorStrokePicker;
	
	private double x,y;
	private boolean isShapeCreated = false;
	
	/**
	 * Constructor
	 */
	public DrawingSheetController(HorizontalPaletteController hpController,VerticalPaletteController vpController, MainApp mainApp) {
		drawingSheet = new DrawingSheet();
		this.hpController =hpController;
		this.vpController =vpController;
		this.mainApp = mainApp;
		this.setMouseEvent();
		this.colorStrokePicker = this.hpController.getColorStrokePicker();
		this.strokeSizeSelection = this.hpController.getStrokeSize();
	}
	
	/**
     * Controls the keys events
     */	
	public void keyPressedEvent(String Key) {					// A retirer
		if (Key == "M") {
			drawingSheet.saveShapes();
		}
		if (Key == "L") {
			drawingSheet.loadShapes(colorStrokePicker, mainApp, vpController);
			//Load Shape 
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
				drawingSheet.resetShapeSelected();
				
				Color colorStroke = colorStrokePicker.getValue();
				double strokeWidth = Character.getNumericValue(strokeSizeSelection.getValue().charAt(0));
				String filePath =  "images/" + this.hpController.getFillingPattern() + ".jpg";
				
				DShapeFactory dSFactory = new DShapeFactory();
				DShape dShapeCreated = dSFactory.getShape(this.vpController.getSelectedTools(), 
						x, y, t.getX(), t.getY(), this.vpController.getStartingPoint(),
						filePath, strokeWidth, colorStroke);

				if(dShapeCreated != null) {
					if(drawingSheet.getShapesList().size() >= drawingSheet.getNbChildrenMax()) {
						drawingSheet.getShapesList().remove(drawingSheet.getShapesList().size()-1);
						drawingSheet.getChildren().remove(drawingSheet.getChildren().size()-1);
					}
					drawingSheet.getShapesList().add(dShapeCreated);
					drawingSheet.getChildren().add(dShapeCreated.getShape());
					isShapeCreated = true;
				}		
			}
		});
		
		
		this.drawingSheet.setOnMouseReleased((t) -> {
			if(isShapeCreated) {
				DShape dShape = drawingSheet.getShapesList().get(drawingSheet.getShapesList().size()-1);
				drawingSheet.addListener(dShape, colorStrokePicker, mainApp, vpController);
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
