drop table if exists therapies;
drop table if exists user_login_infos;
drop table if exists smoking_infos;
drop table if exists app_users;

create table app_users (
    id int primary key identity,
    average_sleeping_time int,
    date_of_birth date,
    gender varchar(15)
);

create table smoking_infos (
    id int primary key identity,
    age_started_smoking int,
    cigarettes_per_day int,
    therapy_mode varchar(20),
    app_user_id int foreign key references app_users(id)
);

create table therapies(
    id int primary key identity,
    period_between_cigarettes float,
    planned_end_date date,
    starting_date date,
    user_id int foreign key references app_users(id)
);

create table user_login_infos(
    id int primary key identity,
    login varchar(255),
    password varchar(255),
    user_id int foreign key references app_users(id)
)