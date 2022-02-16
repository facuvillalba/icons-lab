package com.alkemy.icons.icons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDTO {

    private Long id;
    @NotBlank
    private String image;
    @NotBlank(message = "Nomination cannot be null ")
    private String nomination;
    @NotNull(message = "Population cannot be null ")
    private Long population;
    @NotNull(message = "Area cannot be null ")
    private Long area;
    @NotNull(message = "Id continent cannot be null")
    private Long continentId;

    private List<IconDTO>iconDTOS;
}

