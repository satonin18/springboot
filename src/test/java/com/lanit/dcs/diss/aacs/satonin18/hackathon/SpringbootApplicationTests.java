package com.lanit.dcs.diss.aacs.satonin18.hackathon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { SpringbootApplication.class })
@WebAppConfiguration
public class SpringbootApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    private static final String URL_CLEAR = "/clear";
    private static final String URL_ADD_PERSON = "/person";
    private static final String URL_ADD_CAR = "/car";
    private static final String URL_GET_PERSON = "/personwithcars";
    private static final String URL_STATISTICS = "/statistics";


    @Before
    public void setUp() throws Exception {
        System.out.println("---------@Test-@Before--------------");

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        this.mockMvc.perform(get("/clear"))
                .andExpect(status().isOk());
    }
    @After
    public void tearDown() throws Exception {
        System.out.println("---------@Test-@After--------------");
    }

    @Test
    public void testClear() throws Exception {
        System.out.println("----testClear----");

        String add_person = "{\"id\":\"-1\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(get(URL_CLEAR))
                .andExpect(status().isOk());

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_CLEAR))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-1")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isNotFound());

        System.out.println("----------------");
    }

    @Test
    public void testAddvalid_person() throws Exception {
        System.out.println("----testAddvalid_person----");

        String add_person = "{\"id\":-10,\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\",\"cars\":[]}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-10")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-10"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddvalid_personLess18() throws Exception {
        System.out.println("----testAddvalid_personLess18----");

        String add_person  = "{\"id\":\"-20\",\"name\":\"valid_person2\",\"birthdate\":\"01.12.2017\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-20")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-20"))
                .andExpect(jsonPath("$.name").value("valid_person2"))
                .andExpect(jsonPath("$.birthdate").value("01.12.2017"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddvalid_personEmptyName() throws Exception {
        System.out.println("----testAddvalid_personEmptyName----");

        String add_person  = "{\"id\":\"-30\",\"name\":\"\",\"birthdate\":\"01.12.2017\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-30")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-30"))
                .andExpect(jsonPath("$.name").value(""))
                .andExpect(jsonPath("$.birthdate").value("01.12.2017"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonFutureBirthdate() throws Exception {
        System.out.println("----testAddValidFutureBirthdate----");

        String add_person = "{\"id\":\"-39\",\"name\":\"valid\",\"birthdate\":\"01.12.2117\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-39")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isNotFound());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonEmptyId() throws Exception {
        System.out.println("----testAddBadPersonEmptyId----");

        String add_person = "{\"id\":\"\",\"name\":\"valid\",\"birthdate\":\"01.12.2017\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonNullId() throws Exception {
        System.out.println("----testAddBadPersonNullId----");


        String add_person = "{\"name\":\"valid\",\"birthdate\":\"01.12.2017\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonNullName() throws Exception {
        System.out.println("----testAddBadPersonNullName----");

        String add_person = "{\"id\":\"-70\",\"birthdate\":\"01.12.2017\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-70")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isNotFound());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonNullBirthdate() throws Exception {
        System.out.println("----testAddBadPersonNullBirthdate----");

        String add_person = "{\"id\":\"-80\",\"name\":\"valid\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-80")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isNotFound());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonIncorrectBirthdate() throws Exception {
        System.out.println("----testAddBadPersonIncorrectBirthdate----");

        String add_person = "{\"id\":\"-90\",\"name\":\"valid_person2\",\"birthdate\":\"2017-01-01\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-90")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isNotFound());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonLanientBirthdate() throws Exception {
        System.out.println("----testAddBadPersonLanientBirthdate----");

        String add_person = "{\"id\":\"-100\",\"name\":\"valid_person2\",\"birthdate\":\"01.15.2017\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-100")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isNotFound());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPersonSymbolsBirthdate() throws Exception {
        System.out.println("----testAddBadPersonSymbolsBirthdate----");

        String add_person = "{\"id\":\"-110\",\"name\":\"valid_person2\",\"birthdate\":\"sds\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-110")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isNotFound());

        System.out.println("----------------");
    }

    @Test
    public void testGetBadEmptyNullPassword() throws Exception {
        System.out.println("----testGetBadEmptyNullPassword----");

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "     asdsad")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isBadRequest());

        System.out.println("----------------");
    }

    @Test
    public void testAddValidCar1() throws Exception {
        System.out.println("----testAddValidCar1----");

        String add_person = "{\"id\":\"-130\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-130\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-130\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-130")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-130"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X5')]", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X5')].horsepower", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X5')].horsepower").value(100))
        ;


        System.out.println("----------------");
    }

    @Test
    public void testAddValidCars2() throws Exception {
        System.out.println("----testAddValidCars2----");

        String add_person = "{\"id\":\"-140\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-140\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-140\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        String add_car2 = "{\"id\":\"-139\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-140\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isOk());

        String add_car3 = "{\"id\":\"-138\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-140\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car3))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-140")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-140"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(3)))

                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X3')]", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='Lada-Devyatka')].horsepower", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='Lada-Devyatka')].horsepower").value(50))
                ;
        System.out.println("----------------");
    }

    @Test
    public void testAddValidCars3_modelformat() throws Exception {
        System.out.println("----testAddValidCars3_modelformat----");

        String add_person = "{\"id\":\"-150\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-149\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-150\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        String add_car2 = "{\"id\":\"-148\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-150\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-150")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-150"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(2)))

                .andExpect(jsonPath("$.cars[?(@.model=='Lada-Devyatka')].horsepower", hasSize(0)))
                .andExpect(jsonPath("$.cars[?(@.model=='La-da-Devyatka')].horsepower", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='La-da-Devyatka')].horsepower").value(50))
                .andExpect(jsonPath("$.cars[?(@.model=='La-da-')].horsepower", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='La-da-')].horsepower").value(50))
        ;
        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_modelformat() throws Exception {
        System.out.println("----testAddBadCar_modelformat----");

        String add_person = "{\"id\":\"-160\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-160\",\"model\":\"-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-160\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-160")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-160"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_negative_horsepower() throws Exception {
        System.out.println("----testAddBadCar_negative_horsepower----");

        String add_person = "{\"id\":\"-170\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-170\",\"model\":\"A-B\",\"horsepower\":-50,\"ownerId\":\"-170\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-170")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-170"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_less18years() throws Exception {
        System.out.println("----testAddBadCar_less18years----");

        String add_person = "{\"id\":\"-180\",\"name\":\"valid_person2\",\"birthdate\":\"01.12.2017\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-180\",\"model\":\"A-B\",\"horsepower\":50,\"ownerId\":\"-180\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-180")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-180"))
                .andExpect(jsonPath("$.name").value("valid_person2"))
                .andExpect(jsonPath("$.birthdate").value("01.12.2017"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_emptyNullModel() throws Exception {
        System.out.println("----testAddBadCar_emptyNullModel----");

        String add_person = "{\"id\":\"-190\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-190\",\"model\":\"\",\"horsepower\":50,\"ownerId\":\"-190\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isBadRequest());

        String add_car2 = "{\"id\":\"-189\",\"horsepower\":50,\"ownerId\":\"-190\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-190")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-190"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_emptyNullId() throws Exception {
        System.out.println("----testAddBadCar_emptyNullModel----");

        String add_person = "{\"id\":\"-200\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-200\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isBadRequest());

        String add_car2 = "{\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-200\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-200")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-200"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_emptyNullZero_horsepower() throws Exception {
        System.out.println("----testAddBadCar_emptyNullZero_horsepower----");

        String add_person = "{\"id\":\"-210\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-210\",\"model\":\"BMW-X3\",\"horsepower\":\"\",\"ownerId\":\"-210\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isBadRequest());

        String add_car2 = "{\"id\":\"-209\",\"model\":\"BMW-X3\",\"ownerId\":\"-210\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isBadRequest());

        String add_car3 = "{\"id\":\"-208\",\"model\":\"BMW-X3\",\"horsepower\":\"0\",\"ownerId\":\"-210\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car3))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-210")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-210"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(0)));

        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_emptyNull_ownerId() throws Exception {
        System.out.println("----testAddBadCar_emptyNull_ownerId----");

        String add_person1 = "{\"id\":\"-220\",\"model\":\"BMW-X3\",\"horsepower\":\"100\",\"ownerId\":\"\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person1))
                .andExpect(status().isBadRequest());

        String add_person2 = "{\"id\":\"-219\",\"model\":\"BMW-X3\",\"horsepower\":\"100\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person2))
                .andExpect(status().isBadRequest());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadPerson_unique() throws Exception {
        System.out.println("----testAddBadPerson_unique----");

        String add_car1 = "{\"id\":\"-250\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        String add_car2 = "{\"id\":\"-250\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isBadRequest());

        System.out.println("----------------");
    }

    @Test
    public void testAddBadCar_unique() throws Exception {
        System.out.println("----testAddBadCar_unique----");

        String add_person = "{\"id\":\"-260\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-260\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-260\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        String add_car2 = "{\"id\":\"-260\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-260\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_GET_PERSON)
                .param("personid", "-260")
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("-260"))
                .andExpect(jsonPath("$.name").value("valid_person1"))
                .andExpect(jsonPath("$.birthdate").value("01.01.2000"))
                .andExpect(jsonPath("$.cars", hasSize(1)))

                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X5')].horsepower", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X5')].horsepower").value(100))
        ;;

        System.out.println("----------------");
    }

    @Test
    public void testStatistics() throws Exception {
        System.out.println("----testStatistics----");

        this.mockMvc.perform(get(URL_STATISTICS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.personcount").value(0))
                .andExpect(jsonPath("$.carcount").value(0))
                .andExpect(jsonPath("$.uniquevendorcount").value(0));

        String add_person = "{\"id\":\"-230\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-230\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-230\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        String add_car2 = "{\"id\":\"-229\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-230\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isOk());

        String add_car3 = "{\"id\":\"-228\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car3))
                .andExpect(status().isOk());

        String add_car4 = "{\"id\":\"-227\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car4))
                .andExpect(status().isOk());

        String add_car5 = "{\"id\":\"-226\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-230\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car5))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_STATISTICS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.personcount").value(1))
                .andExpect(jsonPath("$.carcount").value(5))
                .andExpect(jsonPath("$.uniquevendorcount").value(3));

        System.out.println("----------------");
    }

    @Test
    public void testStatistics2notAdd() throws Exception {
        System.out.println("----testStatistics2notAdd----");

        this.mockMvc.perform(get(URL_STATISTICS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.personcount").value(0))
                .andExpect(jsonPath("$.carcount").value(0))
                .andExpect(jsonPath("$.uniquevendorcount").value(0));

        String add_person = "{\"id\":\"-240\",\"name\":\"valid_person1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-240\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-240\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        String add_car2= "{\"id\":\"-239\",\"model\":\"\",\"horsepower\":50,\"ownerId\":\"-240\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get(URL_STATISTICS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.personcount").value(1))
                .andExpect(jsonPath("$.carcount").value(1))
                .andExpect(jsonPath("$.uniquevendorcount").value(1));

        System.out.println("----------------");
    }
    @Test
    public void testStatistics3ignorCase() throws Exception {
        System.out.println("----testStatistics3ignorCase----");

        this.mockMvc.perform(get(URL_STATISTICS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.personcount").value(0))
                .andExpect(jsonPath("$.carcount").value(0))
                .andExpect(jsonPath("$.uniquevendorcount").value(0));

        String add_person = "{\"id\":\"-270\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";

        this.mockMvc.perform(post(URL_ADD_PERSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_person))
                .andExpect(status().isOk());

        String add_car1 = "{\"id\":\"-270\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-270\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car1))
                .andExpect(status().isOk());

        String add_car2 = "{\"id\":\"-269\",\"model\":\"BmW-X3\",\"horsepower\":100,\"ownerId\":\"-270\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car2))
                .andExpect(status().isOk());

        String add_car3 = "{\"id\":\"-268\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-270\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car3))
                .andExpect(status().isOk());

        String add_car4 = "{\"id\":\"-267\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-270\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car4))
                .andExpect(status().isOk());

        String add_car5 = "{\"id\":\"-266\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-270\"}";

        this.mockMvc.perform(post(URL_ADD_CAR)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(add_car5))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL_STATISTICS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.personcount").value(1))
                .andExpect(jsonPath("$.carcount").value(5))
                .andExpect(jsonPath("$.uniquevendorcount").value(3));
        System.out.println("----------------");
    }
}
