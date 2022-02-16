package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.basic.IconBasicDTO;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("icons")
public class IconController {

    @Autowired
    private IconService iconService;

    @GetMapping("/all")
    public ResponseEntity<List<IconBasicDTO>> getAll() {
        List<IconBasicDTO> icons = iconService.getAllIcons();
        return ResponseEntity.ok().body(icons);
    }

    @PostMapping
    public ResponseEntity<IconDTO> save(@Valid @RequestBody IconDTO icon) {
        IconDTO iconSave = iconService.save(icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(iconSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IconDTO> update(@PathVariable Long id, @Valid @RequestBody IconDTO icon){
        IconDTO result = iconService.update(id, icon);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.iconService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IconDTO> getById(@PathVariable Long id){
        IconDTO icon = iconService.getById(id);
        return ResponseEntity.ok(icon);
    }

    @GetMapping
    public ResponseEntity<List<IconBasicDTO>> getByFilters(
            @RequestParam(required = false) String nomination,
            @RequestParam(required = false) String creationDate,
            @RequestParam(required = false) Set<Long> countries
    ) {
        List<IconBasicDTO> icons = iconService.getByFilters(nomination, creationDate, countries);
        return ResponseEntity.ok(icons);
    }



}
