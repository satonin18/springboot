package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.valid;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class DriverCarHave18AgeValidator implements ConstraintValidator<Have18Age, Person> {

    private static final int ADULT_AGE = 18;

//    @Autowired
//    private PersonService personService;
    //todo constructor

    @Override
    public void initialize(Have18Age have18Age) {

    }

    @Override
    public boolean isValid(Person person, ConstraintValidatorContext constraintValidatorContext) {
        if (person != null) {
            LocalDate date = person.getBirthdate();
            return (Period.between(date, LocalDate.now())).getYears() >= ADULT_AGE;
        } else {
            return false;
        }
    }
}