create table users.tr_user (
    id uuid default gen_random_uuid() constraint tr_user_pk primary key,
    login text not null,
    password text not null
);

create table users.tr_role (
    id uuid default gen_random_uuid() constraint tr_role_pk primary key,
    name text not null,
    code text not null
);

create table users.tr_user_role (
    user_id uuid constraint tr_user_fk references users.tr_user,
    role_id uuid constraint tr_role_fk references users.tr_role
);

comment on table users.tr_user is 'Пользователи';
comment on column users.tr_user.id is 'Идентификатор пользователя';
comment on column users.tr_user.login is 'Логин пользователя';
comment on column users.tr_user.password is 'Пароль пользователя в хэшированном виде';

comment on table users.tr_role is 'Роли';
comment on column users.tr_role.id is 'Идентификатор роли';
comment on column users.tr_role.name is 'Название роли';
comment on column users.tr_role.code is 'Код роли';

comment on table users.tr_user_role is 'Роли, выданные пользователю';
comment on constraint tr_user_fk on users.tr_user_role is 'Пользователь';
comment on constraint tr_role_fk on users.tr_user_role is 'Роль';

insert into users.tr_role (id, name, code) values
    ('1d6ce7a2-22b6-4f4c-9e75-6a489ab09691', 'Администратор', 'ADMINISTRATOR'),
    ('1d6ce7a2-22b6-4f4c-9e75-6a489ab09692', 'Ученик', 'STUDENT');

insert into users.tr_user (id, login, password) values
    ('216c1bd3-a698-4b98-a91c-256090a695ca', 'admin', '{bcrypt}$2a$10$/4Nn0ov5DAx5PeWfs2eF6u2xdj9QR/vgAy.wc.eu0ha4YT.gNNpoO');

insert into users.tr_user_role (user_id, role_id) values
    ('216c1bd3-a698-4b98-a91c-256090a695ca', '1d6ce7a2-22b6-4f4c-9e75-6a489ab09691');
