package cn.tiger.dao.bbs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.bbs.Topic;

/**
 * 用户对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class TopicDao extends HibernateDao<Topic, Long> {
	
	public Page<Topic> findPageTopic(final Page<Topic> page) {
		String hql="select t from Topic t order by t.isTop DESC,t.createTime DESC,t.lastCommentAt DESC";
		return findPage(page,hql);
	}
	
	public Page<Topic> findPageNodeTopic(final Page<Topic> page,Long nodeValue) {
		String hql="select t from Topic t where t.node.id=? order by t.isTop DESC,t.createTime DESC,t.lastCommentAt DESC";
		return findPage(page,hql,nodeValue);
	}

}
