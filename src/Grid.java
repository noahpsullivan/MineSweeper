import java.util.Random;

public class Grid {

    private Square[][] grid;
    private int numSquaresUncovered = 0;

    public Grid(int width, int height, int numMines){

        // Create grid with given height and width
        grid = new Square[height][width];

        // Randomly populate grid with mines
        // Create Random object
        Random rand = new Random();

        // Places requested number of Mines
        // Increment argument not passed to prevent automatic incrementation if mine is not present
        for(int i = 0; i < numMines; ){
            // Uses Random object to generate random grid position
            int randRow = rand.nextInt(height);
            int randCol = rand.nextInt(width);

            // Checks to make sure there isn't already a mine at the selected spot
            if(!(grid[randRow][randCol] instanceof MineSquare)){
                // If not already a mine puts down mine and increments for loop
                grid[randRow][randCol] = new MineSquare();
                i++;
            }
        }

        for(int row = 0; row < height; row++){
            for (int col = 0; col < width; col++)
            {
                if(!(grid[row][col] instanceof MineSquare)){
                    grid[row][col] = new NumberSquare(5, row, col);
                }
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }

    public int getNeighbors(int row, int column){
        return 0;
    }

    public Status uncoverSquare(int row, int column){
        return Status.OK;
    }

}
