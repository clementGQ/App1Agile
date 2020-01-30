package views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;



public class AttributePanelController extends AController {

    @FXML
    private TextField translationXField;
    @FXML
    private TextField translationYField;
    @FXML
    private TextField rotationField;
    @FXML
    private TextField scaleField;

    

    private Stage dialogStage;
    private Shape shape;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the shape to be edited in the dialog.
     * 
     * @param shape
     */
    public void editShape(Shape shape) {
        this.shape = shape;
        int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
        translationXField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getTranslateX()));
        translationYField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getTranslateY()));
        rotationField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getRotate()));
        scaleField.setText(Double.toString(this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().getScaleX()));
        
    }
    
    
    
    
    @FXML
    private void updateShape() {
        if (isInputValid()) {

        	int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
       
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setTranslateX(Double.valueOf(translationXField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setTranslateY(Double.valueOf(translationYField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setRotate(Double.valueOf(rotationField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setScaleX(Double.valueOf(scaleField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setScaleY(Double.valueOf(scaleField.getText()));

        }
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {

        	int activeTableIndex = this.mainApp.getDaController().getTable().getSelectionModel().getSelectedIndex();
       
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setTranslateX(Double.valueOf(translationXField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setTranslateY(Double.valueOf(translationYField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setRotate(Double.valueOf(rotationField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setScaleX(Double.valueOf(scaleField.getText()));
            this.mainApp.getDaController().getDrawingSheetControllerList().get(activeTableIndex).getDrawingSheet().getShapeSelected().setScaleY(Double.valueOf(scaleField.getText()));
            
            okClicked = true;
            mainApp.getPaletteLayout().setRight(null);
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        mainApp.getPaletteLayout().setRight(null);
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (translationXField.getText() == null || translationXField.getText().length() == 0) {
            errorMessage += "No abscisse given\n"; 
        }
        if (translationYField.getText() == null || translationYField.getText().length() == 0) {
            errorMessage += "No ordonnee given\n"; 
        }
        if (rotationField.getText() == null || rotationField.getText().length() == 0) {
            errorMessage += "No rotation given\n"; 
        }

        if (scaleField.getText() == null || scaleField.getText().length() == 0) {
            errorMessage += "No scale given\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}
    
