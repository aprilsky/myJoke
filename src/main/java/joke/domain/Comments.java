package joke.domain;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import joke.ext.annoation.Id;
import joke.ext.annoation.MyColumn;
import joke.ext.annoation.MyTable;

/**
 * @author caoxiao
 */
@MyTable(tableName = "t_comments")
public class Comments {

    /**
     *
     *
     */
    @Id
    @MyColumn(columnName = "comment_id")
    private BigInteger commentId;
    /**
     *
     *
     */
    @MyColumn(columnName = "article_id")
    private BigInteger articleId;

    /**
     *
     *
     */
    @MyColumn(columnName = "user_name")
    private BigInteger userName;
    /**
     *
     *
     */
    @MyColumn(columnName = "user_id")
    private Long userId;
    /**
     *
     *
     */
    @MyColumn(columnName = "comment_date")
    private Date commentDate;
    /**
     *
     *
     */
    @MyColumn(columnName = "comment_content")
    private String commentContent;
    /**
     *
     *
     */
    @MyColumn(columnName = "comment_status")
    private String commentStatus;
    /**
     *
     *
     */
    @MyColumn(columnName = "parent_id")
    private BigInteger parentId;

    public List<Comments> getChildren() {
        return children;
    }

    public void setChildren(List<Comments> children) {
        this.children = children;
    }

    private List<Comments> children;




    public BigInteger getCommentId() {
        return this.commentId;
    }

    public void setCommentId(BigInteger commentId) {
        this.commentId = commentId;
    }

    public BigInteger getArticleId() {
        return this.articleId;
    }

    public void setArticleId(BigInteger articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCommentDate() {
        return this.commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentContent() {
        return this.commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentStatus() {
        return this.commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public BigInteger getParentId() {
        return this.parentId;
    }

    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    public BigInteger getUserName() {
        return userName;
    }

    public void setUserName(BigInteger userName) {
        this.userName = userName;
    }
}
