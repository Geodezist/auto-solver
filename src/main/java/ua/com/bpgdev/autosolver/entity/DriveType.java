package ua.com.bpgdev.autosolver.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_drive_type")
public class DriveType extends DimensionWithCategory {
    @Id
    @Column(name = "d_drive_type_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public DriveType(Category category, String name, int value) {
        super(category, name, value);
    }
}
