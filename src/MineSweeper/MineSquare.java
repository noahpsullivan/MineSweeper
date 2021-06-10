package MineSweeper;

/**
 * <code>MineSquare</code> is the class that represents mined squares by extending {@link Square}. It provides methods
 * to uncover the <code>Square</code> and determine if the <code>Square</code> is a mine.
 *
 * @author Noah Sullivan
 */
public class MineSquare extends Square {

    /**
     * Attempts to uncover square, if able to uncover sets display <code>element</code> accordingly. Cannot be uncovered
     * if {@link #isFlagged() isFlagged} or {@link #isUncovered() isUncovered} are true.
     *
     * @return boolean, <code>true</code> if uncover is successful, <code>false</code> if not
     */
    @Override
    public boolean uncover() {
        if (!isFlagged() && !isUncovered()) {
            setUncovered();
            setElement("*");
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMine() {
        return true;
    }

}
