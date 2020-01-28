package views;

import controllers.DrawingSheetController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class HorizontalPaletteController extends AController{
	
	
	@FXML
	private Button minOrMaxButton;
	
	@FXML
	private ColorPicker colorStrokePicker;
	
	@FXML
	private ChoiceBox<String> fillingPatternSelection;
	
	@FXML 
	private ObservableList<String> fillingPatternList = FXCollections.observableArrayList("cube", "line", "beer");
	
	@FXML
	private ChoiceBox<String> strokeSizeSelection;
	
	@FXML 
	private ObservableList<String> strokeSizeList = FXCollections.observableArrayList("1 px", "2 px", "3 px", "5 px", "8 px");
	
	public HorizontalPaletteController() {}
	
	@FXML
	private void onColorSelected() {}
	
	@FXML
	private void initialize() {
		fillingPatternSelection.setValue("cube");
		fillingPatternSelection.setItems(fillingPatternList);
		strokeSizeSelection.setValue("1 px");
		strokeSizeSelection.setItems(strokeSizeList);
		handleStrokeSize();
	}

	@FXML
    public void minOrMaximize() {
    	if(mainApp.getPrimaryStage().isMaximized()) {
        	mainApp.getPrimaryStage().setMaximized(false);
        	mainApp.getPrimaryStage().setHeight(600);    	
        	mainApp.getPrimaryStage().setWidth(800);
        	minOrMaxButton.setText("Maximize");
        	for(DrawingSheetController d: mainApp.getDaController().getDrawingSheetControllerList()) {
        		d.getDrawingSheet().zoom(0.333);
        	}
    	}else {
        	mainApp.getPrimaryStage().setMaximized(true);
        	minOrMaxButton.setText("800x600");
        	for(DrawingSheetController d: mainApp.getDaController().getDrawingSheetControllerList()) {
        		d.getDrawingSheet().zoom(3);
        	}
    	}
    }
	
	@FXML
	public void handleColorStroke() {
		if(mainApp.getDaController().getDrawingSheetControllerList().size() != 0) {
			int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
			if (mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected() != null) 
				mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setStroke(colorStrokePicker.getValue());
		}
		
	}
	
	public void handleFillingPattern() {
		if(mainApp.getDaController().getDrawingSheetControllerList().size() != 0) {
			int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
			if (mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected() != null && fillingPatternSelection.getValue() != "None") {
				String imagePath = "images/" + fillingPatternSelection.getValue() + ".jpg";
				Image image = new Image(imagePath);
				ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
				mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setFill(radialGradient);
			}
		}
	}
	
	private void handleStrokeSize() {
		strokeSizeSelection.setOnAction((t)->{
			if(mainApp.getDaController().getDrawingSheetControllerList().size() != 0) {
				int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
				if (mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected() != null) {
					mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected()
							.setStrokeWidth(Character.getNumericValue(strokeSizeSelection.getValue().charAt(0)));
				}
			}
		});
		
	}
	
	//getters setters
    public ColorPicker getColorStrokePicker() {
		return this.colorStrokePicker;
	}
    
    public ChoiceBox<String> getFillingPattern() {
    	return this.fillingPatternSelection;
    }
    
    public ChoiceBox<String> getStrokeSize() {
    	return this.strokeSizeSelection;
    }
    
}
