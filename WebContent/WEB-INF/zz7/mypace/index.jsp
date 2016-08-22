<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Springbbs</title>
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/bbs.css" rel="stylesheet">

<script src="${ctx}/js/index.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container-fluid">
	
	<div class="row headerBorder">
	<div class="col-md-12">
	<%@ include file="/common/header.jsp"%>
	</div>
	</div>
	
	<div class="row centerPanel"> 
	<div class="col-md-3"></div>
	<div class="col-md-6 border-md6">
		<div class="row bbs-top">
			<div class="col-md-12 background-title-color">
			
			<div class="btn-group">
			<label class="label-all"><a class="ahome" href="${ctx }/mypace/bbs/topic.action" target="_self">全部</a></label>
			<label class="label-hot"><a class="ahot" href="#">热门</a></label>
			<label class="label-label" data-toggle="dropdown">
     		<a class="alabel" href="#">标签</a><span class="caret"></span>
  			</label>
   				<ul class="dropdown-menu" role="menu">
   					<s:iterator value="#request.nodes" var="node">
      				<li><a class="nodeAIndex" href="#"><s:property value='#node.name'/></a>
      				<input name="hiddenNodeIndexValue" type="hidden" value='<s:property value="#node.id"/>'></li>
      				</s:iterator>
   				</ul>
			</div>
			
			<div class="bbsNewTopic pull-right"><a href="${ctx }/mypace/bbs/topic!createNewTopic.action" target="_blank" class="whitez"><span class="glyphicon glyphicon-file"></span>&nbsp;发帖</a></div>
			</div>
		</div>
		<s:iterator value="#request.page.result" var="topic">
		<div class="row bbs-center-border">
			<div class="col-md-2 bbs-icons-spring"><img alt="" src="${ctx }/images/icons/huoyan.fw.png"></div>
			
			<div class="col-md-10 bbs-text-center">
			<div>
			<s:if test='#request.topic.isTop=="500"'>
			<button class="btn btn-warning btn-sm">置顶</button>
			</s:if>
			<button class="btn btn-info btn-sm nodeTypeBtn"><s:property value="#topic.node.name"/></button>
			<input type="hidden" class="nodeInputHidden" value="<s:property value='#topic.node.id'/>"/>
			&nbsp;<a href='${ctx }/mypace/bbs/topic!showTopic.action?viewTopicId=<s:property value="#topic.id"/>' target="_blank"><span class="bbstitle"><s:property value="#topic.titleFormat()"/></span></a>
			</div>
			<div class="divheight5"></div>
			<div class="dateStyle"><span class="bbsCreateUser"><s:property value="#topic.acctuser.name"/></span>&nbsp;<s:property value="#topic.createTimeToString()"/>
			<span class="spansmail">|</span>&nbsp;<s:property value="#topic.commentCount"/>&nbsp;回复
			&nbsp;<span class="spansmail">|</span>
			<s:if test="#topic.lastacctuser!=null">
			&nbsp;最后回应&nbsp;<span class="bbsCreateUser"><s:property value="#topic.lastacctuser.name"/></span>&nbsp;<s:property value="#topic.lastCommentAtToString()"/>
			&nbsp;
			</s:if>
			</div>
			</div>
		</div>
		</s:iterator>
		<div class="row nopaddingmargin">
			<div class="col-md-12 bbs-bottom bbs-bottom-posi">
			<div class="row">
				<div class="col-md-9">
				<div class="btn-group padingtop30">
					<button type="button" class="btn btn-default" name="btnFirstPage">first</button>
					<s:if test="page.hasPre">
  					<button type="button" class="btn btn-default" name="btnPrePage">←</button>
  					</s:if>
  					<s:else>
　					<button type="button" class="btn btn-default" name="btnPrePage" disabled>←</button>
					</s:else>
  					<button type="button" class="btn btn-default" name="btnPageNo">1</button>
  					<s:if test="page.hasNext">
   					<button type="button" class="btn btn-default" name="btnNextPage">→</button>
   					</s:if>
   					<s:else>
　					<button type="button" class="btn btn-default" name="btnNextPage" disabled>→</button>
					</s:else>
					<button type="button" class="btn btn-default" name="btnLastPage">last</button>
				</div>
				</div>
				<div class="col-md-3">
					<s:if test='#request.page.totalCount!=0'>
					 <div class="input-group margin-right-10">
         			<span class="input-group-addon">共${page.totalPages}页</span>
         			<input id="goNumber" type="text" class="form-control">
         			<span id="btnGo" class="input-group-addon">go</span>
      				</div>
					</s:if>
				</div>
			</div>
			</div>
		</div>
	</div>
	<div class="col-md-3">
	</div>
	</div>
	<div class="row headerBorder">
	<div class="col-md-12">
	<%@ include file="/common/footer.jsp"%>
	</div>
	</div>
</div>
<form id="topicForm">
	<input type="hidden" id="ctx" value="${ctx}" /> 
	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
	<input type="hidden" name="hiddenPrePage" value="${page.prePage}"/>
	<input type="hidden" name="hiddenNextPage" value="${page.nextPage}"/>
	<input type="hidden" name="hiddenLasttPage" value="${page.totalPages}"/>
</form>
<form id="showNodeTopicForm" action="${ctx}/mypace/bbs/topic!showNodeTopic.action" method="post">
	<input type="hidden" name="nodeValue" id="nodeIndexValue" value="${nodeValue}"/>
</form>
<script type="text/javascript">
$(document).ready(
		function() {
			$('.nodeAIndex').each(
					function() {
						$(this).click(
								function() {
									
									var hiddenNodeIndex = $(this).next(
											"input[name='hiddenNodeIndexValue']");
									var nodeIndexValueInput=
											$("input[id='nodeIndexValue']");
									nodeIndexValueInput.val(hiddenNodeIndex.val());
									//alert(nodeIndexValueInput.val());
									//alert(hiddenNodeIndex.val());
									
									var frm = $("#showNodeTopicForm");
									 
									 frm.submit();
								})

			})
  	})
  </script>
</body>
</html>