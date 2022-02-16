package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.basic.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.filters.IconFiltersDTO;
import com.alkemy.icons.icons.entity.IconEntity;
import com.alkemy.icons.icons.enume.ErrorMessage;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.IconMapper;
import com.alkemy.icons.icons.repository.IconRepository;
import com.alkemy.icons.icons.repository.specification.IconSpecification;
import com.alkemy.icons.icons.service.IconService;
import com.alkemy.icons.icons.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IconServiceImpl implements IconService {

   @Autowired
    private IconMapper iconMapper;

    @Autowired
    private IconRepository iconRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private IconSpecification iconSpecification;

    //Number 2 PDF
    public List<IconBasicDTO> getAllIcons() {
        List<IconBasicDTO> iconBasicDTOS = this.iconMapper.iconEntityList2BasicDTOList(iconRepository.findAll());
        return iconBasicDTOS;
    }

    //Number 3 PDF
    //Create, update, delete (CRUD)
    //Create icon.
    public IconDTO save(IconDTO iconDTO) {
        IconEntity iconEntity = iconMapper.iconDTO2Entity(iconDTO);
        IconEntity entitySaved = iconRepository.save(iconEntity);
        IconDTO result = iconMapper.iconEntity2DTO(entitySaved, true);
        return result;
    }

    //Update icon.
    public IconDTO update (Long id, IconDTO iconDTO){
        Optional<IconEntity> iconEntity = iconRepository.findById(id);
        if(!iconEntity.isPresent()){
            throw new ParamNotFound(ErrorMessage.ICON_NOT_UPDATED.key());
        }
        iconMapper.iconEntityRefreshValues(iconEntity.get(), iconDTO);
        IconEntity entitySave = iconRepository.save(iconEntity.get());
        IconDTO result = iconMapper.iconEntity2DTO(entitySave, true);
        return result;
    }

    //Delete icon.
    public void delete (Long id){
        Optional<IconEntity> iconEntity = iconRepository.findById(id);
        if(!iconEntity.isPresent()){
            throw new ParamNotFound(ErrorMessage.ICON_NOT_DELETED.key());
        }
        iconRepository.deleteById(id);
    }

    //Number 4 PDF
    //Detail
    public IconDTO getById(Long id){
        Optional<IconEntity> iconEntity = iconRepository.findById(id);
        if(!iconEntity.isPresent()){
            throw new ParamNotFound(ErrorMessage.ICON_NOT_FOUND.key());
        }
        IconDTO iconDTO = iconMapper.iconEntity2DTO(iconEntity.get(), true);
        return iconDTO;
    }

    //Number 5 PDF
    //Search
    public List<IconBasicDTO> getByFilters(String nomination, String creationDate, Set<Long> countries) {
        IconFiltersDTO filtersDTO = new IconFiltersDTO(nomination, creationDate, countries);
        List<IconEntity> iconEntities = iconRepository.findAll(iconSpecification.getByFilters(filtersDTO));
        List<IconBasicDTO> iconBasicDTOS = iconMapper.iconEntityList2BasicDTOList(iconEntities);
        return iconBasicDTOS;
    }

    public IconEntity getEntityById(Long id) {
        Optional<IconEntity> iconEntity = iconRepository.findById(id);
        if (!iconEntity.isPresent()){
            throw new ParamNotFound(ErrorMessage.ICON_NOT_FOUND.key());
        }
        return iconEntity.get();
    }
}
