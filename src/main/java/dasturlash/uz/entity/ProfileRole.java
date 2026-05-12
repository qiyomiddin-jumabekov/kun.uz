package dasturlash.uz.entity;

import dasturlash.uz.enums.ProfileRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProfileRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @Column
    private ProfileRoles role;

}
