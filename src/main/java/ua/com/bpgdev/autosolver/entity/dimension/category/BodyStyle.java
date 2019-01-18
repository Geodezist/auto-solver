package ua.com.bpgdev.autosolver.entity.dimension.category;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_bodystyle")
public class BodyStyle extends DimensionWithCategory {
    @Id
    @Column(name = "d_bodystyle_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public BodyStyle(Category category, String name, int value) {
        super(category, name, value);
    }
}
