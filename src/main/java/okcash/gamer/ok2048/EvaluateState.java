package okcash.gamer.ok2048;

import java.util.HashMap;
import java.util.Map;

public class EvaluateState {

	public class TranspositionTableEntry {
		public int depth;
		public float heuristic;

		public TranspositionTableEntry(int depth, float heuristic) {
			this.depth = depth;
			this.heuristic = heuristic;
		}
	}

	// transposition table, to cache previously-seen moves
	public Map<Long, TranspositionTableEntry> transTable = new HashMap<>();
	public int maxdepth;
	public int curdepth;
	public int cachehits;
	public long moves_evaled;
	public int depth_limit;

	public EvaluateState() {
	}
}
