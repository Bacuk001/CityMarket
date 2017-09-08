-- создание базы данных
create database if not exists city_market;

use city_market;

-- таблица  магазинов
create table if not exists market(
    id int(11),
    name varchar(11) default null,
    address varchar(20) default null,
    about varchar(255) default null,
    primary key (id)
); 

-- таблица  складов
create table if not exists stock(
    id int(11),
    name varchar(11) default null,
    address varchar(20) default null,
    about varchar(255) default null,
    primary key (id)
); 

-- связь магазинов со складом 
create table if not exists market_stock (  
    market_id int(11),
    stock_id int(11),
    foreign key (market_id) references market(id), 
    foreign key  (stock_id) references stock(id)
    );
    

-- таблица зарегистрированных пользователей в приложении
create table if not exists user (  
    id int(11) not null auto_increment,
    name varchar(15) default null,
    market_id int(11) default null,
    stock_id int(11) default null, 
    foreign key (market_id) references market(id),
    foreign key (stock_id) references stock(id),
    primary key (id));
    
-- таблица ролей определяющих права доступа в приложении    
create table if not exists role (  
    id int(11) not null auto_increment,
    name varchar(15) default null,
    primary key (id));
    
    
-- таблица связка для пользователей и ролей
create table if not exists user_role (  
    user_id int(11),
    role_id int(11),
    foreign key (user_id) references user(id), 
    foreign key  (role_id) references role(id)
    );
    
-- таблица категорий товаров
 create table if not exists categories(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    primary key (id)
 );


 
 -- таблитца продуктов
 create table if not exists product(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    categories_id int(11),
    primary key (id),
    foreign key (categories_id) references categories (id)
 );
 
-- таблица связь склада с продуктами 
 create table if not exists stock_product(
    stock_id int(11) default null,
    product_id int(11) default null,
    foreign key (product_id) references product (id),
    foreign key (stock_id) references stock (id)
 );
 
  -- таблица описания продуктов
create table if not exists description(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    value varchar(20) default null,
    product_id int(11),
    primary key (id),
    foreign key (product_id) references product (id)
);

 
 



