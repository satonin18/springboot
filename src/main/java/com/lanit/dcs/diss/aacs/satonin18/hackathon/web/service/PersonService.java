package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("personService")
public class PersonService {

    private final PersonRepository personRepository;
    private final Validator validatorEntity;

    @Autowired
    public PersonService(final PersonRepository personRepository, final Validator validatorEntity) {
        this.personRepository = personRepository;
        this.validatorEntity = validatorEntity;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(){ personRepository.deleteAll(); }

    @Transactional(rollbackFor = Exception.class)
    public void savePerson(
            Person person
    ) throws Exception {
        // VALIDATE DTO ------------------------------------
        Set<ConstraintViolation<Person>> validates = validatorEntity.validate(person);
        if(validates.size() > 0) {
            String errors = validates.stream().map(v -> v.getMessage())
                    .collect(Collectors.joining());//todo check for 2 and more
            throw new Exception(errors);
        }
        if ( personRepository.existsById(person.getId()) ) throw new Exception();
        //todo многопоточная коллизия на добавление(+ в SpringDataJpa есть только save, NO UPDATE)
        // SAVE ------------------------------------
        personRepository.save(person);//can be add:@Transactional(rollbackFor = Exception.class)
    }

    public Person getPerson(Long personid) {
        if ( ! personRepository.existsById(personid) )
            return null;
        else
            return personRepository.findById(personid).get();
    }

    public Optional<Person> findById(Long ownerId) {
        return personRepository.findById(ownerId);
    }
}
