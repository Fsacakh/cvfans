<#--页面面包絮创建宏-->
<#macro Breadcrumb>
  	<div class="row">
		<ul class="breadcrumb">
			<#nested>
		</ul>
	</div>
</#macro>

<#macro form_text label name placeholder="" value="" span="4,8" readonly="false" deserialized="true" bind="">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<input id="${name}" name="${name}" type="text" class="form-control" <#if bind != ''> data-bind='${bind}' </#if> <#if deserialized=='false'> deserialized='false' </#if> <#if readonly!='false'>disabled</#if> placeholder="${placeholder}">
		</div>
	</div>
</#macro>

<#macro field_text name placeholder="" value="" readonly="false" deserialized="true" bind="">
	<input id="${name}" name="${name}" type="text" class="form-control" <#if bind != ''> data-bind='${bind}' </#if> <#if deserialized=='false'> deserialized='false' </#if> <#if readonly!='false'>disabled</#if> placeholder="${placeholder}">
</#macro>

<#macro form_password label name placeholder="" span="4,8" deserialized="true">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<input id="${name}" name="${name}" type="password" class="form-control" placeholder="${placeholder}">
		</div>
	</div>
</#macro>


<#--表单文本框输出定义-->
<#macro form_textarea label name placeholder="" deserialized="true"  readonly="false"  bind="" value="" rows="3" span="4,8">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<textarea id="${name}" name="${name}" rows="${rows}"  <#if deserialized=='false'> deserialized='false' </#if> <#if bind != ''> data-bind='${bind}' </#if> <#if readonly!='false'>disabled</#if> class="form-control" placeholder="${placeholder}"></textarea>
		</div>
	</div>
</#macro>

<#--表单选择框(不带标签)输出定义-->
<#macro field_select name statementId="" filter="" optionValue="value" optionName="name" cascade="" initial="1"  readonly="false" deserialized="true">
	<select id="${name}" name="${name}" class="form-control" <#if readonly!='false'>disabled</#if> data-statement="${statementId}"  <#if deserialized=='false'> deserialized='false' </#if> data-option-name="${optionName}" data-option-value="${optionValue}" <#if filter != ""> data-filter="${filter}" </#if> <#if cascade != ""> data-cascade="${cascade}" </#if>>
		<#nested>
		<#if initial == "1">
			<#assign items=QueryValuePair(statementId, filter)>
			<#list items as item>
				<option value="${item[optionValue]?default('')}">${item[optionName]?default('')}</option>
			</#list>
		</#if>
	</select>
</#macro>

<#--表单选择框(带标签)输出定义-->
<#macro form_select name label="" statementId="" filter="" optionValue="value" bind=""  optionName="name" cascade="" initial="1" span="4,8"  readonly="false" deserialized="true">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<select id="${name}" name="${name}" class="form-control" <#if bind != ''> data-bind='${bind}' </#if> <#if deserialized=='false'> deserialized='false' </#if> <#if readonly!='false'>disabled</#if> data-statement="${statementId}"  data-option-name="${optionName}" data-option-value="${optionValue}" <#if filter != ""> data-filter="${filter}" </#if> <#if cascade != ""> data-cascade="${cascade}" </#if>>
				<#nested>
				<#if initial == "1">
					<#assign items=QueryValuePair(statementId, filter)>
					<#list items as item>
						<option value="${item[optionValue]?default('')}">${item[optionName]?default('')}</option>
					</#list>
				</#if>
			</select>
		</div>
	</div>
</#macro>

<#macro form_static_select label name span="4,8" deserialized="true">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<select id="${name}" name="${name}" class="form-control" <#if deserialized=='false'> deserialized='false' </#if>>
				<#nested>
			</select>
		</div>
	</div>
</#macro>

<#--列表框(带标签)输出定义-->
<#macro field_listbox name statementId="" filter="" optionValue="value" optionName="name" cascade="" initial="1"  readonly="false" deserialized="true">
	<select id="${name}" name="${name}" style="width:80%;height:30%;" multiple ="multiple" class="form-control" <#if readonly!='false'>disabled</#if> data-statement="${statementId}"  <#if deserialized=='false'> deserialized='false' </#if> data-option-name="${optionName}" data-option-value="${optionValue}" <#if filter != ""> data-filter="${filter}" </#if> <#if cascade != ""> data-cascade="${cascade}" </#if>>
		<#nested>
		<#if initial == "1">
			<#assign items=QueryValuePair(statementId, filter)>
			<#list items as item>
				<option value="${item[optionValue]?default('')}">${item[optionName]?default('')}</option>
			</#list>
		</#if>
	</select>
</#macro>


<#--表单单选按钮输出定义-->
<#macro form_radio label name category  span="4,8" rows="1" cols="1" deserialized="true">
	<#assign items=QueryValuePair(category)>
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<#list items as item>
				<label class="radio">
					<input type="radio" <#if deserialized=='false'> deserialized='false' </#if> name="${name}" value="${item.value}" <#if item_index == 0>checked</#if>/>${item.name}
				</label>
			</#list>
		</div>
	</div>
</#macro>

<#--表单单选按钮输出定义-->
<#macro form_static_radio label name items  span="4,8" rows="1" cols="1" deserialized="true">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<#list items as item>
				<label class="radio">
					<input type="radio" <#if deserialized=='false'> deserialized='false' </#if> name="${name}" value="${item.value}" <#if item_index == 0>checked</#if>/>${item.name}
				</label>
			</#list>
		</div>
	</div>
</#macro>

<#--表单日期选择控件定义-->
<#macro form_date label name placeholder="" value="" bind="" span="4,8" readonly="false" deserialized="true" minDate='1900-01-01' maxDate='2100-12-31' deserialized="true">
<#assign spans=(span?split(","))>
<div class="form-group">
    <label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
    <div class="${"col-lg-" + spans[1]}">
    	<input id="${name}" name="${name}" class="Wdate form-control" type="text" onfocus="WdatePicker({minDate:'${minDate}',maxDate:'${maxDate}'})" <#if bind != ''> data-bind='${bind}' </#if>/>
     </div>
 </div>
</#macro>

<#--表单复选按钮输出定义-->
<#macro form_static_checkbox label name value="1" span="4,8" readonly="false"  deserialized="true">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<div class="checkbox">
			   <label><input type="checkbox" id="${name}" name="${name}" value="${value}" <#if deserialized=='false'> deserialized='false' </#if> <#if readonly!='false'>disabled</#if>>是</label>
			</div>
		</div>	
	</div>
</#macro>

<#--表单复选按钮输出定义-->
<#macro form_checkbox label name statementId="" filter=""  value="value"  span="4,8" cols=1 deserialized="true" readonly="false" >
	<#assign items=QueryValuePair(statementId, filter)>
	<#assign spans=(span?split(","))>     
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<#list items as item>
				<#if item_index gt 0 && item_index % cols = 0 ><br/></#if>
				<label class="checkbox-inline">
					<input type="checkbox" id="${name}_${item.value}" <#if deserialized=='false'> deserialized='false' </#if> <#if readonly!='false'>disabled</#if> name="${name}" value="${item[value]}"/>${item.label}
				</label>
			</#list>
		</div>
	</div>
</#macro>

<#--表单复选按钮输出定义-->
<#macro form_group_checkbox label name category statementId  span="4,8"  root="0" rows=1 cols=1 deserialized="true">
	<#assign items = QueryValuePair(category, statementId)>
	<#assign spans=(span?split(","))>     
	
	<div class="control-group">
		<label class="control-label ${"span" + spans[0]}">${label}</label>
		<div class="controls  ${"span" + spans[1]}">
			<#assign parents = FilterValuePair(items, root)>
			
			<#list parents as parent>
				<div class="row-fluid">
					<div class="span3">
						<label class="checkbox">
							<input type="checkbox" name="${name}" <#if deserialized=='false'> deserialized='false' </#if> value="${parent.value}"/>${parent.name}
						</label>
					</div>
					
					<div class="controls span9">
						<#assign childrens=FilterValuePair(items, parent.id)>
						
						<#list childrens as children>
							<label class="checkbox">
								<input type="checkbox" name="${name}" value="${children.value}"/>${children.name}
							</label>
						</#list>
					</div>
				</div>
			</#list>
		</div>
	</div>
</#macro>

<#--表单附件上传控件定义-->
<#macro form_file label name placeholder="" value="" span="4,8">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<input id="${name}" name="${name}" type="file"  placeholder="${placeholder}">
		</div>
	</div>
</#macro>

<#--表单附件上传控件定义-->
<#macro form_image label name value="" bind="" span="4,8" width="" height="">
	<#assign spans=(span?split(","))>
	
	<div class="form-group">
		<label class="control-label ${"col-lg-" + spans[0]}">${label}</label>
		<div class="${"col-lg-" + spans[1]}">
			<img id="${name}" name="${name}" <#if bind != ''> data-bind='${bind}' </#if>/>
		</div>
	</div>
</#macro>

<#--保存按钮输出定义-->
<#macro button_save form='' success='' id="btn_save" label='保存'>
	<button	id="${id}" type="button" data-action="submit" <#if success != ""> data-success="${success}" </#if> class="btn btn_color">${label}</button>
</#macro>

<#--解除绑定按钮输出定义-->
<#macro button_rmb form success='' id="btn_save" label='解除绑定'>
	<button	id="${id}" type="button" data-action="submit" data-form="${form}" <#if success != ""> data-success="${success}" </#if> class="btn btn_color"><i class="icon-ok"></i>${label}</button>
</#macro>

<#--取消按钮输出定义-->
<#macro button_cancel id="btn_cancel" label='取消'>
	<button id="${id}" type="button" class="btn btn_color ">${label}</button>
</#macro>

<#--返回按钮输出定义-->
<#macro button_back id="btn_back" label='返回'>
	<button id="${id}" type="button" data-action="back" class="btn btn_color">${label}</button>
</#macro>

<#--查询按钮输出定义-->
<#macro button_query id="btn_query" label='检索' class="">
	<button	id="${id}" type="button" class="btn btn_color" class="${class}">${label}</button>
</#macro>

<#--表单重置按钮输出定义-->
<#macro button_reset id="btn_reset" label='重置'>
	<button id="${id}" type="button" class="btn btn_color">${label}</button>
</#macro>

<#--新增按钮输出定义-->
<#macro button_add page id="btn_add" label='新增'>
	<button	id="${id}" type="button" data-action="insert" data-page="${page}" class="btn btn_color"><i class="icon-add"></i>${label}</button>
</#macro>

<#--修改按钮输出定义-->
<#macro button_edit page id="btn_edit" label='修改'>
	<button	id="${id}" type="button" data-action="edit" data-page="${page}" class="btn btn_color"><i class="icon-add"></i>${label}</button>
</#macro>

<#--删除按钮输出定义-->
<#macro button_delete url success="" id="btn_delete" message="选择需要删除的数据。" form="" table="" label="删除">
	<button	id="${id}" type="button" data-action="delete" <#if form != ""> data-form="${form}" </#if> <#if table != "">data-table="${table}" </#if> data-message="${message}" data-url="${url}" data-success="${success}" class="btn btn_color"><i class="icon-delete"></i>${label}</button>
</#macro>

<#macro dataTable id statement criteria>
	<div class="col-md-12">
		<table id="${id}" class="dataTable cell-border border" data-statement="${statement}" 	data-criteria="${criteria}">
			<#nested>
		</table>
	</div>
</#macro>
