package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.dto.basic.CountryBasicDTO;
import com.alkemy.icons.icons.entity.CountryEntity;

import java.util.List;

public interface CountryService {

    CountryDTO save(CountryDTO dto);

    List<CountryBasicDTO> getAllCountries();

    CountryDTO update (Long id, CountryDTO countryDTO);

    void delete (Long id);

    List<CountryBasicDTO> getByFilters(String nomination, Long continentId,  String order);

    CountryDTO getById(Long id);


}
