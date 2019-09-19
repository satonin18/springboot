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
@AllArgsConstructor //ATTATION  //no EDIT !!!
class Struct{
    private int id;
    private String json;
}

public class Templates {
    public static final Map<String, Struct> jsonMap = new HashMap<>();

    static {
        //TODO generetor ID (then INCLUDE IN JSON by stringConcantining)
        int id = 0;
        String json4save = Strings.EMPTY;
        String json4output = Strings.EMPTY;

        id = -1;
        json4save = "{\"id\":\"-1\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("addperson", new Struct(id, json4save));
        jsonMap.put("get", new Struct(id, json4output));

        id = -10;
        json4save = "{\"id\":-10,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}\"";
        json4output = "{\"id\":-10,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("addvalid1", new Struct(id, json4save));
        jsonMap.put("get1", new Struct(id, json4output));
    }
}