package server.sopt.carrot.entity;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderProcessor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Long customerWhoSellId;

    @Setter
    @Column(nullable = false)
    private Long customerWhoBuyId;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private Long productId;


    public OrderProcessor(Long customerWhoSellId, Long customerWhoBuyId, Long productId) {
        this.customerWhoSellId = customerWhoSellId;
        this.customerWhoBuyId = customerWhoBuyId;
        this.productId = productId;
    }

    public static OrderProcessor of(Long customerWhoSellId, Long customerWhoBuyId, Long productId) {
        return new OrderProcessor(customerWhoSellId, customerWhoBuyId, productId);
    }
}
