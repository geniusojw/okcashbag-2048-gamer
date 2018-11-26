package okcash.okcash_gamer;

import org.junit.Test;

import okcash.gamer.ok2048.Board;
import okcash.gamer.ok2048.Constants;
import okcash.gamer.ok2048.Utils;

public class ConstantsTest {
	
	@Test
	public void constants() {
		Board board = new Board(Constants.TRANSPOSE_MASK1_1);
		Utils.printBoard(board);
		
		board = new Board(Constants.TRANSPOSE_MASK1_2);
		Utils.printBoard(board);
		
		board = new Board(Constants.TRANSPOSE_MASK1_3);
		Utils.printBoard(board);
		
		board = new Board(Constants.TRANSPOSE_MASK2_1);
		Utils.printBoard(board);
		
		board = new Board(Constants.TRANSPOSE_MASK2_2);
		Utils.printBoard(board);
		
		board = new Board(Constants.TRANSPOSE_MASK2_3);
		Utils.printBoard(board);
	}
}
