package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Embedded
    private HotelLocationEntity location;





}
