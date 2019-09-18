package com.lanit.dcs.diss.aacs.satonin18.hackathon;


import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid.CarDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid.PersonDto4save;
import lombok.Data;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Templates {
    public static final Map<String, PersonDto4save> personDto4saveList = new HashMap<>();
    public static final Map<String, CarDto4save> carDto4saveList = new HashMap<>();

    public static final Map<String, String> personOutput4jsonList = new HashMap<>();

    static {
        PersonDto4save personDto4save = null;

        personDto4save = new PersonDto4save();
        personDto4save.setId(-1L);
        personDto4save.setName("Validperson1");
        personDto4save.setBirthdate("01.01.2000");

        personDto4saveList.put("ValidPerson_1", personDto4save);


        personDto4save = new PersonDto4save();
        personDto4save.setId(-10L);
        personDto4save.setName("Validperson1");
        personDto4save.setBirthdate("01.01.2000");

        personDto4saveList.put("ValidPerson_10", personDto4save);


        personOutput4jsonList.put("ValidPerson_10", "{\"id\":-10,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}");



    }
}