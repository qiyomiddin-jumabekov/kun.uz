package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attach {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "original_name")
    private String originalName;
    @Column(name = "file_path")
    private String path;
    @Column(name = "file_size")
    private Long size;
    @Column(name = "file_extension")
    private String extension;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime creationDate;
}
