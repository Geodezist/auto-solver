package ua.com.bpgdev.autosolver.service.ria.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaRestDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultRiaCarService implements RiaCarService {
    private final RiaRestDao riaRestDao;

    @Override
    public RiaCarDTO getCar(Integer carId) {
        JsonNode rawRiaCar = riaRestDao.getCar(carId);

        return RiaCarDTO.builder()
                .carId(carId)
                .year(rawRiaCar.at("/autoData/year").intValue())
                .mileage(rawRiaCar.at("/autoData/raceInt").intValue())
                .priceUSD(rawRiaCar.at("/USD").intValue())
                .categoryValue(rawRiaCar.at("/autoData/categoryId").intValue())
                .bodystyleValue(rawRiaCar.at("/autoData/bodyId").intValue())
                .markValue(rawRiaCar.at("/markId").intValue())
                .modelValue(rawRiaCar.at("/modelId").intValue())
                .description(rawRiaCar.at("/autoData/description").textValue())
                .fuelTypeName(rawRiaCar.at("/autoData/fuelName").textValue())
                .fuelTypeNameEng(rawRiaCar.at("/autoData/fuelNameEng").textValue())
                .gearboxName(rawRiaCar.at("/autoData/gearboxName").textValue())
                .ukraineStateName(rawRiaCar.at("/stateData/regionName").textValue())
                .cityName(rawRiaCar.at("/stateData/name").textValue())
                .carTitle(rawRiaCar.at("/title").textValue())
                .sourceMessage(rawRiaCar.toPrettyString())
                .linkToAutoRia(rawRiaCar.at("/linkToView").textValue())
                .build();
    }
}
