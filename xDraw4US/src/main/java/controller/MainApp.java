package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.DrawAreaController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private DrawAreaController drawAreaController;
    private DrawingSheetController drawingSheetController;
    
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("xDraw4US");

        initRootLayout();

        showPalettes();
        /*
        drawAreaController = new DrawAreaController();
        drawingSheetController = new DrawingSheetController(drawAreaController.getDrawingSheetList().get(0));
        drawingSheetController.draw();*/
        
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/PaletteLayout.fxml"));
            BorderPane paletteLayout = (BorderPane) loader.load();
            
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(MainApp.class.getResource("../view/HorizontalPalette.fxml"));
            AnchorPane horizontalPalette = (AnchorPane) loader1.load();
            
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(MainApp.class.getResource("../view/VerticalPalette.fxml"));
            AnchorPane verticalPalette = (AnchorPane) loader2.load();
            
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(MainApp.class.getResource("../view/DrawArea.fxml"));
            AnchorPane drawArea = (AnchorPane) loader3.load();
            
            rootLayout.setCenter(paletteLayout);
            paletteLayout.setTop(horizontalPalette);
            paletteLayout.setLeft(verticalPalette);
            paletteLayout.setCenter(drawArea);
            
            //DrawAreaController controller = loader.getController();
            //controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


