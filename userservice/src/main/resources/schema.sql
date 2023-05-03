CREATE TABLE IF NOT EXISTS users(id bigint primary key, username varchar(50));

CREATE TABLE IF NOT EXISTS roles(id bigint primary key, role varchar (20) );

INSERT INTO roles(id, role) values (1, 'USER'), (2, 'ADMIN'), (3, 'SELLER');

CREATE TABLE IF NOT EXISTS user_role(user_id bigint not null, role_id bigint not null, primary key(user_id, role_id), foreign key (user_id) references users(id), foreign key (role_id) references roles(id) );

CREATE TABLE IF NOT EXISTS requests( id bigint primary key auto_increment, user_id bigint not null, role_id bigint not null, foreign key (user_id) references users(id), foreign key (role_id) references roles(id) );

insert into users (id, username) values (1, 'purple');

insert into roles (id, role) values (1, 'ADMIN');

insert into user_role (user_id, role_id) values (1,1);
