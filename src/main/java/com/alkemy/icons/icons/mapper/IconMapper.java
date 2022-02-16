package com.alkemy.icons.icons.mapper;


import com.alkemy.icons.icons.dto.basic.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class IconMapper {

    @Autowired
    public CountryMapper countryMapper;

    //Convert DTO to Entity
    public IconEntity iconDTO2Entity(IconDTO iconDTO){
        IconEntity iconEntity = new IconEntity();
        iconEntity.setImage(iconDTO.getImage());
        iconEntity.setNomination(iconDTO.getNomination());
        iconEntity.setCreationDate(iconDTO.getCreationDate());
        iconEntity.setHeight(iconDTO.getHeight());
        iconEntity.setHistory(iconDTO.getHistory());
        return iconEntity;
    }

    //Convert Entity to DTO
    public IconDTO iconEntity2DTO(IconEntity iconEntity, boolean loadCountries){
        IconDTO iconDTO = new IconDTO();
        iconDTO.setId(iconEntity.getId());
        iconDTO.setImage(iconEntity.getImage());
        iconDTO.setNomination(iconEntity.getNomination());
        iconDTO.setCreationDate(iconEntity.getCreationDate());
        iconDTO.setHeight(iconEntity.getHeight());
        iconDTO.setHistory(iconEntity.getHistory());
        if (loadCountries) {
            List<CountryDTO> countryDTOS = this.countryMapper.countryEntityList2DTOList(iconEntity.getCountries(), false);
            iconDTO.setCountryDTOS(countryDTOS);
        }
        return iconDTO;
    }

    //Convert string to Local Date
    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    //Convert DTO List to Entity Set
    public Set<IconEntity> iconDTOList2EntitySet(List<IconDTO> iconDTOS){
        Set<IconEntity> entities = new HashSet<>();
        for (IconDTO iconDTO : iconDTOS){
            entities.add(iconDTO2Entity(iconDTO));
        }
        return entities;
    }

    //Convert Entity Set to DTO List
    public List<IconDTO> iconEntitySet2IconDTOList(Collection<IconEntity> iconEntities, boolean loadCountries){
        List<IconDTO> iconDTOS = new ArrayList<>();
        for (IconEntity iconEntity : iconEntities){
            iconDTOS.add(iconEntity2DTO(iconEntity, loadCountries));
        }
        return iconDTOS;
    }

    //Convert Entity update DTO
    public void iconEntityRefreshValues(IconEntity iconEntity, IconDTO iconDTO){
        iconEntity.setImage(iconDTO.getImage());
        iconEntity.setNomination(iconDTO.getNomination());
        iconEntity.setCreationDate(iconDTO.getCreationDate());
        iconEntity.setHeight(iconDTO.getHeight());
    }

    public List<IconBasicDTO> iconEntityList2BasicDTOList(List<IconEntity> entities){
        List<IconBasicDTO> basicDTOS = new ArrayList<>();
        IconBasicDTO basicDTO;
        for(IconEntity entity : entities){
            basicDTO = new IconBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setNomination(entity.getNomination());
            basicDTOS.add(basicDTO);
        }
        return basicDTOS;
    }

}
