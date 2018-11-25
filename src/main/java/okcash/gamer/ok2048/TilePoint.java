package okcash.gamer.ok2048;

import java.awt.Point;

public class TilePoint {

	public static Point[] tilePoints;
	
	static {
		Point firstTilePoint = new Point(950, 405);
		int tileGap = 97;
		
		tilePoints = new Point[16];
		
		tilePoints[ 0] = new Point(firstTilePoint.x + tileGap * 0, firstTilePoint.y + tileGap * 0);
		tilePoints[ 1] = new Point(firstTilePoint.x + tileGap * 1, firstTilePoint.y + tileGap * 0);
		tilePoints[ 2] = new Point(firstTilePoint.x + tileGap * 2, firstTilePoint.y + tileGap * 0);
		tilePoints[ 3] = new Point(firstTilePoint.x + tileGap * 3, firstTilePoint.y + tileGap * 0);

		tilePoints[ 4] = new Point(firstTilePoint.x + tileGap * 0, firstTilePoint.y + tileGap * 1);
		tilePoints[ 5] = new Point(firstTilePoint.x + tileGap * 1, firstTilePoint.y + tileGap * 1);
		tilePoints[ 6] = new Point(firstTilePoint.x + tileGap * 2, firstTilePoint.y + tileGap * 1);
		tilePoints[ 7] = new Point(firstTilePoint.x + tileGap * 3, firstTilePoint.y + tileGap * 1);

		tilePoints[ 8] = new Point(firstTilePoint.x + tileGap * 0, firstTilePoint.y + tileGap * 2);
		tilePoints[ 9] = new Point(firstTilePoint.x + tileGap * 1, firstTilePoint.y + tileGap * 2);
		tilePoints[10] = new Point(firstTilePoint.x + tileGap * 2, firstTilePoint.y + tileGap * 2);
		tilePoints[11] = new Point(firstTilePoint.x + tileGap * 3, firstTilePoint.y + tileGap * 2);

		tilePoints[12] = new Point(firstTilePoint.x + tileGap * 0, firstTilePoint.y + tileGap * 3);
		tilePoints[13] = new Point(firstTilePoint.x + tileGap * 1, firstTilePoint.y + tileGap * 3);
		tilePoints[14] = new Point(firstTilePoint.x + tileGap * 2, firstTilePoint.y + tileGap * 3);
		tilePoints[15] = new Point(firstTilePoint.x + tileGap * 3, firstTilePoint.y + tileGap * 3);
	}

}
