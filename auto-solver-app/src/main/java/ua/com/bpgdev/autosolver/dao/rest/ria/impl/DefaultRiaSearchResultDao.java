package ua.com.bpgdev.autosolver.dao.rest.ria.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaSearchResultDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class DefaultRiaSearchResultDao implements RiaSearchResultDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RestApiUrlBuilder restApiUrlBuilder;
    private ObjectMapper objectMapper;

    @Autowired
    public DefaultRiaSearchResultDao(RestApiUrlBuilder restApiUrlBuilder,
                                     ObjectMapper objectMapper) {
        this.restApiUrlBuilder = restApiUrlBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public RiaSearchResultDTO getSearchResult(String queryString) {
        RiaSearchResultDTO riaSearchResultDTO = new RiaSearchResultDTO();
        URL url = restApiUrlBuilder.getUrlApiSearch(queryString);
        try {
            JsonNode jsonNode = objectMapper.readTree(url);
            int page = jsonNode
                    .get("additional_params")
                    .get("page")
                    .intValue();
            List<Integer> carIds = objectMapper.readValue(jsonNode
                    .get("result")
                    .get("search_result")
                    .get("ids")
                    .traverse(), new TypeReference<>() {
            });
            int totalCount = jsonNode
                    .get("result")
                    .get("search_result")
                    .get("count")
                    .intValue();
            int pageSize = jsonNode
                    .get("result")
                    .get("additional")
                    .get("search_params")
                    .get("all")
                    .get("countpage")
                    .intValue();
            riaSearchResultDTO.setQueryString(queryString);
            riaSearchResultDTO.setPage(page);
            riaSearchResultDTO.setCarIds(carIds);
            riaSearchResultDTO.setTotalCount(totalCount);
            riaSearchResultDTO.setPageSize(pageSize);
        } catch (IOException e) {
            logger.error("Error was raised when reading {}", url);
            throw new RuntimeException(e);
        }
        return riaSearchResultDTO;
    }
}
