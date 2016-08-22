use myspringbbssampledb;

-- show tables;
   

alter table acct_role_authority 
        drop foreign key FKAE243466DE3FB930;

alter table acct_role_authority 
        drop foreign key FKAE2434663FE97564;

alter table acct_user_role 
        drop foreign key FKFE85CB3EDE3FB930;

alter table acct_user_role 
        drop foreign key FKFE85CB3E836A7D10;

drop table if exists acct_authority;

drop table if exists acct_role;

drop table if exists acct_role_authority;

drop table if exists acct_user;

drop table if exists acct_user_role;

    -- <权限> 浏览用户 修改用户 浏览角色 修改角色
create table acct_authority (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB default charset=utf8;

    -- <角色> 管理员 用户
create table acct_role (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB default charset=utf8;;

    -- <角色>-<权限> manytomany
    /* 
    <ACCT_ROLE_AUTHORITY ROLE_ID="1" AUTHORITY_ID="1"/> 管理员拥有浏览用户的权限
 	<ACCT_ROLE_AUTHORITY ROLE_ID="1" AUTHORITY_ID="2"/> 管理员拥有修改用户的权限
  	<ACCT_ROLE_AUTHORITY ROLE_ID="1" AUTHORITY_ID="3"/> 管理员拥有浏览角色的权限
  	<ACCT_ROLE_AUTHORITY ROLE_ID="1" AUTHORITY_ID="4"/> 管理员拥有 修改角色的权限
  	
 	<ACCT_ROLE_AUTHORITY ROLE_ID="2" AUTHORITY_ID="1"/> 用户拥有浏览用户的权限
  	<ACCT_ROLE_AUTHORITY ROLE_ID="2" AUTHORITY_ID="3"/> 用户拥有浏览角色的权限
     */
create table acct_role_authority (
        role_id bigint not null,
        authority_id bigint not null
    ) ENGINE=InnoDB;
 
	-- 用户
    -- <ACCT_USER ID="1" EMAIL="admin@springside.org.cn" LOGIN_NAME="admin" NAME="Admin" PASSWORD="admin"/>
    -- <ACCT_USER ID="2" EMAIL="user@springside.org.cn" LOGIN_NAME="user" NAME="User" PASSWORD="user"/>
create table acct_user (
        id bigint not null auto_increment,
        email varchar(255) unique,
        login_name varchar(255) not null unique,
        name varchar(255),
        password varchar(255),
        primary key (id)
    ) ENGINE=InnoDB default charset=utf8;;

select * from acct_user;

-- user扩展表
-- alter table acct_user add column message integer;
-- alter table acct_user drop column userInfoId;

/* create table acct_user_info(
	-- id bigint,
	-- age integer,
	-- primary key (id)
	
-- )ENGINE=InnoDB;

*/

/* alter table acct_user_info 
        -- add constraint FKAE243466CE3FW830 
        -- foreign key (id) 
        -- references acct_user (id);

*/

-- select * from acct_user_info;

-- alter table acct_user_info drop foreign key FKAE243466CE3FW830;
-- drop table if exists acct_user_info;

/*message*/

/*create table userMessage (
        id bigint not null auto_increment,
        fromUser bigint,
        msgTakeOrSend int,
        msgFromType int,
        sendTime DATETIME,
		content varchar(255),
		link varchar(255),
		acctUserInfoId bigint not null,
        primary key (id)
    ) ENGINE=InnoDB;

*/

-- alter table userMessage add column statu int default 0;
-- alter table userMessage add column adminMessageId bigint;
-- alter table userMessage add column fromUserName varchar(12);

/*
alter table userMessage 
        add constraint FKVE243463DE3FB930 
        foreign key (acctUserInfoId) 
        references acct_user_info (id);

*/

/*
create table admin_message (
        id bigint not null auto_increment,
        fromUser bigint,
        msgFromType int,
        sendTime DATETIME,
		content varchar(255),
		link varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

*/

-- select * from userMessage;
   
 /*
     * 
    -- <用户>-<角色> manytomany
    <ACCT_USER_ROLE USER_ID="1" ROLE_ID="1"/>
  	<ACCT_USER_ROLE USER_ID="1" ROLE_ID="2"/>
  	<ACCT_USER_ROLE USER_ID="2" ROLE_ID="2"/>
     */


create table acct_user_role (
	user_id bigint not null,
	role_id bigint not null
)ENGINE=InnoDB;

select * from acct_user_role;


alter table acct_role_authority 
        add constraint FKAE243466DE3FB930 
        foreign key (role_id) 
        references acct_role (id);

alter table acct_role_authority 
        add constraint FKAE2434663FE97564 
        foreign key (authority_id) 
        references acct_authority (id);

alter table acct_user_role 
        add constraint FKFE85CB3EDE3FB930 
        foreign key (role_id) 
        references acct_role (id);

alter table acct_user_role 
        add constraint FKFE85CB3E836A7D10 
        foreign key (user_id) 
        references acct_user (id);

-- bbs

alter table node 
        drop foreign key DNAAE243466DE3FB930;
alter table topic 
        drop foreign key DNAE2434663FE97564;
alter table topic 
        drop foreign key DNAE85CB2E3E3FB930;
alter table `comment`
        drop foreign key DNBE2434663FE97564;
alter table `comment`
        drop foreign key DNBE85CB2E3E3FB930;

-- 分类
create table if not exists `section` (
  `id` bigint not null auto_increment,
  `name` varchar(255) default null unique,
  `sort` int(11) default null,
  primary key (`id`)
) engine=innodb default charset=utf8;

select * from section;

-- 节点
create table if not exists `node` (
  `id` bigint not null auto_increment,
  `description` varchar(255) default null,
  `topic_count` bigint(20) default '0',
  `name` varchar(255) not null unique,
  `status` int(11) not null default '0',
  `section_id` bigint not null,
  primary key (`id`)
) engine=innodb default charset=utf8;

select * from node;



alter table node 
        add constraint DNAAE243466DE3FB930 
        foreign key (section_id) 
        references section (id);

-- topic
create table if not exists `topic` (
  `id` bigint not null auto_increment,
  `comment_count` bigint(20) unsigned default '0',
  `content` text not null,
  `create_time` datetime not null,
  `last_comment_at` datetime default null,
  `last_comment_user_id` bigint default null,
  `status` int(11) not null,
  `title` varchar(255) not null,
  `update_time` datetime default null,
  `view_count` bigint default '0',
  `node_id` bigint not null,
  `user_id` bigint not null,
  primary key (`id`)
) engine=innodb default charset=utf8;

alter table topic add column last_user_id bigint default null;
-- 置顶 
alter table topic add column istop int default 0;


alter table topic 
        add constraint DNFE3434663FE97564 
        foreign key (last_user_id) 
        references acct_user (id);


/*alter table topic 
        drop foreign key DNFE3434663FE97564;
*/

select * from topic;


alter table topic 
        add constraint DNAE2434663FE97564 
        foreign key (node_id) 
        references node (id);

alter table topic 
        add constraint DNAE85CB2E3E3FB930 
        foreign key (user_id) 
        references acct_user (id);

-- commont
create table if not exists `comment` (
  `id` bigint not null auto_increment,
  `content` text not null,
  `create_time` datetime not null,
  `status` int(11) not null,
  `topic_id` bigint not null,
  `user_id` bigint not null,
  primary key (`id`)
) engine=innodb default charset=utf8;

select * from `comment`;


alter table `comment`
        add constraint DNBE2434663FE97564 
        foreign key (topic_id) 
        references topic (id);

alter table `comment`
        add constraint DNBE85CB2E3E3FB930 
        foreign key (user_id) 
        references acct_user (id);

-- 论坛赞和踩的数据库表

-- topic_ad Agree or Disagree

create table if not exists `topic_ad` (
  `id` bigint not null auto_increment,
  `topic_id` bigint not null,
  `agree_count` bigint not null,
  `dsagree_count` bigint not null,
  primary key (`id`)
) engine=innodb default charset=utf8;

alter table `topic_ad`
        add constraint DNCE85CB2E3A3FB930 
        foreign key (topic_id) 
        references topic (id);

-- check_topic_ad 检查是否有用户记录

create table if not exists `check_topic_ad` (
  `id` bigint not null auto_increment,
  `user_id` bigint not null,
  `topic_id` bigint not null,
  primary key (`id`)
) engine=innodb default charset=utf8;

alter table `check_topic_ad`
        add constraint DNCE85CB2E3A3FA931 
        foreign key (user_id) 
        references acct_user (id);

alter table `check_topic_ad`
        add constraint DNCE85CB2E3A3FA932 
        foreign key (topic_id) 
        references topic (id);


-- comment_ad Agree or Disagree

create table if not exists `comment_ad` (
  `id` bigint not null auto_increment,
  `comment_id` bigint not null,
  `agree_count` bigint not null,
  `dsagree_count` bigint not null,
  primary key (`id`)
) engine=innodb default charset=utf8;

alter table `comment_ad`
        add constraint DNEE85CB2E3A3FB911 
        foreign key (comment_id) 
        references `comment` (id);

create table if not exists `check_comment_ad` (
  `id` bigint not null auto_increment,
  `user_id` bigint not null,
  `comment_id` bigint not null,
  primary key (`id`)
) engine=innodb default charset=utf8;

alter table `check_comment_ad`
        add constraint DNEE85CB2E3A3FB921 
        foreign key (user_id) 
        references acct_user (id);

alter table `check_comment_ad`
        add constraint DNEE85CB2E3A3FB931
        foreign key (comment_id) 
        references `comment` (id);


use myspringbbssampledb;
/*
create table acct_user (
        id bigint not null auto_increment,
        email varchar(255),
        login_name varchar(255) not null unique,
		-- 昵称
        name varchar(255),
        password varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

*/
-- 头像图片地址
alter table acct_user add column avatar varchar(255);
-- 登录次数
alter table acct_user add column login_count bigint;
-- 创建时间
alter table acct_user add column create_time datetime;
-- 更新时间
alter table acct_user add column update_time datetime;

select * from acct_user;

select * from acct_user_role;

SET SQL_SAFE_UPDATES = 0;
SET SQL_SAFE_UPDATES = 1;

delete from acct_user_role where user_id in(13,14,15,16,17,18);

select * from acct_role;

select * from topic;

-- 找回密码

alter table acct_user drop column update_time;

alter table acct_user add column out_date datetime;

alter table acct_user add column validata_code varchar(50);

-- userInfo 用户扩展类 保存用户个人信息

create table acct_user_info(
	id bigint not null auto_increment,
	sex integer,		-- 0 男 1 女
	birthday varchar(20),	-- 1987-03-11
	job varchar(50),
	address varchar(100),
	signature varchar(255),	-- 个人签名
	score bigint,
	qq varchar(25),
	user_id bigint,
	primary key (id)
	
)ENGINE=InnoDB;
	
alter table acct_user_info 
        add constraint FKAABC3466CE3FW130 
        foreign key (user_id) 
        references acct_user (id);

alter table acct_user_info 
        drop foreign key FKAABC3466CE3FW130;


-- 等级表

create table community_level(
	id bigint not null auto_increment,
	experience bigint not null default 0,
	levels int not null default 0,
	img blob not null,
	primary key (id)
	
)ENGINE=InnoDB;

-- 0 30 100 290 600 1050 1650 2650 7650


-- 修改user表增加经验和等级字段

alter table acct_user add column experience bigint;
alter table acct_user add column user_level int;


create table badge(
	id bigint not null auto_increment,
	name varchar(20),
	url varchar(200),
	alt varchar(300),
	full_url varchar(300),
	primary key(id)
)ENGINE=InnoDB default charset=utf8;

-- 用户拥有的徽章
create table user_badge(
	user_id bigint not null,
	badge_id bigint not null
)ENGINE=InnoDB;

alter table user_badge 
        add constraint FCAABC0466CE3FW130 
        foreign key (user_id) 
        references acct_user (id);

alter table user_badge 
        add constraint FCAABC1466CE3FW130 
        foreign key (badge_id) 
        references badge (id);


-- 用户显示的勋章
create table badge_show(
	id bigint not null auto_increment,
	user_id bigint not null,
	badge_id bigint not null,
	show_control int(2),
	primary key(id)
)ENGINE=InnoDB default charset=utf8;