package bujny.atlas.controller;

import bujny.atlas.config.AppConfig;
import bujny.atlas.robak.entity.Robak;
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
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("robaksUser")
public class RobaksUserController {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    RobakService robakService = context.getBean(RobakService.class);
    UserService userService = context.getBean(UserService.class);

    static final int LIMIT = 8;
    private List<Robak> robaksToDisplayList;
    private Map<Integer, String> pictureCode;
    private Map<Integer, String> owners;

    private void generatPictureCodeMap() {
        List<Robak> myRobaks = robakService.listRobaks();
        pictureCode = new HashMap<>();
        for (Robak each : myRobaks) {
            pictureCode.put(each.getRobakId(), Base64.getEncoder().encodeToString(each.getPic()));
        }
    }

    private void generateOwnersMap() {
        owners = new HashMap<>();
        List<User> allUsers = userService.listUsers();
        for (User each: allUsers) {
            owners.put(each.getUserId(),each.getUsername());
        }
    }

    @GetMapping(value = "/page")
    public ModelAndView page(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("sessUser");
        if(user == null || user.isAdmin()) modelAndView.setViewName("login");
        else {
            robaksToDisplayList = robakService.listRobaks();
            generatPictureCodeMap();
            generateOwnersMap();
            modelAndView = pages(0, LIMIT);
        }
        return  modelAndView;
    }

    @GetMapping(value = "/pages")
    public ModelAndView pages(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        ModelAndView modelAndView = new ModelAndView();
        List<Robak> pageRobakList = new ArrayList<>();
        for (int i = page * limit; i < limit + page * limit; i++) {
            if (i < robaksToDisplayList.size()) {
                pageRobakList.add(robaksToDisplayList.get(i));
            }
        }
        boolean next = true;
        boolean previous = true;
        if (page == 0) previous = false;
        if ((page + 1) * limit - robaksToDisplayList.size() >= 0) next = false;
        modelAndView.addObject("next", next);
        modelAndView.addObject("previous", previous);
        modelAndView.addObject("pageList", pageRobakList);
        modelAndView.addObject("page", page);
        modelAndView.addObject("pictures",pictureCode);
        modelAndView.addObject("owners",owners);
        modelAndView.setViewName("robaksUser");
        return modelAndView;
    }

    @PostMapping(value = "/search")
    public ModelAndView search(@RequestParam("key") String key) {
        List <Robak> searchedList = new ArrayList<>();
        List <Robak> allRobaks = robakService.listRobaks();
        for(Robak each : allRobaks) {
            if(Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(each.getName()).find()) searchedList.add(each);
            else if (Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(owners.get(each.getOwner())).find()) searchedList.add(each);
        }
        robaksToDisplayList = searchedList;
        ModelAndView modelAndView = pages(0,LIMIT);
        return modelAndView;
    }
}
