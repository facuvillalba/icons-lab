package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.ContinentDTO;
import com.alkemy.icons.icons.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping
    public ResponseEntity<List<ContinentDTO>> getALL() {
        List<ContinentDTO> continents = continentService.getAllContinents();
        return ResponseEntity.ok().body(continents);
    }

    @PostMapping
    public ResponseEntity<ContinentDTO> save(@Valid @RequestBody ContinentDTO continent){
        ContinentDTO continentSave = continentService.save(continent);
        return ResponseEntity.status(HttpStatus.CREATED).body(continentSave);
    }
}
