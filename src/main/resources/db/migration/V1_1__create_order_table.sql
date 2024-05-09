CREATE TABLE orders (
  id VARCHAR(36) PRIMARY KEY,
  active BOOLEAN,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  status VARCHAR(40),
  customer_id VARCHAR(255),
  shipping_fee_amount DECIMAL(19,2),
  shipping_fee_currency VARCHAR(255),
  subtotal_amount DECIMAL(19,2),
  subtotal_currency VARCHAR(255),
  discount_amount DECIMAL(19,2),
  discount_currency VARCHAR(255),
  total_amount DECIMAL(19,2),
  total_currency VARCHAR(255)
);
