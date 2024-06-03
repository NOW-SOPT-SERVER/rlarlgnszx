package server.sopt.carrot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.sopt.carrot.entity.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByProduct_Id(Long proudctId);
}
