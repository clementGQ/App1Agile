package model;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class DLine extends DShape{
	
	double posX2, posY2;

	public DLine(double posX, double posY, double posX2, double posY2) {
		super(posX, posY);
		this.posX2 = posX2;
		this.posY2 = posY2;
	}

	@Override
	public Shape getShape() {
		Line nLine = new Line(posX, posY, posX2, posY2);
		return nLine;
	}

}
