package ua.com.bpgdev.autosolver.dto.ria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String carTitle;
    private String sourceMessage;
    private String linkToAutoRia;
}
