package views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.robot.BaseRobot;

import controllers.MainApp;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

class VerticalPaletteControllerTest extends ApplicationTest {
	MainApp ma;
	BaseRobot r;
	
    @Override public void start(Stage stage) {
        ma = new MainApp();
        ma.start(stage);
    }

    /**
     * Test the shape drawing.
     */
    @Test public void selectShapesTest() {
        // given: app started with no shapes drawn
    	assertEquals("selection", ma.getVpController().getSelectedTools(), "Selected tool must be selection at start.");

    	// select line
    	clickOn("#lineButton");
    	//expect: rectangle tool selected
    	assertEquals("line", ma.getVpController().getSelectedTools(), "Selected tool must be line.");
    	
    	// select rectangle
    	clickOn("#rectangleButton");
    	//expect: rectangle tool selected
    	assertEquals("rectangle", ma.getVpController().getSelectedTools(), "Selected tool must be rectangle.");
    	
    	// select circle
    	clickOn("#circleButton");
    	//expect: rectangle tool selected
    	assertEquals("circle", ma.getVpController().getSelectedTools(), "Selected tool must be circle.");

    }

}
