CREATE TABLE IF NOT EXISTS orders
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_date TIMESTAMP   NOT NULL,
    user_id    BIGINT      NOT NULL,
    status     VARCHAR(40) NOT NULL,
    product_id BIGINT      NOT NULL,
    quantity   INT         NOT NULL check ( quantity >= 0 )
);