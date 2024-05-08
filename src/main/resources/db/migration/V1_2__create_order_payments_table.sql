CREATE TABLE order_payments (
  id VARCHAR(255) NOT NULL PRIMARY KEY,
  order_id VARCHAR(255) NOT NULL,
  payment_id VARCHAR(255) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id)
);