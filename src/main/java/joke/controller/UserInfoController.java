package joke.controller;

import joke.domain.User;
import joke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: caoxiao
 * @Date: 12-11-21 下午4:06
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    @Autowired
    UserService userService;

    /**
     * 跳往注册页面
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    /**
     * 跳往登陆页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    /**
     * 登陆逻辑
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(User user,ModelMap model,HttpServletRequest request){
        user = userService.loginUser(user);
        if(user ==null){
            model.put("msg","userName or PassWord error");
            return "login";
        }
        //登陆成功，保存session
        request.getSession().setAttribute(Constant.session_user_key,user);
        user.setUserStatus(Constant.online);
        //更新用户状态
        userService.saveOrUpdateUser(user);
        return index(model,1);
    }

    /**
     * 退出逻辑
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request,ModelMap modelMap){
        User user = (User) request.getSession().getAttribute(Constant.session_user_key);
        user.setUserStatus(Constant.offline);
        userService.saveOrUpdateUser(user);
        request.getSession().removeAttribute(Constant.session_user_key);
        return "redirect:/index.htm";
    }

    /**
     * 保存注册信息
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveRegister")
    public String saveRegister(User user,ModelMap model,HttpServletRequest request){
        if(user == null || user.getPassWord()==null ||user.getUserEmail()==null){
            return "exception";
        }
        user.setRegisterTime(new Date());
        userService.registerUser(user);
        return login(user,model,request);
    }



}
