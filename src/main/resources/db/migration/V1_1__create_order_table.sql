CREATE TABLE orders (
  id VARCHAR(255) NOT NULL PRIMARY KEY,
  active BOOLEAN,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  status VARCHAR(40) NOT NULL,
  customer_id VARCHAR(255) NOT NULL,
  shipping_fee_amount DECIMAL(19,2) NOT NULL,
  shipping_fee_currency VARCHAR(255) NOT NULL,
  subtotal_amount DECIMAL(19,2) NOT NULL,
  subtotal_currency VARCHAR(255) NOT NULL,
  discount_amount DECIMAL(19,2) NOT NULL,
  discount_currency VARCHAR(255) NOT NULL,
  total_amount DECIMAL(19,2) NOT NULL,
  total_currency VARCHAR(255) NOT NULL
);
