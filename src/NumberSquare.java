public class NumberSquare extends Square {

    private int neighborMines;
    private int myRow;
    private int myCol;

    public NumberSquare(int neighborMines, int myRow, int myCol){
        this.neighborMines = neighborMines;
        this.myRow = myRow;
        this.myCol = myCol;
    }

    @Override
    public boolean uncover() {
        if(!isFlagged()){
            setUncovered();
            if(neighborMines == 0){
                setElement("_");
            }
            else{
                setElement(Integer.toString(neighborMines));
            }
            return true;
        }
        else{
            return false;
        }
    }

    public int getNeighborMines(){
        return neighborMines;
    }

    @Override
    public boolean isMine() {
        return false;
    }
}