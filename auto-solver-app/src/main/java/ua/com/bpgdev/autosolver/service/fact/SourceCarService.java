package ua.com.bpgdev.autosolver.service.fact;

import org.springframework.data.jpa.domain.Specification;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SourceCarSpecification;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.dto.ria.SearchRequestDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.util.ProgressStatus;

import java.util.List;

public interface SourceCarService {
    SourceCar get(Integer sourceCarId);

    List<SourceCar> getAllByIds(List<Integer> sourceCarIds);

    List<SourceCar> getAllByIdsAndFilter(List<Integer> sourceCarIds, Specification<SourceCar> filter);

    List<Integer> findAllByCarIdIn(List<Integer> sourceCarIds);

    List<SourceCar> getAll();

    SourceCar save(SourceCar sourceCar);

    int saveAllDTO(List<RiaCarDTO> riaCarDTOs);

    int saveAll(List<SourceCar> sourceCars);

    int saveAllFromRia(SearchRequestDTO searchRequest, ProgressStatus progressStatus);
}
