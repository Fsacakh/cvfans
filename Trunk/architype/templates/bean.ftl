package ${obj.build.bean.@package};

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.app.base.entity.annotation.Validator;
import com.app.base.entity.annotation.Validators;
import com.app.base.sao.rdbms.Constraint;
import com.app.modules.common.BaseEntity;
import com.app.modules.common.Variable;

@Entity(name="${obj.build.bean.@name}")
@Table(name="${obj.build.bean.@table}")
<#assign className = obj.build.bean.@name + 'Entity'>
public class ${className} extends  BaseEntity<String> {
<#list obj.build.table.column as column>
	<#if column.@primary[0]??>
	<#assign pk = column>
	@Id	
	</#if>
	private @Column(name="${column.@name}") ${column.@type} ${column.@alias}; //<#if column.@label[0]??> ${column.@label} <#else> ${column.@alias} </#if>
</#list>
	
	public ${className}(){}
	
<#list obj.build.table.column as column>

	public void set${column.@alias?cap_first}(${column.@type} ${column.@alias}){
		this.${column.@alias} = ${column.@alias};
	}
	
	public ${column.@type} get${column.@alias?cap_first}(){
		return this.${column.@alias};
	}
</#list>
}