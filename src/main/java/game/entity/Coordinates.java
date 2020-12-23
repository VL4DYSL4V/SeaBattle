package game.entity;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Coordinates {

    private static final Pattern stringPattern = Pattern.compile("-?\\d+ - -?\\d+");
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

//    public static Coordinates parse(String s) {
//        if (s == null || !stringPattern.matcher(s).matches()) {
//            throw new IllegalArgumentException("Invalid coordinate format");
//        }
//        String[] coords = s.split(" - ");
//        return new Coordinates(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "ShipCoordinates {\n" +
                "\tleftCornerX = " + x +
                ", leftCornerY = " + y +
                "\n}";
    }
}
