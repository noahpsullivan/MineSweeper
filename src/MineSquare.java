public class MineSquare extends Square {
//    public boolean uncover() {
//        ;
//    }

    @Override
    public boolean uncover() {
        return false;
    }

    @Override
    public boolean isMine() {
        return true;
    }

}
