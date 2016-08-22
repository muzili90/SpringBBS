package cn.tiger.dao.account;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.account.AcctRole;
import cn.tiger.entity.account.AcctUser;

/**
 * 角色对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class RoleDao extends HibernateDao<AcctRole, Long> {

}
