CREATE TABLE IF NOT EXISTS users ( id BIGINT auto_increment, username varchar (20), primary key (id) );

CREATE TABLE IF NOT EXISTS user_role(user_id BIGINT, role varchar(40), foreign key (user_id) references users(id) );