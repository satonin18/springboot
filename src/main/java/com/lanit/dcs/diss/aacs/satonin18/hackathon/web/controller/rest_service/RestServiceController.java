package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.output.StatisticsDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid.CarDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid.PersonDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.CarService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.StatisticsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("restServiceController")
public class RestServiceController {

	private final PersonService personService;
	private final CarService carService;
	private final StatisticsService statisticsService;
	private final Validator validatorDto;

	@Autowired
	public RestServiceController(
			final PersonService personService,
			final CarService carService,
			final StatisticsService statisticsService,
			final Validator validatorDto
	) {
		this.personService = personService;
		this.carService = carService;
		this.statisticsService = statisticsService;
		this.validatorDto = validatorDto;
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity save_person(
			@Valid @RequestBody PersonDto4save dto,
			BindingResult bindingResult
	) {
		try {
			// VALIDATE DTO -----------------------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			// MAPPING -----------------------------------------------------
			Person person = new Person();
			ModelMapper mapper = new ModelMapper();
			mapper.map(dto, person);
			// ----------------------------------------------------------------------
			personService.savePerson(person); // -> validation entity

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
			// VALIDATE DTO ----------------------------------------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			// todo replace on custom Mapper -----------------------------------------------
//			ModelMapper mapper = new ModelMapper();
//			mapper.addMappings(new PropertyMap<CarDto4save, Car>() {
//				protected void configure() {
//					String[] fullName = source.getModel().split("-",2);
//					map().setVendor(fullName[0]);
//					map().setModel(fullName[1]);
//					Person ownerPerson = personService.findById(source.getOwnerId()).orElseThrow( () -> new RuntimeException("no find ownerPerson") );
//					map().setPerson(ownerPerson);
//				}
//			});
//			mapper.map(dto, car);
			// MAPPING ----------------------------------------------------------------------
			Car car = new Car();
			car.setId(dto.getId());
			car.setHorsepower(dto.getHorsepower());
			String[] fullName = dto.getModel().split("-",2);
			car.setVendor(fullName[0]);
			car.setModel(fullName[1]);
			Person ownerPerson = personService.findById(dto.getOwnerId()).orElseThrow( () -> new Exception() );
			car.setPerson(ownerPerson);
			// VALIDATE ENTITY -----------------------------------------------------------------
			carService.saveCar(car);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	//todo can return PersonWithCarsDto from mapping
	@RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
	public ResponseEntity<Person> get_personwithcars(
			Long personid
	) {
		try {
	        if(personid == null) throw new Exception();

			Person person = personService.getPerson(personid);

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
			StatisticsDto statisticsDto = statisticsService.getStatisticsDto();

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