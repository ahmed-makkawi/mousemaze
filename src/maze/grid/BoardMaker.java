package maze.grid;

public class BoardMaker {

    public BoardMaker() {

    }

    /**
     * responsible for generating random grid
     */
    private void GenGrid()
    {
	int numberOfTiles = (int) ( 10 * Math.random()) + 4;
	
	for (int i = 0; i < numberOfTiles; i++)
	{
	    int blockType = (int) (Math.random());
	    
	    int blockPositionX  = (int) (5 * Math.random());
	    int blockPositionY  = (int) (5 * Math.random());
	    
	    
	    
	}
	
	
    }
}
