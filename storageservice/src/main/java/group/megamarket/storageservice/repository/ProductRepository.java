package group.megamarket.storageservice.repository;

import group.megamarket.storageservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByUserId(Long userId);

    Long deleteByUserId(Long userId);

    Optional<Product> findByUserIdAndName(Long userId, String name);
}
