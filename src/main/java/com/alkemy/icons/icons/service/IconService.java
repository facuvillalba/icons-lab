package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.basic.IconBasicDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IconService {

    //Number 2 PDF
    List<IconBasicDTO> getAllIcons();
    //Number 3 PDF
    //Create, update, delete (CRUD)
    //Create icon.
    IconDTO save(IconDTO dto);
    //Update icon.
    IconDTO update (Long id, IconDTO iconDTO);
    //Delete icon.
    void delete (Long id);
    //Number 4 PDF
    //Detail
    IconDTO getById(Long id);
    //Number 5 PDF
    //Search witch filters
    List<IconBasicDTO> getByFilters(String nomination, String creationDate, Set<Long> countries);

}
