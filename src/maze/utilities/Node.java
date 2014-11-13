package maze.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node implements Comparable<Node> {
	private Block[][] board;
	public int pathCost; // incremented by 2
	public int depthCost;
	public Node parentNode;
	public int costHeuristicValue = 0;

	/**
	 * Node saves the current state of the board
	 * 
	 * @param parentNode
	 * @param board
	 * @param pathCost
	 * @param depthCost
	 */
	public Node(Node parentNode, Block[][] board, int pathCost, int depthCost) {
		this.board = new Block[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				this.board[i][j] = new Block(board[i][j]);
		this.pathCost = pathCost;
		this.depthCost = depthCost;
		this.parentNode = parentNode;
	}

	/**
	 * Method represents the operators and successors/Children
	 * 
	 * @param block
	 * @param action
	 * @return
	 */

	/**
	 * Gets next node from current node
	 * 
	 * @param block
	 * @param action
	 * @return
	 */
	public Node getNextNode(Block block, int action) {
		int posX = block.getPivotPosition()[0];
		int posY = block.getPivotPosition()[1];

		Node newNode = new Node(this, board, pathCost + 3, depthCost + 1);
		Block[][] boardNew = newNode.board;

		final int type = boardNew[posX][posY].getType();
		final int id = boardNew[posX][posY].id;
		switch (action) {
		case Constants.ACTION_UP:

			boardNew[posX - 1][posY].setBlock(type,
					new int[] { posX - 1, posY }, id);
			boardNew[posX][posY].setBlock(type, new int[] { posX - 1, posY },
					id);

			switch (block.getType()) {
			case Constants.BLOCK_VERTICAL_SMALL:

				boardNew[posX + 1][posY]
						.setBlock(Constants.BLOCK_GAP, null, -1);

				break;
			case Constants.BLOCK_VERTICAL_BIG:

				boardNew[posX + 1][posY].setBlock(Constants.BLOCK_VERTICAL_BIG,
						new int[] { posX - 1, posY }, id);

				boardNew[posX + 2][posY]
						.setBlock(Constants.BLOCK_GAP, null, -1);
				break;
			}
			break;
		case Constants.ACTION_DOWN:
			switch (block.getType()) {
			case Constants.BLOCK_VERTICAL_SMALL:
				boardNew[posX + 1][posY].setBlock(
						Constants.BLOCK_VERTICAL_SMALL, new int[] { posX + 1,
								posY }, id);
				boardNew[posX + 2][posY].setBlock(
						Constants.BLOCK_VERTICAL_SMALL, new int[] { posX + 1,
								posY }, id);

				boardNew[posX][posY].setBlock(Constants.BLOCK_GAP, null, -1);
				break;
			case Constants.BLOCK_VERTICAL_BIG:
				boardNew[posX + 1][posY].setBlock(Constants.BLOCK_VERTICAL_BIG,
						new int[] { posX + 1, posY }, id);
				boardNew[posX + 2][posY].setBlock(Constants.BLOCK_VERTICAL_BIG,
						new int[] { posX + 1, posY }, id);
				boardNew[posX + 3][posY].setBlock(Constants.BLOCK_VERTICAL_BIG,
						new int[] { posX + 1, posY }, id);

				boardNew[posX][posY].setBlock(Constants.BLOCK_GAP, null, -1);
				break;
			}
			break;
		case Constants.ACTION_LEFT:
			boardNew[posX][posY - 1].setBlock(type,
					new int[] { posX, posY - 1 }, id);
			boardNew[posX][posY].setBlock(type, new int[] { posX, posY - 1 },
					id);

			boardNew[posX][posY + 1].setBlock(Constants.BLOCK_GAP, null, -1);
			break;

		case Constants.ACTION_RIGHT:
			boardNew[posX][posY + 1].setBlock(type,
					new int[] { posX, posY + 1 }, id);
			boardNew[posX][posY + 2].setBlock(type,
					new int[] { posX, posY + 1 }, id);

			boardNew[posX][posY].setBlock(Constants.BLOCK_GAP, null, -1);

			break;

		}
		return newNode;
	}

	public List<Node> getChildNodes() {
		Set<Integer> visitedBlocks = new HashSet<>();
		List<Node> childNodes = new ArrayList<Node>();
		ArrayList<Integer> childActions;
		Node nextNode;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].getType() == Constants.BLOCK_GAP)
					continue;
				if (visitedBlocks.contains(board[i][j].getPivotID()))
					continue;
				childActions = possibleMoves(board[i][j]);
				if (childActions == null)
					continue;
				visitedBlocks.add(board[i][j].getPivotID());
				for (int action = 0; action < childActions.size(); action++) {
					nextNode = getNextNode(board[i][j],
							childActions.get(action));
					childNodes.add(nextNode);
				}
			}
		return childNodes;

	}

	/**
	 * 
	 * @return childNodes 2D ArrayList childNodes[0] =
	 *         childNodesWithHeuristicValue0; childNodes[1] =
	 *         childNodesWithHeuristicValue1; To ease the greedy algorithm
	 */
	public List<Node> getChildNodesWithHeuristics() {
		Set<Integer> visitedBlocks = new HashSet<>();
		List<Node> childNodes = new ArrayList<Node>();

		ArrayList<Integer> childActions;
		Node nextNode;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].getType() == Constants.BLOCK_GAP)
					continue;
				if (visitedBlocks.contains(board[i][j].getPivotID()))
					continue;
				childActions = possibleMoves(board[i][j]);
				if (childActions == null)
					continue;
				visitedBlocks.add(board[i][j].getPivotID());
				for (int action = 0; action < childActions.size(); action++) {
					nextNode = getNextNode(board[i][j],
							childActions.get(action));
					nextNode.costHeuristicValue = getHeuristicOfBlock(
							Constants.HEURISTIC_CHOSEN, board[i][j]);
					childNodes.add(nextNode);
				}
			}

		return childNodes;
	}

	/**
	 * 
	 * @return childNodes 2D ArrayList childNodes[0] =
	 *         childNodesWithHeuristicValue0; childNodes[1] =
	 *         childNodesWithHeuristicValue1; To ease the greedy algorithm
	 */
	public List<Node> getChildNodesWithHeuristicsAndCost() {
		Set<Integer> visitedBlocks = new HashSet<>();
		List<Node> childNodes = new ArrayList<Node>();

		ArrayList<Integer> childActions;
		Node nextNode;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].getType() == Constants.BLOCK_GAP)
					continue;
				if (visitedBlocks.contains(board[i][j].getPivotID()))
					continue;
				childActions = possibleMoves(board[i][j]);
				if (childActions == null)
					continue;
				visitedBlocks.add(board[i][j].getPivotID());
				for (int action = 0; action < childActions.size(); action++) {
					nextNode = getNextNode(board[i][j],
							childActions.get(action));
					nextNode.costHeuristicValue = getHeuristicOfBlock(
							Constants.HEURISTIC_CHOSEN, board[i][j])
							+ nextNode.pathCost;
					childNodes.add(nextNode);
				}
			}

		return childNodes;
	}

	public int getHeuristicOfBlock(int heuristicID, Block c) {
		// =====================
		// Centric property, goal has heuristic value = 0
		// =====================
		if (c.getType() == Constants.BLOCK_MOUSE)
			if (c.getPivotPosition()[1] == 4)
				return 0;

		switch (heuristicID) {
		// ====================
		// Prefer to move REX and Vertical Blocks with PivotPos in Row1 or Row2
		// ====================
		case Constants.HEURISTIC_1:
			if (c.getType() == Constants.BLOCK_VERTICAL_BIG
					|| c.getType() == Constants.BLOCK_VERTICAL_SMALL)
				if (c.getPivotPosition()[0] == 2
						|| c.getPivotPosition()[0] == 1)
					return 2;
			if (c.getType() == Constants.BLOCK_MOUSE)
				return 1;
			else
				return 3;
		case Constants.HEURISTIC_2:
			// ====================
			// Prefer to move REX and Vertical Blocks with PivotPos in Row1 or
			// Row2 and Horizontal Blocks in Row1 and Row3
			// ====================
			if (c.getType() == Constants.BLOCK_MOUSE)
				return 1;
			if (c.getType() == Constants.BLOCK_VERTICAL_BIG
					|| c.getType() == Constants.BLOCK_VERTICAL_SMALL)
				if (c.getPivotPosition()[0] == 2
						|| c.getPivotPosition()[0] == 1)
					return 2;
			if (c.getType() == Constants.BLOCK_HORIZONTAL)
				if (c.getPivotPosition()[0] == 3
						|| c.getPivotPosition()[0] == 1)
					return 2;
				else
					return 3;

		}
		return 3;
	}

	/**
	 * 
	 * @param block
	 * @return List of all possible actions, null if cannot move
	 */
	private ArrayList<Integer> possibleMoves(Block block) {
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		int posX = block.getPivotPosition()[0];
		int posY = block.getPivotPosition()[1];
		switch (block.getType()) {
		case Constants.BLOCK_GAP:
			return null;
		case Constants.BLOCK_VERTICAL_SMALL:
			if (posX - 1 >= 0)
				if (isGap(board[posX - 1][posY]))
					possibleMoves.add(Constants.ACTION_UP);
			if (posX + 2 <= 5)
				if (isGap(board[posX + 2][posY]))
					possibleMoves.add(Constants.ACTION_DOWN);
			return possibleMoves;
		case Constants.BLOCK_VERTICAL_BIG:
			if (posX - 1 >= 0)
				if (isGap(board[posX - 1][posY]))
					possibleMoves.add(Constants.ACTION_UP);
			if (posX + 3 <= 5)
				if (isGap(board[posX + 3][posY]))
					possibleMoves.add(Constants.ACTION_DOWN);
			return possibleMoves;

		case Constants.BLOCK_HORIZONTAL:
		case Constants.BLOCK_MOUSE:
			if (posY - 1 >= 0)
				if (isGap(board[posX][posY - 1]))
					possibleMoves.add(Constants.ACTION_LEFT);

			if (posY + 2 <= 5)
				if (isGap(board[posX][posY + 2]))
					possibleMoves.add(Constants.ACTION_RIGHT);

			return possibleMoves;
		}
		return null;
	}

	/**
	 * 
	 * @param block
	 * @return true if the destination is a gap block
	 */
	private boolean isGap(Block block) {
		if (block.getType() == Constants.BLOCK_GAP)
			return true;
		return false;
	}

	/**
	 * Recursive call
	 * 
	 * @return
	 */
	public List<Node> getAncestors(List<Node> nodes) {
		if (parentNode == null) {
			return nodes;
		}
		nodes.add(parentNode);
		parentNode.getAncestors(nodes);
		return nodes;
	}

	@Override
	public int hashCode() {

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		Node o = (Node) obj;
		// =====Safety check deprecated for now
		if (parentNode == null) { // this = rootNode
			if (o.parentNode == null) // o = rootNode
				return true;
			else
				return false;
		} else // this != root
		if (o.parentNode == null) // o = rootNode
			return false;
		// ===========================================
		// Boards of nodes and of parents are the same
		// ===========================================
		// if (Arrays.deepEquals(board, o.board)
		// && Arrays.deepEquals(parentNode.board, o.parentNode.board))
		// if (compareBoards(board, o.board)
		// && compareBoards(parentNode.board, o.parentNode.board))
		if (compareBoards(board, o.board))
			return true;
		else
			return false;
	}

	public static boolean compareBoards(Block[][] b1, Block[][] b2) {
		boolean b = true;
		for (int i = 0; i < b1.length; i++)
			for (int j = 0; j < b1[0].length; j++)
				b &= (b1[i][j].equals(b2[i][j]));
		return b;

	}

	public String printBoard() {
		Block block;
		String boardString = "================================================";
		boardString += "\n| ";
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				block = board[i][j];
				switch (block.getType()) {
				case maze.utilities.Constants.BLOCK_GAP:
					boardString += "Gap" + "  |  ";
					break;
				case maze.utilities.Constants.BLOCK_MOUSE:
					boardString += "Rex" + "  |  ";
					break;
				default:
					if (block.id < 10)
						boardString += block.id + "" + block.id + "" + block.id
								+ "" + "  |  ";
					else
						boardString += ("" + block.id + "" + block.id + "  |  ")
								.substring(1);

				}
			}
			boardString += "\n| ";
		}
		boardString = boardString.substring(0, boardString.length() - 2);
		boardString += "================================================";
		return boardString;
	}

	public static String printBoard(Block[][] board) {
		Block block;
		String boardString = "================================================";
		boardString += "\n| ";
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				block = board[i][j];
				switch (block.getType()) {
				case maze.utilities.Constants.BLOCK_GAP:
					boardString += "Gap" + "  |  ";
					break;
				case maze.utilities.Constants.BLOCK_MOUSE:
					boardString += "Rex" + "  |  ";
					break;
				default:
					if (block.id < 10)
						boardString += block.id + "" + block.id + "" + block.id
								+ "" + "  |  ";
					else
						boardString += ("" + block.id + "" + block.id + "  |  ")
								.substring(1);

					// boardString += block.getPivotPosition()[0] + "_"
					// + block.getPivotPosition()[1] + "  |  ";
				}
			}
			boardString += "\n| ";
		}
		boardString = boardString.substring(0, boardString.length() - 2);
		boardString += "================================================";
		return boardString;
	}

	public Block[][] getBoard() {
		return board;
	}

	public String toString() {
		return printBoard();
	}

	@Override
	public int compareTo(Node o) {
		int compare = Integer.compare(costHeuristicValue, o.costHeuristicValue);
		if (compare == 0)
			return 1;
		else
			return Integer.compare(costHeuristicValue, o.costHeuristicValue);
	}
}
