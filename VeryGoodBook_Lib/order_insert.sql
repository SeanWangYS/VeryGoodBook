INSERT INTO orders
(id,customer_id,order_date,order_time,payment_type,payment_fee,
    shipping_type,shipping_fee,recipient_name,recipient_email,recipient_phone,recipient_address)
VALUES(?,?,?,?,?,?,?,?,?,?,?,?);

INSERT INTO order_items
(order_id,product_id,color,price,quantity)
VALUES(?,?,?,?,?);


