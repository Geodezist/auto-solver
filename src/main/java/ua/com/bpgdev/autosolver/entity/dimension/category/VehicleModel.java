package ua.com.bpgdev.autosolver.entity.dimension.category;

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
@Table(name = "d_model")
public class VehicleModel {
    @Id
    @Column(name = "d_model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "d_mark_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NonNull
    private VehicleMark vehicleMark;

    @NonNull
    private String name;

    @NonNull
    private int value;
}
