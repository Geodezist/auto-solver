package ua.com.bpgdev.autosolver.service.ria;

import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.util.ProgressStatus;

import java.util.List;

public interface RiaCarService {
    RiaCarDTO getCar(Integer carId);

    List<RiaCarDTO> getAll(List<Integer> carIds, ProgressStatus progressStatus);
}
