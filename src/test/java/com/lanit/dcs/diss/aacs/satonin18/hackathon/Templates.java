package com.lanit.dcs.diss.aacs.satonin18.hackathon;


import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid.CarDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid.PersonDto4save;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor //ATTATION //no EDIT !!!
class Struct{
    private int id;
    private String json;
}

public class Templates {
    public static final Map<String, Struct> jsonMap = new HashMap<>();

    static {
        //TODO INCLUDE ID IN JSON by stringConcantining)
        int id = 0;
        String json4save = Strings.EMPTY;
        String json4output = Strings.EMPTY;

        id = -1;
        json4save = "{\"id\":\"-1\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_person", new Struct(id, json4save));
        jsonMap.put("get", new Struct(id, json4output));

        id = -10;
        json4save = "{\"id\":-10,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        json4output = "{\"id\":-10,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("add_valid1", new Struct(id, json4save));
        jsonMap.put("get1", new Struct(id, json4output));

        id = -20;
        json4save = "{\"id\":\"-20\",\"name\":\"Validperson2\",\"birthdate\":\"01.12.2017\"}";
        json4output = "{\"id\":-20,\"name\":\"Validperson2\",\"birthdate\":\"01.12.2017\",\"cars\":[]}";
        jsonMap.put("add_valid2", new Struct(id, json4save));
        jsonMap.put("get2", new Struct(id, json4output));

        id = -30;
        json4save = "{\"id\":\"-30\",\"name\":\"\",\"birthdate\":\"01.12.2017\"}";
        json4output = "{\"id\":-30,\"name\":\"\",\"birthdate\":\"01.12.2017\",\"cars\":[]}";
        jsonMap.put("add_valid3_emptyname", new Struct(id, json4save));
        jsonMap.put("get3", new Struct(id, json4output));

        id = -39;
        json4save = "{\"id\":\"-39\",\"name\":\"valid\",\"birthdate\":\"01.12.2117\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_valid4_futurebirthdate", new Struct(id, json4save));
        jsonMap.put("getnot4", new Struct(id, json4output));

        id = 0; //todo no corect
        json4save = "{\"id\":\"\",\"name\":\"valid\",\"birthdate\":\"01.12.2017\"}";
        jsonMap.put("add_notvalid_emptyid", new Struct(id, json4save));

        id = 0; //todo no corect
        json4save = "{\"name\":\"valid\",\"birthdate\":\"01.12.2017\"}";
        jsonMap.put("add_notvalid_nullid", new Struct(id, json4save));

        id = -70;
        json4save = "{\"id\":\"-70\",\"birthdate\":\"01.12.2017\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid5_nullname", new Struct(id, json4save));
        jsonMap.put("getnot5", new Struct(id, json4output));

        id = -80;
        json4save = "{\"id\":\"-80\",\"name\":\"valid\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid6_nullbirthdate", new Struct(id, json4save));
        jsonMap.put("getnot6", new Struct(id, json4output));

        id = -90;
        json4save = "{\"id\":\"-90\",\"name\":\"Validperson2\",\"birthdate\":\"2017-01-01\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid7_incorrectbirthdate", new Struct(id, json4save));
        jsonMap.put("getnot7", new Struct(id, json4output));
    }
}