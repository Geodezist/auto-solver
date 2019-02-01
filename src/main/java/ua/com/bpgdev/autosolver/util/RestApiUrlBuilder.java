package ua.com.bpgdev.autosolver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestApiUrlBuilder {
    private final String baseApiUrl;
    private final String apiKey;

    @Autowired
    public RestApiUrlBuilder(@Value("${autosolver.external-api.base-api-url}") String baseApiUrl,
                             @Value("${autosolver.external-api.api-key}") String apiKey){
        this.baseApiUrl = baseApiUrl;
        this.apiKey = apiKey;
    }

    public String getUrlApi(String apiEntity) {
        return baseApiUrl + apiEntity + "?api_key=" + apiKey;
    }
}
