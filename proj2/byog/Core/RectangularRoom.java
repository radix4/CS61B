package byog.Core;

public class RectangularRoom {
    private int width;
    private int height;
    private Position bottomLeft;
    private Position topRight;


    public RectangularRoom(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Position getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Position bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public void setTopRight(Position topRight) {
        this.topRight = topRight;
    }

    public Position getTopRight() {
        return topRight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
