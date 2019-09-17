package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import java.util.List;
import java.util.Optional;

//TODO can be extend SpringDataJpa Repository interface
public interface CrudService<T, ID> {
    public boolean existsById(ID id);

    public Optional<T> findById(ID id) ;
    public List<T> findAll() ;

    public void save(T entity);

    public void delete(T entity) ;
    public void deleteById(ID id) ;
    public void deleteAll();

    //SpringDataJpa only long
    public long count();
}