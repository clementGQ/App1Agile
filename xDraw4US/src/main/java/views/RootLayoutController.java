package views;

import javafx.fxml.FXML;

public class RootLayoutController extends AController {
	
	private DrawAreaController dwController;
	
	/**
     * Creates a new sheet
     */
	@FXML
    private void handleNew() {
		dwController.newDrawingSheet();
    }

	/**
     * 
     */
	@FXML
    private void handleOpen() {
    	System.out.println("uyf");
    	boolean isOkClicked = mainApp.showImportPanel();
    }

    /**
     * 
     */
    @FXML
    private void handleSave() {
    	mainApp.showSavePanel();
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
    
    /**
     * Delete the content of the current sheet
     */
    @FXML
	public void handleDelete() {
    	if(mainApp.getDaController().getDrawingSheetControllerList().size() != 0) {
    		int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
    		this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().deleteAll();
    	}
	}
    
    //getters setters
	public void setDwController(DrawAreaController dwController) {
		this.dwController = dwController;
	}

}
