$(document).ready(function() {
	$("button[name='commontbtnPageNo']").text($("input[name='commontPage.pageNo']").val());
	
	$("button[name='commontbtnNextPage']").click(function(){
		var nextPage=$("input[name='commontPagehiddenNextPage']").val();
		commontJumpPageTopic(nextPage);
	});
	
	$("button[name='commontbtnPrePage']").click(function(){
		var prePage=$("input[name='commontPagehiddenPrePage']").val();
		commontJumpPageTopic(prePage);
	});
	
	$("button[name='commontbtnFirstPage']").click(function(){
		commontJumpPageTopic(1);
	});
	
	$("button[name='commontbtnLastPage']").click(function(){
		var lastPage=$("input[name='commontPagehiddenLasttPage']").val();
		commontJumpPageTopic(lastPage);
	});
	

	$("#commontbtnGo").click(function(){
		
		var goPage=$("#commontgoNumber").val();
		if(goPage==""){
			alert("请输入数字");
			return false;
		}
		var b=commontValidateNum(goPage);
		var lastPage=$("input[name='commontPagehiddenLasttPage']").val();
		if(!b){
			alert("请输入数字");
			return false;
		}else if(goPage>lastPage){
			alert("超过最大页数");
			return false;
		}
		commontJumpPageTopic(goPage);
	});
	
	//
	$("span[id='commentAdZan']").click(function(){
		var adCommentId=$(this).next("input[name='viewCommentId']").val();
		var commentAdZanNum=$(this).prev("span[id='commentAdZanNum']");
		var commentTip=$(this).prev("span[id='commentAdZanNum']").prev("span[id='commentAdZanOrCaiTip']");
		
		$.ajax({
			url : 'comment!doCommentAdAjax.action',
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				commentZanOrcai : 'zan',
				viewCommentId : adCommentId
			},
			success : function(data) {
                if (data['msg']==0) {
                	commentTip.text("请先登录");
                	commentTip.css("color", "red");
                	commentTip.fadeIn();
                	commentTip.fadeOut();
				}
				/*if (data.status != 'success') {
					alert("unsuccess");
				}else{
					alert("##");
				}*/

                else if (data['msg']==1) {
                	commentTip.text("已评论");
                	commentTip.css("color", "red");
                	commentTip.fadeIn();
                	commentTip.fadeOut();
				}else{
					//alert(data);
					commentAdZanNum.text(data['zancount']);
					commentTip.text("+1");
					commentTip.css("color", "red");
					commentTip.fadeIn();
					commentTip.fadeOut();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);}
		    //complete : function() { alert("complete")}
		});
		//
	});
	//
	$("span[id='commentAdCai']").click(function(){
		var adCommentId=$(this).prev("input[name='viewCommentId']").val();
		var commentAdCaiNum=$(this).next("span[id='commentAdCaiNum']");
		var commentTip=$(this).prev("input[name='viewCommentId']").prev("span[id='commentAdZan']").prev("span[id='commentAdZanNum']").prev("span[id='commentAdZanOrCaiTip']");
		
		$.ajax({
			url : 'comment!doCommentAdAjax.action',
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				commentZanOrcai : 'cai',
				viewCommentId : adCommentId
			},
			success : function(data) {
                if (data['msg']==0) {
                	commentTip.text("请先登录");
                	commentTip.css("color", "red");
                	commentTip.fadeIn();
                	commentTip.fadeOut();
				}
				/*if (data.status != 'success') {
					alert("unsuccess");
				}else{
					alert("##");
				}*/

                else if (data['msg']==1) {
                	commentTip.text("已评论");
                	commentTip.css("color", "red");
                	commentTip.fadeIn();
                	commentTip.fadeOut();
				}else{
					//alert(data);
					commentAdCaiNum.text(data['caicount']);
					commentTip.text("+1");
					commentTip.css("color", "#336699");
					commentTip.fadeIn();
					commentTip.fadeOut();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);}
		    //complete : function() { alert("complete")}
		});
		//
	});
	//
})

	function commontJumpPageTopic(pageNo) {

		
		 $("#commontpageNo").val(pageNo);
		  
		 var ctx = $("#ctx").attr("value");
		 
		 var newAction = ctx + "/mypace/bbs/topic!showTopic.action";
		 
		 var frm = $("#commontForm");
		 
		 frm.attr("action", newAction);
		 
		 frm.submit();
		 
}

	function commontValidateNum(str){
		var s = /^[0-9]*$/;
		return s.test(str);
	}