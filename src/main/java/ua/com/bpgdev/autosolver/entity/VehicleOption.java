package ua.com.bpgdev.autosolver.entity;

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
@Table(name = "d_option")
public class VehicleOption extends DimensionWithCategory {
    @Id
    @Column(name = "d_option_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public VehicleOption(Category category, String name, int value) {
        super(category, name, value);
    }
}
