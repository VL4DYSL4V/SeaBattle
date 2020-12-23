package game.util;

import game.entity.Coordinates;
import game.entity.FieldDimension;
import game.entity.Ship;
import game.entity.battle.Battle;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Deprecated
public final class Printer {

    private final FieldDimension fieldDimension;

    public Printer(FieldDimension fieldDimension) {
        this.fieldDimension = fieldDimension;
    }

    public void printPretty(Battle battle){
        String[][] field1 = createField(fieldDimension.getHeight(), fieldDimension.getWidth());
        String[][] field2 = createField(fieldDimension.getHeight(), fieldDimension.getWidth());

        fillField(field1, battle.getFirstPlayerShips(), Collections.EMPTY_LIST);
        fillField(field2, battle.getSecondPlayerShips(), Collections.EMPTY_LIST);

        printPretty(field1, field2);
    }

    public void printPretty(Collection<Ship> player1ships, Collection<Ship> player2ships,
                                   Collection<Coordinates> usedCoordinates1,
                                   Collection<Coordinates> usedCoordinates2) {
        String[][] field1 = createField(fieldDimension.getHeight(), fieldDimension.getWidth());
        String[][] field2 = createField(fieldDimension.getHeight(), fieldDimension.getWidth());

        fillField(field1, player1ships, usedCoordinates1);
        fillField(field2, player2ships, usedCoordinates2);

        printPretty(field1, field2);
    }

    private void printPretty(String[][] field1, String[][] field2){
        StringBuilder sb = new StringBuilder(210);
        System.out.println("First: \t\t\t\t\t\t\t\t\t\t Second: ");
        for (int i = 0; i < Math.min(field1.length, field2.length); i++) {
            sb.append("\n");
            sb.append(Arrays.toString(field1[i]));
            sb.append("\t\t|\t\t");
            sb.append(Arrays.toString(field2[i]));
        }
        System.out.println(sb);
    }

    private void fillField(String[][] field, Collection<Ship> playerShips,
                                        Collection<Coordinates> usedCoordinates){
        usedCoordinates.forEach(coordinates -> field[coordinates.getY()][coordinates.getX()] = " ");
        playerShips.forEach((ship) -> {
            if(!ship.isDestroyed()) {
                Collection<Coordinates> occupiedCoordinates = ship.getOccupiedCoordinates();
                occupiedCoordinates.forEach(currCoords ->
                        field[currCoords.getY()][currCoords.getX()] = "+");
                Collection<Coordinates> damagedCoordinates = ship.getDamagedCoordinates();
                damagedCoordinates.forEach(coordinates -> field[coordinates.getY()][coordinates.getX()] = "@");
            }
        });
    }

    private String[][] createField(int amountOfInnerArrays, int innerArrayLength){
        String[][] out = new String[amountOfInnerArrays][innerArrayLength];
        for (int i = 0; i < out.length; i++) {
            for (int j = 0; j < out[0].length; j++) {
                out[i][j] = "-";
            }
        }
        return out;
    }
}
