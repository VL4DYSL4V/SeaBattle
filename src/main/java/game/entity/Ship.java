package game.entity;

import game.enums.Placement;

import java.util.*;

public final class Ship{

    private Integer deckAmount;
    private Placement placement;
    private Coordinates leftUpperCornerCoordinates;
    private final Collection<Coordinates> damagedCoordinates = new HashSet<>(4);
    private Collection<Coordinates> occupiedCoordinates;

    public Ship(Ship ship){
        this.deckAmount = ship.deckAmount;
        this.placement = ship.placement;
        this.leftUpperCornerCoordinates = ship.leftUpperCornerCoordinates;
        this.occupiedCoordinates = new HashSet<>(4);
        for(Coordinates c: ship.occupiedCoordinates){
            this.occupiedCoordinates.add(new Coordinates(c.getX(), c.getY()));
        }
        for(Coordinates c: ship.damagedCoordinates){
            this.damagedCoordinates.add(new Coordinates(c.getX(), c.getY()));
        }
    }

    public Ship(Integer deckAmount, Placement placement, Coordinates leftUpperCornerCoordinates) {
        this.deckAmount = deckAmount;
        this.placement = placement;
        this.leftUpperCornerCoordinates = leftUpperCornerCoordinates;
        this.occupiedCoordinates = calculateAllOccupiedCoords();
    }

    private Collection<Coordinates> calculateAllOccupiedCoords(){
        Collection<Coordinates> coordinates = new HashSet<>(6);
        coordinates.add(this.leftUpperCornerCoordinates);
        for (int i = 1; i < this.deckAmount; i++) {
            Coordinates occupiedCoordinates;
            if (this.placement == Placement.VERTICAL) {
                occupiedCoordinates = new Coordinates(this.leftUpperCornerCoordinates.getX(), this.leftUpperCornerCoordinates.getY() + i);
            } else {
                occupiedCoordinates = new Coordinates(this.leftUpperCornerCoordinates.getX() + i, this.leftUpperCornerCoordinates.getY());
            }
            coordinates.add(occupiedCoordinates);
        }
        return coordinates;
    }

    public Integer getDeckAmount() {
        return deckAmount;
    }

    public Placement getPlacement() {
        return placement;
    }

    public Coordinates getLeftUpperCornerCoordinates() {
        return leftUpperCornerCoordinates;
    }

    public void setLeftUpperCornerCoordinates(Coordinates leftUpperCornerCoordinates) {
        if(!damagedCoordinates.isEmpty()){
            throw new IllegalStateException("you can't move damaged ship");
        }
        if(! Objects.equals(leftUpperCornerCoordinates, this.leftUpperCornerCoordinates)) {
            this.leftUpperCornerCoordinates = leftUpperCornerCoordinates;
            this.occupiedCoordinates = calculateAllOccupiedCoords();
        }
    }

    public Collection<Coordinates> getOccupiedCoordinates() {
        return Collections.unmodifiableCollection(occupiedCoordinates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return deckAmount.equals(ship.deckAmount) &&
                placement == ship.placement &&
                leftUpperCornerCoordinates.equals(ship.leftUpperCornerCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckAmount, placement, leftUpperCornerCoordinates);
    }

    @Override
    public String toString() {
        return "Ship{\n" +
                "\t deckAmount = " + deckAmount +
                ", placement = " + placement +
                ", coordinates = " + leftUpperCornerCoordinates +
                "\n}";
    }

    public Collection<Coordinates> getDamagedCoordinates() {
        return damagedCoordinates;
    }

    public boolean isDestroyed(){
        return damagedCoordinates.equals(occupiedCoordinates);
    }

}
