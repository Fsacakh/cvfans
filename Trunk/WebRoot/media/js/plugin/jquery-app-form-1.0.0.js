(function($){
	var re = /^(?:hidden|color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i;
	
	function getValue(element){
		var name=element.attr('name');
		
		if(element.is('input')){
			var type = element.attr('type');
			if(type.match(re)){
				value = element.val().trim();
				
				var dataType = element.attr("data-type");
	    		if(dataType == '' || dataType == undefined){
	    			dataType = 'string';
	    		}
	    		 
	    		if(dataType.toLowerCase() == 'string'){
	    			value = "'" + value + "'";
	    		}
	    		
	    		if(dataType.toLowerCase() == 'date'){
	    			if(value != '') value = "'" + value + "'";
	    			else value="''";
	    		}
			}else if (type == 'radio'){
				$("input[name='" + name + "']").each(function(){
					if(element.parent().hasClass('checked')){
						value = "'" + element.attr('value') + "'";
					}
				});
			}else if (type == 'checkbox'){
				if(element.parent().hasClass('checked')){
					value = "'" + element.attr('value') + "'";
				}else{
					value = "''";
				}
			}
		}else if(element.is('select')){
			value = "'" + element.find('option:selected').attr('value') + "'";
		}else if(element.is('textarea')){
			value = "'" +  element.val() + "'";
		}
		
		return value;
	}
	
	function setValue(element, value){
		if(element.data("ignore") == 'true') return;
		
		var type = element.attr('type');
		var name = element.attr("name");
		if(element.is('input')){
			if(type.match(re)){
				element.val(value);
				
				if(element.hasClass('search-item')){
					element.lookup();
				}
			}else if (type == 'radio'){
				var radios = $("input[name='" + name + "']");
				radios.each(function(){
					var radio = $(this);
					if(radio.attr('value') == value){
						radio.attr('checked', 'checked');
						radio.parent().addClass("checked");
					}else{
						radio.attr("checked",false);
						radio.parent().removeClass("checked");
					}
				})
			}else if (type == 'checkbox'){
				if(element.attr("value") == value){
					element.parent().addClass("checked");
				}
			}
		}else if(element.is('select')){
			element.find('option[value="' + value + '"]').attr("selected", true);
			
			//出发级联操作
			if(element.attr("data-cascade") != undefined){
				element.trigger("change");
			}
		}else if(element.is('textarea')){
			element.val(value);
		}else if(element.is('img')){
			if(value != undefined && value != ''){
				element.attr('src', $.URL.get(value));
			}
		}else{
			element.text(value);
		}
	}
	
	/**
	 * 反序列化缺省实现
	 */
	$.fn.deserializer={
		multiple:function(multilple){
			var json = {};
			var subject = multilple.subject;
			var object = multilple.object;
			
			json[subject] = $('#' + subject).val();
			
			var objectValue = '';
			$("[name='" + object + "']").each(function(){
				if(objectValue != '') objectValue += ",";
				objectValue  += $(this).attr('value');
			});
			json[object] = objectValue;
			
			return json;
		},
		
		single:function(frm){
			var json = {};
			frm.find('input,select,textarea').each(function(){
				var element = $(this);
				if(element.attr("type") != 'file'){
					if(element.attr("deserialized") == undefined || $(this).attr("deserialized") == 'true'){
						var name = element.attr('name');
						var value = getValue(element);
						if(name != ''){
							var names = name.split('.');
							if(names.length > 1){
								var expr = 'json';
								var v = null;
								for(var index in names){
									expr = expr + "." + names[index];
									v = eval(expr);
									if(v == undefined && index < names.length - 1){
										eval(expr + "={}");
									}
								}
							}
							
							if(value != undefined){
								value=value.replace(/\n/ig,"<BR>");
								eval("json." +name + "="+ value);
							}
						}
					}
				}
			});
			
			return json;
		}
	};
	
	$.fn.extend({
		form:function(options){
			var defaults = {
				deserializer:{},
				serializer:{}
			};
			
			var opts = $.extend( true, defaults, options);
			
			$(this).data('options', opts);
			
			return $(this);
		},
		
		multiple:function(options){
			var defaults = {
				subject:"",
				object:""
			};
			
			var opts = $.extend( true, defaults, options);
			
			$(this).data("multiple", opts);
			
			return $(this);
		},
		
		/**
		 * 将界面控件数据返回成JSON对象
		 */
		deserialize:function(){
			var frm = $(this);
			
			var json = {};
			
			var multiple = $(this).data("multiple");
			if(multiple != undefined){
				json = $.fn.deserializer.multiple(multiple);
			}else{
				json = $.fn.deserializer.single($(this));
			}
			
			var options = frm.data("options");
			var deserializer = {};
			if(options != undefined){
				deserializer = options.deserializer;
			}
			
			for(var name in deserializer){
				var names = name.split('.');
				if(names.length > 1){
					var expr = 'json';
					var v = null;
					for(var index in names){
						expr = expr + "." + names[index];
						v = eval(expr);
						if(v == undefined && index < names.length - 1){
							eval(expr + "={}");
						}
					}
				}
				
				eval("json." +name + "='"+ deserializer[name]() +"'");
			}
			
			return json;
		},
		
		_submit:function(options){
			var frm = $(this);
			
			if( $.Events.exist(frm, "onSubmit")){
	    		var success = frm.triggerHandler("onSubmit");
	    		if(success != undefined && !success){
					return false;
	    		}
    		}
			
			var json = this.deserialize();
			
			if(frm.attr('enctype') == "multipart/form-data"){ //带附件的提交
				var files = new Array();
				frm.find("input[type='file']").each(
					function(){
						files.push($(this).attr("name"));
					}
				);
				
				$.Service.multipartInvoke({
					url:frm.attr('action'),
		        	files :files,//file控件id
		        	data : json,
		        	success: function (response) {   //成功后回调
		        		if($.Events.exist(frm, "onSubmitted")){
							frm.trigger("onSubmitted", response);
						}else{
							$.Dialog.alert("保存成功.", function(){
								var page = frm.parents("div[data-role='page']");
					    		page.back();
			    			});
						}
				    },
				    fail:function(response){
				    	if($.Events.exist(frm, "onFailSubmitted")){
							frm.trigger("onFailSubmitted", response);
						}
				    }
				});
				
			}else{
				if($.Events.exist(frm, "onFailSubmitted")){
					$.Service.invoke({
						url: frm.attr('action'),
						data:json,
						success: function (response) {   //成功后回调
							if($.Events.exist(frm, "onSubmitted")){
								frm.trigger("onSubmitted", response);
							}else{
								$.Dialog.alert("保存成功.", function(){
									var page = frm.parents("div[data-role='page']");
						    		page.back();
				    			});
							}
					    },
					    fail:function(response){
							frm.trigger("onFailSubmitted", response);
					    }
					});
				}else{
					$.Service.invoke({
						url: frm.attr('action'),
						data:json,
						success: function (response) {   //成功后回调
							if($.Events.exist(frm, "onSubmitted")){
								frm.trigger("onSubmitted", response);
							}else{
								$.Dialog.alert("保存成功.", function(){
									var page = frm.parents("div[data-role='page']");
						    		page.back();
				    			});
							}
					    }
					});
				}
			}
		},
		
		/**
		 * 将数据填写到界面控件
		 */
		serialize:function(action, data){
			var serializer = {};
			
			var frm = $(this);
			var options = frm.data("options");
			if(options != undefined){
				serializer = options.serializer;
			}
			frm.find('[name]').each(function(){
				var name = this.name;
				var element = $(this);
				
				var value = '';
				if(name in serializer){ //自定义展示
					value = serializer[name](this, data);
				}else{
					var bind = element.data('bind');
					if(bind == undefined || bind == ''){
						bind = element.attr('name');
					}
					
					value = eval('data.' + bind);
				}
				
				setValue(element, value);
				
				if(action == 'view'){
					if(element.attr("disabled") != 'disabled'){
						element.attr('disabled', 'disabled');
					}
					
					if(element.hasClass('search-item')){
						element.refresh();
					}
				}
			});
		},
		
		/**
		 * entity 装载数据实体
		 * data   实体装载参数
		 * options 可选参数，可以用来定义数据显示定义，是一个Json对象。
		 */
		_load:function(options){
			var defaults = {
				entity:'',
				statementId:'',
				data:{},
				action:'edit'
			};
			
			var opts = $.extend( true, defaults, options);
			if(opts.entity == '' && opts.statementId == ''){
				return false;
			}
			
			var frm = $(this);
			
			var params = {};
			var url = "/Query/" + opts.entity + "/Load.action";
			if(opts.entity != ''){
				params.entity = opts.entity;
			}
			
			if(opts.statementId != ''){
				params.statementId = opts.statementId;
			}
			
			params.parameters = opts.data;
			$.Service.invoke({
				url: url,
				data:params,
				success: function (data) {   //成功后回调
					frm.serialize(opts.action, data);
					
					if($.Events.exist(frm, "onLoaded")){
						frm.trigger("onLoaded", data);
					}
					
					frm.data('value',data);
			    }
			});
		},
		
		/**
		 * entity 装载数据实体
		 * data   实体装载参数
		 */
		loadEntity:function(options){
			var defaults = {
				entity:'',
				data:{},
				action:'edit'
			};
			
			var opts = $.extend( true, defaults, options);
			if(opts.entity == ''){
				return false;
			}
			
			var frm = $(this);
			
			var url = "/Persist/" + opts.entity + "/Load.action";
			$.Service.invoke({
				url: url,
				data:opts.data,
				success: function (data) {   //成功后回调
					frm.serialize(opts.action, data);
					
					if($.Events.exist(frm, "onLoaded")){
						frm.trigger("onLoaded", data);
					}
					
					frm.data('value',data);
			    }
			});
		},
		
		reset:function(){
			$(this)[0].reset();
		}
	});     
})(jQuery);     