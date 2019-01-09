package ua.com.bpgdev.autosolver.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "d_bodystyle")
public class BodyStyle {
    @Id
    @Column(name = "d_bodystyle_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "d_category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NonNull
    private Category category;

    @NonNull
    private String name;

    @NonNull
    private int value;
}
