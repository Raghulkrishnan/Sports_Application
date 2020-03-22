/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raghul
 */
public class TeamValidationTest {
    
    private static Validator validator;
    
    public TeamValidationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//    success validation
    @Test
    public void teamPassValidation() {
        Team goodTD = new Team("Thunders",
                "Abi",
                "312943752",
                Level.BEG,
                LocalDateTime.now());

        Set<ConstraintViolation<Team>> constraintViolations
                = validator.validate(goodTD);

        assertEquals(0, constraintViolations.size());
    }

//    @Size bean validation test
    @Test
    public void teamInvalidTeamNameValidation() {
        Team badTD = new Team("Thundersansdjfnkjdsnfiosdj fposjfisdnfnsdonfnsdlfkmkdsm",
                "Abi",
                "312943752",
                Level.BEG,
                LocalDateTime.now());

        Set<ConstraintViolation<Team>> constraintViolations
                = validator.validate(badTD);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "size must be between 2 and 30",
                constraintViolations.iterator().next().getMessage()
        );
    }
    
//    @NotBlank bean validation test for captainName
    @Test
    public void teamBlankCaptainNameValidation() {
        Team badTD = new Team("Thunders",
                "",
                "312943752",
                Level.BEG,
                LocalDateTime.now());

        Set<ConstraintViolation<Team>> constraintViolations
                = validator.validate(badTD);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "must not be blank",
                constraintViolations.iterator().next().getMessage()
        );
    }
    
//    @NotBlank bean validation test for contact
    @Test
    public void teamBadContactValidation() {
        Team badTD = new Team("Thunders",
                "Steve",
                "",
                Level.BEG,
                LocalDateTime.now());

        Set<ConstraintViolation<Team>> constraintViolations
                = validator.validate(badTD);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "must not be blank",
                constraintViolations.iterator().next().getMessage()
        );
    }
}
