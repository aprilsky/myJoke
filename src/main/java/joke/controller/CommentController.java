package joke.controller;

import joke.domain.Article;
import joke.domain.Comments;
import joke.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author: caoxiao
 * @Date: 13-2-27 下午3:31
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
    @Autowired
    CommentsService commentsService;

    @RequestMapping("/list")
    public String listArticleComment(ModelMap modelMap,HttpServletRequest request,Integer pageNo) {
        String articleId = request.getParameter("articleId");
        Assert.notNull(articleId, "articleId is not null");

        //通过ID 查询评论
        Comments comments = new Comments();
        comments.setArticleId(new BigInteger(articleId));
        Page<Comments> page = new Page<Comments>();
        if(pageNo!=null){
            page.setPageNo(pageNo);
        }
        page = commentsService.listCommentsForPage(page,comments);
        modelMap.put("commentPage",page);
        return "listComment";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String saveComment(Comments comments,ModelMap modelMap,HttpServletResponse response){

        comments.setUserId(getUserForSession().getUserId());
        comments.setCommentDate(new Date());
        commentsService.saveOrUpdateComments(comments);
        if(comments.getArticleId()!=null){
            Article article = articleService.loadArticle(comments.getArticleId());
            article.setCommentCount(article.getCommentCount()+1);
            articleService.saveOrUpdateArticle(article);
        }
        sendSuccessMessage(response,null);
        return null;
    }
}
