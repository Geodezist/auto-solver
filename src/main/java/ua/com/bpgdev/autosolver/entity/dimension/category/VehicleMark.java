package ua.com.bpgdev.autosolver.entity.dimension.category;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_mark")
public class VehicleMark extends DimensionWithCategory {
    @Id
    @Column(name = "d_mark_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public VehicleMark(Category category, String name, int value) {
        super(category, name, value);
    }

    public VehicleMark(Long id, Category category, String name, int value) {
        super(category, name, value);
        this.id = id;
    }
}
