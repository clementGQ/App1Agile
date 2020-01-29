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

    @Test public void newDrawTest() {
        // given: app started with one drawing
    	assertEquals(1,ma.getDaController().getDrawingSheetControllerList().size(), "There must be only one drawing at start.");
    	
    	// do: new draw   	
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	
    	//expect: shape is drawn
    	assertEquals(2,ma.getDaController().getDrawingSheetControllerList().size(), "There must be two drawings now.");
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
