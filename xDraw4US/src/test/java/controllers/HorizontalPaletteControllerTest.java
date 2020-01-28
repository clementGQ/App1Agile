package controllers;


import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class HorizontalPaletteControllerTest extends ApplicationTest {
//	MainApp ma;
//	
//    @Override public void start(Stage stage) {
//        ma = new MainApp();
//        ma.start(stage);
//    }
//
//    @Test public void maximizeAndMinimizeTest() {
//        // given: app started in reduced mode
//    	assertFalse(ma.getPrimaryStage().isMaximized(), "Window must be minimized first.");
//    	
//    	// do: maximize window
//    	clickOn("#minOrMaxButton");
//    	
//    	//expect: window is maximized and button text is "800x600"
//    	verifyThat("#minOrMaxButton", hasText("800x600"));
//    	assertTrue(ma.getPrimaryStage().isMaximized(), "Window must be maximized.");
//
//    	// do: minimize window
//    	clickOn("#minOrMaxButton");
//    	
//    	//expect: window is minimized and button text is "Maximize"
//    	verifyThat("#minOrMaxButton", hasText("Maximize"));
//    	//assertFalse(ma.getPrimaryStage().isMaximized(), "Window must be minimized.");
//    }
//    
//    @Test public void selectStrokeSizeTest() {
//        // given: app started with stroke of "1 px"
//    	assertEquals("1 px", ma.getHpController().getStrokeSize().getValue(), "Selected stroke size must be '1 px' at start.");
//    	
//    	// do: select the below menu option
//    	clickOn("#strokeSizeSelection");
//    	type(KeyCode.DOWN);
//    	type(KeyCode.ENTER);
//    	
//    	//expect: "2 px" should be now selected
//    	assertEquals("2 px", ma.getHpController().getStrokeSize().getValue(), "Selected stroke size must be '2 px' now.");    
//    }
//    
//    @Test public void selectPatternTest() {
//        // given: app started with pattern "cube"
//    	assertEquals("cube", ma.getHpController().getFillingPattern().getValue(), "Selected pattern must be 'cube' at start.");
//    	
//    	// do: select the below menu option
//    	clickOn("#fillingPatternSelection");
//    	type(KeyCode.DOWN);
//    	type(KeyCode.ENTER);
//    	
//    	//expect: "line" should be now selected
//    	assertEquals("line", ma.getHpController().getFillingPattern().getValue(), "Selected pattern must be 'line' now.");
//    }   
//    
//    @Test public void selectStrokeColorTest() {
//        // given: app started with black stroke color
//    	assertEquals(Color.BLACK, ma.getHpController().getColorStrokePicker().getValue(), "Selected stroke color must be black at start.");
//    	
//    	// do: select the above menu option
//    	clickOn("#colorStrokePicker");
//    	type(KeyCode.UP);
//    	type(KeyCode.ENTER);
//    	
//    	//expect: lime stroke color should be now selected
//    	assertEquals(Color.LIME, ma.getHpController().getColorStrokePicker().getValue(), "Selected stroke color must be lime at start.");
//    }   
//    
//    
//    @Test public void eraseAllShapes() {
//    	// given: app starts with 5 shapes
//    	clickOn("#rectangleButton");
//    	moveTo(ma.getPrimaryStage());
//    	for(int i = 0; i < 5; i++) {
//    		// -> drag and drop
//        	press(MouseButton.PRIMARY);
//        	moveBy(20+10*i,20+10*i);
//        	release(MouseButton.PRIMARY);
//    	}
//    	
//    	// do: click on erase button
//    	clickOn("#deleteButton");
//    	
//    	assertEquals(0, ma.getDaController().getDrawingSheetList().get(0).getShapesList().size(), "List of shapes must contain zero shape");
//    }
//    
    
    
}