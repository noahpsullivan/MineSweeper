public class NumberSquare extends Square {

    private int neighborMines;
    private int myRow;
    private int myColumn;

    @Override
    public boolean uncover() {
        return false;
    }

    @Override
    public boolean isMine() {
        return false;
    }
}