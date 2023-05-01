package group.megamarket.marketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link group.megamarket.marketservice.entity.Order} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Long productId;
    private int quantity;
    private Long userId;
}
