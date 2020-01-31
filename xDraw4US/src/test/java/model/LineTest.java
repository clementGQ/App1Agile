package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import controllers.DShapeFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


class LineTest {

	private static DLine line = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DShapeFactory dsf = new DShapeFactory();
		line = (DLine) dsf.getShape("line", 50, 50, 100, 100, "border", "images/.jpg", 2, Color.BLACK);
	}

	@Test
	void shapeToStringTest() {
		String str = line.shapeToString();
		assertEquals(str.compareTo("line&50.0&50.0&100.0&100.0&0.0&0.0&1.0&1.0&0.0&2.0&0.0&0.0&0.0"), 0);
		
		DLine lineWithString = new DLine(str);
		assertNotNull(lineWithString, "line must be created !");
	}
	
	@Test
	void zoomTest() {
		double startXBefore = ((Line) line.getShape()).getStartX();
		double startYBefore = ((Line) line.getShape()).getStartY();
		double endXBefore = ((Line) line.getShape()).getEndX();
		double endYBefore = ((Line) line.getShape()).getEndY();
		double strokeWidthBefore = ((Line) line.getShape()).getStrokeWidth();
		double translateXBefore = ((Line) line.getShape()).getTranslateX();
		double translateYBefore = ((Line) line.getShape()).getTranslateY();
		
		int mult = 2;
		double normX = Math.abs(startXBefore-endXBefore);
		double normY = Math.abs(startYBefore-endYBefore);

		line.zoom(mult);
		
		assertEquals( ((Line) line.getShape()).getStartX(), startXBefore*mult);
		assertEquals( ((Line) line.getShape()).getStartY(), startYBefore*mult);
		assertEquals( ((Line) line.getShape()).getEndX(), normX*mult+endXBefore);
		assertEquals( ((Line) line.getShape()).getEndY(), normY*mult+endYBefore);
		assertEquals( ((Line) line.getShape()).getStrokeWidth(), strokeWidthBefore*mult);
		assertEquals( ((Line) line.getShape()).getTranslateX(), translateXBefore*mult);
		assertEquals( ((Line) line.getShape()).getTranslateY(), translateYBefore*mult);
	}

}
