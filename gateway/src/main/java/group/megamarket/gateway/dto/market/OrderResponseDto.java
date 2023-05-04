package group.megamarket.gateway.dto.market;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate orderDate;

    private Long userId;

    private StatusDto statusDto;

    private List<OrderProductDto> orderProducts;

}
