package cn.tiger.dao.bbs;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.bbs.Node;

/**
 * 用户对象的泛型DAO�?.
 * 
 * @author calvin
 */
@Component
public class NodeDao extends HibernateDao<Node, Long> {

}
