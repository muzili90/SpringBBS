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

<script type="text/javascript" src="${ctx}/js/mypace/badge.js"></script>

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
						<div class="infoBorder infoMarginTop infoMarginBottom infoBadgeDiv">
							<div class="badgeInfoText">徽章信息</div>
							<s:iterator value="#request.badges" var="badge">
								<div class="badgeFullImgDiv pull-left">
									<img title=<s:property value="#badge.name"/> src='${ctx}/<s:property value="#badge.full_url"/>'/>
									<div class="getBadgeText">
									<button class="getBadgeBtn btn btn-sm btn-success">领取</button>
									<input type="hidden" name="hiddenBadgeId" value='<s:property value="#badge.id"/>'/>
									</div>
									<div class="BadgeAlt"><s:property value="#badge.alt"/></div>
								</div>
							</s:iterator>
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