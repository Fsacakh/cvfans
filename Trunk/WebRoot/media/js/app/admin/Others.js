$("#Page_Home").on("onShown", function(event, param){
	
});

/**
 * 服务站列表页面处理
 */
$("#Page_FaultListView").on("onShown", function(event, showName){
	$('#Page_FaultListView #btn_query').trigger('click');
});

/**
 * 服务站详情页面处理
 */
$("#Page_FaultEditView").on("onShown", function(event, params){
	$("#content").wysiwyg({
		controls: {
			bold          : { visible : true },
			italic        : { visible : true },
			underline     : { visible : true },
			strikeThrough : { visible : true },
			
			justifyLeft   : { visible : true },
			justifyCenter : { visible : true },
			justifyRight  : { visible : true },
			justifyFull   : { visible : true },

			indent  : { visible : true },
			outdent : { visible : true },

			subscript   : { visible : true },
			superscript : { visible : true },
			
			undo : { visible : true },
			redo : { visible : true },
			
			insertOrderedList    : { visible : true },
			insertUnorderedList  : { visible : true },
			insertHorizontalRule : { visible : true },

			h4: {
				visible: true,
				className: 'h4',
				command: ($.browser.msie || $.browser.safari) ? 'formatBlock' : 'heading',
				arguments: ($.browser.msie || $.browser.safari) ? '<h4>' : 'h4',
				tags: ['h4'],
				tooltip: 'Header 4'
			},
			h5: {
				visible: true,
				className: 'h5',
				command: ($.browser.msie || $.browser.safari) ? 'formatBlock' : 'heading',
				arguments: ($.browser.msie || $.browser.safari) ? '<h5>' : 'h5',
				tags: ['h5'],
				tooltip: 'Header 5'
			},
			h6: {
				visible: true,
				className: 'h6',
				command: ($.browser.msie || $.browser.safari) ? 'formatBlock' : 'heading',
				arguments: ($.browser.msie || $.browser.safari) ? '<h6>' : 'h6',
				tags: ['h6'],
				tooltip: 'Header 6'
			},
			
			cut   : { visible : true },
			copy  : { visible : true },
			paste : { visible : true },
			html  : { visible: true },
			increaseFontSize : { visible : true },
			decreaseFontSize : { visible : true },
			exam_html: {
				exec: function() {
					this.insertHtml('<abbr title="exam">Jam</abbr>');
					return true;
				},
				visible: true
			}
		  },
		  events: {
			click: function(event) {
				if ($("#click-inform:checked").length > 0) {
					event.preventDefault();
					alert("You have clicked jWysiwyg content!");
				}
			}
		  }
	});
	
	if(params.action == 'edit'){
		$('#frm_fault_edit').form({
			serializer:{
				content:function(element, data){
					var content = data.content;
					$("#content").wysiwyg("setContent", content);
					
					return content;
				}
			}
		}).attr('action', '/MultipartPersist/Fault/Update.action');
		
		$('#frm_fault_edit')._load({
			action:params.action,
			entity:"Fault",
			data:params.data
		});
	}else{
		$('#frm_fault_edit').attr('action', '/MultipartPersist/Fault/Insert.action');
	}
});


$('#Page_FaultListView #btn_query').on('click', function(){
	$("#datatable_station").dataTable().fnDestroy();
	$("#datatable_station").dataTable({
		"aoColumns": [
			{ "mData": "FAULT_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
				return "<div class='checkbox'><label><input type='checkbox' class='dataTable_select' value='" + $.JsonUtil.extract(row, {'faultId':'FAULT_ID'}) + "'/></lable></div>";
			}},
			{ "mData": "FAULT_TITLE","sWidth": "200px"},
			{ "mData": "FAULT_TYPE","sWidth": "100px" },
			{ "mData": "FAULT_SOURCE","sWidth": "100px" },
			{ "mData": "FAULT_AUTHOR","sWidth": "75px"},
			{ "mData": "FAULT_EDITOR","sWidth": "75px"},
			{ "mData": "FAULT_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='edit' class='btn' data-page='Page_FaultEditView' data-value='{\"faultId\":\"" + data + "\"}'><i class='icon-edit'></i> 修改</a>";
				return btn;
			}}
        ]
    });
});


$("#Page_ComplainListView").on("onShown", function(event, showName){
	$('#Page_ComplainListView #btn_query').trigger('click');
});

$('#Page_ComplainListView #btn_query').on('click', function(){
	$("#dt_complain").dataTable().fnDestroy();
	$("#dt_complain").dataTable({
		"aoColumns": [
			{ "mData": "NAME","sWidth": "50px"},
			{ "mData": "COMPLAIN_DATE","sWidth": "80px" },
			{ "mData": "CATEGORY","sWidth": "80px" },
			{ "mData": "STATION_NAME","sWidth": "150px" },
			{ "mData": "SHEET_NO","sWidth": "100px" },
			{ "mData": "STATUS_VALUE","sWidth": "70px"},
			{ "mData": "COMPLAIN_ID","sWidth": "75px","sClass":"operation", "mRender":function(data,type,row){
				var btn = "";
				btn += "<a href='#' data-action='edit' class='btn' data-page='Page_ComplainEditView' data-value='{\"complainId\":\"" + data + "\"}'><i class='icon-edit'></i> 处理</a>";
				return btn;
			}}

        ]
    });
});

$("#Page_ComplainEditView").on("onShown", function(event, param){
	var frm = $('#frm_complain_edit'); 
		
	frm.on("onLoaded", function(event, data){
		if(data.STATUS == '1'){
			$('#btn_save').hide();
		}else{
			frm.attr('action', '/Persist/ServiceComplain/Update.action');
			var processUserId = $('#processUserId', frm);
			var processUserName = $('#processUserName', frm);
			if(processUserId.val() == ''){
				processUserId.val($('#userId').val());
				processUserName.val($('#userName').val());
			}
		}
	});
	
	frm._load({
		action:"edit",
		statementId:"cvfans.queryComplainInfo",
		data:param.data
	});
});
