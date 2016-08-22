package cn.tiger.dao.bbs;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.bbs.BadgeShow;

@Component
public class BadgeShowDao extends HibernateDao<BadgeShow, Long>{

	public BadgeShow findBadgeShowByUserIdAndBadgeId(Long userId,Long badgeId){
		
		String hql="from BadgeShow b where b.userId=:userId and b.badgeId=:badgeId";
		Session session = sessionFactory.getCurrentSession();
		
		Query query=session.createQuery(hql);
		query.setLong("userId", userId);
		query.setLong("badgeId", badgeId);
		
		BadgeShow badgeShow=(BadgeShow) query.uniqueResult();
		
		return badgeShow;
		
	}
}
