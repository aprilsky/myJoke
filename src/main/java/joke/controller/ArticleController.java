package joke.controller;

import joke.domain.Article;
import joke.domain.User;
import joke.service.ArticleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utils.Constant;
import utils.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: caoxiao
 * @Date: 12-11-4 下午3:15
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/toAddArticle")
    public String toAddArticle() {
        return "addArticle";
    }

    @RequestMapping("/saveArticle")
    public String saveArticle(Article article, ModelMap modelMap) {
        //检查用户是否登陆，如果未登录提示请先登录
        User user = getUserForSession();
        if(user==null){
            modelMap.put("errorMsg","请先登录");
            return "login";
        }
        article.setArticleAuthor(user.getUserId());
        article.setArticleStatus(Constant.article_status_wait_approve);
        article.setSubmitTime(new Date());
        articleService.saveOrUpdateArticle(article);
        return listHotArticle(modelMap,null);
    }

    @RequestMapping("/approveArticle")
    public String approveArticle(ModelMap modelMap){
        User user = getUserForSession();
        if(user == null){
            return "login";
        }
        List<Article> articles = articleService.listArticleForApprove(user.getUserId());
        if(CollectionUtils.isEmpty(articles)){
            modelMap.put("msg","noArticle");
            return "approveArticle";
        }
        modelMap.put("article",articles.get(0));
        return "approveArticle";
    }

    @RequestMapping("/approve")
    public String approve(@RequestParam("status") String status,@RequestParam("articleId") Long articleId,ModelMap modelMap){
        User user = getUserForSession();
        if(user == null){
            return "login";
        }
        Article article = new Article();
        article.setArticleId(articleId);
        article.setArticleStatus(status);
        article.setApprovalTime(new Date());
        articleService.saveOrUpdateArticle(article);
        return approveArticle(modelMap);
    }



    @RequestMapping("/listHotArticle")
    public String listHotArticle(ModelMap modelMap,Integer pageNo) {
        //根据评论数排序
        Page<Article> articlePage = new Page<Article>();
        articlePage.setSortName("comment_count");
        if(pageNo!=null){
            articlePage.setPageNo(pageNo);
        }
        articlePage= articleService.listArticleForPage(articlePage,new Article());
        modelMap.put("articlePage",articlePage);
        return "index";
    }

    @RequestMapping("/listArticleComment")
    public String listArticleComment(ModelMap modelMap,HttpServletRequest request) {
        String articleId = request.getParameter("articleId");
        Assert.notNull(articleId,"articleId is not null");
        //通过ID 查询评论

        return "index";
    }




}
