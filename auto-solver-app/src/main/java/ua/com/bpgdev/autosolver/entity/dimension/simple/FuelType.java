package ua.com.bpgdev.autosolver.entity.dimension.simple;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_fuel_type")
public class FuelType extends SimpleDimension {
    @Id
    @Column(name = "d_fuel_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    public FuelType(String name, int value) {
        super(name, value);
    }
}
