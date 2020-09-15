create table if not exists Ingredient (
    id varchar(4) not null primary key,
    name varchar(35) not null,
    type varchar(10) not null
);

create table if not exists Taco (
    id identity not null,
    name varchar(50) not null,
    created_at timestamp not null
);

create table if not exists Taco_Ingredients (
    taco identity not null,
    ingredient varchar(4) not null
);

create table if not exists Taco_Order (
    id identity,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(10) not null,
    zip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Taco_Order_Tacos (
    tacoOrder identity not null,
    taco bigint not null
);
