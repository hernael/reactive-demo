package com.example;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@javax.persistence.Entity
@Getter
@Setter
@Table(name = "demo")
public class Entity {

    @Id
    private Long id;

    private String description;
}
