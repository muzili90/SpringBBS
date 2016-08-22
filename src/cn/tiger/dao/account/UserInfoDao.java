package cn.tiger.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.tiger.entity.account.AcctUserInfo;

@Component
public class UserInfoDao extends HibernateDao<AcctUserInfo, Long> {

}
