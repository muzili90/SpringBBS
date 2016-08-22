package cn.tiger.dao.bbs;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.bbs.Badge;

@Component
public class BadgeDao extends HibernateDao<Badge, Long>{

}
