package game.entity.battle;

import com.sun.istack.internal.Nullable;
import game.entity.Coordinates;
import game.entity.FieldDimension;
import game.entity.Ship;
import game.enums.FiringResult;
import game.enums.Level;
import game.enums.Turn;
import game.exception.GameOverException;
import game.moveStrategy.MoveStrategy;
import game.service.FieldGenerator;

import java.util.Collection;
import java.util.Map;

public final class Battle{

    private BattleContext battleContext;
    private BattleStatistics battleStatistics = new BattleStatistics();

    private Battle(BattleContext battleContext) {
        this.battleContext = battleContext;
    }

    public static Battle generateClientServerBattle(Level level) {
        FieldGenerator fieldGenerator = new FieldGenerator();
        Collection<Ship> clientShips = fieldGenerator.createShipsOnStandardField();
        Collection<Ship> serverShips = fieldGenerator.createShipsOnStandardField();
        FieldDimension fieldDimension = new FieldDimension(10, 10);
        return new Battle(BattleContext.clientServerContext(clientShips, serverShips, level, fieldDimension));
    }

    public FiringResult shootAtFirstPlayer(Coordinates coordinates) throws GameOverException {
        if (!battleContext.gameIsOver()) {
            FiringResult firingResult;
            if (coordinates != null) {
                firingResult = fireAtFirst(coordinates);
            } else {
                firingResult = FiringResult.MISSED;
            }
            battleStatistics.updateStatisticsSecond(firingResult);
            changeTurn(firingResult);
            return firingResult;
        } else {
            throw new GameOverException("Game over!");
        }
    }

    public FiringResult shootAtSecondPlayer(Coordinates coordinates) throws GameOverException {
        if (!battleContext.gameIsOver()) {
            FiringResult firingResult;
            if (coordinates != null) {
                firingResult = fireAtSecond(coordinates);
            } else {
                firingResult = FiringResult.MISSED;
            }
            battleStatistics.updateStatisticsFirst(firingResult);
            changeTurn(firingResult);
            return firingResult;
        } else {
            throw new GameOverException("Game over!");
        }
    }

    private void changeTurn(FiringResult firingResult) {
        if (firingResult == FiringResult.MISSED) {
            if (battleContext.getTurn() == Turn.PLAYER_1) {
                battleContext.setTurn(Turn.PLAYER_2);
            } else if (battleContext.getTurn() == Turn.PLAYER_2){
                battleContext.setTurn(Turn.PLAYER_1);
            }
        }
    }

    private FiringResult fireAtFirst(Coordinates whereTo) {
        return fireAt(whereTo, battleContext.getFirstPlayerShips());
    }

    private FiringResult fireAtSecond(Coordinates whereTo) {
        return fireAt(whereTo, battleContext.getSecondPlayerShips());
    }

    private FiringResult fireAt(Coordinates whereTo, Collection<Ship> opponentShips) {
        for (Ship ship : opponentShips) {
            Collection<Coordinates> occupied = ship.getOccupiedCoordinates();
            if (occupied.contains(whereTo)) {
                ship.getDamagedCoordinates().add(whereTo);
                if (ship.isDestroyed()) {
                    opponentShips.remove(ship);
                    return FiringResult.DESTROYED;
                }
                return FiringResult.AIMED;
            }
        }
        return FiringResult.MISSED;
    }

    @Nullable
    public MoveStrategy getServerMoveStrategy() {
        return battleContext.getMoveStrategy();
    }

    public Map<FiringResult, Integer> getStatisticsFirst() {
        return battleStatistics.getStatisticsFirst();
    }

    public Map<FiringResult, Integer> getStatisticsSecond() {
        return battleStatistics.getStatisticsSecond();
    }

    public Turn getTurn() {
        return battleContext.getTurn();
    }

    public Collection<Ship> getFirstPlayerShips() {
        return battleContext.getFirstPlayerShips();
    }

    public Collection<Ship> getSecondPlayerShips() {
        return battleContext.getSecondPlayerShips();
    }

    public boolean gameOver(){
        return battleContext.gameIsOver();
    }
}
