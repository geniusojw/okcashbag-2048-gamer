package okcash.gamer.ok2048;

public class Board {

	private static final Long[] ROW_LEFT_TABLE = new Long[Constants.ROW_CASE_NUMBER];
	private static final Long[] ROW_RIGHT_TABLE = new Long[Constants.ROW_CASE_NUMBER];
	private static final Long[] COL_UP_TABLE = new Long[Constants.ROW_CASE_NUMBER];
	private static final Long[] COL_DOWN_TABLE = new Long[Constants.ROW_CASE_NUMBER];
	
	static {
		for (int row = 0; row < Constants.ROW_CASE_NUMBER; ++row) {
			int[] line = Utils.getLine(row);
			int result = Utils.moveLeft(line);
	
			int reverseRow = Utils.reverseRow(row);
			int reverseResult = Utils.reverseRow(result);
	
			ROW_LEFT_TABLE[row] = Long.parseUnsignedLong(String.valueOf(row ^ result));
			ROW_RIGHT_TABLE[reverseRow] = Long.parseUnsignedLong(String.valueOf(reverseRow ^ reverseResult));
			
			COL_UP_TABLE[row] = Utils.unpackCol(row) ^ Utils.unpackCol(result);
			COL_DOWN_TABLE[reverseRow] = Utils.unpackCol(reverseRow) ^ Utils.unpackCol(reverseResult);
		}
	}
	
	public Long value; // 64bit board (4 bit tile * 16)

	public Board(Long value) {
		this.value = value;
	}

	public Tile getTile(int index) {
		Long tileValue = (value >> (index * 4)) & Constants.TILE_MASK;
		return new Tile(tileValue);
	}

	public void setTile(int index, Tile tile) {
		value &= ~(Constants.TILE_MASK << 4 * index);
		value |= tile.value << 4 * index;
	}
	
	public Board insertTileInit(Tile tile, int emptyTileCount) {
	    int index = Utils.nextInt(emptyTileCount);
	    for (int i = 0; i < 16; i++) {
			Tile tempTile = this.getTile(i);
			if (tempTile.value == 0) {
				if (index == 0) {
					Board newBoard = this.copy();
				    newBoard.setTile(i, tile);
					return newBoard;
				}
				index--;
			}
		}
		throw new Error("insertTileRandom error. tile=" + tile);
	}
	
	public Board insertTile(Tile tile) {
	    int index = Utils.nextInt(this.emptyTileCount());
	    for (int i = 0; i < 16; i++) {
			Tile tempTile = this.getTile(i);
			if (tempTile.value == 0) {
				if (index == 0) {
					Board newBoard = this.copy();
				    newBoard.setTile(i, tile);
					return newBoard;
				}
				index--;
			}
		}
		throw new Error("insertTileRandom error. tile=" + tile);
	}
	
	public boolean executable() {
		for (Move move : Move.values()) {
			if (!this.equals(this.executeMove(move))) {
				return true;
			}
		}
		return false;
	}

	public Board executeMove(Move move) {
		switch (move) {
		case UP: return executeMoveUp();
		case DOWN: return executeMoveDown();
		case LEFT: return executeMoveLeft();
		case RIGHT: return executeMoveRight();
		default: throw new Error("executeMove error. move=" + move);
		}
	}

	private Board executeMoveUp() {
		Long movedValue = value;
		Long transposeValue = transposeValue();

		Long col1Value = (transposeValue >> 0) & Constants.ROW_MASK;
		movedValue ^= COL_UP_TABLE[col1Value.intValue()] << 0;

		Long col2Value = (transposeValue >> 16) & Constants.ROW_MASK;
		movedValue ^= COL_UP_TABLE[col2Value.intValue()] << 4;

		Long col3Value = (transposeValue >> 32) & Constants.ROW_MASK;
		movedValue ^= COL_UP_TABLE[col3Value.intValue()] << 8;

		Long col4Value = (transposeValue >> 48) & Constants.ROW_MASK;
		movedValue ^= COL_UP_TABLE[col4Value.intValue()] << 12;
		
		return new Board(movedValue);
	}

	private Board executeMoveDown() {
		Long movedValue = value;
		Long transposeValue = transposeValue();

		Long col1Value = (transposeValue >> 0) & Constants.ROW_MASK;
		movedValue ^= COL_DOWN_TABLE[col1Value.intValue()] << 0;

		Long col2Value = (transposeValue >> 16) & Constants.ROW_MASK;
		movedValue ^= COL_DOWN_TABLE[col2Value.intValue()] << 4;

		Long col3Value = (transposeValue >> 32) & Constants.ROW_MASK;
		movedValue ^= COL_DOWN_TABLE[col3Value.intValue()] << 8;

		Long col4Value = (transposeValue >> 48) & Constants.ROW_MASK;
		movedValue ^= COL_DOWN_TABLE[col4Value.intValue()] << 12;
		
		return new Board(movedValue);
	}

	private Board executeMoveLeft() {
		Long movedValue = value;

		Long row1Value = (value >> 0) & Constants.ROW_MASK;
		movedValue ^= ROW_LEFT_TABLE[row1Value.intValue()] << 0;

		Long row2Value = (value >> 16) & Constants.ROW_MASK;
		movedValue ^= ROW_LEFT_TABLE[row2Value.intValue()] << 16;

		Long row3Value = (value >> 32) & Constants.ROW_MASK;
		movedValue ^= ROW_LEFT_TABLE[row3Value.intValue()] << 32;

		Long row4Value = (value >> 48) & Constants.ROW_MASK;
		movedValue ^= ROW_LEFT_TABLE[row4Value.intValue()] << 48;
		
		return new Board(movedValue);
	}

	private Board executeMoveRight() {
		Long movedValue = value;

		Long row1Value = (value >> 0) & Constants.ROW_MASK;
		movedValue ^= ROW_RIGHT_TABLE[row1Value.intValue()] << 0;

		Long row2Value = (value >> 16) & Constants.ROW_MASK;
		movedValue ^= ROW_RIGHT_TABLE[row2Value.intValue()] << 16;

		Long row3Value = (value >> 32) & Constants.ROW_MASK;
		movedValue ^= ROW_RIGHT_TABLE[row3Value.intValue()] << 32;

		Long row4Value = (value >> 48) & Constants.ROW_MASK;
		movedValue ^= ROW_RIGHT_TABLE[row4Value.intValue()] << 48;
		
		return new Board(movedValue);
	}
	
	public int emptyTileCount() {
//		int count = 0;
//		for (int i = 0; i < 16; i++) {
//			if (getTile(i).value == 0) {
//				count ++;
//			}
//		}
//		return count;
		
		Long temp = value;
		temp = (temp | temp >> 2) & Constants.EMPTY_TILE_THREE_MASK;
		temp |= temp >> 1;
		temp = ~temp & Constants.EMPTY_TILE_ONE_MASK;
		
		temp += temp >> 32;
		temp += temp >> 16;
		temp += temp >> 8;
		temp += temp >> 4;
		temp &= Constants.TILE_MASK;
		return temp.intValue();
	}

	public Board copy() {
		return new Board(value);
	}
	
	// Transpose rows/columns in a board:
	//   0123     048c
	//   4567 --> 159d
	//   89ab     26ae
	//   cdef     37bf
	private Long transposeValue() {
		Long a1 = this.value & Constants.TRANSPOSE_MASK1_1;
		Long a2 = this.value & Constants.TRANSPOSE_MASK1_2;
		Long a3 = this.value & Constants.TRANSPOSE_MASK1_3;
		Long a = a1 | a2 << 12 | a3 >> 12;
		
		Long b1 = a & Constants.TRANSPOSE_MASK2_1;
		Long b2 = a & Constants.TRANSPOSE_MASK2_2;
		Long b3 = a & Constants.TRANSPOSE_MASK2_3;
		return b1 | b2 >> 24 | b3 << 24;
	}
	
	public Board transpose() {
		Long transposeValue = transposeValue();
		return new Board(transposeValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Board)) {
			return false;
		}
		
		return this.value.equals(((Board) obj).value);
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
