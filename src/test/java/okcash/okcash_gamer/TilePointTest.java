package okcash.okcash_gamer;

import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;

import org.junit.jupiter.api.Test;

import okcash.gamer.ok2048.TileColor;
import okcash.gamer.ok2048.TilePoint;

public class TilePointTest {
	
	@Test
	public void constants() throws Exception {

		Robot robot = new Robot();
		for (Point tilePoint : TilePoint.tilePoints) {
			robot.mouseMove(tilePoint.x, tilePoint.y);
			
			Color tileColor = robot.getPixelColor(tilePoint.x, tilePoint.y);
			System.out.println(tilePoint + "=" + tileColor + " (" + TileColor.getTileByColor(tileColor) + ")");
			Thread.sleep(200);
		}
		
		robot.mouseMove(TilePoint.clickPoint.x, TilePoint.clickPoint.y);
	}
	
}
