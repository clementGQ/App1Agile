package controllers;


import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.stage.Stage;
import main.MainApp;


public class HorizontalBarControllerTest extends ApplicationTest {
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

}