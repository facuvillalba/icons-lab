package com.alkemy.icons.icons.dto.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryFiltersDTO {

    private String nomination;

    private Long continentId;

    private String order;

    public CountryFiltersDTO(String nomination, Long continentId, String order) {
        this.nomination = nomination;
        this.continentId = continentId;
        this.order = order;
    }

    public boolean isASC() { return this.order.compareToIgnoreCase("ASC") == 0;}
}
