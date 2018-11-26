package okcash.okcash_gamer;

import org.junit.Test;

import okcash.gamer.ok2048.AI;
import okcash.gamer.ok2048.Board;
import okcash.gamer.ok2048.Move;
import okcash.gamer.ok2048.Utils;

public class AITest {
	
	@Test
	public void findBestMove() {
		Board board = new Board(Long.parseUnsignedLong("2546788020248641555"));
		Move move = AI.findBestMove(board);
		
		Utils.printBoard(board);
		System.out.println(move);
	}

}
