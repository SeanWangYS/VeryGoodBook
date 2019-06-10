-- SELECT orders.id as oid, customers.id as cid, customers.name as cname, order_date, order_time,status,
--         payment_type,payment_fee,payment_note,
--         shipping_type,shipping_fee,shipping_note,
--         recipient_name,recipient_email,recipient_phone,recipient_address,
--         product_id, products.name as pname, photo_url,color,price,quantity
--     FROM orders LEFT JOIN order_items ON orders.id = order_items.order_id 
--     LEFT JOIN customers ON orders.customer_id = customers.id
--     LEFT JOIN products ON order_items.product_id = products.id
-- WHERE orders.id="2";



SELECT id, customer_id, order_date,order_time,payment_type,payment_fee,shipping_type,shipping_fee, 
    SUM(price*quantity) as total_amount
FROM orders LEFT JOIN order_items ON orders.id = order_items.order_id 
WHERE customer_id='A223456781'
GROUP BY orders.id
--HAVING total_amount>1000
ORDER BY order_date desc,order_time desc;