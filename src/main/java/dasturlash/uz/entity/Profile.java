package dasturlash.uz.entity;

import dasturlash.uz.enums.Status;
import dasturlash.uz.enums.Visible;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String phone;
    @Column(unique = true)
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    @Enumerated(EnumType.STRING)
    private Visible visible;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    private Integer photoId;

}
