package model;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DRectangle extends DShape{

	double width, height;
	ImagePattern image;
	
	public DRectangle(double posX, double posY, double width, double height, ImagePattern image) {
		super(posX, posY);
		this.width = width;
		this.height = height;
		this.image = image;
	}

	@Override
	public Shape getShape() {
		Rectangle nRectangle = new Rectangle(posX, posY, width, height);
		nRectangle.setFill(image);
		// TODO setTranslation, rotation, etc....
		return nRectangle;
	}
	
}
