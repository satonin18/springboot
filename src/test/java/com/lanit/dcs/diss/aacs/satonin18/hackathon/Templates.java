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
    //todo car no consist answerJson
    public static final Map<String, Struct> jsonMap = new HashMap<>();

    static {
        //TODO INCLUDE ID IN JSON by stringConcantining)
        int id = 0;
        int idcar = 0;
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

        id = -100;
        json4save = "{\"id\":\"-100\",\"name\":\"Validperson2\",\"birthdate\":\"01.15.2017\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid8_lanientbirthdate", new Struct(id, json4save));
        jsonMap.put("getnot8", new Struct(id, json4output));

        id = -110;
        json4save = "{\"id\":\"-110\",\"name\":\"Validperson2\",\"birthdate\":\"sds\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid9_symbolsbirthdate", new Struct(id, json4save));
        jsonMap.put("getnot9", new Struct(id, json4output));



        id = -130;
        json4save = "{\"id\":\"-130\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_validperson1", new Struct(id, json4save));

        idcar = -130;
        json4save = "{\"id\":\"-130\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-130\"}";
        jsonMap.put("add_car1", new Struct(idcar, json4save));

        json4output = "{\"id\":-130,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[{\"id\":-130,\"horsepower\":100,\"ownerId\":-130,\"model\":\"BMW-X5\"}]}";
        jsonMap.put("getcar1", new Struct(id, json4output));



        id = -140;
        json4save = "{\"id\":\"-140\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_validperson2", new Struct(id, json4save));

        idcar = -140;
        json4save = "{\"id\":\"-140\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-140\"}";
        jsonMap.put("add_car2", new Struct(idcar, json4save));
        idcar = -139;
        json4save = "{\"id\":\"-139\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-140\"}";
        jsonMap.put("add_car3", new Struct(idcar, json4save));
        idcar = -138;
        json4save = "{\"id\":\"-138\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-140\"}";
        jsonMap.put("add_car4", new Struct(idcar, json4save));

        json4output = "{\"id\":-140,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[{\"id\":-140,\"horsepower\":100,\"ownerId\":-140,\"model\":\"BMW-X5\"},{\"id\":-139,\"horsepower\":100,\"ownerId\":-140,\"model\":\"BMW-X3\"},{\"id\":-138,\"horsepower\":50,\"ownerId\":-140,\"model\":\"Lada-Devyatka\"}]}";
        jsonMap.put("getcars1", new Struct(id, json4output));




        id = -150;
        json4save = "{\"id\":\"-150\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_validperson3", new Struct(id, json4save));

        idcar = -149;
        json4save = "{\"id\":\"-149\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-150\"}";
        jsonMap.put("add_car5", new Struct(idcar, json4save));
        idcar = -148;
        json4save = "{\"id\":\"-148\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-150\"}";
        jsonMap.put("add_car6", new Struct(idcar, json4save));

        json4output = "{\"id\":-150,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[{\"id\":-149,\"horsepower\":50,\"ownerId\":-150,\"model\":\"La-da-Devyatka\"},{\"id\":-148,\"horsepower\":50,\"ownerId\":-150,\"model\":\"La-da-\"}]}";
        jsonMap.put("getcars2", new Struct(id, json4output));




        id = -160;
        json4save = "{\"id\":\"-160\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_validperson4", new Struct(id, json4save));

        idcar = -160;
        json4save = "{\"id\":\"-160\",\"model\":\"-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-160\"}";
        jsonMap.put("add_not_car7", new Struct(idcar, json4save));

        json4output = "{\"id\":-160,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar2", new Struct(id, json4output));






        id = -170;
        json4save = "{\"id\":\"-170\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_validperson5", new Struct(id, json4save));

        idcar = -170;
        json4save = "{\"id\":\"-170\",\"model\":\"A-B\",\"horsepower\":-50,\"ownerId\":\"-170\"}";
        jsonMap.put("add_not_car8", new Struct(idcar, json4save));

        json4output = "{\"id\":-170,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar3", new Struct(id, json4output));






        id = -180;
        json4save = "{\"id\":\"-180\",\"name\":\"Validperson2\",\"birthdate\":\"01.12.2017\"}";
        jsonMap.put("add_validperson_less18years", new Struct(id, json4save));

        idcar = -180;
        json4save = "{\"id\":\"-180\",\"model\":\"A-B\",\"horsepower\":50,\"ownerId\":\"-180\"}";
        jsonMap.put("add_not_car9", new Struct(idcar, json4save));

        json4output = "{\"id\":-180,\"name\":\"Validperson2\",\"birthdate\":\"01.12.2017\",\"cars\":[]}";
        jsonMap.put("getcar4", new Struct(id, json4output));




        id = -190;
        json4save = "{\"id\":\"-190\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_validperson6", new Struct(id, json4save));

        idcar = -190;
        json4save = "{\"id\":\"-190\",\"model\":\"\",\"horsepower\":50,\"ownerId\":\"-190\"}";
        jsonMap.put("add_not_car10", new Struct(idcar, json4save));


        idcar = -189;
        json4save = "{\"id\":\"-189\",\"horsepower\":50,\"ownerId\":\"-190\"}";
        jsonMap.put("add_not_car11", new Struct(idcar, json4save));

        json4output = "{\"id\":-190,\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar5", new Struct(id, json4output));




    }
}