# SpringSecurityJWT
 SpringSecurity using JWT

create table USERS (
  USER_NAME         VARCHAR(36) not null,
  PASSWORD VARCHAR(128) not null,
  Email  VARCHAR(50) not null,
  ENABLED           BIT not null  
);

alter table USERS add constraint USERS_PK primary key (Email);
alter table USERS add constraint USERS_UK unique (USER_NAME);

create table ROLES
(
  ROLE_ID   BIGINT not null,
  ROLE_NAME VARCHAR(30) not null
) ;
--  
alter table ROLES add constraint ROLES_PK primary key (ROLE_ID);
alter table ROLES add constraint ROLES_UK unique (ROLE_NAME);

create table USER_ROLES
(
  ID           BIGINT not null,
  Email  VARCHAR(50) not null,
  ROLE_ID BIGINT not null
);
--  
alter table USER_ROLES add constraint USER_ROLE_PK primary key (ID);
alter table USER_ROLES add constraint USER_ROLE_UK unique (Email, ROLE_ID);

alter table USER_ROLES add constraint USER_ROLE_FK foreign key (Email) references USERS (Email);

alter table USER_ROLES add constraint USER_ROLE_FK2 foreign key (ROLE_ID) references ROLES (ROLE_ID);

alter table user_roles add constraint user_roles_fk foreign key (role_id) references roles (role_id);

alter table user_roles add constraint user_roles_email foreign key (email) references users (email);

insert into Users (user_NAME, PASSWORD, ENABLED,email)
values ('vijay', '$2a$10$vEWqqNydkVU645uDi6r4O.uqEPgPU2K0BRiiIpZ62lPMzcweWxeYy', 1, 'vijay@gmail.com');

insert into Users (user_NAME, PASSWORD, ENABLED,email)
values ('bvpr', '$2a$10$nu6muyG0hwGzkKJSa5bYrOSh.8zKXIcgDF413f3KvdonLyweVWYSm', 1,'bvpr@gmail.com');

insert into Users (user_NAME, PASSWORD, ENABLED,email)
values ('prakash', '$2a$10$nu6muyG0hwGzkKJSa5bYrOSh.8zKXIcgDF413f3KvdonLyweVWYSm', 1,'prakash@gmail.com');

insert into Users (user_NAME, PASSWORD, ENABLED,email)
values ('reddy', '$2a$10$nu6muyG0hwGzkKJSa5bYrOSh.8zKXIcgDF413f3KvdonLyweVWYSm', 0,'reddy@gmail.com');


insert into roles (role_ID, ROLE_NAME)
values (1, 'ADMIN');

insert into roles (role_ID, ROLE_NAME)
values (2, 'USER');

insert into roles (role_ID, ROLE_NAME)
values (3, 'VIEWER');

---

insert into user_roles (ID, EMAIL, ROLE_ID)
values (1, 'bvpr@gmail.com', 1);

insert into user_roles (ID, EMAIL, ROLE_ID)
values (4, 'bvpr@gmail.com', 2);

insert into user_roles (ID, EMAIL, ROLE_ID)
values (5, 'bvpr@gmail.com', 3);

insert into user_roles (ID, EMAIL, ROLE_ID)
values (2, 'vijay@gmail.com', 2);

insert into user_roles (ID, EMAIL, ROLE_ID)
values (6, 'vijay@gmail.com', 3);

insert into user_roles (ID, EMAIL, ROLE_ID)
values (3, 'prakash@gmail.com', 3);


insert into user_roles (ID, EMAIL, ROLE_ID)
values (7, 'reddy@gmail.com', 2);

insert into user_roles (ID, EMAIL, ROLE_ID)
values (8, 'reddy@gmail.com', 3);
