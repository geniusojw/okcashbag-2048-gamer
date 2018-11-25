package okcash.gamer.ok2048;

public class Score {

	private static float[] score_table = new float[Constants.ROW_CASE_NUMBER];
	private static float[] heur_score_table = new float[Constants.ROW_CASE_NUMBER];
	
	// Heuristic scoring settings
	private static final float SCORE_LOST_PENALTY = 200000.0f;
	private static final float SCORE_MONOTONICITY_POWER = 4.0f;
	private static final float SCORE_MONOTONICITY_WEIGHT = 47.0f;
	private static final float SCORE_SUM_POWER = 3.5f;
	private static final float SCORE_SUM_WEIGHT = 11.0f;
	private static final float SCORE_MERGES_WEIGHT = 700.0f;
	private static final float SCORE_EMPTY_WEIGHT = 270.0f;

	public static void initScoreTables() {
		for (int row = 0; row < Constants.ROW_CASE_NUMBER; ++row) {
			int[] line = Utils.getLine(row);

			score_table[row] = score(line);
			heur_score_table[row] = heuristicScore(line);

//			System.out.print(String.format("%2d %2d %2d %2d", line[0], line[1], line[2], line[3]));
//			System.out.print(String.format("%20f %20f", score_table[row], heur_score_table[row]));
//			System.out.println();
		}
	}

	private static float score(int[] line) {
		float score = 0.0f;
		for (int i = 0; i < 4; ++i) {
			int rank = line[i];
			if (rank >= 2) {
				// the score is the total sum of the tile and all intermediate merged tiles
				score += (rank - 1) * (1 << rank);
			}
		}
		return score;
	}

	private static float heuristicScore(int[] line) {
		float sum = 0;
		int empty = 0;
		int merges = 0;

		int prev = 0;
		int counter = 0;
		for (int i = 0; i < 4; ++i) {
			int rank = line[i];
			sum += Math.pow(rank, SCORE_SUM_POWER);
			if (rank == 0) {
				empty++;
			} else {
				if (prev == rank) {
					counter++;
				} else if (counter > 0) {
					merges += 1 + counter;
					counter = 0;
				}
				prev = rank;
			}
		}
		if (counter > 0) {
			merges += 1 + counter;
		}

		float monotonicity_left = 0;
		float monotonicity_right = 0;
		for (int i = 1; i < 4; ++i) {
			if (line[i - 1] > line[i]) {
				monotonicity_left += Math.pow(line[i - 1], SCORE_MONOTONICITY_POWER) - Math.pow(line[i], SCORE_MONOTONICITY_POWER);
			} else {
				monotonicity_right += Math.pow(line[i], SCORE_MONOTONICITY_POWER) - Math.pow(line[i - 1], SCORE_MONOTONICITY_POWER);
			}
		}

		float heur_score = SCORE_LOST_PENALTY + SCORE_EMPTY_WEIGHT * empty + SCORE_MERGES_WEIGHT * merges
				- SCORE_MONOTONICITY_WEIGHT * Math.min(monotonicity_left, monotonicity_right) - SCORE_SUM_WEIGHT * sum;
		return heur_score;
	}

	public static float score_heur_board(Board board) {
		return scoreHelper(board, heur_score_table) + scoreHelper(board.transpose(), heur_score_table);
	}

	public static float score_board(Board board) {
		return scoreHelper(board, score_table);
	}

	private static float scoreHelper(Board board, float[] table) {
		Long row1Value = (board.value >> 0) & Constants.ROW_MASK;
		Long row2Value = (board.value >> 16) & Constants.ROW_MASK;
		Long row3Value = (board.value >> 32) & Constants.ROW_MASK;
		Long row4Value = (board.value >> 48) & Constants.ROW_MASK;
		
		return table[row1Value.intValue()] + table[row2Value.intValue()] + table[row3Value.intValue()] + table[row4Value.intValue()];
	}
}
