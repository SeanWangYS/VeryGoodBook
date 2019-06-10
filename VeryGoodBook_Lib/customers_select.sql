/*
客戶查詢

SELECT   ___ , ___ , ____
FROM   table-name  JOIN
WHERE char column_name = 'value'    --文字加引號
             number_column_name != value
AND  
*/

SELECT id,name,password,email,gender,birthday,
            phone,address,married,blood_type,discount,class_name
FROM customers
WHERE id='A123456789' AND password='123456';
