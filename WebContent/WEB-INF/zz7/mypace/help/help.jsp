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
						<div class="infoMarginTop infoMarginBottom infoHelpDiv">
							<div class="helpFont24PX">springbbs 帮助</div>
							<div class="helpFont14PX">
							<br/>
							SpringBBS是以Spring、Struts2和hibernate技术开发的开源论坛项目。整个项目构建在(SpringSide3)开源框架之上。
							</div>
							<div class="helpFont18PX">
							<br/>
							SpringBBS使用到的技术
							</div>
							<div>
							<br/><br/>
							JavaWeb框架
							</div>
							<div class="colorBlue">
							<br/>
							<a href="#">Spring3</a>
							<br/>
							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a href="#">SpringSecurity</a>
							<br/>
							<br/>
							<a href="#">Struts2</a>
							<br/>
							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a href="#">convention-plugin</a>
							<br/>
							<br/>
							<a href="#">Hibernate3</a>
							<br/>
							<br/>
							<a href="http://www.darkmi.com/man/SpringSide3/"  target="_blank">SpringSide3</a>
							</div>
							<br/>
							<br/>
							Web前端
								<div class="colorBlue">
								<br/>
								<a href="#">Jquery</a>
								<br/>
								<br/>
								<a href="#">BootStrap</a>
								<br/>
								<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a href="#">BootStrapValidator</a>
								<br/>
								<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a href="#">Simditor</a>
								<br/>
								<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <a href="#">SyntaxHighlighter</a>
								</div>
								<br/>
								<br/>
							数据库
							<br/>
							<div class="colorBlue">
								<br/>
								<a href="#">MySQL</a>
							</div>
							<br/>
								<br/>
							测试
							<br/>
							<div class="colorBlue">
								<br/>
								<a href="#">JUnit 4</a>
							</div>
							<br/>
							<br/>
							开源地址
							<br/>
							<div class="colorBlue">
								<br/>
								<a href="https://github.com/muzili90/SpringBBS.git" target="_blank">https://github.com/muzili90/SpringBBS.git</a>
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