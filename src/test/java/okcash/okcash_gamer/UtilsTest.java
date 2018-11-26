package okcash.okcash_gamer;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import okcash.gamer.ok2048.Board;
import okcash.gamer.ok2048.Constants;
import okcash.gamer.ok2048.Utils;

public class UtilsTest {
	
	@Test
	public void moveLeft() {
		int row = 2 | 1 << 4 | 4 << 8 | 5 << 12;
		Utils.printBoard(new Board(Long.parseUnsignedLong(String.valueOf(row))));
		
		int[] line = Utils.getLine(row);
		int moveLeftRow = Utils.moveLeft(line);
		Utils.printBoard(new Board(Long.parseUnsignedLong(String.valueOf(moveLeftRow))));
	}

	@Test
	public void reverseRow() {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int row = random.nextInt(Constants.ROW_CASE_NUMBER);
			System.out.println("[test #" + i + "]");
			Board board = new Board(Long.parseUnsignedLong(String.valueOf(row)));
			Board revBoard = new Board(Long.parseUnsignedLong(String.valueOf(Utils.reverseRow(board.value.intValue()))));
			Board revRevBoard = new Board(Long.parseUnsignedLong(String.valueOf(Utils.reverseRow(revBoard.value.intValue()))));

			Assert.assertEquals(board.value.intValue(), revRevBoard.value.intValue());
		}
	}
}
