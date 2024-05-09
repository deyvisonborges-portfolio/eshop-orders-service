CREATE TABLE order_payments (
  id VARCHAR(36) PRIMARY KEY,
  order_id VARCHAR(36),
  FOREIGN KEY (order_id) REFERENCES orders(id)
);