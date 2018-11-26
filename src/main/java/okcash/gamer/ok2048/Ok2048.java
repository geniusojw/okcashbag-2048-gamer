package okcash.gamer.ok2048;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import okcash.gamer.OkGame;

public class Ok2048 implements OkGame {
	
	private static final int[] KEY_EVENT = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };
	
	private Robot robotGamer;

	@Override
	public void run() {
		try {
			robotGamer = new Robot();
			
			Score.initScoreTables();
			playGame();
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private void playGame() {
		Map<Integer, Integer> drawnTileCount = new HashMap<>();
		
		Board board = getBoardFromScreen();
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

			doMoveAction(move);

			Board expectedBoard = board.executeMove(move);
			System.out.println("board        : " + board);
			System.out.println("expectedBoard: " + expectedBoard);
			
			for (int index = 0; index < 10; index++) {
				Board newBoard = getBoardFromScreen();
				if (!board.equals(newBoard)) {
					Tile drawnTile = Utils.drawnTile(expectedBoard, newBoard);
					if (drawnTile != null || index == 9) {
						if (drawnTile != null) {
							drawnTileCount.put(drawnTile.getTileNumber(), drawnTileCount.getOrDefault(drawnTile.getTileNumber(), 0) + 1);
							System.out.println("newBoard     : " + newBoard);
							System.out.printf("* drawnTile : <%s> %s \n", drawnTile.getTileNumber(), drawnTileCount);
						}
						
						moveCount++;
						board = newBoard;
						break;
					}
				}
				sleepTime(100);
				System.out.printf("sleep (" + index + ")\n");
			}
		}

		Utils.printBoard(board);
		System.out.printf("\nGame over. Your score is %.0f. The highest rank you achieved was %d\n",
				Score.score_board(board) - scorePenalty, get_max_rank(board));
	}

	private void doMoveAction(Move move) {
		robotGamer.mouseMove(TilePoint.clickPoint.x, TilePoint.clickPoint.y);
		robotGamer.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robotGamer.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		sleepTime(200);
		
		int key = KEY_EVENT[move.ordinal()];

		robotGamer.keyPress(key);
		sleepTime(10);
		robotGamer.keyRelease(key);
		sleepTime(980);
	}

	private Board getBoardFromScreen() {
		Board board = new Board(Long.parseUnsignedLong("0"));
		for (int i = 0; i < 16; i++) {
			Point tilePoint = TilePoint.tilePoints[i];
			Color tileColor = robotGamer.getPixelColor(tilePoint.x, tilePoint.y);
			Tile tile = TileColor.getTileByColor(tileColor);
			
			int retryCount = 0;
			while (tile == null) {
				System.out.println("unknown color. tile index=" + i + ", color RGB=" + tileColor + " (retryCout=" + retryCount + ")");
				retryCount++;
				if (retryCount >= 100) {
					throw new Error("unknown color.");
				} else if (retryCount >= 30) {
					sleepTime(10000);
				} else if (retryCount >= 10) {
					sleepTime(5000);
				} else {
					sleepTime(1000);
				}
				
				tileColor = robotGamer.getPixelColor(tilePoint.x, tilePoint.y); // retry
				tile = TileColor.getTileByColor(tileColor);
			}
			board.setTile(i, tile);
		}		
		return board;
	}

	private void sleepTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private int get_max_rank(Board board) {
	    int maxrank = 0;
//	    while (board) {
//	        maxrank = Math.max(maxrank, int(board & 0xf));
//	        board >>= 4;
//	    }
	    return maxrank;
	}
	
}
