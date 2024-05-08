CREATE TABLE order_items (
  id VARCHAR(255) NOT NULL PRIMARY KEY,
  active BOOLEAN,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  product_id VARCHAR(255) NOT NULL,
  quantity INT NOT NULL,
  price_amount DECIMAL(19,2) NOT NULL,
  price_currency VARCHAR(255) NOT NULL,
  
  order_id VARCHAR(255) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id)
);