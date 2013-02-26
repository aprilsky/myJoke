package joke.ext.cfg;

/**
 * @Author: caoxiao
 * @Date: 12-11-23 下午4:35
 */
public class Column {
    String columnName ;
    String columnType;

    public Column(String columnName, String columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
