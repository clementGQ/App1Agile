package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.DrawAreaController;
import views.HorizontalPaletteController;
import views.VerticalPaletteController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private HorizontalPaletteController hpController;
    private VerticalPaletteController vpController;
    private DrawAreaController dwController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("xDraw4US");

        initRootLayout();

        showPalettes();
        
        initKeyEvent();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
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
            // Load person overview.
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
    
    private void initKeyEvent() {
    	rootLayout.setOnKeyPressed(e -> {
    		if (e.getCode() == KeyCode.Z) {
    			this.dwController.keyPressedEvent("Z");
    		}
    		if (e.getCode() == KeyCode.Q) {
    			this.dwController.keyPressedEvent("Q");
    		}
    		if (e.getCode() == KeyCode.S) {
    			this.dwController.keyPressedEvent("S");
    		}
    		if (e.getCode() == KeyCode.D) {
    			this.dwController.keyPressedEvent("D");
    		}
    		if (e.getCode() == KeyCode.A) {
    			this.dwController.keyPressedEvent("A");
    		}
    		if (e.getCode() == KeyCode.E) {
    			this.dwController.keyPressedEvent("E");
    		}
    		if (e.getCode() == KeyCode.R) {
    			this.dwController.keyPressedEvent("R");
    		}
    	});
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