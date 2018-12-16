package edu.eskola.muba.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.user.entity.User;
import edu.eskola.muba.user.service.UserService;

@Controller
@SessionAttributes("sessUser")
public class LoginController {
	
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	UserService userService = context.getBean(UserService.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String helloWorld(Model m) {
		return "login"; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView checkUser(@RequestParam("username")String username, @RequestParam("password")String password) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		if(userService.checkUser(username, password)) {
			User user = new User(username, password);
			modelAndView.addObject("sessUser", user);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutUser(Model m, SessionStatus status) {
		status.setComplete();
		return "redirect:/loggedOut.html";
	}
	
	@RequestMapping(value = "/loggedOut", method = RequestMethod.GET)
	public String loggedOut() {
		return "login";
	}
}
