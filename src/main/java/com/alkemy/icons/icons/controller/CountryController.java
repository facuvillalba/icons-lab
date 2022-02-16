package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.dto.basic.CountryBasicDTO;
import com.alkemy.icons.icons.repository.CountryRepository;
import com.alkemy.icons.icons.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/all")
    public ResponseEntity<List<CountryBasicDTO>>getAll() {
        List<CountryBasicDTO> countries = countryService.getAllCountries();
        return ResponseEntity.ok().body(countries);
    }

    @PostMapping
    public ResponseEntity<CountryDTO> save(@Valid @RequestBody CountryDTO country) {
        CountryDTO countrySave = this.countryService.save(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(countrySave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> update(@PathVariable Long id, @Valid @RequestBody CountryDTO countryDTO){
        CountryDTO result = countryService.update(id, countryDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CountryDTO> delete(@PathVariable Long id){
        countryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getById(@PathVariable Long id){
        CountryDTO countryDTO = countryService.getById(id);
        return ResponseEntity.ok(countryDTO);
    }

    @GetMapping
    public ResponseEntity<List<CountryBasicDTO>> getByFilters(
            @RequestParam(required = false) String nomination,
            @RequestParam(required = false) Long continentId,
            @RequestParam(required = false, defaultValue = "ASC") String order){
        List<CountryBasicDTO> countryBasicDTOS = countryService.getByFilters(nomination,continentId,order);
        return ResponseEntity.ok().body(countryBasicDTOS);
    }
}

