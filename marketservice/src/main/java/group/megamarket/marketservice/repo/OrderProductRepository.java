package group.megamarket.marketservice.repo;

import group.megamarket.marketservice.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Modifying
    @Query(value = "DELETE FROM order_product WHERE order_id IN ( SELECT id FROM orders WHERE user_id = ?2 ) AND product_id = ?1", nativeQuery = true)
    int deleteOrderProductByProductId(Long productId, Long userId);

    Optional<OrderProduct> findByProductIdAndOrder_UserId(Long productId, Long userId);
}