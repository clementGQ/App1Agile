package view;

import java.util.ArrayList;

import controller.MainApp;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.DrawingSheet;

public class DrawAreaController {
	
	private ArrayList<DrawingSheet> drawingSheetList = new ArrayList<DrawingSheet>();

	private MainApp mainApp;
	
	@FXML
	private TabPane table;
	
	public DrawAreaController() {
		drawingSheetList.add(new DrawingSheet());
	}
	
	@FXML
    private void initialize() {
		table.getTabs().get(0).setText("feuille 1");
		table.getTabs().get(0).setContent(drawingSheetList.get(0));
		
		

    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}
	
	
	
	public ArrayList<DrawingSheet> getDrawingSheetList() {
		return drawingSheetList;
	}

	public void setDrawingSheetList(ArrayList<DrawingSheet> drawingSheetList) {
		this.drawingSheetList = drawingSheetList;
	}
}


