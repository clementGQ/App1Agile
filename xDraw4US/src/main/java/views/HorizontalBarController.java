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
	
	private Color colorPicked;
	
	public HorizontalBarController() {}
	
	@FXML
	private void onColorSelected() {
		colorPicked = colorPicker.getValue();
        System.out.println("New Color's RGB = "+colorPicked.getRed()+" "+colorPicked.getGreen()+" "+colorPicked.getBlue());
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
	
	
    public Color getColorPicked() {
		return colorPicked;
	}
}
