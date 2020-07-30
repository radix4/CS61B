package byog.Core;

public class RectangularRoom {
    private int width;
    private int height;
    private Position bottomLeft;
    private Position topRight;

    public RectangularRoom(){
        this(0,0,null,null);
    }


    public RectangularRoom(int width, int height) {
        this(width,height,null,null);
    }

    public RectangularRoom(int width, int height, Position bottomLeft, Position topRight) {
        this.width = width;
        this.height = height;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
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
