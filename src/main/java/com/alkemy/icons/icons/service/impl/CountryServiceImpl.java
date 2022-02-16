package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.dto.basic.CountryBasicDTO;
import com.alkemy.icons.icons.dto.filters.CountryFiltersDTO;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.enume.ErrorMessage;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.CountryMapper;
import com.alkemy.icons.icons.repository.CountryRepository;
import com.alkemy.icons.icons.repository.specification.CountrySpecification;
import com.alkemy.icons.icons.service.CountryService;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private IconService iconService;

    @Autowired
    private CountrySpecification countrySpecification;

    //Number 7 PDF
    //All countries
    public List<CountryBasicDTO> getAllCountries() {
        List<CountryBasicDTO> countryBasicDTOS = countryMapper.countryEntityList2BasicDTOList(countryRepository.findAll());
        return countryBasicDTOS;
    }

    //Number 8 PDF
    //Create, update, delete (CRUD)
    //Create country.
    public CountryDTO save(CountryDTO countryDTO) {
        CountryEntity entity = countryMapper.countryDTO2Entity(countryDTO);
        CountryEntity entitySaved = countryRepository.save(entity);
        CountryDTO result = countryMapper.countryEntity2DTO(entitySaved, true);
        return result;
    }

    //Update country.
    public CountryDTO update (Long id, CountryDTO countryDTO){
        Optional<CountryEntity> countryEntity = countryRepository.findById(id);
        if(!countryEntity.isPresent()){
            throw new ParamNotFound(ErrorMessage.COUNTRY_NOT_UPDATED.key());
        }
        countryMapper.countryEntityRefreshValues(countryEntity.get(), countryDTO);
        CountryEntity entitySave = countryRepository.save(countryEntity.get());
        CountryDTO result = countryMapper.countryEntity2DTO(entitySave, true);
        return result;
    }

    //Delete country.
    public void delete (Long id){
        Optional<CountryEntity> countryEntity = countryRepository.findById(id);
        if(!countryEntity.isPresent()){
            throw new ParamNotFound(ErrorMessage.COUNTRY_NOT_DELETED.key());
        }
        countryRepository.delete(countryEntity.get());
    }
    //Number 9 PDF
    //Search country witch filters
    public List<CountryBasicDTO> getByFilters(String nomination, Long continentId,  String order) {
        CountryFiltersDTO filtersDTO = new CountryFiltersDTO(nomination, continentId, order);
        List<CountryEntity> countryEntities = countryRepository.findAll(countrySpecification.getByFilters(filtersDTO));
        List<CountryBasicDTO> countryBasicDTOS = countryMapper.countryEntityList2BasicDTOList(countryEntities);
        return countryBasicDTOS;
    }

    public CountryDTO getById(Long id){
        Optional<CountryEntity> countryEntity = countryRepository.findById(id);
        if(!countryEntity.isPresent()){
            throw new ParamNotFound(ErrorMessage.COUNTRY_NOT_FOUND.key());
        }
        CountryDTO countryDTO = countryMapper.countryEntity2DTO(countryEntity.get(), true);
        return countryDTO;
    }
}
