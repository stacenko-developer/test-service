create table users.tr_user_lesson (
    user_id uuid constraint tr_user_fk references users.tr_user,
    lesson_id uuid constraint tr_lesson_fk references test.tr_lesson,
    primary key (user_id, lesson_id)
);

comment on table users.tr_user_lesson is 'Уроки, выданные учащемуся';
comment on column users.tr_user_lesson.user_id is 'Идентификатор учащегося';
comment on column users.tr_user_lesson.lesson_id is 'Идентификатор урока';