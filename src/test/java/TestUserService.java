import joke.domain.Article;
import joke.domain.User;
import joke.service.ArticleService;
import joke.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
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

    static Logger logger = Logger.getLogger(TestUserService.class.getName());


    @Test
    public void testLog4j(){
        System.out.println(logger.isInfoEnabled());
        logger.info("info not enable!");
        logger.warn("warn is enable!");
    }

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
    public void testWord(){
        String str = "comment_id";
        //首字母大写;
        String str1 = str.substring(0,1).toUpperCase()+str.substring(1);
        System.out.println(str1);
        String[] arr = str1.split("_");
        for (String s : arr) {
            System.out.println();
        }
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
