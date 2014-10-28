/**
 * 
 */
package maze.grid;

import maze.utilities.Constants;

/**
 * Class represents the Block and the attributes it needs to represent each
 * block, each block contains if its empty or not, its full size, the type of
 * the block either vertical, horizontal or the mouse and the block pivot.
 * 
 * @author ahmed
 */
public class Block {

	private int type;
	private int[] pivotPosition;
	public static int idCounter = 0;
	public int id;

	public Block() {
		type = Constants.BLOCK_GAP;
	}

	public Block(int type, int[] pivotPosition) {
		this.type = type;
		this.pivotPosition = pivotPosition;
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
		this.pivotPosition = rootPosition;
	}

	public int getPivotID() {
		return 6 * pivotPosition[0] + pivotPosition[1];
	}

}
