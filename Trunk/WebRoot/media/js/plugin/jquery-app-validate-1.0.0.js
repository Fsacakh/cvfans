jQuery.extend(jQuery.validator.messages, {
    required: "必选字段",
	remote: "请修正该字段",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
	minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
	rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1}之间的字符串"),
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
	min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});

$.validator.setDefaults({   
	debug: true, 
	ignore: [], 
	errorPlacement:function(error, element){
		var parent = element.parent();
		parent.addClass("checkItem");
		error.appendTo(parent);
	},
	
	errorElement:"div",
	
	submitHandler:function(frm){
		var $frm = $(frm);
		$frm._submit();
	}
});

jQuery.validator.addMethod("checkStrength",function(value,element){
	intScore = 0;
	if (value.match(/[a-z]/)){ // [verified] at least one lower case letter
		intScore = (intScore+1);
	} 
	
	if (value.match(/[A-Z]/)){ // [verified] at least one upper case letter
		intScore = (intScore+5);
	}
	
	if (value.match(/\w[(!@#$%&)]/)){ // [verified] at least one upper case letter
		intScore = (intScore+5);
	}
	
	if (value.match(/[0-9]/)){ // [verified] at least one upper case letter
		intScore = (intScore+1);
	}
	
	if (value.match(/[a-zA-Z0-9_]{8,}$/)){ // [verified] at least one upper case letter
		intScore = (intScore+1);
	} 
	
	return intScore > 8;
} ,  "密码强度弱，必须包含数字、字符（大写和小写）或者特殊字符。"); 

jQuery.validator.addMethod("discount", function(value, element) {
	if(value == '') return true;
	
	return value.match(/^[0-9]+(.[0-9]{1})?$/);
}, "请输入正确的折扣率");

