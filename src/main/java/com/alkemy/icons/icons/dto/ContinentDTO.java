package com.alkemy.icons.icons.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ContinentDTO {
    private Long id;
    private String image;
    @NotEmpty(message = "Nomination cannot be empty")
    private String nomination;
}
