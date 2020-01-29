package ua.com.bpgdev.autosolver.dto.ria;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RiaCarDTO {
    private Integer carId;
    private Integer year;
    private Integer mileage;
    private Integer priceUSD;
    private Integer categoryValue;
    private Integer bodystyleValue;
    private Integer markValue;
    private Integer modelValue;
    private String description;
    private String fuelTypeName;
    private String fuelTypeNameEng;
    private String gearboxName;
    private String ukraineStateName;
    private String cityName;
}
