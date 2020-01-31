package views;

import controllers.DrawingSheetController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.ImagePattern;

public class HorizontalPaletteController extends AController{
	
	private double maximizeRate;
	
	@FXML
	private Button minOrMaxButton;
	
	@FXML
	private Button pattern1;
	
	@FXML
	private Button pattern2;
	
	@FXML
	private Button pattern3;
	
	@FXML
	private Button pattern4;
	
	@FXML
	private Button pattern5;
	
	@FXML
	private ColorPicker colorStrokePicker;
	
	@FXML
	private String fillingPatternSelection;
	
	@FXML
	private ChoiceBox<String> strokeSizeSelection;
	
	@FXML 
	private ObservableList<String> strokeSizeList = FXCollections.observableArrayList("1 px", "2 px", "3 px", "5 px", "8 px");
	
	public HorizontalPaletteController() {}
	
	@FXML
	private void onColorSelected() {}
	
	@FXML
	private void initialize() {
		pattern1.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("images/pattern1.jpg"), 0, 0, 150, 150, false), null, null )));
		pattern2.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("images/pattern2.jpg"), 0, 0, 150, 150, false), null, null )));
		pattern3.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("images/pattern3.jpg"), 0, 0, 150, 150, false), null, null )));
		pattern4.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("images/pattern4.jpg"), 0, 0, 150, 150, false), null, null )));
		pattern5.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("images/pattern5.jpg"), 0, 0, 150, 20, false), null, null )));
		fillingPatternSelection = "pattern5";
		strokeSizeSelection.setValue("1 px");
		strokeSizeSelection.setItems(strokeSizeList);
		handleStrokeSize();
		this.maximizeRate = -1d;
	}

	@FXML
    public void minOrMaximize() {
    	if(mainApp.getPrimaryStage().isMaximized()) {
        	mainApp.getPrimaryStage().setMaximized(false);
        	mainApp.getPrimaryStage().setHeight(600);    	
        	mainApp.getPrimaryStage().setWidth(800);
        	minOrMaxButton.setText("Maximize");
        	for(DrawingSheetController d: mainApp.getDaController().getDrawingSheetControllerList()) {
        		d.getDrawingSheet().zoom(1d/maximizeRate, true);
        	}
    	}else {
        	mainApp.getPrimaryStage().setMaximized(true);
        	minOrMaxButton.setText("800x600");
        	if(maximizeRate == -1d)
        		maximizeRate = Math.max((mainApp.getPrimaryStage().getHeight()-145d)/455d,(mainApp.getPrimaryStage().getWidth()-117d)/683d);
        	for(DrawingSheetController d: mainApp.getDaController().getDrawingSheetControllerList()) {
        		d.getDrawingSheet().zoom(maximizeRate, true);
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
	
	@FXML
	private void patternChoice1() {
		this.fillingPatternSelection = "pattern1";
		  handleFillingPattern("pattern1.jpg");
	}
	@FXML
	private void patternChoice2() {
		this.fillingPatternSelection = "pattern2";

		  handleFillingPattern("pattern2.jpg");
	}
	@FXML
	private void patternChoice3() {
		this.fillingPatternSelection = "pattern3";

		  handleFillingPattern("pattern3.jpg");
	}
	
	@FXML
	private void patternChoice4() {
		this.fillingPatternSelection = "pattern4";

		  handleFillingPattern("pattern4.jpg");
	}
	@FXML
	private void patternChoice5() {
		this.fillingPatternSelection = "pattern5";

		  handleFillingPattern("pattern5.jpg");
	}
	
	
	public void handleFillingPattern(String choice) {
		if(mainApp.getDaController().getDrawingSheetControllerList().size() != 0) {
			int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
			if (mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected() != null) {
				String imagePath = "images/"+choice;
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
    
    public String getFillingPattern() {
    	return this.fillingPatternSelection;
    }
    
    public ChoiceBox<String> getStrokeSize() {
    	return this.strokeSizeSelection;
    }
    
}
