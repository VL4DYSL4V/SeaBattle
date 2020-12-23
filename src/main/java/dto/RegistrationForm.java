package dto;

import formValidation.PasswordConstraint;
import formValidation.signUpValidation.FieldValueMatch;
import formValidation.LoginConstraint;

import java.util.Objects;

@FieldValueMatch(field = "password",
        fieldMatch = "repeatedPassword",
        message = "passwords don't match!")
public final class RegistrationForm {

    @LoginConstraint(message = "login can contain letters, numbers and '_'")
    private String login;

    @PasswordConstraint(message = "password length must be 4 or more")
    private String password;
    private String repeatedPassword;
    private String email;

    public RegistrationForm() {
    }

    public RegistrationForm(String login, String password, String repeatedPassword, String email) {
        this.login = login;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.email = email;
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

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationForm that = (RegistrationForm) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(repeatedPassword, that.repeatedPassword) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, repeatedPassword, email);
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "name='" + login + '\'' +
                ", password='" + password + '\'' +
                ", repeatedPassword='" + repeatedPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
