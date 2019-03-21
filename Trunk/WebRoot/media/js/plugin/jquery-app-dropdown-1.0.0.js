(function($){
	$.fn.actions = {
		remove : function(element) {
			var header = $(element).parents(".header");
			$(".search-result", header).text("");
    		$("input", header).val("").trigger("change");
		},
		
		dropdown : function(element){
			var dropdown = $(element).parents(".dropdown");
			$(".error", dropdown).text('').removeClass("error");
			
			var body = dropdown.find(".body");
    		
    		if($(element).hasClass("icon-chevron-down")){
    			$(element).removeClass("icon-chevron-down").addClass("icon-chevron-up");
    			body.removeClass("hidden");
    			body.find("input").focus();
    		}else{
    			$(element).removeClass("icon-chevron-up").addClass("icon-chevron-down");
    			body.addClass("hidden");
    		}
		},
		
		select : function(element, opts){
			var dropdown = $(element).parents(".dropdown");
    		var header = dropdown.find(".header");
    		var body = dropdown.find(".body");
    		
    		var value = $(element).data("value");
    		if(opts.cascadeHandler == undefined){
    			var mapping = opts.cascadeMapping;
	    		for(var field in mapping){
	    			$('#' + field).val(value[mapping[field]]);
	    			$('#' + field).trigger("onchange");
	    		}
			}else{
				opts.cascadeHandler(value);
			}

    		$(".search-result", header).text($(element).text());
			
    		header.find(".btn-dropdown").removeClass("icon-chevron-up").addClass("icon-chevron-down");
    		body.addClass("hidden");
		}
	},
	
	$.fn.extend({
		refresh:function(){
			var target = $(this);
			var header = target.parent();
			if(target.attr('disabled') != 'disabled'){
				header.find(".search-result").on("click", function(event){header.find(".btn-dropdown").trigger('click');});
				header.find(".btn-remove").on("click", function(event){$.fn.actions.remove($(this));});
				header.find(".btn-dropdown").on("click", function(event){$.fn.actions.dropdown($(this));});
			}else{
				header.addClass("disabled");
				header.find(".search-result").off("click");
				header.find(".btn-remove").off("click");
				header.find(".btn-dropdown").off("click");
			}
		},
		
		lookup:function(){
			var target = $(this);
			if(target.val() == '') return;
			
			var options = target.data('options');
			
			var context={};
			context.statementId = options.statementId;
			context.parameters = {};
			context.parameters[target.attr('name')] = target.val();
			
			$.Service.invoke({
				url: "/Query/List.action",
				data:context,
				success: function (response) {   //成功后回调
					$(response).each(function(index, data){
			    		if(options.cascadeHandler == undefined){
			    			var mapping = options.cascadeMapping;
				    		for(var field in mapping){
				    			$('#' + field).val(data[mapping[field]]).data('ignore', 'true');
				    			
				    		}
						}else{
							options.cascadeHandler(data);
						}
			    		
						target.parents('.header').find(".search-result").text(data[options.showColumn]);
					});
			    }
			});
		},
		
		list: function(options){
			var defaults = {
				statementId:"",
				filter:"",
				showColumn:'',
				cascadeMapping:{},
				cascadeHandler:undefined,
				align:"left",
				height:"100px",
				placeholder:""
			};
			var opts = $.extend(true, defaults, options);
			
			var target = $(this);
			target.attr("type", "hidden").addClass("search-item").data('options', opts);
			
			var parentDiv = target.parent();
			parentDiv.addClass("dropdown");
			
			this.appendTo(parentDiv);
			target.on("onchange", function(){
				if(target.val() != ''){
					$("div[for='" + $(this).attr('id') + "']").text('').removeClass("error");
				}
			});
			
			var header = $("<div class='form-control header' style='position:relative; overflow:hidden; padding-top:0px; margin-bottom:0px;'></div>");
			//header.append(this);
			header.append("<div class='search-result' style='height:100%;margin-left:10px;'></div>");
			header.append("<div class='buttons' style='width:35px; padding-left:5px; position:absolute; border-left:1px solid silver; background-color:white; right:0px; top:0px;'><i class='btn-remove icon-remove'></i> <i class='btn-dropdown icon-chevron-down'></div>");
			header.appendTo(parentDiv);
			
			if(target.attr('disabled') != 'disabled'){
				header.find(".search-result").on("click", function(event){header.find(".btn-dropdown").trigger('click');});
				header.find(".btn-remove").on("click", function(event){$.fn.actions.remove($(this));});
				header.find(".btn-dropdown").on("click", function(event){$.fn.actions.dropdown($(this));});
			}else{
				header.addClass("disabled");
			}
			
			var css = "";
			if(opts.align == "left"){
				css = "left:" + header.position().left + "px; ";
			}else{
				css = "right:" + header.position().left + "px; ";
			}
			
			css +="width:" + header.css("width") + "";
			
			var body = $("<div class='body " + opts.align + " hidden' style='" + css + "'></div>");
			body.append("<div><input type='text' class='form-control' placeholder='" + opts.placeholder + "' deserialized='false'></div>");
			body.append("<div class='data'><ul></ul></div>");
			body.appendTo(parentDiv);
			
			var dataList = body.find("ul");
			var input = body.find("input");
			input.keydown(function(event){
				if(event.which==13){
					var context={};
					context.statementId = opts.statementId;
					context.parameters = {};
					context.parameters[opts.filter] = $(this).val();
					
					$.Service.invoke({
						url: "/Query/List.action",
						data:context,
						success: function (response) {   //成功后回调
							dataList.empty();
							$(response).each(function(index, data){
								var item = $("<li data-value='" + data[opts.valueColumn] + "'>" + data[opts.showColumn] + "</li>");
								item.on("click", function(event){$.fn.actions.select($(this), opts);});
								item.data("value", data);
								dataList.append(item);
							});
					    }
					});
				}
			});
			
			return this;
		},
		
		tree:function(options){
			
		}
	});     
})(jQuery);  