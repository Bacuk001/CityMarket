-- Создание базы данных.
create database if not exists city_market;

use city_market;

-- Tаблица  магазинов.
create table if not exists market(
    id int(11),
    name varchar(11) default null,
    address varchar(20) default null,
    about varchar(255) default null,
    primary key (id)
); 

-- Tаблица  складов.
create table if not exists stock(
    id int(11),
    name varchar(11) default null,
    address varchar(20) default null,
    about varchar(255) default null,
    primary key (id)
); 

-- Cвязь магазинов со складом. 
create table if not exists market_stock (  
    market_id int(11),
    stock_id int(11),
    foreign key (market_id) references market(id), 
    foreign key  (stock_id) references stock(id)
    );
    

-- Tаблица зарегистрированных пользователей в приложении.
create table if not exists user (  
    id int(11) not null auto_increment,
    name varchar(15) default null,
    password varchar(15) default null,
    market_id int(11) default null,
    stock_id int(11) default null, 
    foreign key (market_id) references market(id),
    foreign key (stock_id) references stock(id),
    primary key (id));
    
-- Tаблица ролей определяющих права доступа в приложении. 
create table if not exists role (  
    id int(11) not null auto_increment,
    name varchar(15) default null,
    primary key (id));
    
    
-- Tаблица связка для пользователей и ролей.
create table if not exists user_role (  
    user_id int(11),
    role_id int(11),
    foreign key (user_id) references user(id), 
    foreign key  (role_id) references role(id)
    );
    
-- Tаблица категорий товаров.
 create table if not exists category(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    primary key (id)
 );
 
  -- связь категорий товаров с магазинами.
create table if not exists category_market(
    category_id int(11) default null,
    merket_id int(11) default null,
    foreign key (merket_id) references market (id),
    foreign key (category_id) references category (id)
 );
 
 
 
 -- Таблитца продуктов.
 create table if not exists product(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    primary key (id)
 );
 
 -- связь товаров с категориями.
create table if not exists cetegory_product(
    category_id int(11) default null,
    product_id int(11) default null,
    foreign key (product_id) references product (id),
    foreign key (category_id) references category (id)
 );
 
 
 
 -- Таблица связь магазинов с продуктами. 
 create table if not exists market_product(
    market_id int(11) default null,
    product_id int(11) default null,
    foreign key (product_id) references product (id),
    foreign key (market_id) references market (id)
 );
 
 
-- Таблица связь склада с продуктами. 
 create table if not exists stock_product(
    stock_id int(11) default null,
    product_id int(11) default null,
    foreign key (product_id) references product (id),
    foreign key (stock_id) references stock (id)
 );
 
  -- Таблица описания продуктов.
create table if not exists description(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    value varchar(20) default null,
    product_id int(11),
    primary key (id),
    foreign key (product_id) references product (id)
);

-- Заказы товаров.
create table if not exists in_order(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    contacts varchar(20) default null,
    address varchar(20) default null,
    market_id int(11) default null,
    primary key (id),
    foreign key (market_id) references  market (id)
);

-- связь товара с заказом.
create table if not exists order_product(
    order_id int(11) default null,
    product_id int(11) default null,
    foreign key (product_id) references product (id),
    foreign key (order_id) references in_order (id)
 );
 
 



