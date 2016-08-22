<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<div>
<div class="pull-left">&nbsp;&nbsp;<a href="http://localhost:8080/MySpringBBSSample">主页</a> &nbsp;约吗 &nbsp;<a href="http://localhost:8080/MySpringBBSSample">论坛</a>&nbsp;标签</div>

<div class="pull-right" style="padding-right:75px;">
<%boolean loginVa=SpringSecurityUtils.getCurrentUser()==null; %>
<%if(loginVa==true){%>
<a href="${ctx }/mypace/login.action">登录</a>
<%}else{ %>
你好,<a href="${ctx }/mypace/bbs/userinfo!info.action"><sec:authentication property="principal.userNameAlias"/>&nbsp;</a>&nbsp;&nbsp;<a href="${ctx}/j_spring_security_logout">登出</a> 
<%} %>
 | <a href="${ctx}/mypace/bbs/topic!donate.action" target="_blank">捐赠</a> | <a href="${ctx }/mypace/bbs/help!help.action" target="_blank">帮助</a>&nbsp;&nbsp;
 </div>
 <!-- <sec:authentication property="name"/> -->
</div>