package entity;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "sb_statistics")
@Entity
public final class GameStatistics {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "damaged")
    private int damaged = 0;
    @Column(name = "destroyed")
    private int destroyed = 0;
    @Column(name = "won_battles")
    private int wonBattles = 0;
    @Column(name = "lost_battles")
    private int lostBattles = 0;

    public GameStatistics() {
    }

    public int getDamaged() {
        return damaged;
    }

    public void setDamaged(int damaged) {
        this.damaged = damaged;
    }

    public int getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(int destroyed) {
        this.destroyed = destroyed;
    }

    public int getWonBattles() {
        return wonBattles;
    }

    public void setWonBattles(int wonBattles) {
        this.wonBattles = wonBattles;
    }

    public int getLostBattles() {
        return lostBattles;
    }

    public void setLostBattles(int lostBattles) {
        this.lostBattles = lostBattles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStatistics that = (GameStatistics) o;
        return id == that.id &&
                damaged == that.damaged &&
                destroyed == that.destroyed &&
                wonBattles == that.wonBattles &&
                lostBattles == that.lostBattles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, damaged, destroyed, wonBattles, lostBattles);
    }

    @Override
    public String toString() {
        return "GameStatistics{" +
                "id=" + id +
                ", damaged=" + damaged +
                ", destroyed=" + destroyed +
                ", wonBattles=" + wonBattles +
                ", lostBattles=" + lostBattles +
                '}';
    }
}