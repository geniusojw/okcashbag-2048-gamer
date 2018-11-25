package okcash.gamer.ok2048;

public class Tile {

	public Long value;

	public Tile(Long value) {
		this.value = value;
	}
	
	public int getTileNumber() {
		if (value == 0) {
			return 0;
		} else {
			return 1 << value;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tile)) {
			return false;
		}
		return this.value == ((Tile) obj).value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
