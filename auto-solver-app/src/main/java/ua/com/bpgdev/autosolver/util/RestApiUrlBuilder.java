package ua.com.bpgdev.autosolver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.exception.AutosolverMalformedURLException;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class RestApiUrlBuilder {
    private final String baseApiUrl;
    private final String apiKey;

    @Autowired
    public RestApiUrlBuilder(@Value("${autosolver.external-api.base-api-url}") String baseApiUrl,
                             @Value("${autosolver.external-api.api-key}") String apiKey) {
        this.baseApiUrl = baseApiUrl;
        this.apiKey = apiKey;
    }

    public String getUrlApi(String apiEntity) {
        return baseApiUrl + apiEntity + "?api_key=" + apiKey;
    }

    public URL getUrlApiSearch(String queryString) {
        try {
            return new URL(baseApiUrl + "search?api_key=" + apiKey + queryString);
        } catch (MalformedURLException e) {
            throw new AutosolverMalformedURLException("URL is not valid!", e);
        }
    }

    public URL getUrlApiCar(Integer carId) {
        try {
            return new URL(baseApiUrl + "info?api_key=" + apiKey + "&auto_id=" + carId);
        } catch (MalformedURLException e) {
            throw new AutosolverMalformedURLException("URL is not valid!", e);
        }
    }
}
