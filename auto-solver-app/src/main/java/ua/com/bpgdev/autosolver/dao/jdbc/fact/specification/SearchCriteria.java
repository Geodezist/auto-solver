package ua.com.bpgdev.autosolver.dao.jdbc.fact.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String column;
    private Object value;
    private SearchOperation operation;
    private String andThen;
}
