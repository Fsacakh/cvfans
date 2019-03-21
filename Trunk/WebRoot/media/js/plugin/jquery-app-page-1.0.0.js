/**
 * 页面操作函数
 */
jQuery.Page={
	open : function(page){ //装载一个新页面
		var target=$.URL.get(page);
		$('#frm_page').attr("action", target);
		$('#frm_page')[0].submit();
	},
	
	forward: function(id, url){
		var target = $('#' + id);
		if(target.length > 0){
			target.forward();
		}else{
			$.get($.URL.get(url), function(result){
			    $("#container").append(result);
			    target = $('#' + id);
			    target.forward();
			});
		}
	}
};

/**
 * 页面操作函数
 */
(function($){
	$("div[data-role='page']").filter(".hidden").each(function(){
		$(this).data('content', $(this).html());
	});	
	
	$.fn.extend({
		/**
		 * context 可以作为参数传递使用，该参数存储到目标页面的data-context参数中。
		 */
		forward : function(context){ //页面切换
			var defaults = {
				action:'',
				page:'',
				data:{},
				callback:function(data){}
			};
			
			var ctx = $.extend(defaults, context);
			
			window.scrollTo(0,0);
			
			var target = $(this);
			
			var prePage = $("div[data-role='page']").not(".hidden");
			var breadcrumb = '';
			if(prePage.length > 0){
				breadcrumb = prePage.find(".breadcrumb").html();
				prePage.trigger("onHidden");
				target.attr('data-previous', '#' + prePage.attr('id'));
				prePage.addClass("hidden");
			}
			
			if(target.data('content') != ''){
				target.empty().append(target.data('content'));
			}
			
			$("input[type='radio'], input[type='checkbox']").uniform();
			
			target.removeClass("hidden").trigger("onShown", ctx);
			
			if(breadcrumb != ''){
				target.find(".breadcrumb").prepend(breadcrumb);
			}
		},
		
		back : function(){
			var currPage = $(this);
			var target = currPage.attr('data-previous');
			if(target != undefined){
				var response = currPage.triggerHandler("onHidden");
				
				window.scrollTo(0,0);
				$("div[data-role='page']").addClass("hidden");
				currPage.removeAttr('data-previous');
				
				$(target).removeClass("hidden").trigger("onShown");
				
				//回调
				var context = $(target).data('context');
				if(context != undefined)
					context.callback(response);
			}
		}
	});
})(jQuery);