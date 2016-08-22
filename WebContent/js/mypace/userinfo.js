$(document).ready(function() {
	$(".infoInputValueControl").hide();
	
	//生日初始化
	$.ms_DatePicker({
        YearSelector: "#select_year2",
        MonthSelector: "#select_month2",
        DaySelector: "#select_day2"
    });
	
	$("#btnEdit").click(function(){
		
		$(".infoTextValueControl").hide();
		$(".infoInputValueControl").show();
	})
	
	$("#btnCancel").click(function(){
		
		$(".infoTextValueControl").show();
		$(".infoInputValueControl").hide();
	})
	
	$("#btnSubmit").click(function(){
		//job
		var jobcheck=$("input[name='jobInfo']").val();
		if(jobcheck.length>10){
			$("#jobMsg").css("color","red");
			$("#jobMsg").html("职业描述字符太长!")
			return false;
		}else{
			$("#jobMsg").html("");
		}
		//qq
		var qqcheck=$("input[name='qqInfo']").val();
		if(qqcheck.length>11){
			$("#qqMsg").css("color","red");
			$("#qqMsg").html("QQ字符太长!")
			return false;
		}else{
			$("#qqMsg").html("");
		}
		//签名
		var signaturecheck=$("input[name='signatureInfo']").val();
		if(signaturecheck.length>35){
			$("#signatureMsg").css("color","red");
			$("#signatureMsg").html("签名描述字符太长!")
			return false;
		}else{
			$("#signatureMsg").html("");
		}
	})
	
	
	//
});