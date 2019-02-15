package ua.com.bpgdev.autosolver.controller.ria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/ria", produces = {MediaType.APPLICATION_JSON_VALUE})
@ResponseBody
public class RiaContoller {
    private RiaSearchResultService riaSearchResultService;
    private RiaCarService riaCarService;

    @Autowired
    public RiaContoller(RiaSearchResultService riaSearchResultService,
                        RiaCarService riaCarService) {
        this.riaSearchResultService = riaSearchResultService;
        this.riaCarService = riaCarService;
    }

    @GetMapping(path = "/search/{queryString}")
    public Set<Integer> getAllCars(@PathVariable String queryString) {
        return riaSearchResultService.getSearchResult(queryString);
    }

    @GetMapping(path = "/car/{carId}")
    public RiaCarDTO getCar(@PathVariable int carId) {
        return riaCarService.getCar(carId);
    }

    @GetMapping(path = "/search/{queryString}/cars")
    public List<RiaCarDTO> getAllCar(@PathVariable String queryString) {
        List<Integer> carIds = new ArrayList<>(riaSearchResultService.getSearchResult(queryString));
        return riaCarService.getAll(carIds);
    }

}
