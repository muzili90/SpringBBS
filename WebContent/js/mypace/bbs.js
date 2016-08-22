$(document).ready(
		function() {
			//alert("@");

			// 显示Node获取Node.id
			$('.nodeA').each(
					function() {
						$(this).click(
								function() {
									$('#nodeSpan').text(
											"[ " + $(this).text() + " ]").css(
											"color", "#336699");
									var hiddenNode = $(this).next(
											"input[name='hiddenNodeValue']");
									$("input[name='nodeValue']").val(
											hiddenNode.val());
									// alert($("input[name='nodeValue']").val());
								})

			})

			/*function validateFromz() {
				if ($("input[name='nodeValue']").val().length < 3) {
					alert("不能超过50个字符！");
					//document.a.b.focus();
					return false;
					//bbse-rror topicTitle
				}
			}*/
			
			$("button[id='bbscreateTopicButton']").click(function(){
				$('#bbs-error').text("");
				if ($("input[name='topic.title']").val().length < 6) {
					$('#bbs-error').text("[标题不能少于6个字符!]");
					return false;
				}else if($("input[name='topic.title']").val().length > 32){
					$('#bbs-error').text("[标题不能超过32个字符!]");
					return false;
				}else if($("input[name='nodeValue']").val()==""){
					$('#bbs-error').text("[请选择一个分类!]");
					return false;
				}else if ($("textarea[name='topic.content']").val().length < 6) {
					$('#bbs-error').text("[内容不能少于6个字符!]");
					return false;
				}
				return true;
			})
			//
			$("button[id='bbsreplyTopicButton']").click(function(){
				$('#bbs-error').text("");
				if ($("input[name='topic.title']").val().length < 6) {
					$('#bbs-error').text("[标题不能少于6个字符!]");
					return false;
				}else if($("input[name='topic.title']").val().length > 32){
					$('#bbs-error').text("[标题不能超过32个字符!]");
					return false;
				}else if($("input[name='nodeValue']").val()==""){
					$('#bbs-error').text("[请选择一个分类!]");
					return false;
				}else if ($("textarea[name='topic.content']").val().length < 6) {
					$('#bbs-error').text("[内容不能少于6个字符!]");
					return false;
				}else if ($("textarea[name='topic.content']").val().length > 10000) {
					$('#bbs-error').text("[内容多于10000个字符!]");
					return false;
				}
				return true;
			})
			//
	//$('#bbs-context-panel').css("height",$('#bbs-user-panel').height()+2);
			$("span[id='topicAdZan']").click(function(){
				var adTopicId=$("input[name='viewTopicId']").val()
				//alert(adTopicId);
				var topicAdZanNum=$("span[id='topicAdZan']").prev("span[id='topicAdZanNum']");
				//alert(topicAdZanNum.text());
				var tip=$("span[id='topicAdZanOrCaiTip']");
				
				$.ajax({
					url : 'topic!doTopicAdAjax.action',
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						zanOrcai : 'zan',
						adTopicId : adTopicId
					},
					success : function(data) {
                        if (data['msg']==0) {
							tip.text("请先登录");
							tip.css("color", "red");
							tip.fadeIn();
							tip.fadeOut();
						}
						/*if (data.status != 'success') {
							alert("unsuccess");
						}else{
							alert("##");
						}*/

                        else if (data['msg']==1) {
							tip.text("已评论");
							tip.css("color", "red");
							tip.fadeIn();
							tip.fadeOut();
						}else{
							//alert(data);
							topicAdZanNum.text(data['zancount']);
							tip.text("+1");
							tip.css("color", "red");
							tip.fadeIn();
							tip.fadeOut();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);}
				    //complete : function() { alert("complete")}
				});
			});
			//
			$("span[id='topicAdCai']").click(function(){
				var adTopicId=$("input[name='viewTopicId']").val()
				//alert(adTopicId);
				var topicAdCaiNum=$("span[id='topicAdCai']").next("span[id='topicAdCaiNum']");
				//alert(topicAdZanNum.text());
				var tip=$("span[id='topicAdZanOrCaiTip']");
				
				$.ajax({
					url : 'topic!doTopicAdAjax.action',
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						zanOrcai : 'cai',
						adTopicId : adTopicId
					},
					success : function(data) {
                        if (data['msg']==0) {
							tip.text("请先登录");
							tip.css("color", "red");
							tip.fadeIn();
							tip.fadeOut();
						}
						/*if (data.status != 'success') {
							alert("unsuccess");
						}else{
							alert("##");
						}*/

                        else if (data['msg']==1) {
							tip.text("已评论");
							tip.css("color", "red");
							tip.fadeIn();
							tip.fadeOut();
						}else{
							//alert(data);
							topicAdCaiNum.text(data['caicount']);
							tip.text("+1");
							tip.css("color", "#336699");
							tip.fadeIn();
							tip.fadeOut();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);}
				    //complete : function() { alert("complete")}
				});
			});
			//
});