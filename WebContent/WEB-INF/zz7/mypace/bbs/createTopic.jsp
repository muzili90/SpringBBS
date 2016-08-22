<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Springbbs</title>
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="${ctx}/bootstrap/bootstrapvalidator/css/bootstrapValidator.min.css" />

<link media="all" rel="stylesheet" type="text/css" href="${ctx}/bootstrap/simditor/styles/fonticon.scss" />
<link media="all" rel="stylesheet" type="text/css" href="${ctx}/bootstrap/simditor/styles/simditor.css" />

<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<script
	src="${ctx}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>

<script type="text/javascript" src="${ctx}/bootstrap/simditor/scripts/module.min.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap/simditor/scripts/hotkeys.min.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap/simditor/scripts/simditor.js"></script>

<script src="${ctx}/js/mypace/bbs.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/bbs.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container-fluid">
	
	<div class="row headerBorder">
	<div class="col-md-12">
	<%@ include file="/common/header.jsp"%>
	</div>
	</div>
	
	<div class="row bbsCreateCenterPanel"> 
	<div class="col-md-3"></div>
	<form class="formz" action="${ctx}/mypace/bbs/topic!saveTopic.action" method="post">
	<div class="col-md-6 bbsCreate-border-md6">
		<div class="row">
		<div class="col-md-12 bbs-top background-title-color">发布帖子</div>
		</div>
		<div class="row">
		<div class="col-md-11 form-group bbs-createTopic-title">
			<div class="form-group">
				<div class="col-md-12 nopaddingmargin">
					<div class="input-group">
         				<span class="input-group-addon noRadius">标题</span>
         				<input type="text" name="topic.title" class="form-control bbsnoborderText noRadius">
      				</div>
				</div>
		</div>
		</div>
		<div class="col-md-1"></div>
		</div>
		<div class="row">
		<div class="col-md-11 margin-left30">
		
		<div class="btn-group">
   			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
     		选择分类 <span class="caret"></span>
  			</button>
   				<ul class="dropdown-menu" role="menu">
   					<s:iterator value="#request.nodes" var="node">
      				<li><a class="nodeA" href="#"><s:property value='#node.name'/></a><input name="hiddenNodeValue" type="hidden" value='<s:property value="#node.id"/>'></li>
      				</s:iterator>
   				</ul>
		</div>
		
		<div id="bbs-error" class="pull-right bbs-error"></div>
		
		&nbsp;&nbsp;&nbsp;<span id="nodeSpan"></span>
		<input type="hidden" name="nodeValue">
		
		</div>
		<div class="col-md-1"></div>
		</div>
		<div class="row">
		<div class="col-md-11 bbs-createTopic-context">
			<div class="divSimditor"><textarea class="ztextarea" id="editor" name="topic.content"></textarea></div>
		</div>
		<div class="col-md-1"></div>
		</div>
		<div class="row">
		<div class="col-md-11 bbs-createTopic-bottom nopadding">
			<button id="bbscreateTopicButton" name="submit" type="submit" class="btn btn-default">发表</button>
			<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);">取消</button>
		</div>
		<div class="col-md-1"></div>
		</div>
	</div>
	</form>
	<div class="col-md-3">
	</div>
	</div>
	
	<div class="row headerBorder">
	<div class="col-md-12">
	<%@ include file="/common/footer.jsp"%>
	</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(
		function() {
  		
  		toolbar = ['bold', 'italic', 'underline', 'strikethrough',
					'color', 'ol', 'ul', 'blockquote', 'code',
					'link', 'hr', 'indent', 'outdent' ];
		var editor = new Simditor( {
			textarea : $('#editor'),
			placeholder : '这里输入内容...',
			//cleanPaste: false, //支持复制黏贴,
			toolbar : toolbar
		});
  	})
  </script>
</body>
</html>