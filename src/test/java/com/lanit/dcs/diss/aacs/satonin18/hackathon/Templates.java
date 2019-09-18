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

@Data
class Item<T>{
    private T dto4save;
    private String dto4output;
}

public class Templates {
    public static final Map<String, Item<PersonDto4save>> personList = new HashMap<>();
    public static final Map<String, Item<CarDto4save>> carList = new HashMap<>();

    static {
        PersonDto4save personDto4save = null;
        Item<PersonDto4save> personItem = null;
        Item<CarDto4save> carItem = null;

        personDto4save = new PersonDto4save();
        personDto4save.setId(-1L);
        personDto4save.setName("Validperson1");
        personDto4save.setBirthdate("01.01.2000");

        personItem = new Item<>();
        personItem.setDto4save(personDto4save);
        personItem.setDto4output(Strings.EMPTY);

        personList.put("ValidPerson_1", personItem);


        personDto4save = new PersonDto4save();
        personDto4save.setId(-10L);
        personDto4save.setName("Validperson1");
        personDto4save.setBirthdate("01.01.2000");

        personItem = new Item<>();
        personItem.setDto4save(personDto4save);
        personItem.setDto4output("{\"id\":-10,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}");

        personList.put("ValidPerson_10", personItem);


    }
}