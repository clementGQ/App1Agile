package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import controllers.DShapeFactory;
import controllers.MainApp;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import views.VerticalPaletteController;

public class DrawingSheet extends Pane {
	
	private final double PREF_SIZE_WIDTH = 683;
	private final double PREF_SIZE_HEIGHT = 455;

	private ArrayList<DShape> shapesList = new ArrayList<>();
	
	private Shape shapeSelected = null;
	private int nbChildrenMax;
	
	private int zoomInApplied;
	
	/**
	 * Constructor
	 */
	public DrawingSheet() {
		super();
		this.setStyle("-fx-background-color: white;");
		this.setPrefSize(this.PREF_SIZE_WIDTH, this.PREF_SIZE_HEIGHT);
		this.nbChildrenMax = 1;
		this.zoomInApplied = 0;
	}
	
	/**
	 * Apply Zoom
	 * @param mult : factor multiplication
	 * @param maximize : boolean = true for the fullScreen
	 */
	public void zoom(double mult, boolean maximize) {
		if(maximize || !(mult<1 && this.zoomInApplied==0)) {
			this.setPrefSize(this.getWidth()*mult,this.getHeight()*mult);
			for(DShape shape: this.shapesList) {
				shape.zoom(mult);
			}
			if(!maximize) {
				this.zoomInApplied= (mult > 1d) ? this.zoomInApplied+1:this.zoomInApplied-1;
			}
		}
	}
	
	
	/**
	 * Save shapes
	 */
	public void saveShapes(String fileName) {
		
		System.out.println("save ");
		
		
		String filePath = "./src/main/java/saves/" + fileName + ".xml";

		while(this.zoomInApplied !=0) {
			this.zoom(0.5,false);
		}
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(this.shapesList.size());
			for(DShape shape: this.shapesList) {
				String shapeString = shape.shapeToString();
				encoder.writeObject(shapeString);
			}
			encoder.close();
	        fos.close();	
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	/**
	 * Load Shapes
	 */
	public void loadShapes(File file, ColorPicker cp, MainApp mainApp, VerticalPaletteController vpController) {
		try {
            FileInputStream fis = new FileInputStream(file);
			XMLDecoder decoder = new XMLDecoder(fis);       
            int size = (Integer) decoder.readObject();
            for(int i=0; i<size; i++) {
            	String str = decoder.readObject().toString();
  
            	DShapeFactory dsFactory = new DShapeFactory();
            	DShape newShape = dsFactory.getShape(str.split("&")[0], str);
            	
            	this.addListener(newShape, cp, mainApp, vpController);
            	
            	this.getShapesList().add(newShape);
     			this.getChildren().add(newShape.getShape());
         		this.setNbChildrenMax(this.getNbChildrenMax()+1);            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Add a Listener onMouseClicked on a shape
	 * @param dShape
	 * @param cp
	 * @param mainApp
	 * @param vpController
	 */
	public void addListener(DShape dShape, ColorPicker cp, MainApp mainApp, VerticalPaletteController vpController) { 
		dShape.getShape().setOnMouseClicked((ts) -> {
			if(vpController.getSelectedTools() == "selection") {
				this.resetShapeSelected();
				this.setShapeSelected(dShape.getShape());
				this.getShapeSelected().setOpacity(0.5);
				cp.setValue((Color)this.getShapeSelected().getStroke());
				mainApp.showShapeEditPanel(dShape.getShape());
			}
		});
	}
	
	/**
     * delete the content of the sheet
     */
	public void deleteAll() {
		this.setNbChildrenMax(1);
		this.getChildren().clear();
		this.getShapesList().clear();
		this.setShapeSelected(null);
	}

	/**
	 * Reset the shape selected
	 */
	public void resetShapeSelected() {
		if(shapeSelected != null) {
			shapeSelected.setOpacity(1);
			shapeSelected = null;
		}
	}

	//getters setters
	public void setShapeSelected(Shape shapeSelected) {
		this.shapeSelected = shapeSelected;
	}
	
	public void setNbChildrenMax(int nb) {
		this.nbChildrenMax = nb;
	}

	public int getNbChildrenMax() {
		return nbChildrenMax;
	}
	
	public Shape getShapeSelected() {
		return shapeSelected;
	}
	
	public ArrayList<DShape> getShapesList() {
		return shapesList;
	}

}
