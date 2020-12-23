package game.service;

import game.entity.Coordinates;
import game.entity.FieldDimension;
import game.entity.Ship;
import game.enums.Placement;
import game.util.CoordinateCalculator;

import java.util.*;

public final class FieldGenerator implements FieldGeneratorService {

    @Override
    public Collection<Ship> createShipsOnStandardField() {
        Collection<Ship> occupants = new HashSet<>();
        Collection<Coordinates> unavailableCoordinates = new HashSet<>();
        FieldDimension fieldDimension = new FieldDimension(10, 10);
        addShips(4, 1, occupants, unavailableCoordinates, fieldDimension);
        addShips(3, 2, occupants, unavailableCoordinates, fieldDimension);
        addShips(2, 3, occupants, unavailableCoordinates, fieldDimension);
        addShips(1, 4, occupants, unavailableCoordinates, fieldDimension);
        return occupants;
    }

    private void addShips(int deckAmount, int shipAmount,
                          Collection<Ship> whereTo, Collection<Coordinates> unavailableCoordinates,
                          FieldDimension fieldDimension) {
        for (int i = 0; i < shipAmount; i++) {
            Placement placement = calculatePlacement();
            int maxX = CoordinateCalculator.maxLeftCornerX(placement, deckAmount, fieldDimension);
            int maxY = CoordinateCalculator.maxLeftCornerY(placement, deckAmount, fieldDimension);
            Coordinates coordinates = CoordinateCalculator.simpleCoordinatesCalculation(maxX, maxY);
            Ship ship = new Ship(deckAmount, placement, coordinates);
            makeShipCoordsCorrect(ship, unavailableCoordinates, fieldDimension);
            whereTo.add(ship);
            Collection<Coordinates> occupied = CoordinateCalculator.calculateCoordsAround(ship, fieldDimension);
            unavailableCoordinates.addAll(occupied);
            unavailableCoordinates.addAll(ship.getOccupiedCoordinates());
        }
    }

    private void makeShipCoordsCorrect(Ship ship, Collection<Coordinates> unavailableCoordinates, FieldDimension fieldDimension) {
        if (unavailableCoordinates.isEmpty()) {
            return;
        }
        if (crossesUnavailableArea(ship, unavailableCoordinates)) {
            Ship proxy = new Ship(ship);
            for (int i = 0; i < 5; i++) {
                Coordinates coordinates = CoordinateCalculator.simpleCoordinatesCalculation(
                        CoordinateCalculator.maxLeftCornerX(proxy.getPlacement(), proxy.getDeckAmount(), fieldDimension),
                                CoordinateCalculator.maxLeftCornerY(proxy.getPlacement(), proxy.getDeckAmount(), fieldDimension));
                proxy.setLeftUpperCornerCoordinates(coordinates);
                if (!crossesUnavailableArea(proxy, unavailableCoordinates)) {
                    ship.setLeftUpperCornerCoordinates(coordinates);
                    return;
                }
            }
            Coordinates coordinates = CoordinateCalculator.
                    calculateLeftCornerCoordinates(
                            Collections.unmodifiableCollection(unavailableCoordinates), ship, fieldDimension);
            if(coordinates == null){
                throw new IllegalStateException("Can't place the boat");
            }
            ship.setLeftUpperCornerCoordinates(coordinates);
        }
        if (crossesUnavailableArea(ship, unavailableCoordinates)) {
            throw new IllegalStateException("Can't place the boat");
        }
    }

    private boolean crossesUnavailableArea(Ship ship, Collection<Coordinates> unavailableCoordinates) {
        Collection<Coordinates> occupiedCoords = ship.getOccupiedCoordinates();
        return occupiedCoords.stream().anyMatch(unavailableCoordinates::contains);
    }

    private Placement calculatePlacement() {
        return new Random().nextInt(100) >= 50 ? Placement.VERTICAL : Placement.HORIZONTAL;
    }
}
