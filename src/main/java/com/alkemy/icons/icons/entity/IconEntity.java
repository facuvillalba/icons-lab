package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "icon")
@Getter
@Setter
@SQLDelete(sql = "UPDATE icon SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class IconEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String nomination;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    private Long height;

    private String history;

    private boolean deleted = Boolean.FALSE;


    @ManyToMany(mappedBy = "icons",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})

    private List<CountryEntity> countries = new ArrayList<>();


    public void addPais(CountryEntity country){ this.countries.add(country); }

    public void removePais(CountryEntity country){ this.countries.remove(country); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IconEntity other = (IconEntity) obj;
        return Objects.equals(id, other.id);
    }




}
