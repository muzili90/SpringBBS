<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@ page
	import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"%>
<%@ page import="org.springframework.security.web.WebAttributes"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Mine</title>
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="${ctx}/bootstrap/bootstrapvalidator/css/bootstrapValidator.min.css" />

<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<script
	src="${ctx}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/userinfo.css" rel="stylesheet">

<script type="text/javascript" src="${ctx}/js/mypace/birthday.js"></script>
<script type="text/javascript" src="${ctx}/js/mypace/userinfo.js"></script>
<script type="text/javascript" src="${ctx}/js/mypace/badge.js"></script>
<script src="${ctx}/bootstrap/distpicker/distpicker.data.js"></script>
<script src="${ctx}/bootstrap/distpicker/distpicker.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container-fluid">
	
		<div class="row headerBorder">
		<div class="col-md-12">
		<%@ include file="/common/header.jsp"%>
		</div>
		</div>
		
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-7">
				<div class="row">
					<div class="col-md-4">
						<div class="infoLeft">
							<div class="infoLeve infoBorder infoMarginTop">
							等级信息
							<div class="imgLevel"><img src='${ctx }/mypace/bbs/showlevelimg!showLevelImg.action?userLevel=<s:property value="#request.user.user_level"/>'/></div>
							<div class="experienceDiv">
							经验:&nbsp;<s:property value="#request.user.experience"/>
							</div>
							<div class="rankDiv">
							排名:&nbsp;<s:property value="#request.rank"/>
							</div>
							</div>
							
							<div class="infoBedge infoBorder infoMarginTop">
							徽章信息&nbsp;&nbsp;<span class="getBadgeText"><a href="${ctx }/mypace/bbs/badge!info.action" target="_blank">[领取徽章]</a></span>
							<div>
								<s:iterator value="#request.badges" var="badge">
								<div class="badgeSmallImgDiv pull-left">
									<img title=<s:property value="#badge.name"/> src='${ctx}/<s:property value="#badge.url"/>'/>
									<div class="getBadgeText">
									<s:if test="#request.user.loginName==#session.user.loginName">
									<button class="disableBadgeBtn btn btn-sm btn-default">隐藏</button>
									</s:if>
									<input type="hidden" name="hiddenBadgeId" value='<s:property value="#badge.id"/>'/>
									</div>
								</div>
							</s:iterator>
							</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-8">
						<div class="infoRight infoBorder infoMarginTop">
							<div class="baseInfoTitle">
								<span class="textBold">基本信息</span>
									<div class="pull-right">
									<s:if test="#request.user.loginName==#session.user.loginName">
										<button id="btnEdit" class="btn btn-default btn-warning btn-sm btnEdit">编辑</button>
									</s:if>
									
									</div>
								<hr>
							</div>
						
							<div class="baseInfoText">
								<div class="row">
									<div class="col-md-2">
										
										<div class="baseInfoTextDiv">登录名</div>
										<div class="baseInfoTextDiv">性别</div>
										<div class="baseInfoTextDiv">生日 </div>
										<div class="baseInfoTextDiv">职业</div>
										<div class="baseInfoTextDiv">所在地</div>
										<div class="baseInfoTextDiv">QQ</div>
										<div class="baseInfoTextDiv">签名</div>
										<div class="baseInfoTextDiv">邮箱</div>
										<div class="baseInfoTextDiv">注册时间</div>
									</div>
									<div class="col-md-10 baseInfoInput">
										
										<div class="infoTextValueControl">
										
										<div class="baseInfoTextDivValue"><s:property value="#request.user.loginName"/>
											<s:if test="#request.user.loginName==#session.user.loginName">
											<span style="color:red">&nbsp;&nbsp;&nbsp;<a href="${ctx }/mypace/account/user!resetPasswordForm.action">修改密码</a></span>
											</s:if>
										</div>
										
										<div class="baseInfoTextDivValue">
										<s:if test='#request.userInfo.sex==null'>&nbsp;</s:if>
										<s:elseif test='#request.userInfo.sex==0'>男</s:elseif>
										<s:elseif test="#request.userInfo.sex==1">女</s:elseif>
										</div>
										
										<div class="baseInfoTextDivValue"><s:property value="#request.userInfo.birthday"/>&nbsp; </div>
										<div class="baseInfoTextDivValue"><s:property value="#request.userInfo.job"/>&nbsp;</div>
										<div class="baseInfoTextDivValue"><s:property value="#request.userInfo.address"/>&nbsp;</div>
										<div class="baseInfoTextDivValue"><s:property value="#request.userInfo.qq"/>&nbsp;</div>
										<div class="baseInfoTextDivValue"><s:property value="#request.userInfo.signature"/>&nbsp;</div>
										<div class="baseInfoTextDivValue"><s:property value="#request.user.email"/>&nbsp;</div>
										<div class="baseInfoTextDivValue"><s:property value="#request.user.createTimeToString2()"/></div>
										
										</div>
										
										<form action="${ctx}/mypace/bbs/userinfo!saveInfo.action" method="post">
										<div class="infoInputValueControl">
											<div class="inputBaseInfoTextDivValue"><s:property value="#request.user.loginName"/></div>
											<div class="inputBaseInfoTextDivValue">
											    <s:if test="#request.userInfo.sex==null">
												男&nbsp;<input name="sexInfo" type="radio" value="0" />
												女&nbsp;<input name="sexInfo" type="radio" value="1" />
												</s:if>
												<s:elseif test="#request.userInfo.sex==0">
												男&nbsp;<input name="sexInfo" type="radio" value="0" checked />
												女&nbsp;<input name="sexInfo" type="radio" value="1" />
												</s:elseif>
												<s:elseif test="#request.userInfo.sex==1">
												男&nbsp;<input name="sexInfo" type="radio" value="0" />
												女&nbsp;<input name="sexInfo" type="radio" value="1" checked />
												</s:elseif>
											</div>
											
											<div class="inputBaseInfoTextDivValue">
												<select id="select_year2" name="birthdayYear" rel="<s:property value='#request.birthdayYear'/>"></select>年
                    							<select id="select_month2" name="birthdayMonth" rel="<s:property value='#request.birthdayMonth'/>"></select>月
                    							<select id="select_day2" name="birthdayDay" rel="<s:property value='#request.birthdayDay'/>"></select>日
											</div>
											
											<div class="inputBaseInfoTextDivValue">
												<input name="jobInfo" type="text" class="inputJobAndQQTextWidth" value="<s:property value='#request.userInfo.job'/>">
												<div id="jobMsg"></div>
											</div>
											
											<div class="inputBaseInfoTextDivValue">
												<div id="distpicker" data-toggle="distpicker">
  													<select name="provinces" data-province="<s:property value='#request.provinces'/>"></select>
  													<select name="city" data-city="<s:property value='#request.city'/>"></select>
  													<select name="district" data-district="<s:property value='#request.district'/>"></select>
												</div>
											</div>
											
											<div class="inputBaseInfoTextDivValue">
												<input name="qqInfo"type="text" class="inputJobAndQQTextWidth"  value="<s:property value='#request.userInfo.qq'/>">
												<div id="qqMsg"></div>
											</div>
											
											<div class="inputBaseInfoTextDivValue">
												<input name="signatureInfo" type="text" class="inputSignatureTextWidth" value="<s:property value='#request.userInfo.signature'/>">
												<div id="signatureMsg"></div>
											</div>
											
											<div class="baseInfoTextDivValue">
												<s:property value='#request.user.email'/>
											</div>
											
											<div class="baseInfoTextDivValue">
												<s:property value='#request.user.createTimeToString2()'/>
											</div>
											
											<div class="baseInfoTextDivValue">
											<div>&nbsp;</div>
												<input id="btnSubmit" type="submit" class="btn btn-info" value="确定"/>
												<input id="btnCancel"type="button" class="btn btn-info" value="取消"/>
											</div>
											
										</div>
										<input type="hidden" name="userInfoId" value="<s:property value='#request.userInfo.id'/>"/>
										</form>
									</div>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		
		<div class="row headerBorder">
			<div class="col-md-12">
				<%@ include file="/common/footer.jsp"%>
			</div>
		</div>
		
	</div>
	
	<input type="hidden" id="ctx" value="${ctx}" />
</body>
</html>