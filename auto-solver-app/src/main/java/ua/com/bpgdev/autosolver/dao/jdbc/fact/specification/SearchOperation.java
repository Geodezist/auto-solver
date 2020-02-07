package ua.com.bpgdev.autosolver.dao.jdbc.fact.specification;

public enum SearchOperation {
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH,
    NOT_MATCH,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN
}
