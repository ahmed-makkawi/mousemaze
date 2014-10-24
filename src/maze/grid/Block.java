/**
 * 
 */
package maze.grid;

/**
 * Class represents the Block and the attributes it needs to represent each
 * block, each block contains if its empty or not, its full size, the type of
 * the block either vertical, horizontal or the mouse and the block pivot.
 * 
 * @author ahmed
 */
public class Block {

	private int size;
	private int type;
	private int[] pivotPosition;

	public Block(int type, int size, int[] pivotPosition) {
		this.type = type;
		this.size = size;
		this.pivotPosition = pivotPosition;
	}

	/**
	 * @return empty or not
	 */
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
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

}
