import java.util.Scanner;

public class Minesweeper {
    public void runGame(){
        Grid gameBoard = new Grid(12, 10, 10);
        Status gameState = Status.OK;
        while(gameState == Status.OK){
            gameState = Status.MINE;
        }
    }

    public void getInput(){
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        input = input.replaceAll("\\s", "").toLowerCase();
        int length = input.length();
        if(length == 1 && input.equals("q")){
            System.out.println("yuh");
        }
    }
}