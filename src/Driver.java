import MineSweeper.Minesweeper;

/**
 * Driver class to run the Minesweeper grade.
 *
 * @author Noah Sullivan
 */
public class Driver {

    /**
     * Creates Minesweeper object and runs game.
     *
     * @param args No arguments
     */
    public static void main(String[] args) {
        Minesweeper gameInstance = new Minesweeper();
        gameInstance.runGame();
    }
}
