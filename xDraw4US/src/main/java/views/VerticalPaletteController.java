package views;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VerticalPaletteController extends AController{
	
	
	private String selectedTools;
	
	private String startingPoint = "center";
	
	private Image imageCenter;
	private Image imageCorner;
	
	@FXML
	private Button selectionButton;
	
	@FXML
	private Button changeStartButton;
	
	public VerticalPaletteController() {
		selectedTools = "selection";
    	this.imageCenter = new Image("images/center.jpg",50, 55, false, false);
		this.imageCorner = new Image("images/corner.jpg",50, 55, false, false);
	}
	
	public void initialize() {
		changeStartButton.setGraphic(new ImageView(imageCenter));
	}
	
    @FXML
    /**
     * Selection tool to select a shape already draw on the canvas.
     */
    public void selection() {
    	selectedTools = "selection";
    }
    
    
    /**
     * Selection tool to select a rectangle to draw.
     */
    public void selectRectangle() {
    	selectedTools = "rectangle";
    }
    
    /**
     * Selection tool to select a circle to draw.
     */
    public void selectCircle() {
    	selectedTools = "circle";
    }
    
    /**
     * Selection tool to select the starting point
     */
    public void onChangeStartClicked() {
    	if(startingPoint == "center") {
    		startingPoint = "corner";
    		changeStartButton.setGraphic(new ImageView(imageCorner));
    	} else {
    		startingPoint = "center";
    		changeStartButton.setGraphic(new ImageView(imageCenter));
    	}
    }
    
    public void handleZoom(double mult) {
		DrawAreaController dac = this.mainApp.getDaController();
		if(dac.getDrawingSheetControllerList().size() != 0) {
			int activeTableIndex = dac.getTable().getSelectionModel().getSelectedIndex();
			dac.getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().zoom(mult,false);
		}
	}
	
	@FXML
	public void handleZoomIn() {
		handleZoom(2d); 
	}
	
	@FXML
	public void handleZoomOut() {
		handleZoom(0.5d);
		
	}
    
    /**
     * Selection tool to select a line to draw.
     */
    public void selectLine() {
    	selectedTools = "line";
    }
    
    public String getSelectedTools() {
    	return this.selectedTools;
    }
    
    public String getStartingPoint() {
    	return this.startingPoint;
    }
    
}