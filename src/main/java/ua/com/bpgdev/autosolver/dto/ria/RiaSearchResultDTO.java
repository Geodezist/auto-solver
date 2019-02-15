package ua.com.bpgdev.autosolver.dto.ria;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RiaSearchResultDTO {
    private String queryString;
    private int page;
    private List<Integer> carIds;
    private int totalCount;
}
