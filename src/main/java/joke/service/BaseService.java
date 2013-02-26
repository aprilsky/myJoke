package joke.service;


import joke.ext.cfg.BeanDefineConfigue;
import joke.ext.cfg.TableEntityMapping;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: caoxiao
 * @Date: 12-11-22 上午10:45
 */
public class BaseService {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private BeanDefineConfigue configue;


    protected int saveOrUpdateEntity(Object entity) {
        Map<Class, TableEntityMapping> tableEntityMappingMap = configue.getTableEntityMapping();
        TableEntityMapping entityMapping = tableEntityMappingMap.get(entity.getClass());
        String tableName = entityMapping.getTableName();
        Field primaryField = entityMapping.getPrimaryField();
        Object primaryValue = getFileValueByObj(primaryField, entity);
        List<Field> fields = entityMapping.getFieldList();
        Map<Field, Object> map = new HashMap<Field, Object>();
        for (Field field : fields) {
            Object objValue = getFileValueByObj(field, entity);
            if (objValue != null) {
                map.put(field, objValue);
            }
        }
        if (MapUtils.isEmpty(map)) {
            return 0;
        }
        if (primaryValue == null) {
            StringBuilder sb = new StringBuilder("insert into ").append(tableName);
            sb.append(" (");
            List<Object> objects = new ArrayList<Object>();
            for (Field field : map.keySet()) {
                sb.append(entityMapping.getColumnNameByField(field)).append(",");
            }
            String str = sb.substring(0, sb.lastIndexOf(","));
            sb = new StringBuilder(str);
            sb.append(") values (");
            for (Object o : map.values()) {
                objects.add(o);
                sb.append("?,");
            }
            String sql = sb.substring(0, sb.lastIndexOf(","));
            sb = new StringBuilder(sql).append(")");
            System.out.println(sb.toString());
            jdbcTemplate.update(sb.toString(), objects.toArray());
        } else {
            StringBuilder sb = new StringBuilder("update ").append(tableName);
            sb.append(" set ");
            List<Object> objects = new ArrayList<Object>();
            for (Map.Entry<Field, Object> objectEntry : map.entrySet()) {
                if (objectEntry.getKey().equals(primaryField)) {
                    continue;
                }
                sb.append(entityMapping.getColumnNameByField(objectEntry.getKey())).append("=?,");
                objects.add(objectEntry.getValue());
            }
            String str = sb.substring(0, sb.lastIndexOf(","));
            str = str.concat(" where ").concat(entityMapping.getColumnNameByField(primaryField)).concat("=").concat(" " + getFileValueByObj(primaryField, entity));
            jdbcTemplate.update(str, objects.toArray());
        }
        return 0;
    }

    private Object getFileValueByObj(Field field, Object entity) {
        field.setAccessible(true);
        try {
            return field.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
