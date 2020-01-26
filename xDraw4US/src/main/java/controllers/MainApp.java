package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.DrawAreaController;
import views.HorizontalPaletteController;
import views.VerticalPaletteController;
import views.RootLayoutController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private RootLayoutController rlController;
    public RootLayoutController getRlController() {
		return rlController;
	}
    
    private VerticalPaletteController vpController;
    private DrawAreaController dwController;

    private HorizontalPaletteController hpController;
    public HorizontalPaletteController getHpController() {
		return hpController;
	}


	private VerticalPaletteController vbController;
    public VerticalPaletteController getVbController() {
		return vbController;
	}


	private DrawAreaController daController;
    public DrawAreaController getDaController() {
		return daController;
	}

	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("xDraw4US");

        initRootLayout();
        showPalettes();
        
        
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
     * Shows the person overview inside the root layout.
     */
    public void showPalettes() {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(MainApp.class.getResource("../views/PaletteLayout.fxml"));
            BorderPane paletteLayout = (BorderPane) loader1.load();
            
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(MainApp.class.getResource("../views/HorizontalPalette.fxml"));
            AnchorPane horizontalPalette = (AnchorPane) loader2.load();
            this.hpController = loader2.getController();
            hpController.setMainApp(this);
            
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(MainApp.class.getResource("../views/VerticalPalette.fxml"));
            AnchorPane verticalPalette = (AnchorPane) loader3.load();
            this.vpController = loader3.getController();
            vpController.setMainApp(this);
            

            FXMLLoader loader4 = new FXMLLoader();
            loader4.setLocation(MainApp.class.getResource("../views/DrawArea.fxml"));
            AnchorPane drawArea = (AnchorPane) loader4.load();
            rlController.setDwController(loader4.getController());
            daController = loader4.getController();
            daController.setMainApp(this);
            this.dwController = loader4.getController();
            this.dwController.setMainApp(this);
            this.dwController.setHpController(this.hpController);
            this.dwController.setVpController(this.vpController);
            
            
            
            rootLayout.setCenter(paletteLayout);
            paletteLayout.setTop(horizontalPalette);
            paletteLayout.setLeft(verticalPalette);
            paletteLayout.setCenter(drawArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public HorizontalPaletteController gethpController() {
    	return this.hpController;
    }
    

    public static void main(String[] args) {
        launch(args);
    }


}