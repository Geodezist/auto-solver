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
@Table(name = "d_color")
public class VehicleColor extends SimpleDimension {
    @Id
    @Column(name = "d_color_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public VehicleColor(String name, int value) {
        super(name, value);
    }
}
