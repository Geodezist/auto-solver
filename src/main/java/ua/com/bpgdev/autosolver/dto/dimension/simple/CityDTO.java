package ua.com.bpgdev.autosolver.dto.dimension.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO extends SimpleDTO {
    private String ukraineStateName;
    private int ukraineStateValue;
}
