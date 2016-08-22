package cn.tiger.web.mypace;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.service.account.AccountManager;
import cn.tiger.utils.email.EmailUtils;
import cn.tiger.utils.md5.Md5PasswordEncoderUtil;

@Namespace("/mypace/account")
@Results( { @Result(name = "forget", location = "/WEB-INF/zz7/mypace/user/forget.jsp", type = "dispatcher"),
	@Result(name = "forgetEmail", location = "/WEB-INF/zz7/mypace/user/forgetEmail.jsp", type = "dispatcher"),
	@Result(name = "forgetSuccess", location = "/WEB-INF/zz7/mypace/user/forgetSuccess.jsp", type = "dispatcher"),
	@Result(name = "resetPassword_form", location = "/WEB-INF/zz7/mypace/user/forgetResetPassword.jsp", type = "dispatcher"),
	@Result(name = "resetPasswordSuccess", location = "/WEB-INF/zz7/mypace/user/forgetResetPasswordSuccess.jsp", type = "dispatcher"),
	@Result(name = "resetPassword_err", location = "/WEB-INF/zz7/mypace/user/forgetResetPasswordError.jsp", type = "dispatcher")
	
})
public class ForgetAction extends ActionSupport{
	
	AccountManager accountManager;
	
	private String userName;
	
	private String email;
	
	private String password;
	
	private String repassword;
	
	private String sid;

	private AcctUser user;
	
	public String forget(){
		
		return "forget";
	}
	
	public String forgetName(){
		
		//System.out.println(user.getLoginName());
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    //System.out.println(userName);
	    
	    user=accountManager.findUserByLoginName(userName);
	    
	    if(user==null){
	    	session.put("errorMsg", "用户名不存在!");
	 		
	 		return "forget";
	    }
	    else{
	    	session.put("errorMsg", "");
	    	
	    	//System.out.println(user.getLoginName());
	    	
	    	return "forgetEmail";
	    }
	   
	}
	
	public String forgetEmail(){
		
		//System.out.println(user.getLoginName());
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    user=accountManager.findUserByUserNameAndEmail(userName,email);
	    
	    if(user==null){
	    	session.put("errorMsg", "邮箱不存在!");
	 		
	 		return "forgetEmail";
	    }
	    else{
	    	session.put("errorMsg", "");
	    	
	    	String secretKey = UUID.randomUUID().toString(); // 密钥
	    	Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
	    	long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的
	    	
	    	System.out.println("-------------\n");
	    	System.out.println("date="+date);
	    	System.out.println("-------------\n");
	    	
	    	String key =user.getLoginName() + "$" + date + "$" + secretKey;
	    	
	    	user.setOutDate(outDate);
	    	user.setValidataCode(secretKey);
	    	
	    	accountManager.saveUser(user);
	    	
	    	//String digitalSignature = DigestUtils.md5Hex(key); 
	    	String digitalSignature = DigestUtils.md5Hex(key);                 //数字签名
	    
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	
	    	String path = request.getContextPath();
	    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            String resetPassHref =  basePath+"mypace/account/forget!resetPasswordForm.action?sid="+digitalSignature+"&userName="+user.getLoginName();
	    	
	    	// 发送重新设置密码的链接
			EmailUtils.sendResetPasswordEmail(user,resetPassHref);
			
			session.put("tipMsg", "您的申请已提交成功，请查看您的"+user.getEmail()+"邮箱。");
	    	
	    	return "forgetSuccess";
	    }
	   
	}
	
	public String resetPasswordForm(){
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
		
		if (sid.equals("")  || userName.equals("")) {
            
            session.put("tipMsg", "链接不完整,请重新生成");
            System.out.println("sid or userName is null");
            return "resetPassword_err";
        }
		
		user=accountManager.findUserByLoginName(userName);
		
		if(user==null){
			//用户名不存在
	    	session.put("tipMsg", "链接错误,无法找到匹配用户,请重新申请找回密码。");
	 		
	 		return "resetPassword_err";
	    }
		
		Timestamp outDate = (Timestamp) user.getOutDate();
		
		System.out.println("outDate "+outDate);
        if(outDate.getTime() <= System.currentTimeMillis()){ //表示已经过期
            session.put("tipMsg", "链接已经过期,请重新申请找回密码。");
            return "resetPassword_err";
        }
        
        String key = user.getLoginName()+"$"+outDate.getTime()/1000*1000+"$"+user.getValidataCode();//数字签名
        
        String digitalSignature = DigestUtils.md5Hex(key);// 数字签名
        
        if(!digitalSignature.equals(sid)) {
            
            session.put("tipMsg", "链接不正确,是否已经过期了?重新申请吧。");
            System.out.println("sid不正确");
            
            return "resetPassword_err";
        }else {
          //链接验证通过 转到修改密码页面
          return "resetPassword_form";
      }

	}
	
	public String resetPassword(){
		
		user=accountManager.findUserByLoginName(userName);
		
		user.setPassword(Md5PasswordEncoderUtil.zencodePassword(password, null));
		
		accountManager.saveUser(user);
		
		System.out.println(user.getLoginName());
		System.out.println(user.getPassword());
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    session.put("tipMsg", "密码重置成功");
		
		return "resetPasswordSuccess";
	}
	
	public AccountManager getAccountManager() {
		return accountManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public AcctUser getUser() {
		return user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public void setUser(AcctUser user) {
		this.user = user;
	}

}
