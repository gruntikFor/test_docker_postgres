package com.example.testdockerpostgres;

import com.example.testdockerpostgres.entity.Person;
import com.example.testdockerpostgres.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByName(String name);
}


