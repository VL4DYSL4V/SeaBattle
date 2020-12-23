package dto;

import formValidation.PasswordConstraint;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public final class SignInForm {

    @NotBlank(message = "enter login")
    private String login;
    @PasswordConstraint(message = "enter password")
    private String password;

    public SignInForm(){}

    public SignInForm(String login, String password) {
        this.login = login;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInForm that = (SignInForm) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "SignInForm{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
