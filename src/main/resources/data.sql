insert into privileges(priv_id, priv_name, details) values
(100, 'USER_CREATE',''),
(101, 'USER_EDIT',''),
(102, 'USER_DELETE',''),
(103, 'USER_VIEW',''),
(104, 'USER_VIEW_ALL',''),
(105, 'USER_SEARCH',''),
(200, 'IMAGE_CREATE',''),
(201, 'IMAGE_EDIT',''),
(202, 'IMAGE_DELETE',''),
(203, 'IMAGE_VIEW',''),
(204, 'IMAGE_VIEW_ALL',''),
(205, 'IMAGE_SEARCH','');

insert into ROLES(ROLE_ID, ROLE_NAME, DETAILS)
values (1,'SUPER_USER',''),
(2,'ADMIN_USER',''),
(3,'BASIC_USER','');

--roles and privileges mappings for SUPER_USER, DBA_USER
insert into roles_privileges_map(role_id, priv_id) values
(1,100),
(1,101),
(1,102),
(1,103),
(1,104),
(1,105),
(1,200),
(1,201),
(1,202),
(1,203),
(1,204),
(1,205),
(2,100),
(2,101),
(2,102),
(2,103),
(2,104),
(2,105),
(2,200),
(2,201),
(2,202),
(2,203),
(2,204),
(2,205),
(3,100),
(3,103),
(3,104),
(3,105),
(3,200),
(3,203),
(3,204),
(3,205);

insert into users(user_id, username, password, first_Name, middle_name, last_name, email, is_act_expired, is_act_locked, is_cred_expired, is_enabled) values
(1000,'super', '$2a$10$8L1xorS/.CV.XgKa1OhccuLbE763ZRbg1sDTZqTbmwr2.VW9FLWiG', 'Firstname','Mid','Lastname','test@test.com',false, false, false, true),
(1001,'admin', '$2a$10$8L1xorS/.CV.XgKa1OhccuLbE763ZRbg1sDTZqTbmwr2.VW9FLWiG', 'Firstname','Mid','Lastname','test@test.com',false, false, false, true),
(1002,'vik', '$2a$10$8L1xorS/.CV.XgKa1OhccuLbE763ZRbg1sDTZqTbmwr2.VW9FLWiG', 'Firstname','Mid','Lastname','test@test.com',false, false, false, true);

insert into users_roles_map(user_id, role_id) values
(1000,1),
(1001,2),
(1002,3);

update users set password='$2a$10$ds1PwT46I6nk45m./ifSpuollbgkMAW.6Bl.gNF.gkxrPak0XDXXa' -- this password is vik