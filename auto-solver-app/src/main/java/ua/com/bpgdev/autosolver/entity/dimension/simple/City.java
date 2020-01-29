package ua.com.bpgdev.autosolver.entity.dimension.simple;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_city")
public class City extends SimpleDimension {
    @Id
    @Column(name = "d_city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "d_state_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NonNull
    private UkraineState ukraineState;


    public City(UkraineState ukraineState, String name, int value) {
        super(name, value);
        this.ukraineState = ukraineState;
    }
}
