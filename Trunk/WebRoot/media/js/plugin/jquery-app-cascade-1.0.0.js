(function($){
	$.fn.handle = function(options){
		if(options.value == ''){
			for(var name in options.targets){
				$('#' + name).val('');
			}
		}
		
		var context={};
		context.statementId = options.statementId;
		context.parameters = {};
		context.parameters[options.filter] = options.value;
		$.Service.invoke({
			url: "/Query/Load.action",
			data:context,
			success: function (data) {   //成功后回调
				for(var name in options.targets){
					$('#' + name).val(data[name]);
				}
		    }
		});
	},
	
	$.fn.extend({
		cascade: function(options){
			var defaults = {
				statementId:"",
				filter:"",
				targets:{}
			};
			var opts = $.extend(true, defaults, options);
			var target = $(this);
			
			var value = '';
			if(target.is('input')){
				target.blur(function(){
					value = target.val();
					opts.value = value;
					
					$.fn.handle(opts);
				});
			}else if (target.is('select')){
				target.change(function(){
					value = target.find('option:selected').attr('value');
					opts.value = value;
					
					$.fn.handle(opts);
				});
			}
			
			return this;
		}
	});     
})(jQuery);  