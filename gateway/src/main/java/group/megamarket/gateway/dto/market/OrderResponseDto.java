package group.megamarket.gateway.dto.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderResponseDto {

    private LocalDate orderDate;

    private Long userId;

    private StatusDto statusDto;

    private List<OrderProductDto> orderProducts;

}
