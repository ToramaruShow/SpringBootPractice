create database if not exists webapp;
use webapp;

create table if not exists contact(
    id int auto_increment primary key,
    order_number char(11) not null,
    name varchar(20) not null,
    email char(30) not null,
    title varchar(30) not null,
    contents text not null,
    recv_date datetime not null
);