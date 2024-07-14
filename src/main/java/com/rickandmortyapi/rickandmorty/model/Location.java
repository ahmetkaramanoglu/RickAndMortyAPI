package com.rickandmortyapi.rickandmorty.model;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String type;
    private String dimension;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Character> residents;
    public Location(String name, String type, String dimension) {
        //this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
    }
}
