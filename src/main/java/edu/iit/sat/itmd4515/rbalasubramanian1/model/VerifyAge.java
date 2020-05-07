/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 *The VerifyAge method is used to validate the age entered by the user
 * and check if its above or below the required value.
 * The isValid method is used to return the age if the age entered is valid.
 * If not, the error message defined in the age class will be displayed.
 * @author raghul
 */
public class VerifyAge implements ConstraintValidator<Age, Integer> {

    /**
     * the variable is used to get the age entered by the user
     * and can later be used in isValid method to check if the age is valid or not
     */
    protected long minAge;
    @Override
    public void initialize(Age ageValue) {
        this.minAge = ageValue.value();
    }
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        // null values are not allowed. User has to enter age.
        if ( age == null ) {
            return false;
        }
        
        return age >= minAge;
    }
}