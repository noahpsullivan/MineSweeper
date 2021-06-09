import java.util.Scanner;

public class Minesweeper {

    public void runGame() {
        int width = 12;
        int height = 10;
        int numMines = 10;
        Grid gameBoard = new Grid(width, height, numMines);
        Status gameState = Status.OK;
        System.out.println(gameBoard);
        while (gameState == Status.OK) {

            // Input loop of doom
            boolean inputValid = false;
            ReturnInput input = null;
            while (!inputValid) {
                try {
                    System.out.println("Options: (U)ncover r c,   (F)lag r c,   (Q)uit");
                    input = getInput();
                    inputValid = checkInput(input, width, height);
                } catch (IllegalArgumentException e) {
                    System.out.println("Oopsie Woopsie! You made a Fucky Wucky!");
                }
            }

            // Executes validated user commands
            if (input.action == 'U') {
                gameState = gameBoard.uncoverSquare(input.row, input.col);
            } else if (input.action == 'F') {
                gameBoard.flagSquare(input.row, input.col);
            } else {
                gameState = Status.QUIT;
            }

            if (gameState == Status.OK){
                System.out.println(gameBoard);
            }
        }

        // Responds to game state change
        if (gameState == Status.WIN) {
            System.out.println("YOU'RE WINNER !"); // Big Rigs was ahead of its time
        }
        else if (gameState == Status.MINE) {
            gameBoard.exposeMines();
            System.out.println(gameBoard);
            System.out.println("YOU HAVE DIED OF DYSENTERY (and a mine)"); // I love The Oregon Trail
        }
        else if (gameState == Status.QUIT) {
            System.out.println("That's it -- RAGE QUIT"); // It's actually safer to mine at night
        }
    }

    private ReturnInput getInput() throws IllegalArgumentException {
        IllegalArgumentException badInput = new IllegalArgumentException();
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        String[] pieces = input.toUpperCase().split("\\W");
        int length = pieces.length;
        try {
            char action = pieces[0].charAt(0);
            if (length == 1) {
                return new ReturnInput(action, false);
            } else if (length == 3) {
                int row = Integer.parseInt(pieces[1]);
                int col = Integer.parseInt(pieces[2]);
                return new ReturnInput(action, true, row, col);
            } else {
                throw badInput;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw badInput;
        }
    }

    private boolean checkInput(ReturnInput input, int width, int height) {
        IllegalArgumentException badInput = new IllegalArgumentException();
        if (input.hasCoords) {
            if ((input.action == 'F' || input.action == 'U') && onGrid(input.row, input.col, width, height)) {
                return true;
            } else {
                throw badInput;
            }
        } else {
            if (input.action == 'Q') {
                return true;
            } else {
                throw badInput;
            }
        }
    }

    private boolean onGrid(int row, int col, int width, int height) {
        return ((0 <= row && row < height) && (0 <= col && col < width));
    }

    private static class ReturnInput {
        final private char action;
        final private boolean hasCoords;
        final private int row;
        final private int col;

        private ReturnInput(char action, boolean hasCoords, int row, int col) {
            this.action = action;
            this.hasCoords = hasCoords;
            this.row = row;
            this.col = col;
        }

        private ReturnInput(char action, boolean hasCoords) {
            this(action, hasCoords, -1, -1);
        }
    }
}