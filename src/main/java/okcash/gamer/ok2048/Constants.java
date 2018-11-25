package okcash.gamer.ok2048;

public class Constants {
	public static final int ROW_CASE_NUMBER = (int) Math.pow(2, 16); // 65536

	public static final Long TILE_MASK	= Long.parseUnsignedLong("000000000000000F", 16);
	public static final Long ROW_MASK	= Long.parseUnsignedLong("000000000000FFFF", 16);
	public static final Long COL_MASK	= Long.parseUnsignedLong("000F000F000F000F", 16);
	public static final Long BOARD_MASK	= Long.parseUnsignedLong("FFFFFFFFFFFFFFFF", 16);

	public static final Long TRANSPOSE_MASK1_1	= Long.parseUnsignedLong("F0F00F0FF0F00F0F", 16);
	public static final Long TRANSPOSE_MASK1_2	= Long.parseUnsignedLong("0000F0F00000F0F0", 16);
	public static final Long TRANSPOSE_MASK1_3	= Long.parseUnsignedLong("0F0F00000F0F0000", 16);

	public static final Long TRANSPOSE_MASK2_1	= Long.parseUnsignedLong("FF00FF0000FF00FF", 16);
	public static final Long TRANSPOSE_MASK2_2	= Long.parseUnsignedLong("00FF00FF00000000", 16);
	public static final Long TRANSPOSE_MASK2_3	= Long.parseUnsignedLong("00000000FF00FF00", 16);

	public static final Long EMPTY_TILE_ONE_MASK	= Long.parseUnsignedLong("1111111111111111", 16);
	public static final Long EMPTY_TILE_THREE_MASK	= Long.parseUnsignedLong("3333333333333333", 16);
}
