create table test.tr_lesson (
    id uuid default gen_random_uuid() constraint tr_lesson_pk primary key,
    name text not null
);

create table test.tr_test(
    id uuid default gen_random_uuid() constraint tr_test_pk primary key,
    name text not null,
    permit_all boolean,
    lesson_id uuid constraint tr_test_tr_lesson_fk
        references test.tr_lesson (id)
);

create table test.tr_question (
    id uuid default gen_random_uuid() constraint tr_question_pk primary key,
    name text not null,
    test_id uuid constraint tr_question_tr_test_fk
        references test.tr_test (id)
);

create table test.tr_answer (
    id uuid default gen_random_uuid() constraint tr_answer_pk primary key,
    name text not null,
    is_right boolean,
    question_id uuid constraint tr_answer_tr_question_fk
        references test.tr_question (id)
);

create table test.tr_user_test (
    user_id uuid constraint tr_user_fk references users.tr_user,
    test_id uuid constraint tr_test_fk references test.tr_test
);

comment on table test.tr_lesson is 'Уроки';
comment on column test.tr_lesson.id is 'Идентификатор урока';
comment on column test.tr_lesson.name is 'Название урока';

comment on table test.tr_test is 'Тесты';
comment on column test.tr_test.id is 'Идентификатор теста';
comment on column test.tr_test.name is 'Название теста';
comment on column test.tr_test.permit_all is 'Доступ к тесту для всех учащихся';
comment on column test.tr_test.lesson_id is 'Идентификатор урока';

comment on table test.tr_question is 'Вопросы';
comment on column test.tr_question.id is 'Идентификатор вопроса';
comment on column test.tr_question.name is 'Название вопроса';
comment on column test.tr_question.test_id is 'Название вопроса';

comment on table test.tr_answer is 'Ответы';
comment on column test.tr_answer.id is 'Идентификатор ответа';
comment on column test.tr_answer.name is 'Название ответа';
comment on column test.tr_answer.is_right is 'Правильный ли ответ';
comment on column test.tr_answer.question_id is 'Идентификатор вопроса';

comment on table test.tr_user_test is 'Тесты, выданные учащемуся';
comment on constraint tr_user_fk on test.tr_user_test is 'Пользователь';
comment on constraint tr_test_fk on test.tr_user_test is 'Тест';