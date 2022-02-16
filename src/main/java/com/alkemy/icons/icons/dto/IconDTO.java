package com.alkemy.icons.icons.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class IconDTO {
    private Long id;
    @NotBlank
    private String image;
    @NotBlank(message = "Nomination cannot be empty")
    private String nomination;
    @NotNull(message = "Date cannot be null ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    @NotNull(message = "Height cannot be null ")
    private Long height;
    @Size(min=1, max = 255, message = "History text min 1, max 255 characters")
    private String history;
    @NotNull(message = "Id country cannot be null")
    private Long countryId;

    private List<CountryDTO> countryDTOS;


}
