delete from receipts;
delete from products;
delete from roles;
delete from users;



INSERT INTO `users` VALUES (1,'2019-06-21 01:52:10','email','name','GOOGLE',NULL,NULL,1),(2,'2019-06-20 22:52:18','machine@gmail.com','immachnine','BASIC',NULL,NULL,1),(3,'2019-06-20 22:52:19','funnyman@gmail.com','santa','BASIC',NULL,NULL,0),(4,'2019-06-20 22:52:19','zatovw@gmail.com','zatovw@gmail.com','BASIC','{noop}password','https://peka2.tv/images/profile/113282/avatar.jpg',1),(5,'2019-06-20 22:52:19','nick.fury@gmail.com','nick_fury','BASIC','{noop}shield',NULL,1),(6,'2019-06-20 22:52:19','freshmeat@gmail.com','pudge','BASIC',NULL,NULL,1),(7,'2019-06-20 22:52:19','podlivich@mail.ru','podlivich','BASIC','{noop}x5m',NULL,1),(8,'2019-06-20 22:52:19','goodgame@ru','sana','BASIC',NULL,NULL,1),(9,'2019-06-20 22:52:19','user@gmail.com','user@gmail.com','BASIC','cashier',NULL,1),(10,'2019-06-20 22:52:19','debi.lyou@ukr.net','debi-lyou','BASIC',NULL,NULL,1);

INSERT INTO `roles` VALUES (1,'2019-06-20 22:52:18','SENIOR_CASHIER',NULL),(2,'2019-06-20 22:52:18','ADMIN',NULL),(3,'2019-06-20 22:52:18','MERCHANDISE',NULL),(4,'2019-06-20 22:52:18','CASHIER',NULL);

INSERT INTO `user_roles` (user_id, role_id) VALUES
(4, 4),
(4, 3),
(4, 2),
(4, 1),
(7, 4),
(5, 2);

INSERT INTO `products`
(id   ,creation_time     ,name           ,code     ,is_allowed_for_purchase, unit,    price,    in_stock) VALUES
(1,'2019-06-20 22:52:19','Revo','111111', true, 'unit', 400, 43),
(2,'2019-06-20 22:52:19','Revasik','222', true, 'unit', 300, 56),
(3,'2019-06-20 22:52:19','Waffles','322', true, 'unit', 200, 58),
(4,'2019-06-20 22:52:19','Petrushka','3', true, 'unit', 100, 32);

INSERT INTO `receipts` VALUES (1,'2019-06-21 01:52:18',4,0,'OPENED'),(2,'2019-06-20 22:52:19',4,0,'OPENED'),(3,'2019-06-20 22:52:19',9,0,'OPENED');




