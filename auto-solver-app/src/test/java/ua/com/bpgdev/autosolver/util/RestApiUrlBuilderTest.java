package ua.com.bpgdev.autosolver.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}