package com.alkemy.icons.icons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
@Getter
@Setter
@SQLDelete(sql = "UPDATE country SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@RequiredArgsConstructor
@AllArgsConstructor
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String nomination;

    @Column(name = "population")
    private Long population;

    private Long area;

    private boolean deleted = Boolean.FALSE;//

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})

    @JoinColumn(name = "continent_id", insertable = false, updatable = false)
    private ContinentEntity continent;

    @Column(name = "continent_id", nullable = false)
    private Long continentId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "icon_country",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))


    private Set<IconEntity> icons = new HashSet<>();

    @Override
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CountryEntity other = (CountryEntity) obj;
        return other.id == this.id;
    }

}