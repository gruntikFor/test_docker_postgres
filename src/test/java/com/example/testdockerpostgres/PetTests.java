package com.example.testdockerpostgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PetTests {

    @Autowired
    PetRepository petRepository;

    @Test
    public void testFindByName() {
        System.out.println(petRepository.findAllByName("igor"));
    }
}
