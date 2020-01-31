package views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;

public class AttributPanelController extends AController {

    @FXML
    private TextField translationXField;
    @FXML
    private TextField translationYField;
    @FXML
    private TextField rotationField;
    @FXML
    private TextField scaleField;

    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	translationXField.setOnAction((t) -> {
    		if (translationXField.getText() != null && translationXField.getText().length() != 0) {
    			int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
            	this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setTranslateX(Double.valueOf(translationXField.getText()));
    		}
    	});
    	translationYField.setOnAction((t) -> {
   		 	if (translationYField.getText() != null && translationYField.getText().length() != 0) {
   		 		int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
   		 		this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setTranslateY(Double.valueOf(translationYField.getText()));
            }
    	});
    	rotationField.setOnAction((t) -> {
   		 	if (rotationField.getText() != null && rotationField.getText().length() != 0) {
   		 		int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
   		 		this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setRotate(Double.valueOf(rotationField.getText()));
            }
    	});
    	scaleField.setOnAction((t) -> {
	   		if (scaleField.getText() != null && scaleField.getText().length() != 0) {
	           int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
	           this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setScaleX(Double.valueOf(scaleField.getText()));
	           this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setScaleY(Double.valueOf(scaleField.getText()));
	        }
    	});
    }


    /**
     * Sets the shape to be edited in the dialog.
     * 
     * @param shape
     */
    public void editShape(Shape shape) {
        int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
        translationXField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getTranslateX()));
        translationYField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getTranslateY()));
        rotationField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getRotate()));
        scaleField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getScaleX()));
        
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }


    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        mainApp.getPaletteLayout().setRight(null);
    }

}