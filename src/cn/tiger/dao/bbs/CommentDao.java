package cn.tiger.dao.bbs;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.bbs.Comment;

/**
 * 用户对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class CommentDao extends HibernateDao<Comment, Long> {
	
	/*public Page<Comment> getCommentsByTopic(final Page<Comment> page,List<PropertyFilter> filters) {
		String hql="select c from Comment c order by c.createTime DESC";
		return findPage(page,hql,filters);
	}*/
	
}
