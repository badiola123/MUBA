package bujny.atlas.controller;

import bujny.atlas.config.AppConfig;
import bujny.atlas.user.entity.User;
import bujny.atlas.user.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("robaksAdmin")
public class RobaksAdminController {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @GetMapping(value = "/page")
    public ModelAndView page(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("robaksAdmin");
        User user = (User) request.getSession().getAttribute("sessUser");
        if(user == null || !user.isAdmin()) modelAndView.setViewName("login");
        return  modelAndView;
    }
}
