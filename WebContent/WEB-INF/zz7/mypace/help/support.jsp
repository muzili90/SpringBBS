<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Mine</title>
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/help.css" rel="stylesheet">

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
					<div class="col-md-12">
						<div class="infoMarginTop infoMarginBottom infoHelpDiv2">
							<div class="helpFont24PX">springbbs Support</div>
							<div class="helpFont14PX">
							<br/>
							SpringBBS是以Spring、Struts2和hibernate技术开发的开源论坛项目。整个项目构建在(SpringSide3)开源框架之上。
							</div>
								<div>
								<br/>
								SpringBBS完成了论坛的所有基础功能，包括用户管理(注册、登录以及用户资料等) ，帖子管理(发帖、回复、置顶等)以及一些附加功能。
								<br/>
								<br/>
								开发SpringBBS的最初动机是熟悉SSH开发，学习当下流行的JavaWeb开发技术。
								<br/>
								<br/>
								作为SpringBBS的第一个版本，项目仍有很多不足和错误，会在后续版本中陆续完善。
								<br/>
								<br/>
								欢迎你的加入！
								<br/>
								<br/>
								开源地址:     
								<a href="https://github.com/muzili90/SpringBBS.git" target="_blank">https://github.com/muzili90/SpringBBS.git</a>
								<br/>
								<br/>
								微博:     <a href="http://weibo.com/u/5571130417" target="_blank">http://weibo.com/u/5571130417</a>
								<br/>
								<br/>
								blog:     <a href="http://blog.csdn.net/muzili90s" target="_blank">http://blog.csdn.net/muzili90s</a>
								<br/>
								<br/>
								email:     muzili_90@foxmail.com
								
								</div>
							</div>
							<div>
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