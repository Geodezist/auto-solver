package ua.com.bpgdev.autosolver.dto.dimension.simple;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SimpleDTO {
    @NonNull
    private String name;

    @NonNull
    private int value;
}
