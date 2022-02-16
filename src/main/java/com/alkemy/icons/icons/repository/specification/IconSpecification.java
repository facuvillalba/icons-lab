package com.alkemy.icons.icons.repository.specification;

import com.alkemy.icons.icons.dto.filters.IconFiltersDTO;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class IconSpecification {

    public Specification<IconEntity> getByFilters(IconFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            //String
            if (StringUtils.hasLength(filtersDTO.getNomination())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nomination")),
                                "%" + filtersDTO.getNomination().toLowerCase() + "%"
                        )
                );
            }
            //String
            if (StringUtils.hasLength(filtersDTO.getCreationDate())) {
                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getCreationDate(), formatters);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("creationDate"), date)
                );
            }
            //List
            if (!CollectionUtils.isEmpty(filtersDTO.getCountries())) {
                Join<CountryEntity, IconEntity> join = root.join("countries", JoinType.INNER);
                Expression<String> countriesId = join.get("id");
                predicates.add(countriesId.in(filtersDTO.getCountries()));
            }

            //Remove duplicates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
