package formValidation.signUpValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValueMatch {

    String message();//an attribute message that returns the default
                    // key for creating error messages in case the constraint is violated
    String field();

    String fieldMatch();

    Class<?>[] groups() default { };// an attribute groups that allows the specification of validation
    // groups, to which this constraint belongs (see Chapter 5, Grouping constraints).
    // This must default to an empty array of type Class<?>.

    Class<? extends Payload>[] payload() default { };// an attribute payload that can be used by clients
    // of the Bean Validation API to assign custom payload objects to a constraint. This attribute is
    // not used by the API itself. An example for a custom payload could be the definition of a
    // severity

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldValueMatch[] value();
    }

}
