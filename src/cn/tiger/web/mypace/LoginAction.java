package cn.tiger.web.mypace;


import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.account.AcctRole;
import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.account.UserDetails.MyUserDetails;
import cn.tiger.service.account.AccountManager;

@Namespace("/mypace")
@Results( { @Result(name = "success", location = "/WEB-INF/zz7/mypace/login.jsp", type = "dispatcher"),
	@Result(name = "success_form", location = "bbs/topic.action", type = "redirectAction" )
})
public class LoginAction extends ActionSupport{
	
	private AccountManager accountManager;

	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String loginInfo(){
		
		MyUserDetails myUserDetails=(MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AcctUser user=accountManager.findUserByLoginName(myUserDetails.getUsername());
		//保证servlet session保存时role有数据
		user.getRoleList();
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    session.put("user", user);
	    
	    for(AcctRole role:user.getRoleList()){
	    	/*if(role.getName().equals("管理员")){
	    		isAdmin=true;
	    		System.out.println("\n\n管理员登录\n\n");
	    	}*/
	    	
	    	if(role.getId()==1){
	    		session.put("isAdmin", true);
	    	}
	    	
	    }
		return "success_form";
	}
	
	public AccountManager getAccountManager() {
		return accountManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
