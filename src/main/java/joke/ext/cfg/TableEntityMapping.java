package joke.ext.cfg;
/**
 * @Author: caoxiao
 * @Date: 12-11-23 下午3:05
 */

import java.lang.reflect.Field;
import java.util.*;


public class TableEntityMapping {

    //eg:t_user
    private String tableName;
    //eg:privete joke.domain.User...
    private Field primaryField;

    //数据库列与domain字段 映射
    private Map<Column, Field> columnFieldMap = new HashMap<Column, Field>();

    //数据库列名字与domain字段 映射 eg:user_name:userName
    private Map<String, String> columnNameFieldNameMap = new HashMap<String, String>();

    //doamin列与数据库列名映射
    private Map<Field, String> fieldColumnNameMap = new HashMap<Field, String>();

    /**
     * 添加列和字段映射关系
     *
     * @param column 列
     * @param field  字段
     */
    public void addColumnWithField(Column column, Field field) {
        columnFieldMap.put(column, field);
        //eg:user_name
        String columnName = column.getColumnName();
        //eg:userName
        String fieldName = field.getName();
        columnNameFieldNameMap.put(columnName, fieldName);
        fieldColumnNameMap.put(field, columnName);
    }

    public String getColumnNameByField(Field field) {
        return fieldColumnNameMap.get(field);
    }

    /**
     * 获取映射对象中所有字段
     *
     * @return 字段集合
     */
    public List<Field> getFieldList() {
        Collection<Field> fields = columnFieldMap.values();
        List<Field> fieldList = new ArrayList<Field>(fields);
        return fieldList;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public Field getPrimaryField() {
        return primaryField;
    }


    public void setPrimaryField(Field primaryField) {
        this.primaryField = primaryField;
    }


}

