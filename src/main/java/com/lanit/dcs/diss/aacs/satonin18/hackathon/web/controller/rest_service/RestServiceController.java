package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.output.StatisticsDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid.CarDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid.PersonDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.CarService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.StatisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

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
			@Valid @RequestBody PersonDto dto,
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
			@Valid @RequestBody CarDto dto,
			BindingResult bindingResult
	) {
		try {
			// VALIDATE DTO ----------------------------------------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			// MAPPING todo replace on custom Mapper -----------------------------------------------
			Car car = new Car();
			car.setId(dto.getId());
			car.setHorsepower(dto.getHorsepower());
			String[] fullName = dto.getModel().split("-",2);
			car.setVendor(fullName[0]);
			car.setModel(fullName[1]);
			Person ownerPerson = personService.findById(dto.getOwnerId()).orElseThrow( () -> new Exception() );
			car.setPerson(ownerPerson);
			// VALIDATE ENTITY -----------------------------------------------------------------
			carService.saveCar(car); // -> validation entity

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

}