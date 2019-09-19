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

// todo ПОНЯТЬ КАК РАБОТАЮТ ЭТИ АНОТАЦИИ //ПРОВЕРКА ДОЛЖНА БЫТЬ ВНУТРЕНЯЯЯ ИЛИ ВНЕШНЯЯ (КАК В SoapUI)//здесь какая???

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
        test_add_person();
        http.clear();
        test_get();

        System.out.println("----------------");
    }
    private void test_add_person() {
        System.out.println("->test_add_person()");

        Response r = http.addPerson(jsonMap.get("add_person").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_get() {
        System.out.println("->test_get()");

        Response r = http.get(jsonMap.get("get").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddValidPerson() {
        System.out.println();
        System.out.println("----testAddValidPerson----");

        test_add_valid1();
        test_get1();

        System.out.println("----------------");
    }
    private void test_add_valid1() {
        System.out.println("->test_add_valid1()");

        Response r = http.addPerson(jsonMap.get("add_valid1").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_get1() {
        System.out.println("->test_get1()");

        Response r = http.get(jsonMap.get("get1").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("get1").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddValidPersonLess18() {
        System.out.println();
        System.out.println("----testAddValidPersonLess18----");

        test_add_valid2();
        test_get2();

        System.out.println("----------------");
    }
    private void test_add_valid2() {
        System.out.println("->check_add_valid2()");

        Response r = http.addPerson(jsonMap.get("add_valid2").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_get2() {
        System.out.println("->test_get2()");

        Response r = http.get(jsonMap.get("get2").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("get2").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddValidPersonEmptyName() {
        System.out.println();
        System.out.println("----testAddValidPersonEmptyName----");

        test_add_valid3_emptyname();
        test_get3();

        System.out.println("----------------");
    }
    private void test_add_valid3_emptyname() {
        System.out.println("->check_add_valid3_emptyname()");

        Response r = http.addPerson(jsonMap.get("add_valid3_emptyname").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_get3() {
        System.out.println("->test_get3()");

        Response r = http.get(jsonMap.get("get3").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check var name = "";
        //simple approach
        assertEquals(jsonMap.get("get3").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPersonFutureBirthdate() {
        System.out.println();
        System.out.println("----testAddValidFutureBirthdate----");

        test_add_notvalid4_futurebirthdate();
        test_getnot4();

        System.out.println("----------------");
    }
    private void test_add_notvalid4_futurebirthdate() {
        System.out.println("->check_add_valid4_futurebirthdate()");

        Response r = http.addPerson(jsonMap.get("add_valid4_futurebirthdate").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot4() {
        System.out.println("->test_getnot4()");

        Response r = http.get(jsonMap.get("getnot4").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPersonEmptyId() {
        System.out.println();
        System.out.println("----testAddBadPersonEmptyId----");

        test_add_notvalid_emptyid();

        System.out.println("----------------");
    }
    private void test_add_notvalid_emptyid() {
        System.out.println("->test_add_notvalid_emptyid()");

        Response r = http.addPerson(jsonMap.get("add_notvalid_emptyid").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPersonNullId() {
        System.out.println();
        System.out.println("----testAddBadPersonNullId----");

        test_add_notvalid_nullid();

        System.out.println("----------------");
    }
    private void test_add_notvalid_nullid() {
        System.out.println("->test_add_notvalid_nullid()");

        Response r = http.addPerson(jsonMap.get("add_notvalid_nullid").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPersonNullName() {
        System.out.println();
        System.out.println("----testAddBadPersonNullName----");

        test_add_notvalid5_nullname();
        test_getnot5();

        System.out.println("----------------");
    }
    private void test_add_notvalid5_nullname() {
        System.out.println("->test_add_notvalid5_nullname()");

        Response r = http.addPerson(jsonMap.get("add_notvalid5_nullname").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot5() {
        System.out.println("->test_getnot5()");

        Response r = http.get(jsonMap.get("getnot5").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPersonNullBirthdate() {
        System.out.println();
        System.out.println("----testAddBadPersonNullBirthdate----");

        test_add_notvalid6_nullbirthdate();
        test_getnot6();

        System.out.println("----------------");
    }
    private void test_add_notvalid6_nullbirthdate() {
        System.out.println("->test_add_notvalid6_nullbirthdate()");

        Response r = http.addPerson(jsonMap.get("add_notvalid6_nullbirthdate").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot6() {
        System.out.println("->test_getnot6()");

        Response r = http.get(jsonMap.get("getnot6").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPersonIncorrectBirthdate() {
        System.out.println();
        System.out.println("----testAddBadPersonNullBirthdate----");

        test_add_notvalid7_incorrectbirthdate();
        test_getnot7();

        System.out.println("----------------");
    }
    private void test_add_notvalid7_incorrectbirthdate() {
        System.out.println("->test_add_notvalid7_incorrectbirthdate()");

        Response r = http.addPerson(jsonMap.get("add_notvalid7_incorrectbirthdate").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot7() {
        System.out.println("->test_getnot7()");

        Response r = http.get(jsonMap.get("getnot7").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }
}
