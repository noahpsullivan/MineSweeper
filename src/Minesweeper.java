import java.util.Scanner;

public class Minesweeper {
    public void runGame(){
        Grid gameBoard = new Grid(12, 10, 10);
        Status gameState = Status.OK;
        while(gameState == Status.OK){
            gameState = Status.MINE;
        }
    }

    public char getInput(){
        Scanner keyboard = new Scanner(System.in);

    }
}