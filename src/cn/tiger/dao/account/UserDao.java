package cn.tiger.dao.account;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.bbs.CheckCommentAd;
import cn.tiger.utils.md5.Md5PasswordEncoderUtil;

/**
 * 用户对象的泛型DAO�?.
 * 
 * @author calvin
 */
@Component
public class UserDao extends HibernateDao<AcctUser, Long> {
	
	public AcctUser findUserByUserNameAndEmail(String loginName,String email){
		
		String hql="from AcctUser u where u.loginName=:loginName and u.email=:email";
		Session session = sessionFactory.getCurrentSession();
		
		Query query=session.createQuery(hql);
		query.setString("loginName", loginName);
		query.setString("email", email);
		
		AcctUser acctUser=(AcctUser) query.uniqueResult();
		
		if(acctUser==null){
			System.out.println("acctUser is null");
		}
		
		return acctUser;
		
	}
	
	public AcctUser findUserByUserNameAndPassword(String loginName,String password){
		
		String hql="from AcctUser u where u.loginName=:loginName and u.password=:password";
		Session session = sessionFactory.getCurrentSession();
		
		Query query=session.createQuery(hql);
		query.setString("loginName", loginName);
		query.setString("password", Md5PasswordEncoderUtil.zencodePassword(password, null));
		
		AcctUser acctUser=(AcctUser) query.uniqueResult();
		
		if(acctUser==null){
			System.out.println("acctUser is null");
		}
		
		return acctUser;
		
	}
	
	public int getUserRankBySql(Long userId){
		
		List list =null;
		
		int result=0;

		Session session = sessionFactory.getCurrentSession();
		
		String sql="select id,experience,(select count(1) from acct_user where experience>= (select experience from acct_user where id = ? order by experience desc limit 1)) as rank from acct_user where id = ?";
		
		Query query = session.createSQLQuery(sql);
		
		query.setLong(0, userId);
		query.setLong(1, userId);
		
		list = query.list();
		
		for(Iterator iterator = list.iterator();iterator.hasNext();){ 
            //每个集合元素都是一个数组
            Object[] objects = (Object[]) iterator.next(); 
            //System.out.println("id="+objects[0]); 
            //System.out.println("experience="+objects[1]); 
            //System.out.println("rank="+objects[2]); 
            //System.out.println("----------------------------"); 
            result=Integer.valueOf(objects[2].toString());

        } 
		return result;
	}
}
