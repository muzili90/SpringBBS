<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>重置密码</title>
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="${ctx}/bootstrap/bootstrapvalidator/css/bootstrapValidator.min.css" />

<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<script
	src="${ctx}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/register.css" rel="stylesheet">

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

		<form class="form-horizontal" action="${ctx}/mypace/account/forget!resetPassword.action" method="post">
			<div class="row">
				<div class="col-md-12 rowheight150"></div>
			</div>
			<div class="center">
				<div class="row" style="margin-top:30px;">
					<div class="form-group">
						<label class="col-md-3 control-label">用户名</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="username"
								name="userName" value="${userName}" readonly="readonly"/>
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<label class="col-md-3 control-label">新密码</label>
						<div class="col-md-4">
							<input type="password" class="form-control" name="password"
								id="password" />
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<label class="col-md-3 control-label">确认密码</label>
						<div class="col-md-4">
							<input type="password" class="form-control" name="repassword" id="repassword"/>
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<button class="btn btn-default" type="submit">修改</button>
					</div>
					<div class="col-md-5"></div>
				</div>
			</div>
		</form>
		</div>
		
		<div class="col-md-2"></div>
		
		</div>
		
	</div>
	<script type="text/javascript">
	$(document)
	.ready(
			function() {
				$('.form-horizontal')
						.bootstrapValidator(
								{
									message : 'This value is not valid',
									feedbackIcons : {
										valid : 'glyphicon glyphicon-ok',
										invalid : 'glyphicon glyphicon-remove',
										validating : 'glyphicon glyphicon-refresh'
									},
									fields : {
										password : {
											validators : {
												notEmpty : {
													message : '密码不能为空'
												},
												stringLength : {
													min : 6,
													max : 18,
													message : '密码长度为6到18位'
												},
												identical : {
													field : 'repassword',
													message : '密码不一致'
												}
											}
										},
										repassword : {
											validators : {
												notEmpty : {
													message : '密码不能为空'
												},
												stringLength : {
													min : 6,
													max : 18,
													message : '密码长度为6到18位'
												},
												identical : {
													field : 'password',
													message : '密码不一致'
												}
											}
										}
									}
								});
			});
	</script>
</body>
</html>