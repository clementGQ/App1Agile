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
    @Test public void drawingShapeTest() {
    	// given: drawing sheet started with 0 shapes   	
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	
    	//do: create a new rectangle shape
    	clickOn("#rectangleButton");
    	moveTo(ma.getPrimaryStage());
    	press(MouseButton.PRIMARY);
    	moveBy(40,30);
    	release(MouseButton.PRIMARY);
    	
    	//expect: a new shape have been added to shapeList
    	assertEquals(1,ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapesList().size(), "There must be 1 shape in shapesList");
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
    
    /*
     * Test the changing starting point functionnality
     */
    @Test public void changeStartPointDrawTest() {
    	// given: starting point is set on center   	
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	assertEquals("center",ma.getVpController().getStartingPoint(), "Selected starting point must be \"center\" ");
    	
    	//do: change starting point to corner
    	clickOn("#changeStartButton");
    	
    	//expect: starting point must be set on "corner"
    	assertEquals("corner",ma.getVpController().getStartingPoint(), "Selected starting point must be \"corner\" ");
    	
    	//do: change starting point to center
    	clickOn("#changeStartButton");
    	
    	//expect: starting point must be set on "corner"
    	assertEquals("center",ma.getVpController().getStartingPoint(), "Selected starting point must be \"center\" ");
    }

}
