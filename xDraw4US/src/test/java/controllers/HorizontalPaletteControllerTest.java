package controllers;


import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class HorizontalPaletteControllerTest extends ApplicationTest {
	MainApp ma;
	
    @Override public void start(Stage stage) {
        ma = new MainApp();
        ma.start(stage);
    }

    @Test public void maximizeAndMinimizeTest() {
        // given: app started in reduced mode
    	assertFalse(ma.getPrimaryStage().isMaximized(), "Window must be minimized first.");
    	
    	// do: maximize window
    	clickOn("#minOrMaxButton");
    	
    	//expect: window is maximized and button text is "800x600"
    	verifyThat("#minOrMaxButton", hasText("800x600"));
    	assertTrue(ma.getPrimaryStage().isMaximized(), "Window must be maximized.");

    	// do: minimize window
    	clickOn("#minOrMaxButton");
    	
    	//expect: window is minimized and button text is "Maximize"
    	verifyThat("#minOrMaxButton", hasText("Maximize"));
    	//assertFalse(ma.getPrimaryStage().isMaximized(), "Window must be minimized.");
    }
    
    @Test public void selectStrokeSizeTest() {
        // given: app started with stroke of "1 px"
    	assertEquals("1 px", ma.getHpController().getStrokeSize().getValue(), "Selected stroke size must be '1 px' at start.");
    	
    	// do: select the below menu option
    	clickOn("#strokeSizeSelection");
    	type(KeyCode.DOWN);
    	type(KeyCode.ENTER);
    	
    	//expect: "2 px" should be now selected
    	assertEquals("2 px", ma.getHpController().getStrokeSize().getValue(), "Selected stroke size must be '2 px' now.");    
    }

}