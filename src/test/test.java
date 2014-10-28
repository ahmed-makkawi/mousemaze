package test;

import java.util.List;

import maze.grid.BoardMaker;
import maze.grid.BreadthFirstSearch;
import maze.utilities.Node;

public class test {
	public static void main(String[] args) {
		// BoardMaker bm = new BoardMaker();
		// bm.GenGrid();
		// System.out.println("mNumber of blocks to be initiated= "
		// + bm.numberOfBlocks);
		// System.out.println(bm.printBoard());

		// int[][] board = new int[][] { { 1, 2 }, { 3, 4 } };
		// int[][] boardNew = new int[board.length][];
		//
		// for (int i = 0; i < board.length; i++)
		// boardNew[i] = board[i].clone();
		// // boardNew[1][1] = 55555;
		// for (int[] a : board)
		// for (int b : a)
		// System.out.println(b);

		// Block[][] board = new Block[][] { { new Block(), new
		// Block(maze.utilities.Constants.BLOCK_HORIZONTAL,new int[]{0,0}) }, {
		// new Block(), new Block() } };
		// Block[][] boardNew = new Block[board.length][];
		//
		// for (int i = 0; i < board.length; i++)
		// boardNew[i] = board[i].clone();
		// // boardNew[1][1] = 55555;
		//
		// System.out.println(Arrays.deepEquals(board, boardNew));

		// Set<Integer> childNodes = new HashSet<>();
		// Integer[] a = { 1, 2 };
		// Integer[] b = { 1, 2 };
		// childNodes.add(3);
		// childNodes.add(3);
		//
		// System.out.println(childNodes.size());

		BoardMaker bm = new BoardMaker();
		bm.GenGrid();
		System.out.println(bm.printBoard());

		System.out.println("mNumber of blocks to be initiated= "
				+ bm.numberOfBlocks);
		Node root = new Node(null, bm.getBoard(), 0, 0);

		BreadthFirstSearch bf = new BreadthFirstSearch();
		List<Node> goalPath = bf.breadthFirstSearch(root);
		System.out.println("Size" + goalPath.size());
		for (int i = goalPath.size() - 1; i >= 0; i--)
			System.out.println(goalPath.get(i).printBoard());
	}
}
