package ua.com.bpgdev.autosolver.dto.dimension.simple;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleDTO {
    @NonNull
    private String name;

    @NonNull
    private int value;
}
