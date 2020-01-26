package views;

import javafx.fxml.FXML;

public class RootLayoutController extends AController {
	
	private DrawAreaController dwController;
	
	/**
     * Create a new sheet
     */
	@FXML
    private void handleNew() {
		dwController.newDrawingSheet();
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
//        FileChooser fileChooser = new FileChooser();
//
//        // Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "XML files (*.xml)", "*.xml");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Show save file dialog
//        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
//
//        if (file != null) {
//            mainApp.loadPersonDataFromFile(file);
//        }
    }

//    /**
//     * Saves the file to the person file that is currently open. If there is no
//     * open file, the "save as" dialog is shown.
//     */
//    @FXML
//    private void handleSave() {
//        File personFile = mainApp.getPersonFilePath();
//        if (personFile != null) {
//            mainApp.savePersonDataToFile(personFile);
//        } else {
//            handleSaveAs();
//        }
//    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSave() {
//        FileChooser fileChooser = new FileChooser();
//
//        // Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "XML files (*.xml)", "*.xml");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Show save file dialog
//        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
//
//        if (file != null) {
//            // Make sure it has the correct extension
//            if (!file.getPath().endsWith(".xml")) {
//                file = new File(file.getPath() + ".xml");
//            }
//            mainApp.savePersonDataToFile(file);
//        }
    }
    
    /**
     * Closes the current sheet.
     */
    @FXML
    private void handleClose() {
		dwController.closeDrawingSheet();
    }


    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

	public void setDwController(DrawAreaController dwController) {
		this.dwController = dwController;
	}

}
