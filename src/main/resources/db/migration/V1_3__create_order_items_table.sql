CREATE TABLE order_items (
  id VARCHAR(36) PRIMARY KEY,
  active BOOLEAN,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  product_id VARCHAR(255),
  quantity INT,
  price_amount DECIMAL(19,2),
  price_currency VARCHAR(255),
  
  order_id VARCHAR(36),
  FOREIGN KEY (order_id) REFERENCES orders(id)
);