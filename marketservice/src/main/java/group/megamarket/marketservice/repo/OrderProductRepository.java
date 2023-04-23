package group.megamarket.marketservice.repo;

import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {
    @Modifying
    @Query(value = "DELETE FROM order_product WHERE order_id IN ( SELECT id FROM orders WHERE user_id = ?2 ) AND product_id = ?1", nativeQuery = true)
    void deleteOrderProductByProductId(Long productId, Long userId);


}