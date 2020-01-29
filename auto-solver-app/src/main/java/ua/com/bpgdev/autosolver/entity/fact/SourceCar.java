package ua.com.bpgdev.autosolver.entity.fact;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "src_car")
public class SourceCar {
    @Id
    @Column(name = "car_id")
    private Integer carId;
    private Integer year;
    private Integer mileage;
    @Column(name = "price_usd")
    private Integer priceUsd;
    @Column(name = "category_value")
    private Integer categoryValue;
    @Column(name = "bodystyle_value")
    private Integer bodystyleValue;
    @Column(name = "mark_value")
    private Integer markValue;
    @Column(name = "model_value")
    private Integer modelValue;
    @Column(name = "car_description")
    private String description;
    @Column(name = "fuel_type_name")
    private String fuelTypeName;
    @Column(name = "fuel_type_name_eng")
    private String fuelTypeNameEng;
    @Column(name = "gearbox_name")
    private String gearboxName;
    @Column(name = "ukraine_state_name")
    private String ukraineStateName;
    @Column(name = "city_name")
    private String cityName;
}
