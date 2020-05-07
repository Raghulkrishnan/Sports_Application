/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This is a bean validation custom validator to check if the user registered
 * is above 15 years of age - as that is the age restriction we have kept for the app.s
 * 
 * @author raghul
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {VerifyAge.class})
public @interface Age {

    /**
     *This returns a message if the user registered doesn't enter a age greater than the 
     * threshold value set.
     * @return
     */
    String message() default "Must be atleast {value} years old";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /**
     * the value method will store the value as a long type.
     * @return
     */
    long value();

    /**
     *
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        /**
         *
         * @return
         */
        Age[] value();
    }
}
