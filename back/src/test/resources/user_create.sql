delete
from user_roles;
delete
from users;

insert into users(id, email, password, username) value
    (1, 'george@ggg.com', '$2a$10$pEzXB/.BZJDK6GxDYNyyLum6Wsei7Uhoti7CW2ppKvah2z.SkeIxm', 'george');

insert into user_roles(user_id, role_id)
values (1, 1),
       (1, 3);