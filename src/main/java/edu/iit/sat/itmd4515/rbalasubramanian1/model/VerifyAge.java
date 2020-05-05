/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 *
 * @author raghul
 */
public class VerifyAge implements ConstraintValidator<Age, Integer> {
    protected long minAge;
    @Override
    public void initialize(Age ageValue) {
        this.minAge = ageValue.value();
    }
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if ( age == null ) {
            return true;
        }
        
        return age >= minAge;
    }
}