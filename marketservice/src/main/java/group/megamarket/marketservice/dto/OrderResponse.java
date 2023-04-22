package group.megamarket.marketservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import group.megamarket.marketservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link group.megamarket.marketservice.entity.Order} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse implements Serializable {
    private Long productId;
    private Long quantity;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate orderDate;
    private Long userId;
    private Status status;
}