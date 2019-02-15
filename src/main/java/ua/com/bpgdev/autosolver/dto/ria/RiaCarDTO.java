package ua.com.bpgdev.autosolver.dto.ria;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RiaCarDTO {
    private int carId;
    private int year;
    private int mileage;
    private int priceUSD;
    private int categoryValue;
    private int bodystyleValue;
    private int markValue;
    private int modelValue;
    private String description;
    private String fuelTyeName;
    private String fuelTypeNameEng;
    private String gearboxName;
    private String ukraineStateName;
    private String cityName;
}
