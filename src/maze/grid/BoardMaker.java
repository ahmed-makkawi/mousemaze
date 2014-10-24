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
	    
	    
	    int tilePositionX  = (int) (5 * Math.random());
	    int tilePositionY  = (int) (5 * Math.random());
	    
	    
	    
	}
	
	
    }
}
