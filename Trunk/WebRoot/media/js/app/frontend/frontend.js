/**
 * 服务站列表页面处理
 */
$("#selectProvince").on("click", function(event, showName){
	$('#headerAllProvince').toggleClass("hidden");
});

$("#headerAllProvince a").on("click", function(event, showName){
	$("#selectedProvince").text(this.text);
	$('#headerAllProvince').toggleClass("hidden");
});