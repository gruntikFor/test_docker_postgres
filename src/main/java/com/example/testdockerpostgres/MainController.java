package com.example.testdockerpostgres;

import com.example.swaggercodgenfirst.api.*;
import com.example.testdockerpostgres.entity.Person;
import com.example.testdockerpostgres.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MainController implements DefaultApi, NameApi {

    @Autowired
    PersonRepository repository;

    @Autowired
    PetRepository petRepository;

    @GetMapping("/{name}")
    public ResponseEntity<String> hello(@PathVariable(required = false) String name) {
        String newName = Objects.requireNonNullElse(name, "igor");
        Person person = new Person();
        person.setName(newName);

        repository.saveAndFlush(person);
        String retStr = "hello " + newName;

        if (name.equals("igor")) {
            return new ResponseEntity<>(retStr, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retStr, HttpStatus.OK);
    }

    @PostMapping(value = "/pet")
    public ResponseEntity<?> savePet(@RequestBody Pet pet) {
        if (petRepository.findAllByName(pet.getName()).size() > 0) {
            return new ResponseEntity<>(new Pet(), HttpStatus.NOT_ACCEPTABLE);
        }

        Pet petSaved = petRepository.saveAndFlush(pet);
        return new ResponseEntity<>(petSaved, HttpStatus.OK);
    }

    @PostMapping(value = "/petName", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> savePetMap(@RequestBody HashMap<String, Object> payload) {
        System.out.println(payload.get("name"));
        return new ResponseEntity<>(payload.get("name"), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> main() {
        return new ResponseEntity<>("just main page", HttpStatus.OK);
    }

}
