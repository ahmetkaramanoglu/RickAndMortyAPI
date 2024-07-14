package com.rickandmortyapi.rickandmorty.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "Characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    @Id
    @UuidGenerator
    private String id;

    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "location_id")
    private Location location;

    //Burda builder design patterni kullanilabilir fakat 1 tane constructor oldugu icin kullanmadim.
    public Character(String name, String status, String species, String type, String gender, Location location) {
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.location = location;
    }
}
