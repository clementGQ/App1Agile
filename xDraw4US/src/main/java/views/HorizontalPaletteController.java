package views;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;

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
	}

	@FXML
    /**
     * Minimize to 800x600 or maximize the window.
     */
    public void minOrMaximize() {
    	if(mainApp.getPrimaryStage().isMaximized()) {
        	mainApp.getPrimaryStage().setMaximized(false);
        	mainApp.getPrimaryStage().setHeight(600);    	
        	mainApp.getPrimaryStage().setWidth(800);
        	minOrMaxButton.setText("Maximize");
    	}else {
        	mainApp.getPrimaryStage().setMaximized(true);
        	minOrMaxButton.setText("800x600");
    	}
    }
	/*
    public ColorPicker getColorPicker() {
		return colorPicker;
	}*/
    
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
