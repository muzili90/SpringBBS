package cn.tiger.web.mypace.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.bbs.Badge;
import cn.tiger.entity.bbs.BadgeShow;
import cn.tiger.service.account.AccountManager;
import cn.tiger.service.bbs.BbsManager;

@Namespace("/mypace/bbs")
@Results( {
	@Result(name = "login", location = "/WEB-INF/zz7/mypace/login.jsp", type = "dispatcher"),
	@Result(name = "info", location = "/WEB-INF/zz7/mypace/userinfo/infoBadge.jsp", type = "dispatcher")
})
public class BadgeAction  extends ActionSupport{
	
	private List<Badge> badges=new ArrayList<Badge>();
	
	private Long badgeId;
	
	private Integer badgeShowFlag;
	
	private BbsManager bbsManager;
	
	private AccountManager accountManager;

	public String info(){
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    AcctUser sessionUser=(AcctUser)session.get("user");
	    if(sessionUser==null){
	    	return "login";
	    }
	    
		badges=bbsManager.getAllBadges();
		return "info";
	}
	
	
	//msg 0:	未登录
	//msg 1:	已经领取过
	//msg 2:	正常领取
	//msg 3:	未满足条件
	//msg 4:	未知错误
	public void getBadgeAjax() throws IOException {  
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
        	boolean alreadyget=false;
        	Badge checkBadge=bbsManager.getBadgeById(badgeId);
        	user=accountManager.findUserById(user.getId());
            Set<Badge> badges=user.getBadges();
            Iterator<Badge> it=badges.iterator();
            while(it.hasNext()){
            	Badge tempBadge=it.next();
            	if(tempBadge.getId().equals(checkBadge.getId())){
            		alreadyget=true;
            	}
            }
            if(alreadyget==true){
            	//已经领取
            	writer.print("{\"msg\":1}");  
                writer.flush();  
                writer.close();
            }else{
            	//领取论坛达人勋章(ID=1)
            	if(badgeId==1){
            		int user_level=user.getUser_level();
            		if(user_level>=4){
            			//已领取
            			user.getBadges().add(checkBadge);
            			accountManager.saveUser(user);
            			
            			BadgeShow badgeShow=bbsManager.getBadgeShowByUserIdAndBadgeId(user.getId(), badgeId);
            			
            			if(badgeShow==null){
            				badgeShow=new BadgeShow();
            				badgeShow.setUserId(user.getId());
            				badgeShow.setBadgeId(badgeId);
            				badgeShow.setShow_control(1);
            				bbsManager.saveBadgeShow(badgeShow);
            			}

            			writer.print("{\"msg\":2}");  
                        writer.flush();  
                        writer.close();
            		}else{
            			writer.print("{\"msg\":3}");  
                        writer.flush();  
                        writer.close();
            		}
            	}
            	if(badgeId==2){
            		//功能尚未实现
            		writer.print("{\"msg\":3}");  
            		writer.flush();  
            		writer.close();
            	}else{
            		writer.print("{\"msg\":4}");  
                    writer.flush();  
                    writer.close();
            	}
            }
            
        }
    }  
	
	public void changeBadgeDisableAjax() throws IOException{
		
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
	    	 BadgeShow badgeShow=bbsManager.getBadgeShowByUserIdAndBadgeId(user.getId(), badgeId);
			 
			 if(badgeShow==null){
				 //未找到显示勋章的记录
				 writer.print("{\"msg\":1}");  
		         writer.flush();  
		         writer.close();
			 }else{
				 
				 if(badgeShowFlag==1){
					 badgeShow.setShow_control(1);
					 bbsManager.saveBadgeShow(badgeShow);
					 writer.print("{\"msg\":2}");  
			         writer.flush();  
			         writer.close();
				 }else if(badgeShowFlag==0){
					 badgeShow.setShow_control(0);
					 bbsManager.saveBadgeShow(badgeShow);
					 writer.print("{\"msg\":2}");  
			         writer.flush();  
			         writer.close();
				 }else{
					 writer.print("{\"msg\":3}");  
			         writer.flush();  
			         writer.close();
				 }
			 } 
	     }
	}
	
	//获取显示勋章的显示状态
	public void getBadgeDisableAjax() throws IOException{
		
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
	    	 String print="[";
	    	 user=accountManager.getUser(user.getId());
	    	 Set<Badge> badges=user.getBadges();
	    	 if(badges==null){
	    	 	//9代表没有勋章
	    	 	writer.print("{\"msg\":9}");  
	         	writer.flush();  
	         	writer.close();
	         	return;
	    	 }
	    	 Iterator<Badge> it=badges.iterator();
	    	 while(it.hasNext()){
	    		 print+="{\"msg\":";
	    		 Badge badgeTemp=it.next();
	    		 BadgeShow badgeShowTemp=bbsManager.getBadgeShowByUserIdAndBadgeId(user.getId(), badgeTemp.getId());
	    		 if(badgeShowTemp.getShow_control()==0){
	    			 print+="0";
	    		 }else if(badgeShowTemp.getShow_control()==1){
	    			 print+="1";
	    		 }else{
	    			 
	    		 }
	    		 print+="},";
	    	 }
	    	 if(print.lastIndexOf(",")==print.length()-1){
    			 print=print.substring(0, print.length()-1);
    		 }
	    	 print+="]";
	    	 System.out.println(print);
	    	 //String print="[{\"msg\":1},{\"msg\":3}]";
	    	 writer.print(print);  
	         writer.flush();  
	         writer.close();
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

	public List<Badge> getBadges() {
		return badges;
	}

	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}


	public Integer getBadgeShowFlag() {
		return badgeShowFlag;
	}


	public void setBadgeShowFlag(Integer badgeShowFlag) {
		this.badgeShowFlag = badgeShowFlag;
	}
}
