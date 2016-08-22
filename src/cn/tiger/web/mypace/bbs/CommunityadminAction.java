package cn.tiger.web.mypace.bbs;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.bbs.Topic;
import cn.tiger.service.bbs.BbsManager;

//∞Ê÷˜π‹¿Ì
@Namespace("/mypace/bbs")
@Results( { @Result(name = "SUCCESS", location = "topic.action", type = "redirect")
})
public class CommunityadminAction extends ActionSupport{
	
	private String topicId;
	
	private BbsManager bbsManager;
	
	public String deleteTopic(){
		
		bbsManager.deleteTopic(new Long(topicId));
		
		return "SUCCESS";
	}
	
	public String top(){
		
		Topic topic=bbsManager.getTopic(new Long(topicId));
		
		topic.setIsTop(500);
		
		bbsManager.saveTopic(topic);
		
		return "SUCCESS";
	}
	
	public String cancelTop(){
		
		Topic topic=bbsManager.getTopic(new Long(topicId));
		
		topic.setIsTop(0);
		
		bbsManager.saveTopic(topic);
		
		return "SUCCESS";
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public BbsManager getBbsManager() {
		return bbsManager;
	}

	@Autowired
	public void setBbsManager(BbsManager bbsManager) {
		this.bbsManager = bbsManager;
	}

}
