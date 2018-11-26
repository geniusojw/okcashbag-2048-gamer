package okcash.okcash_gamer;

import org.junit.Test;

import okcash.gamer.ok2048.Board;
import okcash.gamer.ok2048.Move;
import okcash.gamer.ok2048.Utils;

public class BoardTest {

	@Test
	public void transpose() {
		Board board = new Board(Long.parseUnsignedLong("1011216313123132315"));
		Utils.printBoard(board);

		board = board.transpose();
		Utils.printBoard(board);
	}

	@Test
	public void move() {
		Board board = new Board(Long.parseUnsignedLong("282583097409831"));
		Utils.printBoard(board);

		board = board.executeMove(Move.DOWN);
		Utils.printBoard(board);
	}

	@Test
	public void emptyTileCount() {
		Board board = new Board(Long.parseUnsignedLong("1249787611734876435"));
		Utils.printBoard(board);
		
		int emptyTileCount = board.emptyTileCount();
		System.out.println(emptyTileCount);
		
		board = new Board(Long.parseUnsignedLong("0"));
		Utils.printBoard(board);
		
		emptyTileCount = board.emptyTileCount();
		System.out.println(emptyTileCount);
	}
}
