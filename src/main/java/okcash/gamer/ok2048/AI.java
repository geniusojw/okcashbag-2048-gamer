package okcash.gamer.ok2048;

import okcash.gamer.ok2048.EvaluateState.TranspositionTableEntry;

public class AI {

	public static final float DRAW_2_TILE_PRECENTAGE = 0.9f;
	public static final float DRAW_4_TILE_PRECENTAGE = 0.1f;
	
	/* Find the best move for a given board. */
	public static Move findBestMove(Board board) {
		float best = 0;
		Move bestmove = null;
		for (Move move : Move.values()) {
			float res = scoreToplevelMove(board, move);

			if (res > best) {
				best = res;
				bestmove = move;
			}
		}
		return bestmove;
	}

	private static float scoreToplevelMove(Board board, Move move) {
		EvaluateState state = new EvaluateState();
		state.depth_limit = Math.max(3, countDistinctTiles(board) - 2);

		long start = System.currentTimeMillis();
		
		float score = 0;
		Board newboard = board.executeMove(move);
		if (!board.equals(newboard)) {
			score = (float) (scoreTilechooseNode(state, newboard, 1.0f) + 1e-6);
		}
		
	    double elapsed = (System.currentTimeMillis() - start) / 1000.0;
	    
		System.out.printf("Move %s: result %f: eval'd %d moves (%d cache hits, %d cache size) in %.2f seconds (maxdepth=%d)\n",
				 move, score, state.moves_evaled, state.cachehits, (int)state.transTable.size(), elapsed, state.maxdepth);
			
		return score;
	}
	
	private static int countDistinctTiles(Board board) {
	    int bitset = 0;
		for (int i = 0; i < 16; i++) {
	    	Tile tile = board.getTile(i);
	    	bitset |= 1 << tile.value;
	    }

	    int count = 0;
	    
	    // Don't count empty tiles.
	    bitset >>= 1;
	    while (bitset > 0) {
	        bitset &= bitset - 1;
	        count++;
	    }
	    return count;
	}

	private static final float CPROB_THRESH_BASE = 0.0001f;
	private static final int CACHE_DEPTH_LIMIT  = 15;

	private static float scoreTilechooseNode(EvaluateState state, Board board, float cprob) {
	    if (cprob < CPROB_THRESH_BASE || state.curdepth >= state.depth_limit) {
	        state.maxdepth = Math.max(state.curdepth, state.maxdepth);
	        return Score.score_heur_board(board);
	    }
	    if (state.curdepth < CACHE_DEPTH_LIMIT) {
	    	TranspositionTableEntry entry = state.transTable.get(board.value);
	        if (entry != null) {
	            /*
	            return heuristic from transposition table only if it means that
	            the node will have been evaluated to a minimum depth of state.depth_limit.
	            This will result in slightly fewer cache hits, but should not impact the
	            strength of the ai negatively.
	            */
	            if(entry.depth <= state.curdepth)
	            {
	                state.cachehits++;
	                return entry.heuristic;
	            }
	        }
	    }

	    int num_open = board.emptyTileCount();
	    cprob /= num_open;

	    int index = 0;
	    
	    float res = 0.0f;
	    Board tmp = board.copy();
	    Board tile_2 = new Board(Long.parseUnsignedLong("1"));
	    while (tile_2.value > 0) {
			if (tmp.getTile(index).value == 0) {
				Board board1 = new Board(board.value | (tile_2.value));
	            res += scoreMoveNode(state, board1, cprob * DRAW_2_TILE_PRECENTAGE) * DRAW_2_TILE_PRECENTAGE;
	            
	            Board board2 = new Board(board.value | (tile_2.value << 1));
	            res += scoreMoveNode(state, board2, cprob * DRAW_4_TILE_PRECENTAGE) * DRAW_4_TILE_PRECENTAGE;
	        }
	        index++;
	        tile_2.value = (tile_2.value << 4) & Constants.BOARD_MASK;
	    }
	    res = res / num_open;

	    if (state.curdepth < CACHE_DEPTH_LIMIT) {
	    	TranspositionTableEntry entry = state.new TranspositionTableEntry(state.curdepth, res);
	    	state.transTable.put(board.value, entry);
	    }

	    return res;
	}
	
	public static float scoreMoveNode(EvaluateState state, Board board, float cprob) {
	    float best = 0.0f;
	    state.curdepth++;
	    for (Move move : Move.values()) {
	        Board newboard = board.executeMove(move);
	        state.moves_evaled++;
	
	        if (!board.equals(newboard)) {
	            best = Math.max(best, scoreTilechooseNode(state, newboard, cprob));
	        }
	    }
	    state.curdepth--;
	
	    return best;
	}
}
