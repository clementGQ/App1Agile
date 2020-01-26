package views;

import java.util.ArrayList;

import controllers.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.DrawingSheet;

public class DrawAreaController extends AController{
	
	private ArrayList<DrawingSheet> drawingSheetList = new ArrayList<DrawingSheet>();

	@FXML
	private TabPane table;
	
	public DrawAreaController() {
		createDrawingSheet();
	}
	
	public void createDrawingSheet() {
		drawingSheetList.add(new DrawingSheet());
	}
	
	@FXML
    private void initialize() {
		AnchorPane anchorPane = new AnchorPane();
		ScrollPane scrollPane = new ScrollPane();
		table.getTabs().get(0).setText("feuille 1");
		table.getTabs().get(0).setContent(scrollPane);
		scrollPane.setContent(anchorPane);
		anchorPane.getChildren().add(drawingSheetList.get(0));

    }

	
	public ArrayList<DrawingSheet> getDrawingSheetList() {
		return drawingSheetList;
	}

	public void setDrawingSheetList(ArrayList<DrawingSheet> drawingSheetList) {
		this.drawingSheetList = drawingSheetList;
	}
	
	public void setHbController(HorizontalBarController hbController) {
		drawingSheetList.get(0).setHbController(hbController);
	}
}


