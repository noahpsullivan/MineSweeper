package MineSweeper;

/**
 * <code>MineSquare</code> is the class that represents mined squares by extending {@link Square}. It provides methods
 * to uncover the <code>Square</code> and determine if the <code>Square</code> is a mine.
 */
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
