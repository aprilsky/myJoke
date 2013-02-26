package utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.log.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * @Author: caoxiao
 * @Date: 12-11-21 下午5:04
 */
public class DeveloperUtil {

    private DataSource dataSource;

    private String baseEntityPath = "./src/main/java/";

    private String domainPackage = "joke.domain";

    //if tableName isEmpty means all tables;
    private String tableName ;

    private String servicePackage = "joke.service";


    public void generateCRUDByEntity(String className){

        try {
            Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
            Class<?> tClass = Class.forName(className);
            String simpleName = tClass.getSimpleName();
            Field[] fields = tClass.getDeclaredFields();
            String serviceFileStr = baseEntityPath+servicePackage.replaceAll("\\.","/")+"/"+simpleName+"Service.java";
            //检查service impl是否存在
            File serviceFile = new File(serviceFileStr);
            System.out.println(serviceFile.getAbsolutePath());
            if(!serviceFile.exists()){
                serviceFile.createNewFile();
            }
            String implFileStr = baseEntityPath+servicePackage.replaceAll("\\.","/")+"/"+simpleName+"ServiceImpl.java";
            File implFile = new File(implFileStr);
            if(!implFile.exists()){
                implFile.createNewFile();
            }
            //CRUD
            Configuration configure = createConfigure();
            Template serviceTemplate = configure.getTemplate("ServiceTemplate.ftl");
            FileWriter serviceFileWriter = new FileWriter(serviceFile);
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("domain",simpleName);
            serviceTemplate.process(model, serviceFileWriter);
            serviceFileWriter.close();
            Template implTemplate = configure.getTemplate("ServiceImplTemplate.ftl");
            FileWriter implFileWriter = new FileWriter(implFile);
            model.put("tableName",tableName);
            //实现类在 实现分页查询的时候，默认要带上查询条件，条件就是主键，外键（猜的）
            List<String> queryConditions = new ArrayList<String>();
            queryConditions.add("comment_id");
            queryConditions.add("user_id");
            model.put("queryConditions",queryConditions);
            implTemplate.process(model,implFileWriter);
            implFileWriter.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //print Execption
        } catch (IOException e) {
            e.printStackTrace();  //print Execption
        } catch (TemplateException e) {
            e.printStackTrace();  //print Execption
        }
    }
    public void generateEntityAndConfigure() {
        Connection connection = null;
        ResultSet tableResultSet = null;
        try {
            connection = dataSource.getConnection();
            if (connection == null) {
                throw new RuntimeException("获取数据库连接失败。");
            }
            //数据库元数据
            DatabaseMetaData dbMetaData = connection.getMetaData();
            tableResultSet = dbMetaData.getTables(null, null, null,new String[]{"TABLE"});
            Map<String, Object> tablesMetaData = getTablesMetadataByTableResultSet(connection, tableResultSet);

            generateConfigureAndEntities(tablesMetaData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tableResultSet != null) {
                try {
                    tableResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map<String, Object> getTablesMetadataByTableResultSet(
            Connection connection, ResultSet tableResultSet) throws Exception {
        ResultSetMetaData tablesMetaData = tableResultSet.getMetaData();
        int tableNameIndex = getTableNameIndex(tablesMetaData);
        Map<String, Object> fullModel = new HashMap<String, Object>();
        List<Map<String, Object>> tableModels = new ArrayList<Map<String, Object>>();
        fullModel.put("tables", tableModels);
        while (tableResultSet.next()) {
            String t_name = tableResultSet.getString(tableNameIndex);
            if(StringUtils.isNotEmpty(tableName) && tableName.equalsIgnoreCase(t_name)){
                t_name = getTableName();
                //循环每一张表
                String sql = "SELECT * FROM " + t_name + " LIMIT 0";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ResultSetMetaData columnMetaData = resultSet.getMetaData();
                Map<String, Object> tableModel = generateTableModel(t_name,columnMetaData,connection);
                tableModels.add(tableModel);

                resultSet.close();
                statement.close();
            }
        }

        return fullModel;
    }

    private Map<String, Object> generateTableModel(String tableName,
                                                   ResultSetMetaData columnMetaData,Connection connection) throws Exception {
        //获取表列信息
        ResultSet columnSet = connection.getMetaData().getColumns(null, "%", tableName, "%");
        Map<String,String> columnNameCommentMap = new HashMap<String, String>();
        while (columnSet.next()){
            String columnComment = columnSet.getString("REMARKS");
            String columnName = columnSet.getString("COLUMN_NAME");
            columnNameCommentMap.put(columnName,columnComment);
        }

        Map<String, Object> model = new HashMap<String, Object>();
        String entityName = tableName.replace("t_", "");

        entityName = convertDBNameToJavaName(entityName);
        model.put("package", domainPackage);
        /*model.put("annotation", annontation);
        model.put("interfacePackage",interfacePackage);
        model.put("implPackage",implPackage);*/
        model.put("entityName", entityName);
        model.put("tableName", tableName);

        Set<String> importClasses = new HashSet<String>();

        model.put("importClasses", importClasses);
        List<Map<String, Object>> columns = new ArrayList<Map<String, Object>>();
        model.put("columns", columns);

        String pkName = "";
        String pkProperty = "";
        boolean tablePkFound = false;
        int tableColumnCount = columnMetaData.getColumnCount();
        for (int i = 1; i <= tableColumnCount; i++) {
            Map<String, Object> column = new HashMap<String, Object>();
            String columnName = columnMetaData.getColumnName(i);
            String columnComment = columnNameCommentMap.get(columnName);
            String columnClassName = columnMetaData.getColumnClassName(i);
            if ("java.sql.Timestamp".equalsIgnoreCase(columnClassName)
                    || "java.sql.Date".equalsIgnoreCase(columnClassName)) {
                columnClassName = "java.util.Date";
            }
            if (!columnClassName.startsWith("java.lang.")) {
                importClasses.add(columnClassName);
            }
            Class<?> columnType = Class.forName(columnClassName);
            String simpleName = columnType.getSimpleName();
            column.put("columnComment",columnComment);
            column.put("columnName", columnName);
            String propertyName = convertDBNameToJavaName(columnName);
            column.put("propertyName", propertyName);
            column.put("propertyType", simpleName);
            column.put("sqlType", columnMetaData.getColumnTypeName(i));
            if (tablePkFound == false) {
                tablePkFound = autoGuessPk(tableName, columnName);
                if (tablePkFound) {
                    pkName = columnName;
                    pkProperty = propertyName;
                }
                column.put("pkColumn", tablePkFound);
            } else {
                column.put("pkColumn", false);
            }
            columns.add(column);
        }

        if (tablePkFound == false) {
            columns.get(0).put("pkColumn", true);
            pkName = (String) columns.get(0).get("columnName");
            pkProperty = (String) columns.get(0).get("propertyName");
        }

        model.put("pkName", pkName);
        model.put("pkProperty", pkProperty);

        return model;
    }

    private void generateConfigureAndEntities(Map<String, Object> tableMetaData)
            throws Exception {
        Configuration configure = createConfigure();
        File parentDictory = getEntityDictory();

        List<Map<String, Object>> tableModels = (List<Map<String, Object>>) tableMetaData.get("tables");
        for (Map<String, Object> model : tableModels) {
            String entityName = upperCaseFirstChar(model.get("entityName").toString());
            File entityFile = new File(parentDictory, entityName + ".java");
            FileWriter entityWriter = new FileWriter(entityFile);
            Template entityTemplate = configure.getTemplate("EntityTemplate.ftl");
            entityTemplate.process(model, entityWriter);
            entityWriter.close();
        }
    }


    private File getEntityDictory() {
        String path = baseEntityPath + domainPackage.replace('.', '/') + "/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }


    private String convertDBNameToJavaName(String columnName) {
        String[] slices = columnName.split("_");
        String property = slices[0];
        for (int i = 1; i < slices.length; i++) {
            String slice = slices[i];
            slice = upperCaseFirstChar(slice);
            property += slice;
        }

        return property;
    }

    private String upperCaseFirstChar(String slice) {
        if (slice == null || slice.trim().length() == 0) {
            return slice;
        }

        char firstChar = Character.toUpperCase(slice.charAt(0));
        if (slice.length() > 1) {
            slice = firstChar + slice.substring(1);
        } else {
            slice = Character.toString(firstChar);
        }

        return slice;
    }

    private int getTableNameIndex(ResultSetMetaData tablesMetaData)
            throws SQLException {
        int columnCount = tablesMetaData.getColumnCount();
        int tableNameIndex = 0;
        for (int i = 1; i <= columnCount; i++) {
            String columnName = tablesMetaData.getColumnName(i);
            if ("TABLE_NAME".equalsIgnoreCase(columnName)) {
                tableNameIndex = i;
                break;
            }
        }
        return tableNameIndex;
    }

    private boolean autoGuessPk(String tableName, String columnName) {
        if (StringUtils.isBlank(tableName) || StringUtils.isBlank(columnName)) {
            return false;
        }
        String domainName = tableName.substring(1);
        // 第一优先级判断
        while (domainName.contains("_")) {
            if ((domainName + "_id").equalsIgnoreCase(columnName)) {
                return true;
            }
            domainName = domainName.substring(domainName.indexOf("_") + 1);
        }
        if ((domainName + "_id").equalsIgnoreCase(columnName)) {
            return true;
        }
        return false;
    }

    private Configuration createConfigure() throws IOException {
        Configuration config = new Configuration();
        config.setBooleanFormat("true,false");
        config.setDefaultEncoding("utf-8");
        config.setNumberFormat("##########");
        config.setOutputEncoding("utf-8");
        TemplateLoader templateLoader = new ClassTemplateLoader(
                this.getClass(), "");
        config.setTemplateLoader(templateLoader);
        return config;
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
