insert into users (id, username) values (1724986849, 'Sergey') on duplicate key update username=username;

INSERT INTO roles(id, role) values (1, 'USER'), (2, 'ADMIN'), (3, 'SELLER') on duplicate key update role=role;

insert into user_role (user_id, role_id) values (1724986849, 2) on duplicate key update user_id=user_id and role_id=role_id;
