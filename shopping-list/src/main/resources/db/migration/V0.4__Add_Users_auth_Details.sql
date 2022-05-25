create table users_auth_details
(
    id        integer      not null
        primary key,
    active    boolean      not null,
    password  varchar(255),
    user_name varchar(255) not null
);




