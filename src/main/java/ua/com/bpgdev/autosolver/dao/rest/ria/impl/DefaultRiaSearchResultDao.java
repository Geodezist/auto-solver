package ua.com.bpgdev.autosolver.dao.rest.ria.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaSearchResultDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.io.IOException;
import java.util.List;

@Component
public class DefaultRiaSearchResultDao implements RiaSearchResultDao {
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
        try {
            JsonNode jsonNode = objectMapper.readTree(restApiUrlBuilder.getUrlApiSearch(queryString));
            int page = jsonNode
                    .get("additional_params")
                    .get("page")
                    .intValue();
            List<Integer> carIds = objectMapper.readValue(jsonNode
                    .get("result")
                    .get("search_result")
                    .get("ids")
                    .traverse(), new TypeReference<List<Integer>>() {
            });
            int totalCount = jsonNode
                    .get("result")
                    .get("search_result")
                    .get("count")
                    .intValue();
            riaSearchResultDTO.setQueryString(queryString);
            riaSearchResultDTO.setPage(page);
            riaSearchResultDTO.setCarIds(carIds);
            riaSearchResultDTO.setTotalCount(totalCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return riaSearchResultDTO;
    }
}
