package com.lanit.dcs.diss.aacs.satonin18.hackathon;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class HttpService {
    private static final String API_ROOT = "http://10.32.101.63:8080";

    public void clear() {
        RestAssured.get(API_ROOT + "/clear");
    }
    public Response addPerson(String json) {
        return RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(json)
                .post(API_ROOT + "/person");
    }
    public Response get(int id) {
        return RestAssured.get(API_ROOT + "/personwithcars?personid=" + id);
    }
}
