package cn.tiger.web.mypace;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springside.modules.orm.PropertyFilter;

import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.account.UserDetails.MyUserDetails;
import cn.tiger.service.account.AccountManager;

@Namespace("/mypace")
@Results( { @Result(name = "success", location = "bbs/topic.action", type = "redirectAction")
})
public class IndexAction extends ActionSupport{
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
