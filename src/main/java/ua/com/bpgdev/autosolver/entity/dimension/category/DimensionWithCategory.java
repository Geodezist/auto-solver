package ua.com.bpgdev.autosolver.entity.dimension.category;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
class DimensionWithCategory {
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


