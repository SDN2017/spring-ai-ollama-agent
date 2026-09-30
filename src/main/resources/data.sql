INSERT INTO orders (order_id, status, created_at)
VALUES
    ('1042', 'Shipped - arriving tomorrow', NOW()),
    ('1043', 'Processing - not yet shipped', NOW()),
    ('1044', 'Cancelled - out of stock', NOW()),
    ('1045', 'Shipped - arriving next week', NOW())
ON CONFLICT (order_id) DO NOTHING;

INSERT INTO inventory (product_name, quantity)
VALUES
    ('Bluetooth Headphones', 5),
    ('Wireless Mouse', 42),
    ('USB-C Cable', 0),
    ('Laptop Stand', 12),
    ('External Hard Drive', 7),
    ('Smartphone Case', 0),
    ('Portable Charger', 15),
    ('Gaming Keyboard', 3),
    ('Webcam', 8),
    ('Noise-Cancelling Earbuds', 0)
ON CONFLICT (product_name) DO NOTHING;
