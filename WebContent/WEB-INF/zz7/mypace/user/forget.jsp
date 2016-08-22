<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>找回密码</title>
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="${ctx}/bootstrap/bootstrapvalidator/css/bootstrapValidator.min.css" />

<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<script
	src="${ctx}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/register.css" rel="stylesheet">
<link href="${ctx}/css/forget.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
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
			<div class="col-md-8">
			
			

		<form class="form-horizontal" action="${ctx}/mypace/account/forget!forgetName.action" method="post">
			<div class="row">
				<div class="col-md-12 rowheight150"></div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="forget-table-main border1pxnobottom">
						<div class="pull-left forget-table1">1、验证用户名</div>
						<div class="pull-left forget-table2 forget-table2-red">2、验证电子邮件</div>
						<div class="pull-left forget-table3 forget-table3-red">3、邮件发送成功</div>
					</div>
				</div>
			</div>
			<div class="center">
				<div class="row" style="margin-top:30px;">
					<div class="form-group">
						<label class="col-md-3 control-label">用户名</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="username"
								name="userName" value="${userName}" />
							<div class="errorMsg">${session.errorMsg}</div>
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<button class="btn btn-default" type="submit">下一步</button>
					</div>
					<div class="col-md-5"></div>
				</div>
			</div>
		</form>
		</div>
		
		<div class="col-md-2"></div>
		
		</div>
		
	</div>
</body>
</html>