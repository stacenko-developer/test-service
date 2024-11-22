alter table users.tr_user add column first_name text;
alter table users.tr_user add column last_name text;
alter table users.tr_user add column patronymic text;
alter table users.tr_user add column email text;

comment on column users.tr_user.first_name is 'Имя пользователя';
comment on column users.tr_user.last_name is 'Фамилия пользователя';
comment on column users.tr_user.patronymic is 'Отчество пользователя';
comment on column users.tr_user.email is 'Почта пользователя';