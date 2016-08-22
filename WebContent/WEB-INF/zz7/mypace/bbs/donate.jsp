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

<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/donate.css" rel="stylesheet">

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
							<div class="donate">
								<img title="感谢您的支持" src="${ctx}/images/zfb/muzili90zfbSmall.png"/>
							</div>
							<div class="row donateInfo">
								<div class="col-md-4">捐赠人</div>
								<div class="col-md-4">捐款</div>
								<div class="col-md-4">时间</div>
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