package MineSweeper;

/**
 * MineSweeper.MineSweeperDriver class to run the Minesweeper grade.
 *
 * @author Noah Sullivan
 */
public class MineSweeperDriver {

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
