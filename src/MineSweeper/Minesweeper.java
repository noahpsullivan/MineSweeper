package MineSweeper;

import java.util.Scanner;

/**
 * Base class for MineSweeper game. Contains methods used to execute all aspects of the game.
 *
 * @author Noah Sullivan
 */
public class Minesweeper {

    /**
     * Responsible for running Minesweeper game. Creates {@link Grid} object, gets input from the user, and executes
     * <code>Grid</code> methods in response to user input. Upon change in <code>gameState</code> executes response
     * according to new state.
     */
    public void runGame() {
        int width = 12;
        int height = 10;
        int numMines = 10;
        Grid gameBoard = new Grid(width, height, numMines);
        Status gameState = Status.OK;
        System.out.println(gameBoard);
        while (gameState == Status.OK) {

            // Input loop of doom
            ReturnInput input = new ReturnInput();
            try {
                System.out.println("Options: (U)ncover r c,   (F)lag r c,   (Q)uit");
                input = getInput();
                checkInput(input, width, height);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input");
            }

            // Executes validated user commands
            if (input.action == 'U') {
                gameState = gameBoard.uncoverSquare(input.row, input.col);
            } else if (input.action == 'F') {
                gameBoard.flagSquare(input.row, input.col);
            } else {
                gameState = Status.QUIT;
            }

            if (gameState == Status.OK) {
                System.out.println(gameBoard);
            }
        }

        // Responds to game state change
        if (gameState == Status.WIN) {
            System.out.println("YOU'RE WINNER !"); // Big Rigs was ahead of its time
        } else if (gameState == Status.MINE) {
            gameBoard.exposeMines();
            System.out.println(gameBoard);
            System.out.println("YOU HAVE DIED OF DYSENTERY (and a mine)"); // I love The Oregon Trail
        } else if (gameState == Status.QUIT) {
            System.out.println("That's it -- RAGE QUIT"); // It's actually safer to mine at night
        }
    }

    /**
     * <p>
     * Gets input from the user. Input is validated for format and data type but not content. Content is initially
     * received as a string and is then split into an array of strings before being transferred to a {@link ReturnInput}
     * object. The first argument must be a letter and will become a char. Any subsequent characters in the first
     * argument will be discarded. The second and third arguments are ints representing the row and column,
     * respectively. All arguments must be separated by a space. If improper input is given an exception will be thrown.
     * Proper format is demonstrated below.
     * </p>
     *
     * <blockquote>
     * <p>
     * Formatting:
     * <br>
     * <code>[char action] [int row] [int column]</code>
     * </p>
     *
     * <p>
     * Valid Example:
     * <br>
     * <code>U 2 0</code>
     * </p>
     *
     * <p>
     * Valid Example:
     * <br>
     * <code>Fq 6 9</code>
     * <br>
     * <i>Note: only the "F" in "Fq" is processed</i>
     * </p>
     *
     * <p>
     * Invalid Example:
     * <br>
     * <code>U 29</code>
     * <br>
     * <i>Note: Lack of spacing/correct number of arguments will cause an exception to be thrown</i>
     * </p>
     *
     * <p>
     * Invalid Example:
     * <br>
     * <code>1 2 F</code>
     * <br>
     * <i>Note: In this case the first argument would not be accepted and would result in an exception being thrown</i>
     * </p>
     * </blockquote>
     *
     * @return <code>ReturnInput</code> object containing a type-validated user request
     *
     * @throws IllegalArgumentException if user input does not match the designated format
     */
    public ReturnInput getInput() throws IllegalArgumentException {
        IllegalArgumentException badInput = new IllegalArgumentException();
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        String[] pieces = input.toUpperCase().split("\\W");
        int length = pieces.length;
        try {
            char action = pieces[0].charAt(0);
            if (length == 1) {
                return new ReturnInput(action);
            } else if (length == 3) {
                int row = Integer.parseInt(pieces[1]);
                int col = Integer.parseInt(pieces[2]);
                return new ReturnInput(action, row, col);
            } else {
                throw badInput;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw badInput;
        }
    }

    /**
     * Validates user input after initial acquisition by {@link #getInput() getInput}. Verifies action requested by user
     * against possible actions and verifies that coordinates are on game board using {@link #onGrid(int, int, int, int)
     * onGrid} if necessary. If conditions are not met an exception is thrown.
     *
     * @param input  ReturnInput object containing <code>action</code> as a <code>char</code> and <code>row</code> and
     *               <code>col</code> as ints
     * @param width  int holding game board width
     * @param height int holding game board height
     *
     * @throws IllegalArgumentException if user input has invalid action/coordinates
     */
    public void checkInput(ReturnInput input, int width, int height) throws IllegalArgumentException {
        IllegalArgumentException badInput = new IllegalArgumentException();
        if (input.hasCoords) {
            if ((input.action != 'F' && input.action != 'U') || !onGrid(input.row, input.col, width, height)) {
                throw badInput;
            }
        } else {
            if (input.action != 'Q') {
                throw badInput;
            }
        }
    }

    /**
     * Determines if a given set of coordinates is within the game board.
     *
     * @param row    int holding input row
     * @param col    int holding input column
     * @param width  int holding game board width
     * @param height int holding game board height
     *
     * @return returns boolean that is <code>true</code> if coordinates are within game board. Returns
     * <code>false</code> if not.
     */
    public boolean onGrid(int row, int col, int width, int height) {
        return ((0 <= row && row < height) && (0 <= col && col < width));
    }

    /**
     * Internal class used to easily move user input for {@link Minesweeper}.
     */
    private static class ReturnInput {
        final private char action;
        final private boolean hasCoords;
        final private int row;
        final private int col;

        /**
         * Primary constructor for <code>ReturnInput</code>
         *
         * @param action char holding user-requested action
         * @param row    int holding input row
         * @param col    int holding input column
         */
        private ReturnInput(char action, int row, int col) {
            this.action = action;
            this.row = row;
            this.col = col;
            this.hasCoords = col != -1 || row != -1;
        }

        /**
         * Secondary constructor to be used if user does not provide coordinates
         *
         * @param action char holding user-requested action
         */
        private ReturnInput(char action) {
            this(action, -1, -1);
        }

        /**
         * Empty constructor, used for variable initialization
         */
        private ReturnInput(){
            this(Character.MIN_VALUE);
        }
    }
}