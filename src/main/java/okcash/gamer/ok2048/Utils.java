package okcash.gamer.ok2048;

import java.util.Random;

public class Utils {

	private static Random random = new Random();

	public static int nextInt(int bound) {
		return random.nextInt(bound);
	}

	// tile2 : 90%, tile4 : 10%
	public static Tile drawTile() {
		int randomInt = nextInt(10);
		if (randomInt < AI.DRAW_2_TILE_PRECENTAGE * 10) {
			return new Tile(Long.parseUnsignedLong("1"));
		} else {
			return new Tile(Long.parseUnsignedLong("2"));
		}
	}

	public static Board initialBoard() {
		Board board = new Board(Long.parseUnsignedLong("0"));
		board = board.insertTileInit(drawTile(), 16);
		board = board.insertTileInit(drawTile(), 15);

		return board;
	}

	public static int[] getLine(int row) {
		int[] line = new int[4];
		line[0] = (row >> 0) & 0xf;
		line[1] = (row >> 4) & 0xf;
		line[2] = (row >> 8) & 0xf;
		line[3] = (row >> 12) & 0xf;
		return line;
	}

	public static int moveLeft(int[] line) {
		int[] rowMoveLeft = new int[4];
		rowMoveLeft[0] = line[0];
		rowMoveLeft[1] = line[1];
		rowMoveLeft[2] = line[2];
		rowMoveLeft[3] = line[3];

		for (int i = 0; i < 3; ++i) {
			int j;
			for (j = i + 1; j < 4; ++j) {
				if (rowMoveLeft[j] != 0)
					break;
			}
			if (j == 4)
				break; // no more tiles to the right

			if (rowMoveLeft[i] == 0) {
				rowMoveLeft[i] = rowMoveLeft[j];
				rowMoveLeft[j] = 0;
				i--; // retry this entry
				
			} else if (rowMoveLeft[i] == rowMoveLeft[j]) {
				if (rowMoveLeft[i] != 0xf) {
					/* Pretend that 32768 + 32768 = 32768 (representational limit). */
					rowMoveLeft[i]++;
				}
				rowMoveLeft[j] = 0;
			}
		}

		return ((rowMoveLeft[0] << 0) | (rowMoveLeft[1] << 4) | (rowMoveLeft[2] << 8) | (rowMoveLeft[3] << 12)) & 0xffff;
	}

	public static int reverseRow(int row) {
		return ((row >> 12) & 0x000F) | ((row >> 4) & 0x00F0) | ((row << 4) & 0x0F00) | ((row << 12) & 0xF000);
	}

	public static Long unpackCol(int row) {
		Long rowValue = Long.parseUnsignedLong(String.valueOf(row), 10);
		return (rowValue | rowValue << 12 | rowValue << 24 | rowValue << 36) & Constants.COL_MASK;
	}

	public static void printBoard(Board board) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Tile tile = board.getTile(i * 4 + j);
				System.out.printf("%6d", tile.getTileNumber());
			}
			System.out.printf("\n");
		}
		System.out.printf("\n");
	}

	public static Tile drawnTile(Board expectedBoard, Board newBoard) {
		Tile drawnTile = null;
		for (int i = 0; i < 16; i++) {
			Tile tile1 = expectedBoard.getTile(i);
			Tile tile2 = newBoard.getTile(i);
			if (!tile1.equals(tile2)) {
				if (drawnTile == null) {
					drawnTile = tile2;
				} else { // drawn tile exist just one.
					drawnTile = null;
					break;
				}
			}
		}
		return drawnTile;
	}
	
}
