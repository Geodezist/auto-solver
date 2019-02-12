package ua.com.bpgdev.autosolver.dto.dimension.simple;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CityDTO extends SimpleDTO {
    private String ukraineStateName;
    private int ukraineStateValue;
}
