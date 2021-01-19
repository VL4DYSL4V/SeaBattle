package entity;

import converter.RankToStringConverter;
import enums.Rank;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "sb_users")
@Entity
public final class User {

    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "user_password")
    private String password;
    @Column(name = "email")
    private String email;

    @Column(name = "user_rank")
    @Convert(converter = RankToStringConverter.class)
    private Rank rank;
    private int scores;
    @OneToOne
    @JoinColumn(name = "gameStatistics_id")
    private GameStatistics gameStatistics;

    public User() {
    }

    private User(String login, String password, String email, Rank rank, int scores, GameStatistics gameStatistics){
        this.login = login;
        this.password = password;
        this.email = email;
        this.rank = rank;
        this.scores = 0;
        this.gameStatistics = gameStatistics;
    }

    public static User createSeamanApprentice(String login, String password, String email){
        return new User(login, password, email,
                Rank.SEAMAN_APPRENTICE, 0, new GameStatistics());
    }

    public void promote(){
        int scoresDifference = scores - rank.getPromotionScores();
        while(scoresDifference >= 0){
            Rank nextRank = Rank.next(rank);
            if(nextRank != null){
                this.rank = nextRank;
            }
            scores = scoresDifference;
            scoresDifference -= rank.getPromotionScores();
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank marineRank) {
        this.rank = marineRank;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistics(GameStatistics gameStatistics) {
        this.gameStatistics = gameStatistics;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                rank == user.rank &&
                Objects.equals(gameStatistics, user.gameStatistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, rank, gameStatistics);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", rank=" + rank +
                ", gameStatistics=" + gameStatistics +
                '}';
    }
}
