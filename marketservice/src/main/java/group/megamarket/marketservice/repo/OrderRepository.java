package group.megamarket.marketservice.repo;

import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserIdAndStatus(Long userId, Status status);

    void deleteByUserId(Long userId);


}