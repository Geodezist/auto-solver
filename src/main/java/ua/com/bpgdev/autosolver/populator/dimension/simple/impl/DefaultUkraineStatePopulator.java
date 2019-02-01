package ua.com.bpgdev.autosolver.populator.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;
import ua.com.bpgdev.autosolver.populator.dimension.SimpleCommonPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.UkraineStatePopulator;
import ua.com.bpgdev.autosolver.service.dimension.simple.UkraineStateService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

@Component
public class DefaultUkraineStatePopulator extends SimpleCommonPopulator<UkraineState> implements UkraineStatePopulator {
    private static final String UKRAINE_STATE_API_ENTITY = "states";
    private UkraineStateService ukraineStateService;

    @Autowired
    public DefaultUkraineStatePopulator(RestApiUrlBuilder restApiUrlBuilder,
                                        UkraineStateService ukraineStateService) {
        super(restApiUrlBuilder);
        this.ukraineStateService = ukraineStateService;
    }

    @Override
    public int populateAll() {
        return ukraineStateService.saveAll(getUpstreamData(UkraineState[].class, UKRAINE_STATE_API_ENTITY));
    }

    @Override
    public boolean populateUkraineState(UkraineState ukraineState) {
        return ukraineStateService.save(ukraineState);
    }
}
