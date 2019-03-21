<#assign entity=obj.build.list.@bean>
<#assign form = "frm_" + entity?uncap_first + "_query" >

<#assign macro="@macro">
<#assign colNum = obj.build.list.input.@col?number >

<div id="Page_${entity}List" class="container-fluid" data-role="page">
	<${macro}.Breadcrumb/>
	
	<div class="row-fluid">
		<div class="span12">
			<form id="${form}" class="form-horizontal" style="margin: 0 0 0px;!important">
				<#list obj.build.list.input.field as field>
				<#if field_index % colNum == 0>
				<#if field_index gt 0></div></#if>
				<div class="row-fluid">
				</#if>
					<div class="span${12/colNum} ">
						<${macro}.form_text label="${field.@label[0]?default(field.@name)}" name="${field.@name}"/>
					</div>
				</#list>
				</div>		
				
				<div class="row-fluid">
					<div class="span12"  style="text-align:right">
						<${macro}.button_query/>
						
        				<${macro}.button_reset/>
     					
     					<${macro}.button_add page="Page_${entity}Detail" form="frm_${entity?uncap_first}"/>
     					
        				<${macro}.button_delete id="btn_delete" table="datatable_${entity?uncap_first}" url="/persist/${entity}/Delete.action" message="是否要删除当前选择的角色?" success="btn_query" />
					</div>
					<!--/span-->
				</div>
			</form>
			<!-- END VALIDATION STATES-->
		</div>
	</div>
	
	<div class="row-fluid">
		<${macro}.dataTable id="datatable_${entity?uncap_first}" statement="query${entity}Info" criteria="${form}">
			<thead>
				<tr role="row">
					<th tabindex="0"><input type="checkbox" class="dataTable_selectAll"/></th>
					<th tabindex="1">编号</th>
					<th tabindex="2">名称</th>
					<th tabindex="3">类型</th>
					<th tabindex="4">状态</th>
					<th tabindex="5"></th>
				</tr>
			</thead>

			<tbody/>
		</table>
	</div>
</div>