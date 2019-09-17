package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService
        extends CrudService<Person, Long> {
    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<Person> findById(Long id);
    @Override
    public List<Person> findAll();

    @Override
    public void save(Person person);

    @Override
    public void delete(Person person);
    @Override
    public void deleteById(Long id);
    @Override
    public void deleteAll();

    //SpringDataJpa only long
    @Override
    public long count();
    
}
