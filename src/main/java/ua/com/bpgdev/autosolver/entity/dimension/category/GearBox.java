package ua.com.bpgdev.autosolver.entity.dimension.category;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_gearbox")
public class GearBox extends DimensionWithCategory {
    @Id
    @Column(name = "d_gearbox_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    public GearBox(Category category, String name, int value) {
        super(category, name, value);
    }
}
