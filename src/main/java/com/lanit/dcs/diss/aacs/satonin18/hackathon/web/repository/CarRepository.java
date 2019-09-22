package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository
        extends JpaRepository<Car, Long>
{
    //todo НЕ ПОДЕРЖИВАЕТ ВЫЧЕСЛЯЕМОЕ ПОЛЕ ВНУТРИ ДРУГОГО ПОЛЯ //select count(distinct lower(field)) //перейти на nativeQuery
    @Query("SELECT DISTINCT lower(c.vendor) FROM Car c")
    List<String> allDistinctVendorIgnorCase();
}