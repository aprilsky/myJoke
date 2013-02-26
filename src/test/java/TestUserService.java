import joke.domain.Article;
import joke.domain.User;
import joke.service.ArticleService;
import joke.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Date;

/**
 * @Author: caoxiao
 * @Date: 12-11-21 下午7:03
 */
@ContextConfiguration(locations = { "classpath*:applicationContext*.xml" })
public class TestUserService extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    UserService userServiceAPI;

    @Autowired
    ArticleService articleService;

    @Test
    @Rollback(false)
    public void testSaveOrUpdateUser(){
        User user = new User();
        user.setUserId(6);
        user.setPassWord("000000");
        user.setUserName("admin");
        user.setRegisterTime(new Date());
        user.setUserEmail("caoxiao@21tb.com");
        userServiceAPI.saveOrUpdateUser(user);
    }

    @Test
    @Rollback(false)
    public void testSaveOrUpdateArticle(){
        for (int i = 32; i < 183; i++) {
            Article article = new Article();
            article.setArticleId(new Long(i));
            article.setArticleStatus("passed");
            article.setArticleAuthor(1);
            article.setArticleTitle("这是一个中文"+i);
            article.setArticleContent("hahaha");
            articleService.saveOrUpdateArticle(article);
        }

    }
}
