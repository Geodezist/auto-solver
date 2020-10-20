package ua.com.bpgdev.autosolver.service.filter.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.config.property.CarFiltersProperties;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchCriteria;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SourceCarSpecification;
import ua.com.bpgdev.autosolver.dto.AdditionalFilterDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.filter.AdditionalFilterService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultAdditionalFilterService implements AdditionalFilterService {

    private final Map<String, Specification<SourceCar>> additionalFilters;
    private final Map<String, String> additionalFilterDefinitions;

    public DefaultAdditionalFilterService(CarFiltersProperties carFiltersProperties) {
        additionalFilters = buildAdditionalFilters(carFiltersProperties);
        additionalFilterDefinitions = buildAdditionalFilterDefinitions(carFiltersProperties);
    }

    private Map<String, String> buildAdditionalFilterDefinitions(CarFiltersProperties carFiltersProperties) {
        return carFiltersProperties.getAdditionalFilters().stream()
                .collect(Collectors.toMap(
                        CarFiltersProperties.Filter::getName,
                        CarFiltersProperties.Filter::getDescription,
                        (a, b) -> b));
    }

    @Override
    public List<AdditionalFilterDTO> getAllAdditionalFilterDefinitions() {
        return additionalFilterDefinitions.entrySet().stream()
                .map(e -> new AdditionalFilterDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public Specification<SourceCar> getAdditionalFilter(String additionalFilterName) {
        return additionalFilters.getOrDefault(additionalFilterName, new SourceCarSpecification());
    }

    private Map<String, Specification<SourceCar>> buildAdditionalFilters(CarFiltersProperties carFiltersProperties) {
        return carFiltersProperties.getAdditionalFilters().stream()
                .collect(Collectors.toMap(
                        CarFiltersProperties.Filter::getName,
                        filter -> buildSpecification(filter.getSearchCriteria()),
                        (a, b) -> b));
    }

    private Specification<SourceCar> buildSpecification(LinkedList<SearchCriteria> searchCriteria) {
        SearchCriteria first = searchCriteria.getFirst();
        Specification<SourceCar> sourceCarSpecification = Specification.where(new SourceCarSpecification(first));
        for (int i = 1; i < searchCriteria.size(); i++) {
            SearchCriteria previousCriteria = searchCriteria.get(i - 1);
            SearchCriteria currentCriteria = searchCriteria.get(i);
            if ("OR".equalsIgnoreCase(previousCriteria.getAndThen())) {
                sourceCarSpecification = sourceCarSpecification.or(new SourceCarSpecification(currentCriteria));
            } else if ("AND".equalsIgnoreCase(previousCriteria.getAndThen())) {
                sourceCarSpecification = sourceCarSpecification.and(new SourceCarSpecification(currentCriteria));
            } else if (previousCriteria.getAndThen() == null) {
                return sourceCarSpecification;
            } else {
                throw new RuntimeException("Type currentCriteria.getAndThen() is not supported!");
            }
        }
        return sourceCarSpecification;
    }
}
