
$("#frm_register").on("onSubmitted", function(event){
	alert("注册成功!");
	window.location.href='/auto/index.html';	
});
//点击监听事件
$('#btn_reg').on("click", function() {
	$("#frm_register").submit();
});