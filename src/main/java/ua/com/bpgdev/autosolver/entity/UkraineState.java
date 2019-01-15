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
@Table(name = "d_state")
public class UkraineState extends SimpleDimension {
    @Id
    @Column(name = "d_state_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public UkraineState(String name, int value) {
        super(name, value);
    }
}
