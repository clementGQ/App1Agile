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
	private HorizontalPaletteController hpController;
	private VerticalPaletteController vpController;
	
	@FXML
	private TabPane table;
	
	public DrawAreaController() {
		this.sheetNumber = 1;
	}
	
	@FXML
    private void initialize() {
	}
	
	public void newDrawingSheet() {
		if (drawingSheetList.size() == 0) {
			AnchorPane anchorPane = new AnchorPane();
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(anchorPane);
			table.getTabs().get(0).setText("feuille " + Integer.toString(this.sheetNumber));
			table.getTabs().get(0).setContent(scrollPane);
			drawingSheetList.add(new DrawingSheet(hpController,vpController));
			anchorPane.getChildren().add(drawingSheetList.get(0));
			sheetNumber++;
		}
		else {
			AnchorPane anchorPane = new AnchorPane();
			ScrollPane scrollPane = new ScrollPane();
			DrawingSheet sheet = new DrawingSheet(hpController,vpController);
			scrollPane.setContent(anchorPane);
			Tab tab = new Tab("feuille " + Integer.toString(this.sheetNumber), scrollPane);
			table.getTabs().add(tab);
			drawingSheetList.add(sheet);
			anchorPane.getChildren().add(sheet);
			sheetNumber++;
			table.getSelectionModel().select(tab);
		}
	}
	
	public void closeDrawingSheet() {
		if (drawingSheetList.size() == 1) {
			table.getTabs().get(0).setText("");
			table.getTabs().get(0).setContent(null);
			drawingSheetList.remove(drawingSheetList.get(0));
		}
		else {
			//indice de la table active
			int i = table.getSelectionModel().getSelectedIndex();
			table.getTabs().remove(table.getTabs().get(i));
			drawingSheetList.remove(drawingSheetList.get(i));
		}
		
	}
	
	public void setDeleteButtonListener() {
		this.hpController.getDeleteButton().setOnAction((t) -> {
			int i = table.getSelectionModel().getSelectedIndex();
			System.out.println("delete");
			this.drawingSheetList.get(i).setNbChildrenMax(1);
			this.drawingSheetList.get(i).getChildren().clear();
			this.drawingSheetList.get(i).getShapesList().clear();
			this.drawingSheetList.get(i).setShapeSelected( null);
		});
	}
	
	
	public ArrayList<DrawingSheet> getDrawingSheetList() {
		return drawingSheetList;
	}

	public void setDrawingSheetList(ArrayList<DrawingSheet> drawingSheetList) {
		this.drawingSheetList = drawingSheetList;
	}

	
	public void setHpController(HorizontalPaletteController hpController) {
		this.hpController=hpController;
	}
	
	public void setVpController(VerticalPaletteController vpController) {
		this.vpController=vpController;
	}
	
	public void keyPressedEvent(String key) {
		drawingSheetList.get(0).keyPressedEvent(key);
	}
}


