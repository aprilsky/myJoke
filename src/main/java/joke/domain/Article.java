package joke.domain;

import java.math.BigInteger;
import java.util.Date;

import joke.ext.annoation.Id;
import joke.ext.annoation.MyColumn;
import joke.ext.annoation.MyTable;

/**
 * @author caoxiao
 */
@MyTable(tableName = "t_article")
public class Article {

    /**
     * 主键
     */
    @Id
    @MyColumn(columnName = "article_id")
    private Long articleId;
    /**
     * 类型
     */
    @MyColumn(columnName = "article_type")
    private String articleType;
    /**
     * 标题
     */
    @MyColumn(columnName = "article_title")
    private String articleTitle;
    /**
     * 内容
     */
    @MyColumn(columnName = "article_content")
    private String articleContent;
    /**
     * 图片路径
     */
    @MyColumn(columnName = "picture_url")
    private String pictureUrl;
    /**
     * 作者
     */
    @MyColumn(columnName = "article_author")
    private Long articleAuthor;
    /**
     * 提交时间
     */
    @MyColumn(columnName = "submit_time")
    private Date submitTime;
    /**
     * 状态
     */
    @MyColumn(columnName = "article_status")
    private String articleStatus;
    /**
     * 审批日期
     */
    @MyColumn(columnName = "approval_time")
    private Date approvalTime;
    /**
     * 评论数量
     */
    @MyColumn(columnName = "comment_count")
    private Long commentCount;


    public Long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleType() {
        return this.articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleTitle() {
        return this.articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return this.articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public long getArticleAuthor() {
        return this.articleAuthor;
    }

    public void setArticleAuthor(long articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getArticleStatus() {
        return this.articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Date getApprovalTime() {
        return this.approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Long getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }


}
