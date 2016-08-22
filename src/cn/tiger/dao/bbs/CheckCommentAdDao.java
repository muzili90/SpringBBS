package cn.tiger.dao.bbs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.bbs.CheckCommentAd;

@Component
public class CheckCommentAdDao extends HibernateDao<CheckCommentAd, Long>{
	
	public CheckCommentAd getCheckCommentAd(Long UserId,Long commentId){
		String hql="from CheckCommentAd c where c.userId=:userId and c.commentId=:commentId";
		Session session = sessionFactory.getCurrentSession();
		
		Query query=session.createQuery(hql);
		query.setLong("userId", UserId);
		query.setLong("commentId", commentId);
		
		List<CheckCommentAd> list = query.list();

		CheckCommentAd c = null;
		if (list.size() != 0) {
			c = list.get(0);
		}
		
		return c;

	}
}
