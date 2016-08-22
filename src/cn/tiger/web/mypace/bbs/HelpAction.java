package cn.tiger.web.mypace.bbs;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/mypace/bbs")
@Results( {
	@Result(name = "help", location = "/WEB-INF/zz7/mypace/help/help.jsp", type = "dispatcher"),
	@Result(name = "support", location = "/WEB-INF/zz7/mypace/help/support.jsp", type = "dispatcher")
})
public class HelpAction extends ActionSupport{

	public String help(){
		return "help";
	}
	
	public String support(){
		return "support";
	}
}
