package ua.com.bpgdev.autosolver.controller.ria;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.fact.SourceCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/ria", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@ResponseBody
@RequiredArgsConstructor
public class RiaController {
    private final RiaSearchResultService riaSearchResultService;
    private final RiaCarService riaCarService;
    private final SourceCarService sourceCarService;

    @GetMapping(path = "/car/{carId}")
    public RiaCarDTO getCar(@PathVariable Integer carId) {
        return riaCarService.getCar(carId);
    }

    @GetMapping(path = "/search/{queryString}")
    public Set<Integer> getAllCarIds(@PathVariable String queryString) {
        return riaSearchResultService.getSearchResult(queryString);
    }

    @GetMapping(path = "/search/{queryString}/getcars")
    public List<SourceCar> getAllCars(@PathVariable String queryString) {
        List<Integer> carIds = new ArrayList<>(riaSearchResultService.getSearchResult(queryString));
        return sourceCarService.getAllByIds(carIds);
    }
}
