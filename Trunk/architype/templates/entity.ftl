<?xml version="1.0" encoding="UTF-8"?>
<build>
	<table name="${obj.name}">
	<#list obj.columns as column>
		<column name="${column.name}" alias="${column.field}" type="${column.type}"/>
	</#list>
	</table>
	
	<bean name="" package="" table="${obj.name}"/>
	
	<form breadcrumb="" col="" bean=""/>
	
	<list>
		<inputs>
		</inputs>
		
		<outputs>
		</outputs>
	</list>
</build>