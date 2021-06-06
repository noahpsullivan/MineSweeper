import java.util.Random;

public class Grid {

    private Square[][] grid;
    private int width;
    private int height;
    private int numSquaresUncovered = 0;

    public Grid(int width, int height, int numMines) {

        this.width = width;
        this.height = height;

        // Create grid with given height and width
        grid = new Square[height][width];

        // Randomly populate grid with mines
        // Create Random object
        Random rand = new Random();

        // Places requested number of Mines
        // Increment argument not passed to prevent automatic incrementation if mine is not present
        for (int i = 0; i < numMines; ) {
            // Uses Random object to generate random grid position
            int randRow = rand.nextInt(height);
            int randCol = rand.nextInt(width);

            // Checks to make sure there isn't already a mine at the selected spot
            if (!(grid[randRow][randCol] instanceof MineSquare)) {
                // If not already a mine puts down mine and increments for loop
                grid[randRow][randCol] = new MineSquare();
                i++;
            }
        }

        // Iterates over grid and places number squares where mines are not present
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!(grid[row][col] instanceof MineSquare)) {
                    grid[row][col] = new NumberSquare(getNeighbors(row, col), row, col);
                }
            }
        }
    }

    public int getNeighbors(int row, int col) {
        int[] range = {-1, 0, 1};
        int numMines = 0;
        for (int rangeRow : range) {
            for (int rangeCol : range) {
                // Keeps getNeighbors from checking its own square
                if (rangeRow != 0 || rangeCol != 0) {
                    int checkX = row + rangeRow;
                    int checkY = col + rangeCol;
                    if (((0 <= checkX) && (checkX < height)) && (0 <= checkY) && (checkY < width)) {
                        if (grid[checkX][checkY] instanceof MineSquare) {
                            numMines++;
                        }
                    }
                }
            }
        }
        return numMines;
    }

    public Status uncoverSquare(int row, int col) {
        return Status.OK;
    }

    public void exposeMines() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Square square = grid[row][col];
                if (square instanceof MineSquare) {
                    if(square.isFlagged()){
                        square.flagSquare();
                    }
                    grid[row][col].uncover();
                }
            }
        }
    }

    public void flagSquare(int row, int col) {
        // Call Square.flagSquare. Plan and simple
        grid[row][col].flagSquare();
    }

    @Override
    public String toString() {
        // Uses StringBuilder to create grid for output
        StringBuilder sb = new StringBuilder();

        // Iterates over spots
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // Appends string representation of spot
                sb.append(grid[row][col]);
            }
            // Appends system newline
            sb.append(System.lineSeparator());
        }
        // Returns StringBuilder as String
        return sb.toString();
    }

}
