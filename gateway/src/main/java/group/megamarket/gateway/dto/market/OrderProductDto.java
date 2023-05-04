package group.megamarket.gateway.dto.market;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderProductDto {

    private Long productId;

    private Integer quantity;
}
