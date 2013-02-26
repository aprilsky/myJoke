package joke.ext.cfg;

import joke.ext.annoation.Id;
import joke.ext.annoation.MyColumn;
import joke.ext.annoation.MyTable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

public class BeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent> {

    private Map<Class, TableEntityMapping> classEntityMapping = new HashMap<Class, TableEntityMapping>();

    /**
     * 当一个ApplicationContext被初始化或刷新触发
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ApplicationContext applicationContext = event.getApplicationContext();
        // 获取带有MyTable标记的Annotation Bean
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MyTable.class);
        Collection<Object> values = beansWithAnnotation.values();
        // 循环添加对象及映射关系
        for (Object object : values) {
            Class<?> entityClass = object.getClass();
            //解析实体类返回实体映射对象
            TableEntityMapping mapping = analyzeClass(entityClass);
            classEntityMapping.put(entityClass, mapping);
        }
    }

    /**
     * 解析实体类
     *
     * @param entityClass 实体类
     * @return 实体映射关系对象
     */
    private TableEntityMapping analyzeClass(Class<?> entityClass) {
        // 如果实体类中不存在MyTable的Annotation则返回null
        if (!entityClass.isAnnotationPresent(MyTable.class)) {
            return null;
        }
        TableEntityMapping mapping = new TableEntityMapping();
        // 添加实体类到mapping集合

        // 通过Annotation的Table标记获取table对象
        MyTable tableAnnotation = entityClass.getAnnotation(MyTable.class);
        // 添加表名到mapping集合
        mapping.setTableName(tableAnnotation.tableName());

        // 获取实体类所表示的类或接口所声明的所有字段，包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段
        Field[] classFieldArray = entityClass.getDeclaredFields();
        if (classFieldArray == null) {
            return null;
        }
        // 循环处理获得的字段
        for (Field field : classFieldArray) {
            processField(mapping, field);
        }
        return mapping;
    }

    private void processField(TableEntityMapping mapping, Field field) {
        // 判断字段带有Column标记
        if (field.isAnnotationPresent(MyColumn.class)) {
            // 通过Column标记获取对应的Column对象
            MyColumn columnAnnotation = field.getAnnotation(MyColumn.class);
            String columnName = columnAnnotation.columnName();
            String columnType = columnAnnotation.columnType();
            Column column = new Column(columnName, columnType);
            // 保存列和字段到mapping集合
            mapping.addColumnWithField(column, field);

            if (field.isAnnotationPresent(Id.class)) {
                // 获取主键
                Field primaryField = mapping.getPrimaryField();
                // 如果已经有Column为主键的字段存在 则抛出 多个 带有Id标记的异常
                if (primaryField != null) {
                    throw new RuntimeException("Only one column can be marked with Id annottion!");
                }
                // 保存主键字段
                mapping.setPrimaryField(field);
            }
        }
    }


    public Map<Class,TableEntityMapping> getTableEntityMapping() {
        return classEntityMapping;
    }
}
