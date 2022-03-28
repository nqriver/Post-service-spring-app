--liquibase formatted sql
--changeset nqriver:4

create table users(
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username varchar ( 50 ) not null UNIQUE,
                      password varchar ( 100 ) not null,
                      enabled boolean not null
);
--changeset nqriver:5
create table authorities (
                             username varchar ( 50 ) not null,
                             authority varchar ( 50 ) not null,
                             constraint fk_authorities_users foreign key (username) references
                                 users(username) ,
                             UNIQUE KEY username_authority (username, authority)
);
--changeset nqriver:6
insert into users (id, username, password, enabled)
values (1, 'test', '{bcrypt}$2a$10$kVMrSfQuAPDAK9..QhXSh.kieTrJzNt9Ak/Hbawu69ggCRKUbVoTK', true);
insert into authorities (username, authority) values ('test','USER');