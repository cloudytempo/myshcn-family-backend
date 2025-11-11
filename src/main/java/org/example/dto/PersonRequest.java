package org.example.dto;

import lombok.*;
import org.example.entity.Person;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private Person.Gender gender = Person.Gender.UNKNOWN;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String notes;
    private MultipartFile photo;
}

