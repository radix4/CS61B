package byog.Core;

public class Position implements Comparable<Position>{
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /** If this is > than o, returns 1. */
    @Override
    public int compareTo(Position o) {
        if (this.x - o.getX() > 0 && this.y - o.getY() > 0) {
            return 1;
        } else if (this.x - o.getX() < 0 && this.y - o.getY() < 0) {
            return -1;
        }

        return 0;
    }
}
