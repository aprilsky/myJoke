package ${package};

<#list importClasses as imprtClass>
import ${imprtClass};
</#list>
import joke.ext.annoation.Id;
import joke.ext.annoation.MyColumn;
import joke.ext.annoation.MyTable;
/**
*
* @author caoxiao
*/
@MyTable(tableName="${tableName}")
public class ${entityName?cap_first} {

<#list columns as column>
/**
*
*${column.columnComment!}
*/
<#if column.pkColumn>
@Id
</#if>
@MyColumn(columnName = "${column.columnName}")
private ${column.propertyType} ${column.propertyName?uncap_first};
</#list>


<#list columns as column>
public ${column.propertyType} get${column.propertyName?cap_first}(){
return this.${column.propertyName?uncap_first};
}

public void set${column.propertyName?cap_first}(${column.propertyType} ${column.propertyName?uncap_first}){
this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
}
</#list>




}
