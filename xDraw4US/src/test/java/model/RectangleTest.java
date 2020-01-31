package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import controllers.DShapeFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


class RectangleTest {

	private static DRectangle rectangle = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DShapeFactory dsf = new DShapeFactory();
		rectangle = (DRectangle) dsf.getShape("rectangle", 50, 50, 100, 100, "border", "images/.jpg", 2, Color.BLACK);
		rectangle.getShape().setFill(Color.BLACK);
	}

	@Test
	void shapeToStringTest() {
		String str = rectangle.shapeToString();
		assertEquals(str.compareTo("rectangle&0.0&0.0&100.0&100.0&0.0&0.0&1.0&1.0&0.0&0.0&0.0&0.0&2.0&null"), 0);
		
		try {
			new DRectangle(str);
			assertFalse(true,"can't create a rectangle without filePath");
		} catch(Exception e) {
		}
	}
	
	@Test
	void zoomTest() {
		
		int mult = 2;
		double centerXBefore = ((Rectangle) rectangle.getShape()).getX();
		double centerYBefore = ((Rectangle) rectangle.getShape()).getY();
		double widthBefore = ((Rectangle) rectangle.getShape()).getWidth();
		double heightBefore = ((Rectangle) rectangle.getShape()).getHeight();
		double translateXBefore = ((Rectangle) rectangle.getShape()).getTranslateX();
		double translateYBefore = ((Rectangle) rectangle.getShape()).getTranslateY();
		
		rectangle.zoom(mult);
		
		assertEquals( ((Rectangle) rectangle.getShape()).getX(), centerXBefore*mult);
		assertEquals( ((Rectangle) rectangle.getShape()).getY(), centerYBefore*mult);
		assertEquals( ((Rectangle) rectangle.getShape()).getWidth(), widthBefore*mult);
		assertEquals( ((Rectangle) rectangle.getShape()).getHeight(), heightBefore*mult);
		assertEquals( ((Rectangle) rectangle.getShape()).getTranslateX(), translateXBefore*mult);
		assertEquals( ((Rectangle) rectangle.getShape()).getTranslateY(), translateYBefore*mult);
	}

}
