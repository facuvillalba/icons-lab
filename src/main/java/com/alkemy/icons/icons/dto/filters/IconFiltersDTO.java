package com.alkemy.icons.icons.dto.filters;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class IconFiltersDTO {

   private String nomination;
    private String creationDate;
    private Set<Long> countries;

    public IconFiltersDTO(String nomination, String creationDate, Set<Long> countries) {
        this.nomination = nomination;
        this.creationDate = creationDate;
        this.countries = countries;
    }

}

