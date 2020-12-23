package formValidation.signUpValidation;

import dto.RegistrationForm;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public final class FieldValueMatchValidator
        implements ConstraintValidator<FieldValueMatch, RegistrationForm> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldValueMatch fieldValueMatch) {
        this.field = fieldValueMatch.field();
        this.fieldMatch = fieldValueMatch.fieldMatch();
    }

    @Override
    public boolean isValid(RegistrationForm registrationForm,
                           ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(registrationForm).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(registrationForm).getPropertyValue(fieldMatch);
        return fieldMatchValue != null
                && fieldValue != null
                && Objects.equals(fieldValue, fieldMatchValue);
    }

}
