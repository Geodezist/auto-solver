package ua.com.bpgdev.autosolver.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchCriteria;

import java.util.LinkedList;
import java.util.List;

@Component
@Data
@Configuration
@PropertySource(value = "classpath:/car-filters.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "car-filters")
public class CarFiltersProperties {
    private List<Filter> additionalFilters;

    @Data
    public static class Filter {
        private String name;
        private String description;
        private LinkedList<SearchCriteria> searchCriteria;
    }
}
