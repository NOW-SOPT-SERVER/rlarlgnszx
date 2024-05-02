package server.sopt.carrot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.constant.Place;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {


    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String itemName;

    @Setter
    @Column(nullable = false)
    private String itemDescription;

    @Setter
    @Column(nullable = false)
    private Integer price;

    @Getter
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Place place;

    @ColumnDefault("0")
    @Builder.Default
    @Setter
    private Integer good=0;

    @Setter
    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
//    @ColumnDefault("NOT_SOLD")
    private CellingStatus cellingStatus = CellingStatus.NOT_SOLD;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
