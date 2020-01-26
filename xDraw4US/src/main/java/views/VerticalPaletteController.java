package views;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VerticalPaletteController extends AController{
	
	
	private String selectedTools;
	
	@FXML
	private Button selectionButton;
	
	public VerticalPaletteController() {
		selectedTools = "selection";
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
     * Selection tool to select a line to draw.
     */
    public void selectLine() {
    	selectedTools = "line";
    }
    
    public String getSelectedTools() {
    	return this.selectedTools;
    }
    
    
    
}