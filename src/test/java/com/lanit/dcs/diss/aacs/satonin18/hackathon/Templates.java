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
    private Integer id;
    private String json;
}

public class Templates {
    //todo car no consist answerJson
    public static final Map<String, Struct> jsonMap = new HashMap<>();

    static {
        //TODO INCLUDE ID IN JSON by stringConcantining)
        Integer id = null;
        Integer idcar = null;
        String json4save = Strings.EMPTY;
        String json4output = Strings.EMPTY;

        id = -1;
        json4save = "{\"id\":\"-1\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_person", new Struct(id, json4save));
        jsonMap.put("get", new Struct(id, json4output));

        id = -10;
        json4save = "{\"id\":-10,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        json4output = "{\"id\":-10,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("add_valid1", new Struct(id, json4save));
        jsonMap.put("get1", new Struct(id, json4output));

        id = -20;
        json4save = "{\"id\":\"-20\",\"name\":\"valid_person2\",\"birthdate\":\"01.12.2017\"}";
        json4output = "{\"id\":-20,\"name\":\"valid_person2\",\"birthdate\":\"01.12.2017\",\"cars\":[]}";
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

        id = null;
        json4save = "{\"id\":\"\",\"name\":\"valid\",\"birthdate\":\"01.12.2017\"}";
        jsonMap.put("add_notvalid_emptyid", new Struct(id, json4save));

        id = null;
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
        json4save = "{\"id\":\"-90\",\"name\":\"valid_person2\",\"birthdate\":\"2017-01-01\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid7_incorrectbirthdate", new Struct(id, json4save));
        jsonMap.put("getnot7", new Struct(id, json4output));

        id = -100;
        json4save = "{\"id\":\"-100\",\"name\":\"valid_person2\",\"birthdate\":\"01.15.2017\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid8_lanientbirthdate", new Struct(id, json4save));
        jsonMap.put("getnot8", new Struct(id, json4output));

        id = -110;
        json4save = "{\"id\":\"-110\",\"name\":\"valid_person2\",\"birthdate\":\"sds\"}";
        json4output = Strings.EMPTY;
        jsonMap.put("add_notvalid9_symbolsbirthdate", new Struct(id, json4save));
        jsonMap.put("getnot9", new Struct(id, json4output));



        id = -130;
        json4save = "{\"id\":\"-130\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person1", new Struct(id, json4save));

        idcar = -130;
        json4save = "{\"id\":\"-130\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-130\"}";
        jsonMap.put("add_car1", new Struct(idcar, json4save));

        json4output = "{\"id\":-130,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[{\"id\":-130,\"horsepower\":100,\"ownerId\":-130,\"model\":\"BMW-X5\"}]}";
        jsonMap.put("getcar1", new Struct(id, json4output));



        id = -140;
        json4save = "{\"id\":\"-140\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person2", new Struct(id, json4save));

        idcar = -140;
        json4save = "{\"id\":\"-140\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-140\"}";
        jsonMap.put("add_car2", new Struct(idcar, json4save));
        idcar = -139;
        json4save = "{\"id\":\"-139\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-140\"}";
        jsonMap.put("add_car3", new Struct(idcar, json4save));
        idcar = -138;
        json4save = "{\"id\":\"-138\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-140\"}";
        jsonMap.put("add_car4", new Struct(idcar, json4save));

        json4output = "{\"id\":-140,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[{\"id\":-140,\"horsepower\":100,\"ownerId\":-140,\"model\":\"BMW-X5\"},{\"id\":-139,\"horsepower\":100,\"ownerId\":-140,\"model\":\"BMW-X3\"},{\"id\":-138,\"horsepower\":50,\"ownerId\":-140,\"model\":\"Lada-Devyatka\"}]}";
        jsonMap.put("getcars1", new Struct(id, json4output));




        id = -150;
        json4save = "{\"id\":\"-150\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person3", new Struct(id, json4save));

        idcar = -149;
        json4save = "{\"id\":\"-149\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-150\"}";
        jsonMap.put("add_car5", new Struct(idcar, json4save));
        idcar = -148;
        json4save = "{\"id\":\"-148\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-150\"}";
        jsonMap.put("add_car6", new Struct(idcar, json4save));

        json4output = "{\"id\":-150,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[{\"id\":-149,\"horsepower\":50,\"ownerId\":-150,\"model\":\"La-da-Devyatka\"},{\"id\":-148,\"horsepower\":50,\"ownerId\":-150,\"model\":\"La-da-\"}]}";
        jsonMap.put("getcars2", new Struct(id, json4output));




        id = -160;
        json4save = "{\"id\":\"-160\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person4", new Struct(id, json4save));

        idcar = -160;
        json4save = "{\"id\":\"-160\",\"model\":\"-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-160\"}";
        jsonMap.put("add_not_car7", new Struct(idcar, json4save));

        json4output = "{\"id\":-160,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar2", new Struct(id, json4output));






        id = -170;
        json4save = "{\"id\":\"-170\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person5", new Struct(id, json4save));

        idcar = -170;
        json4save = "{\"id\":\"-170\",\"model\":\"A-B\",\"horsepower\":-50,\"ownerId\":\"-170\"}";
        jsonMap.put("add_not_car8", new Struct(idcar, json4save));

        json4output = "{\"id\":-170,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar3", new Struct(id, json4output));






        id = -180;
        json4save = "{\"id\":\"-180\",\"name\":\"valid_person2\",\"birthdate\":\"01.12.2017\"}";
        jsonMap.put("add_valid_person_less18years", new Struct(id, json4save));

        idcar = -180;
        json4save = "{\"id\":\"-180\",\"model\":\"A-B\",\"horsepower\":50,\"ownerId\":\"-180\"}";
        jsonMap.put("add_not_car9", new Struct(idcar, json4save));

        json4output = "{\"id\":-180,\"name\":\"valid_person2\",\"birthdate\":\"01.12.2017\",\"cars\":[]}";
        jsonMap.put("getcar4", new Struct(id, json4output));




        id = -190;
        json4save = "{\"id\":\"-190\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person6", new Struct(id, json4save));

        idcar = -190;
        json4save = "{\"id\":\"-190\",\"model\":\"\",\"horsepower\":50,\"ownerId\":\"-190\"}";
        jsonMap.put("add_not_car10", new Struct(idcar, json4save));

        idcar = -189;
        json4save = "{\"id\":\"-189\",\"horsepower\":50,\"ownerId\":\"-190\"}";
        jsonMap.put("add_not_car11", new Struct(idcar, json4save));

        json4output = "{\"id\":-190,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar5", new Struct(id, json4output));






        id = -200;
        json4save = "{\"id\":\"-200\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person7", new Struct(id, json4save));

        idcar = null;
        json4save = "{\"id\":\"\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-200\"}";
        jsonMap.put("add_not_car12", new Struct(idcar, json4save));
        idcar = null;
        json4save = "{\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-200\"}";
        jsonMap.put("add_not_car13", new Struct(idcar, json4save));

        json4output = "{\"id\":-200,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar6", new Struct(id, json4output));






        id = -210;
        json4save = "{\"id\":\"-210\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person8", new Struct(id, json4save));

        idcar = -210;
        json4save = "{\"id\":\"-210\",\"model\":\"BMW-X3\",\"horsepower\":\"\",\"ownerId\":\"-210\"}";
        jsonMap.put("add_not_car14", new Struct(idcar, json4save));

        idcar = -209;
        json4save = "{\"id\":\"-209\",\"model\":\"BMW-X3\",\"ownerId\":\"-210\"}";
        jsonMap.put("add_not_car15", new Struct(idcar, json4save));

        idcar = -208;
        json4save = "{\"id\":\"-208\",\"model\":\"BMW-X3\",\"horsepower\":\"0\",\"ownerId\":\"-210\"}";
        jsonMap.put("add_not_car16", new Struct(idcar, json4save));

        json4output = "{\"id\":-210,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";
        jsonMap.put("getcar7", new Struct(id, json4output));





        idcar = -220;
        json4save = "{\"id\":\"-220\",\"model\":\"BMW-X3\",\"horsepower\":\"100\",\"ownerId\":\"\"}";
        jsonMap.put("add_not_car17", new Struct(idcar, json4save));

        idcar = -219;
        json4save = "{\"id\":\"-219\",\"model\":\"BMW-X3\",\"horsepower\":\"100\"}";
        jsonMap.put("add_not_car18", new Struct(idcar, json4save));




        id = -250;
        json4save = "{\"id\":\"-250\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person9", new Struct(id, json4save));
        json4save = "{\"id\":\"-250\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_not_valid_uniqueperson", new Struct(id, json4save));





        id = -260;
        json4save = "{\"id\":\"-260\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person10", new Struct(id, json4save));

        idcar = -260;
        json4save = "{\"id\":\"-260\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-260\"}";
        jsonMap.put("add_car7", new Struct(idcar, json4save));
        json4save = "{\"id\":\"-260\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-260\"}";
        jsonMap.put("add_not_valid_unique_car", new Struct(idcar, json4save));




        id = null;
        json4save = "{\"personcount\":0,\"carcount\":0,\"uniquevendorcount\":0}";
        jsonMap.put("empty_statistics", new Struct(id, json4save));

        id = -230;
        json4save = "{\"id\":\"-230\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person11", new Struct(id, json4save));

        idcar = -230;
        json4save = "{\"id\":\"-230\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-230\"}";
        jsonMap.put("add_car8", new Struct(idcar, json4save));
        idcar = -229;
        json4save = "{\"id\":\"-229\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-230\"}";
        jsonMap.put("add_car9", new Struct(idcar, json4save));
        idcar = -228;
        json4save = "{\"id\":\"-228\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        jsonMap.put("add_car10", new Struct(idcar, json4save));
        idcar = -227;
        json4save = "{\"id\":\"-227\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        jsonMap.put("add_car11", new Struct(idcar, json4save));
        idcar = -226;
        json4save = "{\"id\":\"-226\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        jsonMap.put("add_car12", new Struct(idcar, json4save));

        id = null;
        json4save = "{\"personcount\":1,\"carcount\":5,\"uniquevendorcount\":3}";
        jsonMap.put("statistics", new Struct(id, json4save));






        id = -240;
        json4save = "{\"id\":\"-240\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person12", new Struct(id, json4save));
        id = -239;
        json4save = "{\"id\":\"-239\",\"birthdate\":\"01.12.2017\"}";
        jsonMap.put("add_not_valid_person13_nullname", new Struct(id, json4save));

        idcar = -240;
        json4save = "{\"id\":\"-240\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-240\"}";
        jsonMap.put("add_valid_car", new Struct(idcar, json4save));
        idcar = -239;
        json4save = "{\"id\":\"-239\",\"model\":\"\",\"horsepower\":50,\"ownerId\":\"-240\"}";
        jsonMap.put("add_not_valid_car_empty_model", new Struct(idcar, json4save));

        id = null;
        json4save = "{\"personcount\":1,\"carcount\":1,\"uniquevendorcount\":1}";
        jsonMap.put("statistics2", new Struct(id, json4save));



        id = -270;
        json4save = "{\"id\":\"-270\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        jsonMap.put("add_valid_person13", new Struct(id, json4save));

        idcar = -270;
        json4save = "{\"id\":\"-270\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-270\"}";
        jsonMap.put("add_valid_car1", new Struct(idcar, json4save));
        idcar = -269;
        json4save = "{\"id\":\"-269\",\"model\":\"BmW-X3\",\"horsepower\":100,\"ownerId\":\"-270\"}";
        jsonMap.put("add_valid_car2", new Struct(idcar, json4save));
        idcar = -268;
        json4save = "{\"id\":\"-268\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-270\"}";
        jsonMap.put("add_valid_car3", new Struct(idcar, json4save));
        idcar = -267;
        json4save = "{\"id\":\"-267\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-270\"}";
        jsonMap.put("add_valid_car4", new Struct(idcar, json4save));
        idcar = -266;
        json4save = "{\"id\":\"-266\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-270\"}";
        jsonMap.put("add_valid_car5", new Struct(idcar, json4save));

        id = null;
        json4save = "{\"personcount\":1,\"carcount\":5,\"uniquevendorcount\":3}";
        jsonMap.put("statistics3", new Struct(id, json4save));


    }
}