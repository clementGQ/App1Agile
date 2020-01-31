package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import controllers.DShapeFactory;
import controllers.MainApp;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

class CircleTest {

	private static DCircle circle = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DShapeFactory dsf = new DShapeFactory();
		circle = (DCircle) dsf.getShape("circle", 50, 50, 100, 100, "border", "images/.jpg", 2, Color.BLACK);
		circle.getShape().setFill(Color.BLACK);
	}

	@Test
	void shapeToStringTest() {
		String str = circle.shapeToString();
		assertEquals(str.compareTo("circle&50.0&50.0&70.71067811865476&0.0&0.0&1.0&1.0&0.0&0.0&0.0&0.0&2.0&null"), 0);
		
		
		try {
			new DCircle(str);
			assertFalse(true,"can't create a circle without filePath");
		} catch(Exception e) {
			
		}
		
	}
	
	@Test
	void zoomTest() {
		int mult = 2;
		double centerXBefore = ((Circle) circle.getShape()).getCenterX();
		double centerYBefore = ((Circle) circle.getShape()).getCenterY();
		double scaleXBefore = ((Circle) circle.getShape()).getScaleX();
		double scaleYBefore = ((Circle) circle.getShape()).getScaleY();
		double translateXBefore = ((Circle) circle.getShape()).getTranslateX();
		double translateYBefore = ((Circle) circle.getShape()).getTranslateY();
		
		circle.zoom(mult);
		
		assertEquals( ((Circle) circle.getShape()).getCenterX(), centerXBefore*mult);
		assertEquals( ((Circle) circle.getShape()).getCenterY(), centerYBefore*mult);
		assertEquals( ((Circle) circle.getShape()).getScaleX(), scaleXBefore*mult);
		assertEquals( ((Circle) circle.getShape()).getScaleY(), scaleYBefore*mult);
		assertEquals( ((Circle) circle.getShape()).getTranslateX(), translateXBefore*mult);
		assertEquals( ((Circle) circle.getShape()).getTranslateY(), translateYBefore*mult);
	}

}
