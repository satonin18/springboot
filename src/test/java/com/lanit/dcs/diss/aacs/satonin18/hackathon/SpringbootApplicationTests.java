package com.lanit.dcs.diss.aacs.satonin18.hackathon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.response.Response;

import static com.lanit.dcs.diss.aacs.satonin18.hackathon.Templates.*;
import static org.junit.Assert.assertEquals;

//TODO ИЗ ЗА ТОГО ЧТО МЕТОДЫ ЗАПУСКАЮТСЯ В ХАОТИЧНОМ ПОРЯДКЕ ТО НУЖНО ПЕРЕД КАЖДЫМ ПОСТВАИТЬ ОЧИСТКУ
//TODO JSON-parsing and check every var

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
    public void testAddvalid_person() {
        System.out.println();
        System.out.println("----testAddvalid_person----");

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
    public void testAddvalid_personLess18() {
        System.out.println();
        System.out.println("----testAddvalid_personLess18----");

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
    public void testAddvalid_personEmptyName() {
        System.out.println();
        System.out.println("----testAddvalid_personEmptyName----");

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
        System.out.println("----testAddBadPersonIncorrectBirthdate----");

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

    @Test
    public void testAddBadPersonLanientBirthdate() {
        System.out.println();
        System.out.println("----testAddBadPersonLanientBirthdate----");

        test_add_notvalid8_lanientbirthdate();
        test_getnot8();

        System.out.println("----------------");
    }
    private void test_add_notvalid8_lanientbirthdate() {
        System.out.println("->test_add_notvalid8_lanientbirthdate()");

        Response r = http.addPerson(jsonMap.get("add_notvalid8_lanientbirthdate").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot8() {
        System.out.println("->test_getnot8()");

        Response r = http.get(jsonMap.get("getnot8").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPersonSymbolsBirthdate() {
        System.out.println();
        System.out.println("----testAddBadPersonSymbolsBirthdate----");

        test_add_notvalid9_symbolsbirthdate();
        test_getnot9();

        System.out.println("----------------");
    }
    private void test_add_notvalid9_symbolsbirthdate() {
        System.out.println("->test_add_notvalid9_symbolsbirthdate()");

        Response r = http.addPerson(jsonMap.get("add_notvalid9_symbolsbirthdate").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot9() {
        System.out.println("->test_getnot9()");

        Response r = http.get(jsonMap.get("getnot9").getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testGetBadEmptyNullPassword() {
        System.out.println();
        System.out.println("----testGetBadEmptyNullPassword----");

        test_getnot_empty_personid();
        test_getnot_null_personid();
        test_getnot_personid_format();

        System.out.println("----------------");
    }
    private void test_getnot_empty_personid() {
        System.out.println("->test_getnot_empty_personid()");

        Response r = http.getEmptyValueParam();
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot_null_personid() {
        System.out.println("->test_getnot_null_personid()");

        Response r = http.getWithoutParam();
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getnot_personid_format() {
        System.out.println("->test_getnot_personid_format()");

        Response r = http.getWithBadStringParam("     asdsad");
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddValidCar1() {
        System.out.println();
        System.out.println("----testAddValidCar1----");

        test_add_valid_person1();
        test_add_car1();
        test_getcar1();

        System.out.println("----------------");
    }
    private void test_add_valid_person1() {
        System.out.println("->test_add_valid_person1()");

        Response r = http.addPerson(jsonMap.get("add_valid_person1").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car1() {
        System.out.println("->test_add_car1()");

        Response r = http.addCar(jsonMap.get("add_car1").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcar1() {
        System.out.println("->test_getcar1()");

        Response r = http.get(jsonMap.get("getcar1").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcar1").getJson(), r.getBody().print());

        System.out.println("<-");
    }


    @Test
    public void testAddValidCars2() {
        System.out.println();
        System.out.println("----testAddValidCars2----");

        test_add_valid_person2();
        test_add_car2();
        test_add_car3();
        test_add_car4();
        test_getcars1();

        System.out.println("----------------");
    }
    private void test_add_valid_person2() {
        System.out.println("->test_add_valid_person2()");

        Response r = http.addPerson(jsonMap.get("add_valid_person2").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car2() {
        System.out.println("->test_add_car2()");

        Response r = http.addCar(jsonMap.get("add_car2").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car3() {
        System.out.println("->test_add_car3()");

        Response r = http.addCar(jsonMap.get("add_car3").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car4() {
        System.out.println("->test_add_car4()");

        Response r = http.addCar(jsonMap.get("add_car4").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcars1() {
        System.out.println("->test_getcars1()");

        Response r = http.get(jsonMap.get("getcars1").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcars1").getJson(), r.getBody().print());

        System.out.println("<-");
    }


    @Test
    public void testAddValidCars3_modelformat() {
        System.out.println();
        System.out.println("----testAddValidCars3_modelformat----");

        test_add_valid_person3();
        test_add_car5();
        test_add_car6();
        test_getcars2();

        System.out.println("----------------");
    }
    private void test_add_valid_person3() {
        System.out.println("->test_add_valid_person3()");

        Response r = http.addPerson(jsonMap.get("add_valid_person3").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car5() {
        System.out.println("->test_add_car5()");

        Response r = http.addCar(jsonMap.get("add_car5").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car6() {
        System.out.println("->test_add_car6()");

        Response r = http.addCar(jsonMap.get("add_car6").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcars2() {
        System.out.println("->test_getcars2()");

        Response r = http.get(jsonMap.get("getcars2").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcars2").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddBadCar_modelformat() {
        System.out.println();
        System.out.println("----testAddBadCar_modelformat----");

        test_add_valid_person4();
        test_add_not_car7();
        test_getcar2();

        System.out.println("----------------");
    }
    private void test_add_valid_person4() {
        System.out.println("->test_add_valid_person4()");

        Response r = http.addPerson(jsonMap.get("add_valid_person4").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car7() {
        System.out.println("->test_add_not_car7()");

        Response r = http.addCar(jsonMap.get("add_not_car7").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcar2() {
        System.out.println("->test_getcar2()");

        Response r = http.get(jsonMap.get("getcar2").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcar2").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddBadCar_negative_horsepower() {
        System.out.println();
        System.out.println("----testAddBadCar_negative_horsepower----");

        test_add_valid_person5();
        test_add_not_car8();
        test_getcar3();

        System.out.println("----------------");
    }
    private void test_add_valid_person5() {
        System.out.println("->test_add_valid_person5()");

        Response r = http.addPerson(jsonMap.get("add_valid_person5").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car8() {
        System.out.println("->test_add_not_car8()");

        Response r = http.addCar(jsonMap.get("add_not_car8").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcar3() {
        System.out.println("->test_getcar3()");

        Response r = http.get(jsonMap.get("getcar3").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcar3").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddBadCar_less18years() {
        System.out.println();
        System.out.println("----testAddBadCar_less18years----");

        test_add_valid_person_less18years();
        test_add_not_car9();
        test_getcar4();

        System.out.println("----------------");
    }
    private void test_add_valid_person_less18years() {
        System.out.println("->test_add_valid_person_less18years()");

        Response r = http.addPerson(jsonMap.get("add_valid_person_less18years").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car9() {
        System.out.println("->test_add_not_car9()");

        Response r = http.addCar(jsonMap.get("add_not_car9").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcar4() {
        System.out.println("->test_getcar4()");

        Response r = http.get(jsonMap.get("getcar4").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcar4").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddBadCar_emptyNullModel() {
        System.out.println();
        System.out.println("----testAddBadCar_emptyNullModel----");

        test_add_valid_person6();
        test_add_not_car10();
        test_add_not_car11();
        test_getcar5();

        System.out.println("----------------");
    }
    private void test_add_valid_person6() {
        System.out.println("->test_add_valid_person6()");

        Response r = http.addPerson(jsonMap.get("add_valid_person6").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car10() {
        System.out.println("->test_add_not_car10()");

        Response r = http.addCar(jsonMap.get("add_not_car10").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car11() {
        System.out.println("->test_add_not_car11()");

        Response r = http.addCar(jsonMap.get("add_not_car11").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcar5() {
        System.out.println("->test_getcar5()");

        Response r = http.get(jsonMap.get("getcar5").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcar5").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testAddBadCar_emptyNullId() {
        System.out.println();
        System.out.println("----testAddBadCar_emptyNullModel----");

        test_add_valid_person7();
        test_add_not_car12();
        test_add_not_car13();
        test_getcar6();

        System.out.println("----------------");
    }
    private void test_add_valid_person7() {
        System.out.println("->test_add_valid_person7()");

        Response r = http.addPerson(jsonMap.get("add_valid_person7").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car12() {
        System.out.println("->test_add_not_car12()");

        Response r = http.addCar(jsonMap.get("add_not_car12").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car13() {
        System.out.println("->test_add_not_car13()");

        Response r = http.addCar(jsonMap.get("add_not_car13").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcar6() {
        System.out.println("->test_getcar6()");

        Response r = http.get(jsonMap.get("getcar6").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcar6").getJson(), r.getBody().print());

        System.out.println("<-");
    }



    @Test
    public void testAddBadCar_emptyNullZero_horsepower() {
        System.out.println();
        System.out.println("----testAddBadCar_emptyNullZero_horsepower----");

        test_add_valid_person8();
        test_add_not_car14();
        test_add_not_car15();
        test_add_not_car16();
        test_getcar7();

        System.out.println("----------------");
    }
    private void test_add_valid_person8() {
        System.out.println("->test_add_valid_person8()");

        Response r = http.addPerson(jsonMap.get("add_valid_person8").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car14() {
        System.out.println("->test_add_not_car14()");

        Response r = http.addCar(jsonMap.get("add_not_car14").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car15() {
        System.out.println("->test_add_not_car15()");

        Response r = http.addCar(jsonMap.get("add_not_car15").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car16() {
        System.out.println("->test_add_not_car16()");

        Response r = http.addCar(jsonMap.get("add_not_car16").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_getcar7() {
        System.out.println("->test_getcar7()");

        Response r = http.get(jsonMap.get("getcar7").getId());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("getcar7").getJson(), r.getBody().print());

        System.out.println("<-");
    }


    @Test
    public void testAddBadCar_emptyNull_ownerId() {
        System.out.println();
        System.out.println("----testAddBadCar_emptyNull_ownerId----");

        test_add_not_car17();
        test_add_not_car18();

        System.out.println("----------------");
    }
    private void test_add_not_car17() {
        System.out.println("->test_add_not_car17()");

        Response r = http.addCar(jsonMap.get("add_not_car17").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_car18() {
        System.out.println("->test_add_not_car18()");

        Response r = http.addCar(jsonMap.get("add_not_car18").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadPerson_unique() {
        System.out.println();
        System.out.println("----testAddBadPerson_unique----");

        test_add_valid_person9();
        test_add_not_valid_uniqueperson();

        System.out.println("----------------");
    }
    private void test_add_valid_person9() {
        System.out.println("->test_add_valid_person9()");

        Response r = http.addPerson(jsonMap.get("add_valid_person9").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_valid_uniqueperson() {
        System.out.println("->test_add_not_valid_uniqueperson()");

        Response r = http.addPerson(jsonMap.get("add_not_valid_uniqueperson").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testAddBadCar_unique() {
        System.out.println();
        System.out.println("----testAddBadCar_unique----");

        test_add_valid_person10();
        test_add_car7();
        test_add_not_valid_unique_car();

        System.out.println("----------------");
    }
    private void test_add_valid_person10() {
        System.out.println("->test_add_valid_person10()");

        Response r = http.addPerson(jsonMap.get("add_valid_person10").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car7() {
        System.out.println("->test_add_car7()");

        Response r = http.addCar(jsonMap.get("add_car7").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_valid_unique_car() {
        System.out.println("->test_add_not_valid_unique_car()");

        Response r = http.addCar(jsonMap.get("add_not_valid_unique_car").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }

    @Test
    public void testStatistics() {
        System.out.println();
        System.out.println("----testAddValidCars2----");

        http.clear();
        test_empty_statistics();

        test_add_valid_person11();
        test_add_car8();
        test_add_car9();
        test_add_car10();
        test_add_car11();
        test_add_car12();
        test_statistics();

        System.out.println("----------------");
    }
    private void test_empty_statistics() {
        System.out.println("->test_empty_statistics()");

        Response r = http.statistics();
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("empty_statistics").getJson(), r.getBody().print());

        System.out.println("<-");
    }
    private void test_add_valid_person11() {
        System.out.println("->test_add_valid_person11()");

        Response r = http.addPerson(jsonMap.get("add_valid_person11").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car8() {
        System.out.println("->test_add_car8()");

        Response r = http.addCar(jsonMap.get("add_car8").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car9() {
        System.out.println("->test_add_car9()");

        Response r = http.addCar(jsonMap.get("add_car9").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car10() {
        System.out.println("->test_add_car10()");

        Response r = http.addCar(jsonMap.get("add_car10").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car11() {
        System.out.println("->test_add_car11()");

        Response r = http.addCar(jsonMap.get("add_car11").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_car12() {
        System.out.println("->test_add_car12()");

        Response r = http.addCar(jsonMap.get("add_car12").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_statistics() {
        System.out.println("->test_statistics()");

        Response r = http.statistics();
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("statistics").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testStatistics2notAdd() {
        System.out.println();
        System.out.println("----testStatistics2notAdd----");

        http.clear();
        test_empty_statistics();

        test_add_valid_person12();
        test_add_not_valid_person13_nullname();
        test_add_valid_car();
        test_add_not_valid_car_empty_model();
        test_statistics2();

        System.out.println("----------------");
    }
    private void test_add_valid_person12() {
        System.out.println("->test_add_valid_person12()");

        Response r = http.addPerson(jsonMap.get("add_valid_person12").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_valid_person13_nullname() {
        System.out.println("->test_add_not_valid_person13_nullname()");

        Response r = http.addPerson(jsonMap.get("add_not_valid_person13_nullname").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_valid_car() {
        System.out.println("->test_add_valid_car()");

        Response r = http.addCar(jsonMap.get("add_valid_car").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_not_valid_car_empty_model() {
        System.out.println("->test_add_not_valid_car_empty_model()");

        Response r = http.addCar(jsonMap.get("add_not_valid_car_empty_model").getJson());
        assertEquals(HttpStatus.BAD_REQUEST.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_statistics2() {
        System.out.println("->test_statistics2()");

        Response r = http.statistics();
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("statistics2").getJson(), r.getBody().print());

        System.out.println("<-");
    }

    @Test
    public void testStatistics3ignorCase() {
        System.out.println();
        System.out.println("----testStatistics3ignorCase----");

        http.clear();
        test_empty_statistics();

        test_add_valid_person13();
        test_add_valid_car1();
        test_add_valid_car2();
        test_add_valid_car3();
        test_add_valid_car4();
        test_add_valid_car5();
        test_statistics3();

        System.out.println("----------------");
    }
    private void test_add_valid_person13() {
        System.out.println("->test_add_valid_person13()");

        Response r = http.addPerson(jsonMap.get("add_valid_person13").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_valid_car1() {
        System.out.println("->test_add_valid_car1()");

        Response r = http.addCar(jsonMap.get("add_valid_car1").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_valid_car2() {
        System.out.println("->test_add_valid_car2()");

        Response r = http.addCar(jsonMap.get("add_valid_car2").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_valid_car3() {
        System.out.println("->test_add_valid_car3()");

        Response r = http.addCar(jsonMap.get("add_valid_car3").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_valid_car4() {
        System.out.println("->test_add_valid_car4()");

        Response r = http.addCar(jsonMap.get("add_valid_car4").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_add_valid_car5() {
        System.out.println("->test_add_valid_car5()");

        Response r = http.addCar(jsonMap.get("add_valid_car5").getJson());
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());

        System.out.println("<-");
    }
    private void test_statistics3() {
        System.out.println("->test_statistics3()");

        Response r = http.statistics();
        assertEquals(HttpStatus.OK.value(), r.getStatusCode());
        //todo check vars -> jsonParsing->everyVar==requestVar
        //simple approach
        assertEquals(jsonMap.get("statistics3").getJson(), r.getBody().print());

        System.out.println("<-");
    }

}
