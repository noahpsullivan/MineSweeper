package MineSweeper;

public class NumberSquare extends Square {

    private int neighborMines;
    private int myRow;
    private int myCol;

    public NumberSquare(int neighborMines, int myRow, int myCol){
        this.neighborMines = neighborMines;
        this.myRow = myRow;
        this.myCol = myCol;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean uncover() {
        if(!isFlagged() && !isUncovered()){
            setUncovered();
            if(getNeighborMines() == 0){
                setElement("_");
            }
            else{
                setElement(Integer.toString(getNeighborMines()));
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @return
     */
    public int getNeighborMines(){
        return neighborMines;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isMine() {
        return false;
    }
}