package com.lanit.dcs.diss.aacs.satonin18.hackathon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid.PersonDto4save;
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
        addPerson(personDto4saveList.get("ValidPerson_1"));
        clear();
        getAndCheck(personDto4saveList.get("ValidPerson_1").getId(), HttpStatus.NOT_FOUND.value(), Strings.EMPTY);
        System.out.println("----------------");
    }

    @Test
    public void addValidPerson() throws JsonProcessingException {
        System.out.println();
        System.out.println("----addValidPerson----");
        addPerson(personDto4saveList.get("ValidPerson_10"));

//        String json = new ObjectMapper().writeValueAsString(getValidPerson_10());
        String json = personOutput4jsonList.get("ValidPerson_10");

        getAndCheck(personDto4saveList.get("ValidPerson_10").getId(), HttpStatus.OK.value(), json);
        System.out.println("----------------");
    }

    @Test
    public void addValidPersonLess18() throws JsonProcessingException {
        System.out.println();
        System.out.println("----addValidPerson----");
        addPerson(personDto4saveList.get("ValidPerson_10"));

//        String json = new ObjectMapper().writeValueAsString(getValidPerson_10());
        String json = personOutput4jsonList.get("ValidPerson_10");

        getAndCheck(personDto4saveList.get("ValidPerson_10").getId(), HttpStatus.OK.value(), json);
        System.out.println("----------------");
    }

    private void clear() {
        System.out.println("->clear()");

        Response r = RestAssured.get(API_ROOT + "/clear");

        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        assertEquals(0, r.getBody().print().length());
        System.out.println("<-");
    }

    private void addPerson(PersonDto4save dto) {
        System.out.println("->addPerson( " + dto + " )");

        Response r = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .post(API_ROOT + "/person");
//        Response r = RestAssured.get(API_ROOT + "/person");

        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        assertEquals(0, r.getBody().print().length());
        System.out.println("<-");
    }

    private void getAndCheck(Long id, int requiredStatusCode, String requiredBody) {
        System.out.println("->getAndCheck( " + id + " )");

        Response r = RestAssured.get(API_ROOT + "/personwithcars?personid=" + id);

        assertEquals(requiredStatusCode, r.getStatusCode());
        String body = r.getBody().print();
        assertEquals(requiredBody.length(), body.length());
        assertEquals(requiredBody, body);
        System.out.println("<-");
    }
}
