package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("personService")
public class PersonServiceImpl
        implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean existsById(Long id) { return personRepository.existsById(id); }
    
    @Override
    public Optional<Person> findById(Long id) { return personRepository.findById(id); }
    @Override
    public List<Person> findAll() { return personRepository.findAll(); }

    @Override
    public void save(Person person){ personRepository.save(person); }

    @Override
    public void delete(Person person) { personRepository.delete(person); }
    @Override
    public void deleteById(Long id) { personRepository.deleteById(id); }
    @Override
    public void deleteAll(){ personRepository.deleteAll(); }

    //SpringDataJpa only long
    @Override
    public long count(){ return personRepository.count(); }

}
