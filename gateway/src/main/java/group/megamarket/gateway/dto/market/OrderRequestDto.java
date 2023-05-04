package group.megamarket.gateway.dto.market;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderRequestDto {

    private Long productId;

    private int quantity;

    private Long userId;
}
