package ru.maxima.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.dto.PersonDTO;
import ru.maxima.models.Person;
import ru.maxima.repositories.PeopleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, ModelMapper modelMapper) {
        this.peopleRepository = peopleRepository;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public List<PersonDTO> findAll() {
        return peopleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PersonDTO findOne(int id) {
        return convertToPersonDTO(peopleRepository.findById(id).orElse(null));
    }

    @Transactional
    public Person findFirstByNameAndAge(String name,int age) {
        return peopleRepository.findFirstByNameAndAge(name,age).orElse(null);
    }

    @Transactional
    public void save(Person person) {

        enrich(person);
        peopleRepository.save(person);
    }

    private void enrich(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setRemoved(false);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
