package group.megamarket.marketservice.repo;

import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {
    @Modifying
    @Query("DELETE FROM OrderProduct op " +
            "WHERE op.pk.order.userId = ?2 " +
            "AND op.pk.productId = ?1")
    int deleteOrderProductByProductId(Long productId, Long userId);


}