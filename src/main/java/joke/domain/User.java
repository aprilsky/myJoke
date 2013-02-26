package joke.domain;

import joke.ext.annoation.Id;
import joke.ext.annoation.MyColumn;
import joke.ext.annoation.MyTable;

import java.util.Date;

/**
 * @author caoxiao
 */
@MyTable(tableName="t_user")
public class User {

    @Id
    @MyColumn(columnName = "user_id")
    private Long userId;
    @MyColumn(columnName = "user_email")
    private String userEmail;
    @MyColumn(columnName = "user_name")
    private String userName;
    @MyColumn(columnName = "user_status")
    private String userStatus;
    @MyColumn(columnName = "register_time")
    private Date registerTime;
    @MyColumn(columnName = "pass_word")
    private String passWord;


    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


}
