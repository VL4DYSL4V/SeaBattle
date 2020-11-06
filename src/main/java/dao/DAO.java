package dao;

import entity.Pair;
import exception.NoSuchUserException;
import game.entity.battle.Battle;

@Deprecated
public interface DAO {

    boolean someoneIsWaiting();

    boolean userExists(String name);

    Pair<String> getRegisteredPair(String oneOfPair) throws NoSuchUserException;

    void addClientServerBattle(String name, Battle battle);

    void addClientClientBattle(Pair<String> users, Battle battle);

    void putInQueue(String name, Battle battle);

    void removeFromQueue(String name);

    String getWaiting();

    Battle getBattle(Pair<String> users);

    Battle getWaitingBattle(String name);

    void removeBattle(Pair<String> users);

}
