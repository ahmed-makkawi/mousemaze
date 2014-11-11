package maze.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import maze.grid.Block;

public class CopyOfNode implements Comparable<CopyOfNode> {
	private Block[][] board;
	int pathCost;
	int depthCost;
	CopyOfNode parentNode;

	/**
	 * Node saves the current state of the board
	 * 
	 * @param parentNode
	 * @param board
	 * @param pathCost
	 * @param depthCost
	 */
	public CopyOfNode(CopyOfNode parentNode, Block[][] board, int pathCost, int depthCost) {
		this.board = board;
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
	public CopyOfNode getNextNodeDeprecated(Block block, int action) {
		Block[][] boardNew = new Block[board.length][];

		for (int i = 0; i < board.length; i++)
			boardNew[i] = board[i].clone();

		int posX = block.getPivotPosition()[0];
		int posY = block.getPivotPosition()[1];
		switch (action) {
		case Constants.ACTION_UP:
			if (posX - 1 >= 0)
				if (isGap(boardNew[posX - 1][posY])) {
					block.setPivotPosition(new int[] { posX - 1, posY });
					boardNew[posX - 1][posY] = block;
					boardNew[posX][posY] = block;

					switch (block.getType()) {
					case Constants.BLOCK_VERTICAL_SMALL:
						boardNew[posX + 1][posY] = new Block();
						break;
					case Constants.BLOCK_VERTICAL_BIG:
						boardNew[posX + 1][posY] = block;
						boardNew[posX + 2][posY] = new Block();
						break;
					default:
						return null;

					}
				}
			break;
		case Constants.ACTION_DOWN:
			switch (block.getType()) {
			case Constants.BLOCK_VERTICAL_SMALL:
				if (posX + 2 <= 5)
					if (isGap(boardNew[posX + 2][posY])) {
						block.setPivotPosition(new int[] { posX + 1, posY });
						boardNew[posX + 1][posY] = block;
						boardNew[posX + 2][posY] = block;
						boardNew[posX][posY] = new Block();
					}
				break;
			case Constants.BLOCK_VERTICAL_BIG:
				if (posX + 3 <= 5)
					if (isGap(boardNew[posX + 3][posY])) {
						block.setPivotPosition(new int[] { posX + 1, posY });
						boardNew[posX + 1][posY] = block;
						boardNew[posX + 2][posY] = block;
						boardNew[posX + 3][posY] = block;
						boardNew[posX][posY] = new Block();
					}
				break;
			default:
				return null;
			}
			break;
		case Constants.ACTION_LEFT:
			if (posY - 1 >= 0)
				if (isGap(boardNew[posX][posY - 1])) {
					block.setPivotPosition(new int[] { posX, posY - 1 });
					boardNew[posX][posY + 1] = new Block();
					boardNew[posX][posY - 1] = block;
					boardNew[posX][posY] = block;
				}
			break;

		case Constants.ACTION_RIGHT:
			if (posY + 2 <= 5)
				if (isGap(boardNew[posX][posY + 2])) {
					block.setPivotPosition(new int[] { posX, posY + 1 });
					boardNew[posX][posY] = new Block();
					boardNew[posX][posY + 1] = block;
					boardNew[posX][posY + 2] = block;
				}
			break;
		default:
			return null;

		}

		return new CopyOfNode(this, boardNew, pathCost++, depthCost++);
	}

	/**
	 * Gets next node from current node
	 * 
	 * @param block
	 * @param action
	 * @return
	 */
	public CopyOfNode getNextNode(Block[][] boardNew, Block block, int action) {

		int posX = block.getPivotPosition()[0];
		int posY = block.getPivotPosition()[1];
		switch (action) {
		case Constants.ACTION_UP:
			block.setPivotPosition(new int[] { posX - 1, posY });
			boardNew[posX - 1][posY] = block;
			boardNew[posX][posY] = block;

			switch (block.getType()) {
			case Constants.BLOCK_VERTICAL_SMALL:
				boardNew[posX + 1][posY] = new Block();
				break;
			case Constants.BLOCK_VERTICAL_BIG:
				boardNew[posX + 1][posY] = block;
				boardNew[posX + 2][posY] = new Block();
				break;
			}
			break;
		case Constants.ACTION_DOWN:
			switch (block.getType()) {
			case Constants.BLOCK_VERTICAL_SMALL:
				block.setPivotPosition(new int[] { posX + 1, posY });
				boardNew[posX + 1][posY] = block;
				boardNew[posX + 2][posY] = block;
				boardNew[posX][posY] = new Block();
				break;
			case Constants.BLOCK_VERTICAL_BIG:
				block.setPivotPosition(new int[] { posX + 1, posY });
				boardNew[posX + 1][posY] = block;
				boardNew[posX + 2][posY] = block;
				boardNew[posX + 3][posY] = block;
				boardNew[posX][posY] = new Block();
				break;
			}
			break;
		case Constants.ACTION_LEFT:
			block.setPivotPosition(new int[] { posX, posY - 1 });
			boardNew[posX][posY + 1] = new Block();
			boardNew[posX][posY - 1] = block;
			boardNew[posX][posY] = block;
			break;

		case Constants.ACTION_RIGHT:
			block.setPivotPosition(new int[] { posX, posY + 1 });
			boardNew[posX][posY + 1] = block;
			boardNew[posX][posY + 2] = block;
			boardNew[posX][posY] = new Block();

			break;

		}

		return new CopyOfNode(this, boardNew, pathCost++, depthCost++);
	}

	public List<CopyOfNode> getChildNodes() {
		Set<Integer> visitedBlocks = new HashSet<>();
		List<CopyOfNode> childNodes = new ArrayList<CopyOfNode>();
		ArrayList<Integer> childActions;
		int currentVisited;
		CopyOfNode nextNode;
		Block[][] boardNew = new Block[board.length][];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].getType() == Constants.BLOCK_GAP)
					continue;
				currentVisited = board[i][j].getPivotID();
				if (visitedBlocks.contains(board[i][j].getPivotID()))
					continue;
				childActions = possibleMoves(board[i][j]);
				if (childActions == null)
					continue;
				boardNew = new Block[board.length][];
				visitedBlocks.add(board[i][j].getPivotID());
				for (int action = 0; action < childActions.size(); action++) {
					System.out.println("Action: " + childActions.get(action)
							+ ",applied on block: " + board[i][j].getPivotID());
					for (int xxx = 0; xxx < board.length; xxx++)
						boardNew[xxx] = board[xxx].clone();
					nextNode = getNextNode(boardNew, boardNew[i][j],
							childActions.get(action));
					System.out.println("Cos omm el board:");
					System.out.println(printBoard());
					System.out.println("Cos omm el board l metnaka l new:");

					System.out.println(nextNode.printBoard());
					childNodes.add(nextNode);
				}
			}
		return childNodes;

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
	public List<CopyOfNode> getAncestors(ArrayList<CopyOfNode> nodes) {
		if (parentNode == null) {
			return nodes;
		}
		nodes.add(parentNode);
		parentNode.getAncestors(nodes);
		return nodes;
	}

	@Override
	public int compareTo(CopyOfNode o) {
		// =====Safety check deprecated for now
		if (parentNode == null) { // this = rootNode
			if (o.parentNode == null) // o = rootNode
				return 0;
			else
				return -1;
		} else // this != root
		if (o.parentNode == null) // o = rootNode
			return -1;
		// ===========================================
		// Boards of nodes and of parents are the same
		// ===========================================
		if (Arrays.deepEquals(board, o.board)
				&& Arrays.deepEquals(parentNode.board, o.parentNode.board))
			return 0;
		else
			return -1;
	}

	// public String printBoard() {
	// Block block;
	// Object value;
	// int currentBlockNumber = -1;
	// int blockNumber = 1;
	// String boardString = "================================================";
	// boardString += "\n| ";
	// Hashtable names = new Hashtable();
	// for (int i = 0; i < 6; i++) {
	// for (int j = 0; j < 6; j++) {
	// block = board[i][j];
	// switch (block.getType()) {
	// case maze.utilities.Constants.BLOCK_GAP:
	// boardString += "Gap" + "  |  ";
	// break;
	// case maze.utilities.Constants.BLOCK_MOUSE:
	// boardString += "Rex" + "  |  ";
	// break;
	// default:
	// value = names.get(block.getPivotPosition()[0] + "_"
	// + block.getPivotPosition()[1]);
	// if (value == null) {
	// names.put(
	// block.getPivotPosition()[0] + "_"
	// + block.getPivotPosition()[1],
	// blockNumber);
	// blockNumber++;
	// }
	// currentBlockNumber = (int) names.get(block
	// .getPivotPosition()[0]
	// + "_"
	// + block.getPivotPosition()[1]);
	// if (currentBlockNumber < 10)
	// boardString += currentBlockNumber + ""
	// + currentBlockNumber + "" + currentBlockNumber
	// + "  |  ";
	// else
	// boardString += ("" + currentBlockNumber + ""
	// + currentBlockNumber + "  |  ").substring(1);
	//
	// // boardString += block.getPivotPosition()[0] + "_"
	// // + block.getPivotPosition()[1] + "  |  ";
	// }
	// }
	// boardString += "\n| ";
	// }
	// boardString = boardString.substring(0, boardString.length() - 2);
	// boardString += "================================================";
	// return boardString;
	// }

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
}
