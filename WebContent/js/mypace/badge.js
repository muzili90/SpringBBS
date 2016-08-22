$(document).ready(function() {
	//领取勋章
	$('.getBadgeBtn').each(
			function() {
				$(this).click(
						function() {
							var hiddenBadge = $(this).next(
							"input[name='hiddenBadgeId']");
							
							var hiddenBadgeIdValue=hiddenBadge.val();
							
							var tip;
							
							//alert(hiddenBadgeIdValue);
								
								$.ajax({
									url : 'badge!getBadgeAjax.action',
									type : 'post',
									dataType : 'json',
									async : false,
									data : {
										badgeId : hiddenBadgeIdValue
									},
									success : function(data) {
				                        if (data['msg']==0) {
											tip="请先登录"
											alert(tip);
										}
				                        else if (data['msg']==1) {
											tip="该勋章已领过"
											alert(tip);
										}else if (data['msg']==2) {
											tip="已领取勋章，请重新登录查看"
											alert(tip);
										}else if (data['msg']==3) {
											tip="未达到领取条件"
											alert(tip);
										}else{
											tip="未知错误"
											alert(tip);
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
									alert(XMLHttpRequest.status);
				                    alert(XMLHttpRequest.readyState);
				                    alert(textStatus);}
								    //complete : function() { alert("complete")}
								});
							});
							
						})
						//加载页面获取勋章显示状态
						var badgeShowFlagArray=new Array();
						//点击数
						var count=0;
						$.ajax({
								url : 'badge!getBadgeDisableAjax.action',
								type : 'post',
								dataType : 'json',
								async : false,
								data : {
								},
								success : function(data) {
									//idx编号 item内容
									$.each(data,function(idx,item){     
										//输出
										badgeShowFlagArray[idx]=item.msg;
										
									})
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
								alert(XMLHttpRequest.status);
			                    alert(XMLHttpRequest.readyState);
			                    alert(textStatus);}
							    //complete : function() { alert("complete")}
							});
						//设置显示按钮状态
					$('.disableBadgeBtn').each(
								function(index,domEle){
									if(badgeShowFlagArray[index]==0){
										$(this).text("显示");
									}else if(badgeShowFlagArray[index]==1){
										$(this).text("隐藏");
									}else{
										
									}
								}
							);
						//点击显示隐藏勋章
		$('.disableBadgeBtn').each(
				function(index,domEle) {
					$(this).click(
						function() {
							var badgeShowTip;
							
							var hiddenBadge = $(this).next(
							"input[name='hiddenBadgeId']");
							
							var hiddenBadgeIdValue=hiddenBadge.val();
							
							if(count>15){
								alert("您点击太快！请休息一下！");
								return;
							}
							
							if(badgeShowFlagArray[index]==1){
								count++;
								badgeShowFlagArray[index]=0;
								$(this).text("显示");

								$.ajax({
									url : 'badge!changeBadgeDisableAjax.action',
									type : 'post',
									dataType : 'json',
									async : false,
									data : {
										badgeId : hiddenBadgeIdValue,
										badgeShowFlag:badgeShowFlagArray[index]
									},
									success : function(data) {
				                        if (data['msg']==0) {
				                        	badgeShowTip="请先登录";
										}
				                        else if (data['msg']==1) {
				                        	badgeShowTip="未找到显示勋章记录";
										}else if (data['msg']==2) {
											badgeShowTip="已显示或隐藏勋章";
										}else{
											badgeShowTip="未知错误";
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
									alert(XMLHttpRequest.status);
				                    alert(XMLHttpRequest.readyState);
				                    alert(textStatus);}
								    //complete : function() { alert("complete")}
								});
							}else if(badgeShowFlagArray[index]==0){
								count++;
								badgeShowFlagArray[index]=1;
								$(this).text("隐藏");
								$.ajax({
									url : 'badge!changeBadgeDisableAjax.action',
									type : 'post',
									dataType : 'json',
									async : false,
									data : {
										badgeId : hiddenBadgeIdValue,
										badgeShowFlag:badgeShowFlagArray[index]
									},
									success : function(data) {
				                        if (data['msg']==0) {
				                        	badgeShowTip="请先登录";
										}
				                        else if (data['msg']==1) {
				                        	badgeShowTip="未找到显示勋章记录";
										}else if (data['msg']==2) {
											badgeShowTip="已显示或隐藏勋章";
										}else{
											badgeShowTip="未知错误";
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) { alert("error");
									alert(XMLHttpRequest.status);
				                    alert(XMLHttpRequest.readyState);
				                    alert(textStatus);}
								    //complete : function() { alert("complete")}
								});
							}
							
							
							});
							
						})		
});