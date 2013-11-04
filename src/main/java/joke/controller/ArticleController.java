package joke.controller;

import joke.domain.Article;
import joke.domain.Comments;
import joke.domain.User;
import joke.service.ArticleService;
import joke.service.CommentsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utils.Constant;
import utils.Page;
import utils.StringUtil;

import java.math.BigInteger;
import java.util.ArrayList;
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
    @Autowired
    CommentsService commentsService;


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
        return listArticle(modelMap, null);
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



    @RequestMapping("/listArticle")
    public String listArticle(ModelMap modelMap,Integer pageNo) {
        String articleType = request.getParameter("articleType");
        //根据评论数排序
        Page<Article> articlePage = new Page<Article>();
        articlePage.setSortName("submit_time");
        Article article = new Article();
        article.setArticleType(articleType);
        if(pageNo!=null){
            articlePage.setPageNo(pageNo);
        }
        articlePage= articleService.listArticleForPage(articlePage,article);
        modelMap.put("articlePage",articlePage);
        if ("tips".equals(articleType)){
            return "listTips";
        }
        return "listArticle";
    }

    @RequestMapping("/articleDetail")
    public String articleDetail(ModelMap modelMap){
        BigInteger articleId = BigInteger.valueOf(Integer.parseInt(request.getParameter("articleId")));
        Article article = articleService.loadArticle(articleId);
        request.setAttribute("article",article);
        List<Comments> list = commentsService.listCommentsByArticleId(articleId);
        int replySize = list.size();
        //循环出一级回应
        List<Comments> commentsList = getCommentsLevel(list);
        request.setAttribute("replySize",replySize);
        request.setAttribute("commentsList",commentsList);
        return "articleDetail";
    }

    private List<Comments> getCommentsLevel(List<Comments> list) {
        List<Comments> commentsList = new ArrayList<Comments>();
        for (Comments comments : list) {
            if(comments.getParentId()==null||comments.getParentId().toString().equals("0")){
                commentsList.add(comments);
            }else{
                addChildToParent(comments,commentsList);
            }
        }
        return commentsList;
    }

    private void addChildToParent(Comments comments, List<Comments> commentsList) {
        for (Comments par : commentsList) {
            if(comments.getParentId()==par.getCommentId()){
                if(par.getChildren()==null){
                    List<Comments> children = new ArrayList<Comments>();
                    children.add(comments);
                    par.setChildren(children);
                }else{
                    par.getChildren().add(comments);
                }
            }
        }
    }


}
