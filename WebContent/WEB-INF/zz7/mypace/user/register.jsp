<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>register</title>
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
		//alert("Hello World!"); 

		/* $("#username").on("blur", function(e) {
			getUniqueUserNameAjax();
		}); */

		/* function getUniqueUserNameAjax() {

			var $userNameInput = $("#username");
			var userName = $userNameInput.val();

			if ($.trim(userName) == "") {

				return;
			}

			$.ajax({
				url : "user!getUserNamelAjax.action",
				type : "GET",
				data : "uniqueUserName=" + userName,
				success : function(data, textStatus) {
					//alert(data);
					if (data == "false") {
						$userNameInput.siblings(
								".help-block.with-errors").html(
								"用户名已被注册!");
					} else {
						$userNameInput.siblings(
								".help-block.with-errors").html("");
					}

				}
			});
		}
		 */
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

		<form class="form-horizontal" action="user!register.action" method="post">
			<div class="row">
				<div class="col-md-12 rowheight150"></div>
			</div>
			<div class="center">
				<div class="row" style="margin-top:30px;">
					<div class="form-group">
						<label class="col-md-3 control-label">用户名</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="username"
								name="username" value="${user.loginName}" />
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<label class="col-md-3 control-label">密码</label>
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
							<input type="password" class="form-control" name="repassword" />
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<label class="col-md-3 control-label">Email</label>
						<div class="col-md-4">
							<input type="text" class="form-control" name="email"
								id="email" value="${user.email}" />
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<button class="btn btn-default" type="submit">注册</button>
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
													username : {
														message : 'The username is not valid',
														validators : {
															notEmpty : {
																message : '用户名不能为空'
															},
															callback : {

																message : '用户名已经被占用',
																callback : function(
																		value,
																		validator) {
																	var res = true;

																	var $userNameInput = $("#username");
																	var userName = $userNameInput
																			.val();

																	$.ajax({
																				url : 'user!getUserNamelAjax.action',
																				type : 'post',
																				dataType : 'json',
																				async : false,
																				data : {
																					uniqueUserName : userName,
																				},
																				success : function(
																						data) {
																					if (data.status != 'success') {
																						res = false;
																					}

																					if ($
																							.trim(data) == "true") {
																						//alert(data);
																						res = true;
																					} else if ($
																							.trim(data) == "false") {
																						res = false;
																					}
																				}
																			});

																	return res;
																}
															},
															stringLength : {
																min : 6,
																max : 18,
																message : '用户名长度为6到18位'
															},
															regexp : {
																regexp : /^[a-zA-z][a-zA-Z0-9_]{5,17}$/,
																message : '用户名由字母数字组成不能以数字开头'
															}
														}
													},
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
													},
													email : {
														validators : {
															notEmpty : {
																message : '邮箱地址不能为空'
															},
															callback : {

																message : '邮箱已经被占用',
																callback : function(
																		value,
																		validator) {
																	var res = true;

																	var $emailInput = $("#email");
																	var email = $emailInput
																			.val();

																	$.ajax({
																				url : 'user!getEmailAjax.action',
																				type : 'post',
																				dataType : 'json',
																				async : false,
																				data : {
																					uniqueEmail : email,
																				},
																				success : function(
																						data) {
																					if (data.status != 'success') {
																						res = false;
																					}

																					if ($
																							.trim(data) == "true") {
																						//alert(data);
																						res = true;
																					} else if ($
																							.trim(data) == "false") {
																						res = false;
																					}
																				}
																			});

																	return res;
																}
															},
															emailAddress : {
																message : '请输入正确的邮箱格式'
															}
														}
													}
												}
											});
						});
	</script>
</body>
</html>