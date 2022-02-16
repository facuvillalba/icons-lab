package com.alkemy.icons.icons.repository.specification;

import com.alkemy.icons.icons.dto.filters.CountryFiltersDTO;
import com.alkemy.icons.icons.entity.CountryEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountrySpecification {

    public Specification<CountryEntity> getByFilters(CountryFiltersDTO filtersDTO){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getNomination())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nomination")),
                                "%" + filtersDTO.getNomination().toLowerCase() + "%"
                        )
                );
            }

            if (filtersDTO.getContinentId() != null){
                predicates.add(
                        criteriaBuilder.like(
                                root.get("continentId").as(String.class),
                                "%" + filtersDTO.getContinentId() + "%"
                        )
                );
            }

            // Remove duplicates
            query.distinct(true);

            // Order resolver
            String orderByField = "nomination";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
