package ua.com.bpgdev.autosolver.util;

import org.junit.Test;
import ua.com.bpgdev.autosolver.exception.AutosolverMalformedURLException;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class RestApiUrlBuilderTest {
    private static final String BASE_API_URL = "http://base.api.url/";
    private static final String API_KEY = "TEST_API_KEY";

    private static final RestApiUrlBuilder restApiUrlBuilder = new RestApiUrlBuilder(BASE_API_URL, API_KEY);

    @Test
    public void getUrlApi() {
        String apiEntity = "countries";
        String expected = BASE_API_URL + apiEntity + "?api_key=" + API_KEY;
        String actual = restApiUrlBuilder.getUrlApi(apiEntity);
        assertEquals(expected, actual);
    }

    @Test
    public void getUrlApiSearch() throws MalformedURLException {
        String queryString = "testQueryString";
        URL expextedURL = new URL(BASE_API_URL + "search?api_key=" + API_KEY + queryString);
        URL actualURL = restApiUrlBuilder.getUrlApiSearch(queryString);
        assertEquals(expextedURL, actualURL);
    }

    @Test
    public void getUrlApiCar() throws MalformedURLException {
        int carId = 1;
        URL expextedURL = new URL(BASE_API_URL + "info?api_key=" + API_KEY + "&auto_id=" + carId);
        URL actualURL = restApiUrlBuilder.getUrlApiCar(carId);
        assertEquals(expextedURL, actualURL);
    }

    @Test(expected = AutosolverMalformedURLException.class)
    public void getUrlApiSearchAutosolverMalformedURLException() {
        RestApiUrlBuilder restApiUrlBuilder = new RestApiUrlBuilder("Exception Test", "Exception Test");
        URL actualURL = restApiUrlBuilder.getUrlApiSearch("");
    }

    @Test(expected = AutosolverMalformedURLException.class)
    public void getUrlApiCarAutosolverMalformedURLException() {
        RestApiUrlBuilder restApiUrlBuilder = new RestApiUrlBuilder("Exception Test", "Exception Test");
        URL actualURL = restApiUrlBuilder.getUrlApiCar(1);
    }
}