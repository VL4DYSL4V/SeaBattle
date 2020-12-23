package game.moveStrategy;

import game.entity.Coordinates;
import game.entity.FieldDimension;
import game.entity.Ship;
import game.util.CoordinateCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public final class AdvancedServerMoveStrategy implements MoveStrategy{

    private Collection<Ship> clientShips;
    private Coordinates firstDamaged;
    private HashSet<Coordinates> unprofitableCoordinates = new HashSet<>(50);
    private ArrayList<Coordinates> currUsefulCoords;
    private final FieldDimension fieldDimension;

    public AdvancedServerMoveStrategy(Collection<Ship> clientShips, FieldDimension fieldDimension){
        this.clientShips = Collections.unmodifiableCollection(clientShips);
        this.fieldDimension = fieldDimension;
    }

    @Override
    public Coordinates getCoordinates() {
        if (firstDamaged == null) {
            return noOneIsDamagedAlgorithm();
        }
        if (currUsefulCoords == null) {
            currUsefulCoords = CoordinateCalculator.generateSWENneighbours(firstDamaged, fieldDimension);
        }
        return oneDeckIsDamagedAlgorithm();
    }

    private Coordinates noOneIsDamagedAlgorithm() {
        Coordinates coordinates;
        for (int i = 0; i < 10; i++) {
            coordinates = CoordinateCalculator.simpleCoordinatesCalculation(fieldDimension.getMaxX(),
                    fieldDimension.getMaxY());
            if (!unprofitableCoordinates.contains(coordinates)) {
                initialAimingAttempt(coordinates);
                return coordinates;
            }
        }
        coordinates = CoordinateCalculator.advancedCoordinatesCalculation(unprofitableCoordinates, fieldDimension);
        initialAimingAttempt(coordinates);
        return coordinates;
    }

    private Coordinates oneDeckIsDamagedAlgorithm() {
        Coordinates possibleUseful = currUsefulCoords.get(0);
        unprofitableCoordinates.add(possibleUseful);
        currUsefulCoords.remove(possibleUseful);
        for (Ship clientShip : clientShips) {
            if (clientShip.getOccupiedCoordinates().contains(possibleUseful)) {
                Integer damagedDeckAmount = clientShip.getDamagedCoordinates().size();
                if (damagedDeckAmount.equals(clientShip.getDeckAmount() - 1)) {
                    Collection<Coordinates> unavailable = CoordinateCalculator.calculateCoordsAround(clientShip, fieldDimension);
                    unprofitableCoordinates.addAll(unavailable);
                    currUsefulCoords = null;
                    firstDamaged = null;
                } else {
                    currUsefulCoords = CoordinateCalculator.calculateNextPossibleUseful(firstDamaged, possibleUseful, fieldDimension);
                }
                break;
            }
        }
        return possibleUseful;
    }

    private void initialAimingAttempt(Coordinates whereTo) {
        unprofitableCoordinates.add(whereTo);
        for (Ship clientShip : clientShips) {
            if (clientShip.getOccupiedCoordinates().contains(whereTo)) {
                if (clientShip.getDeckAmount().equals(1)) {
                    Collection<Coordinates> unavailable = CoordinateCalculator.calculateCoordsAround(clientShip, fieldDimension);
                    unprofitableCoordinates.addAll(unavailable);
                } else {
                    firstDamaged = whereTo;
                }
                break;
            }
        }
    }
}
