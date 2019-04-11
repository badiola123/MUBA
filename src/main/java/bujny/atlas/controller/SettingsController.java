package bujny.atlas.controller;

import bujny.atlas.config.AppConfig;
import bujny.atlas.user.entity.User;
import bujny.atlas.user.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("settings")
public class SettingsController {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @GetMapping(value = "/page")
    public ModelAndView page(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("sessUser");
        if (user == null) modelAndView.setViewName("login");
        if (user.isAdmin()) modelAndView.setViewName("settingsAdmin");
        else modelAndView.setViewName("settingsUser");
        return modelAndView;
    }

    @PostMapping(value = "/password")
    public ModelAndView password(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("sessUser");
        modelAndView.setViewName("settingsUser");
        if (!user.getPassword().equals(oldPassword)) {
            System.out.println(user.getPassword());
            System.out.println(oldPassword);
            modelAndView.addObject("error", "Złe hasło");
        } else {
            modelAndView.addObject("success", "Hasło zmienione poprawnie");
            userService.changePass(newPassword, user.getUserId());
        }
        return modelAndView;
    }
}
