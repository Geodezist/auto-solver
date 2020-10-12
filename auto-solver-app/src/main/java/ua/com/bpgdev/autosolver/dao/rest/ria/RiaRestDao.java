package ua.com.bpgdev.autosolver.dao.rest.ria;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.bpgdev.autosolver.config.FeignClientConfig;
import ua.com.bpgdev.autosolver.dto.ria.SearchRequestDTO;

@FeignClient(name = "ria-rest-api", url = "${autosolver.external-api.base-api-url}", configuration = FeignClientConfig.class)
public interface RiaRestDao {

    @GetMapping(value = "/search?api_key=${autosolver.external-api.api-key}")
    JsonNode getSearchResult(@SpringQueryMap SearchRequestDTO queryString);

    @GetMapping(value = "/info?api_key=${autosolver.external-api.api-key}&auto_id={carId}")
    JsonNode getCar(@PathVariable Integer carId);
}
