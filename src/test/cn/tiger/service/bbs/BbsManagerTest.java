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

import cn.tiger.entity.bbs.BadgeShow;
import cn.tiger.entity.bbs.CheckTopicAd;
import cn.tiger.entity.bbs.CommunityLevel;
import cn.tiger.entity.bbs.Topic;
import cn.tiger.entity.bbs.TopicAd;
import cn.tiger.service.bbs.BbsManager;

@ContextConfiguration(locations={"/spring/applicationContext-test.xml"})
public class BbsManagerTest extends SpringTxTestCase{

	private static DataSource dataSourceHolder = null;
	
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
	@Rollback(true)
	public void testTopicAd(){
		Topic topic=bbsManager.getTopic(1L);
		Set<TopicAd> topicAds=topic.getTopicAd();
		TopicAd topicAd=null;
		if(topicAds.size()!=0){
			Iterator<TopicAd> it=topicAds.iterator();
			while(it.hasNext()){
				topicAd=it.next();
			}
			System.out.println(topicAd.getAgreeCount());
		}else{
			System.out.println("topicAd is null");
			topicAd=new TopicAd();
			topicAd.setAgreeCount(0L);
			topicAd.setDsagreeCount(0L);
			topicAd.setTopic(topic);
			topic.getTopicAd().add(topicAd);
			bbsManager.saveTopicAd(topicAd);
			System.out.println("topicId="+topicAd.getId());
		}
	}
	
	@Test
	@Rollback(true)
	public void testGetCheckTopicAd(){
		CheckTopicAd checkTopicAd=bbsManager.findCheckTopicAd(3L, 1L);
		if(checkTopicAd==null){
			System.out.println("is null");
			checkTopicAd=new CheckTopicAd();
			checkTopicAd.setUserId(3L);
			checkTopicAd.setTopicId(1L);
			bbsManager.saveCheckTopicAd(checkTopicAd);
			System.out.println("checkTopicAd is create");
		}else{
			System.out.println("is not null");
			System.out.println(checkTopicAd.getId());
		}
	}
	
	@Test
	@Rollback(false)
	public void testGetAllCommunityLevels(){
		List<CommunityLevel> list=bbsManager.getAllCommunityLevels();
		if(list==null||list.size()==0){
			System.out.println("list is null");
		}else{
			Iterator<CommunityLevel> it=list.iterator();
			while(it.hasNext()){
				System.out.println(it.next().getExperience());
			}
		}
		
	}
	
	@Test
	@Rollback(false)
	public void testGetBadge(){
		BadgeShow badgeShow=bbsManager.getBadgeShowByUserIdAndBadgeId(3L, 1L);
		if(badgeShow==null){
			System.out.println("badgeShow is null");
		}else{
			System.out.println(badgeShow.getShow_control());
		}
		
	}

}
