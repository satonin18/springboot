package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService
        extends CrudService<Car, Long> {
    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<Car> findById(Long id);
    @Override
    public List<Car> findAll();

    @Override
    public void save(Car car);

    @Override
    public void delete(Car car);
    @Override
    public void deleteById(Long id);
    @Override
    public void deleteAll();

    //SpringDataJpa only long
    @Override
    public long count();

    public Long countDistinctVendor();
}
