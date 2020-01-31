package controllers;

import javafx.scene.paint.Color;
import model.DCircle;
import model.DLine;
import model.DRectangle;
import model.DShape;

public class DShapeFactory {
	
	   public DShape getShape(String shapeType, double x, double y, double cursorX, double cursorY, 
			  	String startingPoint, String imagePath, double strokeWidth, Color strokeColor) {
		   
	      if(shapeType == null){
	         return null;
	      }		
	      
	      if(shapeType.equalsIgnoreCase("circle")){
	         return new DCircle(x, y, cursorX, cursorY, startingPoint, imagePath, strokeWidth, strokeColor);
	         
	      } else if(shapeType.equalsIgnoreCase("rectangle")){
	    	  return new DRectangle(x, y, cursorX, cursorY, startingPoint, imagePath, strokeWidth, strokeColor);
	         
	      } else if(shapeType.equalsIgnoreCase("line")){
	    	  return new DLine(x, y, cursorX, cursorY, startingPoint, imagePath, strokeWidth, strokeColor);
	      }
	      
	      return null;
	   }
	   
	   public DShape getShape(String shapeType, String stringShape) {
		   
	      if(shapeType == null){
	         return null;
	      }		
	      
	      if(shapeType.equalsIgnoreCase("circle")){
	         return new DCircle(stringShape);
	         
	      } else if(shapeType.equalsIgnoreCase("rectangle")){
	    	  return new DRectangle(stringShape);
	         
	      } else if(shapeType.equalsIgnoreCase("line")){
	    	  return new DLine(stringShape);
	      }
	      
	      return null;
	   }
	}