package dasturlash.uz.entity;

import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.Visible;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title")
    private String title;

    @Column
    private String description;

    @Column
    private String content;

    @Column(name = "shared_count")
    private Integer sharedCount;

    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "region_id")
    private Integer regionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private Region region;

    @Column(name = "moderator_id")
    private Integer moderatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    private Profile moderator;

    @Column(name = "publisher_id")
    private Integer publisherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    private Profile publisher;

    @Column
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column
    private Double readTime;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "published_date")
    private LocalDateTime publishedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private Visible visible;

    @Column(name = "view_count")
    private Integer viewCount;
}
