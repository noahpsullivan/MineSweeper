public class MineSquare extends Square {

    @Override
    public boolean uncover() {
        return false;
    }

    @Override
    public boolean isMine() {
        return true;
    }

}
