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
<link href="${ctx}/css/register.css" rel="stylesheet">

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
			<div class="col-md-8">

		<form id="loginForm" class="form-horizontal" action="${ctx}/j_spring_security_check" method="post">
			<div class="row">
				<div class="col-md-12 rowheight150"></div>
			</div>
			
			<div class="center">
				<div class="row">
					<div class="col-md-12">
						${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }
					</div>
				</div>
				<div class="row" style="margin-top:30px;">
					<div class="form-group">
						<label class="col-md-3 control-label">用户名或邮箱</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="j_username"
								name="j_username" placeholder="用户名或者邮箱" />
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<label class="col-md-3 control-label">密码</label>
						<div class="col-md-4">
							<input type="password" class="form-control" name="j_password" placeholder="密码"
								id="j_password" />
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group">
					<div class="col-md-3"></div>
					<div class="col-md-4">
					<label class="control-label" for="j_captcha"> 验证码: </label>
					&nbsp;
					<input type='text' name='j_captcha' class="required inputWidth"  
                        size='5' />  
                        &nbsp;
                        <img id="imageF" src="<c:url value="/util/verify/image.jsp"/>" />  
                        &nbsp;
                    <span id="flashImage" style="font-size: 12px;"><a href="#">看不清?</a></span>
						&nbsp;
					<span id="forgetSpan" style="font-size: 12px;"><a href="${ctx}/mypace/account/forget!forget.action">忘了?</a></span>
					</div>
					<div class="col-md-5">
						
					</div>
					</div>
				</div>
				<div class="row">
					
					<div class="col-md-3"></div>
					<script type="text/javascript">
					</script>
					<div class="col-md-4"><span id="msgg" style="color:#89503F; margin-left:12px;"><s:property value="#request.msgg"/></span></div>
					<div class="col-md-3"></div>
				</div>
				
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-4">
						<button class="btn btn-default buttonWidth" type="submit">登录</button>
						&nbsp;
						<button id="registerBtn" class="btn btn-default pull-right buttonWidth" type="button">注册</button>
						<!-- <input class="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>记住我  -->
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
							//
							//
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
													j_username : {
														message : 'The username is not valid',
														validators : {
															notEmpty : {
																message : '用户名或邮箱不能为空'
															},
															stringLength : {
																min : 6,
																max : 18,
																message : '用户名长度为6到18位'
															},
															callback : {
																message : '用户名或邮箱格式错误',
																callback : function(
																		value,
																		validator) {
																	var res = false;
																	if (value
																			.match(/^[a-zA-z][a-zA-Z0-9_]{5,17}$/) || value
																			.match(/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/)) {
																		res=true;
																	}
																	return res;
																}
															},
														}
													},
													j_password : {
														validators : {
															notEmpty : {
																message : '密码不能为空'
															},
															stringLength : {
																min : 6,
																max : 18,
																message : '密码长度为6到18位'
															}
														}
													}
												}
											});
							//
							$('#registerBtn').click(function(){
								var ctx=$("#ctx").val();
								window.location.href=ctx+"/mypace/account/user!singup.action";
							})
							$("#flashImage").click(function(){
								///util/verify/image.jsp
								//imageF
								//flashImage
								
								//alert("@");
								var ctx=$("#ctx").val();
								var zdatetime="?code="+new Date().getTime();
								$("#imageF").attr("src",ctx+"/util/verify/image.jsp"+zdatetime);
							})
						
							//
							//
						});
	</script>
	<input type="hidden" id="ctx" value="${ctx}" />
</body>
</html>