package maze.utilities;

public class Constants {

	public static final int BLOCK_MOUSE = 0;
	public static final int BLOCK_HORIZONTAL = 1;
	public static final int BLOCK_VERTICAL_SMALL = 2;
	public static final int BLOCK_VERTICAL_BIG = 3;
	public static final int BLOCK_GAP = -1;

	public static final int ACTION_UP = 5;
	public static final int ACTION_DOWN = 6;
	public static final int ACTION_LEFT = 7;
	public static final int ACTION_RIGHT = 8;

	public static final int HEURISTIC_1 = 1;
	public static final int HEURISTIC_2 = 2;

	public static int HEURISTIC_CHOSEN = HEURISTIC_2; //default heurestic

	public static final int SEARCH_BREADTH_FIRST = 0, SEARCH_DEPTH_FIRST = 1,
			SEARCH_ITERATIVE_DEEPENING = 2, SEARCH_GREEDY_SEARCH = 3,
			SEARCH_ASTAR_SEARCH = 4;

	public static final int maxLevel = 20; // For ItereativeDeepening
}
