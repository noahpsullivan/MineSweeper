package MineSweeper;

/**
 * Possible game statuses after uncovering a square
 *
 * @author Noah Sullivan
 */
public enum Status {
    /**
     * Returned if no mines are uncovered/win conditions are not met -- game continues
     */
    OK,
    /**
     * Returned if win conditions are met -- game ends
     */
    WIN,
    /**
     * Returned if a mine is uncovered -- game ends
     */
    MINE,
    /**
     * Returned if user quits game -- game ends
     */
    QUIT
}
