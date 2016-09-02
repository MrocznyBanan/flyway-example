create table users (
    id bigint not null,
    active boolean not null,
    created date not null,
    email varchar(255) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    login varchar(255) not null,
    password varchar(255) not null,    
    primary key (id)
);