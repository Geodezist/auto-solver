package ua.com.bpgdev.autosolver.entity.dimension.category;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    public VehicleOption(Category category, String name, int value) {
        super(category, name, value);
    }
}
