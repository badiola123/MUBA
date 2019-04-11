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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("myRobaks")
public class MyRobaksController {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);
    RobakService robakService = context.getBean(RobakService.class);
    static final int LIMIT = 8;
    private List<Robak> robaksToDisplayList;
    private Map<Integer, String> pictureCode;

    static final String PATH = "C:\\Users\\Mateusz Bujnowicz\\Documents\\Programming\\robakImages\\robak";

    private void generateMap(int userId) {
        List<Robak> myRobaks = robakService.listMyRobaks(userId);
        pictureCode = new HashMap<>();
        for (Robak each : myRobaks) {
            pictureCode.put(each.getRobakId(), Base64.getEncoder().encodeToString(each.getPic()));
        }
    }

    @GetMapping(value = "/page")
    public ModelAndView page(HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("sessUser");
        if (user == null || user.isAdmin()) modelAndView.setViewName("login");
        else {
            robaksToDisplayList = robakService.listMyRobaks(user.getUserId());
            generateMap(user.getUserId());
            modelAndView = pages(0, LIMIT);
        }
        return modelAndView;
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
        modelAndView.addObject("path", PATH);
        modelAndView.addObject("pictures",pictureCode);
        modelAndView.setViewName("myRobaks");
        return modelAndView;
    }

    @PostMapping(value = "/addPage")
    public ModelAndView addPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addRobak");
        User user = (User) request.getSession().getAttribute("sessUser");
        if (user == null || user.isAdmin()) modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "/addRobak")
    public ModelAndView addRobak(@RequestParam("description") String description, @RequestParam("name") String name, @RequestParam("pic") MultipartFile file, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView;
        User user = (User) request.getSession().getAttribute("sessUser");
        Robak robak = new Robak(name, user.getUserId(), description, file.getBytes());
        robakService.add(robak);
        modelAndView = page(request);
        return modelAndView;
    }

    public void fileUpload(byte[] bFile, int id, String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(PATH + id + name + ".jpg");
        fos.write(bFile);
        fos.close();
    }

    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam("robakId") int robakId, @RequestParam("page") int page, @RequestParam("elements") int elements, HttpServletRequest request) {
        Robak robak = robakService.get(robakId);
        robakService.remove(robak);
        if (page == 0) {
        } else if (elements == 1) page = page - 1;
        User user = (User) request.getSession().getAttribute("sessUser");
        robaksToDisplayList = robakService.listMyRobaks(user.getUserId()); // TODO add search delete option
        return pages(page, LIMIT);
    }

}
