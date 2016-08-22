use myspringbbssampledb;

-- authority
-- 执行前修改表和字段编码UTF-8
insert into acct_authority values (1,'浏览用户');
insert into acct_authority values (2,'修改用户');
insert into acct_authority values (3,'浏览角色');
insert into acct_authority values (4,'修改角色');


insert into acct_authority values (5,'浏览论坛');
insert into acct_authority values (6,'发布和修改和回复[bbshome]');
insert into acct_authority values (7,'发布和修改和回复[mybbs]');
insert into acct_authority values (8,'删除和置顶和加精华');


-- 浏览bbs 操作(发帖 回复 修改自己发帖)bbs 管理bbs(删帖，置顶，精华)

select * from acct_authority order by id;

-- TRUNCATE table acct_authority;

/*
delete from acct_authority where id=1;
delete from acct_authority where id=2;
delete from acct_authority where id=3;
delete from acct_authority where id=4;
*/

-- role
insert into acct_role values (1,'管理员');
insert into acct_role values (2,'用户');

select * from acct_role order by id;


-- delete from acct_role where id=1;
-- delete from acct_role where id=2;

-- role-authority
insert into acct_role_authority values (1,1);
insert into acct_role_authority values (1,2);
insert into acct_role_authority values (1,3);
insert into acct_role_authority values (1,4);
insert into acct_role_authority values (2,1);
insert into acct_role_authority values (2,3);

insert into acct_role_authority values (1,5);
insert into acct_role_authority values (1,6);
insert into acct_role_authority values (1,7);
insert into acct_role_authority values (1,8);

insert into acct_role_authority values (2,5);
insert into acct_role_authority values (2,6);

select * from acct_role_authority;

-- user
insert into acct_user values (1,"admin@springside.org.cn","admin","Admin","admin");
insert into acct_user values (2,"user@springside.org.cn","user","User","user");
-- insert into acct_user values (3,"jack@springside.org.cn","user2","Jack","user2");
-- insert into acct_user values (4,"kate@springside.org.cn","user3","Kate","user3");
-- insert into acct_user values (5,"sawyer@springside.org.cn","user4","Sawyer","user4");
-- insert into acct_user values (6,"ben@springside.org.cn","user5","Ben","user5");

select * from acct_user;

-- delete from acct_user where id=1;
-- delete from acct_user where id=2;
-- delete from acct_user where id=3;


-- user_role
insert into acct_user_role values (1,1);
insert into acct_user_role values (1,2);
insert into acct_user_role values (2,2);
-- insert into acct_user_role values (3,2);
-- insert into acct_user_role values (4,2);
-- insert into acct_user_role values (5,2);
-- insert into acct_user_role values (6,2);

select * from acct_user_role;


-- bbs
insert into section values(1,'springBBS',null);

insert into node values(1,'Java',0,'Java',0,1);
insert into node values(2,'Spring(Springside3)',0,'Spring(Springside3)',0,1);
insert into node values(3,'Struts2',0,'Struts2',0,1);
insert into node values(4,'Hibernate',0,'Hibernate',0,1);
insert into node values(5,'异常集合',0,'异常集合',0,1);
insert into node values(6,'其他',0,'其他',0,1);

select * from topic;
