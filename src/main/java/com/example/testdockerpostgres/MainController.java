package com.example.testdockerpostgres;

import com.example.testdockerpostgres.entity.Person;
import com.example.testdockerpostgres.entity.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
public class MainController {

    @Autowired
    PersonRepository repository;

    @Autowired
    PetRepository petRepository;

    @Operation(summary = "Add string to db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything ok",
                    content = {@Content(mediaType = "text/plain; charset=utf-8", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @GetMapping("/{name}")
    public ResponseEntity<String> hello(@Parameter(description = "Name for add into db") @PathVariable(required = false) String name) {
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

    @Operation(summary = "Add pet to db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet added",
                    content = {@Content(mediaType = "application/json;", schema = @Schema(implementation = Pet.class))}),
            @ApiResponse(responseCode = "406", description = "Pet already exists")})
    @PostMapping(value = "/pet")
    public ResponseEntity<Pet> savePet(@RequestBody Pet pet) {
        if (petRepository.findAllByName(pet.getName()).size() > 0) {
            return new ResponseEntity<>(new Pet(), HttpStatus.NOT_ACCEPTABLE);
        }

        Pet petSaved = petRepository.saveAndFlush(pet);
        return new ResponseEntity<>(petSaved, HttpStatus.OK);
    }

    @PostMapping(value = "/petName", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> savePet(@RequestBody HashMap<String, Object> payload) {
        System.out.println(payload.get("name"));
        return new ResponseEntity<>(payload.get("name"), HttpStatus.OK);
    }

    @GetMapping("/")
    public String main() {
        return "just main page";
    }
}
