package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("carService")
public class CarService {

    private final CarRepository carRepository;
    private final Validator validatorEntity;

    @Autowired
    public CarService(final CarRepository carRepository, final Validator validatorEntity){
        this.carRepository = carRepository;
        this.validatorEntity = validatorEntity;
    }

    //todo check type javax.transactions
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        carRepository.deleteAll();
    }

    //todo check type javax.transactions
    @Transactional(rollbackFor = Exception.class)
    public void saveCar(
//            @Valid //наверн тупой монитринг и запись ошибки, без каких либо предпринятых действий или исключений
             Car car
    ) throws Exception {
        if ( carRepository.existsById(car.getId()) ) throw new Exception();
        //(else)todo многопоточная коллиция на добавление(т.к. в SpringDataJpa есть только save NO UPDATE)

        Set<ConstraintViolation<Car>> validates = validatorEntity.validate(car);
        if(validates.size() > 0) {
            String errors = validates.stream().map(v -> v.getMessage())
                    .collect(Collectors.joining());//todo check for 2 and more
            throw new Exception(errors);
        }
        // SAVE ---------------------------------------------------------------------------------------
        carRepository.save(car);//can be add:@Transactional(rollbackFor = Exception.class)
    }
}
