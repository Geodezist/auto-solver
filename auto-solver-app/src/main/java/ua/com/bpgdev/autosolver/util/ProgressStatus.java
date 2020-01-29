package ua.com.bpgdev.autosolver.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgressStatus {
    Integer total = 0;
    Integer current = 0;
    Integer percents = 0;
}
