package com.example.testdockerpostgres.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "pet", schema = "cursovaya", catalog = "kurss")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int age;
    String name;

    public Pet(Long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Pet() {
    }
}
