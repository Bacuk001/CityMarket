/* Создание базы данных.*/

create database if not exists city_market CHARACTER SET utf8;

use city_market;

-- Tаблица  магазинов.

create table if not exists Market(
    id int(11) not null auto_increment,
    name varchar(11) default null,
    addres varchar(20) default null,
    about varchar(255) default null,
    primary key (id)
); 

insert into Market (about, addres, name) values
('Все для вашего любимца', 'г.Гродно ул.Пролетарская 7 кв 15', 'домашний'),
('Хозяйственные товары для вашего дома', 'г.Гродно  Дзержинского 6', 'все дома');


-- Tаблица  складов.
create table if not exists Stock(
    id int(11) not null auto_increment,
    name varchar(11) default null,
    address varchar(20) default null,
    about varchar(255) default null,
    primary key (id)
); 
insert into Stock (about, address, name) values
('Склад с кормом для дпитомцев', 'Ул. Стоковая 16', 'Нахимовский склад'),('многое', 'Москва', 'Черкизово');

-- Таблица связь магазинов со складом. 
create table if not exists market_stock (  
    markets int(11),
    stocks int(11),
    foreign key (markets) references Market(id), 
    foreign key  (stocks) references Stock(id)
    );
insert into market_stock (stocks, markets) values
(1, 1),(2, 2); 

-- Tаблица зарегистрированных пользователей в приложении.
create table if not exists User (  
    id int(11) not null auto_increment,
    name varchar(15) default null,
    password varchar(255) default null,
    market_id int(11) default null,
    stock_id int(11) default null, 
    foreign key (market_id) references Market(id),
    foreign key (stock_id) references Stock(id),
    primary key (id));

insert into User (name, password, market_id, stock_id) values
('vasik', '123', NULL, NULL),
('Dima', '123', 1, NULL),
('Витя', '123', NULL, 1),
('Витя Пушкин', '123', NULL, 2),
('Dima home', '123', 2, NULL);

    
-- Tаблица ролей определяющих права доступа в приложении. 
create table if not exists Role (  
    id int(11) not null auto_increment,
    name varchar(15) default null,
    primary key (id));

insert into Role (name) values
('ROLE_ADMIN'),('ROLE_MANAGER_SHOP'),('ROLE_MANAGER_STOCK');
    
-- Tаблица связка для пользователей и ролей.
create table if not exists roles_users (  
    users int(11),
    roles int(11),
    foreign key (users) references User(id), 
    foreign key  (roles) references Role(id)
    );
  
insert into roles_users (users, roles) values 
(1, 1),(2, 2),(3, 3),(4, 3),(5, 2);
  
-- Tаблица категорий товаров.
 create table if not exists Category(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    urlPhoto varchar(70) default null,
    primary key (id)
 );

insert into Category (name, urlPhoto) values
('Мебель', 'http://127.0.0.1:8090/CityMarket/image/group1.jpg'),
('Чайники', 'http://127.0.0.1:8090/CityMarket/image/group2.png'),
('Стиральные машины', 'http://127.0.0.1:8090/CityMarket/image/group3.png'),
('Все для кухни', 'http://127.0.0.1:8090/CityMarket/image/group4.png'),
('Светильники', 'http://127.0.0.1:8090/CityMarket/image/group5.png'),
('Утюги', 'http://127.0.0.1:8090/CityMarket/image/group6.png'),
('Телевизоры', 'http://127.0.0.1:8090/CityMarket/image/group7.png'),
('Бритвы', 'http://127.0.0.1:8090/CityMarket/image/group8.png'),
('Посуда', 'http://127.0.0.1:8090/CityMarket/image/group9.jpg'),
('Пылесосы', 'http://127.0.0.1:8090/CityMarket/image/group10.png'),
('Микроволновые печи', 'http://127.0.0.1:8090/CityMarket/image/group11.png'),
('Планшеты', 'http://127.0.0.1:8090/CityMarket/image/group12.png');
 
 -- Таблитца продуктов.
 create table if not exists Product(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    urlPhoto varchar(70) default null,
    category_id int(11) default null,
    foreign key (category_id) references Category (id),
    primary key (id)
 );

insert into Product (name, category_id, urlPhoto) values
('Кресло мешок "Домовик"', 1, 'http://127.0.0.1:8090/CityMarket/image/3.jpg'),
('Кресло мешок "Уют"', 1, 'http://127.0.0.1:8090/CityMarket/image/4.jpg'),
('Кресло мешок "Пижамник"', 1, 'http://127.0.0.1:8090/CityMarket/image/5.jpg'),
('Кресло мешок "Чиканчик"', 1, 'http://127.0.0.1:8090/CityMarket/image/6.jpg'),
('Кресло мешок "Пушечка"', 1, 'http://127.0.0.1:8090/CityMarket/image/2.jpg'),
('Кресло мешок "Сонник"', 1, 'http://127.0.0.1:8090/CityMarket/image/8.jpg'),
('Кресло мешок "Сонник-2"', 1, 'http://127.0.0.1:8090/CityMarket/image/9.jpg'),
('Кресло мешок "Уют-3"', 1, 'http://127.0.0.1:8090/CityMarket/image/10.jpg'),
('Кресло мешок "Керамик"', 1, 'http://127.0.0.1:8090/CityMarket/image/1.jpg'),
('Кресло мешок "Изюминка"', 1, 'http://127.0.0.1:8090/CityMarket/image/2.jpg'),
('Кресло мешок "Сонник-3"', 1, 'http://127.0.0.1:8090/CityMarket/image/3.jpg'),
('Кресло мешок "Креатив"', 1, 'http://127.0.0.1:8090/CityMarket/image/6.jpg'),
('Кресло мешок "Пристол"', 1, 'http://127.0.0.1:8090/CityMarket/image/8.jpg'),
('Чайник керамический "Ромашка"', 2, 'http://127.0.0.1:8090/CityMarket/image/20.jpg'),
('Чайник стеклянный "Заварка"', 2, 'http://127.0.0.1:8090/CityMarket/image/21.jpg'),
('Чайник нержавейка "Ржавчик"', 2, 'http://127.0.0.1:8090/CityMarket/image/22.jpg'),
('Чайник электрический  АК-22', 2, 'http://192.168.0.100:8090/CityMarket/image/23.jpg'),
('Чайник садик', 2, 'http://192.168.0.100:8090/CityMarket/image/24.jpg'),
('Чайник керамический "Глинка"', 2, 'http://127.0.0.1:8090/CityMarket/image/25.jpg'),
('Чайник керамический "Теплыш"', 2, 'http://127.0.0.1:8090/CityMarket/image/26.jpg'),
('Чайник керамический "HOLT FR -22"', 2, 'http://127.0.0.1:8090/CityMarket/image/27.jpg'),
('Чайник керамический "HOLT FR -16"', 2, 'http://127.0.0.1:8090/CityMarket/image/28.jpg'),
('Чайник керамический "VES FR -16"', 2, 'http://127.0.0.1:8090/CityMarket/image/29.jpg'),
('Чайник нержавейка ЕR -16', 2, 'http://127.0.0.1:8090/CityMarket/image/30.jpg'),
('Чайник нержавейка ЕR -25', 2, 'http://127.0.0.1:8090/CityMarket/image/31.jpg'),
('Чайник нержавейка ЕR -36', 2, 'http://127.0.0.1:8090/CityMarket/image/32.jpg'),
('Стиральная машина автомат INDESIT FRT 500', 3, 'http://127.0.0.1:8090/CityMarket/image/51.jpg'),
('Стиральная машина автомат Indesit FRE800C200', 3, 'http://127.0.0.1:8090/CityMarket/image/50.jpg'),
('Стиральная машина автомат Indesit F1200C200', 3, 'http://127.0.0.1:8090/CityMarket/image/52.jpg'),
('Стиральная машина автомат Indesit F100', 3, 'http://127.0.0.1:8090/CityMarket/image/40.jpg'),
('Стиральная машина автомат LG F100', 3, 'http://127.0.0.1:8090/CityMarket/image/41.jpg'),
('Стиральная машина автомат LG F100N500', 3, 'http://127.0.0.1:8090/CityMarket/image/42.jpg'),
('Стиральная машина автомат LG F10HG400', 3, 'http://127.0.0.1:8090/CityMarket/image/43.jpg'),
('Стиральная машина автомат LG F12HG400', 3, 'http://127.0.0.1:8090/CityMarket/image/44.jpg'),
('Стиральная машина автомат LG F\\HG400', 3, 'http://127.0.0.1:8090/CityMarket/image/45.jpg'),
('Стиральная машина автомат SAMSUNG F\\HG400', 3, 'http://127.0.0.1:8090/CityMarket/image/46.jpg'),
('Стиральная машина автомат SAMSUNG F\\HG', 3, 'http://127.0.0.1:8090/CityMarket/image/47.jpg'),
('Стиральная машина SAMSUNG WW7MJ4210HSDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/365867059.indesit-ewsd-61252.jpg'),
('Стиральная машина автомат SAMSUNG HG100', 3, 'http://127.0.0.1:8090/CityMarket/image/49.jpg'),
('Стиральная машина SAMSUNG WW65K52E69SDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/new_71220953_l_1488869313.jpeg'),
('Стиральная машина SAMSUNG WW80K52E61WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/new_71177024_l_1464071125.jpeg'),
('Стиральная машина SAMSUNG WW7MJ42102WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/mfX229fV3Q9x72cjDID66ng.jpg'),
(' Стиральная машина SAMSUNG WW65K42E09WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/236834382.jpg'),
(' Стиральная машина SAMSUNG WW6MJ42602WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/235800314.jpg'),
(' Стиральная машина SAMSUNG WW60J30G03WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/41O2Roe9CCL.jpg'),
('Стиральная машина SAMSUNG WW65J42E02WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/41O2Roe9CCL (1).jpg'),
(' Стиральная машина SAMSUNG WW70J52E02WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/wm-11.png'),
('Стиральная машина SAMSUNG WW65K52E69SDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/new_71220953_l_1488869313.jpeg'),
('Стиральная машина SAMSUNG WW80K52E61WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/new_71177024_l_1464071125.jpeg'),
('Стиральная машина SAMSUNG WW7MJ42102WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/mfX229fV3Q9x72cjDID66ng.jpg'),
('Стиральная машина SAMSUNG WW7MJ4210HSDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/img_br208678_big_1-600x600-47.jpg'),
(' Стиральная машина SAMSUNG WW65K42E09WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/365867059.indesit-ewsd-61252.jpg'),
('Стиральная машина SAMSUNG WW6MJ42602WDLP', 3, 'http://127.0.0.1:8090/CityMarket/image/236834382.jpg'),
(' Холодильник LG GC-Q247CABV', 4, 'http://127.0.0.1:8090/CityMarket/image/1_73561.png'),
('Холодильник LG GC-Q247CABV', 4, 'http://127.0.0.1:8090/CityMarket/image/34_600x_1498560182.jpg'),
('Холодильник SAMSUNG RB37K63412A/WT', 4, 'http://127.0.0.1:8090/CityMarket/image/194234980.lg-gb3133pvgw.jpg'),
('Холодильник LG GA-M589ZMQZ', 4, 'http://127.0.0.1:8090/CityMarket/image/30106232323223.jpg'),
('Холодильник LG GA-E499ZVQZ', 4, 'http://127.0.0.1:8090/CityMarket/image/GA-B489TGBM_medium_01.jpg'),
('Холодильник LG GA-M599ZMQZ', 4, 'http://127.0.0.1:8090/CityMarket/image/GA-B499TGBM_350.jpg'),
('Холодильник LG GA-E499ZAQZ', 4, 'http://127.0.0.1:8090/CityMarket/image/GA-E429SMRZ_350.jpg'),
('Холодильник LG GA-B499TGBM', 4, 'http://127.0.0.1:8090/CityMarket/image/holod-lg-1.jpg'),
('Холодильник LG GA-B499TGRF', 4, 'http://127.0.0.1:8090/CityMarket/image/LG_REGRIGERATOR.jpg'),
('Холодильник LG GA-M599ZEQZ', 4, 'http://127.0.0.1:8090/CityMarket/image/medium_GA-B439TGMR_002_logo-1.jpg'),
('Холодильник LG GC-B247SEUV', 4, 'http://127.0.0.1:8090/CityMarket/image/p366344-0mw.jpg'),
('Холодильник LG GC-B247SMUV', 4, 'http://127.0.0.1:8090/CityMarket/image/Refrigerator-Thumbnail-Image-Desktop-350.jpg'),
(' Светильник-ночник ULTRA LIGHT CZ-3(D)', 5, 'http://127.0.0.1:8090/CityMarket/image/95676_200.png'),
(' Светильник-ночник 4HOME ', 5, 'http://127.0.0.1:8090/CityMarket/image/1467026343_blue.jpg'),
(' Светильник-ночник 4HOME PTDJ07I', 5, 'http://127.0.0.1:8090/CityMarket/image/big_33-1188_1_wh_b.jpg'),
(' Светильник-ночник 4HOME PTDJ054', 5, 'http://127.0.0.1:8090/CityMarket/image/dl-s28t-c.jpg'),
(' Ночник TDM Весна с датчиком света, светодиодный', 5, 'http://127.0.0.1:8090/CityMarket/image/HMPCalt lilith pl 70.jpg'),
(' Светильник на солнечных батареях TDM СС', 5, 'http://127.0.0.1:8090/CityMarket/image/LED-Ceiling-Light-Modern-Minimalist-Living-Room-light-bedroom-balcony-aisle-lighting-fixture-400mm-20W-Gold.jpg'),
(' NL-004 Ночник с выкл.CAMELION', 5, 'http://127.0.0.1:8090/CityMarket/image/night-598361_960_720.jpg'),
(' LLED-01-12W-4000-W Светильник линейный LED ЭРА', 5, 'http://127.0.0.1:8090/CityMarket/image/Of_light_and_wires_(298912154).jpg'),
(' NL-003 кошка Ночник с выкл.CAMELION', 5, 'http://127.0.0.1:8090/CityMarket/image/runde-kristall-deckenlampe-ottilia-nickel-maytoni_0.jpg'),
(' NN-619-LS-W белый ночник ЭРА', 5, 'http://127.0.0.1:8090/CityMarket/image/SW_MB9002B-3.jpg'),
(' Ночник TDM Хамелеон D75 с выключателем, белый ', 5, 'http://127.0.0.1:8090/CityMarket/image/wm.jpg'),
('Телевизор LED LG 55UH950V', 6, 'http://127.0.0.1:8090/CityMarket/image/58edea3b22bebd8e8cfda5918d6d1a0a54fc6b6f46f28fd0bafbac0b4821509b.jpg');
  
 -- Таблица связь магазинов с продуктами. 
 create table if not exists market_product(
    markets int(11) default null,
    products int(11) default null,
    foreign key (products) references Product (id),
    foreign key (markets) references Market (id)
 );
 
 
-- Таблица связь склада с продуктами. 
 create table if not exists product_stock(
    stocks int(11) default null,
    product int(11) default null,
    foreign key (product) references Product (id),
    foreign key (stocks) references Stock (id)
 );
insert into product_stock (stocks, product) values
(1, 2),(1, 14),(1, 26),(1, 38),(2, 50),(2, 62),(2, 74),(2, 8),
(1, 3),(1, 15),(1, 27),(1, 39),(2, 51),(2, 63),(2, 75),(2, 9),
(1, 4),(1, 16),(1, 28),(1, 40),(2, 52),(2, 64),(2, 76),(2, 10),
(1, 5),(1, 17),(1, 29),(2, 41),(2, 53),(2, 65),(2, 77),(2, 11),
(1, 6),(1, 18),(1, 30),(2, 42),(2, 54),(2, 66),(2, 19),(2, 12),
(1, 7),(1, 19),(1, 31),(2, 43),(2, 55),(2, 67),(2, 1),(2, 13),
(1, 8),(1, 20),(1, 32),(2, 44),(2, 56),(2, 68),(2, 2),(2, 14),
(1, 9),(1, 21),(1, 33),(2, 45),(2, 57),(2, 69),(2, 3),(2, 15),
(1, 10),(1, 22),(1, 34),(2, 46),(2, 58),(2, 70),(2, 4),(2, 16),
(1, 11),(1, 23),(1, 35),(2, 47),(2, 59),(2, 71),(2, 5),(2, 17),
(1, 12),(1, 24),(1, 36),(2, 48),(2, 60),(2, 72),(2, 6),(2, 18),
(1, 13),(1, 25),(1, 37),(2, 49),(2, 61),(2, 73),(2, 7);
 
  -- Таблица описания продуктов.
create table if not exists Description(
    id int(11) not null auto_increment,
    name varchar(20) default null,
    value varchar(20) default null,
    product_id int(11),
    primary key (id),
    foreign key (product_id) references Product (id)
);


insert into Description ( name, value, product_id) values
('тип корма', 'гранулы', 1),
('вес питомца', 'от 1 до 5 кг', 1),
('витямины', 'а,в,с', 1),
('Объем', '3л', 14),
('Цвет', 'нержавеющая сталь', 14),
('Рост', '1м75см', 13),
('Вес ', '35 кг', 13),
('Возраст', '3года', 13),
('гарантия', '3 года', 13),
('Срок службы', '7 лет', 13),
('Наполнитель', 'паралон', 31),
('Высота', '0,5м', 31),
('Ширина', '0,3м', 31),
('материал', 'эко-кожа', 31),
('нитки', 'шелк', 31),
('Наполнитель', 'пена', 33),
('Материал', 'эко-кожа', 33),
('гарантия', '3 года', 33),
('срок службы', '5 лет', 33),
('максимальная нагрузка', '300 кг', 33),
('объем', '1л', 61),
('объем', '3', 47);

-- Заказы товаров.
create table if not exists in_order(
    id int(11) not null auto_increment,
    contacts varchar(20) default null,
    address varchar(20) default null,
    nameUser varchar(20) default null,
    status varchar(10) default null,
    market_id int(11) default null,
    primary key (id),
    foreign key (market_id) references  Market (id)
);

insert into in_order (address, contacts, nameUser, status, market_id)  values 
('г.Гродно п-т Клецкова д.96', '+37592893523', 'Дмитрий Сергеевич', 'DENIED', 1),
('Гродно Новая 37-1', '458635', 'Виктор Сергеевич', 'отказанно', 1),
('Минск Смоленский проспект 7-15', '568975', 'Дмитрий Стапанович', 'Ожидание', 1);

-- связь товара с заказом.
create table if not exists order_product(
    orders int(11) default null,
    product int(11) default null,
    foreign key (product) references Product (id),
    foreign key (orders) references in_order (id)
 );

insert into order_product (orders, product) values
(1, 33),(2, 33),(3, 31);
 
-- цены товаров.
create table if not exists Price(
    id int(11) not null auto_increment,
    inReserv int(11) default null,
    inStock int(11) default null,
    price int(11) default null,
    product_id int(11) default null,
    stock_id int(11) default null,
    primary key (id),
    foreign key (product_id) references  Product (id),
    foreign key (stock_id) references  Stock (id)
);
insert into Price (inReserv, inStock, price, product_id, stock_id) values
(0, 324, 15, 1, 1),(0, 124, 25, 2, 1),
(0, 7, 15, 2, 1),(0, 4, 4, 15, 2),
(0, 2, 15, 3, 1),(0, 12, 12, 12, 1),
(1, 0, 10, 4, 2),(0, 20, 20, 13, 1),
(0, 10, 12, 5, 2),(0, 25, 15, 21, 1),
(0, 10, 13, 33, 1),(0, 13, 44, 28, 1),
(0, 23, 24, 30, 1),(0, 3, 16, 26, 1),
(0, 12, 76, 24, 1),(0, 12, 45, 22, 1),
(0, 12, 45, 25, 1),(0, 10, 2, 29, 1),
(1, 18, 6, 31, 1),(0, 0, 14, 32, 2),
(0, 2, 14, 32, 2),(0, 2, 34, 29, 2),
(0, 34, 4, 38, 2),(0, 15, 5, 39, 2),
(0, 4, 20, 46, 2),(0, 12, 1, 61, 1),
(0, 15, 5, 47, 1),(0, 10, 500, 75, 2),
(0, 10, 300, 2, 2),(0, 40, 800, 71, 2),
(0, 15, 300, 3, 2),(0, 5, 200, 33, 2),
(0, 5, 500, 65, 2),(0, 4, 400, 32, 2),
(0, 3, 200, 62, 2),(0, 4, 200, 31, 2),
(0, 4, 525, 64, 2),(0, 4, 454, 30, 2),
(0, 4, 554, 70, 2),(0, 5, 577, 29, 2),
(0, 4, 552, 74, 2),(0, 4, 545, 28, 2),
(0, 1, 582, 6, 2),(0, 1, 987, 27, 2),
(0, 4, 142, 7, 2),(0, 5, 653, 26, 2),
(0, 4, 453, 8, 2),(0, 2, 453, 25, 2),
(0, 4, 324, 9, 2),(0, 4, 345, 24, 2),
(0, 4, 534, 10, 2),(0, 4, 534, 23, 2),
(0, 4, 453, 11, 2),(0, 4, 453, 22, 2),
(0, 1, 21, 12, 2),(0, 1, 32, 21, 2),
(0, 1, 12, 13, 2),(0, 5, 12, 20, 2),
(0, 4, 45, 14, 2),(0, 4, 42, 19, 2),
(0, 4, 45, 15, 2),(0, 4, 47, 18, 2),
(0, 4, 74, 16, 2),(0, 4, 58, 17, 2);
 



