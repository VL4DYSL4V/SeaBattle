package game.entity;

import java.util.Objects;

public final class FieldDimension{

    private final int minX;
    private final int maxX;

    private final int minY;
    private final int maxY;

    private final int width;
    private final int height;

    public FieldDimension(int width, int height) {
        this.width = width;
        this.height = height;
        this.minX = 0;
        this.maxX = width - 1;
        this.minY = 0;
        this.maxY = height - 1;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
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
        FieldDimension that = (FieldDimension) o;
        return minX == that.minX &&
                maxX == that.maxX &&
                minY == that.minY &&
                maxY == that.maxY &&
                width == that.width &&
                height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minX, maxX, minY, maxY, width, height);
    }

    @Override
    public String toString() {
        return "FieldDimension{" +
                "minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}