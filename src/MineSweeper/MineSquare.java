package MineSweeper;

public class MineSquare extends Square {

    @Override
    public boolean uncover() {
        if(!isFlagged() && !isUncovered()){
            setUncovered();
            setElement("*");
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isMine() {
        return true;
    }

}
