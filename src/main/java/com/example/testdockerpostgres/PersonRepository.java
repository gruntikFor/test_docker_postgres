package com.example.testdockerpostgres;

import com.example.testdockerpostgres.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}


