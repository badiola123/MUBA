package bujny.atlas.controller;

import bujny.atlas.config.AppConfig;
import bujny.atlas.robak.service.RobakService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("allUsers")
public class AllUsersController {
    static final int LIMIT = 10;
    private List<User> usersToDisplayList;

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);
    RobakService robakService = context.getBean(RobakService.class);

    @GetMapping(value = "/page")
    public ModelAndView page(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("sessUser");
        if (user == null || !user.isAdmin()) modelAndView.setViewName("login");
        else {
            usersToDisplayList = userService.listUsers();
            modelAndView = pages(0, LIMIT);
        }
        return modelAndView;
    }

    @GetMapping(value = "/pages")
    public ModelAndView pages(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> pageList = new ArrayList<>();
        for (int i = page * limit; i < limit + page * limit; i++) {
            if (i < usersToDisplayList.size()) {
                if (!usersToDisplayList.get(i).isAdmin()) pageList.add(usersToDisplayList.get(i));
            }
        }
        boolean previous = true;
        boolean next = true;
        if (page == 0) previous = false;
        if ((page + 1) * limit - usersToDisplayList.size() >= 0) next = false;
        modelAndView.addObject("next", next);
        modelAndView.addObject("previous", previous);
        modelAndView.addObject("pageList", pageList);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("allUsers");
        return modelAndView;
    }

    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam("userId") int userId, @RequestParam("page") int page, @RequestParam("elements") int elements, HttpServletRequest request) {
        User user = userService.get(userId);
        userService.remove(user);
        robakService.removeAllOwnersRobaks(userId);
        if(page == 0){}
        else if(elements==1) page = page -1;
        usersToDisplayList = userService.listUsers(); // TODO add search delete option
        return pages(page, LIMIT);
    }

    @PostMapping(value = "/search")
    public ModelAndView search(@RequestParam("key") String key) {
        List <User> searchedList = new ArrayList<>();
        List<User> allUsers = userService.listUsers();
        for(User each : allUsers) {
            if(Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(each.getUsername()).find()) searchedList.add(each);
        }
        usersToDisplayList = searchedList;
        ModelAndView modelAndView = pages(0,LIMIT);
        return modelAndView;
    }
}

