package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.helper.PropertiesApp;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "persons")

//@JsonIgnoreProperties({"cars"})
public class Person {



    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    @Column(name = "name", nullable = false, length = 100)
    String name;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PropertiesApp.DATA_FORMAT_BIRTHDATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)

    @Column(name = "birthdate", nullable = false)
    LocalDate birthdate;



    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "person")
    @OneToMany(mappedBy = "ownerId")
    List<Car> cars;

}
