package ua.com.bpgdev.autosolver.service.fact.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.SourceCarDao;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchCriteria;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchOperation;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SourceCarSpecification;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.dto.ria.SearchRequestDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.fact.SourceCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;
import ua.com.bpgdev.autosolver.util.ProgressStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSourceCarService implements SourceCarService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SOURCE_CAR_DTO_TYPE = new TypeToken<List<SourceCar>>() {
    }.getType();

    private final SourceCarDao sourceCarDao;
    private final RiaSearchResultService riaSearchResultService;
    private final RiaCarService riaCarService;


    @Override
    public SourceCar get(Integer sourceCarId) {
        return sourceCarDao.findByCarId(sourceCarId);
    }

    @Override
    public List<Integer> findAllByCarIdIn(List<Integer> sourceCarIds) {
        List<Integer> result = new ArrayList<>();
        if (sourceCarIds.size() > 0) {
            result = sourceCarDao.findAllCarIdByCarIdIn(sourceCarIds);
        }
        return result;
    }

    @Override
    public List<SourceCar> getAllByIds(List<Integer> sourceCarIds) {
        return sourceCarDao.findAllByCarIdIn(sourceCarIds);
    }

    @Override
    public List<SourceCar> getAllByIdsAndFilter(List<Integer> sourceCarIds, Specification<SourceCar> filter) {
        SourceCarSpecification sourceCarIdsFilter = new SourceCarSpecification();
        sourceCarIdsFilter.addSearchCriteria(new SearchCriteria("carId", sourceCarIds, SearchOperation.IN));

        return sourceCarDao.findAll(
                Specification.where(sourceCarIdsFilter).and(filter)
        );
    }

    @Override
    public List<SourceCar> getAll() {
        List<SourceCar> sourceCars = new ArrayList<>();
        sourceCarDao.findAll().forEach(sourceCars::add);
        return sourceCars;
    }

    @Override
    public SourceCar save(SourceCar sourceCar) {
        return sourceCarDao.save(sourceCar);
    }

    public int saveAllDTO(List<RiaCarDTO> riaCarDTOs) {
        List<SourceCar> sourceCars = MODEL_MAPPER.map(riaCarDTOs, SOURCE_CAR_DTO_TYPE);
        return saveAll(sourceCars);
    }

    @Override
    public int saveAll(List<SourceCar> sourceCars) {
        sourceCarDao.saveAll(sourceCars);
        return sourceCars.size();
    }

    @Override
    @SneakyThrows
    public int saveAllFromRia(SearchRequestDTO searchRequest, ProgressStatus progressStatus) {
        List<Integer> carIds = new ArrayList<>(riaSearchResultService.getSearchResult(searchRequest));
        if (searchRequest.isSaveCars()) {
            List<Integer> existingCarIds = findAllByCarIdIn(carIds);
            List<Integer> absentCarIds = new ArrayList<>(carIds);
            absentCarIds.removeAll(existingCarIds);
            progressStatus.setTotal(absentCarIds.size());
            AtomicInteger counter = new AtomicInteger();

            ExecutorService riaCarExecutorService = Executors.newFixedThreadPool(3);

            List<Callable<RiaCarDTO>> carDTOCallables = new ArrayList<>();
            for (Integer c : absentCarIds) {
                Callable<RiaCarDTO> carDTOCallable = () -> {
                    progressStatus.setCurrent(counter.incrementAndGet());
                    return riaCarService.getCar(c);
                };
                carDTOCallables.add(carDTOCallable);
            }

            List<Future<RiaCarDTO>> carDTOFutures = riaCarExecutorService.invokeAll(carDTOCallables);
            List<RiaCarDTO> riaCarDTOs = new ArrayList<>();

            for (Future<RiaCarDTO> carDTOFuture : carDTOFutures) {
                riaCarDTOs.add(carDTOFuture.get());
            }
            riaCarExecutorService.shutdown();
            return saveAllDTO(riaCarDTOs);
        }
        return 0;
    }
}
