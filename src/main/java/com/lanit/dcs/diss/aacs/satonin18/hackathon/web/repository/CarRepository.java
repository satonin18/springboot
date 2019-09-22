package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository
        extends JpaRepository<Car, Long>
{
    //ATATION !!! NATIVE QUERY (USE TALES "cars" and FIELD "vendor")
    @Query(nativeQuery = true, value = "SELECT COUNT(DISTINCT LOWER(c.vendor)) FROM cars c")
    Long countDistinctVendorIgnorCase();
}