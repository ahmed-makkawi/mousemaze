/**
 * 
 */
package maze.grid;

/**
 * Class represents the Block and the attributes it needs to represent
 * each block, each block contains if its empty or not, its full size,
 * the type of the block either vertical, horizontal or the mouse and
 * the block pivot.
 * 
 * @author ahmed
 */
public class Block {

    private boolean 	empty;
    private int 	size;
    private int		type;
    private int [] 	pivotPosition;
    /**
     * @return empty or not
     */
    public boolean isEmpty() {
        return empty;
    }
    /**
     * @param empty the empty to set
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    /**
     * @param size the size to set
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
     * @param type the type to set
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
     * @param pivotPosition the PivotPosition to set
     */
    public void setPivotPosition(int[] rootPosition) {
        this.pivotPosition = rootPosition;
    }

    

}
