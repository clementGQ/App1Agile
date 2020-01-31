package views;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.Label;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	
	public void getFiles() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./src/main/java/saves"));
		fileChooser.setTitle("Open Resource File");
		File selectedFile = fileChooser.showOpenDialog(dialogStage);
		
		mainApp.getDaController().newDrawingSheet();

        int indexSheet = mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
        ColorPicker cp = mainApp.getHpController().getColorStrokePicker();
        VerticalPaletteController vpController = mainApp.getVpController();
        mainApp.getDaController().getDrawingSheetControllerList().get(indexSheet).getDrawingSheet().loadShapes(selectedFile, cp, mainApp, vpController);
		
	}

}
