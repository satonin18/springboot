package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor

@Entity
@Table(name = "cars")

//@JsonIgnoreProperties({"person", "vendor", "model"})
public class Car {



    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    @JsonIgnore

    @Column(name = "vendor", nullable = false, length = 50)
    String vendor;



    @JsonIgnore

    @Column(name = "model", nullable = false, length = 50)
    String model;



    @JsonProperty("model")
    String model(){
        return String.join("-",vendor, model);
    }



//    @Positive
//    @Range(min=1)

    @Column(name = "horsepower", nullable = false)
    Integer horsepower;



    @Column(name = "ownerId", nullable = false)
    Long ownerId;



//    @JsonIgnore
//
//    @ManyToOne
//    @JoinColumn(name = "ownerId", referencedColumnName = "id", nullable = false)
//    private Person person;

}