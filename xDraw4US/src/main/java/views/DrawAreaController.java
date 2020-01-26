package views;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.DrawingSheet;

public class DrawAreaController extends AController{
	
	private int sheetNumber;
	private ArrayList<DrawingSheet> drawingSheetList = new ArrayList<DrawingSheet>();
	
	@FXML
	private TabPane table;
	
	public DrawAreaController() {
		this.sheetNumber = 1;
		createDrawingSheet();
	}
	
	public void createDrawingSheet() {}
	
	@FXML
    private void initialize() {
		AnchorPane anchorPane = new AnchorPane();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(anchorPane);
		table.getTabs().get(0).setText("feuille " + Integer.toString(this.sheetNumber));
		table.getTabs().get(0).setContent(scrollPane);
		drawingSheetList.add(new DrawingSheet());
		anchorPane.getChildren().add(drawingSheetList.get(0));
		sheetNumber++;
    }

	
	public void newDrawingSheet() {
		AnchorPane anchorPane = new AnchorPane();
		ScrollPane scrollPane = new ScrollPane();
		DrawingSheet sheet = new DrawingSheet();
		scrollPane.setContent(anchorPane);
		Tab tab = new Tab("feuille " + Integer.toString(this.sheetNumber), scrollPane);
		table.getTabs().add(tab);
		drawingSheetList.add(sheet);
		anchorPane.getChildren().add(sheet);
		sheetNumber++;
		table.getSelectionModel().select(tab);
	}
	
	public void closeDrawingSheet() {
		table.getTabs().remove( table.getSelectionModel().getSelectedItem());
	}
	
	public ArrayList<DrawingSheet> getDrawingSheetList() {
		return drawingSheetList;
	}

	public void setDrawingSheetList(ArrayList<DrawingSheet> drawingSheetList) {
		this.drawingSheetList = drawingSheetList;
	}
	
	public void setHpController(HorizontalPaletteController hpController) {
		drawingSheetList.get(0).setHpController(hpController);
		drawingSheetList.get(0).setColorPickerListener();
		drawingSheetList.get(0).setFillingPatternListener();
		drawingSheetList.get(0).setStrokeSizeListener();
		drawingSheetList.get(0).setDeleteButtonListener();
	}
	
	public void setVpController(VerticalPaletteController vpController) {
		drawingSheetList.get(0).setVpController(vpController);
	}
	
	public void keyPressedEvent(String key) {
		drawingSheetList.get(0).keyPressedEvent(key);
	}
}


