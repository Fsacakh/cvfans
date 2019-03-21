/**
Core script to handle the entire layout and base functions
**/
var App = function () {
    // IE mode
    var isRTL = false;
    var isIE8 = false;
    var isIE9 = false;
    var isIE10 = false;

    var sidebarWidth = 225;
    var sidebarCollapsedWidth = 35;

    var responsiveHandlers = [];

    // theme layout color set
    var layoutColorCodes = {
        'blue': '#4b8df8',
        'red': '#e02222',
        'green': '#35aa47',
        'purple': '#852b99',
        'grey': '#555555',
        'light-grey': '#fafafa',
        'yellow': '#ffb848'
    };

    var handleInit = function() {

        if ($('body').css('direction') === 'rtl') {
            isRTL = true;
        }

        isIE8 = !! navigator.userAgent.match(/MSIE 8.0/);
        isIE9 = !! navigator.userAgent.match(/MSIE 9.0/);
        isIE10 = !! navigator.userAgent.match(/MSIE 10/);
        
        if (isIE10) {
            jQuery('html').addClass('ie10'); // detect IE10 version
        }
    }


    var handleSidebarMenu = function () {
        jQuery('.page-sidebar').on('click', 'li > a', function (e) {
                if ($(this).next().hasClass('sub-menu') == false) {
                    if ($('.btn-navbar').hasClass('collapsed') == false) {
                        $('.btn-navbar').click();
                    }
                    return;
                }

                var parent = $(this).parent().parent();

                parent.children('li.open').children('a').children('.arrow').removeClass('open');
                parent.children('li.open').children('.sub-menu').slideUp(200);
                parent.children('li.open').removeClass('open');

                var sub = jQuery(this).next();
                if (sub.is(":visible")) {
                    jQuery('.arrow', jQuery(this)).removeClass("open");
                    jQuery(this).parent().removeClass("open");
                    sub.slideUp(200, function () {
                            handleSidebarAndContentHeight();
                        });
                } else {
                    jQuery('.arrow', jQuery(this)).addClass("open");
                    jQuery(this).parent().addClass("open");
                    sub.slideDown(200, function () {
                            handleSidebarAndContentHeight();
                        });
                }

                e.preventDefault();
            });

        // handle ajax links
        jQuery('.page-sidebar').on('click', ' li > a.ajaxify', function (e) {
                e.preventDefault();
                App.scrollTop();

                var url = $(this).attr("href");
                var menuContainer = jQuery('.page-sidebar ul');
                var pageContent = $('.page-content');
                var pageContentBody = $('.page-content .page-content-body');

                menuContainer.children('li.active').removeClass('active');
                menuContainer.children('arrow.open').removeClass('open');

                $(this).parents('li').each(function () {
                        $(this).addClass('active');
                        $(this).children('a > span.arrow').addClass('open');
                    });
                $(this).parents('li').addClass('active');

                App.blockUI(pageContent, false);

                $.post(url, {}, function (res) {
                        App.unblockUI(pageContent);
                        pageContentBody.html(res);
                        App.fixContentHeight(); // fix content height
                        App.initUniform(); // initialize uniform elements
                    });
            });
    }

    var handleUniform = function () {
        if (!jQuery().uniform) {
            return;
        }
        var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
        if (test.size() > 0) {
            test.each(function () {
                    if ($(this).parents(".checker").size() == 0) {
                        $(this).show();
                        $(this).uniform();
                    }
                });
        }
    }

    var handleDatePickers = function () {
        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl : App.isRTL()
            });
        }
    }
    
    var handleItemRemove = function () {
    	$('.icon_remove').each(function(){
    		$(this).on('click', function(){
    			$(this).parent.attr('data-target');
                $('#' + target).val();
    		});
    	});
    }
    
    var handleDataTableSelectAll = function () {
    	$('.dataTable_selectAll').each(function(){
    		$(this).on('click', function(){
    			var checked = $(this).parent().hasClass('checked');
    			$(".dataTable_select",$(this).parents(".dataTable")).each(function(){
    				if(checked)
    					$(this).parent().addClass('checked');
    				else
    					$(this).parent().removeClass('checked');
    			});
    		});
    	});
    };
    
    /**
     * 各种按钮处理预定义
     */
    var handleButtonAction = function(){
    	//返回按钮
    	$(document).on("click", "*[data-action='back']", function(){
    		var obj = $(this);
    		var page = obj.parents("div[data-role='page']");
    		page.back();
    	}).on("click", "*[data-action='delete']", function(){ //删除按钮处理
    		var obj = $(this);
    		var target, data, isOk = false;
    		
    		if(obj.data("table") != undefined){
    			data = new Array();
				target = $("#" + obj.data("table"));
				target.find('.dataTable_select').each(function(){
					var json = eval("(" + $(this).attr('value') + ")");
					if($(this).parent().hasClass('checked')) data.push(json);
				});
				
				isOk = data.length > 0;
    		}else{
    			target = $("#" + obj.data("form"));
    			data = target.data("id");
    			
    			isOk = (data != undefined && data != null);
    		}
			
    		
    		if(! isOk){
				$.Dialog.alert("没有记录可以删除， 请确认。");
				return;
			}
    		
    		if( target.data("events") && target.data("events")["onDelete"]){
				var success = target.triggerHandler("onDelete");
				if(success != undefined && !success){
					return false;
				}
			}
    		
			$.Dialog.confirm(obj.data("message"),function(){
				$.Service.invoke({
					url : obj.data("url"),
					data : data,
					success : function (response){
						if( target.data("events") && target.data("events")["onDeleted"]){
							target.trigger("onDeleted");
						}else{
							var success = obj.data("success");
							if(success.startWith('function')){
								eval(success.substring(9) + "()");
							}else{
								$('#' + obj.data("success")).click();
							}
						}
				  	}
				});
			});
    	}).on("click", "*[data-action='submit']", function(event){ //提交按钮处理
    		var obj = $(this);
    		var frm = obj.parents("form");
    		frm.validate();
    		frm.submit();
    		
    	}).on("click", "[data-page]", function(event){ //页面流转除按钮处理
    		var obj = $(this);
    		
    		var context = {};
    		context.action = obj.data('action');
    		context.data = obj.data('value');
    		
    		var page = $('#' + obj.data('page'));
    		page.forward(context);
    		
    		event.preventDefault();
    	});
    };
    
    var handleCascadeSelect=function(){
    	$(document).on("change", "[data-cascade]", function(event){
    		var obj = $(this);
			var value = obj.children('option:selected').val();
			
			var target = $('#' + obj.data('cascade'));
			if(target != undefined && target.length > 0){
				var context = {};
		    	context.statementId = target.data("statement");
		    	context.parameters = {};
		    	eval("context.parameters." + target.data("filter") + "='" +  value + "'");
		    	
		    	$.Service.invoke({
					url: '/Query/List.action',
					data:context,
					success: function (result) {   //成功后回调
						var name = target.data('option-name');
						var value = target.data('option-value');
						$("option:gt(0)", target).remove('option');
						$.each(result, function(index, data){
    						target.append("<option value='" + data[value] + "'>" + data[name] + "</option>");
    					});
				    }
				});
			}
    		
    		event.preventDefault();
    	});
    };
    
    //* END:CORE HANDLERS *//

    return {

        //main function to initiate template pages
        init: function () {

            //IMPORTANT!!!: Do not modify the core handlers call order.

            //core handlers
            handleInit();
            handleUniform();        

            //layout handlers
            handleSidebarMenu(); // handles main menu
            handleItemRemove();
            handleDataTableSelectAll();
            handleCascadeSelect(); //处理下拉框级联显示
            handleButtonAction();
        },

        getURLParameter: function (paramName) {
            var searchString = window.location.search.substring(1),
                i, val, params = searchString.split("&");

            for (i = 0; i < params.length; i++) {
                val = params[i].split("=");
                if (val[0] == paramName) {
                    return unescape(val[1]);
                }
            }
            return null;
        },

        isIE8: function () {
            return isIE8;
        },

        isRTL: function () {
            return isRTL;
        }
    };
}();