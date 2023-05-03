package group.megamarket.storageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ДТО продукта
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private Integer count;
}
