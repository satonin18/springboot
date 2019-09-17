package com.lanit.dcs.diss.aacs.satonin18.hackathon;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.StatisticsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringbootApplication.class }, webEnvironment
        = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootApplicationTests {

    private static final String API_ROOT = "http://10.32.101.63:8080";

    @Test
    public void contextLoads() {
    }

    @Test
    public void whenGetstatistics_thenOK() {
        System.out.println("----whenGetstatistics_thenOK----");

        Response r = RestAssured.get(API_ROOT + "/statistics");

        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        System.out.println("----assertEquals----");
        System.out.println("r.getContentType()"+r.getContentType());
        System.out.println("r.getBody().print()"+r.getBody().print());
        System.out.println("r.as(StatisticsDto.class)"+r.as(StatisticsDto.class));
    }
}
