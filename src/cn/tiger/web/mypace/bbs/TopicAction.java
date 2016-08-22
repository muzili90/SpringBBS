package cn.tiger.web.mypace.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.account.UserDetails.MyUserDetails;
import cn.tiger.entity.bbs.Badge;
import cn.tiger.entity.bbs.BadgeShow;
import cn.tiger.entity.bbs.CheckTopicAd;
import cn.tiger.entity.bbs.Comment;
import cn.tiger.entity.bbs.CommunityLevel;
import cn.tiger.entity.bbs.Node;
import cn.tiger.entity.bbs.Topic;
import cn.tiger.entity.bbs.TopicAd;
import cn.tiger.service.account.AccountManager;
import cn.tiger.service.bbs.BbsManager;
import cn.tiger.utils.level.LevelUtil;
import cn.tiger.utils.zstring.MyStringUtil;

@Namespace("/mypace/bbs")
@Results( { @Result(name = "index", location = "/WEB-INF/zz7/mypace/index.jsp", type = "dispatcher"),
	@Result(name = "nodeIndex", location = "/WEB-INF/zz7/mypace/bbs/nodeIndex.jsp", type = "dispatcher"),
	@Result(name = "createNewTopic", location = "/WEB-INF/zz7/mypace/bbs/createTopic.jsp", type = "dispatcher"),
	@Result(name = "login", location = "/WEB-INF/zz7/mypace/login.jsp", type = "dispatcher"),
	@Result(name = "save_ok", location = "topic.action", type = "redirect"),
	@Result(name = "showTopic", location = "/WEB-INF/zz7/mypace/bbs/showTopic.jsp", type = "dispatcher")

})
public class TopicAction extends ActionSupport{
	
	private BbsManager bbsManager;
	
	private AccountManager accountManager;
	
	private List<Node> nodes;
	
	private Topic topic;
	
	//查看帖子的Id
	private Long viewTopicId;
	
	//赞与踩的帖子Id
	private Long adTopicId;
	
	//zan or cai
	private String zanOrcai;

	//选择的节点
	private String nodeValue;

	private Page<Topic> page = new Page<Topic>(9);
	
	private Page<Comment> commontPage = new Page<Comment>(9);
	
	//徽章相关

	//选择分类
	public static final String SECTIONID="1";

	public String execute(){
		
		page=bbsManager.getIndexTopics(page);
	    
		List filters = new ArrayList();
		PropertyFilter filter = new PropertyFilter("EQL_section.id", SECTIONID);
        filters.add(filter);
        nodes = bbsManager.findAllNodesByFitler(filters);
		
		return "index";
	}
	
	//查看帖子
	public String showTopic(){
		
		topic=bbsManager.getTopic(viewTopicId);
		
		//showBadges
		Set<Badge> userBadges=topic.getAcctuser().getBadges();
		
		Iterator<Badge> it_user_badge=userBadges.iterator();
		
		//发帖用户排除隐藏的勋章
		while(it_user_badge.hasNext()){
			Badge badgeTemp=it_user_badge.next();
			BadgeShow badgeShowTemp=bbsManager.getBadgeShowByUserIdAndBadgeId(topic.getAcctuser().getId(), badgeTemp.getId());
			if(badgeShowTemp!=null){
				if(badgeShowTemp.getShow_control()==0){
					it_user_badge.remove();
				}
			}
		}

		List filters = new ArrayList();
		PropertyFilter filter = new PropertyFilter("EQL_topic.id", viewTopicId.toString());
        filters.add(filter);
		
		commontPage=bbsManager.getComments(commontPage,filters);
		
		List<Comment> comments=commontPage.getResult();
		
		////回帖用户排除隐藏的勋章
		if(comments.isEmpty()==false){
			Iterator<Comment> itComments=comments.iterator();
			while(itComments.hasNext()){
				Comment cTemp=itComments.next();
				Set<Badge> CommentUserbadges=cTemp.getAcctuser().getBadges();
				Iterator<Badge> it_comment_user_badge=CommentUserbadges.iterator();
				while(it_comment_user_badge.hasNext()){
					Badge badgeCommentTemp=it_comment_user_badge.next();
					BadgeShow badgeShowTemp=bbsManager.getBadgeShowByUserIdAndBadgeId(cTemp.getAcctuser().getId(), badgeCommentTemp.getId());
					if(badgeShowTemp!=null){
						if(badgeShowTemp.getShow_control()==0){
							it_comment_user_badge.remove();
						}
					}
				}
			}
		}
		
		return "showTopic";
	}
	
	public String showNodeTopic(){
		
		System.out.println("");
		System.out.println(nodeValue);
		System.out.println("");
		
		Long nodeLongValue=new Long(nodeValue);
		
		page=bbsManager.getNodeTopics(page,nodeLongValue);
		
		List filters = new ArrayList();
		PropertyFilter filter = new PropertyFilter("EQL_section.id", SECTIONID);
        filters.add(filter);
        nodes = bbsManager.findAllNodesByFitler(filters);
		
		return "nodeIndex";
	}
	
	public String createNewTopic(){
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    AcctUser user=(AcctUser)session.get("user");
	    if(user==null){
	    	return "login";
	    }
		
		 List filters = new ArrayList();
		 PropertyFilter filter = new PropertyFilter("EQL_section.id", SECTIONID);
         filters.add(filter);
         nodes = bbsManager.findAllNodesByFitler(filters);
         
		for(Node n:nodes){
			System.out.println(n.getName());
		}
		
		return "createNewTopic";
	}
	
	public String saveTopic(){
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    AcctUser user=(AcctUser)session.get("user");
	    
	    if(user==null){
	    	return "login";
	    }
	    
	    Long nodeId=Long.valueOf(nodeValue);
	    
	    Node node=bbsManager.getNode(nodeId);
	    
	    Topic newTopic=new Topic();
	    newTopic.setAcctuser(user);
	    newTopic.setNode(node);
	    newTopic.setCommentCount(0L);
	    newTopic.setTitle(topic.getTitle());
	    //替换帖子内容
	    newTopic.setContent(MyStringUtil.strReplaces(topic.getContent(), "<p>","</p>","<code>","<pre>","</pre>","<code class=\"lang-java\">"));
	    newTopic.setCreateTime(new Date());
	    newTopic.setStatus(0);
	    newTopic.setViewCount(0L);
	    
	    System.out.println(node.getName());
	    System.out.println(user.getLoginName());
	    System.out.println(topic.getTitle());
	    
	    System.out.println(topic.getContent());
	    
	    bbsManager.saveTopic(newTopic);
	    
	    //发布帖子增加经验
	    user=accountManager.getUser(user.getId());
		long experience=user.getExperience();
		experience+=5;
		
		List<CommunityLevel> bbsLevels=bbsManager.getAllCommunityLevels();
		try {
			CommunityLevel newLevel=LevelUtil.getNewLevel(bbsLevels, experience);
			user.setExperience(experience);
			user.setUser_level(newLevel.getLevels());
			accountManager.saveUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //-------------
	    
		return "save_ok";
	}
	
	public void doTopicAdAjax() throws IOException {  
        HttpServletResponse response = ServletActionContext.getResponse();  
        
        ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    AcctUser user=(AcctUser)session.get("user");
	    
        PrintWriter writer = response.getWriter();  
        if(user==null){
        	//未登录
        	writer.print("{\"msg\":0}");  
            writer.flush();  
            writer.close();
        }else{
            CheckTopicAd checkTopicAd=bbsManager.findCheckTopicAd(user.getId(), adTopicId);
            if(checkTopicAd!=null){
            	//已经评论过
            	writer.print("{\"msg\":1}");  
                writer.flush();  
                writer.close();
            }else{
            	//创建用户关联
            	checkTopicAd=new CheckTopicAd();
            	checkTopicAd.setUserId(user.getId());
            	checkTopicAd.setTopicId(adTopicId);
            	bbsManager.saveCheckTopicAd(checkTopicAd);
            	
            	TopicAd topicAd=bbsManager.getTopicAdByTopicId(adTopicId);
            	if(topicAd==null){
            		//创建TopicAd
            		topicAd=new TopicAd();
        			topicAd.setAgreeCount(0L);
        			topicAd.setDsagreeCount(0L);
        			Topic topicForAd=bbsManager.getTopic(adTopicId);
        			topicAd.setTopic(topicForAd);
        			bbsManager.saveTopicAd(topicAd);
            	}
            	//增加count
        		if(zanOrcai.trim().equals("zan")){
        			topicAd.setAgreeCount(topicAd.getAgreeCount()+1);
        			bbsManager.saveTopicAd(topicAd);
        			writer.print("{\"zancount\":"+topicAd.getAgreeCount()+"}");  
                    writer.flush();  
                    writer.close();
        		}else{
        			topicAd.setDsagreeCount(topicAd.getDsagreeCount()+1);
        			bbsManager.saveTopicAd(topicAd);
        			writer.print("{\"caicount\":"+topicAd.getDsagreeCount()+"}");  
                    writer.flush();  
                    writer.close();
        		}
            }
        }
    }  

	public BbsManager getBbsManager() {
		return bbsManager;
	}

	@Autowired
	public void setBbsManager(BbsManager bbsManager) {
		this.bbsManager = bbsManager;
	}
	
	public AccountManager getAccountManager() {
		return accountManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public Page<Topic> getPage() {
		return page;
	}

	public void setPage(Page<Topic> page) {
		this.page = page;
	}
	
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}
	
	public Long getViewTopicId() {
		return viewTopicId;
	}

	public void setViewTopicId(Long viewTopicId) {
		this.viewTopicId = viewTopicId;
	}

	public Page<Comment> getCommontPage() {
		return commontPage;
	}

	public void setCommontPage(Page<Comment> commontPage) {
		this.commontPage = commontPage;
	}
	
	public Long getAdTopicId() {
		return adTopicId;
	}

	public void setAdTopicId(Long adTopicId) {
		this.adTopicId = adTopicId;
	}
	
	public String getZanOrcai() {
		return zanOrcai;
	}

	public void setZanOrcai(String zanOrcai) {
		this.zanOrcai = zanOrcai;
	}
}
