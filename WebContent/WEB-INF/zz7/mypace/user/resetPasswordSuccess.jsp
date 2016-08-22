<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>重置密码成功</title>
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
			
			

		<div class="row">
				<div class="col-md-12 rowheight150"></div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="reset-table-main">
						重置密码
					</div>
				</div>
			</div>
			<div class="center">
				<div class="row" style="margin-top:30px;">
					<div class="form-group">
							<div class="errorMsg" style="margin-left:100px">${session.tipMsg}</div>
						</div>
						<div class="col-md-5"></div>
					</div>
				</div>
			
		</div>
		<div class="col-md-2"></div>
		</div>
		
		</div>
		
	<script type="text/javascript">
	</script>
</body>
</html>