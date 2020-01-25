package main;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;


public class AppTest extends ApplicationTest {
	MainApp ma;
	
    @Override public void start(Stage stage) {
        ma = new MainApp();
        ma.start(stage);
    }

    @Test public void appStartedTest() {
        // given: app started
    	// do: nothing
    	//expect: check app is shown
    	assertTrue(ma.getPrimaryStage().isShowing());
    }

}