package views;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

public class HorizontalBarController extends AController{
	
	@FXML
	private Button minOrMaxButton;
	
	@FXML
	private ColorPicker colorPicker;
	
	@FXML
	private MenuButton fillingPatternMenuButton;
	
	@FXML
	private MenuItem fillingPattern1;
	
	@FXML
	private MenuItem fillingPattern2;
	
	@FXML
	private MenuItem fillingPattern3;
	
	public HorizontalBarController() {
		
	}
	
	@FXML
	private void onColorSelected() {
		Color c = colorPicker.getValue();
        System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
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
    
    @FXML
    public void patternFillingSelection() {
    	System.out.println("Click on pattern filling button");    	
    }
    
    @FXML
    public void fillingPattern1Selection() {
    	System.out.println("filling pattern 1");
    	fillingPatternMenuButton.setText("coockies");
    }
    
    @FXML
    public void fillingPattern2Selection() {
    	System.out.println("filling pattern 2");
    	fillingPatternMenuButton.setText("option 2");
    }
    
    @FXML
    public void fillingPattern3Selection() {
    	System.out.println("filling pattern 3");
    	fillingPatternMenuButton.setText("option 3");
    }
    
    
    
}
