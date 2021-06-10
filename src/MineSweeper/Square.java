package MineSweeper;

/**
 * Abstract class serving as a superclass to the {@link MineSquare} and {@link NumberSquare} classes. Contains getters
 * and setters for all attributes, with {@link #toString() toString} serving as the getter for a formatted
 * <code>element</code>.
 *
 * @author Noah Sullivan
 */
abstract class Square {

    private String element;
    private boolean flagged;
    private boolean uncovered;

    /**
     * Default constructor for <code>Square</code> class.
     *
     * @param element   string containing visual representation of square state
     * @param flagged   boolean describing if the square is flagged or un-flagged
     * @param uncovered boolean describing if the square is covered or uncovered
     */
    public Square(String element, boolean flagged, boolean uncovered) {
        this.element = element;
        this.flagged = flagged;
        this.uncovered = uncovered;
    }

    /**
     * Secondary constructor for <code>Square</code> class. Defaults <code>element</code> to "<code>x</code>",
     * <code>flagged</code> to <code>false</code>, and <code>uncovered</code> to <code>false</code>.
     */
    public Square() {
        this("x", false, false);
    }

    /**
     * Getter for <code>flagged</code>  attribute.
     *
     * @return boolean, <code>true</code> if flagged, <code>false</code> if not
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * Getter for <code>uncovered</code> attribute.
     *
     * @return boolean, <code>true</code> if uncovered, <code>false</code> if not
     */
    public boolean isUncovered() {
        return uncovered;
    }

    /**
     * Handles flagging and un-flagging <code>Square</code> objects. Prevents uncovered squares from being flagged,
     * un-flags flagged squares, and flags un-flagged squares. Updates <code>flagged</code> internally and
     * <code>element</code> using {@link #setElement(String) setElement} as needed.
     */
    public void flagSquare() {
        // Keeps already uncovered squares from being flagged/mislabeled as flagged
        if (isUncovered()) {
            this.flagged = false;
        }

        // Flags un-flagged square
        else if (!isFlagged()) {
            setElement("f");
            this.flagged = true;

            // De-flags flagged square
        } else {
            setElement("x");
            this.flagged = false;
        }
    }

    /**
     * Sets <code>Square</code> object attribute <code>uncovered</code> to true.
     */
    public void setUncovered() {
        this.uncovered = true;
    }

    /**
     * Sets <code>Square</code> object attribute <code>element</code> to param value.
     *
     * @param element String to set <code>element</code> value to
     */
    public void setElement(String element) {
        this.element = element;
    }

    /**
     * <p>
     * Gets a formatted string containing the {@link Square} object's <code>element</code> attribute.
     * </p>
     * <p>
     * Currently returns <code>element</code> in a right-justified string with a width of 4.
     * </p>
     *
     * @return formatted String containing <code>element</code>
     */
    @Override
    public String toString() {
        return String.format("%4s", element);
    }

    /**
     * Used by subclasses to uncover themselves, implementation varies.
     *
     * @return boolean, <code>true</code> if uncover is successful, <code>false</code> if not
     */
    public abstract boolean uncover();

    /**
     * Getter for whether or not a square is a mine.
     *
     * @return boolean, <code>true</code> if square is a mine, <code>false</code> if not.
     */
    public abstract boolean isMine();

}
