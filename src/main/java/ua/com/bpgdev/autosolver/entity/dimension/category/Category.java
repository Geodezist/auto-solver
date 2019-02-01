package ua.com.bpgdev.autosolver.entity.dimension.category;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "d_category")
public class Category {
    @Id
    @Column(name = "d_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int value;
}
