-- SELECT * FROM products;
-- 
-- SELECT * FROM products WHERE name LIKE '%色鉛筆%';
-- 
-- SELECT * FROM products WHERE name LIKE '%色鉛筆%' OR description LIKE '%色鉛筆%';

-- SELECT * FROM products WHERE id='1';

SELECT *, stock+unit_price FROM products LEFT OUTER JOIN book_details ON products.id = book_details.product_id ORDER BY stock,unit_price DESC;

-- SELECT class_name FROM products;
