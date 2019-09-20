package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.output.StatisticsDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid.CarDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid.PersonDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.CarService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("restServiceController")
public class RestServiceController {

	private final PersonService personService;
	private final CarService carService;
	private final Validator validator;

	@Autowired
	public RestServiceController(final PersonService personService, final CarService carService, final Validator validator) {
		this.personService = personService;
		this.carService = carService;
		this.validator = validator;
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity save_person(
			@Valid @RequestBody PersonDto4save dto,
			BindingResult bindingResult
	) {
		try {
			// VALIDATE ------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			if ( personService.existsById(dto.getId()) ) throw new Exception();
			//------------------------------------
			Person person = new Person();
			person.setId(dto.getId());
			person.setName(dto.getName());
			person.setBirthdate(dto.getBirthdate());

			personService.save(person);//can be add:@Transactional(rollbackFor = Exception.class)

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public ResponseEntity save_car(
			@Valid @RequestBody CarDto4save dto,
			BindingResult bindingResult
	) {
		try {
			// VALIDATE ------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			if ( carService.existsById(dto.getId()) ) throw new Exception();
			Person ownerPerson = personService.findById(dto.getOwnerId()).orElseThrow( () -> new Exception() );
			//------------------------------------
			String[] fullName = dto.getModel().split("-",2);

			Car car = new Car();
			car.setId(dto.getId());
			car.setHorsepower(dto.getHorsepower());
			car.setVendor(fullName[0]);
			car.setModel(fullName[1]);

			car.setPerson(ownerPerson);
//			car.setOwnerId(ownerPerson.getId());

			Set<ConstraintViolation<Car>> validates = validator.validate(car);
			if(validates.size() > 0) {
				String errors = validates.stream().map(v -> v.getMessage())
						.collect(Collectors.joining());//todo check for 2 and more
				throw new Exception(errors);
			}
			carService.save(car);//can be add:@Transactional(rollbackFor = Exception.class)

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
	public ResponseEntity<Person> get_personwithcars(
			Long personid //todo Long (not null)
	) {
		try {
	        if (personid == null) throw new Exception();

			if ( ! personService.existsById(personid) ) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			Person person = personService.findById(personid).get();
			if(person == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<Person>(
					person,
					HttpStatus.OK
			);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ResponseEntity<StatisticsDto> statistics() {
		try {
			Long personcount = personService.count();
			Long carcount = carService.count();
			Long uniquevendorcount = carService.countDistinctVendor();

			if(personcount == null || carcount == null || uniquevendorcount == null)
				throw new Exception();

			StatisticsDto statisticsDto = new StatisticsDto();
			statisticsDto.setPersoncount(personcount);
			statisticsDto.setCarcount(carcount);
			statisticsDto.setUniquevendorcount(uniquevendorcount);

			return new ResponseEntity<StatisticsDto>(
					statisticsDto,
					HttpStatus.OK
			);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.OK);
		}
	}

	//todo no rest, GET-reguest = edit state
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ResponseEntity clear() {
		try {
			carService.deleteAll();
			personService.deleteAll();
		} finally {
			return new ResponseEntity(HttpStatus.OK);
		}
	}


//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public Car test() {
//		Car car = new Car();
//		car.setId(0L);
//		car.setOwnerId(0L);
//		car.setModel("-------7");
//		car.setVendor("7-------");
//		car.setHorsepower(0);
//
//		if(true) throw new MyExc("123456789qwerty");
//
//		return  car; //=HttpStatus.OK//200
//	}
//
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = "/test2", method = RequestMethod.GET)
//	public Car test2() {
//		Car car = new Car();
//		car.setId(2L);
//		car.setOwnerId(2L);
//		car.setModel("22222*");
//		car.setVendor("*2222222");
//		car.setHorsepower(2);
//
//		if(true) throw new MyExc2("22222");
//
//		return  car; //=HttpStatus.OK//200
//	}
//
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = "/test3", method = RequestMethod.GET)
//	public Car test3() {
//		Car car = new Car();
//		car.setId(3L);
//		car.setOwnerId(3L);
//		car.setModel("333*");
//		car.setVendor("*3333");
//		car.setHorsepower(3);
//
//		if(true) throw new RuntimeException("3333");
//
//		return  car; //=HttpStatus.OK//200
//	}
//
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = "/test4", method = RequestMethod.GET)
//	public Car test4() throws Exception {
//		Car car = new Car();
//		car.setId(4L);
//		car.setOwnerId(4L);
//		car.setModel("4444*");
//		car.setVendor("*4444");
//		car.setHorsepower(4);
//
//		if(true) throw new Exception("4444");
//
//		return  car; //=HttpStatus.OK//200
//	}
}