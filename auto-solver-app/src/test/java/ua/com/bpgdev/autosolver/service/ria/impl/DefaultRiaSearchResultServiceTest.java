package ua.com.bpgdev.autosolver.service.ria.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaSearchResultDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRiaSearchResultServiceTest {
    @Mock
    private RiaSearchResultDao riaSearchResultDao;
    @InjectMocks
    private DefaultRiaSearchResultService defaultRiaSearchResultService;

    @Test
    public void getSearchResult() {
        List<Integer> expectedCarIds = new ArrayList<>();
        expectedCarIds.add(1);
        expectedCarIds.add(2);

        RiaSearchResultDTO expectedRiaSearchResultDTO = new RiaSearchResultDTO();
        expectedRiaSearchResultDTO.setQueryString("test");
        expectedRiaSearchResultDTO.setPage(0);
        expectedRiaSearchResultDTO.setCarIds(expectedCarIds);
        expectedRiaSearchResultDTO.setTotalCount(expectedCarIds.size());
        expectedRiaSearchResultDTO.setPageSize(10);

        Mockito.when(riaSearchResultDao.getSearchResult("test")).thenReturn(expectedRiaSearchResultDTO);
        Set<Integer> actualCarIds = defaultRiaSearchResultService.getSearchResult("test");
        assertEquals(expectedCarIds, new ArrayList<>(actualCarIds));

        expectedCarIds = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            expectedCarIds.add(i);
        }

        expectedRiaSearchResultDTO.setCarIds(expectedCarIds);
        expectedRiaSearchResultDTO.setTotalCount(expectedCarIds.size());
        Mockito.when(riaSearchResultDao.getSearchResult("test&page=" + 1)).thenReturn(expectedRiaSearchResultDTO);
        actualCarIds = defaultRiaSearchResultService.getSearchResult("test");
        assertEquals(expectedCarIds, new ArrayList<>(actualCarIds));
    }
}