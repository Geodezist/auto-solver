package ua.com.bpgdev.autosolver.service.filter;

import org.springframework.data.jpa.domain.Specification;
import ua.com.bpgdev.autosolver.dto.AdditionalFilterDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;

import java.util.List;

public interface AdditionalFilterService {
    List<AdditionalFilterDTO> getAllAdditionalFilterDefinitions();

    Specification<SourceCar> getAdditionalFilter(String additionalFilterName);
}
