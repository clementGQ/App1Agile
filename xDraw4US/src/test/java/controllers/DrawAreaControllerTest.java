package controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.robot.BaseRobot;
import controllers.MainApp;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


class DrawAreaControllerTest extends ApplicationTest {
	MainApp ma;
	BaseRobot r;
	
    @Override public void start(Stage stage) {
        ma = new MainApp();
        ma.start(stage);
    }

    
    @Test public void drawShapeTest() {
        // given: app started with no shapes drawn
    	assertTrue(ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapesList().isEmpty(), "List of shapes must be empty");
    	// do: draw a shape    	
    		// select rectangle
    	clickOn("#rectangleButton");
    		// -> drag and drop
    	moveTo(ma.getPrimaryStage());
    	press(MouseButton.PRIMARY);
    	moveBy(20,20);
    	release(MouseButton.PRIMARY);
    	
    	//expect: shape is drawn
    	assertEquals(1, ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapesList().size(), "List of shapes must contain one shape");
    }
//
//        
}