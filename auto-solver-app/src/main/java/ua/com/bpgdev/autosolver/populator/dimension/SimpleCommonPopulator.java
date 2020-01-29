package ua.com.bpgdev.autosolver.populator.dimension;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SimpleCommonPopulator<T> {
    private RestApiUrlBuilder restApiUrlBuilder;
    private RestTemplate restTemplate;

    public SimpleCommonPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                    RestTemplate restTemplate) {
        this.restApiUrlBuilder = restApiUrlBuilder;
        this.restTemplate = restTemplate;
    }

    public List<T> getUpstreamData(Class<T[]> objectClass, String apiEntityName) {
        ArrayList<T> result = new ArrayList<>();

        ResponseEntity<T[]> responseEntity =
                restTemplate.getForEntity(
                        restApiUrlBuilder.getUrlApi(apiEntityName),
                        objectClass);
        if (responseEntity.getBody() != null) {
            result = new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        }
        return result;
    }
}
