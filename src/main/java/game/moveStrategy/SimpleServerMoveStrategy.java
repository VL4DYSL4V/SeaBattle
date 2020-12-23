package game.moveStrategy;

import game.entity.Coordinates;
import game.entity.FieldDimension;
import game.util.CoordinateCalculator;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public final class SimpleServerMoveStrategy implements MoveStrategy, Serializable {

    private final Collection<Coordinates> usedCoords = new HashSet<>(50);
    private final FieldDimension fieldDimension;
    private Coordinates lastCalculated = new Coordinates(0, 0);

    private static final long serialVersionUID = 3003460519162862780L;

    public SimpleServerMoveStrategy(FieldDimension fieldDimension) {
        this.fieldDimension = fieldDimension;
    }

    @Override
    public Coordinates getCoordinates() {
        if (usedCoords.size() < 40) {
            for (int i = 0; i < 5; i++) {
                Coordinates coordinates = CoordinateCalculator.simpleCoordinatesCalculation(fieldDimension.getMaxX(),
                        fieldDimension.getMaxY());
                if (!this.usedCoords.contains(coordinates)) {
                    usedCoords.add(coordinates);
                    return coordinates;
                }
            }
        }
        while (this.usedCoords.contains(lastCalculated)) {
            lastCalculated = CoordinateCalculator.walkRightAndDown(
                    new Coordinates(lastCalculated.getX(), lastCalculated.getY()), 0, fieldDimension);
        }
        usedCoords.add(lastCalculated);
        return lastCalculated;
    }

}
