package MineSweeper;

abstract class Square {

    private String element;
    private boolean flagged;
    private boolean uncovered;

    public Square(String element, boolean flagged, boolean uncovered) {
        this.element = element;
        this.flagged = flagged;
        this.uncovered = uncovered;
    }

    public Square() {
        this("x", false, false);
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isUncovered() {
        return uncovered;
    }

    public void flagSquare() {
        // Keeps already uncovered squares from being flagged/mislabeled as flagged
        if (isUncovered()){
            this.flagged = false;
        }

        // Flags un-flagged square
        else if (!isFlagged()) {
            setElement("f");
            this.flagged = true;

        // De-flags flagged square
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

    @Override
    public String toString() {
        return String.format("%4s", element);
    }

    public abstract boolean uncover();

    public abstract boolean isMine();

}