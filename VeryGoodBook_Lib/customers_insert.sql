/*
INSERT INTO table name
    (id, name,  ．．．,married,　．．)
VALUES('value' , ’value’, ．．．,false,．．)

*/
USE vgb;
TRUNCATE TABLE products;
INSERT INTO customers
(id,name,password,email,gender,birthday)
VALUES('A123456789','菜狄倫','123456','test01@gmail.com','M','1987-07-05','0965235456');

INSERT INTO customers
(id,name,password,email,gender,birthday,
    phone,address,married,blood_type,discount,class_name)
VALUES('A223456781','蔡六月','123456','junejune@gmail.com','F','1989-09-06',
'0965124588','台中市',false,'O',15,'VIP');

INSERT INTO customers
(id,name,password,email,gender,birthday,
    phone,address,married,blood_type,discount,class_name)
VALUES('A123123123','林微微','123456','willy@gmail.com','M','1985-09-04',
'0933125873','新竹市風很大的地方',true,'AB',25,'VIP');

INSERT INTO customers
(id,name,password,email,gender,birthday,
    phone,address,married,blood_type,discount,class_name)
VALUES('A123123132','陳姍姍','123456','sandy@gmail.com','M','1993-10-04',
'0933125873','新竹市風很大的地方',false,'A',15,'VIP');