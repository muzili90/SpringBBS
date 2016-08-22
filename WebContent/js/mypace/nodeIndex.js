$(document).ready(function() {
	$("button[name='btnPageNo']").text($("input[name='page.pageNo']").val());
	
	$("button[name='btnNextPage']").click(function(){
		var nextPage=$("input[name='hiddenNextPage']").val();
		jumpPageNodeTopic(nextPage);
	});
	
	$("button[name='btnPrePage']").click(function(){
		var prePage=$("input[name='hiddenPrePage']").val();
		jumpPageNodeTopic(prePage);
	});
	
	$("button[name='btnFirstPage']").click(function(){
		jumpPageNodeTopic(1);
	});
	
	$("button[name='btnLastPage']").click(function(){
		var lastPage=$("input[name='hiddenLasttPage']").val();
		jumpPageNodeTopic(lastPage);
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
		jumpPageNodeTopic(goPage);
	});
	
	
})

	function jumpPageNodeTopic(pageNo) {

		
		 $("#pageNo").val(pageNo);
		 
		 var frm = $("#showNodeTopicForm");
		 
		 frm.submit();
		 
}

	function validateNum(str){
		var s = /^[0-9]*$/;
		return s.test(str);
	}