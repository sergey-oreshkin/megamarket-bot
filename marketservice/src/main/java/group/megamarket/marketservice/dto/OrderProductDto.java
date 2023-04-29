package group.megamarket.marketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link group.megamarket.marketservice.entity.OrderProduct} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto implements Serializable {
    private Long productId;
    private Integer quantity;
}