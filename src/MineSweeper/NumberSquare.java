package MineSweeper;

/**
 * <code>NumberSquare</code> is the class that represents non-mined squares by extending {@link Square}. It provides
 * methods to uncover the <code>Square</code>, determine the number of neighboring mines, and determining if the
 * <code>Square</code> is a mine.
 *
 * @author Noah Sullivan
 */
public class NumberSquare extends Square {

    private final int neighborMines;

    /**
     * Constructor for <code>NumberSquare</code> taking neighboring mines, row number, and row column
     *
     * @param neighborMines int containing number of neighboring mines
     */
    public NumberSquare(int neighborMines) {
        this.neighborMines = neighborMines;
    }

    /**
     * <p>
     * Attempts to uncover square, if able to uncover sets display <code>element</code> accordingly. Cannot be uncovered
     * if {@link #isFlagged() isFlagged} or {@link #isUncovered() isUncovered} are true.
     * </p>
     * <p>
     * If there are no neighbor mines then the display <code>element</code> is set to "<code>_</code>"; otherwise it is
     * set to the number of neighbor mines. Number of neighbor mines is retrieved using the {@link #getNeighborMines()
     * getNeightborMines} method.
     * </p>
     *
     * @return boolean, <code>true</code> if uncover is successful, <code>false</code> if not
     */
    @Override
    public boolean uncover() {
        if (!isFlagged() && !isUncovered()) {
            setUncovered();
            if (getNeighborMines() == 0) {
                setElement("_");
            } else {
                setElement(Integer.toString(getNeighborMines()));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for <code>neighborMines</code>
     *
     * @return int equal to <code>neighborMines</code>
     */
    public int getNeighborMines() {
        return neighborMines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMine() {
        return false;
    }
}