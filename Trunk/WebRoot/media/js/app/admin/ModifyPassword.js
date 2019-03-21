jQuery(document).ready(function() {
		 $('a[rel*=leanModal]').leanModal({ top: 100, closeButton: ".modal_close" });
			
		 // 清空所有输入框中的值
		 $('a[rel*=leanModal]').on("click", function(event) {
					$(':input','#frm_modify_password').not('#userId').val(''); 
		 });
			
		 //成功回调函数操作
		 $("#frm_modify_password").on("onSubmitted", function(response){
			 $("#OpenWindow").hide();
			 $("#lean_overlay").css({"display":"none","opacity":"0"});
	    	 alert("修改成功！");
		 });
		 
		 //失败回调函数操作
		 $("#frm_modify_password").on("onFailSubmitted", function(event, response){
	    		alert(response.status_text);
		 });
				
				
		// 点击监听事件
		$('#btn_modify').on("click", function(event) {
			$('#frm_modify_password').validate({
		        rules: {
		   oldPwd: {
		   required:true,
		   },
		   newPwd: {
		    required: true,
		    minlength: 6
		   },
		   confirm_password: {
		    required: true,
		    minlength: 6,
		    equalTo: "#newPwd"
		   },
		  },
		   messages: {
		   oldPwd: {
		    required: "请输入旧密码",
		   },
		   newPwd: {
		    required: "请输入新密码",
		    minlength: "密码至少为6位"
		   },
		   confirm_password: {
		    required: "请输入确认密码",
		    minlength: "密码至少为6位",
		    equalTo: "两次密码输入不一致"
		   },
		  }
	  });
	});
});