package views;

import java.util.ArrayList;

import controllers.DrawingSheetController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class DrawAreaController extends AController{
	
	private int sheetNumber;
	private ArrayList<DrawingSheetController> drawingSheetControllerList = new ArrayList<DrawingSheetController>();
	private HorizontalPaletteController hpController;
	private VerticalPaletteController vpController;
	
	@FXML
	private TabPane table;
	

	@FXML
    private void initialize() {
		this.sheetNumber = 1;
	}
	
	/**
     * create a new sheet
     */
	public void newDrawingSheet() {
		if (drawingSheetControllerList.size() == 0) {
			AnchorPane anchorPane = new AnchorPane();
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(anchorPane);
			table.getTabs().get(0).setText("feuille " + Integer.toString(this.sheetNumber));
			table.getTabs().get(0).setContent(scrollPane);
			drawingSheetControllerList.add(new DrawingSheetController(hpController,vpController, mainApp));
			anchorPane.getChildren().add(drawingSheetControllerList.get(0).getDrawingSheet());
			sheetNumber++;
		}
		else {
			AnchorPane anchorPane = new AnchorPane();
			ScrollPane scrollPane = new ScrollPane();
			DrawingSheetController sheet = new DrawingSheetController(hpController,vpController, mainApp);
			scrollPane.setContent(anchorPane);
			Tab tab = new Tab("feuille " + Integer.toString(this.sheetNumber), scrollPane);
			table.getTabs().add(tab);
			drawingSheetControllerList.add(sheet);
			anchorPane.getChildren().add(sheet.getDrawingSheet());
			sheetNumber++;
			table.getSelectionModel().select(tab);
		}
	}
	
	/**
     * close the current sheet
     */
	public void closeDrawingSheet() {
		if (drawingSheetControllerList.size() == 1) {
			table.getTabs().get(0).setText("");
			table.getTabs().get(0).setContent(null);
			drawingSheetControllerList.remove(drawingSheetControllerList.get(0));
		}
		else {
			int activeTableIndex = table.getSelectionModel().getSelectedIndex(); 
			table.getTabs().remove(table.getTabs().get(activeTableIndex));
			drawingSheetControllerList.remove(drawingSheetControllerList.get(activeTableIndex));
		}
		
	}
	
	
	//getters setters	
	public ArrayList<DrawingSheetController> getDrawingSheetControllerList() {
		return drawingSheetControllerList;
	}

	public void setDrawingSheetControllerList(ArrayList<DrawingSheetController> drawingSheetControllerList) {
		this.drawingSheetControllerList = drawingSheetControllerList;
	}
	
	public void setHpController(HorizontalPaletteController hpController) {
		this.hpController=hpController;
	}
	
	public void setVpController(VerticalPaletteController vpController) {
		this.vpController=vpController;
	}
	
	public TabPane getTable() {
		return table;
	}
}


