package ua.com.bpgdev.autosolver.dao.rest.ria;

import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface RiaCarDao {
    RiaCarDTO getCar(int carId) throws IOException;
    List<RiaCarDTO> getAll(List<Integer> carIds);
}
