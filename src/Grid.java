public class Grid {

    private Square[][] grid;
    private int width;
    private int height;
    private int numMines;
    private int numSquaresUncovered = 0;

    public Grid(int width, int height, int numMines){
        this.width = width;
        this.height = height;
        this.numMines = numMines;
    }

    public int getNeighbors(int row, int column){
        return 0;
    }

    public Status uncoverSquare(int row, int column){
        return Status.OK;
    }

}
