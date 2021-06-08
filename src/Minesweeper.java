import java.util.Scanner;

public class Minesweeper {

    public void runGame() {
        Grid gameBoard = new Grid(12, 10, 10);
        Status gameState = Status.OK;
        while (gameState == Status.OK) {
            gameState = Status.MINE;
        }
    }

    public ReturnInput getInput() throws IllegalArgumentException {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        String[] pieces = input.split("\\W");
        int length = pieces.length;
        try{
            char action = pieces[0].charAt(0);
            if(length == 1){
                return new ReturnInput(action);
            }
            else if(length == 3){
                int row = Integer.parseInt(pieces[1]);
                int col = Integer.parseInt(pieces[2]);
                return new ReturnInput(action, row, col);
            }
            else {
                throw IllegalArgumentException;
            }
        }
    }

    private class ReturnInput {
        private char action;
        private int row;
        private int col;

        public ReturnInput(char action, int row, int col) {
            this.action = action;
            this.row = row;
            this.col = col;
        }

        public ReturnInput(char action) {
            this(action, -1, -1);
        }

        public ReturnInput() {
            this(Character.MIN_VALUE, -1, -1);
        }

        public char getAction() {
            return action;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}