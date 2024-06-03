package server.sopt.carrot.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @OneToOne
    Product product;

    @Builder
    public Image(String imageUrl, Product product) {
        this.imageUrl = imageUrl;
        this.product = product;
    }
}
