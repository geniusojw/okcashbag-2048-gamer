package okcash.okcash_gamer;

import org.junit.Assert;
import org.junit.Test;

import okcash.gamer.ok2048.Tile;

public class TileTest {
	
	@Test
	public void getTileValue() {
		Assert.assertEquals(0, new Tile(Long.parseUnsignedLong("0")).getTileNumber());
		Assert.assertEquals(2, new Tile(Long.parseUnsignedLong("1")).getTileNumber());
		Assert.assertEquals(4, new Tile(Long.parseUnsignedLong("2")).getTileNumber());
		Assert.assertEquals(8, new Tile(Long.parseUnsignedLong("3")).getTileNumber());
		Assert.assertEquals(16, new Tile(Long.parseUnsignedLong("4")).getTileNumber());
		Assert.assertEquals(32, new Tile(Long.parseUnsignedLong("5")).getTileNumber());
		Assert.assertEquals(64, new Tile(Long.parseUnsignedLong("6")).getTileNumber());
	}
}
