package ua.com.bpgdev.autosolver.service.filter.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchCriteria;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchOperation;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SourceCarSpecification;
import ua.com.bpgdev.autosolver.dto.AdditionalFilterDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.filter.AdditionalFilterService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultAdditionalFilterService implements AdditionalFilterService {

    private static final String DESCRIPTION_FIELD_NAME = "description";
    private static final String ONLY_OFFICIAL = "onlyOfficial";
    private static final String NOT_USA = "notUSA";

    private Map<String, Specification<SourceCar>> additionalFilters = new HashMap<>();

    public DefaultAdditionalFilterService() {
        additionalFilters.put(ONLY_OFFICIAL, Specification
                .where(createSpecification("фиц", SearchOperation.MATCH))
                .or(createSpecification("offi", SearchOperation.MATCH))
                .or(createSpecification("ofi", SearchOperation.MATCH)));

        additionalFilters.put(NOT_USA, Specification
                .where(createSpecification("сша", SearchOperation.NOT_MATCH))
                .and(createSpecification("usa", SearchOperation.NOT_MATCH))
                .and(createSpecification("амер", SearchOperation.NOT_MATCH)));
    }

    private Specification<SourceCar> createSpecification(Object criteria, SearchOperation searchOperation) {
        return new SourceCarSpecification(new SearchCriteria(DESCRIPTION_FIELD_NAME, criteria, searchOperation));
    }

    @Override
    public List<AdditionalFilterDTO> getAllAdditionalFilterDefinitions() {
        return Arrays.asList(
                new AdditionalFilterDTO(ONLY_OFFICIAL, "Содержит в описании \"официальный\" и подобные слова"),
                new AdditionalFilterDTO(NOT_USA, "Не содержит в описании США и подобные слова")
        );
    }

    @Override
    public Specification<SourceCar> getAdditionalFilter(String additionalFilterName) {
        return additionalFilters.getOrDefault(additionalFilterName, new SourceCarSpecification());
    }
}
