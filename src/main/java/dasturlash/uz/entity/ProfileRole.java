package dasturlash.uz.entity;

import dasturlash.uz.enums.ProfileRoles;
import jakarta.persistence.*;

@Entity
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
