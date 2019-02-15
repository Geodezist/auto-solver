package ua.com.bpgdev.autosolver.dao.rest.ria.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaCarDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultRiaCarDao implements RiaCarDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RestApiUrlBuilder restApiUrlBuilder;
    private ObjectMapper objectMapper;

    @Autowired
    public DefaultRiaCarDao(RestApiUrlBuilder restApiUrlBuilder,
                            ObjectMapper objectMapper) {
        this.restApiUrlBuilder = restApiUrlBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public RiaCarDTO getCar(int carId) throws IOException {
        RiaCarDTO result = new RiaCarDTO();
        URL urlApiCar = restApiUrlBuilder.getUrlApiCar(carId);

        logger.debug("Getting car from external REST resourse - " + urlApiCar.toString());

        JsonNode jsonResponseNode = objectMapper.readTree(urlApiCar);
        result.setCarId(carId);
        result.setYear(jsonResponseNode.at("/autoData/year").intValue());
        result.setMileage(jsonResponseNode.at("/autoData/raceInt").intValue());
        result.setPriceUSD(jsonResponseNode.at("/USD").intValue());
        result.setCategoryValue(jsonResponseNode.at("/autoData/categoryId").intValue());
        result.setBodystyleValue(jsonResponseNode.at("/autoData/bodyId").intValue());
        result.setMarkValue(jsonResponseNode.at("/markId").intValue());
        result.setModelValue(jsonResponseNode.at("/modelId").intValue());
        result.setDescription(jsonResponseNode.at("/autoData/description").textValue());
        result.setFuelTyeName(jsonResponseNode.at("/autoData/fuelName").textValue());
        result.setFuelTypeNameEng(jsonResponseNode.at("/autoData/fuelNameEng").textValue());
        result.setGearboxName(jsonResponseNode.at("/autoData/gearboxName").textValue());
        result.setUkraineStateName(jsonResponseNode.at("/stateData/regionName").textValue());
        result.setCityName(jsonResponseNode.at("/stateData/name").textValue());

        return result;
    }

    @Override
    public List<RiaCarDTO> getAll(List<Integer> carIds) {
        List<RiaCarDTO> result = new ArrayList<>();
        logger.debug("Getting all cars from external REST resourse. Count of cars - {}", carIds.size());
        try {
            for (Integer carId : carIds) {
                int tryCount = 0;
                try {
                    RiaCarDTO car = getCar(carId);
                    result.add(car);
                } catch (IOException e) {
                    tryCount++;
                    if (tryCount > 3) {
                        throw new RuntimeException(e);
                    }
                    logger.debug("External resourse is busy. Sleeping for {} second(s)", tryCount);
                    Thread.sleep(tryCount * 1000);
                    logger.debug("Trying get data from external resourse one more time...");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
