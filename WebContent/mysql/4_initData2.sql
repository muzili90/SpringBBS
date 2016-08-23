use myspringbbssampledb;

-- 等级数据
-- insert into community_level (id,experience,levels) values (1,0,1);
-- insert into community_level (id,experience,levels) values (2,30,2);
-- insert into community_level (id,experience,levels) values (3,100,3);
-- insert into community_level (id,experience,levels) values (4,290,4);
-- insert into community_level (id,experience,levels) values (5,600,5);
-- insert into community_level (id,experience,levels) values (6,1050,6);
-- insert into community_level (id,experience,levels) values (7,1650,7);
-- insert into community_level (id,experience,levels) values (8,2650,8);
-- insert into community_level (id,experience,levels) values (9,7650,9);

update community_level t set t.experience = 0, t.levels = 1 where t.id = 1;

update community_level t set t.experience = 30, t.levels = 2 where t.id = 2;

update community_level t set t.experience = 100, t.levels = 3 where t.id = 3;

update community_level t set t.experience = 290, t.levels = 4 where t.id = 4;

update community_level t set t.experience = 600, t.levels = 5 where t.id = 5;

update community_level t set t.experience = 1050, t.levels = 6 where t.id = 6;

update community_level t set t.experience = 1650, t.levels = 7 where t.id = 7;

update community_level t set t.experience = 2650, t.levels = 8 where t.id = 8;

update community_level t set t.experience = 7650, t.levels = 9 where t.id = 9;



-- 徽章数据

insert into badge (id,name,url,alt,full_url) values (1,'论坛达人勋章','images/badge/badge1.jpg','论坛等级达到level4可以领取。','images/badge/full_img/badge1full.jpg');
insert into badge (id,name,url,alt,full_url) values (2,'论坛鼓励特别奖杯','images/badge/badge2.gif','鼓励赞助特别奖杯。','images/badge/full_img/badge2full.jpg');