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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("robaksAdmin")
public class RobaksAdminController {
    static final int LIMIT = 10;
    private List<RobakWithOwner> robaksToDisplayList;

    class RobakWithOwner {
        public Robak robak;
        public User owner;

        public RobakWithOwner(Robak robak, User owner){
            this.robak = robak;
            this.owner = owner;
        }
    }

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);
    RobakService robakService = context.getBean(RobakService.class);

    @GetMapping(value = "/page")
    public ModelAndView page(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("sessUser");
        if(user == null || !user.isAdmin()) modelAndView.setViewName("login");
        else {
            robaksToDisplayList = allRobaksWithOwners();
            modelAndView = pages(0,LIMIT);
        }
        return  modelAndView;
    }

    private List<RobakWithOwner> allRobaksWithOwners(){
        List<RobakWithOwner> robaksWithOwners = new ArrayList<>();
        List<Robak> allRobaks = robakService.listRobaks();
        for(Robak each : allRobaks) {
            User owner = userService.get(each.getOwner());
            RobakWithOwner robakWithOwner = new RobakWithOwner(each,owner);
            robaksWithOwners.add(robakWithOwner);
        }
        return  robaksWithOwners;
    }

    @GetMapping(value = "/pages")
    public ModelAndView pages(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        ModelAndView modelAndView = new ModelAndView();
        List<Robak> pageRobakList = new ArrayList<>();
        List<User> pageOwnerList = new ArrayList<>();
        for (int i = page * limit; i < limit + page * limit; i++) {
            if (i < robaksToDisplayList.size()) {
                pageRobakList.add(robaksToDisplayList.get(i).robak);
                pageOwnerList.add(robaksToDisplayList.get(i).owner);
            }
        }

        boolean next = true;
        boolean previous = true;
        if (page == 0) previous = false;
        if ((page + 1) * limit - robaksToDisplayList.size() >= 0) next = false;
        modelAndView.addObject("next", next);
        modelAndView.addObject("previous", previous);
        modelAndView.addObject("pageRobakList", pageRobakList);
        modelAndView.addObject("pageOwnerList", pageOwnerList);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("robaksAdmin");
        return modelAndView;
    }

    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam("robakId") int robakId, @RequestParam("page") int page, @RequestParam("elements") int elements, HttpServletRequest request) {
        Robak robak = robakService.get(robakId);
        robakService.remove(robak);
        if(page == 0){}
        else if(elements==1) page = page -1;
        robaksToDisplayList = allRobaksWithOwners(); // TODO add search delete option
        return pages(page, LIMIT);
    }

    @PostMapping(value = "/search")
    public ModelAndView search(@RequestParam("key") String key) {
        List <RobakWithOwner> searchedList = new ArrayList<>();
        List <RobakWithOwner> allRobaksWithOwners = allRobaksWithOwners();
        for(RobakWithOwner each : allRobaksWithOwners) {
            if(each.owner.getUsername().contains(key)) searchedList.add(each);
            else if (each.robak.getName().contains(key)) searchedList.add(each);
        }
        robaksToDisplayList = searchedList;
        ModelAndView modelAndView = pages(0,LIMIT);
        return modelAndView;
    }
}
