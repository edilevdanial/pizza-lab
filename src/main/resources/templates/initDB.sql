-- pg_ctl -D /opt/homebrew/var/postgres start


-- Table: Users (Пользователи)
CREATE TABLE Users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(100) NOT NULL,
    email         VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    phone         VARCHAR(50),
    address       VARCHAR(255),
    is_active     BOOLEAN DEFAULT TRUE
);

-- Table: Admins (Администраторы)
CREATE TABLE Admins
(
    id      SERIAL PRIMARY KEY,
    username      VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    is_active     BOOLEAN DEFAULT TRUE,
    roles         VARCHAR(50) NOT NULL
);

-- Table: Pizzas (Пиццы)
CREATE TABLE Pizza
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL,
    is_active   BOOLEAN DEFAULT TRUE,
    image_url   VARCHAR(255)
);

-- -- Table: Categories (Категории пиццы)
-- CREATE TABLE Categories
-- (
--     id          SERIAL PRIMARY KEY,
--     name        VARCHAR(100) NOT NULL UNIQUE,
--     is_active   BOOLEAN DEFAULT TRUE,
--     description TEXT
-- );

-- -- Table: Ingredients (Ингредиенты)
-- CREATE TABLE Ingredients
-- (
--     id   SERIAL PRIMARY KEY,
--     name VARCHAR(100) NOT NULL
-- );


-- Table: Orders (Заказы)
CREATE TABLE Orders
(
    id           SERIAL PRIMARY KEY,
    user_id      INT            NOT NULL,
    order_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount NUMERIC(10, 2) NOT NULL,
    status       VARCHAR(50)    NOT NULL,
    CONSTRAINT fk_order_user FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE
);

-- Table: Order_Pizzas (Заказанные пиццы)
CREATE TABLE Order_Pizzas
(
    id SERIAL PRIMARY KEY,
    order_id       INT            NOT NULL,
    pizza_id       INT            NOT NULL,
    quantity       INT            NOT NULL,
    size           VARCHAR(50),
    CONSTRAINT fk_orderpizza_order FOREIGN KEY (order_id) REFERENCES Orders (id) ON DELETE CASCADE,
    CONSTRAINT fk_orderpizza_pizza FOREIGN KEY (pizza_id) REFERENCES Pizza (id) ON DELETE CASCADE
);

-- Table: Payments (Оплаты)
CREATE TABLE Payments
(
    id     SERIAL PRIMARY KEY,
    order_id       INT            NOT NULL,
    payment_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount         NUMERIC(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES Orders (id) ON DELETE CASCADE
);

-- -- Table: Reviews (Отзывы)
-- CREATE TABLE Reviews
-- (
--     review_id   SERIAL PRIMARY KEY,
--     user_id     INT NOT NULL,
--     pizza_id    INT NOT NULL,
--     rating      INT NOT NULL,
--     comment     TEXT,
--     review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES Users (user_id) ON DELETE CASCADE,
--     CONSTRAINT fk_review_pizza FOREIGN KEY (pizza_id) REFERENCES Pizzas (pizza_id) ON DELETE CASCADE
-- );

-- Optional Table: Coupons (Купоны)
CREATE TABLE Coupons
(
    id        SERIAL PRIMARY KEY,
    code             VARCHAR(50) NOT NULL UNIQUE,
    discount_amount  NUMERIC(10, 2),
    expiry_date      DATE,
    min_order_amount NUMERIC(10, 2)
);

-- Optional Table: Delivery (Доставка)
CREATE TABLE Delivery
(
    id     SERIAL PRIMARY KEY,
    order_id        INT NOT NULL,
    delivery_person VARCHAR(100),
    delivery_time   TIMESTAMP,
    tracking_status VARCHAR(100),
    CONSTRAINT fk_delivery_order FOREIGN KEY (order_id) REFERENCES Orders (id) ON DELETE CASCADE
);
