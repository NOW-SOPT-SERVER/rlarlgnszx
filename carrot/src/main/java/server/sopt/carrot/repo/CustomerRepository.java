package server.sopt.carrot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.sopt.carrot.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
