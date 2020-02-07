package ua.com.bpgdev.autosolver.dao.jdbc.fact;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;

import java.util.List;

public interface SourceCarDao extends PagingAndSortingRepository<SourceCar, Integer>,
        JpaSpecificationExecutor<SourceCar> {
    SourceCar findByCarId(Integer carId);

    List<SourceCar> findAllByCarIdIn(List<Integer> carIds);

    //List<SourceCar> findAllByCarIdIn(List<Integer> carIds, Specification<SourceCar> sourceCarSpecification);

    @Query(value = "SELECT car_id FROM src_car WHERE car_id IN (:car_ids)", nativeQuery = true)
    List<Integer> findAllCarIdByCarIdIn(@Param("car_ids") List<Integer> carIds);
}
