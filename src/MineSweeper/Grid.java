package MineSweeper;

import java.util.Random;

/**
 * <p>
 * Contains a two dimensional array of {@link Square} objects and the methods needed to interact with them. Many methods
 * in the <code>Grid</code> object call similar methods from the <code>Square</code> object, such as how {@link
 * #flagSquare(int, int) Grid.flagSquare} calls {@link Square#flagSquare() Square.flagSquare}.
 * </p>
 * <p>
 * The <code>Grid</code> object is created and called by {@link Minesweeper#runGame() Minesweeper.runGame} when a new
 * game is started. <code>Grid</code> object methods are called by <code>Minesweeper.runGame</code> according to user
 * input.
 * </p>
 *
 * @author Noah Sullivan
 */
public class Grid {

    private final Square[][] grid;
    private final int width;
    private final int height;
    private final int numMines;
    private int numSquaresUncovered;

    /**
     * Constructor for <code>Grid</code> class taking width, height, and number of mines.
     * <p>
     * Creates the two dimensional array <code>grid</code> and randomly populates with given number of {@link
     * MineSquare} objects. After mines are placed all other indexes are filled by {@link NumberSquare} objects. The
     * {@link NumberSquare#NumberSquare(int) neighborMines} parameter in the <code>NumberSquare</code>
     * constructor is filled using the {@link #getNeighbors(int, int) getNeighbors}, which runs independently of
     * <code>Square</code> initialization
     *
     * @param width    int representing desired grid width
     * @param height   int representing desired grid height
     * @param numMines int representing desired number of mines
     */
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
                    grid[row][col] = new NumberSquare(getNeighbors(row, col));
                }
            }
        }
    }

    /**
     * <p>
     * Gets the number of mines adjacent to the indicated grid location. Checks all grid locations adjacent to indicated
     * point, including corners. In the example below, the cell marked "<code>a</code>" represents the called grid
     * location, cells marked "<code>_</code>" represent squares that are checked by <code>getNeighbors</code>, and
     * cells marked "<code>#</code>" represent squares that are not checked by <code>getNeighbors</code>.
     * </p>
     *
     * <pre>
     *     [#][#][#][#][#]
     *     [#][_][_][_][#]
     *     [#][_][a][_][#]
     *     [#][_][_][_][#]
     *     [#][#][#][#][#]
     * </pre>
     *
     * <p>
     * Does not require a <code>Square</code> object to exist at called coordinate or in surrounding area, just checks
     * if given <code>grid</code> array element is an instance of {@link MineSquare}.
     * </p>
     *
     * @param row int of grid square row to be checked
     * @param col int of grid square column to be checked
     *
     * @return int containing number of neighboring mines
     */
    public int getNeighbors(int row, int col) {
        int[] range = {-1, 0, 1};
        int numMines = 0;
        for (int rangeRow : range) {
            for (int rangeCol : range) {
                // Keeps getNeighbors from checking its own square
                if (rangeRow != 0 || rangeCol != 0) {
                    int checkX = row + rangeRow;
                    int checkY = col + rangeCol;

                    // Makes sure it's looking on grid
                    if (((0 <= checkX) && (checkX < height)) && (0 <= checkY) && (checkY < width)) {

                        // Get 'em, boys
                        if (grid[checkX][checkY] instanceof MineSquare) {
                            numMines++;
                        }
                    }
                }
            }
        }
        return numMines;
    }

    /**
     * <p>
     * Attempts to uncover indicated {@link Square} object and then attempts to uncover adjacent <code>Square</code>
     * objects. If the adjacent <code>Square</code> object has no neighboring mines then the function is recursively
     * called on it as well. A diagram is included below, where the cell marked "<code>a</code>" is the
     * <code>Square</code> object the function is called on and the cells marked "<code>_</code>" are
     * <code>Square</code> objects checked by the function.
     * </p>
     *
     * <pre>
     *     [_][_][_]
     *     [_][a][_]
     *     [_][_][_]
     * </pre>
     *
     * <p>
     * The function is only recursively called on <code>Square</code> objects with no neighboring mines to reduce the
     * odds of uncovering all squares in one swoop. There are increased odds of completing the game immediately when
     * <code>Square</code> objects with neighboring mines are recursed upon due to the relatively low odds of one or
     * more mines being entirely insulated from the others. It is still possible to do so using the no neighbors only
     * method, but it is much less likely.
     * </p>
     *
     * @param row int of grid square row to be uncovered
     * @param col int of grid square column to be uncovered
     *
     * @return <code>Status</code> enum of uncovered square:
     * <code>Status.MINE</code> if <code>Square</code> is a mine
     * <code>Status.WIN</code> if all <code>Square</code> objects successfully uncovered
     * <code>Status.OK</code> if game continues
     */
    public Status uncoverSquare(int row, int col) {

        // Gives grid square a nice name for easy access
        Square square = grid[row][col];

        // What if mine go boom
        if (square.isMine()) {
            return Status.MINE;
        }

        // Haha jk
        else {
            // Checks if this is a square that needs uncovering
            if (square.uncover()) {
                numSquaresUncovered++;
            }

            // Range allows only squares in immediate vicinity to be checked
            int[] range = {-1, 0, 1};

            // Iterates over squares around main square
            for (int rangeRow : range) {
                for (int rangeCol : range) {

                    // Keeps it from checking its own square
                    if (rangeRow != 0 || rangeCol != 0) {

                        // Determines row/column of new square being checked
                        int checkRow = row + rangeRow;
                        int checkCol = col + rangeCol;

                        // Makes sure it's on the grid too
                        if (((0 <= checkRow) && (checkRow < height)) && (0 <= checkCol) && (checkCol < width)) {

                            // After confirming gives it a name
                            Square checkSquare = grid[checkRow][checkCol];

                            // Makes sure this new square isn't a mine
                            if (!(checkSquare instanceof MineSquare)) {

                                // It's not a mine so we can typecast it as a number
                                NumberSquare checkNumSquare = (NumberSquare) checkSquare;

                                // Sees if uncover is successful
                                if (checkNumSquare.uncover()) {
                                    numSquaresUncovered++;

                                    // If not touching any mines continues recursion
                                    if (checkNumSquare.getNeighborMines() == 0)
                                        uncoverSquare(checkRow, checkCol);
                                }
                            }
                        }
                    }
                }
            }

        }

        if (numSquaresUncovered == ((width * height) - numMines)) {
            return Status.WIN;
        } else {
            return Status.OK;
        }
    }

    /**
     * Uncovers all mines in <code>grid</code> array. Iterates over {@link Square} objects in <code>grid</code> and
     * uncovers instances of {@link MineSquare}. Flagged <code>MineSquare</code> objects are un-flagged before
     * attempting to uncover.
     */
    public void exposeMines() {

        // Iterates over grid squares
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                // Names square for easier typing
                Square square = grid[row][col];

                // Uncovers mine squares
                if (square instanceof MineSquare) {

                    // De-flags flagged mines
                    if (square.isFlagged()) {
                        square.flagSquare();
                    }
                    square.uncover();
                }
            }
        }
    }

    /**
     * Toggles flag status on {@link Square} object. Removes flag if <code>Square</code> is already flagged.
     *
     * @param row int of grid square row to be flagged
     * @param col int of grid square column to be flagged
     */
    public void flagSquare(int row, int col) {
        // Call Square.flagSquare. Plain and simple
        grid[row][col].flagSquare();
    }

    /**
     * Generates and returns a representation of the game board including row and column numbers. Representations of
     * individual mines, flags, covered, and uncovered {@link Square} objects are handled by methods in their respective
     * classes.
     *
     * @return String interpretation of current grid state
     *
     * @see Square#flagSquare()
     * @see MineSquare#uncover()
     * @see NumberSquare#uncover()
     */
    @Override
    public String toString() {
        // Uses StringBuilder to create grid for output
        StringBuilder sb = new StringBuilder();

        // Buffer before column numbers
        sb.append(String.format("%4s", ""));

        // Adds column numbers
        for (int col = 0; col < width; col++) {
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

            // Appends newline to row
            sb.append(System.lineSeparator());
        }
        // Returns StringBuilder as String
        return sb.toString();
    }

}
