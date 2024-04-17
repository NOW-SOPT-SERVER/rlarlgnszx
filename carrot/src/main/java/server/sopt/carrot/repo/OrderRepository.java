package server.sopt.carrot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.sopt.carrot.entity.OrderProcessor;

@Repository
public interface OrderRepository extends JpaRepository<OrderProcessor, Long> {
}
