package game.util;

import game.entity.Coordinates;
import game.entity.FieldDimension;
import game.entity.Ship;
import game.enums.Placement;

import javax.annotation.Nullable;
import java.util.*;

public final class CoordinateCalculator {

    private CoordinateCalculator(){}

    public static Coordinates simpleCoordinatesCalculation(Integer maxDesiredX, Integer maxDesiredY) {
        Random random = new Random();
        return new Coordinates(random.nextInt(maxDesiredX + 1),
                random.nextInt(maxDesiredY + 1));
    }

    public static Coordinates advancedCoordinatesCalculation(Collection<Coordinates> redundantCoords, FieldDimension fieldDimension) {
        Coordinates coordinates;
        int chance = new Random().nextInt(4);
        switch (chance) {
            case 1:
                coordinates = generateLeftBottomCorner(fieldDimension);
                while (redundantCoords.contains(coordinates)) {
                    coordinates = walkRightAndUp(coordinates, fieldDimension.getMinX(), fieldDimension);
                    if (isRightUpperCorner(coordinates, fieldDimension)) {
                        return coordinates;
                    }
                }
                break;
            case 2:
                coordinates = generateRightUpperCorner(fieldDimension);
                while (redundantCoords.contains(coordinates)) {
                    coordinates = walkLeftAndDown(coordinates, fieldDimension.getMaxX(), fieldDimension);
                    if (isLeftBottomCorner(coordinates, fieldDimension)) {
                        return coordinates;
                    }
                }
                break;
            case 3:
                coordinates = generateRightBottomCorner(fieldDimension);
                while (redundantCoords.contains(coordinates)) {
                    coordinates = walkLeftAndUp(coordinates, fieldDimension.getMaxX(), fieldDimension);
                    if (isLeftUpperCorner(coordinates, fieldDimension)) {
                        return coordinates;
                    }
                }
                break;
            default:
                coordinates = generateLeftUpperCorner(fieldDimension);
                while (redundantCoords.contains(coordinates)) {
                    coordinates = walkRightAndDown(coordinates, fieldDimension.getMinX(), fieldDimension);
                    if (isRightBottomCorner(coordinates, fieldDimension)) {
                        return coordinates;
                    }
                }
                break;
        }
        return coordinates;
    }

    public static Coordinates walkRightAndDown(Coordinates from, Integer where2StartNewRowX, FieldDimension fieldDimension) {
        if (from.getX() < fieldDimension.getMaxX()) {
            return walkRight(from);
        } else if (from.getY() < fieldDimension.getMaxY()) {
            return new Coordinates(where2StartNewRowX, from.getY() + 1);
        } else {
            throw new IllegalStateException(from.toString());
        }
    }

    public static Coordinates walkLeftAndDown(Coordinates from, Integer where2StartNewRowX, FieldDimension fieldDimension) {
        if (from.getX() > fieldDimension.getMinX()) {
            return walkLeft(from);
        } else if (from.getY() < fieldDimension.getMaxY()) {
            return new Coordinates(where2StartNewRowX, from.getY() + 1);
        } else {
            throw new IllegalStateException(from.toString());
        }
    }

    public static Coordinates walkLeftAndUp(Coordinates from, Integer where2StartNewRowX, FieldDimension fieldDimension) {
        if (from.getX() > fieldDimension.getMinX()) {
            return walkLeft(from);
        } else if (from.getY() > fieldDimension.getMinY()) {
            return new Coordinates(where2StartNewRowX, from.getY() - 1);
        } else {
            throw new IllegalStateException(from.toString());
        }
    }

    public static Coordinates walkRightAndUp(Coordinates from, Integer where2StartNewRowX, FieldDimension fieldDimension) {
        if (from.getX() < fieldDimension.getMaxX()) {
            return walkRight(from);
        } else if (from.getY() > fieldDimension.getMinY()) {
            return new Coordinates(where2StartNewRowX, from.getY() - 1);
        } else {
            throw new IllegalStateException(from.toString());
        }
    }

    public static Collection<Coordinates> calculateCoordsAround(Ship ship, final FieldDimension fieldDimension) {
        Collection<Coordinates> out;
        if (ship.getPlacement() == Placement.HORIZONTAL) {
            out = calculateCoordsAroundHorizontal(ship.getLeftUpperCornerCoordinates(), ship.getDeckAmount());
        } else {
            out = calculateCoordsAroundVertical(ship.getLeftUpperCornerCoordinates(), ship.getDeckAmount());
        }
        out.removeIf(c -> inInvalidPosition(c, fieldDimension));
        return out;
    }

    private static Collection<Coordinates> calculateCoordsAroundHorizontal(Coordinates leftCornerCoordinates, int deckAmount) {
        Collection<Coordinates> out = new HashSet<>();
        int count = deckAmount + 2;
        for (int i = 0; i < count; i++) {
            out.add(new Coordinates(leftCornerCoordinates.getX() + i - 1,
                    leftCornerCoordinates.getY() - 1));
            out.add(new Coordinates(leftCornerCoordinates.getX() + i - 1,
                    leftCornerCoordinates.getY() + 1));
        }
        out.add(new Coordinates(leftCornerCoordinates.getX() - 1, leftCornerCoordinates.getY()));
        out.add(new Coordinates(leftCornerCoordinates.getX() + deckAmount, leftCornerCoordinates.getY()));
        return out;
    }

    private static Collection<Coordinates> calculateCoordsAroundVertical(Coordinates leftUpperCornerCoordinates, int deckAmount) {
        Collection<Coordinates> out = new HashSet<>();
        int count = deckAmount + 2;
        for (int j = 0; j < count; j++) {
            out.add(new Coordinates(leftUpperCornerCoordinates.getX() - 1,
                    leftUpperCornerCoordinates.getY() + j - 1));
            out.add(new Coordinates(leftUpperCornerCoordinates.getX() + 1,
                    leftUpperCornerCoordinates.getY() + j - 1));
        }
        out.add(new Coordinates(leftUpperCornerCoordinates.getX(), leftUpperCornerCoordinates.getY() - 1));
        out.add(new Coordinates(leftUpperCornerCoordinates.getX(), leftUpperCornerCoordinates.getY() + deckAmount));
        return out;
    }

    @Nullable
    public static Coordinates calculateLeftCornerCoordinates(Collection<Coordinates> unavailableCoordinates,
                                                             Ship ship, FieldDimension fieldDimension) {
        Coordinates coordinates = null;
        Ship proxy = new Ship(ship);
        for (int x = fieldDimension.getMinX(); x <= maxLeftCornerX(proxy.getPlacement(), proxy.getDeckAmount(), fieldDimension); x++) {
            for (int y = fieldDimension.getMinY(); y <= maxLeftCornerY(proxy.getPlacement(), proxy.getDeckAmount(), fieldDimension); y++) {
                coordinates = new Coordinates(x, y);
                proxy.setLeftUpperCornerCoordinates(coordinates);
                Collection<Coordinates> proxyAllOccupied = proxy.getOccupiedCoordinates();
                boolean coordsAreOk = true;
                for (Coordinates c : proxyAllOccupied) {
                    if (unavailableCoordinates.contains(c)) {
                        coordsAreOk = false;
                        break;
                    }
                }
                if (coordsAreOk) {
                    return coordinates;
                }
            }
        }
        return coordinates;
    }

    public static ArrayList<Coordinates> calculateNextPossibleUseful(Coordinates firstDamaged,
                                                                     Coordinates lastDamaged, FieldDimension fieldDimension) {
        if(!is_SWEN_Neighbour(lastDamaged, firstDamaged)){
            throw new IllegalArgumentException("last damaged and first damaged must be SWEN-neighbours!");
        }
        Coordinates nextUsefulFirst = null;
        Coordinates nextUsefulSecond = null;
        if (lastDamaged.getX() == firstDamaged.getX()) {
            if (lastDamaged.getY() < firstDamaged.getY()) {
                if (!isOnUpperEdge(lastDamaged, fieldDimension)) {
                    nextUsefulFirst = walkUp(lastDamaged);
                }
                if (!isOnBottomEdge(firstDamaged, fieldDimension)) {
                    nextUsefulSecond = walkDown(firstDamaged);
                }
            } else {
                if (!isOnBottomEdge(lastDamaged, fieldDimension)) {
                    nextUsefulFirst = walkDown(lastDamaged);
                }
                if (!isOnUpperEdge(firstDamaged, fieldDimension)) {
                    nextUsefulSecond = walkUp(firstDamaged);
                }
            }
        } else if (lastDamaged.getY() == firstDamaged.getY()) {
            if (lastDamaged.getX() < firstDamaged.getX()) {
                if (!isOnLeftEdge(lastDamaged, fieldDimension)) {
                    nextUsefulFirst = walkLeft(lastDamaged);
                }
                if (!isOnRightEdge(firstDamaged, fieldDimension)) {
                    nextUsefulSecond = walkRight(firstDamaged);
                }
            } else {
                if (!isOnRightEdge(lastDamaged, fieldDimension)) {
                    nextUsefulFirst = walkRight(lastDamaged);
                }
                if (!isOnLeftEdge(firstDamaged, fieldDimension)) {
                    nextUsefulSecond = walkLeft(firstDamaged);
                }
            }
        }
        ArrayList<Coordinates> out = new ArrayList<>(2);
        if (nextUsefulFirst != null) {
            out.add(nextUsefulFirst);
        }
        if (nextUsefulSecond != null) {
            out.add(nextUsefulSecond);
        }
        return out;
    }

    public static Coordinates walkRight(Coordinates from) {
        return new Coordinates(from.getX() + 1, from.getY());
    }

    public static Coordinates walkLeft(Coordinates from) {
        return new Coordinates(from.getX() - 1, from.getY());
    }

    public static Coordinates walkUp(Coordinates from) {
        return new Coordinates(from.getX(), from.getY() - 1);
    }

    public static Coordinates walkDown(Coordinates from) {
        return new Coordinates(from.getX(), from.getY() + 1);
    }

    public static ArrayList<Coordinates> generateSWENneighbours(Coordinates coordinates, final FieldDimension fieldDimension) {
        ArrayList<Coordinates> out = new ArrayList<>(6);
        out.add(new Coordinates(coordinates.getX(), coordinates.getY() - 1));
        out.add(new Coordinates(coordinates.getX() + 1, coordinates.getY()));
        out.add(new Coordinates(coordinates.getX(), coordinates.getY() + 1));
        out.add(new Coordinates(coordinates.getX() - 1, coordinates.getY()));
        out.removeIf(c -> inInvalidPosition(c, fieldDimension));
        return out;
    }

    public static Coordinates generateLeftUpperCorner(FieldDimension fieldDimension) {
        return new Coordinates(fieldDimension.getMinX(), fieldDimension.getMinY());
    }

    public static Coordinates generateRightBottomCorner(FieldDimension fieldDimension) {
        return new Coordinates(fieldDimension.getMaxX(), fieldDimension.getMaxY());
    }

    public static Coordinates generateRightUpperCorner(FieldDimension fieldDimension) {
        return new Coordinates(fieldDimension.getMaxX(),fieldDimension.getMinY());
    }

    public static Coordinates generateLeftBottomCorner(FieldDimension fieldDimension) {
        return new Coordinates(fieldDimension.getMinX(), fieldDimension.getMaxY());
    }

    public static Integer maxLeftCornerX(Placement placement, Integer deckAmount, FieldDimension fieldDimension) {
        return (placement == Placement.HORIZONTAL)
                ? fieldDimension.getMaxX() - deckAmount + 1
                : fieldDimension.getMaxX();
    }

    public static Integer maxLeftCornerY(Placement placement, Integer deckAmount, FieldDimension fieldDimension) {
        return (placement == Placement.HORIZONTAL)
                ? fieldDimension.getMaxY()
                : fieldDimension.getMaxY() - deckAmount + 1;
    }

    public static boolean isLeftUpperCorner(Coordinates coordinates, FieldDimension fieldDimension) {
        return Objects.equals(coordinates, generateLeftUpperCorner(fieldDimension));
    }

    public static boolean isRightUpperCorner(Coordinates coordinates, FieldDimension fieldDimension) {
        return Objects.equals(coordinates, generateRightUpperCorner(fieldDimension));
    }

    public static boolean isLeftBottomCorner(Coordinates coordinates, FieldDimension fieldDimension) {
        return Objects.equals(coordinates, generateLeftBottomCorner(fieldDimension));
    }

    public static boolean isRightBottomCorner(Coordinates coordinates, FieldDimension fieldDimension) {
        return Objects.equals(coordinates, generateRightBottomCorner(fieldDimension));
    }

    public static boolean isOnLeftEdge(Coordinates coordinates, FieldDimension fieldDimension) {
        return coordinates.getX() == fieldDimension.getMinX();
    }

    public static boolean isOnRightEdge(Coordinates coordinates, FieldDimension fieldDimension) {
        return coordinates.getX() == fieldDimension.getMaxX();
    }

    public static boolean isOnUpperEdge(Coordinates coordinates, FieldDimension fieldDimension) {
        return coordinates.getY() == fieldDimension.getMinY();
    }

    public static boolean isOnBottomEdge(Coordinates coordinates, FieldDimension fieldDimension) {
        return coordinates.getY() == fieldDimension.getMaxY();
    }

    public static boolean inInvalidPosition(Coordinates coords, FieldDimension fieldDimension) {
        return coords.getX() < fieldDimension.getMinX() || coords.getX() > fieldDimension.getMaxX()
                || coords.getY() < fieldDimension.getMinY() || coords.getY() > fieldDimension.getMaxY();
    }

    public static boolean is_SWEN_Neighbour(Coordinates toWhat, Coordinates whatToTest){
        return walkDown(toWhat).equals(whatToTest) || walkLeft(toWhat).equals(whatToTest)
                || walkRight(toWhat).equals(whatToTest) || walkUp(toWhat).equals(whatToTest);
    }
}

