package ua.com.bpgdev.autosolver.service.ria;

import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;

import java.util.List;

public interface RiaCarService {
    RiaCarDTO getCar(Integer carId);

    List<RiaCarDTO> getAll(List<Integer> carIds);
}
