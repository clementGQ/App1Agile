package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HorizontalBarController extends AController{
	
	@FXML
	private Button minOrMaxButton;
	
	public HorizontalBarController() {}
	
    @FXML
    /**
     * Minimize to 800x600 or maximize the window.
     */
    public void minOrMaximize() {
    	if(mainApp.getPrimaryStage().isMaximized()) {
        	mainApp.getPrimaryStage().setMaximized(false);
        	mainApp.getPrimaryStage().setHeight(600);    	
        	mainApp.getPrimaryStage().setWidth(800);
    	}else {
        	mainApp.getPrimaryStage().setMaximized(true);
    	}
    }
}
