<#assign entity=obj.build.form.@bean>
<#assign macro="@macro">

<#assign colNum = obj.build.form.@col?number >
<#assign form = "frm_" + entity?uncap_first >

<div id="Page_${entity}Detail" data-role='page' class="container-fluid hidden">
	<${macro}.Breadcrumb subtitle="${obj.build.form.@breadcrumb[0]?default("0")}"/>
		
	<div class="row-fluid">
		<div class="span12">
			<form id="${form}" data-entity="${entity}" class="form-horizontal">
				<input id="id" name="id" type="hidden">
				<#list obj.build.table.column as column>
				<#if column_index % colNum == 0>
				<#if column_index gt 0></div></#if>
				<div class="row-fluid">
				</#if>
					<div class="span${12/colNum} ">
						<${macro}.form_text label="${column.@label[0]?default(column.@alias)}" name="${column.@alias}"/>
					</div>
				</#list>
				</div>			

				<div class="row-fluid">
					<div class="span12" style="text-align:right">
						<${macro}.button_save form="${form}" success="back"/>
						<${macro}.button_back/>
						<${macro}.button_reset/>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>