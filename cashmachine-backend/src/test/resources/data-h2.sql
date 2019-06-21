delete from receipts;
delete from users;

INSERT INTO users (id, creation_time, email, name, password, auth, is_active)
VALUES (1, parsedatetime('17-05-2019 18:47:52', 'dd-MM-yyyy hh:mm:ss'), 'email@example.com', 'name', null, 'GOOGLE', true),
       (2, parsedatetime('17-05-2019 18:57:52', 'dd-MM-yyyy hh:mm:ss'), 'iam@batman.com', 'batman', '{noop}bat', 'BASIC', true);

INSERT INTO receipts(id, creation_time, cashier_id, total, status       ) VALUES
(1, parsedatetime('17-05-2019 18:57:52', 'dd-MM-yyyy hh:mm:ss'), 1, 10.0, 'OK'),
(2, parsedatetime('17-05-2019 18:58:52', 'dd-MM-yyyy hh:mm:ss'), 1, 11.0, 'OK'),
(3, parsedatetime('17-05-2019 18:59:52', 'dd-MM-yyyy hh:mm:ss'), 1, 12.0, 'OK'),
(4, parsedatetime('17-05-2019 19:57:52', 'dd-MM-yyyy hh:mm:ss'), 1, 13.0, 'OK');
