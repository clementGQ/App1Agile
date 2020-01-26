package controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import model.DrawingSheet;

public class DrawAreaController extends AController {
	
	private ArrayList<DrawingSheet> drawingSheetList = new ArrayList<DrawingSheet>();
	
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
	
	
	public ArrayList<DrawingSheet> getDrawingSheetList() {
		return drawingSheetList;
	}

	public void setDrawingSheetList(ArrayList<DrawingSheet> drawingSheetList) {
		this.drawingSheetList = drawingSheetList;
	}
}


