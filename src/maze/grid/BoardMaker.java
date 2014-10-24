package maze.grid;

public class BoardMaker {
	Block[][] board = new Block[6][6];
	public static final int MAX_LOOPS = 1000;// TODO

	public BoardMaker() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)
				board[i][j] = new Block(-1, 0, null);
	}

	/**
	 * responsible for generating random grid
	 */
	public void GenGrid() {
		// int numberOfBlocks = (int) (10 * Math.random()) + 4;
		int numberOfBlocks = 1;

		Block block;
		boolean blockInserted = false;
		int loopsTaken = 0;
		for (int i = 0; i < numberOfBlocks; i++) {
			block = new Block();
			int blockType = (int) (4 * Math.random());
			switch (blockType) {
			case 0 | 1:
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
			while (!blockInserted && loopsTaken < MAX_LOOPS) {
				System.out.println("in here while blockinserted");
				int blockPositionY = -1, blockPositionX = -1;
				switch (blockType) {
				case 0 | 1:
					blockPositionY = (int) (5 * Math.random());
					blockPositionX = (int) (6 * Math.random());
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
		String boardString = "";
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				block = board[i][j];
				switch (block.getType()) {
				case maze.utilities.Constants.BLOCK_GAP:
					boardString += -1 + " ";
					break;
				default:
					boardString += block.getPivotPosition()[0] + "_"
							+ block.getPivotPosition()[1] + " ";
				}
			}
			boardString += "\n";
		}
		return boardString;
	}
}
