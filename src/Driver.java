import MineSweeper.Minesweeper;

/*
EXTRA CREDIT CONSIDERATION
I have added the following functionality that I would like evaluated for extra credit:
    ** Allow the user to restart the game (3 points)
    ** Use recursion and implement a true game of minesweeper (15 points)
 */

/**
 * Driver class to run the Minesweeper grade.
 *
 * @author Noah Sullivan
 */
public class Driver {

    /**
     * Creates {@link Minesweeper#minesweeper() minesweeper} object and runs game.
     *
     * @param args No arguments
     */
    public static void main(String[] args) {
        Minesweeper gameInstance = new Minesweeper();
        gameInstance.minesweeper();
    }
}
