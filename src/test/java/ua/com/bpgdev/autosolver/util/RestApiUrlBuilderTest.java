package ua.com.bpgdev.autosolver.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RestApiUrlBuilderTest {

    @Autowired
    RestApiUrlBuilder restApiUrlBuilder;

    @Test
    public void getUrlApi() {
        System.out.println(restApiUrlBuilder.getUrlApi("countries"));
    }

}