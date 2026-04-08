DROP DATABASE IF EXISTS oenskeskyen;

CREATE DATABASE IF NOT EXISTS oenskeskyen;
USE oenskeskyen;

# lav database lokalt hvis det ikke virker 'oenskeskyen', alt i småt

CREATE TABLE IF NOT EXISTS users
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(155) UNIQUE NOT NULL,
    email      VARCHAR(155) UNIQUE NULL,
    password   VARCHAR(255)        NOT NULL,
    birthday   DATE                NOT NULL,
    created_at TIMESTAMP           NOT NULL DEFAULT NOW()

);

CREATE TABLE IF NOT EXISTS wish_list
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,

    title       VARCHAR(155)        NOT NULL,
    share_token VARCHAR(155) UNIQUE NULL,
    created_at  TIMESTAMP           NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS wish
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    wish_list_id INT            NOT NULL,
    FOREIGN KEY (wish_list_id) REFERENCES wish_list (id) ON DELETE CASCADE,

    name         VARCHAR(155)   NOT NULL,
    description  VARCHAR(500)   NULL,
    url          VARCHAR(1000)  NULL,
    price        DECIMAL(10, 2) NULL,
    priority     INT            NULL,
    created_at   TIMESTAMP      NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS reservation
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    wish_id     INT       NOT NULL UNIQUE,
    user_id     INT       NULL,
    FOREIGN KEY (wish_id) REFERENCES wish (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,

    reserved_at TIMESTAMP NOT NULL DEFAULT NOW()
);
