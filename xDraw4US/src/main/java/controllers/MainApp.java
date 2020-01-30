package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.AttributePanelController;
import views.DrawAreaController;
import views.HorizontalPaletteController;
import views.VerticalPaletteController;
import views.RootLayoutController;


public class MainApp extends Application {

    private Stage primaryStage;
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    private BorderPane rootLayout;
    private BorderPane paletteLayout;
    public BorderPane getPaletteLayout() {
		return paletteLayout;
	}
    
    
    private RootLayoutController rlController;
    public RootLayoutController getRlController() {
		return rlController;
	}
    
    private VerticalPaletteController vpController;
    public VerticalPaletteController getVpController() {
		return vpController;
	}

	private DrawAreaController daController;

    private HorizontalPaletteController hpController;
    public HorizontalPaletteController getHpController() {
		return hpController;
	}


	private VerticalPaletteController vbController;
    public VerticalPaletteController getVbController() {
		return vbController;
	}

    
	//start
	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("xDraw4US");
        this.primaryStage.setMaximized(false);
        this.primaryStage.setHeight(600);
        this.primaryStage.setWidth(800);

        initRootLayout();
        showPalettes();
        
        initKeyEvent();
    }
	
	//main
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../views/RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();
            this.rlController = loader.getController();
            rlController.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the palettes and the draw area inside the root layout.
     */
    public void showPalettes() {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(MainApp.class.getResource("../views/PaletteLayout.fxml"));
            BorderPane paletteLayout = (BorderPane) loader1.load();
            this.paletteLayout = paletteLayout;
            
            //horizontal palette
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(MainApp.class.getResource("../views/HorizontalPalette.fxml"));
            AnchorPane horizontalPalette = (AnchorPane) loader2.load();
            this.hpController = loader2.getController();
            hpController.setMainApp(this);
            
            //vertical palette
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(MainApp.class.getResource("../views/VerticalPalette.fxml"));
            AnchorPane verticalPalette = (AnchorPane) loader3.load();
            this.vpController = loader3.getController();
            vpController.setMainApp(this);
            
            //draw area
            FXMLLoader loader4 = new FXMLLoader();
            loader4.setLocation(MainApp.class.getResource("../views/DrawArea.fxml"));
            AnchorPane drawArea = (AnchorPane) loader4.load();
            rlController.setDwController(loader4.getController());
            daController = loader4.getController();
            daController.setMainApp(this);
            this.daController = loader4.getController();
            this.daController.setMainApp(this);
            this.daController.setHpController(this.hpController);
            this.daController.setVpController(this.vpController);
            
            
            rootLayout.setCenter(paletteLayout);
            this.paletteLayout.setTop(horizontalPalette);
            this.paletteLayout.setLeft(verticalPalette);
            this.paletteLayout.setCenter(drawArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showShapeEditPanel(Shape shape) {
    	
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../views/AttributePanel.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            this.paletteLayout.setRight(page);


            // Set the person into the controller.
            AttributePanelController controller = loader.getController();
            
            controller.setMainApp(this);
            controller.editShape(shape);


            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
	}
    
    /**
     * initialize key event
     */
    private void initKeyEvent() {
    	rootLayout.setOnKeyPressed(e -> {
    		if (e.getCode() == KeyCode.Z) {
    			keyPressedEvent("Z");
    		}
    		if (e.getCode() == KeyCode.Q) {
    			keyPressedEvent("Q");
    		}
    		if (e.getCode() == KeyCode.S) {
    			keyPressedEvent("S");
    		}
    		if (e.getCode() == KeyCode.D) {
    			keyPressedEvent("D");
    		}
    		if (e.getCode() == KeyCode.A) {
    			keyPressedEvent("A");
    		}
    		if (e.getCode() == KeyCode.E) {
    			keyPressedEvent("E");
    		}
    		if (e.getCode() == KeyCode.R) {
    			keyPressedEvent("R");
    		}
    		if (e.getCode() == KeyCode.M) {
    			keyPressedEvent("M");
    		}
    		if (e.getCode() == KeyCode.L) {
    			keyPressedEvent("L");
    		}
    	});
    }
    
    /**
     * activate the key action on the current sheet
     */
    public void keyPressedEvent(String key) {
		int activeTableIndex = this.daController.getTable().getSelectionModel().getSelectedIndex();
		this.daController.getDrawingSheetControllerList().get(activeTableIndex).keyPressedEvent(key);
	}
    
    
    public DrawAreaController getDaController() {
		return daController;
	}
    
    
	
	
    


}