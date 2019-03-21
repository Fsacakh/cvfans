<#assign entity=obj.build.list.@bean>

$(document).on("shown", "#Page_${entity}Detail", function(event, showName){
});

$(document).on("shown", "#Page_${entity}List", function(event, showName){
	$('#btn_query', this).click();
});

$('#Page_${entity}List #btn_query').on('click', function(){
	$("#datatable_${entity?uncap_first}").dataTable().fnDestroy();
	$("#datatable_${entity?uncap_first}").dataTable({
		"aoColumns": [
			{ "mData": "ROLE_ID","sWidth": "20px", "sClass":"center", "mRender":function(data,type,row){
			return "<input type='checkbox' class='dataTable_select' value='" + data + "' data-delete='{\"id\":\"" + data + "\"}' />";
			}},
			{ "mData": "CODE","sWidth": "100px"},
			{ "mData": "NAME","sWidth": "100px" },
			{ "mData": "ROLETYPE","sWidth": "100px" },
			{ "mData": "STATUS","sWidth": "200px" },
			{ "mData": "ROLE_ID","sWidth": "100px", "mRender":function(data,type,row){
			return "<a id='btn_edit' data-role='edit' data-page='Page_RoleDetail'  data-form='frm_role' data-value='{\"id\":\"" + data + "\"}'>详细</a>";
			}}
        ]
    });
});

$(document).ready(function(){
	$("#Page_${entity}List").trigger("shown");
});