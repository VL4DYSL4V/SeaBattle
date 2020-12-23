package entity;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "sea_battle_users")
@Entity
public final class User {

    @Id
    @Column(name = "name")
    private String login;
    private String password;
    private String email;

    @Transient
    private int battle_id;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getBattle_id() {
        return battle_id;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBattle_id(int battle_id) {
        this.battle_id = battle_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  battle_id == user.battle_id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, battle_id);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", battle_id=" + battle_id +
                '}';
    }
}
