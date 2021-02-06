CREATE TABLE users (
                       id                      BIGSERIAL PRIMARY KEY,
                       username                VARCHAR(30) NOT NULL UNIQUE,
                       password                VARCHAR(80) NOT NULL,
                       email                   VARCHAR(50) unique,
                       created_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
                       id                      BIGSERIAL PRIMARY KEY,
                       name                    VARCHAR(50) NOT NULL UNIQUE,
                       created_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users_roles (
                             user_id                 BIGINT NOT NULL REFERENCES users(id),
                             role_id                 BIGINT NOT NULL REFERENCES roles(id),
                             primary key (user_id, role_id)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES
('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(2, 2);

CREATE TABLE products (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    price           INT NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                      );

INSERT INTO products (title, price) VALUES
('Авокадо', 70),
('Хлеб', 1),
('Лосось', 50),
('Яблоко', 15),
('Макароны', 10),
('Говядина', 90),
('Пирожок с вишней', 10),
('Торт', 30),
('Тунец', 35),
('Свинина', 55),
('Банан', 20),
('Печенье', 25),
('Вермишель', 12),
('Грейпфрут', 35),
('Огурец', 10),
('Помидор', 7),
('Мороженое', 25),
('Шоколад', 13),
('Лимон', 5),
('Варенье', 15);

CREATE TABLE order_items (
                          id              BIGSERIAL PRIMARY KEY,
                          product_id      BIGINT NOT NULL REFERENCES products(id),
                          title           VARCHAR(255),
                          price           INT,
                          quantity        INT,
                          summa           INT,
                          created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);