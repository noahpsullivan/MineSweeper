abstract class Square {

    private String element = "x";
    private boolean flagged = false;
    private boolean uncovered = false;

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isUncovered() {
        return uncovered;
    }

    public void flagSquare() {
        if (!isFlagged()) {
            setElement("f");
            this.flagged = true;
        } else {
            setElement("x");
            this.flagged = false;
        }
    }

    public void setUncovered() {
        this.uncovered = true;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String toString() {
        ;
    }

    public abstract boolean uncover();

    public abstract boolean isMine();

}
