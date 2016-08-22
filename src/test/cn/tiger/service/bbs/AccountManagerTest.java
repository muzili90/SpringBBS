package test.cn.tiger.service.bbs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.test.spring.SpringTxTestCase;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.bbs.Badge;
import cn.tiger.entity.bbs.CheckTopicAd;
import cn.tiger.entity.bbs.CommunityLevel;
import cn.tiger.entity.bbs.Topic;
import cn.tiger.entity.bbs.TopicAd;
import cn.tiger.service.account.AccountManager;
import cn.tiger.service.bbs.BbsManager;

@ContextConfiguration(locations={"/spring/applicationContext-test.xml"})
public class AccountManagerTest extends SpringTxTestCase{

	private static DataSource dataSourceHolder = null;
	
	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private BbsManager bbsManager;
	
	@Before
	public void loadDefaultData() throws Exception {
		
	}

	@AfterClass
	public static void cleanDefaultData() throws Exception {
	}
	
	@Test
	public void test() {
		System.out.println("test");
	}
	
	@Test
	@Rollback(false)
	public void testAddBadges(){
		
		AcctUser user=accountManager.getUser(3L);
		Badge badge=bbsManager.getBadgeById(2L);
		user.getBadges().add(badge);
		accountManager.saveUser(user);
		
	}
	
	@Test
	@Rollback(false)
	public void testShowBadges(){
		
		AcctUser user=accountManager.getUser(3L);
		Set<Badge> set=user.getBadges();
		
		Iterator<Badge> it=set.iterator();
		
		while(it.hasNext()){
			Badge badge=it.next();
			System.out.println(badge.getName());
		}
	}
	
}
