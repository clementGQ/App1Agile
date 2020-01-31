package views;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ImportPanelController extends AController {
	
	
	private Stage dialogStage;
	private boolean okClicked = false;
	
	@FXML
	private Button Cancel;
	
	@FXML
	private Button Import;
	
	@FXML
	private Label importLabel;
	
	@FXML
	private TextField field1;
	
	@FXML
	private TextField field2;
	
	@FXML
	private TextField field3;
	
	@FXML
	private TextField field4;
	
	/**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelButton() {
        dialogStage.close();
    }
	
	@FXML
	public void handleImportButton() {
		System.out.println("Click sur save button");
		
	}
	
	public boolean isOkClicked() {
        return okClicked;
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
}
