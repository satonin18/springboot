package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("carService")
public class CarServiceImpl
        implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public boolean existsById(Long id) { return carRepository.existsById(id); }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        carRepository.deleteAll();
    }

    //SpringDataJpa only long
    @Override
    public long count() {
        return carRepository.count();
    }

    @Override
    public Long countDistinctVendor() {
        //todo replace
        return Long.valueOf(carRepository.allDistinctVendorIgnorCase().size());
    }
}
