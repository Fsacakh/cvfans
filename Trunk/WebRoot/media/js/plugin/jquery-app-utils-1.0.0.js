/**
 * URL 
 */
jQuery.URL={
	get:function(url){
		if(url.startWith('/') || base.endsWith('/')){
			return base + url;
		}else{
			return base + '/' + url;
		}
	},
	
	parse:function(url){
		var a =  document.createElement('a');  
		a.href = url;     
		return {  
			source: url,  
			protocol: a.protocol.replace(':',''),  
			host: a.hostname,  
			port: a.port, 
			context:'/' + a.pathname.replace(/^\//,'').split('/')[0],
			query: a.search,  
			params: (function(){  
				var ret = {},  seg = a.search.replace(/^\?/,'').split('&'), len = seg.length, i = 0, s;  
				for (;i<len;i++) {  
					if (!seg[i]) { continue; }  
					s = seg[i].split('=');  
					ret[s[0]] = s[1];  
				}  
				return ret;  
			})(),  
			file: (a.pathname.match(/\/([^\/?#]+)$/i) || [,''])[1],  
			hash: a.hash.replace('#',''),  
			path: a.pathname.replace(/^([^\/])/,'/$1'),  
			relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [,''])[1],  
			segments: a.pathname.replace(/^\//,'').split('/')  
		};  
	}	
};


/**
 * 对话框
 */
jQuery.Dialog={
	alert : function(message, onClose){
		bootbox.alert({message:message, title:'信息提示框', callback:function() {
			if(onClose != undefined){
				onClose();
			}
		}});
	},	

	confirm : function(message, onOK){
		bootbox.confirm({message:message, title:'信息提示框', callback:function(result) {
			if(result){
				onOK();
			}
		}}); 
	},
	
	promopt:function(message, onOK){
		bootbox.prompt(message, function(result) {                
			if (result === null) {                                             
				Example.show("Prompt dismissed");                              
			} else {
				if(onOK != undefined){
					onOK(result);
				}
			}
		});
	}
};

jQuery.Events={
	exist:function(element, event){
		var objEvt = $._data(element[0], "events");
		
		return objEvt && objEvt[event];
	}
}

jQuery.JsonUtil={
	extract:function(data, fields){
		var json = {};
		
		for(var field in fields){
			json[field] = data[fields[field]];
		}
		
		return JSON.stringify(json);
	}	
}

jQuery.ImageUtil={
		get:function(fileName, category){
			if(fileName != undefined && fileName != ''){
				fileName = fileName.split(".")[0] + "-" + category + "." + fileName.split(".")[1];
			}
			
			return fileName;
		}	
	}

jQuery.Service={
	invoke:function(options){
		var defaults = {
			url:'url',
			data:{},
			success:function(){},
			fail:function(response){$.Dialog.alert(response.status_text);}
		};
		
		var opts = $.extend(defaults, options);
		var url = opts.url;
		var data = opts.data;
		var successCallBack = opts.success;
		var failCallback = opts.fail;

		$.ajax({
	        url: $.URL.get(url),
	        datatype: "json",
	        contentType : "application/json",
	        type: 'post',
	        data: JSON.stringify(data),
	        success: function (response) {   //成功后回调
	        	if(response.status_code == '0'){
        			successCallBack(response.data);
	        	}else{
	        		failCallback(response);
	        	}
	        }
		});
	},
	
	multipartInvoke:function(options){
		var defaults = {
			url:'url',
			data:{},
			files:new Array(),
			success:function(){}
		};
		
		var opts = $.extend(defaults, options);
		var url = opts.url;
		var data = opts.data;
		var files = opts.files;
		var success = opts.success;
		
		$.ajaxFileUpload({
			url:$.URL.get(url),
			secureuri :false,
			fileElementId :files,//file控件id
			dataType:'json',
			data : data,
			type : 'post',
			success : function (response, status){
				if(response.status_code == '0'){
	        		if(success != undefined){
	        			success(response.data);
	        		}
	        	}else{
	        		$.Dialog.alert(response.status_text);
	        	}
			}
		});
	}
};

jQuery.Input={
	getText:function(element){
		if(element == undefined) return '';
		
		if(element.is("select")){
			var selected = element.find("option:selected");
			if(selected.attr('value') == ''){
				return '';
			}else{
				return selected.text();
			}
		}
		
		return '';
	}
}
  