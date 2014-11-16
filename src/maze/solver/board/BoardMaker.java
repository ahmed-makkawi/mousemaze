package maze.solver.board;

import maze.solver.utilities.Block;
import maze.solver.utilities.Constants;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/**
 * main class that generates the board, and generates a random number of blocks
 * and assign them in random places, also it can print the board.
 * 
 * @author ahmed,Omar and Gelly
 *
 */
public class BoardMaker {
    Block[][] board = new Block[6][6];
    public static final int MAX_LOOPS = 100;

    // ============================#================================
    // Booleans which decreases the chance of "No Possible solution"
    // ============================#================================

    public static boolean horizontalBlockBesideMouse = false;

    // ============================#================================
    // End of Booleans
    // ============================#================================

    public BoardMaker() {

	for (int i = 0; i < 6; i++)
	    for (int j = 0; j < 6; j++)
		board[i][j] = new Block();
	board[2][0] = new Block(Constants.BLOCK_MOUSE, new int[] { 2, 0 }, true);
	board[2][1] = new Block(Constants.BLOCK_MOUSE, new int[] { 2, 0 }, true);

    }

    public int numberOfBlocks = -1;

    /**
     * responsible for generating random grid and fill it randomly with blocks
     */
    public void GenGrid() {
	numberOfBlocks = (int) (10 * Math.random()) + 4;
	Block block;
	boolean blockInserted = false;
	int loopsTaken = 0;
	for (int i = 0; i < numberOfBlocks; i++) {
	    block = new Block();
	    int blockType = (int) (4 * Math.random());
	    switch (blockType) {
	    case 0:
	    case 1:
		block.setTypeWithID(maze.solver.utilities.Constants.BLOCK_HORIZONTAL);
		break;
	    case 2:
		block.setTypeWithID(maze.solver.utilities.Constants.BLOCK_VERTICAL_SMALL);
		break;
	    case 3:
		block.setTypeWithID(maze.solver.utilities.Constants.BLOCK_VERTICAL_BIG);
		break;
	    }
	    blockInserted = false;
	    loopsTaken = 0;
	    while (!blockInserted && loopsTaken < MAX_LOOPS) {
		// System.out.println("blockType:"+);
		int blockPositionY = -1, blockPositionX = -1;
		switch (blockType) {
		case 0:
		case 1:
		    blockPositionY = (int) (5 * Math.random());
		    blockPositionX = (int) (6 * Math.random());
		    if (!horizontalBlockBesideMouse) {
			if (blockPositionX == 2)
			    break;
		    }
		    if (board[blockPositionX][blockPositionY].getType() == maze.solver.utilities.Constants.BLOCK_GAP) {
			if (board[blockPositionX][blockPositionY + 1].getType() == maze.solver.utilities.Constants.BLOCK_GAP) {
			    blockInserted = true;
			    block.setPivotPosition(new int[] { blockPositionX,
				    blockPositionY });
			    board[blockPositionX][blockPositionY] = new Block(
				    block.getType(), block.getPivotPosition(),
				    block.id);
			    board[blockPositionX][blockPositionY + 1] = new Block(
				    block.getType(), block.getPivotPosition(),
				    block.id);

			}
		    }
		    break;
		case 2:
		    blockPositionX = (int) (5 * Math.random());
		    blockPositionY = (int) (6 * Math.random());
		    if (board[blockPositionX][blockPositionY].getType() == maze.solver.utilities.Constants.BLOCK_GAP) {
			if (board[blockPositionX + 1][blockPositionY].getType() == maze.solver.utilities.Constants.BLOCK_GAP) {
			    blockInserted = true;
			    block.setPivotPosition(new int[] { blockPositionX,
				    blockPositionY });
			    board[blockPositionX][blockPositionY] = new Block(
				    block.getType(), block.getPivotPosition(),
				    block.id);
			    board[blockPositionX + 1][blockPositionY] = new Block(
				    block.getType(), block.getPivotPosition(),
				    block.id);
			}
		    }

		    break;
		case 3:
		    blockPositionX = (int) (4 * Math.random());
		    blockPositionY = (int) (6 * Math.random());
		    if (board[blockPositionX][blockPositionY].getType() == maze.solver.utilities.Constants.BLOCK_GAP) {
			if (board[blockPositionX + 1][blockPositionY].getType() == maze.solver.utilities.Constants.BLOCK_GAP) {
			    if (board[blockPositionX + 2][blockPositionY]
				    .getType() == maze.solver.utilities.Constants.BLOCK_GAP) {
				blockInserted = true;
				block.setPivotPosition(new int[] {
					blockPositionX, blockPositionY });
				board[blockPositionX][blockPositionY] = new Block(
					block.getType(),
					block.getPivotPosition(), block.id);
				board[blockPositionX + 1][blockPositionY] = new Block(
					block.getType(),
					block.getPivotPosition(), block.id);
				board[blockPositionX + 2][blockPositionY] = new Block(
					block.getType(),
					block.getPivotPosition(), block.id);

			    }
			}
		    }
		    break;
		}
		loopsTaken++;
	    }
	}
    }

    /**
     * Print board
     * 
     * @return String representation of the board
     */
    public String printBoard() {
	Block block;
	Object value;
	int currentBlockNumber = -1;
	int blockNumber = 1;
	String boardString = "================================================";
	boardString += "\n| ";
	Hashtable names = new Hashtable();
	for (int i = 0; i < 6; i++) {
	    for (int j = 0; j < 6; j++) {
		block = board[i][j];
		switch (block.getType()) {
		case maze.solver.utilities.Constants.BLOCK_GAP:
		    boardString += "Gap" + "  |  ";
		    break;
		case maze.solver.utilities.Constants.BLOCK_MOUSE:
		    boardString += "Rex" + "  |  ";
		    break;
		default:
		    value = names.get(block.getPivotPosition()[0] + "_"
			    + block.getPivotPosition()[1]);
		    if (value == null) {
			names.put(
				block.getPivotPosition()[0] + "_"
					+ block.getPivotPosition()[1],
				blockNumber);
			blockNumber++;
		    }
		    currentBlockNumber = (int) names.get(block
			    .getPivotPosition()[0]
			    + "_"
			    + block.getPivotPosition()[1]);
		    if (currentBlockNumber < 10)
			boardString += currentBlockNumber + ""
				+ currentBlockNumber + "" + currentBlockNumber
				+ "  |  ";
		    else
			boardString += ("" + currentBlockNumber + ""
				+ currentBlockNumber + "  |  ").substring(1);
		}
	    }
	    boardString += "\n| ";
	}
	boardString = boardString.substring(0, boardString.length() - 2);
	boardString += "================================================";
	return boardString;
    }

    /**
     * @return board
     */
    public Block[][] getBoard() {
	return board;
    }
}