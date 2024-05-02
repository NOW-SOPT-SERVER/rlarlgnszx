package server.sopt.carrot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.sopt.carrot.constant.CellingStatus;
import server.sopt.carrot.dto.ProductFindDto;
import server.sopt.carrot.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    ProductFindDto getProductById(Long id);
    List<Product> findProductsByCustomerId(Long customerId);

    List<Product> findProductsByCellingStatusEquals(CellingStatus cellingStatus);
    List<Product> findProductsByPlace(Place place);
}
