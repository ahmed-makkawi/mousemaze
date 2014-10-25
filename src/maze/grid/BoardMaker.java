package maze.grid;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import maze.utilities.Constants;

public class BoardMaker {
	Block[][] board = new Block[6][6];
	public static final int MAX_LOOPS = 100;// TODO
	public static boolean horizontalBlockBesideMouse = false;

	public BoardMaker() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)
				board[i][j] = new Block(-1, null);
		board[2][0] = new Block(Constants.BLOCK_MOUSE, new int[] { 2, 0 });
		board[2][1] = new Block(Constants.BLOCK_MOUSE, new int[] { 2, 0 });

	}

	public int numberOfBlocks = -1;

	/**
	 * responsible for generating random grid
	 */
	public void GenGrid() {
		numberOfBlocks = (int) (10 * Math.random()) + 4;
		// int numberOfBlocks = 1;

		Block block;
		boolean blockInserted = false;
		int loopsTaken = 0;
		for (int i = 0; i < numberOfBlocks; i++) {
			block = new Block();
			int blockType = (int) (4 * Math.random());
			switch (blockType) {
			case 0:
			case 1:
				block.setType(maze.utilities.Constants.BLOCK_HORIZONTAL);
				break;
			case 2:
				block.setType(maze.utilities.Constants.BLOCK_VERTICAL_SMALL);
				break;
			case 3:
				block.setType(maze.utilities.Constants.BLOCK_VERTICAL_BIG);
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
					if (board[blockPositionX][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
						if (board[blockPositionX][blockPositionY + 1].getType() == maze.utilities.Constants.BLOCK_GAP) {
							blockInserted = true;
							block.setPivotPosition(new int[] { blockPositionX,
									blockPositionY });
							board[blockPositionX][blockPositionY] = block;
							board[blockPositionX][blockPositionY + 1] = block;

						}
					}
					break;
				case 2:
					blockPositionX = (int) (5 * Math.random());
					blockPositionY = (int) (6 * Math.random());
					if (board[blockPositionX][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
						if (board[blockPositionX + 1][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
							blockInserted = true;
							block.setPivotPosition(new int[] { blockPositionX,
									blockPositionY });
							board[blockPositionX][blockPositionY] = block;
							board[blockPositionX + 1][blockPositionY] = block;
						}
					}

					break;
				case 3:
					blockPositionX = (int) (4 * Math.random());
					blockPositionY = (int) (6 * Math.random());
					if (board[blockPositionX][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
						if (board[blockPositionX + 1][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
							if (board[blockPositionX + 2][blockPositionY]
									.getType() == maze.utilities.Constants.BLOCK_GAP) {
								blockInserted = true;
								block.setPivotPosition(new int[] {
										blockPositionX, blockPositionY });
								board[blockPositionX][blockPositionY] = block;
								board[blockPositionX + 1][blockPositionY] = block;
								board[blockPositionX + 2][blockPositionY] = block;

							}
						}
					}
					break;
				}
				loopsTaken++;// TODO
			}
		}
	}

	public String printBoard() {
		Block block;
		Object value;
		int currentBlockNumber = -1;
		int blockNumber = 0;
		String boardString = "================================================";
		boardString += "\n| ";
		Hashtable names = new Hashtable();
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
						boardString += "B0" + currentBlockNumber + "  |  ";
					else
						boardString += "B" + currentBlockNumber + "  |  ";

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
}
