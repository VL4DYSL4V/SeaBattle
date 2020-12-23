package game.entity.battle;

import com.sun.istack.internal.Nullable;
import game.entity.FieldDimension;
import game.entity.Ship;
import game.enums.Level;
import game.enums.Turn;
import game.moveStrategy.AdvancedServerMoveStrategy;
import game.moveStrategy.MoveStrategy;
import game.moveStrategy.SimpleServerMoveStrategy;

import java.util.Collection;
import java.util.Objects;

public final class BattleContext {

    private Collection<Ship> firstPlayerShips;
    private Collection<Ship> secondPlayerShips;
    private MoveStrategy moveStrategy;
    private Turn turn = Turn.PLAYER_1;

    private BattleContext(Collection<Ship> firstPlayerShips, Collection<Ship> secondPlayerShips,
                          MoveStrategy moveStrategy) {
        this.firstPlayerShips = firstPlayerShips;
        this.secondPlayerShips = secondPlayerShips;
        this.moveStrategy = moveStrategy;
    }

    public static BattleContext clientServerContext(Collection<Ship> clientShips,
                                                    Collection<Ship> serverShips,
                                                    Level level, FieldDimension fieldDimension) {
        return new BattleContext(clientShips, serverShips,
                createStrategy(level, clientShips, fieldDimension));
    }

    private static MoveStrategy createStrategy(Level level, Collection<Ship> clientShips, FieldDimension fieldDimension) {
        if (level == Level.SIMPLE) {
            return new SimpleServerMoveStrategy(fieldDimension);
        }
        return new AdvancedServerMoveStrategy(clientShips, fieldDimension);
    }

    public Collection<Ship> getFirstPlayerShips() {
        return firstPlayerShips;
    }

    public Collection<Ship> getSecondPlayerShips() {
        return secondPlayerShips;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    @Nullable
    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public boolean gameIsOver() {
        return firstPlayerShips.isEmpty() || secondPlayerShips.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleContext that = (BattleContext) o;
        return  Objects.equals(firstPlayerShips, that.firstPlayerShips) &&
                Objects.equals(secondPlayerShips, that.secondPlayerShips) &&
                turn == that.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPlayerShips, secondPlayerShips, turn);
    }

    @Override
    public String toString() {
        return "BattleContext{" +
                "firstPlayerShips=" + firstPlayerShips +
                ", secondPlayerShips=" + secondPlayerShips +
                ", moveStrategy=" + moveStrategy +
                ", turn=" + turn +
                '}';
    }
}
