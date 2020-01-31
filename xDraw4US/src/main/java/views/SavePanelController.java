package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import java.awt.Button;
//import java.awt.Label;
//import java.awt.TextField;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SavePanelController extends AController {
	
	private Stage dialogStage;
	private boolean okClicked = false;
	
	@FXML
	private Label fileNameLabel;
	
	@FXML
	private TextField fileNameTextField;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private Button saveButton;
	
	
	/**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelButton() {
        dialogStage.close();
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleSaveButton() {
        System.out.println("save");
    }
	
	public boolean isOkClicked() {
        return okClicked;
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
//	@FXML
//	public void onClickSave() {
//		boolean okClicked = mainApp.showSavePanel();
//	}
	
	
	
	
	
	
}
