package ua.com.bpgdev.autosolver.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Table(name = "d_user")
public class User {
    @Id
    @Column(name = "d_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(name = "password_hash")
    private String passwordHash;

    @NonNull
    private String roles;

    @NonNull
    private String email;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "create_ts")
    private Date createTs = new Date();

    @Column(name = "modify_ts")
    private Date modifyTs;

    @Column(name = "start_date")
    private Date startDate = new Date();

    @NonNull
    @Column(name = "end_date")
    private Date endDate;


}
