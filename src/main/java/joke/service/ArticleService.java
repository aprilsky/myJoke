package joke.service;


import joke.domain.Article;
import utils.Page;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: caoxiao
 * @Date: 12-11-21 下午9:27
 */
public interface ArticleService {

    int saveOrUpdateArticle(Article article);

    List<Article> listArticleForApprove(long userId);

    Page<Article> listArticleForPage(Page<Article> articlePage, Article article);

    Article loadArticle(BigInteger articleId);
}
