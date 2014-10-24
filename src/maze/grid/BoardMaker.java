package maze.grid;

public class BoardMaker {
	Block[][] board = new Block[6][6];

	public BoardMaker(){
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)
				board[i][j] = new Block(-1, 0, null);
	}

	/**
	 * responsible for generating random grid
	 */
	private void GenGrid() {
		int numberOfBlocks = (int) (10 * Math.random()) + 4;
		Block block;
		boolean blockInserted = false;
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
			while (!blockInserted) {
				int blockPositionY = -1, blockPositionX = -1;
				switch (blockType) {
				case 0 | 1:
					blockPositionY = (int) (5 * Math.random());
					blockPositionX = (int) (6 * Math.random());
					if (board[blockPositionX][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
						if (board[blockPositionX][blockPositionY + 1].getType() == maze.utilities.Constants.BLOCK_GAP) {
							blockInserted = true;
						}
					}
					break;
				case 2:
					blockPositionX = (int) (5 * Math.random());
					blockPositionY = (int) (6 * Math.random());
					if (board[blockPositionX][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
						if (board[blockPositionX + 1][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
							blockInserted = true;
						}
					}

					break;
				case 3:
					blockPositionX = (int) (4 * Math.random());
					blockPositionY = (int) (6 * Math.random());
					if (board[blockPositionX][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
						if (board[blockPositionX + 1][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
							if (board[blockPositionX + 2][blockPositionY].getType() == maze.utilities.Constants.BLOCK_GAP) {
							blockInserted = true;
							}
						}
					}
					break;
				}
				block.setPivotPosition(new int[] { blockPositionX,
						blockPositionY });
				board[blockPositionX][blockPositionY] = block;
			}
		}

	}
}
