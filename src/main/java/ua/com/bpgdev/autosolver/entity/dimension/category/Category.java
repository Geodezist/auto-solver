package ua.com.bpgdev.autosolver.entity.dimension.category;

import lombok.*;
import ua.com.bpgdev.autosolver.entity.dimension.simple.SimpleDimension;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_category")
public class Category extends SimpleDimension {
    @Id
    @Column(name = "d_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    public Category(String name, int value) {
        super(name, value);
    }

    public Category(Long id, String name, int value) {
        super(name, value);
        this.id = id;
    }
}
