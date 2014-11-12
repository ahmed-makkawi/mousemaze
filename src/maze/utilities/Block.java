package maze.utilities;

/**
 * Class represents the Block and the attributes it needs to represent each
 * block, each block contains if its empty or not, its full size, the type of
 * the block either vertical, horizontal or the mouse and the block pivot.
 * 
 */
public class Block {

	private int type;
	private int[] pivotPosition = new int[2];
	public static int idCounter = 0;
	public int id;

	public Block() {
		type = Constants.BLOCK_GAP;
	}

	public Block(Block b) {
		type = b.getType();
		if (b.pivotPosition != null) {
			pivotPosition[0] = b.pivotPosition[0];
			pivotPosition[1] = b.pivotPosition[1];
		} else
			pivotPosition = null;

		id = b.id;
	}

	public void setBlock(int type, int[] pivotPosition, int id) {
		this.type = type;
		if (pivotPosition == null)
			this.pivotPosition = null;
		else
			this.pivotPosition = pivotPosition.clone();
		this.id = id;
	}

	public Block(int type, int[] pivotPosition, int id) {
		this.type = type;
		if (pivotPosition == null)
			this.pivotPosition = null;
		else
			this.pivotPosition = pivotPosition.clone();
		this.id = id;
	}

	public Block(int type, int[] pivotPosition, boolean id) {
		this.type = type;
		if (pivotPosition == null)
			this.pivotPosition = null;
		else
			this.pivotPosition = pivotPosition.clone();
		this.id = idCounter;
		idCounter++;

	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	public void setTypeWithID(int type) {
		this.type = type;
		this.id = idCounter;
		idCounter++;
	}

	public void setType(int type, int id) {
		this.type = type;
		this.id = id;
	}

	/**
	 * @return the PivotPosition
	 */
	public int[] getPivotPosition() {
		return pivotPosition;
	}

	/**
	 * @param pivotPosition
	 *            the PivotPosition to set
	 */
	public void setPivotPosition(int[] rootPosition) {
		if (rootPosition == null)
			this.pivotPosition = null;
		else
			this.pivotPosition = rootPosition.clone();
	}

	public int getPivotID() {
		return 6 * pivotPosition[0] + pivotPosition[1];
	}

	@Override
	public boolean equals(Object o) {
		return this.id == ((Block) o).id;
	}

}
