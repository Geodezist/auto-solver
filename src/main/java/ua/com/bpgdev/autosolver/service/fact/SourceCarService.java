package ua.com.bpgdev.autosolver.service.fact;

import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;

import java.util.List;

public interface SourceCarService {
    SourceCar get(Integer sourceCarId);

    List<SourceCar> getAllByIds(List<Integer> sourceCarIds);

    List<Integer> findAllByCarIdIn(List<Integer> sourceCarIds);

    List<SourceCar> getAll();

    SourceCar save(SourceCar sourceCar);

    int saveAllDTO(List<RiaCarDTO> riaCarDTOs);

    int saveAll(List<SourceCar> sourceCars);
}
