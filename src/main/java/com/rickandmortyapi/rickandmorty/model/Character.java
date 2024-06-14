package com.rickandmortyapi.rickandmorty.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "Characters")
@Getter
@Setter
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

    //Burda builder design patterni kullanilabilir fakat 1 tane constructor oldugu icin kullanmadim.
    public Character(String name, String status, String species, String type, String gender, Location location) {
        //this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.location = location;
    }



    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "location_id",nullable = false)
    private Location location;


}
