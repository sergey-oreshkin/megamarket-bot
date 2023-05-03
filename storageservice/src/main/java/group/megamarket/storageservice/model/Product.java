package group.megamarket.storageservice.model;

import lombok.*;

import javax.persistence.*;

/**
 * Отображение таблицы products в БД
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Integer count;

    @Column(name = "user_id")
    private Long userId;
}
