package cn.tiger.dao.bbs;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.TypedValue;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.bbs.CheckTopicAd;

@Component
public class CheckTopicAdDao extends HibernateDao<CheckTopicAd, Long>{

	public CheckTopicAd getCheckTopicAd(Long UserId,Long TopicId){
		String hql="from CheckTopicAd c where c.userId=:userId and c.topicId=:topicId";
		Session session = sessionFactory.getCurrentSession();
		
		Query query=session.createQuery(hql);
		query.setLong("userId", UserId);
		query.setLong("topicId", TopicId);
		
		List<CheckTopicAd> list = query.list();

		CheckTopicAd c = null;
		if (list.size() != 0) {
			c = list.get(0);
		}
		
		return c;

	}
}
