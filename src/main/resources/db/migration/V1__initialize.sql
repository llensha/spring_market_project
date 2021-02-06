CREATE TABLE products (
    product_id      BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255),
    price           INT,
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
                          title           VARCHAR(255),
                          price           INT,
                          quantity        INT,
                          summa           INT,
                          created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);