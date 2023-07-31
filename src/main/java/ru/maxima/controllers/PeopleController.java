package ru.maxima.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maxima.dto.PersonDTO;
import ru.maxima.exceptions.PersonErrorResponse;
import ru.maxima.exceptions.PersonNotSuccessCreatedException;
import ru.maxima.models.Person;
import ru.maxima.services.PeopleService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping()
    public List<PersonDTO> getAllPeople() {
        return peopleService.findAll();
    }


    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable int id) {
        return peopleService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
            throw new PersonNotSuccessCreatedException(builder.toString());
        }

        peopleService.save(convertToPerson(personDTO));


        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        return person;
    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotSuccessCreatedException ex) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(ex.getLocalizedMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
