package com.lanit.dcs.diss.aacs.satonin18.hackathon;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.lanit.dcs.diss.aacs.satonin18.hackathon.Templates.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringbootApplication.class }, webEnvironment
        = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootApplicationTests {

    private static final String API_ROOT = "http://10.32.101.63:8080";

//    @Test
//    public void whenGetstatistics_thenOK() {
//        System.out.println("----whenGetstatistics_thenOK----");
//
//        Response r = RestAssured.get(API_ROOT + "/statistics");
//
//        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
//        System.out.println("----assertEquals----");
//        System.out.println("r.getContentType()"+r.getContentType());
//        System.out.println("r.getBody().print()"+r.getBody().print());
//        System.out.println("r.as(StatisticsDto.class)"+r.as(StatisticsDto.class));
//    }

    @Test
    public void testClear() {
        System.out.println();
        System.out.println("----testClear----");

        clear();
        addPersonAndCheck(jsonMap.get("addperson").getJson(), HttpStatus.OK);
        clear();
        getAndCheck(jsonMap.get("get").getId(), HttpStatus.NOT_FOUND, jsonMap.get("get").getJson());

        System.out.println("----------------");
    }

    @Test
    public void addValidPerson() {
        System.out.println();
        System.out.println("----addValidPerson----");

        addPersonAndCheck(jsonMap.get("addvalid1").getJson(), HttpStatus.OK);
        getAndCheck(jsonMap.get("get1").getId(), HttpStatus.NOT_FOUND, jsonMap.get("get1").getJson());

        System.out.println("----------------");
    }

    @Test
    public void addValidPersonLess18() {

    }

    private void clear() {
//        System.out.println("->clear()");

        Response r = RestAssured.get(API_ROOT + "/clear");

//        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
//        assertEquals(0, r.getBody().print().length());

//        System.out.println("<-");
    }

    private void addPersonAndCheck(String json, HttpStatus requiredHttpStatus) {
        System.out.println("->addPersonAndCheck( " + json + " )");

        Response r = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(json)
                .post(API_ROOT + "/person");

        assertEquals(requiredHttpStatus.value(), r.getStatusCode());
//        assertEquals(0, r.getBody().print().length());

        System.out.println("<-");
    }

    private void getAndCheck(int id, HttpStatus requiredHttpStatus, String requiredBody) {
        System.out.println("->getAndCheck( " + id + " )");

        Response r = RestAssured.get(API_ROOT + "/personwithcars?personid=" + id);

        assertEquals(requiredHttpStatus.value(), r.getStatusCode());
        String body = r.getBody().print();
//        assertEquals(requiredBody.length(), body.length());
        assertEquals(requiredBody, body);

        System.out.println("<-");
    }
}
