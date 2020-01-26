package views;

import controllers.MainApp;

public abstract class AController {
	protected MainApp mainApp;
	
	
	public void setMainApp(MainApp ma) {
		mainApp = ma;
	}
}
