package ua.com.bpgdev.autosolver.service.ria.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaRestDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;
import ua.com.bpgdev.autosolver.dto.ria.SearchRequestDTO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRiaSearchResultServiceTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private RiaRestDao riaRestDao;
    private DefaultRiaSearchResultService defaultRiaSearchResultService;

    @Test
    @SneakyThrows
    public void getSearchResult() {
        List<Integer> expectedCarIds = List.of(27882282, 28050424, 27946127, 28061578, 28049229, 28014652, 27731200, 27844373, 28019477, 27997492);
        defaultRiaSearchResultService = new DefaultRiaSearchResultService(riaRestDao, objectMapper);

        String rawJson = Files.readString(Path.of("src/test/resources/json/search_result.json"));
        JsonNode jsonNode = objectMapper.readTree(rawJson);

        RiaSearchResultDTO expectedRiaSearchResultDTO = new RiaSearchResultDTO();
        expectedRiaSearchResultDTO.setQueryString("test");
        expectedRiaSearchResultDTO.setPage(0);
        expectedRiaSearchResultDTO.setCarIds(expectedCarIds);
        expectedRiaSearchResultDTO.setTotalCount(expectedCarIds.size());
        expectedRiaSearchResultDTO.setPageSize(10);

        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        Mockito.when(riaRestDao.getSearchResult(searchRequestDTO)).thenReturn(jsonNode);
        Set<Integer> actualCarIds = defaultRiaSearchResultService.getSearchResult(searchRequestDTO);
        assertEquals(expectedCarIds, new ArrayList<>(actualCarIds));
    }
}