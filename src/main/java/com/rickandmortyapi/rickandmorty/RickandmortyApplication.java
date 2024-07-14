package com.rickandmortyapi.rickandmorty;

import com.rickandmortyapi.rickandmorty.model.*;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.repository.CharacterRepository;
import com.rickandmortyapi.rickandmorty.repository.LocationRepository;
import com.rickandmortyapi.rickandmorty.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Set;


@AllArgsConstructor
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RickandmortyApplication implements CommandLineRunner {
	private final CharacterRepository characterRepository;
	private final LocationRepository locationRepository;
	private final UserRepository userRepository;
    public static void main(String[] args) {
		SpringApplication.run(RickandmortyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		characterRepository.deleteAll();
		locationRepository.deleteAll();
		userRepository.deleteAll();

		Location earth = new Location("Earth", "Planet", "Dimension C-137");
		Character rick = new Character( "Rick Sanchez", "Alive", "Human", "","Male", earth);
		Character morty = new Character("Morty Smith", "Alive", "Human", "","Male",earth);
		Character summer = new Character("Summer Smith", "Alive", "Human", "","Female",earth);
		Character jerry = new Character("Jerry Smith", "Alive", "Human", "","Male",earth);
		Character ahmet = new Character("AHMET Smith", "Alive", "Human", "","Male",earth);
		Character omer = new Character("OMER Smith", "Alive", "Human", "","Male",earth);
		Set<Character> characters = Set.of(rick, morty, summer, jerry, ahmet, omer);
		//Character'e earth verince ve cascade  yapisi All oldugu icin arkada location tablosuna da insert yapilir. Bu yuzden location.setResidents(characters) yapmamiza gerek yok. DIYE DUSUNUYORUM.
		characterRepository.saveAll(characters);
		System.out.println(rick.getId());

	}
}
