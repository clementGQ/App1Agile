package views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.robot.BaseRobot;

import controllers.MainApp;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


class RootLayoutControllerTest extends ApplicationTest {
	MainApp ma;
	BaseRobot r;
	
    @Override public void start(Stage stage) {
        ma = new MainApp();
        ma.start(stage);
    }

    
    /**
     * Test that new draw button creates a new canvas
     */
    /*
    @Test public void newTest() {
        // given: app started with one drawing
    	assertEquals(1,ma.getDaController().getDrawingSheetControllerList().size(), "There must be only one drawing at start.");
    	
    	// do: new draw   	
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	
    	//expect: shape is drawn
    	assertEquals(2,ma.getDaController().getDrawingSheetControllerList().size(), "There must be two drawings now.");
    }
    */
    
    /**
     * Test that new draw button creates a new canvas
     */
    @Test public void newDrawTest() {
    	// given: app started with 0 drawing
    	assertEquals(true,ma.getDaController().getDrawingSheetControllerList().isEmpty(), "There must be 0 drawing sheet at start.");
    	
    	// do: new draw   	
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	
    	//expect: a new canvas is set
    	assertEquals(1,ma.getDaController().getDrawingSheetControllerList().size(), "There must be 1 drawing sheet");
    	
    }

    @Test public void eraseAllShapesTest() {
    	// given: app starts with some shapes
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	clickOn("#rectangleButton");
    	moveTo(ma.getPrimaryStage());
    	for(int i = 0; i < 3; i++) {
    		// -> drag and drop
        	press(MouseButton.PRIMARY);
        	moveBy(20+10*i,20+10*i);
        	release(MouseButton.PRIMARY);
    	}
    	
    	assertNotEquals(0, ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapesList().size(), "List of shapes must contain some shapes.");

    	// do: click on erase button
    	clickOn("#edit");
    	clickOn("#delete");
    	
    	assertEquals(0, ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapesList().size(), "List of shapes must contain zero shape.");
    }

}
