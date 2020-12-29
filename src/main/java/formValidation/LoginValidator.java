package formValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public final class LoginValidator
        implements ConstraintValidator<LoginConstraint, String> {

    private static final Pattern userNamePattern = Pattern.compile("[a-zA-z[0-9][_]]+");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userNamePattern.matcher(s).matches() && s.length() <= 30 && s.length() > 0;
    }

}
