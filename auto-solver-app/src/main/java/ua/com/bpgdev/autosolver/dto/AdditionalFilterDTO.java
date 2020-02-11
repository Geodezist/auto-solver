package ua.com.bpgdev.autosolver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AdditionalFilterDTO {
    @NonNull
    private String name;
    private String description;
}
