package joke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: caoxiao
 * @Date: 12-11-22 下午7:25
 * 首页
 */
@Controller
public class IndexController extends BaseController{
    @RequestMapping("index")
    public String index(ModelMap modelMap,HttpServletRequest request,Integer pageNo){
        return super.index(modelMap,pageNo);
    }
}
