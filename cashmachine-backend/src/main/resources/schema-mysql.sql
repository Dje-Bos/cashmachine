DROP TABLE IF EXISTS product_prices;
DROP TABLE IF EXISTS product_stocks;
DROP TABLE IF EXISTS receipt_entries;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS receipts;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF exists users ;
DROP TABLE IF EXISTS roles;

CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    creation_time TIMESTAMP   NOT NULL,
    email         VARCHAR(25) NOT NULL UNIQUE,
    name          VARCHAR(50),
    auth          VARCHAR(25) NOT NULL,
    password      VARCHAR(150),
    picture_url   VARCHAR(255),
    is_active     BOOLEAN     NOT NULL
);

CREATE TABLE roles
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    creation_time TIMESTAMP   NOT NULL,
    uid           VARCHAR(20) NOT NULL UNIQUE,
    description   VARCHAR(255)
);

CREATE TABLE user_roles
(
    user_id BIGINT,
    role_id BIGINT,
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE products
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    creation_time           TIMESTAMP   NOT NULL,
    name                    VARCHAR(35) NOT NULL,
    code                    VARCHAR(25) NOT NULL UNIQUE,
    is_allowed_for_purchase BOOLEAN     NOT NULL
    unit                    VARCHAR(25) NOT NULL,
    price                   DOUBLE      NOT NULL,
    in_stock                DOUBLE      NOT NULL
);


CREATE TABLE receipts
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    creation_time TIMESTAMP NOT NULL,
    cashier_id    BIGINT    NOT NULL,
    total         DECIMAL NOT NULL DEFAULT 0,
    status        VARCHAR(25),
    CONSTRAINT cashier_id_fk FOREIGN KEY (cashier_id) REFERENCES users (id)
);

CREATE TABLE receipt_entries
(
    product_id     BIGINT,
    receipt_id     BIGINT,
    order_number   INT     NOT NULL,
    order_quantity DECIMAL NOT NULL,
    total          DECIMAL NOT NULL,

    CONSTRAINT receipt_entries_pk PRIMARY KEY (product_id, receipt_id),
    CONSTRAINT receipt_id_fk FOREIGN KEY (receipt_id) REFERENCES receipts (id),
    CONSTRAINT receipt_product_id_fk FOREIGN KEY (product_id) REFERENCES products (id)

);


