-- drop table if exists user_team;
drop table if exists user_permission;
-- drop table if exists team_permission;
drop table if exists permission;
-- drop table if exists team;
drop table if exists user;

create table if not exists user (
                                       id bigint not null auto_increment,
                                       name varchar(100) not null,
                                       username varchar(50) not null,
                                       password varchar(100) not null,
                                       active boolean default false,
                                       primary key (id),
                                       unique key un_user_username (username)
);

create table if not exists permission (
                                         id bigint not null auto_increment,
                                         name varchar(100) not null,
                                         primary key (id)
);

create table if not exists user_permission (
                                                 user_id bigint not null,
                                                 permission_id varchar(50) not null,
                                                 primary key (user_id, permission_id),
                                                 constraint fk_userpermission_user foreign key (user_id) references user(id),
                                                 constraint fk_userpermission_permission foreign key (permission_id) references permission(id)
);

-- create table if not exists team (
--                                      id bigint not null auto_increment,
--                                      name varchar(100) not null,
--                                      primary key (id),
--                                      unique key un_team_name (name)
-- );
--
-- create table if not exists user_team (
--                                              user_id bigint not null,
--                                              team_id bigint not null,
--                                              primary key(user_id, team_id),
--                                              constraint fk_userteam_user foreign key (user_id) references user(id),
--                                              constraint fk_userteam_team foreign key (team_id) references team(id)
-- );
--
-- create table if not exists team_permission (
--                                                team_id bigint not null,
--                                                permission_id varchar(50) not null,
--                                                primary key (team_id, permission_id),
--                                                constraint fk_teampermission_team foreign key (team_id) references team(id),
--                                                constraint fk_teampermission_permission foreign key (permission_id) references permission(id)
-- );