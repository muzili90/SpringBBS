$(document).ready(function() {
	$("button[name='btnPageNo']").text($("input[name='page.pageNo']").val());
	
	$("button[name='btnNextPage']").click(function(){
		var nextPage=$("input[name='hiddenNextPage']").val();
		jumpPageTopic(nextPage);
	});
	
	$("button[name='btnPrePage']").click(function(){
		var prePage=$("input[name='hiddenPrePage']").val();
		jumpPageTopic(prePage);
	});
	
	$("button[name='btnFirstPage']").click(function(){
		jumpPageTopic(1);
	});
	
	$("button[name='btnLastPage']").click(function(){
		var lastPage=$("input[name='hiddenLasttPage']").val();
		jumpPageTopic(lastPage);
	});
	

	$("#btnGo").click(function(){
		
		var goPage=$("#goNumber").val();
		if(goPage==""){
			alert("请输入数字");
			return false;
		}
		var b=validateNum(goPage);
		var lastPage=$("input[name='hiddenLasttPage']").val();
		if(!b){
			alert("请输入数字");
			return false;
		}else if(goPage>lastPage){
			alert("超过最大页数");
			return false;
		}
		jumpPageTopic(goPage);
	});
	
	//点击标签跳转到该板块
	$('.nodeTypeBtn').each(
			function() {
				$(this).click(
					function() {
							
					var nodeHiddenValue=$(this).next($('.nodeInputHidden')).val();	
					var ctx = $("#ctx").attr("value");
					location.href = ctx+"/mypace/bbs/topic!showNodeTopic.action?nodeValue="+nodeHiddenValue;
				})
	})
})

	function jumpPageTopic(pageNo) {

		
		 $("#pageNo").val(pageNo);
		  
		 var ctx = $("#ctx").attr("value");
		 
		 var newAction = ctx + "/mypace/bbs/topic.action";
		 
		 var frm = $("#topicForm");
		 
		 frm.attr("action", newAction);
		 
		 frm.attr("method","post");
		 
		 frm.submit();
		 
}

	function validateNum(str){
		var s = /^[0-9]*$/;
		return s.test(str);
	}