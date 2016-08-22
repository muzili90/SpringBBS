package cn.tiger.web.mypace.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import cn.tiger.entity.bbs.CheckCommentAd;
import cn.tiger.entity.bbs.CheckTopicAd;
import cn.tiger.entity.bbs.Comment;
import cn.tiger.entity.bbs.CommentAd;
import cn.tiger.entity.bbs.CommunityLevel;
import cn.tiger.entity.bbs.Node;
import cn.tiger.entity.bbs.Topic;
import cn.tiger.entity.bbs.TopicAd;
import cn.tiger.service.account.AccountManager;
import cn.tiger.service.bbs.BbsManager;
import cn.tiger.utils.level.LevelUtil;
import cn.tiger.utils.zstring.MyStringUtil;

@Namespace("/mypace/bbs")
@Results( {
	@Result(name = "login", location = "/WEB-INF/zz7/mypace/login.jsp", type = "dispatcher"),
	@Result(name = "success", location = "topic!showTopic.action?viewTopicId=${viewTopicId}", type = "redirect")
})
public class CommentAction extends ActionSupport{
	
	private BbsManager bbsManager;
	
	private AccountManager accountManager;

	//...
	private Page<Topic> commontPage = new Page<Topic>(9);
	
	//查看帖子的Id
	private Long viewTopicId;
	
	private String commont;
	
	//回复的帖子Id
	private Long viewCommentId;
	
	//zan or cai
	private String commentZanOrcai;
	
	//回复
	public String saveComment(){
		
		Topic replyTopic=bbsManager.getTopic(viewTopicId);
		System.out.println(replyTopic.getTitle());
		System.out.println(commont);
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    AcctUser user=(AcctUser)session.get("user");
	    
	    if(user==null){
	    	return "login";
	    }
	    
		Comment newComment=new Comment();
		newComment.setAcctuser(user);
		newComment.setTopic(replyTopic);
		newComment.setCreateTime(new Date());
		newComment.setContent(MyStringUtil.strReplaces(commont, "<p>","</p>","<code>","<pre>","</pre>","<code class=\"lang-java\">"));
		newComment.setStatus(0);
		
		bbsManager.saveComment(newComment);
		
		replyTopic.setCommentCount(replyTopic.getCommentCount()+1);
		replyTopic.setLastacctuser(user);
		replyTopic.setLastCommentAt(new Date());
		replyTopic.setLastCommentUserId(user.getId());
		
		bbsManager.saveTopic(replyTopic);
		
		//回复增加经验
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
		//------------
		
		return SUCCESS;
	}
	
	public void doCommentAdAjax() throws IOException {  
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
            CheckCommentAd checkCommentAd=bbsManager.findCheckCommentAd(user.getId(), viewCommentId);
            if(checkCommentAd!=null){
            	//已经评论过
            	writer.print("{\"msg\":1}");  
                writer.flush();  
                writer.close();
            }else{
            	//创建用户关联
            	checkCommentAd=new CheckCommentAd();
            	checkCommentAd.setUserId(user.getId());
            	checkCommentAd.setCommentId(viewCommentId);
            	bbsManager.saveCheckCommentAd(checkCommentAd);
            	
            	CommentAd commentAd=bbsManager.getCommentAdByCommentId(viewCommentId);
            	if(commentAd==null){
            		//创建TopicAd
            		commentAd=new CommentAd();
            		commentAd.setAgreeCount(0L);
            		commentAd.setDsagreeCount(0L);
        			Comment commentForAd=bbsManager.getComment(viewCommentId);
        			commentAd.setComment(commentForAd);
        			bbsManager.saveCommentAd(commentAd);
            	}
            	//增加count
        		if(commentZanOrcai.trim().equals("zan")){
        			commentAd.setAgreeCount(commentAd.getAgreeCount()+1);
        			bbsManager.saveCommentAd(commentAd);
        			writer.print("{\"zancount\":"+commentAd.getAgreeCount()+"}");  
                    writer.flush();  
                    writer.close();
        		}else{
        			commentAd.setDsagreeCount(commentAd.getDsagreeCount()+1);
        			bbsManager.saveCommentAd(commentAd);
        			writer.print("{\"caicount\":"+commentAd.getDsagreeCount()+"}");  
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

	public Page<Topic> getCommontPage() {
		return commontPage;
	}

	public void setCommontPage(Page<Topic> commontPage) {
		this.commontPage = commontPage;
	}
	
	public Long getViewTopicId() {
		return viewTopicId;
	}

	public void setViewTopicId(Long viewTopicId) {
		this.viewTopicId = viewTopicId;
	}

	public String getCommont() {
		return commont;
	}

	public void setCommont(String commont) {
		this.commont = commont;
	}

	public String getCommentZanOrcai() {
		return commentZanOrcai;
	}

	public void setCommentZanOrcai(String commentZanOrcai) {
		this.commentZanOrcai = commentZanOrcai;
	}

	public Long getViewCommentId() {
		return viewCommentId;
	}

	public void setViewCommentId(Long viewCommentId) {
		this.viewCommentId = viewCommentId;
	}

}
