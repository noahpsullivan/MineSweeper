import java.util.Scanner;

public class Minesweeper {
    public void runGame(){
        Grid gameBoard = new Grid(12, 10, 10);
        Status gameState = Status.OK;
        while(gameState == Status.OK){
            gameState = Status.MINE;
        }
    }

    public ReturnInput getInput(){
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        String[] pieces = input.replaceAll("\\W", "").split("");
        if(pieces.length != 1 && pieces.length != 3){
            return new ReturnInput();
        }
        char action = pieces[0].charAt(0);
        if(!Character.isAlphabetic(action)){
            return new ReturnInput();
        }
    }

    private class ReturnInput{
        private char action;
        private int row;
        private int col;

        public ReturnInput(char action, int row, int col){
            this.action = action;
            this.row = row;
            this.col = col;
        }

        public ReturnInput(char action){
            this(action, -1, -1);
        }

        public ReturnInput(){
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