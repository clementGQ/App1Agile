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