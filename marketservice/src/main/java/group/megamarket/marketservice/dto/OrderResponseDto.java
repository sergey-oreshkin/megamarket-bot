package group.megamarket.marketservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import group.megamarket.marketservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link group.megamarket.marketservice.entity.Order} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto implements Serializable {
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate orderDate;
    private Long userId;
    private Status status;
    private List<OrderProductDto> orderProducts;
}