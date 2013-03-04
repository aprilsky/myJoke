package joke.service;

import joke.domain.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.Constant;
import utils.Page;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: caoxiao
 * @Date: 12-12-7 上午11:07
 */
@Service
@Transactional
public class ArticleServiceImpl extends BaseService implements ArticleService {
    @Override
    public int saveOrUpdateArticle(Article article) {
        return saveOrUpdateEntity(article);
    }

    @Override
    public List<Article> listArticleForApprove(long userId) {
        StringBuilder builder = new StringBuilder();
        builder.append("select * from t_article where article_status = ? ");
        builder.append(" and article_author != ?");
        Object[] args = {Constant.article_status_wait_approve,userId};
        return jdbcTemplate.query(builder.toString(),args,new BeanPropertyRowMapper<Article>(Article.class));
    }

    @Override
    public Page<Article> listArticleForPage(Page<Article> page, Article article) {
        StringBuilder builder = new StringBuilder();
        builder.append("select * from t_article where article_status = ? ");
        builder.append(" order by comment_count desc ");
        if (page.isAutoPaging()) {
            builder.append("limit ");
            builder.append(page.getStartRow());
            builder.append(",");
            builder.append(page.getPageSize());
        }
        Object[] args = {Constant.article_status_passed};
        int count = jdbcTemplate.queryForInt("select count(*) from t_article where article_status = ? ",args);
        List<Article> list = jdbcTemplate.query(builder.toString(),args,new BeanPropertyRowMapper<Article>(Article.class));
        page.setRows(list);
        page.setTotal(count);
        return page;
    }

    @Override
    public Article loadArticle(BigInteger articleId) {
        StringBuilder builder = new StringBuilder();
        builder.append("select * from t_article where article_id = ? ");
        Object[] args = {articleId};
        List<Article> articles = jdbcTemplate.query(builder.toString(), args, new BeanPropertyRowMapper<Article>(Article.class));
        return articles.get(0);
    }
}
