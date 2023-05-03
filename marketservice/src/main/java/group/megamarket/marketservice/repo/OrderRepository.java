package group.megamarket.marketservice.repo;

import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join fetch o.orderProducts where o.userId = ?1 and o.status = ?2")
    Optional<Order> findByUserIdAndStatus(Long userId, Status status);

    int deleteByUserId(Long userId);

}