package views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import controllers.MainApp;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

class VerticalPaletteControllerTest extends ApplicationTest {
	MainApp ma;
	
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
    
    @Test public void changeShapeTranslationTest() {
    	// given: shape drawn in the center of the drawing sheet
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	clickOn("#circleButton");
    	moveTo(ma.getPrimaryStage());
    	press(MouseButton.PRIMARY);
    	moveBy(40,30);
    	release(MouseButton.PRIMARY);
    	
    	//do: change position of shape
    	clickOn("#selectButton");
    	moveTo(ma.getPrimaryStage());
    	clickOn(MouseButton.PRIMARY);

    	double shapeX = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getTranslateX();
    	double shapeY = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getTranslateY();
    	
    	clickOn("#translationXField");
    	write("9");
    	type(KeyCode.ENTER);
    	
    	clickOn("#translationYField");
    	write("8");
    	type(KeyCode.ENTER);
    	
    	//expect: position of the shape is different
    	double shapeXnew = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getTranslateX();
    	double shapeYnew = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getTranslateY();
    	
    	assertNotEquals(shapeXnew,shapeX, "X translation of the shape must be changed.");
    	assertNotEquals(shapeYnew,shapeY, "Y translation of the shape must be changed.");

    }
    
    @Test public void changeShapeRotationTest() {
    	// given: shape drawn in the center of the drawing sheet
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	clickOn("#circleButton");
    	moveTo(ma.getPrimaryStage());
    	press(MouseButton.PRIMARY);
    	moveBy(40,30);
    	release(MouseButton.PRIMARY);
    	
    	//do: change rotation of shape
    	clickOn("#selectButton");
    	moveTo(ma.getPrimaryStage());
    	clickOn(MouseButton.PRIMARY);

    	double rotation = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getRotate();
    	
    	clickOn("#rotationField");
    	write("9");
    	type(KeyCode.ENTER);
    	
    	//expect: rotation of the shape is different
    	double rotationNew = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getRotate();

    	assertNotEquals(rotationNew,rotation, "Rotation of the shape must be changed.");
    }
    
    @Test public void changeShapeScaleTest() {
    	// given: shape drawn in the center of the drawing sheet
    	clickOn("#file");
    	clickOn("#newDrawButton");
    	clickOn("#circleButton");
    	moveTo(ma.getPrimaryStage());
    	press(MouseButton.PRIMARY);
    	moveBy(40,30);
    	release(MouseButton.PRIMARY);
    	
    	//do: change scale of shape
    	clickOn("#selectButton");
    	moveTo(ma.getPrimaryStage());
    	clickOn(MouseButton.PRIMARY);

    	double scale = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getScaleX();
    	
    	clickOn("#scaleField");
    	write("2");
    	type(KeyCode.ENTER);
    	
    	//expect: scale of the shape is different
    	double scaleNew = ma.getDaController().getDrawingSheetControllerList().get(0).getDrawingSheet().getShapeSelected().getScaleX();

    	assertNotEquals(scaleNew,scale, "Rotation of the shape must be changed.");
    }

}
