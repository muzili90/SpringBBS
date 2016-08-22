package cn.tiger.web.mypace.bbs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tiger.entity.account.AcctUser;
import cn.tiger.entity.account.AcctUserInfo;
import cn.tiger.entity.bbs.Badge;
import cn.tiger.service.account.AccountManager;
import cn.tiger.service.bbs.BbsManager;
import cn.tiger.utils.zstring.MyStringUtil;

//用户扩展
@Namespace("/mypace/bbs")
@Results( {
	@Result(name = "login", location = "/WEB-INF/zz7/mypace/login.jsp", type = "dispatcher"),
	@Result(name = "InfoSuccess", location = "/WEB-INF/zz7/mypace/userinfo/infoIndex.jsp", type = "dispatcher"),
	@Result(name = "InfoSaveSuccess", location = "userinfo!info.action", type = "redirect"),
	@Result(name = "showUserInfoSuccess", location = "/WEB-INF/zz7/mypace/userinfo/infoIndex.jsp", type = "dispatcher"),
})
public class UserinfoAction extends ActionSupport{
	
	private AcctUser user;
	
	//浏览其他人的资料
	private Long userId;
	
	private String birthdayYear;
	private String birthdayMonth;
	private String birthdayDay;
	
	private String provinces;
	private String city;
	private String district;
	
	private String userInfoId;
	
	private String sexInfo;
	private String jobInfo;
	private String signatureInfo;
	private String qqInfo;
	
	private int rank;
	
	private AcctUserInfo userInfo;
	
	private Long badgeId;
	
	//用户所有徽章
	private Set<Badge> badges=new HashSet<Badge>();
	
	private BbsManager bbsManager;
	
	AccountManager accountManager;
	
	//显示个人资料
	public String info(){
		
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    
	    AcctUser sessionUser=(AcctUser)session.get("user");
	    if(sessionUser==null){
	    	return "login";
	    }
	    
	    //需要get ohers吗
	    user=accountManager.findUserById(sessionUser.getId());
	    
	    Iterator<AcctUserInfo> it = user.getAcctUserInfo().iterator();  
	    while(it.hasNext()) {  
	    	userInfo=it.next();
	    }  
	    if(userInfo.getBirthday()==null||userInfo.getBirthday().trim().equals("")){
	    	System.out.println("birthday is null");
	    }else{
	    	System.out.println("----------\n"+userInfo.getBirthday());
	    	String[] birthday=new String[2];
		    birthday=MyStringUtil.getBirthdayYMD(userInfo.getBirthday());
		    birthdayYear=birthday[0];
		    birthdayMonth=birthday[1];
		    birthdayDay=birthday[2];
	    }
	    
	    if(userInfo.getAddress()==null||userInfo.getAddress().trim().equals("")){
	    	System.out.println("address is null");
	    }else{
	    	System.out.println("----------\n"+userInfo.getAddress());
	    	String[] addresses=new String[2];
	 	    addresses=MyStringUtil.getAddressSCQ(userInfo.getAddress());
	 	    provinces=addresses[0];
	 	    city=addresses[1];
	 	    district=addresses[2];
	    }
	    
	    //显示用户全部勋章
	    badges=user.getBadges();
	    
	    try{
	    	
	    	rank=accountManager.getUserRankBySql(sessionUser.getId());
	    	
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    
	    
		return "InfoSuccess";
		}
	
	//浏览他人的资料
	public String showUserinfo(){
	    
	    //需要get ohers吗
	    user=accountManager.findUserById(new Long(userId));
	    
	    Iterator<AcctUserInfo> it = user.getAcctUserInfo().iterator();  
	    while(it.hasNext()) {  
	    	userInfo=it.next();
	    }  
	    if(userInfo.getBirthday()==null||userInfo.getBirthday().trim().equals("")){
	    	System.out.println("birthday is null");
	    }else{
	    	System.out.println("----------\n"+userInfo.getBirthday());
	    	String[] birthday=new String[2];
		    birthday=MyStringUtil.getBirthdayYMD(userInfo.getBirthday());
		    birthdayYear=birthday[0];
		    birthdayMonth=birthday[1];
		    birthdayDay=birthday[2];
	    }
	    
	    if(userInfo.getAddress()==null||userInfo.getAddress().trim().equals("")){
	    	System.out.println("address is null");
	    }else{
	    	System.out.println("----------\n"+userInfo.getAddress());
	    	String[] addresses=new String[2];
	 	    addresses=MyStringUtil.getAddressSCQ(userInfo.getAddress());
	 	    provinces=addresses[0];
	 	    city=addresses[1];
	 	    district=addresses[2];
	    }
	    
	    //显示用户全部勋章
	    badges=user.getBadges();
	    
	    try{
	    	
	    	rank=accountManager.getUserRankBySql(new Long(userId));
	    	
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    
	    
		return "showUserInfoSuccess";
	}
	
	
	public String saveInfo(){
		
		userInfo=accountManager.findUserInfoById(new Long(userInfoId));
		
		//性别
		if(sexInfo==null||sexInfo.trim().equals("")){
			
		}else{
			userInfo.setSex(Integer.parseInt(sexInfo));
		}
		
		//生日
		if(birthdayYear.trim().equals("--")||birthdayMonth.trim().equals("--")||birthdayDay.trim().equals("--")){
			System.out.println("生日为空");
		}else if(birthdayYear.trim().equals("0")||birthdayMonth.trim().equals("0")||birthdayDay.trim().equals("0")){
			System.out.println("生日为空2");
		}
		else{
			String[] birthdays=new String[3];
			birthdays[0]=birthdayYear;
			birthdays[1]=birthdayMonth;
			birthdays[2]=birthdayDay;
			
			String birthdayMerge=MyStringUtil.getFullBirthday(birthdays);
			
			userInfo.setBirthday(birthdayMerge);
		}
		
		//城市		
		if(provinces.equals("—— 省 ——")||city.equals("—— 市 ——")||district.equals("—— 区 ——")){
			System.out.println("城市为空");
		}else if(provinces.equals("")||city.equals("")||district.equals("")){
			System.out.println("城市为空2");
		}else{
			String[] addresses=new String[3];
			addresses[0]=provinces;
			addresses[1]=city;
			addresses[2]=district;
					
			String addressMerge=MyStringUtil.getFullAddress(addresses);
					
			userInfo.setAddress(addressMerge);
		}
		
		//工作
		if(jobInfo==null||jobInfo.trim().equals("")){
			userInfo.setJob("");
		}else{
			userInfo.setJob(jobInfo);
		}
		
		//qq
		if(qqInfo==null||qqInfo.trim().equals("")){
			userInfo.setQq("");
		}else{
			userInfo.setQq(qqInfo);
		}

		//签名
		if(signatureInfo==null||signatureInfo.trim().equals("")){
			userInfo.setSignature("");
		}else{
			userInfo.setSignature(signatureInfo);
		}
		
		accountManager.saveUserInfo(userInfo);
		
		
		return "InfoSaveSuccess";
	}

	public AcctUser getUser() {
		return user;
	}

	public void setUser(AcctUser user) {
		this.user = user;
	}

	public AcctUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(AcctUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	public String getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public String getBirthdayMonth() {
		return birthdayMonth;
	}

	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public String getBirthdayDay() {
		return birthdayDay;
	}

	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getSexInfo() {
		return sexInfo;
	}

	public void setSexInfo(String sexInfo) {
		this.sexInfo = sexInfo;
	}

	public String getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}

	public String getSignatureInfo() {
		return signatureInfo;
	}

	public void setSignatureInfo(String signatureInfo) {
		this.signatureInfo = signatureInfo;
	}

	public String getQqInfo() {
		return qqInfo;
	}

	public void setQqInfo(String qqInfo) {
		this.qqInfo = qqInfo;
	}

	public synchronized int getRank() {
		return rank;
	}

	public synchronized void setRank(int rank) {
		this.rank = rank;
	}

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}

	public BbsManager getBbsManager() {
		return bbsManager;
	}

	@Autowired
	public void setBbsManager(BbsManager bbsManager) {
		this.bbsManager = bbsManager;
	}

	public Set<Badge> getBadges() {
		return badges;
	}

	public void setBadges(Set<Badge> badges) {
		this.badges = badges;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
