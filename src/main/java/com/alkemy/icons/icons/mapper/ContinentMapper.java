package com.alkemy.icons.icons.mapper;


import com.alkemy.icons.icons.dto.ContinentDTO;
import com.alkemy.icons.icons.entity.ContinentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinentMapper {

    //Convert DTO to Entity
    public ContinentEntity continentDTO2Entity(ContinentDTO continentDTO){
        ContinentEntity continentEntity = new ContinentEntity();
        continentEntity.setImage(continentDTO.getImage());
        continentEntity.setNomination(continentDTO.getNomination());
    return continentEntity;
    }

    //Convert Entity to DTO
    public ContinentDTO continentEntity2DTO(ContinentEntity continentEntity){
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setId(continentEntity.getId());
        continentDTO.setImage(continentEntity.getImage());
        continentDTO.setNomination(continentEntity.getNomination());
        return continentDTO;
    }

    //Convert Entity List to DTO List
    public List<ContinentDTO> continentEntityList2DTOList(List<ContinentEntity> entities){
        List<ContinentDTO> continentDTOS = new ArrayList<>();
        for (ContinentEntity entity : entities) {
            continentDTOS.add(this.continentEntity2DTO(entity));
        }
        return continentDTOS;
    }
}
