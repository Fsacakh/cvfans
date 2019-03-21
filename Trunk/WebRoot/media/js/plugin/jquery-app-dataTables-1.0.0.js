/* Set the defaults for DataTables initialisation */
$.extend( true, $.fn.dataTable.defaults, {
	"bAutoWidth": false,
	"bLengthChange":false,
    "iDisplayLength" : 10,
    "bFilter": false,
    "bSort": false,
    "bStateSave" : false,
    "bRetrieve":true,
    "bServerSide": true,
    "bProcessing": true,
    "sAjaxSource" : 'Query/Pagination.action',
    "sPaginationType" : "full_numbers",
    "aoColumnDefs" : [{
        sDefaultContent : '',
        aTargets : [ '_all' ]

    }],
    
    "oLanguage" : { //主要用于设置各种提示文本
			sEmptyTable: "没有找到符合条件的数据",
			sInfo: "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
			sInfoEmpty: "",
			sInfoFiltered: "",
			sInfoPostFix: "",
			sInfoThousands: ",",
			sLoadingRecords: "Loading...",
			sProcessing: "Processing...",
			sUrl: "",
			sZeroRecords: "没有找到符合条件的数据。",
			oPaginate: {
		        sFirst: "首页",
		        sLast: "末页",
		        sNext: "下一页",
		        sPrevious: "上一页"
		    }
	 },
   
     fnServerData: function ( sSource, aoData, fnCallback ) {
    	var context = {};
    	context.statementId = this.attr("data-statement");
    	context.parameters = {};
    	
    	for(var data in aoData){
    		context.parameters[aoData[data].name] = aoData[data].value;
    	}
    	//context.parameters = aoData;
    	
      	$.ajax({
      		"dataType": 'json',
      		"contentType" : "application/json",
      		"type": "POST",
      		"url": $.URL.get(sSource),
      		"data": JSON.stringify(context),
      		"success": function (response){
      			if(response.status_code != '0'){
      				$('.dataTables_processing').css("visibility","hidden");
      				$.Dialog.alert(response.status_text);
      			}else{
      				fnCallback(response);
      			}
      		}
      	});
      	
      	$("input[type='radio'], input[type='checkbox']").uniform();
     },
     
     fnServerParams: function( aoData ){
    	 var frm = this.attr("data-criteria");
    	 if(frm != undefined){
	    	 var values = $("#" + frm).serializeArray();
	    	 $.each(values, function(i, field){
	    		 var value = field.value.trim();
	    		 
	    		 if(value != ''){
		    		 aoData.push(  
			             {"name":field.name,"value":value}
		    	     );
	    		 }
	    	 });
    	 }
     }
});

$.fn.dataTableExt.oApi.fnDblClick=function(data){
	
};
