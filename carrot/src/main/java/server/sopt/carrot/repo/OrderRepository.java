package server.sopt.carrot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.sopt.carrot.entity.Customer;
import server.sopt.carrot.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
