package cn.tiger.web.mypace;

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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.account.AcctRole;
import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.account.AcctUserInfo;
import cn.tiger.entity.account.UserDetails.MyUserDetails;
import cn.tiger.service.account.AccountManager;
import cn.tiger.utils.md5.Md5PasswordEncoderUtil;

@Namespace("/mypace/account")
@Results( { @Result(name = "singup", location = "/WEB-INF/zz7/mypace/user/register.jsp", type = "dispatcher"),
	@Result(name = "register_ok", location = "/mypace/login.action", type = "redirect"),
	@Result(name = "login", location = "/WEB-INF/zz7/mypace/login.jsp", type = "dispatcher"),
	@Result(name = "resetPassword_form", location = "/WEB-INF/zz7/mypace/user/resetPassword.jsp", type = "dispatcher"),
	@Result(name = "resetPassword_err", location = "/WEB-INF/zz7/mypace/user/resetPasswordError.jsp", type = "dispatcher"),
	@Result(name = "resetPasswordSuccess", location = "/WEB-INF/zz7/mypace/user/resetPasswordSuccess.jsp", type = "dispatcher")
})
public class UserAction extends ActionSupport{
	
	private String uniqueEmail;
	private String uniqueUserName;
	
	private AcctUser user;
	
	private String username;
	private String password;
	private String email;
	
	//修改密码
	private String reNewPassword;
	//-------
	
	private static final String ZROLENAME="用户";
	
	AccountManager accountManager;
	
	public void getUserNamelAjax() throws IOException {  
        HttpServletResponse response = ServletActionContext.getResponse();  
        
        AcctUser user=accountManager.findUserByLoginName(uniqueUserName);
        PrintWriter writer = response.getWriter();  
        if(user==null){
        	writer.print("true");  
            writer.flush();  
            writer.close();
        }else{
            writer.print("false");  
            writer.flush();  
            writer.close();
        }

    }  
	
	public void getEmailAjax() throws IOException {  
        HttpServletResponse response = ServletActionContext.getResponse();  
        
        AcctUser user=accountManager.getUserByEmail(uniqueEmail);
        PrintWriter writer = response.getWriter();  
        if(user==null){
        	writer.print("true");  
            writer.flush();  
            writer.close();
        }else{
            writer.print("false");  
            writer.flush();  
            writer.close();
        }

    }  
	
	public String getUniqueUserName() {
		return uniqueUserName;
	}

	public void setUniqueUserName(String uniqueUserName) {
		this.uniqueUserName = uniqueUserName;
	}

	public String getUniqueEmail() {
		return uniqueEmail;
	}

	public void setUniqueEmail(String uniqueEmail) {
		this.uniqueEmail = uniqueEmail;
	}
	
	public AcctUser getUser() {
		return user;
	}

	public void setUser(AcctUser user) {
		this.user = user;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public AccountManager getAccountManager() {
		return accountManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public String singup(){
		return "singup";
	}
	
	/*public String singin(){
		return "singin";
	}*/
	
	public String register() throws Exception{
		/*System.out.println("用户名"+username);
		System.out.println(email);*/
		user=accountManager.findUserByLoginName(username);
		if(user!=null){
			throw new Exception("用户已存在！");
		}
		user=accountManager.getUserByEmail(email);
		if(user!=null){
			throw new Exception("邮箱已存在！");
		}
		user=new AcctUser();
		user.setAvatar("https://aq.qq.com/v2/images/app_qqsafe.png");
		user.setLoginName(username);
		user.setPassword(Md5PasswordEncoderUtil.zencodePassword(password, null));
		user.setEmail(email);
		user.setName(username);
		user.setCreate_time(new Date());
		user.setLogin_count(1L);
		
		//----------- 用户等级
		user.setExperience(0L);
		user.setUser_level(1);
		//------------
		
		AcctRole acctRole=accountManager.getRole(ZROLENAME);
		user.getRoleList().add(acctRole);
		
		//------------------------------------------------
		AcctUserInfo userInfo=new AcctUserInfo();
		
		user.getAcctUserInfo().add(userInfo);
		userInfo.setAcctUser(user);
		
		accountManager.saveUser(user);
		
		return "register_ok";
	}
	
	public String resetPasswordForm(){
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    AcctUser user=(AcctUser)session.get("user");
	    if(user==null){
	    	return "login";
	    }
	    
	    username=user.getLoginName();
	    
	    return "resetPassword_form";
	}

	public String resetPassword(){
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
		
		user=accountManager.findUserByUserNameAndPassword(username, password);
		
		if(user==null){
			session.put("tipMsg", "密码错误");
			return "resetPassword_err";
		}else{
			user.setPassword(Md5PasswordEncoderUtil.zencodePassword(reNewPassword, null));
			
			accountManager.saveUser(user);
		    
		    session.put("tipMsg", "密码重置成功");
			
			return "resetPasswordSuccess";
		}
	
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}
}
