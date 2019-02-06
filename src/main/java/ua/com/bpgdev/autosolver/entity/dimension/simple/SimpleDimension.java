package ua.com.bpgdev.autosolver.entity.dimension.simple;

import lombok.*;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public class SimpleDimension {
    @NonNull
    private String name;

    @NonNull
    private int value;
}
