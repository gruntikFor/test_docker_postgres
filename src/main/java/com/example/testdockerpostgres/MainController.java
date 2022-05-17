package com.example.testdockerpostgres;

import com.example.testdockerpostgres.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MainController {

    @Autowired
    PersonRepository repository;

    @GetMapping("/{name}")
    public String hello(@PathVariable(required = false) String name) {
        String newName = Objects.requireNonNullElse(name, "igor");
        Person person = new Person();
        person.setName(newName);

        repository.saveAndFlush(person);
        return "hello " + newName;
    }

    @GetMapping("/")
    public String main(){
        return "just main page";
    }
}
