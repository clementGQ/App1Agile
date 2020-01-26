package views;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class HorizontalBarController extends AController{
	
	@FXML
	private Button minOrMaxButton;
	
	@FXML
	private ColorPicker colorPicker;
	
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
}
