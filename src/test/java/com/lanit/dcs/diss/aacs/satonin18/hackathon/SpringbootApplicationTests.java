package com.lanit.dcs.diss.aacs.satonin18.hackathon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.response.Response;

import static com.lanit.dcs.diss.aacs.satonin18.hackathon.Templates.*;
import static org.junit.Assert.assertEquals;

//todo НАЗВАНИЯ ПЕРЕМЕННЫХ НЕ СООТВЕСТВУЮТ КОД_КОНВЕНШН, ТАК КАК НЕКОТОРЫЕ МЕТОДЫ(например get) асертят (проверяют валидность)
// НО ЗАТО НАЗВАНИЯ МЕТОДОВ СООТВЕСТВУЮТ МЕТОДАМ SOAP UI

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringbootApplication.class },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootApplicationTests {
    //can be replace
    private HttpService http = new HttpService();

    @Test
    public void testClear() {
        System.out.println();
        System.out.println("----testClear----");

        http.clear();
        check_addperson();
        http.clear();
        check_get();

        System.out.println("----------------");
    }
    private void check_addperson() {
        System.out.println("->check_addperson()");

        Response r = http.addPerson(jsonMap.get("addperson").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void check_get() {
        System.out.println("->check_get()");

        Response r = http.get(jsonMap.get("get").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddValidPerson() {
        System.out.println();
        System.out.println("----testAddValidPerson----");

        check_addvalid1();
        check_get1();

        System.out.println("----------------");
    }
    private void check_addvalid1() {
        System.out.println("->check_addvalid1()");

        Response r = http.addPerson(jsonMap.get("addvalid1").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void check_get1() {
        System.out.println("->check_get()");

        Response r = http.get(jsonMap.get("get1").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("get1").getJson(), r.getBody().print());

        System.out.println("<-");
    }
}
