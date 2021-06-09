import java.util.Random;

public class Grid {

    private Square[][] grid;
    private int width;
    private int height;
    private int numMines;
    private int numSquaresUncovered;

    public Grid(int width, int height, int numMines) {

        this.width = width;
        this.height = height;
        this.numMines = numMines;
        this.numSquaresUncovered = 0;

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
        Square square = grid[row][col];
        if (square.isMine()) {
            return Status.MINE;
        } else {
            // Need to typecast to NumberSquare because getNeighborMines is not an abstract method in Square
            NumberSquare numSquare = (NumberSquare) square;
            int number = numSquare.getNeighborMines();
            if (number == 0) {
                if(grid[row][col].uncover()){
                    numSquaresUncovered++;
                }
                int[] range = {-2, -1, 0, 1, 2};
                for (int rangeRow : range) {
                    for (int rangeCol : range) {
                        // Keeps it from checking its own square
                        if (rangeRow != 0 || rangeCol != 0) {
                            int checkX = row + rangeRow;
                            int checkY = col + rangeCol;
                            if (((0 <= checkX) && (checkX < height)) && (0 <= checkY) && (checkY < width)) {
                                if (!(grid[checkX][checkY] instanceof MineSquare)) {
                                    if(grid[checkX][checkY].uncover()){
                                        numSquaresUncovered++;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (number == 1) {
                if(grid[row][col].uncover()){
                    numSquaresUncovered++;
                }
                int[] range = {-1, 0, 1};
                for (int rangeRow : range) {
                    for (int rangeCol : range) {
                        // Keeps it from checking its own square
                        if (rangeRow != 0 || rangeCol != 0) {
                            int checkX = row + rangeRow;
                            int checkY = col + rangeCol;
                            if (((0 <= checkX) && (checkX < height)) && (0 <= checkY) && (checkY < width)) {
                                if (!(grid[checkX][checkY] instanceof MineSquare)) {
                                    if (grid[checkX][checkY].uncover()){
                                        numSquaresUncovered++;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if(square.uncover()) {
                    numSquaresUncovered++;
                }
            }
        }
        if(numSquaresUncovered == ((width*height)-numMines)){
            return Status.WIN;
        }
        else{
            return Status.OK;
        }
    }

    public void exposeMines() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Square square = grid[row][col];
                if (square instanceof MineSquare) {
                    if (square.isFlagged()) {
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

        // Buffer before column numbers
        sb.append(String.format("%4s", ""));

        // Adds column numbers
        for (int col = 0; col < width; col++){
            sb.append(String.format("%4d", col));
        }
        sb.append(System.lineSeparator());

        // Iterates over spots
        for (int row = 0; row < height; row++) {
            // Adds row number
            sb.append(String.format("%4d", row));
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
