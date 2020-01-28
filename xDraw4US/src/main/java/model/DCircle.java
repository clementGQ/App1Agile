package model;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DCircle extends DShape{
	
	double radius;
	ImagePattern image;
	
	public DCircle(double posX, double posY, double radius, ImagePattern image) {
		super(posX, posY);
		this.radius = radius;
		this.image = image;
	}

	@Override
	public Shape getShape() {
		Circle nCircle = new Circle(posX, posY, radius);
		nCircle.setFill(image);
		// TODO setTranslation, rotation, etc....
		return nCircle;
	}

}
