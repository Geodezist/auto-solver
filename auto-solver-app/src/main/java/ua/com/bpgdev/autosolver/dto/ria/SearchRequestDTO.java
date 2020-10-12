package ua.com.bpgdev.autosolver.dto.ria;

import feign.Param;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequestDTO {
    @Param("category_id")
    private Integer categoryId;
    @Param("marka_id")
    private Integer markaId;
    @Param("model_id")
    private Integer modelId;
    @Param("s_yers[0]")
    private Integer yearFrom;
    @Param("po_yers[0]")
    private Integer yearTo;
    @Param("type[0]")
    private Integer type;
    private Integer raceFrom;
    private Integer raceTo;
    private Integer[] gearbox;
    private Integer currency = 1;
    private Integer abroad = 2;
    private Integer custom = 1;
    @Param("countpage")
    private Integer countPage;
    private Integer page = 0;
    private boolean saveCars = false;
    private String addFilter;
}
