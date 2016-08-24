package cn.tiger.web.mypace.bbs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;

import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.bbs.CheckCommentAd;
import cn.tiger.entity.bbs.CheckTopicAd;
import cn.tiger.entity.bbs.Comment;
import cn.tiger.entity.bbs.CommentAd;
import cn.tiger.entity.bbs.Topic;
import cn.tiger.entity.bbs.TopicAd;
import cn.tiger.service.bbs.BbsManager;

//∞Ê÷˜π‹¿Ì
@Namespace("/mypace/bbs")
@Results( { @Result(name = "SUCCESS", location = "topic.action", type = "redirect")
})
public class CommunityadminAction extends ActionSupport{
	
	private String topicId;
	
	private BbsManager bbsManager;
	
	public String deleteTopic(){
		
		List commentfilters = new ArrayList();
		PropertyFilter commentfilter = new PropertyFilter("EQL_topic.id", topicId);
		commentfilters.add(commentfilter);
		
		List<Comment> commentList=bbsManager.getAllCommentsByFitler(commentfilters);
		
		if(commentList.size()!=0){
			Iterator<Comment> commentsIt=commentList.iterator();
			while(commentsIt.hasNext()){
				Comment commentTemp=commentsIt.next();
				CommentAd commentad=bbsManager.getCommentAdByCommentId(commentTemp.getId());
				if(commentad!=null){
					bbsManager.deleteCommentAd(commentad.getId());
				}
				List<CheckCommentAd> checkCommentAds=bbsManager.findCheckCommentAdsByCommentId(commentTemp.getId());
				if(checkCommentAds.size()!=0){
					Iterator<CheckCommentAd> checkCommentAdsIt=checkCommentAds.iterator();
					while(checkCommentAdsIt.hasNext()){
						CheckCommentAd checkCommentAdTemp=checkCommentAdsIt.next();
						bbsManager.deleteCheckCommentAd(checkCommentAdTemp.getId());
					}
				}
				bbsManager.deleteComment(commentTemp.getId());
				commentsIt.remove();
			}
		}
		
		TopicAd topicAd=bbsManager.getTopicAdByTopicId(new Long(topicId));
		if(topicAd!=null){
			bbsManager.deleteTopicAd(topicAd.getId());
		}
		List<CheckTopicAd> checkTopicAds=bbsManager.findCheckTopicAdsByTopicId(new Long(topicId));
		if(checkTopicAds.size()!=0){
			Iterator<CheckTopicAd> checkTopicAdsIt=checkTopicAds.iterator();
			while(checkTopicAdsIt.hasNext()){
				CheckTopicAd checkTopicAdTemp=checkTopicAdsIt.next();
				bbsManager.deleteCheckTopicAd(checkTopicAdTemp.getId());
			}
		}
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
