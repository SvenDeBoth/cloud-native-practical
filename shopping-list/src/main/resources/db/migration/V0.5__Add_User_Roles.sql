create table user_roles
(
    user_id integer not null,
        constraint FK_USER
        FOREIGN KEY(user_id)
        REFERENCES users_auth_details(id),
    roles   varchar(255)
);


