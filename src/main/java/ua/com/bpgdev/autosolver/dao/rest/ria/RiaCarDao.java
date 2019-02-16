package ua.com.bpgdev.autosolver.dao.rest.ria;

import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;

import java.util.List;

public interface RiaCarDao {
    RiaCarDTO getCar(Integer carId) throws InterruptedException;
    List<RiaCarDTO> getAll(List<Integer> carIds);
}
