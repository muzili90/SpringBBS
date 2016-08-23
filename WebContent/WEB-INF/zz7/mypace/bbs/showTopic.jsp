<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Springbbs</title>
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link media="all" rel="stylesheet" type="text/css" href="${ctx}/bootstrap/simditor/styles/fonticon.scss" />
<link media="all" rel="stylesheet" type="text/css" href="${ctx}/bootstrap/simditor/styles/simditor.css" />
<link
	href="${ctx}/bootstrap/bootstrapvalidator/css/bootstrapValidator.min.css" />
<script src="${ctx}/bootstrap/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<script
	src="${ctx}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
	
<script type="text/javascript" src="${ctx}/bootstrap/simditor/scripts/module.min.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap/simditor/scripts/hotkeys.min.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap/simditor/scripts/simditor.js"></script>

<script type="text/javascript" src="${ctx}/bootstrap/syntaxhighlighter/scripts/shCore.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap/syntaxhighlighter/scripts/shBrushJava.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/bootstrap/syntaxhighlighter/styles/shCore.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/bootstrap/syntaxhighlighter/styles/shThemeDefault.css" />

<script type="text/javascript">
				//SyntaxHighlighter插件
				SyntaxHighlighter.defaults['toolbar'] = false;  //去掉右上角问号图标
				SyntaxHighlighter.config.tagName = 'code';       //可以更改解析的默认Tag。
				SyntaxHighlighter.config.bloggerMode = true; 
				SyntaxHighlighter.config.stripBrs = true; 
			
				SyntaxHighlighter.all();
				
</script>

<script src="${ctx}/js/mypace/bbs.js"></script>

<link href="${ctx}/css/index.css" rel="stylesheet">
<link href="${ctx}/css/bbs.css" rel="stylesheet">

<script src="${ctx}/js/mypace/commont.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container-fluid">
	
	<div class="row headerBorder">
	<div class="col-md-12">
	<%@ include file="/common/header.jsp"%>
	</div>
	</div>
	
	<!-- 帖子标题 -->
	<div class="row bbsViewTitlePanel"> 
	<div class="col-md-2"></div>
	<div class="col-md-8 bbsViewTitle-border-md6">
		<div class="row">
		<div class="col-md-12 bbs-top background-title-color"><span>标题:&nbsp;</span><span><s:property value='#request.topic.title'/></span></div>
		</div>

		<!-- 帖子分类 -->
		<div class="row">
		<div class="col-md-12 bbs-top-node">
		<span><s:property value='#request.topic.node.section.name'/></span>
		<span>></span>
		<span><s:property value='#request.topic.node.name'/></span>
		</div>
		</div>
		<!-- 帖子分类 -->
	</div>
	<div class="col-md-2">
	</div>
	</div>
	<!-- 帖子标题 -->
	<div class="row">
		<div class="col-md-12 blank20"></div>
	</div>
	
	<!-- 查看帖子 -->
	<s:if test='#request.commontPage.pageNo==1'>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8 bbs-top-time">
		
		<s:if test="#session.isAdmin==true">
		<span class="admin-manage">管理&nbsp;</span>
		<a href="${ctx }/mypace/bbs/communityadmin!top.action?topicId=<s:property value='#request.topic.id'/>"><span class="admin-manage">置顶&nbsp;</span></a>
		<a href="${ctx }/mypace/bbs/communityadmin!cancelTop.action?topicId=<s:property value='#request.topic.id'/>"><span class="admin-manage">取消置顶&nbsp;</span></a>
		<a href="${ctx }/mypace/bbs/communityadmin!deleteTopic.action?topicId=<s:property value='#request.topic.id'/>"><span class="admin-manage">删除&nbsp;</span></a>
		</s:if>
		
		<span>发表于:</span><span><s:property value='#request.topic.createTimeToString2()'/></span></div>
		<div class="col-md-2"></div>
	</div>
	
	<div class="row bbs-context-margin-bottom">
		<div class="col-md-2"></div>
		<div class="col-md-8 border1px">
		
		<div class="row">
			<div id="bbs-user-panel" class="col-md-3 bbs-user-panel bbs-panel-height">
				<div class="bbs-user-img"><img alt="" src="${ctx }/images/icons/huoyan.fwlg.jpg"></div>
				<div class="bbs-user-loginname"><span><a href="${ctx }/mypace/bbs/userinfo!showUserinfo.action?userId=<s:property value='#request.topic.acctuser.id'/>" target="_blank"><s:property value='#request.topic.acctuser.loginName'/></a></span></div>
				<div  class="bbs-user-level">
				<div class="levelText pull-left">等级:&nbsp;&nbsp;</div>
				<img class="imglevel" src='${ctx }/mypace/bbs/showlevelimg!showLevelImg.action?userLevel=<s:property value="#request.topic.acctuser.user_level"/>'/>
				<div class="badgeImg">
					<s:iterator value="topic.acctuser.badges" var="badge">
						<img title=<s:property value="#badge.name"/> src='${ctx}/<s:property value="#badge.url"/>'/>
					</s:iterator>
				</div>
				</div>
			</div>
			<div id="bbs-context-panel" class="col-md-9 bbs-context-panel">
			<%-- <s:property value='#request.topic.content'/> --%>
			${topic.content}
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="bbs-good-tread">
				<span id="topicAdZanOrCaiTip"></span>
				&nbsp;&nbsp;
				<span id="topicAdZanNum"><s:if test="#request.topic.topicAd==null||#request.topic.topicAd.isEmpty()">0</s:if>
				<s:else>
					<s:iterator value="#request.topic.topicAd"> 
						<s:property value='agreeCount'/>
					</s:iterator>
				</s:else>
				</span>
				<span id="topicAdZan" class="glyphicon glyphicon-thumbs-up"></span>
				&nbsp;&nbsp;|&nbsp;&nbsp;
				<span id="topicAdCai" class="glyphicon glyphicon-thumbs-down"></span>
				<span id="topicAdCaiNum"><s:if test="#request.topic.topicAd==null||#request.topic.topicAd.isEmpty()">0</s:if>
				<s:else>
					<s:iterator value="#request.topic.topicAd"> 
						<s:property value='dsagreeCount'/>
					</s:iterator>
				</s:else>
				</span>
				</div>
			</div>
		</div>
		
		</div>
		<div class="col-md-2"></div>
	</div>
	</s:if>
	<!-- 查看帖子 -->
	<!-- 历史回复 -->
	<s:iterator value="#request.commontPage.result" var="comment" status="statu" >
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8 bbs-top-time"><!-- 计算楼层 --><span class="pull-left"><s:property value='((#request.commontPage.pageNo-1)*9)+#statu.count'/>楼</span>回复于: <span><s:property value='#request.comment.createTimeToString2()'/></span></div>
		<div class="col-md-2"></div>
	</div>
	<div class="row bbs-reply-margin-bottom">
		<div class="col-md-2"></div>
		<div class="col-md-8 border1px">
		
		<div class="row">
			<div id="bbs-user-panel" class="col-md-3 bbs-user-panel bbs-panel-height">
				<div class="bbs-user-img"><img alt="" src="${ctx }/images/icons/huoyan.fwlg.jpg"></div>
				<div class="bbs-user-loginname"><a href="${ctx }/mypace/bbs/userinfo!showUserinfo.action?userId=<s:property value='#request.comment.acctuser.id'/>" target="_blank"><s:property value='#request.comment.acctuser.loginName'/></a></div>
				<div  class="bbs-user-level">
				<div class="levelText pull-left">等级:&nbsp;&nbsp;</div>
				<img class="imglevel" src='${ctx }/mypace/bbs/showlevelimg!showLevelImg.action?userLevel=<s:property value="#request.comment.acctuser.user_level"/>'/>
				
				<div class="badgeImg">
					<s:iterator value="#comment.acctuser.badges" var="comment_badge">
						<img title=<s:property value="#comment_badge.name"/> src='${ctx}/<s:property value="#comment_badge.url"/>'/>
					</s:iterator>
				</div>
				
				</div>
			</div>
			<div id="bbs-context-panel" class="col-md-9 bbs-context-panel">
			<%-- <s:property value='#request.comment.content'/> --%>
			${comment.content}
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="bbs-good-tread">
				<span id="commentAdZanOrCaiTip"></span>
				<span id="commentAdZanNum"><s:if test="#request.comment.commentAd==null">0</s:if><s:else><s:property value='#request.comment.commentAd.agreeCount'/></s:else></span>
				<span id="commentAdZan" class="glyphicon glyphicon-thumbs-up"></span>
				<input type="hidden" name="viewCommentId" value='<s:property value="#request.comment.id"/>'/>
				&nbsp;&nbsp;|&nbsp;&nbsp;
				<span id="commentAdCai" class="glyphicon glyphicon-thumbs-down"></span>
				<span id="commentAdCaiNum"><s:if test="#request.comment.commentAd==null">0</s:if><s:else><s:property value='#request.comment.commentAd.dsagreeCount'/></s:else></span>
				</div>
			</div>
		</div>
		
		</div>
		<div class="col-md-2"></div>
	</div>
	</s:iterator>
	<!-- 历史回复 -->
	
	<!-- 分页 -->
	<div class="row nopaddingmargin">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 bbs-bottom2">
				<div class="row">
					<div class="col-md-9">
						<div class="btn-group padingtop30">
						<button type="button" class="btn btn-default" name="commontbtnFirstPage">first</button>
						<s:if test="commontPage.hasPre">
  						<button type="button" class="btn btn-default" name="commontbtnPrePage">←</button>
  						</s:if>
  						<s:else>
　						<button type="button" class="btn btn-default" name="commontbtnPrePage" disabled>←</button>
						</s:else>
  						<button type="button" class="btn btn-default" name="commontbtnPageNo">1</button>
  						<s:if test="commontPage.hasNext">
   						<button type="button" class="btn btn-default" name="commontbtnNextPage">→</button>
   						</s:if>
   						<s:else>
　						<button type="button" class="btn btn-default" name="commontbtnNextPage" disabled>→</button>
						</s:else>
						<button type="button" class="btn btn-default" name="commontbtnLastPage">last</button>
						</div>
						</div>
						<div class="col-md-3">
						<s:if test='#request.commontPage.totalCount!=0'>
					 	<div class="input-group margin-right-10-2 pull-right">
         				<span class="input-group-addon">共${commontPage.totalPages}页</span>
         				<input id="commontgoNumber" type="text" class="form-control">
         				<span id="commontbtnGo" class="input-group-addon">go</span>
      					</div>
						</s:if>
					</div>
			</div>
				</div>
				<div class="col-md-2">
				</div>
			</div>
	</div>
	<!-- 分页 -->
	
	<!-- 回复 -->
	<form id="replyTopic" action="${ctx}/mypace/bbs/comment!saveComment.action" method="post">
	<div class="row bbs-margin-bottom">
		<div class="col-md-2"></div>
		<div class="col-md-8 border1px">
			<div class="bbs-reply"><div class="divSimditor"><textarea name="commont" id="editor" class="ztextareaBorder" rows="" cols=""></textarea></div></div>
			<div id="bbsreplyTopicButton" class="bbs-reply-btn"><button type="submit" class="btn btn-default">回复</button></div>
			<input type="hidden" name="viewTopicId" value='<s:property value="#request.topic.id"/>'>
		</div>
		<div class="col-md-2"></div>
	</div>
	</form>
	<!-- 回复 -->
	
	<div class="row headerBorder">
	<div class="col-md-12">
	<%@ include file="/common/footer.jsp"%>
	</div>
	</div>
</div>
<form id="commontForm">
	<input type="hidden" id="ctx" value="${ctx}" /> 
	<input type="hidden" name="commontPage.pageNo" id="commontpageNo" value="${commontPage.pageNo}"/>
	<input type="hidden" name="commontPagehiddenPrePage" value="${commontPage.prePage}"/>
	<input type="hidden" name="commontPagehiddenNextPage" value="${commontPage.nextPage}"/>
	<input type="hidden" name="commontPagehiddenLasttPage" value="${commontPage.totalPages}"/>
	
	<input type="hidden" name="viewTopicId" value='<s:property value="#request.topic.id"/>'/>
</form>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#replyTopic')
									.bootstrapValidator(
											{
												message : 'This value is not valid',
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													commont : {
														validators : {
															notEmpty : {
																message : '回复内容不能为空'
															},
															stringLength : {
																min : 6,
																max : 10000,
																message : '回复内容太短(<6)或者回复内容太长(>10000)'
															}
														}
													}
												//
												}
									});
							//
							toolbar = ['bold', 'italic', 'underline', 'strikethrough',
										'color', 'ol', 'ul', 'blockquote', 'code',
										'link', 'hr','indent', 'outdent'];
							var editor = new Simditor( {
								textarea : $('#editor'),
								placeholder : '这里输入内容...',
								//cleanPaste: false, //支持复制黏贴,
								toolbar : toolbar
							});
						});
	</script>
</body>
</html>