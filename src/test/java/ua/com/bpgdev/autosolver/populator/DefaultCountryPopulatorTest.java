package ua.com.bpgdev.autosolver.populator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.bpgdev.autosolver.populator.dimension.simple.impl.DefaultCountryPopulator;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DefaultCountryPopulatorTest {

    @Autowired
    DefaultCountryPopulator defaultCountryPopulator;

    @Test
    public void getUpstreamData() {
    }
}