package cn.tiger.web.mypace.bbs;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.bbs.CommunityLevel;
import cn.tiger.service.bbs.BbsManager;

//显示用户等级图片
@Namespace("/mypace/bbs")
public class ShowlevelimgAction extends ActionSupport{
	
	private BbsManager bbsManager;
	
	private String userLevel;
	
	public String showLevelImg(){
		
		int length = 0;  
		
		byte[] imgBytes=null;
		
		CommunityLevel communityLevel=bbsManager.getCommunityLevelByLevel(Integer.valueOf(userLevel));
		
		HttpServletResponse resp = ServletActionContext.getResponse();
				 
		resp.setContentType("image/gif");  
		
		imgBytes=communityLevel.getImg();
		
        try {
        	length=imgBytes.length;
            resp.setContentLength(length);  
            ServletOutputStream op = resp.getOutputStream();  
        	op.write(imgBytes);
			op.flush();  
	        op.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        
		return null;
	}

	public BbsManager getBbsManager() {
		return bbsManager;
	}

	@Autowired
	public void setBbsManager(BbsManager bbsManager) {
		this.bbsManager = bbsManager;
	}

	public synchronized String getUserLevel() {
		return userLevel;
	}

	public synchronized void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

}
