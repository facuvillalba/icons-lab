package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.basic.CountryBasicDTO;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CountryMapper {

    @Autowired
    private IconMapper iconMapper;

    @Autowired
    private ContinentMapper continentMapper;

    //Convert DTO to Entity
    public CountryEntity countryDTO2Entity(CountryDTO countryDTO){
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setImage(countryDTO.getImage());
        countryEntity.setNomination(countryDTO.getNomination());
        countryEntity.setPopulation(countryDTO.getPopulation());
        countryEntity.setArea(countryDTO.getArea());
        countryEntity.setContinentId(countryDTO.getContinentId());
        Set<IconEntity> iconEntities = iconMapper.iconDTOList2EntitySet(countryDTO.getIconDTOS());
        countryEntity.setIcons(iconEntities);
    return countryEntity;
    }

    //Convert Entity to DTO
    public CountryDTO countryEntity2DTO(CountryEntity countryEntity, boolean loadIcons) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(countryEntity.getId());
        countryDTO.setImage(countryEntity.getImage());
        countryDTO.setNomination(countryEntity.getNomination());
        countryDTO.setPopulation(countryEntity.getPopulation());
        countryDTO.setArea(countryEntity.getArea());
        if(loadIcons){
            List<IconDTO> iconDTOS = iconMapper.iconEntitySet2IconDTOList(countryEntity.getIcons(), false );
            countryDTO.setIconDTOS(iconDTOS);
        }
        return countryDTO;
    }

    //Convert Entity List to DTO List
    public List<CountryDTO> countryEntityList2DTOList(List<CountryEntity> entities, boolean loadIcons){
        List<CountryDTO> dtos = new ArrayList<>();
        for (CountryEntity entity : entities) {
            dtos.add(this.countryEntity2DTO(entity, loadIcons));
        }
        return dtos;
    }

    //Convert DTO List to Entity List
    public List<CountryEntity> countryDTOListEntityList(List<CountryDTO> countryDTOS){
        List<CountryEntity> entities = new ArrayList<>();
        for (CountryDTO countryDTO : countryDTOS) {
            entities.add(this.countryDTO2Entity(countryDTO));
        }
        return entities;
    }

    //Convert Entity update DTO
    public void countryEntityRefreshValues(CountryEntity countryEntity, CountryDTO countryDTO){
        countryEntity.setNomination(countryDTO.getNomination());
        countryEntity.setImage(countryDTO.getImage());
        countryEntity.setPopulation(countryDTO.getPopulation());
        countryEntity.setArea(countryDTO.getArea());
        countryEntity.setContinentId(countryDTO.getContinentId());
        Set<IconEntity> iconEntities = iconMapper.iconDTOList2EntitySet(countryDTO.getIconDTOS());
        for (IconEntity iconEntity : iconEntities){
            countryEntity.getIcons().add(iconEntity);
        }
    }

    public List<CountryBasicDTO> countryEntityList2BasicDTOList(List<CountryEntity> entities){
        List<CountryBasicDTO> basicDTOS = new ArrayList<>();
        CountryBasicDTO basicDTO;
        for(CountryEntity entity : entities){
            basicDTO = new CountryBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setNomination(entity.getNomination());
            basicDTO.setImage(entity.getImage());
            basicDTO.setPopulation(entity.getPopulation());
            basicDTOS.add(basicDTO);
        }
        return basicDTOS;
    }
}
