package byog.Core;

import java.util.Objects;

public class Rectangular {
    private int width;
    private int height;
    private Position bottomLeft;
    private Position topRight;

    public Rectangular(){
        this(0,0,null,null);
    }


    public Rectangular(int width, int height) {
        this(width,height,null,null);
    }

    public Rectangular(int width, int height, Position bottomLeft, Position topRight) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangular that = (Rectangular) o;
        return width == that.width &&
                height == that.height &&
                Objects.equals(bottomLeft, that.bottomLeft) &&
                Objects.equals(topRight, that.topRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, bottomLeft, topRight);
    }
}
