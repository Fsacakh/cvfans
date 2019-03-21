$(document).on("shown", "#Page_RoleDetail", function(event, showName){
});

$(document).on("shown", "#Page_RoleList", function(event, showName){
	$('#btn_query', this).click();
});

$('#Page_RoleList #btn_query').on('click', function(){
	$("#datatable_role").dataTable().fnDestroy();
	$("#datatable_role").dataTable({
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
	$("#Page_RoleList").trigger("shown");
});