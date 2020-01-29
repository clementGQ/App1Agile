package model;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class DrawingSheet extends Pane {

	private ArrayList<DShape> shapesList = new ArrayList<>();
	
	private Shape shapeSelected = null;
	private int nbChildrenMax;
	
	//builder
	public DrawingSheet() {
		super();
		this.setStyle("-fx-background-color: white;");
		this.setPrefSize(683,455);
		this.nbChildrenMax = 1;
	}

	public void zoom(double mult) {
		this.setPrefSize(this.getWidth()*mult,this.getHeight()*mult);
		for(DShape shape: this.shapesList) {
			shape.zoom(mult);
		}
	}
	
	public void saveShapes() {
		//Tests
		String shapeString = shapesList.get(0).shapeToString();
		System.out.println(shapeString);
		
		String token = shapeString.split("&")[0];
		System.out.println(token);
		if(token == "line") {
			
		}
		
		
		

		/*
		try {
            FileOutputStream fos = new FileOutputStream(new File("../shapeSave.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            for(DShape shape: this.shapesList) {
            	shape.saveShape();
            	System.out.println(shapesList.get(0).getShape().toString());
            	encoder.writeObject(shapesList.get(0).getShape());
            }
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
	}
	
	public void loadShapes() {
		try {
            FileInputStream fis = new FileInputStream(new File("../shapeSave.xml"));
            XMLDecoder decoder = new XMLDecoder(fis);
            Object obj = decoder.readObject();
            System.out.println(obj.toString());
            shapesList.add((DShape)obj);
            this.getChildren().add((Shape)obj);
            decoder.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ArrayList<DShape> getShapesList() {
		return shapesList;
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
	
	
	//getters setters
	public void setNbChildrenMax(int nb) {
		this.nbChildrenMax = nb;
	}

	public int getNbChildrenMax() {
		return nbChildrenMax;
	}
	
	public Shape getShapeSelected() {
		return shapeSelected;
	}
	public void setShapeSelected(Shape shapeSelected) {
		this.shapeSelected = shapeSelected;
	}
	public void resetShapeSelected() {
		if(shapeSelected != null) {
			shapeSelected.setOpacity(1);
			shapeSelected = null;
		}
	}

}
