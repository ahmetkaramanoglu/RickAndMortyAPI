package com.rickandmortyapi.rickandmorty.request;

import com.rickandmortyapi.rickandmorty.dto.CharacterLocationDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCharacterRequest {
    @NotBlank(message = "Isim bos birakilamaz.")
    @Size(min = 3, max = 50, message = "Isim 3 ile 50 karakter arasinda olmalidir.")
    private String name;
    @NotBlank(message = "Durum bos birakilamaz.")
    private String status;
    private String species;
    @NotNull(message = "Tur null olamaz.")
    private String type;

    @NotNull(message = "Cinsiyet null olamaz.")
    private String gender;

    private CharacterLocationDto location;
}
