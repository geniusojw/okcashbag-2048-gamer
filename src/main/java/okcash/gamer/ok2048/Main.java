package okcash.gamer.ok2048;

public class Main {

	public static void main(String[] ar) {
		Score.initScoreTables();
		playGame();
	}

	private static void playGame() {
		Board board = Utils.initialBoard();
		System.out.println("board=" + board.value);
		int moveCount = 0;
		int scorePenalty = 0; // "penalty" for obtaining free 4 tiles

		while (board.executable()) {
			System.out.printf("Move #%d, current score=%.0f\n", moveCount, Score.score_board(board) - scorePenalty);
			System.out.printf("Current scores: heur %.0f, actual %.0f\n\n", Score.score_heur_board(board), Score.score_board(board));
			
			Move move = AI.findBestMove(board);
			System.out.printf("-> Next Move : %s (current row=%s)\n", move, board.value);
			Utils.printBoard(board);
			System.out.printf("####################################################################\n");

			Board newboard = board.executeMove(move);
			if (newboard.equals(board)) {
				System.out.println("board   =" + board.value);
				System.out.println("newboard=" + newboard.value);
				throw new Error("Illegal move! move=" + move);
			}
			moveCount++;

			Tile tile = Utils.drawTile();
			if (tile.value == 2) {
				scorePenalty += 4;
			}
			board = newboard.insertTile(tile);
		}

		Utils.printBoard(board);
		System.out.printf("\nGame over. Your score is %.0f. The highest rank you achieved was %d\n",
				Score.score_board(board) - scorePenalty, get_max_rank(board));
	}
	
	private static int get_max_rank(Board board) {
	    int maxrank = 0;
//	    while (board) {
//	        maxrank = Math.max(maxrank, int(board & 0xf));
//	        board >>= 4;
//	    }
	    return maxrank;
	}
}