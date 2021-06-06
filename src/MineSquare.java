public class MineSquare extends Square {

    @Override
    public boolean uncover() {
        if(!isFlagged()){
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
