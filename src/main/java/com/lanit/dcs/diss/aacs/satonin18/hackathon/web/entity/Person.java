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

//import javax.validation.constraints.Past;

//todo validation
/*
JSON, представляющий объект Person
{
id: Long (not null),
name: String (not null),
birthdate: Date (not null,формат dd.MM.yyyy),
}

Все поля удовлетворяют ограничениям на тип и формат
Дата рождения в прошлом
Дата рождения в нужном формате
Ранее валидный объект с таким id не передавался
*/

//todo ограничения БД (по идее они уже прописаны в DTO на более высоком уровне приложения)
//todo JSON PersonWithCars(only 4 var)

@Data
@NoArgsConstructor

@Entity
@Table(name = "persons")

//@JsonIgnoreProperties({"cars"}) //todo replace java.sql.Date birthdate on STRING
public class Person {



    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;




    @Column(name = "name", nullable = false, length = 100)
    String name;



    @JsonFormat(pattern = PropertiesApp.DATA_FORMAT_BIRTHDATE, shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)

//    @Past

    @Column(name = "birthdate", nullable = false)
    LocalDate birthdate; //java.sql.Date



    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "person")
    @OneToMany(mappedBy = "ownerId")
    List<Car> cars; //Array of Cars [{}, {}, ...] (not null)

}
