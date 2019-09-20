package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long>
{
}