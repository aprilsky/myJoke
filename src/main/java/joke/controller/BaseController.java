package joke.controller;

import joke.domain.Article;
import joke.domain.User;
import joke.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import utils.Constant;
import utils.HtmlUtil;
import utils.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: caoxiao
 * @Date: 12-11-15 下午5:23
 */
public class BaseController {
    public final static String SUCCESS ="success";

    public final static String MSG ="msg";

    @Autowired
    ArticleService articleService;

    protected HttpServletRequest request;

    @ModelAttribute("ctx")
    public String getCtx() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getContextPath();
    }


    public User getUserForSession(){
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object obj = request.getSession().getAttribute(Constant.session_user_key);
        if(obj == null){
            return null;
        }
        User user = (User) obj;
        return user;
    }

    /**
     * 首页 如果没有登录，就跳到index.ftl
     *     如果已经登录就跳转到 最新心得页面
     * @param modelMap
     * @return
     */
    public String index(ModelMap modelMap,Integer pageNo) {
        /*//根据评论数排序
        //根据评论数排序
        Page<Article> articlePage = new Page<Article>();
        articlePage.setSortName("comment_count");
        articlePage.setPageNo(pageNo==null?0:pageNo);
        articlePage= articleService.listArticleForPage(articlePage,new Article());
        modelMap.put("articlePage",articlePage);*/
        User user = getUserForSession();
        if(user!=null){
            return alreadyLoginIndex();
        }
        return "index";
    }

    /**
     * 会员的首页
     * @return
     */
    private String alreadyLoginIndex() {
        return null;
    }

    /**
     *
     * 提示成功信息
     *
     * @param message
     *
     */
    public void sendSuccessMessage(HttpServletResponse response,  String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SUCCESS, true);
        if(message==null){
            result.put(MSG, message);
        }
        HtmlUtil.writerJson(response, result);
    }

    /**
     *
     * 提示失败信息
     *
     * @param message
     *
     */
    public void sendFailureMessage(HttpServletResponse response,String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SUCCESS, false);
        result.put(MSG, message);
        HtmlUtil.writerJson(response, result);
    }



}
