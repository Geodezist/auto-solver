package ua.com.bpgdev.autosolver.entity;

import lombok.*;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
class SimpleDimension {
    @NonNull
    private String name;

    @NonNull
    private int value;
}
