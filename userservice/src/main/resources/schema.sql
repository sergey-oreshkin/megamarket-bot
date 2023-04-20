CREATE TABLE IF NOT EXISTS users(id bigint primary key, username varchar(50));

CREATE TABLE IF NOT EXISTS roles(id bigint primary key, role varchar (20) );

CREATE TABLE IF NOT EXISTS user_role(user_id bigint not null, role_id bigint not null, primary key(user_id, role_id), foreign key (user_id) references users(id), foreign key (role_id) references roles(id) );

CREATE TABLE IF NOT EXISTS requests( id bigint primary key, user_id bigint not null, role_id bigint not null, foreign key (user_id) references users(id), foreign key (role_id) references roles(id) );
