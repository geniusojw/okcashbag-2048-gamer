package okcash.gamer.ok2048;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class TileColor {
	
	private static final Map<Color, Tile> TILE_OF_COLOR_MAP;
	
	static {
		TILE_OF_COLOR_MAP = new HashMap<>();
		
		// diaplay 1
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(155, 155, 147), new Tile(Long.parseUnsignedLong("0"))); // empty
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 228, 190), new Tile(Long.parseUnsignedLong("1"))); // 2
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 202, 111), new Tile(Long.parseUnsignedLong("2"))); // 4
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 169, 117), new Tile(Long.parseUnsignedLong("3"))); // 8
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 87, 215, 207), new Tile(Long.parseUnsignedLong("4"))); // 16
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 116,  89), new Tile(Long.parseUnsignedLong("5"))); // 32
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 15, 175, 190), new Tile(Long.parseUnsignedLong("6"))); // 64
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(148,   0, 101), new Tile(Long.parseUnsignedLong("7"))); // 128
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(227,  45, 155), new Tile(Long.parseUnsignedLong("8"))); // 256
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 88, 154, 208), new Tile(Long.parseUnsignedLong("9"))); // 512
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(156, 154,   1), new Tile(Long.parseUnsignedLong("10"))); // 1024
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(228,  68,  71), new Tile(Long.parseUnsignedLong("11"))); // 2048
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 81,  51, 149), new Tile(Long.parseUnsignedLong("12"))); // 4096
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(  0,  97,  96), new Tile(Long.parseUnsignedLong("13"))); // 8192
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(158,   1,  20), new Tile(Long.parseUnsignedLong("14"))); // 16384
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 65,  65,  65), new Tile(Long.parseUnsignedLong("15"))); // 32768
		
		// diaplay 2
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(153, 153, 153), new Tile(Long.parseUnsignedLong("0"))); // empty
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 238, 187), new Tile(Long.parseUnsignedLong("1"))); // 2
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 204, 102), new Tile(Long.parseUnsignedLong("2"))); // 4
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 170, 119), new Tile(Long.parseUnsignedLong("3"))); // 8
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 85, 221, 204), new Tile(Long.parseUnsignedLong("4"))); // 16
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(255, 119,  85), new Tile(Long.parseUnsignedLong("5"))); // 32
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(  0, 170, 187), new Tile(Long.parseUnsignedLong("6"))); // 64
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(153,   0, 102), new Tile(Long.parseUnsignedLong("7"))); // 128
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(238,  34, 153), new Tile(Long.parseUnsignedLong("8"))); // 256
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 85, 153, 221), new Tile(Long.parseUnsignedLong("9"))); // 512
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(153, 153,   0), new Tile(Long.parseUnsignedLong("10"))); // 1024
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(238,  68,  68), new Tile(Long.parseUnsignedLong("11"))); // 2048
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 85,  51, 153), new Tile(Long.parseUnsignedLong("12"))); // 4096
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(  0, 102, 102), new Tile(Long.parseUnsignedLong("13"))); // 8192
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(153,   0,  17), new Tile(Long.parseUnsignedLong("14"))); // 16384
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 68,  68,  68), new Tile(Long.parseUnsignedLong("15"))); // 32768
		
		// display ??
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(155, 154,   1), new Tile(Long.parseUnsignedLong("10"))); // 1024
		TILE_OF_COLOR_MAP.putIfAbsent(new Color(228,  67,  72), new Tile(Long.parseUnsignedLong("11"))); // 2048
		TILE_OF_COLOR_MAP.putIfAbsent(new Color( 80,  51, 148), new Tile(Long.parseUnsignedLong("12"))); // 4096
	}
	
	public static Tile getTileByColor(Color color) {
		return TILE_OF_COLOR_MAP.get(color);
	}
}
