CREATE TABLE IF NOT EXISTS orders
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_date DATE        NOT NULL,
    user_id    BIGINT      NOT NULL,
    status     VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_product
(
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL check ( quantity >= 0 ),
    constraint pk primary key (order_id, product_id),
    constraint order_id foreign key (order_id) references orders(id)
);