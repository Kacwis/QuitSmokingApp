drop table if exists therapies;
drop table if exists user_login_infos;
drop table if exists smoking_infos;
drop table if exists app_users;

create table app_users (
                           id integer not null auto_increment,
                           average_sleeping_time integer,
                           date_of_birth date,
                           gender varchar(15)
);

create table smoking_infos (
                               id integer not null auto_increment,
                               age_started_smoking integer,
                               cigarettes_per_day integer,
                               therapy_mode varchar(20),
                               app_user_id int,
                               PRIMARY KEY (id),
                               FOREIGN KEY (app_user_id) references app_users(id)
);

create table therapies(
                          id integer not null auto_increment,
                          period_between_cigarettes float,
                          planned_end_date date,
                          starting_date date,
                          user_id integer,
                          primary key (id),
                          foreign key (user_id) references app_users(id)
);

create table user_login_infos(
                                 id integer not null auto_increment,
                                 login varchar(255),
                                 password varchar(255),
                                 user_id integer,
                                 primary key (id),
                                 foreign key (user_id) references app_users(id)
)